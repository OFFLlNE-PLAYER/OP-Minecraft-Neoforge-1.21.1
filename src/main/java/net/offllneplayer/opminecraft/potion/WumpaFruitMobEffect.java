
package net.offllneplayer.opminecraft.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCure;
import net.offllneplayer.opminecraft.method.crash.wumpafruit.WumpaFruit_OnTick_Method;

import java.util.Set;

public class WumpaFruitMobEffect extends MobEffect {
	public WumpaFruitMobEffect() {
		super(MobEffectCategory.NEUTRAL, -0xFFFD4B13);
	}

	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		WumpaFruit_OnTick_Method.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}
