
package net.offllneplayer.opminecraft.block.stonetiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.SoundType;

public class StoneTileTrapdoorBlock extends TrapDoorBlock {
	public StoneTileTrapdoorBlock() {
		super(BlockSetType.STONE, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.5f, 6f).requiresCorrectToolForDrops());
	}
}
