
package net.offllneplayer.opminecraft.mobeffect;

import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.EffectCure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.offllneplayer.opminecraft.blocks._block.crying.essence.method.effect.Crying1_OnTick_Method;
import net.offllneplayer.opminecraft.blocks._block.crying.essence.method.effect.CryingActiveTickCondition_Method;

import java.util.Set;

public class CryingIMobEffect extends MobEffect {
	public CryingIMobEffect() {
		super(MobEffectCategory.HARMFUL, -6750023);
	}

	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.add(EffectCures.MILK);
		cures.add(EffectCures.PROTECTED_BY_TOTEM);
		cures.add(EffectCures.HONEY);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return CryingActiveTickCondition_Method.execute(amplifier, duration);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		Crying1_OnTick_Method.execute(entity.level(), entity);
		return super.applyEffectTick(entity, amplifier);
	}
}
