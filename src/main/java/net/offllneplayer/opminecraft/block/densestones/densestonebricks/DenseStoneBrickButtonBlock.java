
package net.offllneplayer.opminecraft.block.densestones.densestonebricks;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.material.MapColor;

public class DenseStoneBrickButtonBlock extends ButtonBlock {
	public DenseStoneBrickButtonBlock() {
		super(BlockSetType.STONE, 20, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops());
	}
}
