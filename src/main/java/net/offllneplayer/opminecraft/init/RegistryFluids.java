
package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.fluid.CryingEssenceFluid;
import net.offllneplayer.opminecraft.fluid.types.CryingEssenceFluidType;

import static net.offllneplayer.opminecraft.OPMinecraft.Mod_ID;


public class RegistryFluids {

/*--------------------------------------------------------------------------------------------*/
	/*[Declare Registries]*/
	public static final DeferredRegister<Fluid> FLUIDSREGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, Mod_ID);
	public static final DeferredRegister<FluidType> FLUIDTYPESREGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, OPMinecraft.Mod_ID);

/*-----------------------------------------------------------------------------------------------------------------------*/
	/*[Declare Fluids]*/
	public static final DeferredHolder<Fluid, FlowingFluid> CRYING_ESSENCE = FLUIDSREGISTRY.register("crying_essence", CryingEssenceFluid.Source::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_CRYING_ESSENCE = FLUIDSREGISTRY.register("flowing_crying_essence", CryingEssenceFluid.Flowing::new);

/*-----------------------------------------------------------------------------------------------------------------------*/
	/*[Declare Fluid Types]*/
	public static final DeferredHolder<FluidType, FluidType> CRYING_ESSENCE_TYPE = FLUIDTYPESREGISTRY.register("crying_essence", CryingEssenceFluidType::new);

}
