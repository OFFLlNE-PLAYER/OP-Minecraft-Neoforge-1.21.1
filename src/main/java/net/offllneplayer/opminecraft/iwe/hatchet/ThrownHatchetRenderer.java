package net.offllneplayer.opminecraft.iwe.hatchet;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

@OnlyIn(Dist.CLIENT)
public class ThrownHatchetRenderer extends EntityRenderer<ThrownHatchet> {
	public ThrownHatchetRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(ThrownHatchet entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		Direction dir = entity.getDirection();
		Direction stuckFace = entity.getStuckFace();
		float rotation = entity.getRenderingRotation();

		if (!entity.isGrounded()) {
			if (dir == Direction.NORTH) poseStack.mulPose(Axis.YP.rotationDegrees(90));
			if (dir == Direction.SOUTH) poseStack.mulPose(Axis.YP.rotationDegrees(270));
			if (dir == Direction.EAST) poseStack.mulPose(Axis.YP.rotationDegrees(180));
			if (dir == Direction.WEST) poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
		} else {
			if (dir == Direction.NORTH) poseStack.mulPose(Axis.YP.rotationDegrees(270));
			if (dir == Direction.SOUTH) poseStack.mulPose(Axis.YP.rotationDegrees(90));
			if (dir == Direction.EAST) poseStack.mulPose(Axis.YP.rotationDegrees(0));
			if (dir == Direction.WEST) poseStack.mulPose(Axis.YP.rotationDegrees(180));

			if (stuckFace == Direction.NORTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90));
			} else if (stuckFace == Direction.SOUTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90));
			} else if (stuckFace == Direction.EAST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90));
			} else if (stuckFace == Direction.WEST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90));
			} else if (stuckFace == Direction.UP) {
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
			} else if (stuckFace == Direction.DOWN) {
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(180));
			}
		}

		Item hatchetItem = HatchetMaterial.valueOf(entity.getMaterialName()).getRegisteredItem();
		ItemStack hatchetStack = new ItemStack(hatchetItem != null ? hatchetItem : RegistryBIBI.GOLDEN_HATCHET.get());
		BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(hatchetStack, entity.getCommandSenderWorld(), null, entity.getId());

		Minecraft.getInstance().getItemRenderer().render(hatchetStack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(ThrownHatchet entity) {
		String materialName = entity.getMaterialName();
		return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_" + materialName + "_hatchet.png");
	}
}


