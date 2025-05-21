package net.offllneplayer.opminecraft.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.offllneplayer.opminecraft.method.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

import java.util.EnumMap;
import java.util.Map;

public class OnyxLampBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty BLOCKISABOVE = BooleanProperty.create("blockisabove");
    public static final BooleanProperty LAMPISLIT = BooleanProperty.create("lampislit");
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);

    public OnyxLampBlock(DyeColor defaultColor) {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .sound(SoundType.WOOL)
                .strength(3F, 3F)
                .pushReaction(PushReaction.NORMAL)
                .isRedstoneConductor((bs, br, bp) -> false)
                .forceSolidOff()
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCKISABOVE, false).setValue(LAMPISLIT, false).setValue(COLOR, defaultColor)
        );
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getValue(LAMPISLIT) ? 15 : 0;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getValue(LAMPISLIT) ? 15 : 0;
    }

    // standing core cuboids
    private static final VoxelShape BOTTOM = Block.box(5,   0,  5, 11,  1, 11);
    private static final VoxelShape MID = Block.box(7,   1,  7,  9,  7,  9);
    private static final VoxelShape TOP = Block.box(4,   7,  4, 12, 15, 12);
    private static final VoxelShape TOP_OFTOP  = Block.box(5,  15,  5, 11, 16, 11);

    // hanging core cuboids
    private static final VoxelShape H_BOTTOM = Block.box(5,  15,  5, 11, 16, 11);
    private static final VoxelShape H_MID = Block.box(7,   9,  7,  9, 15,  9);
    private static final VoxelShape H_TOP = Block.box(4,   1,  4, 12,  9, 12);
    private static final VoxelShape H_TOP_OFTOP = Block.box(5,   0,  5, 11,  1, 11);

    // lightstring per‑facing (standing)
    private static final Map<Direction, VoxelShape> STRING_SHAPES =
            Util.make(new EnumMap<>(Direction.class), m -> {
                m.put(Direction.NORTH, Block.box(6.5, 4,  7.75, 7,   7, 8.25));
                m.put(Direction.SOUTH, Block.box(9.0, 4,  7.75, 9.5, 7, 8.25));
                m.put(Direction.EAST,  Block.box(7.75,4,  6.5,  8.25,7, 7));
                m.put(Direction.WEST,  Block.box(7.75,4,  9.0,  8.25,7, 9.5));
            });

    // lightstring per‑facing (hanging)
    private static final Map<Direction, VoxelShape> H_STRING_SHAPES =
            Util.make(new EnumMap<>(Direction.class), m -> {
                m.put(Direction.NORTH, Block.box(4.75,9,  7.75, 7.75,9.5,8.25));
                m.put(Direction.SOUTH, Block.box(8.25,9,  7.75,11.25,9.5,8.25));
                m.put(Direction.EAST,  Block.box(7.75,9,  4.75, 8.25,9.5,7.75));
                m.put(Direction.WEST,  Block.box(7.75,9,  8.25, 8.25,9.5,11.25));
            });

    // full shapes per facing
    private static final Map<Direction, VoxelShape> SHAPES =
            Util.make(new EnumMap<>(Direction.class), m -> {
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    m.put(dir, Shapes.or(BOTTOM, MID, TOP, TOP_OFTOP, STRING_SHAPES.get(dir)));
                }
            });

    private static final Map<Direction, VoxelShape> H_SHAPES =
            Util.make(new EnumMap<>(Direction.class), m -> {
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    m.put(dir, Shapes.or(H_BOTTOM, H_MID, H_TOP, H_TOP_OFTOP, H_STRING_SHAPES.get(dir)));
                }
            });

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        boolean belowSolid = world.getBlockState(pos.below()).isSolid();
        boolean aboveSolid = state.getValue(BLOCKISABOVE);
        Direction facing   = state.getValue(FACING);

        if (belowSolid) {
            return SHAPES.get(facing);
        } else if (aboveSolid) {
            return H_SHAPES.get(facing);
        } else {
            return SHAPES.get(facing);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, BLOCKISABOVE, LAMPISLIT, COLOR);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        boolean above = level.getBlockState(pos.above()).isSolid();
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(BLOCKISABOVE, above);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        Direction newFacing = mirrorIn.getRotation(state.getValue(FACING))
                .rotate(state.getValue(FACING));
        return state.setValue(FACING, newFacing);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        boolean aboveSolid = level.getBlockState(pos.above()).isSolid();
        boolean belowSolid = level.getBlockState(pos.below()).isSolid() || level.getBlockState(pos.below()).is(OP_TagKeyUtil.Blocks.OP_LAMPS);

        if (belowSolid) {// Supported from below, standing.
            return state.setValue(BLOCKISABOVE, false);
        }

        // Set BLOCKISABOVE and either hang or fall.
        state = state.setValue(BLOCKISABOVE, aboveSolid);

        if (!level.isClientSide()) {
            if (aboveSolid) {// Supported above, hanging.
                return state;
            }
            // else lamp falls
            FallingBlockEntity.fall((ServerLevel)level, pos, state);
            return Blocks.AIR.defaultBlockState();
        }

        return state;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);

        if (!level.isClientSide) {

            boolean aboveSolid = level.getBlockState(pos.above()).isSolid();
            boolean belowSolid = level.getBlockState(pos.below()).isSolid() || level.getBlockState(pos.below()).is(OP_TagKeyUtil.Blocks.OP_LAMPS);

            if (!belowSolid && !aboveSolid) {
                FallingBlockEntity.fall(level, pos, state);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // Check both hands for a dye
        ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHand  = player.getItemInHand(InteractionHand.OFF_HAND);
        DyeColor newColor   = null;
        InteractionHand dyeHand = null;

        if (mainHand.getItem() instanceof DyeItem) {
            newColor = ((DyeItem)mainHand.getItem()).getDyeColor();
            dyeHand  = InteractionHand.MAIN_HAND;
        } else if (offHand.getItem() instanceof DyeItem) {
            newColor = ((DyeItem)offHand.getItem()).getDyeColor();
            dyeHand  = InteractionHand.OFF_HAND;
        }

        // If no dye, fall back
        if (newColor == null) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // If it’s already that color, do nothing
        if (state.getValue(COLOR) == newColor) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // --- BEGIN swap to a color‐specific block instance ---
        // Map DyeColor → your registered block, e.g. ModBlocks.ONYX_LAMP_BLACK, etc.
        Block targetBlock = switch (newColor) {
            case BLACK -> RegistryBIBI.ONYX_LAMP_BLACK.get();
            case BLUE -> RegistryBIBI.ONYX_LAMP_BLUE.get();
            case BROWN -> RegistryBIBI.ONYX_LAMP_BROWN.get();
            case CYAN -> RegistryBIBI.ONYX_LAMP_CYAN.get();
            case GRAY -> RegistryBIBI.ONYX_LAMP_GRAY.get();
            case GREEN -> RegistryBIBI.ONYX_LAMP_GREEN.get();
            case LIGHT_BLUE -> RegistryBIBI.ONYX_LAMP_LIGHT_BLUE.get();
            case LIGHT_GRAY -> RegistryBIBI.ONYX_LAMP_LIGHT_GRAY.get();
            case LIME -> RegistryBIBI.ONYX_LAMP_LIME.get();
            case MAGENTA -> RegistryBIBI.ONYX_LAMP_MAGENTA.get();
            case ORANGE -> RegistryBIBI.ONYX_LAMP_ORANGE.get();
            case PINK -> RegistryBIBI.ONYX_LAMP_PINK.get();
            case PURPLE -> RegistryBIBI.ONYX_LAMP_PURPLE.get();
            case RED -> RegistryBIBI.ONYX_LAMP_RED.get();
            case WHITE -> RegistryBIBI.ONYX_LAMP_WHITE.get();
            case YELLOW -> RegistryBIBI.ONYX_LAMP_YELLOW.get();
        };
        // Build the new blockstate, preserving facing/above/lit
        BlockState updated = targetBlock.defaultBlockState()
                .setValue(FACING,     state.getValue(FACING))
                .setValue(BLOCKISABOVE,state.getValue(BLOCKISABOVE))
                .setValue(LAMPISLIT,  state.getValue(LAMPISLIT))
                .setValue(COLOR,       newColor);
        level.setBlock(pos, updated, 3);

        if (!player.isCreative()) {
            player.getItemInHand(dyeHand).shrink(1);
        }

        level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, Mth.nextFloat(RandomSource.create(), 0.9420F, 1.0420F));

        return ItemInteractionResult.SUCCESS;
    }


    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.CONSUME;

        // Compute local hit in block‐space
        Vec3 local = hit.getLocation()
                .subtract(pos.getX(), pos.getY(), pos.getZ());
        double hx = local.x * 16;
        double hy = local.y * 16;
        double hz = local.z * 16;

        // Figure out which String‐box to test against
        boolean belowSolid = level.getBlockState(pos.below()).isSolid();
        boolean aboveSolid = state.getValue(BLOCKISABOVE);
        Direction f = state.getValue(FACING);


        double minX, minY, minZ, maxX, maxY, maxZ;
        if (belowSolid) {
            // standing lightstring
            switch (f) {
                case NORTH -> { minX=6.5; maxX=7;   minY=4;  maxY=7;   minZ=7.75; maxZ=8.25; }
                case SOUTH -> { minX=9.0; maxX=9.5; minY=4;  maxY=7;   minZ=7.75; maxZ=8.25; }
                case EAST  -> { minX=7.75; maxX=8.25; minY=4;  maxY=7;   minZ=6.5;  maxZ=7;    }
                case WEST  -> { minX=7.75; maxX=8.25; minY=4;  maxY=7;   minZ=9.0;  maxZ=9.5;  }
                default    -> { return InteractionResult.PASS; }
            }
        } else if (aboveSolid) {
            // hanging lightstring
            switch (f) {
                case NORTH -> { minX=4.75; maxX=7.75; minY=9;  maxY=9.5; minZ=7.75; maxZ=8.25; }
                case SOUTH -> { minX=8.25; maxX=11.25; minY=9;  maxY=9.5; minZ=7.75; maxZ=8.25; }
                case EAST  -> { minX=7.75; maxX=8.25;  minY=9;  maxY=9.5; minZ=4.75; maxZ=7.75; }
                case WEST  -> { minX=7.75; maxX=8.25;  minY=9;  maxY=9.5; minZ=8.25; maxZ=11.25;}
                default    -> { return InteractionResult.PASS; }
            }
        } else {
            // (do nothing here)
            return InteractionResult.CONSUME;
        }

        // Only toggle if within that box
        if (hx < minX || hx > maxX ||
                hy < minY || hy > maxY ||
                hz < minZ || hz > maxZ) {
            return InteractionResult.PASS;
        }

        // Clicked the string → toggle
        boolean lit = state.getValue(LAMPISLIT);
        level.setBlock(pos, state.setValue(LAMPISLIT, !lit), 3);
        level.playSound(null, pos,
                RegistrySounds.LAMP_USE.get(),
                SoundSource.BLOCKS, 1.0F, lit ? 1.142F : 0.8420F
        );
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
