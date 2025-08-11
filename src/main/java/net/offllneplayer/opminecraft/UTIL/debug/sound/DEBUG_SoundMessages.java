package net.offllneplayer.opminecraft.UTIL.debug.sound;

/**
 * Enum of debug messages for the SoundPoolDebugger. Centralizes message
 * templates and colors so the debugger can simply select and format.
 * Colors are defined here instead of in the debugger class.
 */
public enum DEBUG_SoundMessages {
    // Header-style messages (with separators)
    INIT_START("=== Initializing Sound Pool Debugger ===", "\033[1;38;5;22m"),
    SUCCESS_INIT("===--= Sound Pool Debugger successfully initialized =--===", "\033[1;38;5;22m"),
    ENTERED_WORLD("=== ENTERED WORLD - Reinitializing sound pool access ===", "\033[1;38;5;22m"),
    LEFT_WORLD("=== LEFT WORLD - Will reinitialize when entering a world again ===", "\033[1;38;5;22m"),
    REINIT_WHEN_NULL("=== Sound manager or sound pool field is null while %s - attempting to reinitialize", "\033[1;38;5;22m"),
    SOUND_POOL_NULL("=== Sound pool is null while %s", "\033[1;38;5;22m"),
    SIZE_CHANGED("=== [%s] (%d%% of limit) # in pool went from %d -> %d/%d ===", "\033[1;38;5;22m"),

    // Summary uses lime
    SUMMARY("Summary: %d sounds added, %d sounds removed", "\033[1;92m"),

    // Informational and warnings
    CLIENT_ONLY("Sound Pool Debugger only works on client side", "\033[0;32m"),
    INIT_DONE("Sound Pool Debugger initialized", "\033[0;32m"),
    ERROR_INIT("ERROR: Error initializing Sound Pool Debugger: %s", "\033[0;32m"),
    MC_SOUND_MGR_NOT_AVAILABLE("Minecraft sound manager not yet available %s", "\033[0;32m"),
    SOUND_ENGINE_NOT_FOUND("WARNING: Could not find sound engine field in SoundManager", "\033[0;32m"),
    CHECKING_ALL_COLLECTION_FIELDS("No sound-related fields found, checking all collection fields", "\033[0;32m"),
    SOUND_POOL_FIELD_NOT_FOUND("WARNING: Could not find sound pool field after checking %d fields", "\033[0;32m"),
    FOUND_FIELD("Found %s %s field: %s with size %d", "\033[0;32m"),
    WARNING_UNCHANGED("WARNING: Sound pool size unchanged at %d/%d (%d%% of limit) %s", "\033[0;32m"),
    ERROR_CHECK("ERROR: Error checking sound pool: %s", "\033[0;32m"),
    SOUND_ADDED("Sound added: %s (Type: %s)", "\033[0;32m"),
    SOUND_REMOVED("Sound removed: %s", "\033[0;32m");

    private final String template;
    private final String color;

    DEBUG_SoundMessages(String template, String color) {
        this.template = template;
        this.color = color;
    }

    public String template() { return template; }
    public String color() { return color; }
}
