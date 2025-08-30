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
public final class PopBalloons_Method {
    /**
     * Pops a balloon held by the entity
     * @param entity The entity holding balloon(s)
     */
	 public static void execute(Entity entity) {
		 if (entity == null) return;

		 if (entity instanceof LivingEntity livingEntity) {
			 Level level = livingEntity.level();

			 // Check if entity is holding balloons
			 boolean hasBalloonMainHand = livingEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem;
			 boolean hasBalloonOffHand = livingEntity.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem;

			 playPopFx(livingEntity);

			 // If both hands have balloons, randomly choose one to pop
			 if (hasBalloonMainHand && hasBalloonOffHand) {
				 boolean popMainHand = level.random.nextBoolean();
				 if (popMainHand) {
					 livingEntity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
				 } else {
					 livingEntity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
				 }
			 } else {
				 // Remove balloon from hand
				 if (hasBalloonMainHand) {
					 livingEntity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
				 } else if (hasBalloonOffHand) {
					 livingEntity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
				 }
			 }
		 }
	 }

    /**
     * Pops only the balloon in the specified hand (intended for durability break).
     * @param livingEntity entity holding the balloon
     * @param hand which hand to pop
     * @return true if a balloon was popped in that hand
     */
    public static void popHand(LivingEntity livingEntity, InteractionHand hand) {
        if (livingEntity == null) return;
        ItemStack stack = livingEntity.getItemInHand(hand);
        if (!(stack.getItem() instanceof BalloonItem)) {
            return;
        }
        playPopFx(livingEntity);
        livingEntity.setItemInHand(hand, ItemStack.EMPTY);
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