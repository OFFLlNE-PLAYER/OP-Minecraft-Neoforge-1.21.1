package net.offllneplayer.opminecraft.client.tnt;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.offllneplayer.opminecraft.client.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.entity.ThrownTNTStick;

public class ThrownTNTStickRenderer extends EntityRenderer<ThrownTNTStick> {
    private ThrownTNTStickModel model;
    public ThrownTNTStickRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownTNTStickModel(context.bakeLayer(ModModelLayers.THROWN_TNT_STICK));
    }

    @Override
    public void render(ThrownTNTStick pEntity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

/*
    *    poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getyRenderingRotation() * 5f));
    *    poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getxRenderingRotation() * 5f));
    *
    *
    *    uncomment for some magical spinning ^
*/

        /*
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, pEntity.xRotO, pEntity.getXRot())));
        poseStack.mulPose(Axis.ZP.rotationDegrees(pEntity.getXRot() * 5f));

 some paper airplane shit ^

        poseStack.mulPose(Axis.ZP.rotationDegrees(pEntity.getXRot() * 5f));
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, pEntity.xRotO, pEntity.getXRot())));
   kinda derpy but nice ^
         */

        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getXRot() * 10f));
        poseStack.translate(-1, 0f, 0.2);


        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                bufferSource, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(pEntity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownTNTStick entity) {
        return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_tnt_stick.png");
    }
}
