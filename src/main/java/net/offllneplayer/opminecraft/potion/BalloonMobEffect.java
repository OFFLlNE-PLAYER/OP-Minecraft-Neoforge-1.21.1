package net.offllneplayer.opminecraft.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCure;
import net.offllneplayer.opminecraft.item.balloon.Balloon_OnTick_Method;

import java.util.Set;

public class BalloonMobEffect extends MobEffect {
    public BalloonMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFE7E7); // Light pink color for balloon
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
        Balloon_OnTick_Method.execute(entity);
        return super.applyEffectTick(entity, amplifier);
    }
}