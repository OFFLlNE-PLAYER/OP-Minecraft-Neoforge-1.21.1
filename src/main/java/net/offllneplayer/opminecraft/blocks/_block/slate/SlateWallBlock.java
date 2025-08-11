package net.offllneplayer.opminecraft.blocks._block.slate;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class SlateWallBlock extends WallBlock {
	public SlateWallBlock() {
		super(Properties.of()
				.mapColor(MapColor.STONE)
				.sound(SoundType.DEEPSLATE)
				.strength(3F, 8F)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}

	public SlateWallBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
		super(properties);
	}
}