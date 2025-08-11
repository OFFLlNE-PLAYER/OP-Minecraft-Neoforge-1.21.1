
package net.offllneplayer.opminecraft.blocks._block.charcoal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

public class CharcoalFenceGateBlock extends FenceGateBlock {
	public CharcoalFenceGateBlock() {
		super(WoodType.OAK, Properties.of()
				.ignitedByLava()
				.mapColor(MapColor.COLOR_BLACK)
				.sound(SoundType.STONE)
				.strength(3f, 8f)
				.requiresCorrectToolForDrops()
				.forceSolidOn());
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}
}
