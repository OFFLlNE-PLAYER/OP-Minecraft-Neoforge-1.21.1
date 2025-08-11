package net.offllneplayer.opminecraft.blocks._block.slate;

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
				.sound(SoundType.DEEPSLATE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops());
	}

	public SlateButtonBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
		super(BlockSetType.STONE, 20, properties);
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}