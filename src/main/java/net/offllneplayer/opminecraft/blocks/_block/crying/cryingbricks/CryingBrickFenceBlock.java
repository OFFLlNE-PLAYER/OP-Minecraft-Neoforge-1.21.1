
package net.offllneplayer.opminecraft.blocks._block.crying.cryingbricks;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceBlock;

public class CryingBrickFenceBlock extends FenceBlock {
	public CryingBrickFenceBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
