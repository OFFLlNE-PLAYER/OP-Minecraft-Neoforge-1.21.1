package net.offllneplayer.opminecraft.items._item.balloon;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Method class for popping balloons held by entities
 */
public class PopBalloons_Method {
    /**
     * Pops any balloons held by the entity
     * @param entity The entity holding balloons
     * @return True if any balloons were popped, false otherwise
     */
	 public static boolean execute(Entity entity) {
		 if (entity == null) return false;

		 if (entity instanceof LivingEntity livingEntity) {
			 Level level = livingEntity.level();

			 // Check if entity is holding balloons
			 boolean hasBalloonMainHand = livingEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem;
			 boolean hasBalloonOffHand = livingEntity.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem;

			 if (hasBalloonMainHand || hasBalloonOffHand) {
				 // If both hands have balloons, randomly choose one to pop
				 if (hasBalloonMainHand && hasBalloonOffHand) {
					 boolean popMainHand = level.random.nextBoolean();
					 if (popMainHand) {
						 playPopFx(livingEntity);
						 livingEntity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
					 } else {
						 playPopFx(livingEntity);
						 livingEntity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
					 }
					 return true;
				 } else {
					 boolean popMainHand = hasBalloonMainHand && level.random.nextBoolean();
					 boolean popOffHand = hasBalloonOffHand && level.random.nextBoolean();

					 if (popMainHand || popOffHand) {
						 playPopFx(livingEntity);

						 // Remove balloons from hands
						 if (popMainHand) {
							 livingEntity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
						 }
						 if (popOffHand) {
							 livingEntity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
						 }

						 return true;
					 }
				 }
			 }
		 }
		 return false;
	 }

    /**
     * Pops only the balloon in the specified hand, intended for durability break or targeted popping
     * @param livingEntity entity holding the balloon
     * @param hand which hand to pop
     * @return true if a balloon was popped in that hand
     */
    public static boolean popHand(LivingEntity livingEntity, InteractionHand hand) {
        if (livingEntity == null) return false;
        ItemStack stack = livingEntity.getItemInHand(hand);
        if (!(stack.getItem() instanceof BalloonItem)) {
            return false;
        }
        playPopFx(livingEntity);
        // Clear that hand
        livingEntity.setItemInHand(hand, ItemStack.EMPTY);
        return true;
    }

    private static void playPopFx(LivingEntity livingEntity) {
        Level level = livingEntity.level();
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        // Play balloon pop sound
        level.playSound(null, x, y, z, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.9F, 0.8F);
        // Create pop particles
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 3, 0.4, 1.5, 0.4, 0.1);
			  serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 3, -0.4, 1.5, -0.4, 0.1);
			  serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 3, 0.4, 1.5, -0.4, 0.1);
			  serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 3, -0.4, 1.5, 0.4, 0.1);
        }
    }
}