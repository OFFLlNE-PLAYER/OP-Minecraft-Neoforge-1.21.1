package net.offllneplayer.opminecraft.items._item.balloon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.offllneplayer.opminecraft.init.RegistryMobEffects;

public class BalloonActivate_Method {
	public static void execute(Entity entity) {
		if (entity == null) return;
		if (entity instanceof LivingEntity living) {
			int balloonCount = 0;

			if (living.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem) {
				balloonCount++;
			}
			if (living.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem) {
				balloonCount++;
			}

			if (balloonCount <= 0) {
				return;
			}

			// If the balloon buff is not present, apply immediately.
			boolean hasBalloonBuff = living.getEffect(RegistryMobEffects.BALLOON) != null;
			if (!hasBalloonBuff) {
				living.addEffect(new MobEffectInstance(RegistryMobEffects.BALLOON, 22, balloonCount - 1, false, true));
				living.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 22, 0, false, true));
				return;
			}

			// If effect is already applied, refresh once per second to keep it up (duration is 22 ticks).
			if (living.tickCount % 20 == 0) {
				living.addEffect(new MobEffectInstance(RegistryMobEffects.BALLOON, 22, balloonCount - 1, false, true));
				living.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 22, 0, false, true));
			}
		}
	}
}
