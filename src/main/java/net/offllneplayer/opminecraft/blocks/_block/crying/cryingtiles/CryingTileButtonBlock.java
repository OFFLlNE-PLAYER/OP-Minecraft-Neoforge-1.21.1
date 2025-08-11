
package net.offllneplayer.opminecraft.blocks._block.crying.cryingtiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.blocks._block.crying.CryingButtons_Method;

public class CryingTileButtonBlock extends ButtonBlock {
	public CryingTileButtonBlock() {
		super(BlockSetType.STONE, 20, Properties.of()
				.mapColor(MapColor.COLOR_PURPLE)
				.sound(SoundType.SCULK_CATALYST)
				.strength(5f, 10f)
				.requiresCorrectToolForDrops());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			CryingButtons_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}
}
