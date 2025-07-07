package net.offllneplayer.opminecraft.UTIL.Enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.init.RegistryEnchantments;

import java.util.Random;

public class OP_WeaponEnchantmentUtil {
	private static final Random RANDOM = new Random();

	public static void applyWeaponEnchantments(Level level, ItemStack weapon) {
		Item witem = weapon.getItem();
		boolean isSword = (witem instanceof SwordItem || witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/sword"))));
		boolean isAxeLike = (witem instanceof AxeItem);
		boolean isBow = (witem == Items.BOW);

		if (isSword) {
			if (RANDOM.nextInt(40) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.FIRE_ASPECT, 2);
			if (RANDOM.nextInt(40) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.LOOTING, 2);
			if (RANDOM.nextInt(20) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.KNOCKBACK, 2);
			if (RANDOM.nextInt(20) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.SWEEPING_EDGE, 3);
		}

		if (isBow) {
			if (RANDOM.nextInt(4) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.POWER, 5);
			if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.PUNCH, 2);
			if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.FLAME, 1);
			if (RANDOM.nextInt(30) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.INFINITY, 1);
		}

		if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/mining")))) {
			if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.EFFICIENCY, 5);
		}

		if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/mining_loot")))) {
			boolean fortune = (RANDOM.nextInt(40) == 0);
			boolean silk = (RANDOM.nextInt(40) == 0);
			if (fortune && silk) {
				if (RANDOM.nextBoolean()) {
					silk = false;
				} else {
					fortune = false;
				}
			}
			if (fortune) {
				applyRandomLevelEnchant(level, weapon, Enchantments.FORTUNE, 2);
			} else if (silk) applyRandomLevelEnchant(level, weapon, Enchantments.SILK_TOUCH, 1);
		}

		if (witem == Items.TRIDENT) {
			if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.IMPALING, 5);
			if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.UNBREAKING, 3);
			if (RANDOM.nextInt(1000) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.MENDING, 1);

			if (RANDOM.nextInt(40) == 0) {
				applyRandomLevelEnchant(level, weapon, Enchantments.LOYALTY, 3);
			} else if (RANDOM.nextInt(40) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.RIPTIDE, 3);

			if (RANDOM.nextInt(20) == 0) {
				applyRandomLevelEnchant(level, weapon, Enchantments.CHANNELING, 1);
			} else if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, RegistryEnchantments.TEMPEST, 3);
		}

		if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/sharp_weapon")))) {
			if (RANDOM.nextInt(4) == 0) {
				switch (RANDOM.nextInt(3)) {
					case 0 -> applyRandomLevelEnchant(level, weapon, Enchantments.SHARPNESS, 5);
					case 1 -> applyRandomLevelEnchant(level, weapon, Enchantments.SMITE, 5);
					case 2 -> applyRandomLevelEnchant(level, weapon, Enchantments.BANE_OF_ARTHROPODS, 5);
				}
			}
		}

		if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/durability")))) {
			if (RANDOM.nextInt(4) == 0) {
				applyRandomLevelEnchant(level, weapon, Enchantments.UNBREAKING, 3);
			}
			if (RANDOM.nextInt(1000) == 4) {
				applyRandomLevelEnchant(level, weapon, Enchantments.MENDING, 1);
			}
		}

		if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/weapon")))) {
			if (RANDOM.nextInt(20) == 4) applyRandomLevelEnchant(level, weapon, RegistryEnchantments.TEMPEST, 1);
		}
	}

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		private static void applyRandomLevelEnchant(Level level, ItemStack stack, ResourceKey<Enchantment> enchantKey, int maxLevel) {
			Holder<net.minecraft.world.item.enchantment.Enchantment> holder =
					level.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
							.getHolder(enchantKey).orElseThrow(() -> new IllegalStateException("Enchantment not found: " + enchantKey.location()));
			int chosenLevel = 1 + RANDOM.nextInt(maxLevel);
			stack.enchant(holder, chosenLevel);
		}
}
