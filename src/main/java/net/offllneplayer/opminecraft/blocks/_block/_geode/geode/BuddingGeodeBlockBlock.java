package net.offllneplayer.opminecraft.blocks._block._geode.geode;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.minecraft.world.level.material.PushReaction;
import net.offllneplayer.opminecraft.UTIL.SilkTouchCheck_Method;
import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.BuddingGeodeBlock_Method;
import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.GeodeMaterial;

/**
 * Generic budding geode block; behavior is driven by the provided GeodeMaterial.
 */
public class BuddingGeodeBlockBlock extends Block {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	private final GeodeMaterial material;

 public BuddingGeodeBlockBlock(GeodeMaterial material) {
		super(Properties.of()
				.mapColor(material.mapColor())
				.sound(material.sound())
				.strength(material.solidHardness(), material.solidResistance())
				.requiresCorrectToolForDrops()
				.lightLevel(s -> 1)
				.pushReaction(PushReaction.DESTROY)
				.randomTicks());
		this.material = material;
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return material.beaconColor();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AXIS);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(AXIS, context.getClickedFace().getAxis());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
			if (state.getValue(AXIS) == Direction.Axis.X) {
				return state.setValue(AXIS, Direction.Axis.Z);
			} else if (state.getValue(AXIS) == Direction.Axis.Z) {
				return state.setValue(AXIS, Direction.Axis.X);
			}
		}
		return state;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && SilkTouchCheck_Method.hasSilkTouch(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(state, world, pos, random);
		BuddingGeodeBlock_Method.execute(world, pos, random, this.material);
	}
}
