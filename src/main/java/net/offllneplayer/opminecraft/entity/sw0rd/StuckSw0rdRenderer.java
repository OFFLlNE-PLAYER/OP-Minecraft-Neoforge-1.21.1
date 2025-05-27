package net.offllneplayer.opminecraft.entity.sw0rd;

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
import net.minecraft.world.item.Items;
import net.offllneplayer.opminecraft.OPMinecraft;

public class StuckSw0rdRenderer extends EntityRenderer<StuckSw0rd> {
	public StuckSw0rdRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(StuckSw0rd entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();

		Direction stuckFace = entity.getStuckFace();
		float rotation = entity.getRenderingRotation();

		if (!entity.isGrounded()) {
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
			poseStack.mulPose(Axis.ZP.rotationDegrees(135));

		} else {
			if (stuckFace == Direction.NORTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(270));
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(135));
			} else if (stuckFace == Direction.SOUTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				poseStack.mulPose(Axis.YP.rotationDegrees(0));
				poseStack.mulPose(Axis.ZP.rotationDegrees(135));
			} else if (stuckFace == Direction.EAST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				poseStack.mulPose(Axis.YP.rotationDegrees(0));
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			} else if (stuckFace == Direction.WEST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(270));
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			} else if (stuckFace == Direction.UP) {
				poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
				poseStack.mulPose(Axis.ZP.rotationDegrees(135));
			} else if (stuckFace == Direction.DOWN) {
				poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
				poseStack.mulPose(Axis.ZP.rotationDegrees(315));
			}
		}


		String materialName = entity.getEntityData().get(StuckSw0rd.MATERIAL_NAME);
		Sw0rdMaterialMap.Sw0rdMaterial material = Sw0rdMaterialMap.get(materialName);
		Item bladeItem = material.getRegisteredItem();

		ItemStack bladeStack = new ItemStack(bladeItem != null ? bladeItem : Items.NETHERITE_SWORD);

		BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(bladeStack, entity.getCommandSenderWorld(), null, entity.getId());
		Minecraft.getInstance().getItemRenderer().render(bladeStack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(StuckSw0rd entity) {
		String materialName = entity.getEntityData().get(StuckSw0rd.MATERIAL_NAME);
		return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_" + materialName + "_sword.png");
	}
}
