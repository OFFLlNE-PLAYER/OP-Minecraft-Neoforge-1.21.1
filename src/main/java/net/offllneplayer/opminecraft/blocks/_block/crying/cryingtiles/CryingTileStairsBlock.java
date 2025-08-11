
package net.offllneplayer.opminecraft.blocks._block.crying.cryingtiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class CryingTileStairsBlock extends StairBlock {
	public CryingTileStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 10f;
	}
}
