package net.offllneplayer.opminecraft.block.slate.smooth;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class SmoothSlateTrapdoorBlock extends TrapDoorBlock {
	public SmoothSlateTrapdoorBlock() {
		super(BlockSetType.STONE, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.STONE)
				.strength(3.5F, 10F)
				.requiresCorrectToolForDrops());
	}
}