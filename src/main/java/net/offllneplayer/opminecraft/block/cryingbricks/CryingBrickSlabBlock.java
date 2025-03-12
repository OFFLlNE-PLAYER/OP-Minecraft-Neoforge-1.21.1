
package net.offllneplayer.opminecraft.block.cryingbricks;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class CryingBrickSlabBlock extends SlabBlock {
	public CryingBrickSlabBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops());
	}
}
