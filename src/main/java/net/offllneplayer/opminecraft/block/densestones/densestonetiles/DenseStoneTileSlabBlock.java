
package net.offllneplayer.opminecraft.block.densestones.densestonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class DenseStoneTileSlabBlock extends SlabBlock {
	public DenseStoneTileSlabBlock() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f, 9f).requiresCorrectToolForDrops());
	}
}
