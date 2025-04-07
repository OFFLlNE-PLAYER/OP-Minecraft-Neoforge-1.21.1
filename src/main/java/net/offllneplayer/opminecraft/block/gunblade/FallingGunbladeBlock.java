
package net.offllneplayer.opminecraft.block.gunblade;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.util.FastColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.method.gunblade.PROC_Stuck_Gunblade_OnRightClickProcedure;
import net.offllneplayer.opminecraft.method.gunblade.PROCStuckGunbladeCheckSilkTouchProcedure;
import net.offllneplayer.opminecraft.method.gunblade.PROCFallingGunbladeOnTickProcedure;
import net.offllneplayer.opminecraft.method.gunblade.PROCFallingGunbladeCollisionProcedure;


import com.mojang.serialization.MapCodec;

public class FallingGunbladeBlock extends FallingBlock implements EntityBlock {
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	public static final MapCodec<FallingGunbladeBlock> CODEC = simpleCodec(properties -> new FallingGunbladeBlock());

	public MapCodec<FallingGunbladeBlock> codec() {
		return CODEC;
	}

	public FallingGunbladeBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
				.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.armor.equip_chain")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.chain.step")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:gunblade_in_dirt")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.chain.hit")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.chain.fall"))))
				.strength(-1, 3600000).noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return FastColor.ARGB32.opaque(-3355444);
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
			case NORTH, SOUTH -> box(5, 7, -6, 11, 9, 21);
			case EAST, WEST -> box(-6, 7, 5, 21, 9, 11);
			case UP, DOWN -> box(5, -6, 7, 11, 21, 9);
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return 6f;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && PROCStuckGunbladeCheckSilkTouchProcedure.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 2);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		PROCFallingGunbladeOnTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 2);
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		PROCFallingGunbladeCollisionProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		PROC_Stuck_Gunblade_OnRightClickProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FallingGunbladeBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof FallingGunbladeBlockEntity be) {
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return false;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		return 0;
	}
}