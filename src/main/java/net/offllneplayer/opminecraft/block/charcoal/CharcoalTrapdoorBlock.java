
package net.offllneplayer.opminecraft.block.charcoal;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class CharcoalTrapdoorBlock extends TrapDoorBlock {
	public CharcoalTrapdoorBlock() {
		super(BlockSetType.STONE, Properties.of()
				.ignitedByLava()
				.mapColor(MapColor.COLOR_BLACK)
				.sound(SoundType.STONE)
				.strength(3f, 8f)
				.requiresCorrectToolForDrops());
	}
}
