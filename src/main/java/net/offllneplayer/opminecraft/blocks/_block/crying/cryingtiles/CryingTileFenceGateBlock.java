
package net.offllneplayer.opminecraft.blocks._block.crying.cryingtiles;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceGateBlock;

public class CryingTileFenceGateBlock extends FenceGateBlock {
	public CryingTileFenceGateBlock() {
		super(WoodType.OAK, Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SCULK_CATALYST).strength(5f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
