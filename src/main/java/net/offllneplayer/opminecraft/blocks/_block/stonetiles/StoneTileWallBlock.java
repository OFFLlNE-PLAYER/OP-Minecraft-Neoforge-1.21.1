
package net.offllneplayer.opminecraft.blocks._block.stonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class StoneTileWallBlock extends WallBlock {
	public StoneTileWallBlock() {
		super(Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(1.5f, 6f)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}
}
