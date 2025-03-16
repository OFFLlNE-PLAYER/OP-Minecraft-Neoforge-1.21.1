
package net.offllneplayer.opminecraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.method.PROCAkuAkuEndProcedure;
import net.offllneplayer.opminecraft.potion.*;
import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryMobEffects {

	public static final DeferredRegister<MobEffect> MOBEFFECTSREGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, OPMinecraft.Mod_ID);

	public static final DeferredHolder<MobEffect, MobEffect> CRYING_I = MOBEFFECTSREGISTRY.register("crying_i", CryingIMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> CRYING_II = MOBEFFECTSREGISTRY.register("crying_ii", CryingIIMobEffect::new);

	public static final DeferredHolder<MobEffect, MobEffect> EFFECT_AKU_AKU = MOBEFFECTSREGISTRY.register("effect_aku_aku", () -> new EFFECTAkuAkuMobEffect());

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.getEffect().is(EFFECT_AKU_AKU)) {
			PROCAkuAkuEndProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		}
	}
}
