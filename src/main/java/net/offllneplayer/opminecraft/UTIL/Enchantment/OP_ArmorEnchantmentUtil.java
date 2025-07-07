package net.offllneplayer.opminecraft.UTIL.Enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.Random;

public class OP_ArmorEnchantmentUtil {
	private static final Random RANDOM = new Random();

	public static void applyArmorEnchantments(Level level, ItemStack armor, EquipmentSlot slot) {
		if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, armor, Enchantments.UNBREAKING, 3);
		if (RANDOM.nextInt(1000) == 0) applyRandomLevelEnchant(level, armor, Enchantments.MENDING, 1);

		if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, armor, Enchantments.THORNS, 3);

		if (RANDOM.nextInt(6) == 0) {
			switch (RANDOM.nextInt(4)) {
				case 0 -> applyRandomLevelEnchant(level, armor, Enchantments.PROTECTION, 4);
				case 1 -> applyRandomLevelEnchant(level, armor, Enchantments.PROJECTILE_PROTECTION, 4);
				case 2 -> applyRandomLevelEnchant(level, armor, Enchantments.BLAST_PROTECTION, 4);
				case 3 -> applyRandomLevelEnchant(level, armor, Enchantments.FIRE_PROTECTION, 4);
			}
		}

		// Slot-specific enchantments.
		switch (slot) {
			case HEAD:
				if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, armor, Enchantments.AQUA_AFFINITY, 1);
				if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, armor, Enchantments.RESPIRATION, 3);
				break;
			case CHEST:
				break;
			case LEGS:
				if (RANDOM.nextInt(1000) == 0) applyRandomLevelEnchant(level, armor, Enchantments.SWIFT_SNEAK, 3);
				break;
			case FEET:
				// roll a 1% chance to apply Frost Walker (rare), otherwise apply Depth Strider.
				if (RANDOM.nextInt(100) == 0) {
					applyRandomLevelEnchant(level, armor, Enchantments.FROST_WALKER, 2);
				} else if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, armor, Enchantments.DEPTH_STRIDER, 3);

				if (RANDOM.nextInt(40) == 0) applyRandomLevelEnchant(level, armor, Enchantments.FEATHER_FALLING, 4);
				break;

			default: break;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private static void applyRandomLevelEnchant(Level level, ItemStack stack, ResourceKey<Enchantment> enchantKey, int maxLevel) {
		var holder = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
				.getHolder(enchantKey).orElseThrow(() -> new IllegalStateException("Enchantment not found: " + enchantKey.location()));
		int chosenLevel = 1 + RANDOM.nextInt(maxLevel);
		stack.enchant(holder, chosenLevel);
	}
}
