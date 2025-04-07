package net.offllneplayer.opminecraft.client.dynamite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.offllneplayer.opminecraft.entity.ThrownDynamiteStick;

public class ThrownDynamiteStickModel extends EntityModel<ThrownDynamiteStick> {
    private final ModelPart bb_main;

    public ThrownDynamiteStickModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(13, 13).addBox(5.0F, 0.0F, -8.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(3, 12).addBox(5.5F, 8.0F, -7.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(27, 9).addBox(5.6F, 10.8F, -6.5F, 0.8F, 1.5F, 0.85F, new CubeDeformation(0.0F))
                .texOffs(27, 9).addBox(5.5F, 12.2F, -6.1F, 0.6F, 1.0F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(5.7F, 9.0F, -7.0F, 1.3F, 1.8F, 1.3F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(ThrownDynamiteStick entity, float v, float v1, float v2, float v3, float v4) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}