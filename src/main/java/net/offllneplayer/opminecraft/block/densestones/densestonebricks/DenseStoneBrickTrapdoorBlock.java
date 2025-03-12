
package net.offllneplayer.opminecraft.block.densestones.densestonebricks;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.SoundType;

public class DenseStoneBrickTrapdoorBlock extends TrapDoorBlock {
	public DenseStoneBrickTrapdoorBlock() {
		super(BlockSetType.STONE, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops());
	}
}
