
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryPotions {

	public static final DeferredRegister<Potion> POTSREGISTRY = DeferredRegister.create(Registries.POTION, OPMinecraft.Mod_ID);

	public static final DeferredHolder<Potion, Potion> CRYING_I_POTION = POTSREGISTRY.register("crying_i_potion", () -> new Potion(new MobEffectInstance(RegistryMobEffects.CRYING_I, 240, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> CRYING_II_POTION = POTSREGISTRY.register("crying_ii_potion", () -> new Potion(new MobEffectInstance(RegistryMobEffects.CRYING_II, 240, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> CRYING_I_POTION_LONG = POTSREGISTRY.register("crying_i_potion_long", () -> new Potion(new MobEffectInstance(RegistryMobEffects.CRYING_I, 480, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> CRYING_II_POTION_LONG = POTSREGISTRY.register("crying_ii_potion_long", () -> new Potion(new MobEffectInstance(RegistryMobEffects.CRYING_II, 480, 0, false, true)));
}
