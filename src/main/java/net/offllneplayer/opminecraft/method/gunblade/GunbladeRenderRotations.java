package net.offllneplayer.opminecraft.method.gunblade;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;

public class GunbladeRenderRotations {

	public static void applyGunbladeRotation(PoseStack poseStack, Direction stuckFace, Direction dir, boolean isGrounded, float rotation) {

		if (!isGrounded) {
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
			poseStack.mulPose(Axis.ZP.rotationDegrees(45));
		} else {
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
				poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			} else if (stuckFace == Direction.DOWN) {
				poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
				poseStack.mulPose(Axis.ZP.rotationDegrees(45));
			}
		}
	}
}


