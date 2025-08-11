package net.offllneplayer.opminecraft.eventhandler.spawnhandler;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public final class SpawnEquipArmor {
    private static final Random RANDOM = new Random();

    private static final String[] MATERIALS = {"leather", "chainmail", "iron", "golden", "diamond", "netherite"};
    private static final EquipmentSlot[] SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final String[] SUFFIXES = {"helmet", "chestplate", "leggings", "boots"};
    private static final Item[][] ARMOR_TABLE = new Item[4][6];

    static {
        for (int i = 0; i < 4; i++) {
            for (int t = 0; t < 6; t++) {
                ResourceLocation rl = ResourceLocation.fromNamespaceAndPath("minecraft", MATERIALS[t] + "_" + SUFFIXES[i]);
                ARMOR_TABLE[i][t] = BuiltInRegistries.ITEM.get(rl);
            }
        }
    }

    private SpawnEquipArmor() {}

    public static void equipRandomArmor(Mob mob, Level level) {
        boolean allowHigh = mob instanceof PiglinBrute
                || (!level.dimension().equals(Level.OVERWORLD) && RANDOM.nextBoolean());

        for (int i = 0; i < SLOTS.length; i++) {
            if (RANDOM.nextInt(10) != 0) continue;

            int maxTier = allowHigh ? MATERIALS.length - 1 : 3;
            int tier    = RANDOM.nextInt(maxTier + 1);
            if (tier >= 4) tier = RANDOM.nextInt(maxTier + 1);

            Item chosen = ARMOR_TABLE[i][tier];
            if (chosen == null) continue;

            ItemStack stack = new ItemStack(chosen);
            if (RANDOM.nextBoolean()) {
                SpawnEnchantments.applyArmorEnchantments(level, stack, SLOTS[i]);
            }
            mob.setItemSlot(SLOTS[i], stack);
        }
    }
}
