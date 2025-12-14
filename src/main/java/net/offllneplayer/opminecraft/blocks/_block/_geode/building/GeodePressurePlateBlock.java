package net.offllneplayer.opminecraft.blocks._block._geode.building;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.GeodeMaterial;

public class GeodePressurePlateBlock extends PressurePlateBlock {
	private final GeodeMaterial material;

	public GeodePressurePlateBlock(GeodeMaterial material) {
		super(BlockSetType.STONE, Properties.of()
			.mapColor(material.mapColor())
			.sound(material.sound())
			.strength(material.solidHardness() + 0.5F, material.solidResistance() + 0.5F)
			.lightLevel(s -> 2)
			.requiresCorrectToolForDrops()
			.forceSolidOn());
		this.material = material;
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return material.beaconColor();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
