package net.offllneplayer.opminecraft.block.slate.smooth;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.MapColor;

public class SmoothSlateStairsBlock extends StairBlock {
	public SmoothSlateStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3.5F, 10F)
				.requiresCorrectToolForDrops());
	}
}