package net.offllneplayer.opminecraft.VANILLA_MIXIN;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.core.BlockPos;
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

      if (isInWater) {
         BlockPos entityPos = currentEntity.blockPosition();
         Level level = currentEntity.level();
         boolean isWaterBlock = level.getBlockState(entityPos).getFluidState().is(FluidTags.WATER);
         boolean isAirAbove = level.getBlockState(entityPos.above()).isAir();

         if (isWaterBlock && isAirAbove) {
            double waterSurfaceY = entityPos.getY() + 0.420D;
            double distanceToSurface = Math.abs(currentEntity.getY() - waterSurfaceY);
            double transitionDistance = 0.5D;

            if (distanceToSurface < transitionDistance) {
               waterSurfaceFactor = 1.0F - (float)(distanceToSurface / transitionDistance);
            }
         }

         if (waterSurfaceFactor > 0) {
            float targetXRot = 45F;
            xRot = Mth.lerp(waterSurfaceFactor, xRot, targetXRot);

            spinAngle *= (1.0F - (waterSurfaceFactor * 0.1420F)); // Reduce spin at surface to 14%
         }

         instance.mulPose(Axis.XP.rotationDegrees(xRot));
         instance.mulPose(Axis.YP.rotationDegrees(yRot));
         instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));

      } else if (currentEntity.onGround()) { // On ground

         if (thisStack.is(OP_TagKeyUtil.Items.DONT_ROTATE_ITEMS)) {
            instance.mulPose(Axis.XP.rotationDegrees(xRot));
            instance.mulPose(Axis.YP.rotationDegrees(yRot));
         } else {
            instance.mulPose(Axis.XP.rotationDegrees(270F));
            instance.mulPose(Axis.YP.rotationDegrees(0));
            instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));
         }

      } else { // In air
         xRot = (float) Math.toDegrees(Math.atan2(motion.y, Math.sqrt(motion.x * motion.x + motion.z * motion.z)));
         yRot = (float) Math.toDegrees(Math.atan2(motion.z, motion.x));
         spinAngle = (float) (Math.sin((spinAngle % 360) / 360F * Math.PI) * 15);

         instance.mulPose(Axis.XP.rotationDegrees(xRot));
         instance.mulPose(Axis.YP.rotationDegrees(yRot));
         instance.mulPose(Axis.ZP.rotationDegrees(spinAngle));
      }
   }
}
