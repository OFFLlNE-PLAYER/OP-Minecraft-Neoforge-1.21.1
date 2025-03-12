
package net.offllneplayer.opminecraft.block.densestones.densestonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceBlock;

public class DenseStoneTileFenceBlock extends FenceBlock {
	public DenseStoneTileFenceBlock() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f, 9f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
