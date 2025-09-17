package net.offllneplayer.opminecraft.blocks._block._geode.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class CrystalPressurePlateBlock extends PressurePlateBlock {
	public CrystalPressurePlateBlock() {
		super(BlockSetType.STONE, Properties.of()
			.sound(SoundType.AMETHYST)
			.strength(5.5F, 12F)
			.lightLevel(s -> 2)
			.requiresCorrectToolForDrops()
			.forceSolidOn());
	}

	public CrystalPressurePlateBlock(Properties properties) {
		super(BlockSetType.STONE, properties);
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return 0xff00e7ff;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
