package net.offllneplayer.opminecraft.UTIL.debug.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.loading.FMLEnvironment;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Debug class that monitors the Minecraft sound pool
 * Prints information when sounds are added or removed
 */
public class DEBUG_SoundPool {
    // Core state variables
    private static Object soundManager;
    private static Field soundPoolField;
    private static int lastSoundPoolSize = 0;
    private static Map<String, Object> lastSoundHandles = new HashMap<>();
    private static boolean initialized = false;
    private static boolean wasInWorld = false;
    private static boolean isReinitializing = false;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    // Constants
    private static final int SOUND_LIMIT = 247;
    private static final int WARNING_THRESHOLD = 200;
    private static final int CHECK_INTERVAL_MS = 5000;
    private static final int INITIAL_DELAY_MS = 10000;
    

    /**
     * Initialize the debugger
     */
    public static void init() {
        if (!FMLEnvironment.dist.isClient()) {
            log(DEBUG_SoundMessages.CLIENT_ONLY);
            return;
        }

        log(DEBUG_SoundMessages.INIT_START);

        try {
            // Reset state and start monitoring
            resetState();
            initializeSoundPoolAccess();
            startMonitoringThread();
            
            log(DEBUG_SoundMessages.INIT_DONE);
        } catch (Exception e) {
            log(DEBUG_SoundMessages.ERROR_INIT, e.getMessage());
        }
    }
    
    /**
     * Reset the debugger state
     */
    private static void resetState() {
        initialized = false;
        wasInWorld = false;
        lastSoundPoolSize = 0;
        lastSoundHandles.clear();
    }
    
    /**
     * Start the background thread that monitors the sound pool
     */
    private static void startMonitoringThread() {
        Thread debugThread = new Thread(() -> {
            try {
                Thread.sleep(INITIAL_DELAY_MS);

                while (true) {
                    if (!initialized) {
                        initializeSoundPoolAccess();
                    }

                    if (initialized) {
                        checkSoundPool();
                    }

                    Thread.sleep(CHECK_INTERVAL_MS);
                }
            } catch (InterruptedException e) {
                log(DEBUG_SoundMessages.ERROR_CHECK, "Thread interrupted: " + e.getMessage());
            }
        }, "SoundPoolDebugger");

        debugThread.setDaemon(true);
        debugThread.start();
    }

    /**
     * Initialize access to the sound pool using reflection
     */
    @OnlyIn(Dist.CLIENT)
    private static void initializeSoundPoolAccess() {
        if (isReinitializing) return;
        
        try {
            isReinitializing = true;
            
            // Get Minecraft instance and check if we're in a world
            Minecraft minecraft = Minecraft.getInstance();
            boolean inWorld = minecraft != null && minecraft.level != null;
            
            // Check if sound manager is available
            if (minecraft == null || minecraft.getSoundManager() == null) {
                log(DEBUG_SoundMessages.MC_SOUND_MGR_NOT_AVAILABLE, (inWorld ? " (in world)" : " (in menu)"));
                return;
            }

            // Get sound manager and engine
            SoundManager soundManagerInstance = minecraft.getSoundManager();
            SoundEngine soundEngine = getSoundEngine(soundManagerInstance);
            if (soundEngine == null) return;
            
            // Find the sound pool field
            findSoundPoolField(soundEngine, inWorld);
        } catch (Exception e) {
            log(DEBUG_SoundMessages.ERROR_INIT, e.getMessage());
        } finally {
            isReinitializing = false;
        }
    }
    
    /**
     * Get the sound engine from the sound manager
     */
    private static SoundEngine getSoundEngine(SoundManager soundManagerInstance) throws IllegalAccessException {
        for (Field field : SoundManager.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == SoundEngine.class) {
                return (SoundEngine) field.get(soundManagerInstance);
            }
        }
        
        log(DEBUG_SoundMessages.SOUND_ENGINE_NOT_FOUND);
        return null;
    }
    
    /**
     * Find the sound pool field in the sound engine
     */
    private static void findSoundPoolField(SoundEngine soundEngine, boolean inWorld) {
        Class<?> soundEngineClass = soundEngine.getClass();
        int fieldsChecked = 0;
        
        // First try sound-related fields
        for (Field field : soundEngineClass.getDeclaredFields()) {
            field.setAccessible(true);
            fieldsChecked++;
            
            if (isSoundRelatedField(field) && trySetSoundPoolField(field, soundEngine)) {
                if (!wasInWorld) {
                    checkSoundPool(); // Initial check
                }
                return;
            }
        }
        
        // If not found, try any collection field
        log(DEBUG_SoundMessages.CHECKING_ALL_COLLECTION_FIELDS);
        for (Field field : soundEngineClass.getDeclaredFields()) {
            field.setAccessible(true);
            
            if (isCollectionField(field) && trySetSoundPoolField(field, soundEngine)) {
                if (!wasInWorld) {
                    checkSoundPool(); // Initial check
                }
                return;
            }
        }
        
        log(DEBUG_SoundMessages.SOUND_POOL_FIELD_NOT_FOUND, fieldsChecked);
    }
    
    /**
     * Check if a field is likely to be sound-related
     */
    private static boolean isSoundRelatedField(Field field) {
        String fieldName = field.getName().toLowerCase();
        return (fieldName.contains("sound") || 
                fieldName.contains("channel") || 
                fieldName.contains("handle") || 
                fieldName.contains("pool")) &&
               isCollectionField(field);
    }
    
    /**
     * Check if a field is a collection type
     */
    private static boolean isCollectionField(Field field) {
        String typeName = field.getType().getName();
        return typeName.contains("Map") || 
               typeName.contains("Set") || 
               typeName.contains("Collection");
    }
    
    /**
     * Try to set the sound pool field if it's a valid collection
     */
    private static boolean trySetSoundPoolField(Field field, SoundEngine soundEngine) {
        try {
            Object fieldValue = field.get(soundEngine);
            if (fieldValue == null) return false;
            
            int size = getCollectionSize(fieldValue);
            if (size < 0) return false;
            
            String fieldDesc = field.getName().toLowerCase().contains("sound") ? "sound-related" : "";
            String type = getCollectionType(fieldValue);
            log(DEBUG_SoundMessages.FOUND_FIELD, fieldDesc, type, field.getName(), size);
            
            soundPoolField = field;
            soundManager = soundEngine;
            initialized = true;
            
            log(DEBUG_SoundMessages.SUCCESS_INIT);
            return true;
        } catch (Exception e) {
            // Ignore errors for non-collection fields
            return false;
        }
    }
    
    /**
     * Get the size of a collection
     */
    private static int getCollectionSize(Object collection) {
        if (collection instanceof Map<?,?> map) {
            return map.size();
        } else if (collection instanceof Set<?> set) {
            return set.size();
        } else if (collection instanceof Collection<?> col) {
            return col.size();
        }
        return -1;
    }
    
    /**
     * Get the type of a collection as a string
     */
    private static String getCollectionType(Object collection) {
        if (collection instanceof Map) {
            return "map";
        } else if (collection instanceof Set) {
            return "set";
        } else if (collection instanceof Collection) {
            return "collection";
        }
        return "unknown";
    }

    /**
     * Check the sound pool for changes
     */
    @OnlyIn(Dist.CLIENT)
    private static void checkSoundPool() {
        try {
            // Check world state
            Minecraft minecraft = Minecraft.getInstance();
            boolean inWorld = minecraft != null && minecraft.level != null;
            String gameState = inWorld ? "IN WORLD" : "IN MENU";
            
            // Handle world state changes
            if (handleWorldStateChanges(inWorld, gameState)) {
                return;
            }
            
            // Get current sound pool data
            Map<String, Object> currentHandles = getCurrentSoundPoolData(gameState);
            if (currentHandles == null) return;
            
            int currentSize = currentHandles.size();
            
            // Check for changes
            if (currentSize != lastSoundPoolSize) {
                handleSoundPoolChanges(currentHandles, currentSize, gameState);
            } else if (currentSize > WARNING_THRESHOLD) {
                // If size is unchanged but close to limit, log a warning
                log(DEBUG_SoundMessages.WARNING_UNCHANGED,
                        currentSize, SOUND_LIMIT, (currentSize * 100 / SOUND_LIMIT), gameState);
            }
        } catch (Exception e) {
            log(DEBUG_SoundMessages.ERROR_CHECK, e.getMessage());
        }
    }
    
    /**
     * Handle world state changes (entering/leaving world)
     */
    private static boolean handleWorldStateChanges(boolean inWorld, String gameState) {
        if (inWorld && !wasInWorld) {
            log(DEBUG_SoundMessages.ENTERED_WORLD);
            initialized = false;
            wasInWorld = true;
            return true;
        } else if (!inWorld && wasInWorld) {
            log(DEBUG_SoundMessages.LEFT_WORLD);
            wasInWorld = false;
            initialized = false;
            return true;
        }
        
        // Check if initialized
        if (soundManager == null || soundPoolField == null) {
            if (inWorld) {
                log(DEBUG_SoundMessages.REINIT_WHEN_NULL, gameState);
                initialized = false;
            }
            return true;
        }
        
        return false;
    }
    
    /**
     * Get the current sound pool data
     */
    private static Map<String, Object> getCurrentSoundPoolData(String gameState) throws IllegalAccessException {
        Object soundPool = soundPoolField.get(soundManager);
        if (soundPool == null) {
            log(DEBUG_SoundMessages.SOUND_POOL_NULL, gameState);
            return null;
        }
        
        Map<String, Object> currentHandles = new HashMap<>();
        
        // Extract data based on collection type
        if (soundPool instanceof Map<?, ?> map) {
            for (Object key : map.keySet()) {
                currentHandles.put(key.toString(), map.get(key));
            }
        } else if (soundPool instanceof Set<?> set) {
            int i = 0;
            for (Object handle : set) {
                currentHandles.put("Handle_" + i++, handle);
            }
        } else if (soundPool instanceof Collection<?> collection) {
            int i = 0;
            for (Object handle : collection) {
                currentHandles.put("Handle_" + i++, handle);
            }
        }
        
        return currentHandles;
    }
    
    /**
     * Handle changes in the sound pool
     */
    private static void handleSoundPoolChanges(Map<String, Object> currentHandles, int currentSize, String gameState) {
        log(DEBUG_SoundMessages.SIZE_CHANGED, gameState, (currentSize * 100 / SOUND_LIMIT), lastSoundPoolSize, currentSize, SOUND_LIMIT);
        
        // Count changes
        int addedCount = 0;
        int removedCount = 0;
        
        for (String key : currentHandles.keySet()) {
            if (!lastSoundHandles.containsKey(key)) {
                addedCount++;
            }
        }
        
        for (String key : lastSoundHandles.keySet()) {
            if (!currentHandles.containsKey(key)) {
                removedCount++;
            }
        }
        
        // Print summary before detailed logs
        log(DEBUG_SoundMessages.SUMMARY, addedCount, removedCount);
        
        // Log details
        logSoundChanges(currentHandles);
        
        // Update state
        lastSoundPoolSize = currentSize;
        lastSoundHandles = new HashMap<>(currentHandles);
    }
    
    /**
     * Log details of added and removed sounds
     */
    private static void logSoundChanges(Map<String, Object> currentHandles) {
        // Log added sounds
        for (String key : currentHandles.keySet()) {
            if (!lastSoundHandles.containsKey(key)) {
                Object handle = currentHandles.get(key);
                String handleType = handle != null ? handle.getClass().getSimpleName() : "null";
                log(DEBUG_SoundMessages.SOUND_ADDED, key, handleType);
            }
        }
        
        // Log removed sounds
        for (String key : lastSoundHandles.keySet()) {
            if (!currentHandles.containsKey(key)) {
                log(DEBUG_SoundMessages.SOUND_REMOVED, key);
            }
        }
    }

    /**
     * Centralized logger that prints a message based on enum template and its color flags
     */
    private static void log(DEBUG_SoundMessages msg, Object... args) {
       String formatted = args != null && args.length > 0 ? String.format(msg.template(), args) : msg.template();
       String timestamp = LocalDateTime.now().format(formatter);
       String prefix = "[" + timestamp + "] [DEBUG_SoundPool] ";
       String color = msg.color();
		 String separator = "~".repeat(40);
		 String titleseparator = "~".repeat(10);
		 String reset = "\033[0m";

		 boolean header = msg == DEBUG_SoundMessages.INIT_START ||
				 msg == DEBUG_SoundMessages.SUCCESS_INIT ||
				 msg == DEBUG_SoundMessages.ENTERED_WORLD ||
				 msg == DEBUG_SoundMessages.LEFT_WORLD ||
				 msg == DEBUG_SoundMessages.REINIT_WHEN_NULL ||
				 msg == DEBUG_SoundMessages.SOUND_POOL_NULL ||
				 msg == DEBUG_SoundMessages.SIZE_CHANGED;

		 boolean summary = msg == DEBUG_SoundMessages.SUMMARY;

        if (header) {
            System.out.println(color + titleseparator + separator + titleseparator + reset);
            System.out.println(color + prefix + formatted + reset);
            System.out.println(color + titleseparator + separator + titleseparator + reset);
        } else if (summary) {
            System.out.println(color + prefix + formatted + reset);
            System.out.println(color + separator + reset);
        } else {
            System.out.println(color + prefix + formatted + reset);
        }
    }
}