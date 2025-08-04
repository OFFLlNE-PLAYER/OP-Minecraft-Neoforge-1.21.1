package net.offllneplayer.opminecraft.block.slate;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class SlateStairsBlock extends StairBlock {
	public SlateStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops());
	}
}