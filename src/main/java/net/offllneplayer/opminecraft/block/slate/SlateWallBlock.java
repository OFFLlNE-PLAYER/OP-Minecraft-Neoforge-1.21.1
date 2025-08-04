package net.offllneplayer.opminecraft.block.slate;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class SlateWallBlock extends WallBlock {
	public SlateWallBlock() {
		super(Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}
}