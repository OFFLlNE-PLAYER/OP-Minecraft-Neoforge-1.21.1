
package net.offllneplayer.opminecraft.block.charcoal;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.MapColor;

public class CharcoalWallBlock extends WallBlock {
	public CharcoalWallBlock() {
		super(Properties.of()
				.ignitedByLava()
				.mapColor(MapColor.COLOR_BLACK)
				.sound(SoundType.STONE)
				.strength(3f, 8f)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}
}
