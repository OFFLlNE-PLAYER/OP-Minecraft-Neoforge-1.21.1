
package net.offllneplayer.opminecraft.blocks._block.crying.cryingtiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceBlock;

public class CryingTileFenceBlock extends FenceBlock {
	public CryingTileFenceBlock() {
		super(Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
