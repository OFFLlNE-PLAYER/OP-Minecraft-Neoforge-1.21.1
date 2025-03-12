
package net.offllneplayer.opminecraft.block.densestones;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceBlock;

public class DenseStoneFenceBlock extends FenceBlock {
	public DenseStoneFenceBlock() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
