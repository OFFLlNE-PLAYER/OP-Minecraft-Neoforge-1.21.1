package net.offllneplayer.opminecraft.block.slate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;

public class SlateButtonBlock extends ButtonBlock {
	public SlateButtonBlock() {
		super(BlockSetType.STONE, 20, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops());
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}