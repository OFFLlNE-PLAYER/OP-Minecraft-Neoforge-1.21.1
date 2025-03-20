
package net.offllneplayer.opminecraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.method.crash.akuaku.AkuAkuEnd_Method;
import net.offllneplayer.opminecraft.method.crash.wumpafruit.WumpaFruitEnd_Method;
import net.offllneplayer.opminecraft.potion.*;
import net.offllneplayer.opminecraft.OPMinecraft;

@EventBusSubscriber
public class RegistryMobEffects {

	public static final DeferredRegister<MobEffect> MOBEFFECTSREGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, OPMinecraft.Mod_ID);

	public static final DeferredHolder<MobEffect, MobEffect> CRYING_I = MOBEFFECTSREGISTRY.register("crying_i", CryingIMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> CRYING_II = MOBEFFECTSREGISTRY.register("crying_ii", CryingIIMobEffect::new);

	public static final DeferredHolder<MobEffect, MobEffect> AKU_AKU = MOBEFFECTSREGISTRY.register("aku_aku", AkuAkuMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> TOTEM_OF_LIFE = MOBEFFECTSREGISTRY.register("totem_of_life", TOLMobEffect::new);

	public static final DeferredHolder<MobEffect, MobEffect> WUMPA_FRUIT = MOBEFFECTSREGISTRY.register("wumpa_fruit", WumpaFruitMobEffect::new);

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
		if (effectInstance.getEffect().equals(AKU_AKU)){
			AkuAkuEnd_Method.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);

		}else if (effectInstance.getEffect().equals(WUMPA_FRUIT)){
			WumpaFruitEnd_Method.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		}

	}
}










