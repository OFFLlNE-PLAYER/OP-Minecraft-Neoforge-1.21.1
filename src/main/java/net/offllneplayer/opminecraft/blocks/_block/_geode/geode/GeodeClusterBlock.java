package net.offllneplayer.opminecraft.blocks._block._geode.geode;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.MineGeodeCluster_Method;
import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.GeodeMaterial;

public class GeodeClusterBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final GeodeMaterial material;

    public GeodeClusterBlock(GeodeMaterial material) {
        super(Properties.of()
            .mapColor(material.mapColor())
            .sound(material.sound())
            .strength(material.clusterHardness(), material.clusterResistance())
            .lightLevel(s -> 6)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .isRedstoneConductor((bs, br, bp) -> false));
        this.material = material;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
        return material.beaconColor();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 4;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(1, 0, 1, 15, 14, 15);
            case NORTH -> box(1, 1, 2, 15, 15, 16);
            case SOUTH -> box(1, 1, 0, 15, 15, 14);
            case EAST -> box(0, 1, 1, 14, 15, 15);
            case WEST -> box(2, 1, 1, 16, 15, 15);
            case UP -> box(1, 0, 1, 15, 14, 15);
            case DOWN -> box(1, 2, 1, 15, 16, 15);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        // Use defaultBlockState to avoid potential null from super.getStateForPlacement
        return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(WATERLOGGED, flag);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

 @Override
 public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
     if (state.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
     }

     // Only react to changes on the attachment side (vanilla parity)
     if (facing == state.getValue(FACING).getOpposite() && !canSurvive(state, world, currentPos)) {
         if (world instanceof Level level && !level.isClientSide) {
             Block.dropResources(state, level, currentPos, null);
         }
         return state.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
     }
     return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
 }

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		// Vanilla-parity: support is behind (opposite of FACING), check the face toward the cluster (FACING)
		Direction facing = state.getValue(FACING);
		BlockPos supportPos = pos.relative(facing.getOpposite());
		return world.getBlockState(supportPos).isFaceSturdy(world, supportPos, facing);
	}

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        return super.canHarvestBlock(state, world, pos, player) && MineGeodeCluster_Method.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player, material);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
        return retval;
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);
        if (world instanceof ServerLevel _level) {
            ItemStack drop = new ItemStack(material.shard());
            ItemEntity entityToSpawn = new ItemEntity(_level, pos.getX(), pos.getY(), pos.getZ(), drop);
            entityToSpawn.setPickUpDelay(10);
            _level.addFreshEntity(entityToSpawn);
            _level.addFreshEntity(new ItemEntity(_level, pos.getX(), pos.getY(), pos.getZ(), drop.copy()));
        }
    }

    public GeodeMaterial material() { return material; }
}
