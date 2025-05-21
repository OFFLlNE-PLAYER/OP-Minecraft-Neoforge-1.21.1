package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.init.RegistryBIBI;

public class StuckGunblade_SilkTouch_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return false;
		boolean Silk_Touch_It = false;

		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH)) != 0) {
			Silk_Touch_It = true;
		} else {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(RegistryBIBI.TITAN_GUNBLADE.get()));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
		}
		return Silk_Touch_It;
	}
}
