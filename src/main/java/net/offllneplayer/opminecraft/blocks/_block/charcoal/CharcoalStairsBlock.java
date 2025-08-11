
package net.offllneplayer.opminecraft.blocks._block.charcoal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class CharcoalStairsBlock extends StairBlock {
	public CharcoalStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), Properties.of()
				.ignitedByLava()
				.mapColor(MapColor.COLOR_BLACK)
				.sound(SoundType.STONE).strength(5f, 6f)
				.strength(3f, 8f)
				.requiresCorrectToolForDrops());
	}
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}
}
