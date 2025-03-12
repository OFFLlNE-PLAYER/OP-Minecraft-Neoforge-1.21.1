
package net.offllneplayer.opminecraft.block.densestones.densestonebricks;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;

public class DenseStoneBrickStairsBlock extends StairBlock {
	public DenseStoneBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 8.5f;
	}
}
