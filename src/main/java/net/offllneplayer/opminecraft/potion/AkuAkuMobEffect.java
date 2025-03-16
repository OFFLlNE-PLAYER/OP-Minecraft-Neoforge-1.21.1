
package net.offllneplayer.opminecraft.potion;

import net.neoforged.neoforge.common.EffectCure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.offllneplayer.opminecraft.method.crash.akuaku.PROCAkuAkuOnTickProcedure;
import net.offllneplayer.opminecraft.method.crash.akuaku.EFFECTAkuAkuEffectStartedappliedProcedure;

import java.util.Set;

public class AkuAkuMobEffect extends MobEffect {
	public AkuAkuMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -26584);
	}

	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		EFFECTAkuAkuEffectStartedappliedProcedure.execute(entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		PROCAkuAkuOnTickProcedure.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}
