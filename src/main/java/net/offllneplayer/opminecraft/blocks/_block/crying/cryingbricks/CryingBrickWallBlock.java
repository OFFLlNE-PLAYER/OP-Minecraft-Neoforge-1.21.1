
package net.offllneplayer.opminecraft.blocks._block.crying.cryingbricks;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class CryingBrickWallBlock extends WallBlock {
	public CryingBrickWallBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
