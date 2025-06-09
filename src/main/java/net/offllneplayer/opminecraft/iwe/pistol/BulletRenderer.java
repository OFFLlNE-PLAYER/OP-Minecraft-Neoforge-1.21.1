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
public class BulletRenderer extends EntityRenderer<Bullet> {
	public BulletRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(Bullet entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
	
		float rotation = entity.getRenderingRotation();
		Direction dir = entity.getDirection();
		if (dir == Direction.EAST || dir == Direction.WEST) dir = entity.getDirection().getOpposite();

			if (dir == Direction.NORTH) {
				poseStack.scale(0.2F, 0.2F, 0.4F);
				poseStack.mulPose(Axis.YP.rotationDegrees(90));
				poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
			}
			if (dir == Direction.SOUTH) {
				poseStack.scale(0.2F, 0.2F, 0.4F);
				poseStack.mulPose(Axis.YP.rotationDegrees(270));
				poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
			}
			if (dir == Direction.EAST) {
				poseStack.scale(0.2F, 0.4F, 0.2F);
				poseStack.mulPose(Axis.YP.rotationDegrees(0));
				poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
			}
			if (dir == Direction.WEST) {
				poseStack.scale(0.2F, 0.4F, 0.2F);
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
			}


		var itemInstance = entity.getMaterialFromName().getRegisteredRenderItem().getDefaultInstance();
		BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(itemInstance, entity.getCommandSenderWorld(), null, entity.getId());
		Minecraft.getInstance().getItemRenderer().render(itemInstance, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(Bullet entity) {
		String materialName = entity.getMaterialName();
		return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_" + materialName + "_bullet.png");
	}
}


