
package net.offllneplayer.opminecraft.blocks._block.crash.crates.akuaku;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.CrashCratesCollision_Method;

public class AkuAkuCrateBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public AkuAkuCrateBlock() {
		super(Properties.of()
				.mapColor(MapColor.WOOD)
				.sound(new DeferredSoundType(1.0f, 1.0f,
                        (RegistrySounds.CRASH_CRATE_BREAK),
						() -> SoundEvents.BAMBOO_STEP,
						() -> SoundEvents.BAMBOO_PLACE,
						(RegistrySounds.CRASH_SPIN),
						(RegistrySounds.SILENT_SOUND)))
				.strength(0.1f, 0f)
				.lightLevel(s -> 5)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY)
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return FastColor.ARGB32.opaque(-11322742);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case NORTH, SOUTH, EAST, WEST, UP -> box(0, 0, 0, 16, 15.9, 16);
			case DOWN -> box(0, 0.1, 0, 16, 16, 16);
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		Direction newFacing = mirrorIn.getRotation(state.getValue(FACING)).rotate(state.getValue(FACING));
		return state.setValue(FACING, newFacing);
	}

	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return PathType.BLOCKED;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && AkuAku_SilkTouch_Method.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		CrashCratesCollision_Method.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}
