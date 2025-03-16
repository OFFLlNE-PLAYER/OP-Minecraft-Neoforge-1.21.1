
package net.offllneplayer.opminecraft.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCure;
import net.offllneplayer.opminecraft.method.totemoflife.*;


import java.util.Set;

public class TOLMobEffect extends MobEffect {
	public TOLMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -26584);
	}

	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		TOLEffectStartedapplied_Method.execute(entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		TOL_OnTick_Method.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}
