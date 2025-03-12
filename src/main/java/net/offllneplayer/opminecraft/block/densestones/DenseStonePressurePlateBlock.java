
package net.offllneplayer.opminecraft.block.densestones;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock;

public class DenseStonePressurePlateBlock extends PressurePlateBlock {
	public DenseStonePressurePlateBlock() {
		super(BlockSetType.IRON, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
