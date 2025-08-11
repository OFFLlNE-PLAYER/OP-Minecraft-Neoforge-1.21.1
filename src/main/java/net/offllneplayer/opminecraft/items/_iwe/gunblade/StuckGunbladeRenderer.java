package net.offllneplayer.opminecraft.items._iwe.gunblade;

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
public class StuckGunbladeRenderer extends EntityRenderer<StuckGunblade> {
	public StuckGunbladeRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(StuckGunblade entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();

		Direction stuckFace = entity.getStuckFace();
		float rotation = entity.getRenderingRotation();
		float offset = 0.269420F;
		float yoffs = 0.20420F;

		// Apply the offset based on direction - inverting it from what was in the hitbox
		if (stuckFace == Direction.UP) {
			double cos = Math.cos(Math.toRadians(rotation));
			double sin = Math.sin(Math.toRadians(rotation));

			poseStack.translate(offset * cos, yoffs, -offset * sin);

		} else if (stuckFace == Direction.DOWN) {
			double cos = Math.cos(Math.toRadians(rotation));
			double sin = Math.sin(Math.toRadians(rotation));
			poseStack.translate(-offset * cos, -yoffs, offset * sin);

		} else if (stuckFace == Direction.NORTH) {
			poseStack.translate(offset, 0, -yoffs);
		} else if (stuckFace == Direction.SOUTH) {
			poseStack.translate(-offset, 0, yoffs);
		} else if (stuckFace == Direction.EAST) {
			poseStack.translate(yoffs, 0, offset);
		} else if (stuckFace == Direction.WEST) {
			poseStack.translate(-yoffs, 0, -offset);
		}


		poseStack.scale(1.420F, 1.420F, 1.420F);

		if (!entity.isGrounded()) {
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
			poseStack.mulPose(Axis.ZP.rotationDegrees(45));

		} else {
			if (stuckFace == Direction.NORTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(270));
				poseStack.mulPose(Axis.YP.rotationDegrees(0));
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			} else if (stuckFace == Direction.SOUTH) {
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			} else if (stuckFace == Direction.EAST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(270));
				poseStack.mulPose(Axis.YP.rotationDegrees(0));
				poseStack.mulPose(Axis.ZP.rotationDegrees(315));
			} else if (stuckFace == Direction.WEST) {
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				poseStack.mulPose(Axis.ZP.rotationDegrees(315));
			} else if (stuckFace == Direction.UP) {
				poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			} else if (stuckFace == Direction.DOWN) {
				poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
				poseStack.mulPose(Axis.ZP.rotationDegrees(225));
			}
		}

		Item bladeItem = GunbladeMaterial.valueOf(entity.getMaterialName()).getRegisteredItem();
		ItemStack bladeStack = new ItemStack(bladeItem != null ? bladeItem : RegistryBIBI.TITAN_GUNBLADE.get());
		BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(bladeStack, entity.getCommandSenderWorld(), null, entity.getId());

		Minecraft.getInstance().getItemRenderer().render(bladeStack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(StuckGunblade entity) {
		String materialName = entity.getMaterialName();
		return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_" + materialName + "_gunblade.png");
	}
}
