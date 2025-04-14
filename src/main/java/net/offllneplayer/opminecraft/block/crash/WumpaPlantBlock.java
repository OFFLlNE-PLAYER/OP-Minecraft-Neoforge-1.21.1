
package net.offllneplayer.opminecraft.block.crash;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.method.crash.wumpaplant.WumpaPlant_SilkTouch_Method;
import net.offllneplayer.opminecraft.method.crash.wumpaplant.WumpaPlantPlacement_Method;
import net.offllneplayer.opminecraft.method.crash.wumpaplant.WumpaPlant_OnClick_Method;

public class WumpaPlantBlock extends Block {
	public WumpaPlantBlock() {
		super(Properties.of()
				.mapColor(MapColor.TERRACOTTA_ORANGE)
				.sound(SoundType.SMALL_DRIPLEAF)
				.strength(1, 1)
				.noCollission()
				.noOcclusion()
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
		WumpaPlant_OnClick_Method.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
		WumpaPlant_OnClick_Method.execute(world, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), entity);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && WumpaPlant_SilkTouch_Method.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);

		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			_level.playSound(null, pos, RegistrySounds.WUMPA_FRUIT.get(), SoundSource.MASTER, 1, 1);
		}

		if (world instanceof ServerLevel serverLevel) {
			double spawnX = pos.getX() + 0.5;
			double spawnY = pos.getY();
			double spawnZ = pos.getZ() + 0.5;

			ItemEntity fruitEntity = new ItemEntity(serverLevel, spawnX, spawnY, spawnZ, new ItemStack(RegistryIBBI.WUMPA_FRUIT.get()));
			fruitEntity.setPickUpDelay(0);
			serverLevel.addFreshEntity(fruitEntity);

			ItemEntity podEntity = new ItemEntity(serverLevel, spawnX, spawnY, spawnZ, new ItemStack(Items.PITCHER_POD));
			podEntity.setPickUpDelay(0);
			serverLevel.addFreshEntity(podEntity);
		}
	}
}
