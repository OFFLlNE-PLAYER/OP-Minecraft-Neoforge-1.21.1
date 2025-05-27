package net.offllneplayer.opminecraft.VANILLA_MIXIN;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
   private ItemEntity currentEntity;
   private float capturedPartialTicks;

   // Helper method to normalize angle to -180 to 180 range
   private float normalizeAngle(float angle) {
      angle = angle % 360;
      if (angle > 180) angle -= 360;
      if (angle < -180) angle += 360;
      return angle;
   }

   // Helper method to interpolate angles using the shortest path
   private float lerpAngle(float alpha, float start, float end) {
      float diff = end - start;

      // Adjust for shortest path
      if (diff > 180) diff -= 360;
      if (diff < -180) diff += 360;

      return start + alpha * diff;
   }

   @Inject(method = "render", at = @At("HEAD"))
   private void captureEntity(ItemEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
      this.currentEntity = entity;
      this.capturedPartialTicks = partialTicks;
   }

   @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V", ordinal = 0))
   private void modifyFirstRotation(PoseStack instance, Quaternionf quaternion) {
      Vec3 motion = currentEntity.getDeltaMovement();
      boolean isInWater = currentEntity.isInWaterOrBubble();
      boolean isAtWaterSurface = false;
      float transitionFactor = 0.0F; // 0.0 = deep water, 1.0 = surface

      if (isInWater) {
         // Get block position at and above the entity
         BlockPos entityPos = currentEntity.blockPosition();
         Level level = currentEntity.level();
         boolean isWaterBlock = level.getBlockState(entityPos).getFluidState().is(FluidTags.WATER);
         boolean isAirAbove = level.getBlockState(entityPos.above()).isAir();

         if (isWaterBlock && isAirAbove) {
            double waterSurfaceY = entityPos.getY() + 0.420D;  // Top of water is slightly below block top
            double distanceToSurface = Math.abs(currentEntity.getY() - waterSurfaceY);

            // Create smooth transition from 0.0 to 0.3 distance
            if (distanceToSurface < 0.3D) {
               isAtWaterSurface = true;
               // transitionFactor is 1.0 at distance 0, and 0.0 at distance 0.3
               transitionFactor = 1.0F - (float)(distanceToSurface / 0.3D);
            }
         }

         System.out.println("Item isAtWaterSurface: " + isAtWaterSurface);
         System.out.println("Transition factor: " + transitionFactor);

         if (!isAtWaterSurface) {
            // Your existing deep water rotation code
            float randomrot = currentEntity.getAge() % 360;
            instance.mulPose(Axis.YP.rotationDegrees(Mth.lerp(randomrot, currentEntity.yRotO, currentEntity.getYRot())));
            instance.mulPose(Axis.ZP.rotationDegrees(currentEntity.getYRot() - currentEntity.getSpin(randomrot)));
            instance.mulPose(Axis.XP.rotationDegrees(Mth.lerp(randomrot, currentEntity.xRotO, currentEntity.getXRot())));
            return;

         } else if (transitionFactor >= 0.99F) { // Fully at surface
            instance.mulPose(Axis.XP.rotationDegrees(currentEntity.getXRot()));
            instance.mulPose(Axis.YP.rotationDegrees(currentEntity.getYRot()));
            instance.mulPose(Axis.ZP.rotationDegrees(currentEntity.getSpin(capturedPartialTicks)));
            return;

         } else {
            // Transitional state with shorter path rotation
            float randomrot = currentEntity.getAge() % 360;

            // Get the two states' rotation values
            float deepXRot = Mth.lerp(randomrot, currentEntity.xRotO, currentEntity.getXRot());
            float deepYRot = Mth.lerp(randomrot, currentEntity.yRotO, currentEntity.getYRot());
            float deepZRot = currentEntity.getYRot() - currentEntity.getSpin(randomrot);

            float surfaceXRot = currentEntity.getXRot();
            float surfaceYRot = currentEntity.getYRot();
            float surfaceZRot = currentEntity.getSpin(capturedPartialTicks);

            // Normalize angles to -180 to 180 range for shortest path interpolation
            deepXRot = normalizeAngle(deepXRot);
            deepYRot = normalizeAngle(deepYRot);
            deepZRot = normalizeAngle(deepZRot);

            surfaceXRot = normalizeAngle(surfaceXRot);
            surfaceYRot = normalizeAngle(surfaceYRot);
            surfaceZRot = normalizeAngle(surfaceZRot);

            // Use shortest path interpolation
            float blendedXRot = lerpAngle(transitionFactor, deepXRot, surfaceXRot);
            float blendedYRot = lerpAngle(transitionFactor, deepYRot, surfaceYRot);
            float blendedZRot = lerpAngle(transitionFactor, deepZRot, surfaceZRot);

            instance.mulPose(Axis.XP.rotationDegrees(blendedXRot));
            instance.mulPose(Axis.YP.rotationDegrees(blendedYRot));
            instance.mulPose(Axis.ZP.rotationDegrees(blendedZRot));
            return;
         }

      } else if (currentEntity.onGround()) {
         instance.mulPose(Axis.XP.rotationDegrees(90F));
         instance.mulPose(Axis.YP.rotationDegrees(0));
         instance.mulPose(Axis.ZP.rotationDegrees(currentEntity.getSpin(capturedPartialTicks)));
         return;
      }

      // in air
      float pitchAngle = (float) Math.toDegrees(Math.atan2(motion.y, Math.sqrt(motion.x * motion.x + motion.z * motion.z)));
      float yawAngle = (float) Math.toDegrees(Math.atan2(motion.z, motion.x));
      float rollAngle = (float) (Math.sin((currentEntity.getSpin(capturedPartialTicks) % 360) / 360F * Math.PI) * 15);

      // Apply the calculated rotations
      instance.mulPose(Axis.XP.rotationDegrees(pitchAngle));
      instance.mulPose(Axis.YP.rotationDegrees(yawAngle));
      instance.mulPose(Axis.ZP.rotationDegrees(rollAngle));
   }
}
