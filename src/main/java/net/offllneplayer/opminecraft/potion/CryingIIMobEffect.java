
package net.offllneplayer.opminecraft.potion;

import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.EffectCure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.offllneplayer.opminecraft.block.crying.essence.method.effect.Crying2_OnTick_Method;
import net.offllneplayer.opminecraft.block.crying.essence.method.effect.CryingActiveTickCondition_Method;

import java.util.Set;

public class CryingIIMobEffect extends MobEffect {
	public CryingIIMobEffect() {
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
		Crying2_OnTick_Method.execute(entity.level(), entity);
		return super.applyEffectTick(entity, amplifier);
	}
}
