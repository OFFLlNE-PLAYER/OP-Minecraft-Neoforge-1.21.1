
package net.offllneplayer.opminecraft.block.densestones.densestonetiles;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class DenseStoneTileStairsBlock extends StairBlock {
	public DenseStoneTileStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of().sound(SoundType.STONE).strength(4f, 9f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 9f;
	}
}
