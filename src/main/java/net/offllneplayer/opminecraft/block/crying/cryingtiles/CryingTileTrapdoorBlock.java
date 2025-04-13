
package net.offllneplayer.opminecraft.block.crying.cryingtiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.SoundType;

public class CryingTileTrapdoorBlock extends TrapDoorBlock {
	public CryingTileTrapdoorBlock() {
		super(BlockSetType.STONE, Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops());
	}
}
