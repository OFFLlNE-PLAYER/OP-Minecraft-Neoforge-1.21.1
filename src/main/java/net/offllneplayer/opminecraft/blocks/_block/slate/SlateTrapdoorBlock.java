package net.offllneplayer.opminecraft.blocks._block.slate;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.SoundType;

public class SlateTrapdoorBlock extends TrapDoorBlock {
	public SlateTrapdoorBlock() {
		super(BlockSetType.STONE, Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.DEEPSLATE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops());
	}

	public SlateTrapdoorBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
		super(BlockSetType.STONE, properties);
	}
}