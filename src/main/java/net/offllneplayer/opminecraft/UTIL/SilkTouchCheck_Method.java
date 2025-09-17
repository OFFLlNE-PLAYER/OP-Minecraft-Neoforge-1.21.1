package net.offllneplayer.opminecraft.UTIL;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;


// Clean silk touch check using an entity's main hand item.
public class SilkTouchCheck_Method {
	public static boolean hasSilkTouch(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return false;
		return ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
			.getEnchantmentLevel(world.registryAccess()
			.lookupOrThrow(Registries.ENCHANTMENT)
			.getOrThrow(Enchantments.SILK_TOUCH)) != 0);
	}
}
