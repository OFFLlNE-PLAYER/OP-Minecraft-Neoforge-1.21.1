
package net.offllneplayer.opminecraft.blocks._block.crash.wumpaplant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.offllneplayer.opminecraft.UTIL.SilkTouchCheck_Method;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

public class WumpaPlantBlock extends Block {
	public WumpaPlantBlock() {
		super(Properties.of()
				.mapColor(MapColor.TERRACOTTA_ORANGE)
				.sound(SoundType.SMALL_DRIPLEAF)
				.strength(1, 1)
				.noCollission()
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY)
				.isRedstoneConductor((bs, br, bp) -> false));
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
		return box(2, 0, 2, 14, 32, 14);
	}

	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return PathType.WALKABLE;
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return WumpaPlantPlacement_Method.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() != this.asItem();
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (entity.getMainHandItem().getItem() == Blocks.AIR.asItem()) {
			world.levelEvent(2001, BlockPos.containing(x, y + 1, z), Block.getId(RegistryBIBI.WUMPA_PLANT.get().defaultBlockState()));
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			WumpaPlantDestroyed_Method.execute(world, x, y, z);
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
		WumpaPlantDestroyed_Method.execute(world, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ());
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && SilkTouchCheck_Method.hasSilkTouch(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
		// If the tool does NOT have Silk Touch, handle custom drops here.
		boolean hasSilk = SilkTouchCheck_Method.hasSilkTouch(level, pos.getX(), pos.getY(), pos.getZ(), player);
		if (!hasSilk) {
			WumpaPlantDestroyed_Method.execute(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		}
		return level.isClientSide() ? level.setBlock(pos, fluid.createLegacyBlock(), 11) : level.removeBlock(pos, false);
	}

	// Mimic non-silk break: play sound and pop off fruit
	@Override
	public void onDestroyedByPushReaction(BlockState state, Level level, BlockPos pos, Direction pushDirection, FluidState fluid) {
		WumpaPlantDestroyed_Method.execute(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
		level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
	}

	// Delegate to centralized destroy logic so sounds and drops are consistent on explosion.
	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		WumpaPlantDestroyed_Method.execute(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
}
