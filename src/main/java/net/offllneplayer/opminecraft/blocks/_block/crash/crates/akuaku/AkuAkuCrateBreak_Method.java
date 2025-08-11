package net.offllneplayer.opminecraft.blocks._block.crash.crates.akuaku;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryBIBI;

public class AkuAkuCrateBreak_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.destroyBlock(BlockPos.containing(x, y, z), false);
		if (world instanceof ServerLevel _level) {
			ItemEntity entityToSpawn = new ItemEntity(_level, (x + Math.random()), (y + Math.random()), (z + Math.random()), new ItemStack(RegistryBIBI.AKU_AKU_MASK.get()));
			entityToSpawn.setPickUpDelay(0);
			_level.addFreshEntity(entityToSpawn);
		}
	}
}
