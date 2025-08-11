package net.offllneplayer.opminecraft.MiXiN;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
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

import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;

@OnlyIn(Dist.CLIENT)
@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
   private ItemEntity currentEntity;
   private float capturedPartialTicks;
   
   @Inject(method = "render", at = @At("HEAD"))
   private void captureEntity(ItemEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
      this.currentEntity = entity;
      this.capturedPartialTicks = partialTicks;
   }
   
   @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V", ordinal = 0))
   private void modifyFirstRotation(PoseStack instance, Quaternionf quaternion) {
      ItemStack thisStack = currentEntity.getItem();
      Vec3 motion = currentEntity.getDeltaMovement();
      float xRot = currentEntity.getXRot();
      float yRot = currentEntity.getYRot();
      float spinAngle = currentEntity.getSpin(capturedPartialTicks);
      boolean isInWater = currentEntity.isInWaterOrBubble();
      float waterSurfaceFactor = 0.0F;

      if (currentEntity.onGround()) { // On ground
         if (!thisStack.is(OP_TagKeyUtil.Items.MIXIN_NON_ROTATE_ITEMS)) {
            instance.mulPose(Axis.XP.rotationDegrees(270F));
            instance.mulPose(Axis.YP.rotationDegrees(0));
            instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));
         } else {
            instance.mulPose(Axis.XP.rotationDegrees(xRot));
            instance.mulPose(Axis.YP.rotationDegrees(yRot));
         }

      } else if (!isInWater) { // In air
         xRot = (float) Math.toDegrees(Math.atan2(motion.y, Math.sqrt(motion.x * motion.x + motion.z * motion.z)) * 1.1420);
         
         // Determine dominant direction based on motion
         double absX = Math.abs(motion.x);
         double absZ = Math.abs(motion.z);
         Direction dir;

         if (absX > absZ) {
            dir = motion.x > 0 ? Direction.EAST : Direction.WEST;
         } else {
            dir = motion.z > 0 ? Direction.SOUTH : Direction.NORTH;
         }

         // Adjust rotation based on direction
         if (dir == Direction.NORTH) {
            instance.mulPose(Axis.YP.rotationDegrees(0));
            instance.mulPose(Axis.XP.rotationDegrees(xRot));
            instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));
         } else if (dir == Direction.SOUTH) {
            instance.mulPose(Axis.YP.rotationDegrees(180));
            instance.mulPose(Axis.XP.rotationDegrees(xRot));
            instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));
         } else if (dir == Direction.EAST) {
            instance.mulPose(Axis.YP.rotationDegrees(0));
            instance.mulPose(Axis.ZP.rotationDegrees(xRot));
            instance.mulPose(Axis.XP.rotationDegrees(spinAngle));
         } else if (dir == Direction.WEST) {
            instance.mulPose(Axis.YP.rotationDegrees(180));
            instance.mulPose(Axis.ZP.rotationDegrees(xRot));
            instance.mulPose(Axis.XP.rotationDegrees(spinAngle));
         }

      } else if (isInWater) { // In water
         BlockPos entityPos = currentEntity.blockPosition();
         Level level = currentEntity.level();
         boolean isWaterBlock = level.getBlockState(entityPos).getFluidState().is(FluidTags.WATER);
         boolean isAirAbove = level.getBlockState(entityPos.above()).isAir();
         if (isWaterBlock && isAirAbove) {
            double waterSurfaceY = entityPos.getY() + 0.420D;
            double distanceToSurface = Math.abs(currentEntity.getY() - waterSurfaceY);
            double transitionDistance = 0.5D;
            float op_equation_buffer = 0.1420F;

            if (distanceToSurface < transitionDistance) {
               waterSurfaceFactor = 1.0F - (float)(distanceToSurface / transitionDistance) * op_equation_buffer;
            }
         }

         if (waterSurfaceFactor > 0) {
            float targetXRot = 45F;
            xRot = Mth.lerp(waterSurfaceFactor, xRot, targetXRot);
            spinAngle *= (1.0F - waterSurfaceFactor);
         }

         instance.mulPose(Axis.XP.rotationDegrees(xRot));
         instance.mulPose(Axis.YP.rotationDegrees(yRot));
         instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));
      }
   }
}