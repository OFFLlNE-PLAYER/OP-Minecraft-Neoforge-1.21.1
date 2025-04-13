
package net.offllneplayer.opminecraft.block.charcoal;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.MapColor;

public class CharcoalStairsBlock extends StairBlock {
	public CharcoalStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of()
				.ignitedByLava()
				.mapColor(MapColor.COLOR_BLACK)
				.sound(SoundType.STONE).strength(5f, 6f)
				.strength(3f, 8f)
				.requiresCorrectToolForDrops());
	}
}
