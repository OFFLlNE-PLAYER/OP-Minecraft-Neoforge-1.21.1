
package net.offllneplayer.opminecraft.block.densestones.densestonebricks;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.material.MapColor;

public class DenseStoneBrickPressurePlateBlock extends PressurePlateBlock {
	public DenseStoneBrickPressurePlateBlock() {
		super(BlockSetType.IRON, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
