package net.offllneplayer.opminecraft.item.balloon;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Balloon_OnTick_Method {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        
        if (entity instanceof LivingEntity livingEntity) {
            // Apply slow falling effect
            livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, false, false));
        }
    }
}