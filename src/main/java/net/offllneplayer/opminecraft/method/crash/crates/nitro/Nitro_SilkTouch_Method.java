package net.offllneplayer.opminecraft.method.crash.crates.nitro;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class Nitro_SilkTouch_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return false;
		boolean Harvest_It = false;

		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH)) != 0) {
			Harvest_It = true;
		} else {
			Harvest_It = false;
			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				NitroBoom_Method.execute(world, x, y, z);
			}
		}

		return Harvest_It;
	}
}
