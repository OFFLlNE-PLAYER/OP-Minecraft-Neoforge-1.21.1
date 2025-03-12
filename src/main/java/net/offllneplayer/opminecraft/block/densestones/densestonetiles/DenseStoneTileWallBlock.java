
package net.offllneplayer.opminecraft.block.densestones.densestonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class DenseStoneTileWallBlock extends WallBlock {
	public DenseStoneTileWallBlock() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f, 9f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
