package net.offllneplayer.opminecraft.method.crash.wumpaplant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class WumpaPlant_SilkTouch_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return false;
		BlockPos pos = BlockPos.containing(x, y, z);
		boolean Silk_Touch_It = false;

		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
			.getEnchantmentLevel(world.registryAccess()
			.lookupOrThrow(Registries.ENCHANTMENT)
			.getOrThrow(Enchantments.SILK_TOUCH)) != 0) {

				Silk_Touch_It = true;
		} else {

			if (world instanceof Level _level && !_level.isClientSide()) {
				_level.playSound(null, pos, RegistrySounds.WUMPA_FRUIT.get(), SoundSource.MASTER, 1, 1);

				// Adjust spawn coordinates if needed (adding 0.5 centers the item in the block)
				double spawnX = pos.getX() + 0.5;
				double spawnY = pos.getY();
				double spawnZ = pos.getZ() + 0.5;

				ItemEntity fruitEntity = new ItemEntity(_level, spawnX, spawnY, spawnZ, new ItemStack(RegistryIBBI.WUMPA_FRUIT.get()));
				fruitEntity.setPickUpDelay(0);
				_level.addFreshEntity(fruitEntity);

				ItemEntity podEntity = new ItemEntity(_level, spawnX, spawnY, spawnZ, new ItemStack(Items.PITCHER_POD));
				podEntity.setPickUpDelay(0);
				_level.addFreshEntity(podEntity);
			}
		}

		return Silk_Touch_It;
	}
}
