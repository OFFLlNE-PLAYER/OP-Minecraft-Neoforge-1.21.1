
package net.offllneplayer.opminecraft.block.densestones;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceGateBlock;

public class DenseStoneFenceGateBlock extends FenceGateBlock {
	public DenseStoneFenceGateBlock() {
		super(WoodType.OAK, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3.5f, 8.5f)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
