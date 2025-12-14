
package net.offllneplayer.opminecraft.blocks._block._geode.building;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.UTIL.SilkTouchCheck_Method;
import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.GeodeMaterial;

public class GeodeSlabBlock extends SlabBlock {
	private final GeodeMaterial material;

	public GeodeSlabBlock(GeodeMaterial material) {
		super(Properties.of()
			.mapColor(material.mapColor())
			.sound(material.sound())
			.strength(material.solidHardness() + 0.5F, material.solidResistance() + 0.5F)
			.lightLevel(s -> 2)
			.requiresCorrectToolForDrops());
		this.material = material;
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return material.beaconColor();
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && SilkTouchCheck_Method.hasSilkTouch(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}
}
