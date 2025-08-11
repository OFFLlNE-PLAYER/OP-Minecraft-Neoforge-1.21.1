package net.offllneplayer.opminecraft.mobeffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCure;
import net.offllneplayer.opminecraft.items._item.tou.TOU_OnTick_Method;

import java.util.Set;

public class TOUMobEffect extends MobEffect {
	public TOUMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -0xFF8000FF); // Purple color
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
		TOU_OnTick_Method.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}