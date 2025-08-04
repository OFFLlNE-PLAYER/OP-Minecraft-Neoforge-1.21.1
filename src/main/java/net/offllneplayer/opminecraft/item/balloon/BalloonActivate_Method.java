package net.offllneplayer.opminecraft.item.balloon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.offllneplayer.opminecraft.init.RegistryMobEffects;

public class BalloonActivate_Method {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) return;

        if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()) {
            if (livingEntity.level() instanceof ServerLevel serverLevel) {
                // Check if entity is not on ground
                if (!livingEntity.onGround()) {
                    // Add balloon effect for 1 second (20 ticks)
                    livingEntity.addEffect(new MobEffectInstance(RegistryMobEffects.BALLOON, 20, 0, false, false));
                    
                    // Occasionally play a subtle balloon sound and show particles
                    if (livingEntity.getRandom().nextInt(100) < 5) {
                        // Play sound
                        serverLevel.playSound(null, BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()), 
                                SoundEvents.WOOL_FALL, SoundSource.PLAYERS, 0.2F, 1.5F);
                        
                        // Create particles
                        serverLevel.sendParticles(ParticleTypes.CLOUD, 
                                livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 
                                1, 0.2, 0.1, 0.2, 0.01);
                    }
                }
            }
        }
    }
}