package net.offllneplayer.opminecraft.blocks._block.slate;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class SlateStairsBlock extends StairBlock {
	public SlateStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.DEEPSLATE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops());
	}

	public SlateStairsBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties);
	}
}