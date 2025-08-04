package net.offllneplayer.opminecraft.block.slate.smooth;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.MapColor;

public class SmoothSlateWallBlock extends WallBlock {
	public SmoothSlateWallBlock() {
		super(Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3.5F, 10F)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}
}