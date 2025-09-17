
package net.offllneplayer.opminecraft.blocks._block.stonetile;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class StoneTileStairsBlock extends StairBlock {
	public StoneTileStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(1.5f, 6f)
				.requiresCorrectToolForDrops());
	}
}
