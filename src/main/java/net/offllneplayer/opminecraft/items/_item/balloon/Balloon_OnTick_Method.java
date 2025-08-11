package net.offllneplayer.opminecraft.items._item.balloon;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.RegistryMobEffects;

public class Balloon_OnTick_Method {
	private static final double ONE_BALLOON_BOOST = 0.06420;
	private static final double TWO_BALLOON_BOOST = 0.08420;

	public static void execute(Entity entity) {
		if (!(entity instanceof LivingEntity livingEntity)) return;
		if (!hasBalloonInHand(livingEntity)) return;

		Level level = livingEntity.level();

		// Check if entity is above build limit
		if (livingEntity.getY() > level.getMaxBuildHeight()) {
			PopBalloons_Method.execute(livingEntity);
			return;
		}

		ballooneffect(livingEntity);

		// Effects and sounds once per second
		if (livingEntity.tickCount % 20 == 0) {
			handleTickEffects(livingEntity, level);
		}

	 	// End player logic here, continue for other mobs.
		if (livingEntity instanceof Player player) return;


		if (!level.isClientSide() && !livingEntity.onGround()) {
			BalloonActivate_Method.execute(livingEntity);

			// Run durability damage once per second
			if (livingEntity.tickCount % 20 == 0) {
				if (livingEntity.getMainHandItem().getItem() instanceof BalloonItem) {
					mobTickBalloonDurability(livingEntity, InteractionHand.MAIN_HAND);
				}
				if (livingEntity.getOffhandItem().getItem() instanceof BalloonItem) {
				mobTickBalloonDurability(livingEntity, InteractionHand.OFF_HAND);
				}
			}
		}
	}


	/* ---------------------------------------- +[Helper Methods]+ ---------------------------------------- */

	private static void ballooneffect(LivingEntity livingEntity) {
		// Get balloon effect amplifier
		int amplifier = livingEntity.hasEffect(RegistryMobEffects.BALLOON)
				? livingEntity.getEffect(RegistryMobEffects.BALLOON).getAmplifier() : 0;

		Vec3 motion = livingEntity.getDeltaMovement();

		// Apply balloon physics based on amplifier
		if (amplifier == 0) {
			// One balloon: only boost while already moving upward (during jump)
			if (motion.y > 0) {
				livingEntity.setDeltaMovement(motion.x, motion.y + ONE_BALLOON_BOOST, motion.z);
			}
		} else if (amplifier == 1) {
			// Two balloons: constant upward force for floating
			livingEntity.setDeltaMovement(motion.x, motion.y + TWO_BALLOON_BOOST, motion.z);
		}
	}

	private static boolean hasBalloonInHand(LivingEntity livingEntity) {
		return (livingEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem) ||
				(livingEntity.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem);
	}


 private static void handleTickEffects(LivingEntity livingEntity, Level level) {
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();

        // Play soft wool sound and spawn particles
        level.playSound(null, x, y, z, SoundEvents.WOOL_FALL, SoundSource.PLAYERS, 0.05F, 1.69F);

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 1, 0.15, 0.1, 0.15, 0.01);
				serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 1, -0.15, 0.1, -0.15, 0.01);
				serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 1, 0.15, 0.1, -0.15, 0.01);
				serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 1, -0.15, 0.1, 0.15, 0.01);
        }
    }

    private static void mobTickBalloonDurability(LivingEntity living, InteractionHand hand) {
        ItemStack stack = living.getItemInHand(hand);
        if (!(stack.getItem() instanceof BalloonItem)) return;
        int cur = stack.getDamageValue();
        int max = stack.getMaxDamage();
        if (cur + 1 >= max) {
            PopBalloons_Method.popHand(living, hand);
        } else {
            stack.setDamageValue(cur + 1);
        }
    }
}
