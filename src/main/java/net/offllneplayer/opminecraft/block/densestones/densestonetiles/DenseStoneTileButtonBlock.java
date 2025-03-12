
package net.offllneplayer.opminecraft.block.densestones.densestonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;

public class DenseStoneTileButtonBlock extends ButtonBlock {
	public DenseStoneTileButtonBlock() {
		super(BlockSetType.STONE, 20, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(4f, 9f).requiresCorrectToolForDrops());
	}
}
