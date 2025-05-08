package net.offllneplayer.opminecraft.client.dynamite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import net.offllneplayer.opminecraft.client.ModModelLayers;
import net.offllneplayer.opminecraft.entity.ThrownDynamiteStick;
import net.offllneplayer.opminecraft.OPMinecraft;

public class ThrownDynamiteStickRenderer extends EntityRenderer<ThrownDynamiteStick> {
    private final ThrownDynamiteStickModel model;

    public ThrownDynamiteStickRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownDynamiteStickModel(context.bakeLayer(ModModelLayers.THROWN_DYNAMITE_STICK));
    }

    @Override
    public void render(ThrownDynamiteStick entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        Direction dir = Direction.NORTH;

        if (entity.isGrounded()) {
            dir = entity.getStuckDirection();

            // use to y-offset grounded state
            poseStack.translate(0, -0.420D, 0);
            poseStack.mulPose(Axis.YP.rotationDegrees(dir.toYRot()));

            if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                // For north/south, rotate 90 around X
                poseStack.mulPose(Axis.XP.rotationDegrees(90));

                if (dir == Direction.NORTH) poseStack.translate(-0.420D, -0.2D, 0);
                if (dir == Direction.SOUTH) poseStack.translate(0.420D, -0.2D, 0);

                // Spawn flame particles at the fuse end for north/south
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    double particleX = entity.getX() + (dir == Direction.NORTH ? -0.5 : 0.5);
                    double particleY = entity.getY() + 0.1;
                    double particleZ = entity.getZ();
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            particleX, particleY, particleZ,
                            0.0D, 0.05D, 0.0D
                    );
                }
            } else {
                // else rotate 90 around Z
                poseStack.mulPose(Axis.ZP.rotationDegrees(90));

                // Apply the directional offsets to position correctly
                if (dir == Direction.EAST) poseStack.translate(0, -0.2D, -0.420D);
                if (dir == Direction.WEST) poseStack.translate(0, -0.2D, 0.420D);

                // Spawn flame particles at the fuse end for east/west
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    double particleX = entity.getX();
                    double particleY = entity.getY() + 0.1;
                    double particleZ = entity.getZ() + (dir == Direction.EAST ? -0.5 : 0.5);
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            particleX, particleY, particleZ,
                            0.0D, 0.05D, 0.0D
                    );
                }
            }

        } else {
            // In flight
            dir = entity.getMotionDirection();

            // Perform rotation based on motion direction
            if (dir == Direction.NORTH) {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() - entity.getSpinRotation()));

                // Particle for north
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            entity.getX(), entity.getY(), entity.getZ() - 0.5,
                            0.0D, 0.05D, 0.0D
                    );
                }
            } else if (dir == Direction.SOUTH) {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() + entity.getSpinRotation()));

                // Particle for south
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            entity.getX(), entity.getY(), entity.getZ() + 0.5,
                            0.0D, 0.05D, 0.0D
                    );
                }
            } else if (dir == Direction.EAST) {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() - entity.getSpinRotation()));

                // Particle for east
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            entity.getX() + 0.5, entity.getY(), entity.getZ(),
                            0.0D, 0.05D, 0.0D
                    );
                }
            } else if (dir == Direction.WEST) {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() + entity.getSpinRotation()));

                // Particle for west
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            entity.getX() - 0.5, entity.getY(), entity.getZ(),
                            0.0D, 0.05D, 0.0D
                    );
                }
            } else {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() + entity.getSpinRotation()));

                // Default particle for other directions
                if (entity.getLitTime() > 0 && entity.level().isClientSide()) {
                    entity.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.FLAME,
                            entity.getX(), entity.getY(), entity.getZ() - 0.5,
                            0.0D, 0.05D, 0.0D
                    );
                }
            }
        }

        // Render the model
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                bufferSource, this.model.renderType(this.getTextureLocation(entity)), false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(ThrownDynamiteStick entity) {
        if (entity.getLitTime() > 0) {
            return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_dynamite_stick_lit.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_dynamite_stick.png");
        }
    }

}
