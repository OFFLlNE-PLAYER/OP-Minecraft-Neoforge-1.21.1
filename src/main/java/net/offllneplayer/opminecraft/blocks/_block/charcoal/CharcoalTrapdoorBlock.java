
package net.offllneplayer.opminecraft.blocks._block.charcoal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
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
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}
}
