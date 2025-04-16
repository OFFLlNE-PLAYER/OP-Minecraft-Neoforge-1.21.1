
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLETYPESREGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, OPMinecraft.Mod_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_GUNBLADE_SHOT =
			PARTICLETYPESREGISTRY.register("particle_gunblade_shot", () -> new SimpleParticleType(true));

}
