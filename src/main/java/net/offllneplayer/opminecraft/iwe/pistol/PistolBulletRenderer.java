package net.offllneplayer.opminecraft.iwe.pistol;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.OPMinecraft;

@OnlyIn(Dist.CLIENT)
public class PistolBulletRenderer extends EntityRenderer<PistolBullet> {
	public PistolBulletRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(PistolBullet entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();


		Direction dir = entity.getDirection();
		if (dir == Direction.EAST || dir == Direction.WEST) dir = entity.getDirection().getOpposite();

			if (dir == Direction.NORTH) {
				poseStack.scale(0.1F, 0.1F, 0.2F);
				poseStack.mulPose(Axis.YP.rotationDegrees(90));
			}
			if (dir == Direction.SOUTH) {
				poseStack.scale(0.1F, 0.1F, 0.2F);
				poseStack.mulPose(Axis.YP.rotationDegrees(270));
			}
			if (dir == Direction.EAST) {
				poseStack.scale(0.2F, 0.1F, 0.1F);
				poseStack.mulPose(Axis.ZP.rotationDegrees(0));
			}
			if (dir == Direction.WEST) {
				poseStack.scale(0.2F, 0.1F, 0.1F);
				poseStack.mulPose(Axis.ZP.rotationDegrees(180));
			}

		float rotation = entity.getRenderingRotation();
		poseStack.mulPose(Axis.XP.rotationDegrees(rotation));


		var itemInstance = entity.getMaterialFromName().getRegisteredRenderItem().getDefaultInstance();
		BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(itemInstance, entity.getCommandSenderWorld(), null, entity.getId());
		Minecraft.getInstance().getItemRenderer().render(itemInstance, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(PistolBullet entity) {
		return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_pistol_bullet.png");
	}
}


