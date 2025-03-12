
package net.offllneplayer.opminecraft.block.cryingtiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class CryingTileWallBlock extends WallBlock {
	public CryingTileWallBlock() {
		super(Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
