package net.offllneplayer.opminecraft.method.crying.essence;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CryingEssenceFlowingCondition_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		BlockPos below = BlockPos.containing(x, y - 1, z);
		BlockState state = world.getBlockState(below);
		return !state.is(Blocks.LAVA) && !state.is(Blocks.WATER) && !state.is(Blocks.BUBBLE_COLUMN);
	}
}
