
package net.offllneplayer.opminecraft.block.densestones;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;

public class DenseStoneButtonBlock extends ButtonBlock {
	public DenseStoneButtonBlock() {
		super(BlockSetType.STONE, 20, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops());
	}
}
