package net.offllneplayer.opminecraft.blocks._block.slate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceBlock;

public class SlateFenceBlock extends FenceBlock {
	public SlateFenceBlock() {
		super(Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.DEEPSLATE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}

	public SlateFenceBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
		super(properties);
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}