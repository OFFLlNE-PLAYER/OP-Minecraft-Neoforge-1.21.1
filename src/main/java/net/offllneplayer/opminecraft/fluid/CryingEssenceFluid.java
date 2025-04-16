
package net.offllneplayer.opminecraft.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryFluids;
import net.offllneplayer.opminecraft.method.crying.essence.CryingEssenceFlowingCondition_Method;
import net.offllneplayer.opminecraft.init.RegistryIBBI;


public abstract class CryingEssenceFluid extends BaseFlowingFluid {
	public static final Properties PROPERTIES = new Properties(RegistryFluids.CRYING_ESSENCE_TYPE::get, RegistryFluids.CRYING_ESSENCE,
            RegistryFluids.FLOWING_CRYING_ESSENCE::get)
			.explosionResistance(1200f)
			.tickRate(6)
			.levelDecreasePerBlock(3)
			.slopeFindDistance(2)
			.bucket(RegistryIBBI.CRYING_ESSENCE_BUCKET)
			.block(() -> (LiquidBlock) RegistryIBBI.CRYING_ESSENCE.get());

	private CryingEssenceFluid() {super(PROPERTIES);}

	@Override
	public ParticleOptions getDripParticle() {return ParticleTypes.DRIPPING_OBSIDIAN_TEAR;}

	@Override
	protected boolean canSpreadTo(BlockGetter worldIn, BlockPos fromPos, BlockState blockstate, Direction direction, BlockPos toPos, BlockState intostate, FluidState toFluidState, Fluid fluidIn) {
		boolean condition = true;
		if (worldIn instanceof LevelAccessor world) {
			int x = fromPos.getX();
			int y = fromPos.getY();
			int z = fromPos.getZ();
			if (direction == direction.DOWN){condition = CryingEssenceFlowingCondition_Method.execute(world, x, y, z);}
		}
		return super.canSpreadTo(worldIn, fromPos, blockstate, direction, toPos, intostate, toFluidState, fluidIn) && condition;
	}

	public static class Source extends CryingEssenceFluid {
		public int getAmount(FluidState state) {return 8;}

		public boolean isSource(FluidState state) {return true;}
	}

	public static class Flowing extends CryingEssenceFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {return state.getValue(LEVEL);}

		public boolean isSource(FluidState state) {return false;}
	}
}
