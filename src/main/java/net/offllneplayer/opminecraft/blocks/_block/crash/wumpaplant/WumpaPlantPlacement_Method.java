package net.offllneplayer.opminecraft.blocks._block.crash.wumpaplant;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class WumpaPlantPlacement_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.GRASS_BLOCK
			|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.DIRT
			|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.COARSE_DIRT
			|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.PODZOL
			|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.ROOTED_DIRT
			|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.MUD
			|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.FARMLAND;
	}
}
