
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.potion.CryingIMobEffect;
import net.offllneplayer.opminecraft.potion.CryingIIMobEffect;
import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryMobEffects {

	public static final DeferredRegister<MobEffect> MOBEFFECTSREGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, OPMinecraft.Mod_ID);

	public static final DeferredHolder<MobEffect, MobEffect> CRYING_I = MOBEFFECTSREGISTRY.register("crying_i", CryingIMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> CRYING_II = MOBEFFECTSREGISTRY.register("crying_ii", CryingIIMobEffect::new);
}
