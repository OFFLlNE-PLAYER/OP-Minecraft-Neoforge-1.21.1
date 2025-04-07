package net.offllneplayer.opminecraft.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.offllneplayer.opminecraft.entity.SMBSuperFan;

public class SMBSuperFanModel extends EntityModel<SMBSuperFan> {
    private final ModelPart root;

    public SMBSuperFanModel(ModelPart root) {
        this.root = root;
    }

    @Override
    public void setupAnim(SMBSuperFan entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // No animation needed for a flat model.
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition rootDef = meshDefinition.getRoot();
        rootDef.addOrReplaceChild("root",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8.0F, -8.0F, -0.4F, 16, 16, 0.8F),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );
        return LayerDefinition.create(meshDefinition, 16, 16);
    }
}