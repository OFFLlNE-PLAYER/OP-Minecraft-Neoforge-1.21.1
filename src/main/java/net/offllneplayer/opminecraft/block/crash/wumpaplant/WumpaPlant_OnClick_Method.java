package net.offllneplayer.opminecraft.block.crash.wumpaplant;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class WumpaPlant_OnClick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.CROP_BREAK, SoundSource.MASTER, 1.0F, 1.1F);

			}
			world.levelEvent(2001, BlockPos.containing(x, y + 1, z), Block.getId(RegistryBIBI.WUMPA_PLANT.get().defaultBlockState()));
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);

			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.WUMPA_FRUIT.get(), SoundSource.MASTER, 1, 1);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(RegistryBIBI.WUMPA_FRUIT.get()));
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.PITCHER_POD));
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
		}
	}
}
