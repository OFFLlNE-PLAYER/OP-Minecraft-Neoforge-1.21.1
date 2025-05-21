package net.offllneplayer.opminecraft.method.hatchet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.core.Direction;

public class HatchetRenderRotations {


	public static void applyProjectileRotation(PoseStack poseStack, Direction dir, Direction stuckFace, boolean isGrounded, float rotation) {

		if (!isGrounded) {
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
	}
}

