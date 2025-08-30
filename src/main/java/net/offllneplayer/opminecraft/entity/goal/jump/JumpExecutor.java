package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.UTIL.debug.LogColors;

/**
 * Executes the current jump plan (navigate path to take off and fire jump) without any planning logic.
 * Purely consumes JumpContext and finalized scanner positions.
 */
public final class JumpExecutor {

	/** Decide movement type */
    public static void executeNavOrJump(JumpContext ctx) {
    	// Must have a current plan
    	if (ctx.landingPos.equals(Vec3.ZERO) || ctx.jumpFromPos.equals(Vec3.ZERO)) return;

		boolean atJump = isAtJumpPosition(ctx);
		Vec3 toward = JumpUtils.exactToward(ctx.mob, ctx.jumpFromPos, ctx.targetExactPos);
		ctx.faceDir = JumpUtils.cardinalFrom(toward, ctx.faceDir);

		LogColors.debugBlueBold("[OP_DEBUG_useJump.exe] atJump=" + atJump);
		LogColors.debugBlue("[OP_DEBUG_useJump.exe] ---- takeoff=" + ctx.jumpFromPos + "landing=" + ctx.landingPos + "----");

      // At intended takeoff; stop nav, look at landing, and fire when cooldown allows
      if (atJump) {
         if (ctx.mob.onGround()) {
				LogColors.debugBlueBold("[OP_DEBUG_useJump.exe]~ ~ ~ Jump!!! ~ ~ ~");
            fireJump(ctx);
         }
         return;
      }
        navTo(ctx);
    }


	/** Forced movement */
	// Navigate to jump-from block center using ground-Y beneath it; while in PATHFINDING state
	public static void navTo(JumpContext ctx) {

		// aim toward the landingpos
		double lookY = ctx.landingPos.y + 1; // look slightly above jumpfrom
		ctx.mob.getLookControl().setLookAt(ctx.landingPos.x, lookY, ctx.landingPos.z);

		// Face body toward jumpfrom center
		setYawFromDirXZ(ctx, ctx.jumpFromPos.x - ctx.mob.getX(), ctx.jumpFromPos.z - ctx.mob.getZ());

		// Path surface center
		ctx.mob.getNavigation().moveTo(ctx.jumpFromPos.x, ctx.jumpFromPos.y + 0.5, ctx.jumpFromPos.z, 1.0D);
	}


 	// Control airborne movement toward the landing while in AIRBORNE state
	public static void navToAirborne(JumpContext ctx) {
	
	double distXZ = Math.hypot(ctx.landingPos.x - ctx.mob.position().x, ctx.landingPos.z - ctx.mob.position().z);
	
		// aim toward the TARGET while airborne (not the landing)
		double lookY = ctx.targetExactPos.y + 1;
		ctx.mob.getLookControl().setLookAt(ctx.targetExactPos.x, lookY, ctx.targetExactPos.z);
		try { LogColors.debugBlue("[OP_DEBUG_useJump.exe] AIRBORNE look=target pos=" + ctx.targetExactPos); } catch (Throwable ignored) {}
		// Face body/head toward target
		setYawFromDirXZ(ctx, ctx.targetExactPos.x - ctx.mob.getX(), ctx.targetExactPos.z - ctx.mob.getZ());
		
		// Within landing radius: stop forced horizontal movement (preserve Y)
		if (distXZ <= 0.420D) {
			ctx.mob.setDeltaMovement(ctx.mob.getDeltaMovement().x * 0.2, ctx.mob.getDeltaMovement().y, ctx.mob.getDeltaMovement().z * 0.2);
			return;
		}
		
		ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);
	}


 	// Fire the actual jump to the intended landing position
    public static void fireJump(JumpContext ctx) {

		ctx.mob.getNavigation().stop();

		// Look at the jumpto center (+1 so we don't look at the ground)
		double lookY = ctx.landingPos.y + 1;
		ctx.mob.getLookControl().setLookAt(ctx.landingPos.x, lookY, ctx.landingPos.z);

		// Face toward jumpto (or target) center
		setYawFromDirXZ(ctx, ctx.landingPos.x - ctx.mob.getX(), ctx.landingPos.z - ctx.mob.getZ());

		ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

		ctx.mob.getJumpControl().jump();
		ctx.mob.setJumping(true);
 		ctx.state = JumpContext.State.AIRBORNE;
		ctx.lastJumpTick = ctx.mob.tickCount;
	}


	// Helper: set body/head/entity yaw from an XZ direction (keep visuals and motion aligned)
	private static void setYawFromDirXZ(JumpContext ctx, double dx, double dz) {
		float yaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI - 90.0D);
		// Core yaw
		ctx.mob.setYRot(yaw);
		ctx.mob.yRotO = yaw;
		// Body yaw
		ctx.mob.setYBodyRot(yaw);
		ctx.mob.yBodyRotO = yaw;
		// Head yaw
		ctx.mob.setYHeadRot(yaw);
		ctx.mob.yHeadRotO = yaw;
	}


	// Use the mob's EXACT position to decide readiness at takeoff
	public static boolean isAtJumpPosition(JumpContext ctx) {
		if (!ctx.mob.onGround()) return false;

		// Ensure we are effectively on the same stand Y (allow small epsilon for slabs/steps)
		if (Math.abs(ctx.mob.position().y - ctx.jumpFromPos.y) > 0.51D) return false;
		// Within ~0.4 blocks of takeoff point using mob's exact position
		return JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.jumpFromPos) <= 0.22D;
	}
}
