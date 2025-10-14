package net.offllneplayer.opminecraft.blocks._block._furnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.offllneplayer.opminecraft.blocks._block._furnace.method.gui.FurnacesExpButton_Method;
import net.offllneplayer.opminecraft.blocks._block._furnace.method.Furnaces_OnClick_Method;
import net.offllneplayer.opminecraft.blocks._block._furnace.method.ontick.Furnaces_OnTick_Method;


public class OPFurnaceBlock extends Block implements EntityBlock {

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*[VARIABLES]*/
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty FURNACEISLIT = IntegerProperty.create("furnaceislit", 0, 1); //create blockstates 0 and 1

	private final OPFurnaceMaterial material;

	  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
   /*[BUILDER]*/
	public OPFurnaceBlock(OPFurnaceMaterial material) {
		super(Properties.of()
				.mapColor(material.getMapColor())
				.sound(material.getSoundType())
				.strength(material.getHardness(), material.getBlastResistance())
				.requiresCorrectToolForDrops()
				.pushReaction(PushReaction.BLOCK));
		this.material = material;
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FURNACEISLIT, 0)); //set default blockstate
	}

	  /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*/
	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
   /*[HELP]*/
	public OPFurnaceMaterial getMaterial() {
		return material;
	}

    /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		if (state.getValue(FURNACEISLIT) == 1) {return 15;}return 0; // set light values for blockstates
    }
    
    @Override
	public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
    	if (state.getValue(FURNACEISLIT) == 1) {return 15;}return 0; // set light values for blockstates
    }

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FURNACEISLIT);
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
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 20);
	}


	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		Furnaces_OnTick_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 20);
	}


	@Override
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		// Only spawn particles when furnace is lit
		if (blockstate.getValue(FURNACEISLIT) == 1) {
			// Vanilla furnaces spawn particles more frequently
			if (random.nextInt(5) == 0) {  // Changed from 10 to 5 for more frequent spawning
				Direction direction = blockstate.getValue(FACING);

				// Base position at block center
				double x = pos.getX() + 0.5D;
				double y = pos.getY() + 0.75D; // Position at about 3/4 of the way up the block
				double z = pos.getZ() + 0.5D;

				// Create multiple particles per tick for a fuller effect
				for (int i = 0; i < 2 + random.nextInt(2); i++) {  // Generate 2-3 particles per cycle
					// Random forward offset (between 0.51 and 0.6)
					double forwardOffset = 0.51D + random.nextDouble() * 0.09D;

					// Random horizontal variation (35% in either direction from center)
					double horizontalVariation = random.nextDouble() * 0.7D - 0.35D;

					// Random vertical variation (10% variation from 3/4 height)
					double verticalVariation = random.nextDouble() * 0.2D - 0.1D;

					double particleX = x;
					double particleZ = z;

					// Adjust particle position based on facing direction
					switch (direction) {
						case NORTH:
							particleZ = z - forwardOffset;
							particleX = x + horizontalVariation;
							break;
						case SOUTH:
							particleZ = z + forwardOffset;
							particleX = x + horizontalVariation;
							break;
						case WEST:
							particleX = x - forwardOffset;
							particleZ = z + horizontalVariation;
							break;
						case EAST:
							particleX = x + forwardOffset;
							particleZ = z + horizontalVariation;
							break;
						default:
							break;
					}

					// Apply vertical variation
					double particleY = y + verticalVariation;

					// Decide between smoke and flame particles
					if (i == 0 || random.nextBoolean()) {
						// Add smoke particle with upward motion
						world.addParticle(ParticleTypes.SMOKE,
								particleX,
								particleY,
								particleZ,
								0.0D,
								0.05D + random.nextDouble() * 0.02D,
								0.0D);
					} else {
						// Add flame particle with slight random motion
						world.addParticle(ParticleTypes.FLAME,
								particleX,
								particleY,
								particleZ,
								Mth.nextDouble(random, -0.01D, 0.01D),
								Mth.nextDouble(random, 0.01D, 0.03D),
								Mth.nextDouble(random, -0.01D, 0.01D));
					}
				}

				// Sometimes add an extra large smoke puff (similar to vanilla behavior)
				if (random.nextInt(10) == 0) {
					double smokeX = x;
					double smokeZ = z;

					switch (direction) {
						case NORTH:
							smokeZ = z - (0.52D + random.nextDouble() * 0.08D);
							smokeX = x + (random.nextDouble() * 0.4D - 0.2D);
							break;
						case SOUTH:
							smokeZ = z + (0.52D + random.nextDouble() * 0.08D);
							smokeX = x + (random.nextDouble() * 0.4D - 0.2D);
							break;
						case WEST:
							smokeX = x - (0.52D + random.nextDouble() * 0.08D);
							smokeZ = z + (random.nextDouble() * 0.4D - 0.2D);
							break;
						case EAST:
							smokeX = x + (0.52D + random.nextDouble() * 0.08D);
							smokeZ = z + (random.nextDouble() * 0.4D - 0.2D);
							break;
						default:
							break;
					}

					world.addParticle(ParticleTypes.LARGE_SMOKE,
							smokeX,
							y + 0.75D,
							smokeZ,
							0.0D,
							0.07D,
							0.0D);
				}
			}
		}
	}


	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		FurnacesExpButton_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
		return retval;
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		FurnacesExpButton_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		Furnaces_OnClick_Method.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new OPFurnaceBlockEntity(pos, state, this.material);
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
			if (blockEntity instanceof OPFurnaceBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof OPFurnaceBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}
