package net.offllneplayer.opminecraft.client.dynamite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.offllneplayer.opminecraft.client.ModModelLayers;
import net.offllneplayer.opminecraft.entity.ThrownDynamiteStick;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.offllneplayer.opminecraft.OPMinecraft;

public class ThrownDynamiteStickRenderer extends EntityRenderer<ThrownDynamiteStick> {
private ThrownDynamiteStickModel model;
    public ThrownDynamiteStickRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownDynamiteStickModel(context.bakeLayer(ModModelLayers.THROWN_DYNAMITE_STICK));
    }

    @Override
    public void render(ThrownDynamiteStick pEntity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getRenderingRotation() * 5f));
            poseStack.translate(-2, 0f, 0.2);

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                bufferSource, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(pEntity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(ThrownDynamiteStick entity) {
        return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_dynamite_stick.png");
    }
}
