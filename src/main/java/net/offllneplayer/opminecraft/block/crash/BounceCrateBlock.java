
package net.offllneplayer.opminecraft.block.crash;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
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
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.offllneplayer.opminecraft.method.crash.crates.CrashCratesCollision_Method;
import net.offllneplayer.opminecraft.method.crash.crates.crate.CrashCrateBreak_Method;
import net.offllneplayer.opminecraft.method.crash.crates.crate.CrashCrate_SilkTouch_Method;

public class BounceCrateBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public BounceCrateBlock() {
		super(Properties.of().mapColor(MapColor.DIRT)
				.sound(new DeferredSoundType(1.0f, 1.0f,
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_break")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.bamboo.step")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.bamboo.place")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_spin")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:silent_sound"))))
				.strength(0.1f, 0f)
				.lightLevel(s -> 5)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return FastColor.ARGB32.opaque(-17648);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
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
		BlockState state = super.getStateForPlacement(context);
		if (state == null) {
			return this.defaultBlockState();
		}
		return state.setValue(FACING, context.getHorizontalDirection().getOpposite());
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
		return super.canHarvestBlock(state, world, pos, player) && CrashCrate_SilkTouch_Method.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		CrashCrateBreak_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		CrashCratesCollision_Method.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}
