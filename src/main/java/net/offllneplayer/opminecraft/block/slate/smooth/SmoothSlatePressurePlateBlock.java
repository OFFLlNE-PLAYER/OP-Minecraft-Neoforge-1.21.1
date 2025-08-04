package net.offllneplayer.opminecraft.block.slate.smooth;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class SmoothSlatePressurePlateBlock extends PressurePlateBlock {
	public SmoothSlatePressurePlateBlock() {
		super(BlockSetType.STONE, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3.5F, 10F)
				.requiresCorrectToolForDrops().forceSolidOn());
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}