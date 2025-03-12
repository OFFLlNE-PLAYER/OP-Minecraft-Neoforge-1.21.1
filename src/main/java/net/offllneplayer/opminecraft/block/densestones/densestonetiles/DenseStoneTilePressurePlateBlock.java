
package net.offllneplayer.opminecraft.block.densestones.densestonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock;

public class DenseStoneTilePressurePlateBlock extends PressurePlateBlock {
	public DenseStoneTilePressurePlateBlock() {
		super(BlockSetType.IRON, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f, 9f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
