package net.offllneplayer.opminecraft.block.slate.smooth;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class SmoothSlateButtonBlock extends ButtonBlock {
	public SmoothSlateButtonBlock() {
		super(BlockSetType.STONE, 20, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3.5F, 10F)
				.requiresCorrectToolForDrops());
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}