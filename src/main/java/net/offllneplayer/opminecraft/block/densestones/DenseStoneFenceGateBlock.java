
package net.offllneplayer.opminecraft.block.densestones;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceGateBlock;

public class DenseStoneFenceGateBlock extends FenceGateBlock {
	public DenseStoneFenceGateBlock() {
		super(WoodType.OAK, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3.5f, 8.5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
