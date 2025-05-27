package net.offllneplayer.opminecraft.eventHANDLER;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EventBusSubscriber(modid = OPMinecraft.Mod_ID)
public class AnvilUpdateHandler {

    // Define incompatibility mappings
    private static final Map<String, List<String>> INCOMPATIBLE_ENCHANTMENTS = Map.of
            ("opminecraft:tempest", List.of("minecraft:sweeping_edge", "minecraft:channeling")
            // Add a comma , and then more enchantments and their incompatibilities as needed:
            // OPMinecraft.Mod_ID + ":enchant2", List.of("minecraft:incompatible1", "minecraft:incompatible2"),
            //  (OPMinecraft.Mod_ID + ":tempest", List.of("minecraft:sweeping_edge", "minecraft:channeling")
    );

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack first = event.getLeft();
        ItemStack second = event.getRight();

        if (first.isEmpty() || second.isEmpty()) return;

        // Get all enchantments from both items
        Set<String> firstEnchants = getAllEnchantmentKeys(first);
        Set<String> secondEnchants = getAllEnchantmentKeys(second);

        // Check for conflicts in both directions
        if (hasConflicts(firstEnchants, secondEnchants) || hasConflicts(secondEnchants, firstEnchants)) {
            event.setCanceled(true);
        }
    }

// Check if any enchantment in sourceEnchants is incompatible with any in targetEnchants
    private static boolean hasConflicts(Set<String> sourceEnchants, Set<String> targetEnchants) {
        for (String sourceEnchant : sourceEnchants) {
            List<String> incompatibles = INCOMPATIBLE_ENCHANTMENTS.get(sourceEnchant);
            if (incompatibles != null) {
                for (String incompatible : incompatibles) {
                    if (targetEnchants.contains(incompatible)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

// Get all enchantment keys from an item/book
    private static Set<String> getAllEnchantmentKeys(ItemStack stack) {
        Set<String> result = new HashSet<>();

        // Get stored enchantments from books
        if (stack.is(Items.ENCHANTED_BOOK)) {
            collectEnchantmentKeys(stack, DataComponents.STORED_ENCHANTMENTS, result);
        } else {
            collectEnchantmentKeys(stack, DataComponents.ENCHANTMENTS, result);
        }
        return result;
    }

// collect enchantment keys from a component
    private static void collectEnchantmentKeys(ItemStack stack, DataComponentType<ItemEnchantments> component, Set<String> result) {
        var enchants = stack.getComponents().get(component);
        for (var entry : enchants.entrySet()) {
            entry.getKey().unwrapKey().ifPresent(key -> result.add(key.location().toString()));
        }
    }
}

