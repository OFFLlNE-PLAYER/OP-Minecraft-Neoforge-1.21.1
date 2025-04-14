package net.offllneplayer.opminecraft.method.crash.crates.crate;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

public class CrashCrate_SilkTouch_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return false;
		boolean Silk_Touch_It = false;

		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
			.getEnchantmentLevel(world.registryAccess()
			.lookupOrThrow(Registries.ENCHANTMENT)
			.getOrThrow(Enchantments.SILK_TOUCH)) != 0) {

			Silk_Touch_It = true;
		} else {

			CrashCrateBreak_Method.execute(world, x, y, z);
		}

		return Silk_Touch_It;
	}
}
