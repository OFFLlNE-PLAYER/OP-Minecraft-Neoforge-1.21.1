package net.offllneplayer.opminecraft.blocks._block.slate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock;

public class SlatePressurePlateBlock extends PressurePlateBlock {
	public SlatePressurePlateBlock() {
		super(BlockSetType.STONE, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.DEEPSLATE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops().forceSolidOn());
	}

	public SlatePressurePlateBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
		super(BlockSetType.STONE, properties);
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}