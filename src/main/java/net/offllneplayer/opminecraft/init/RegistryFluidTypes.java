
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.offllneplayer.opminecraft.fluid.types.CryingEssenceFluidType;
import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryFluidTypes {

	public static final DeferredRegister<FluidType> FLUIDTYPESREGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, OPMinecraft.Mod_ID);

	public static final DeferredHolder<FluidType, FluidType> CRYING_ESSENCE_TYPE = FLUIDTYPESREGISTRY.register("crying_essence", CryingEssenceFluidType::new);
}
