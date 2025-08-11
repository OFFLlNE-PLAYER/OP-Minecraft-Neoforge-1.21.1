package net.offllneplayer.opminecraft.items._iwe.tntstick;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.client.ModModelLayers;


@OnlyIn(Dist.CLIENT)
public class ThrownTNTStickRenderer extends EntityRenderer<ThrownTNTStick> {

	private final ThrownTNTStickModel model;

	public ThrownTNTStickRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ThrownTNTStickModel(context.bakeLayer(ModModelLayers.THROWN_TNT_STICK));
	}

	@Override
	public void render(ThrownTNTStick entity, float entityYaw, float partialTicks, PoseStack poseStack,
	                   MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();

		Direction dir;

		if (entity.isGrounded()) {
			// Handle the grounded state
			dir = entity.getStuckDirection();

			poseStack.mulPose(Axis.YP.rotationDegrees(dir.toYRot()));
			poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getGroundedzRotation()));

			if (dir == Direction.NORTH || dir == Direction.SOUTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
			} else if (dir == Direction.EAST || dir == Direction.WEST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(270));
			}



			if (entity.level().isClientSide() && !(entity.getLitTime() == -1)) {
				if (entity.getLitTime() < 60) {
					double offset = 0.32D + (0.4D * (entity.getLitTime() / 100.0D));
					double extraParticleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
					double extraParticleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
					double extraParticleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
					entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.010420D, 0.0D);
				}
				if (entity.getLitTime() < 40) {
					double offset = 0.3D + (0.1420D * (entity.getLitTime() / 100.0D));
					double extraParticleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
					double extraParticleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
					double extraParticleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
					entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.01420D, 0.0D);
				}
			}


		} else {
			// Handle the in-flight state

			dir = entity.getMotionDirection();

			if (dir == Direction.UP || dir == Direction.DOWN) dir = Direction.NORTH;

			if (dir == Direction.NORTH) {
				poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
				poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
				poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() - entity.getSpinRotation()));
			} else if (dir == Direction.SOUTH) {
				poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
				poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
				poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() + entity.getSpinRotation()));
			} else if (dir == Direction.EAST) {
				poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
				poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getYRot() - entity.getSpinRotation()));
				poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
			} else if (dir == Direction.WEST) {
				poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
				poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getYRot() - entity.getSpinRotation()));
				poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
			}
		}

		poseStack.translate(-0.420D, -0.2D, 0.420D);


		if (entity.level().isClientSide() && !(entity.getLitTime() == -1)) {
			if (entity.getLitTime() > 0) {
				double offset = 0.24D + (0.420D * (entity.getLitTime() / 100.0D));
				double particleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
				double particleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
				double particleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
				entity.level().addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, 0.010420D, 0.0D);
			}
			if (entity.getLitTime() < 30) {
				double offset = -0.26D + (0.1420D * (entity.getLitTime() / 100.0D));
				double extraParticleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
				double extraParticleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
				double extraParticleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
				entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.02420D, 0.0D);
			}
			if (entity.getLitTime() < 15) {
				double extraParticleX = entity.getX();
				double extraParticleY = entity.getY();
				double extraParticleZ = entity.getZ();
				entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.0169D, 0.0D);
			}
		}

		// Render the model once for either case
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
			bufferSource, this.model.renderType(this.getTextureLocation(entity)), false, false);
		this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(ThrownTNTStick entity) {
		if (entity.getLitTime() > 0) {
			return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_tnt_stick_lit.png");
		} else {
			return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_tnt_stick.png");
		}
	}
}