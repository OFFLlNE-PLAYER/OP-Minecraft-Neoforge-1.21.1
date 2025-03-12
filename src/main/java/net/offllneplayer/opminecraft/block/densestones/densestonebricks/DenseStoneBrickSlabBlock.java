
package net.offllneplayer.opminecraft.block.densestones.densestonebricks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.material.MapColor;

public class DenseStoneBrickSlabBlock extends SlabBlock {
	public DenseStoneBrickSlabBlock() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops());
	}
}
