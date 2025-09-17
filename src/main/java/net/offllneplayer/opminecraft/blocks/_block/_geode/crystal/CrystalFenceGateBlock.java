package net.offllneplayer.opminecraft.blocks._block._geode.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import net.offllneplayer.opminecraft.UTIL.SilkTouchCheck_Method;

public class CrystalFenceGateBlock extends FenceGateBlock {
	public CrystalFenceGateBlock() {
		super(WoodType.OAK, Properties.of()
			.sound(SoundType.AMETHYST)
			.strength(5.5f, 12f)
			.lightLevel(s -> 2)
			.requiresCorrectToolForDrops()
			.forceSolidOn());
	}

	public CrystalFenceGateBlock(Properties properties) {
		super(WoodType.OAK, properties);
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return 0xff00e7ff;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && SilkTouchCheck_Method.hasSilkTouch(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}
}
