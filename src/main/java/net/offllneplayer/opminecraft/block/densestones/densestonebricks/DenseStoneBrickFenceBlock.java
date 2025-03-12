
package net.offllneplayer.opminecraft.block.densestones.densestonebricks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.material.MapColor;

public class DenseStoneBrickFenceBlock extends FenceBlock {
	public DenseStoneBrickFenceBlock() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
