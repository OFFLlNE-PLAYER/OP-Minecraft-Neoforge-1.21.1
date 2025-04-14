
package net.offllneplayer.opminecraft.block.crash;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.offllneplayer.opminecraft.method.crash.crates.CrashCratesCollision_Method;
import net.offllneplayer.opminecraft.method.crash.crates.nitro.NitroBoom_Method;
import net.offllneplayer.opminecraft.method.crash.crates.nitro.Nitro_OnTick_Method;
import net.offllneplayer.opminecraft.method.crash.crates.nitro.NitroIdleSound_Method;
import net.offllneplayer.opminecraft.method.crash.crates.nitro.Nitro_SilkTouch_Method;

import java.util.List;

public class NitroBlock extends FallingBlock implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final MapCodec<NitroBlock> CODEC = simpleCodec(properties -> new NitroBlock());

	public MapCodec<NitroBlock> codec() {
		return CODEC;
	}

	public NitroBlock() {
		super(Properties.of().mapColor(MapColor.COLOR_GREEN)
				.sound(new DeferredSoundType(1.0f, 1.0f,
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_3")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.bamboo.step")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:silent_sound")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_spin")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_2"))))
				.strength(0.1f, 0f)
				.lightLevel(s -> 5)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY)
				.hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return FastColor.ARGB32.opaque(-10027213);
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
			case NORTH, SOUTH, EAST, WEST, UP -> box(0.4, 0, 0.4, 15.6, 15.6, 15.6);
			case DOWN -> box(0.4, 0.4, 0.4, 15.6, 16, 15.6);  // Adjusted Y values for DOWN case
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
		return super.canHarvestBlock(state, world, pos, player) && Nitro_SilkTouch_Method.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 20);
		NitroIdleSound_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		Nitro_OnTick_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 20);
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		NitroBoom_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		CrashCratesCollision_Method.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NitroBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("block.opminecraft.nitro.description_0"));
		list.add(Component.translatable("block.opminecraft.nitro.description_1"));
	}

}
