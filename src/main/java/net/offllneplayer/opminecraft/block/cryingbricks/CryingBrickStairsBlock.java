
package net.offllneplayer.opminecraft.block.cryingbricks;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class CryingBrickStairsBlock extends StairBlock {
	public CryingBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 10f;
	}
}
