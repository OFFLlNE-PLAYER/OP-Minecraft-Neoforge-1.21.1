package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

/**
 * Executes the current jump plan (navigate path to take off and fire jump) without any planning logic.
 * Purely consumes JumpContext and finalized scanner positions.
 */
public final class JumpExecutor {

	/** Decide movement type */
    public static void executeNavOrJump(JumpContext ctx) {
     // Must have a current plan
    	if (!ctx.hasPlan()) return;

		ctx.faceDir = JumpUtils.cardinalFrom(JumpUtils.exactToward(ctx.mob, ctx.jumpFromPos, ctx.targetExactPos), ctx.faceDir);

      // At intended takeoff; stop nav, look at landing, and fire when cooldown allows
      if (isAtJumpPosition(ctx)) {
         if (ctx.mob.onGround()) {
            fireJump(ctx);
         }
         return;
      }
        navTo(ctx);
    }

	// Use the mob's position to decide readiness at takeoff
	public static boolean isAtJumpPosition(JumpContext ctx) {
		if (!ctx.mob.onGround()) return false;

		// Ensure we are effectively on the same stand Y (allow small epsilon for slabs/steps)
		if (Math.abs(ctx.mob.position().y - ctx.jumpFromPos.y) > 0.51D) return false;
		// Within ~0.4 blocks of takeoff point using mob's exact position
		return JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.jumpFromPos) <= 0.2420D;
	}


	// set body/head/entity yaw from an XZ direction (keep visuals and motion aligned)
	private static void setYawFromDirXZ(JumpContext ctx, double dx, double dz) {
		float yaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI - 90.0D); // - 90 for the stupid entity twist
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


	/** Forced movement */
	// Navigate to jump-from block center using ground-Y beneath it; while in PATHFINDING state
	public static void navTo(JumpContext ctx) {

		// Look at above the takeoff
		ctx.mob.getLookControl().setLookAt(ctx.jumpFromPos.x, ctx.jumpFromPos.y + 1, ctx.jumpFromPos.z);

		// Face body toward jumpfrom center
		setYawFromDirXZ(ctx, ctx.jumpFromPos.x - ctx.mob.getX(), ctx.jumpFromPos.z - ctx.mob.getZ());

		// Continue using navigation toward the takeoff
		ctx.mob.getNavigation().moveTo(ctx.jumpFromPos.x, ctx.jumpFromPos.y, ctx.jumpFromPos.z, 1.0D);

		// force horizontal motion toward the takeoff using max capable speed
		ctx.mob.setDeltaMovement(setupNavTo(ctx).x, ctx.mob.getDeltaMovement().y, setupNavTo(ctx).z);
	}


	/**
	 * Prepare a per-tick horizontal carry vector that pushes the mob toward the jump-from position,
	 * using its maximum capable ground speed (Attributes.MOVEMENT_SPEED). Does not mutate ctx.airCarryXZ
	 * so airborne behavior remains driven by the planner’s setupNavToAirborne.
	 * Returns Vec3.ZERO if inputs are invalid or already aligned.
	 */
	public static Vec3 setupNavTo(JumpContext ctx) {
		Vec3 diff = new Vec3(ctx.jumpFromPos.x - ctx.mob.position().x, 0.0D, ctx.jumpFromPos.z - ctx.mob.position().z);
		return new Vec3(diff.normalize().x * ctx.mob.getAttributeValue(Attributes.MOVEMENT_SPEED), 0.0D, diff.normalize().z * ctx.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
	}


	/**
	 * Compute and store the airborne horizontal carry vector based on the mob’s max capable speed.
	 */
	public static void setupNavToAirborne(JumpContext ctx) {

		// Direction (horizontal) from takeoff to landing
		Vec3 dir = new Vec3(ctx.landingPos.x - ctx.jumpFromPos.x, 0.0D, ctx.landingPos.z - ctx.jumpFromPos.z);

		// Get mob's max capable movement speed attribute (generic.movement_speed)
		ctx.airCarryXZ = new Vec3(dir.normalize().x * ctx.mob.getAttributeValue(Attributes.MOVEMENT_SPEED), 0.0D, dir.normalize().z * ctx.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
	}

	// Fire the actual jump to the intended landing position
	public static void fireJump(JumpContext ctx) {

		ctx.mob.getNavigation().stop();

		// Look at the jumpto center (+1 so we don't look at the ground)
		ctx.mob.getLookControl().setLookAt(ctx.landingPos.x, ctx.landingPos.y + 1, ctx.landingPos.z);

		// Face toward jumpto (or target) center
		setYawFromDirXZ(ctx, ctx.landingPos.x - ctx.mob.getX(), ctx.landingPos.z - ctx.mob.getZ());

		// Ensure the airborne carry is freshly computed (consistent use of setupNavToAirborne)
		setupNavToAirborne(ctx);
		ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

		ctx.mob.getJumpControl().jump();
		ctx.mob.setJumping(true);
		ctx.state = JumpContext.State.AIRBORNE;
		ctx.lastJumpTick = ctx.mob.tickCount;
	}

	// Control airborne movement toward the landing while in AIRBORNE state
	public static void navToAirborne(JumpContext ctx) {

		// aim toward the TARGET while airborne (not the landing)
		ctx.mob.getLookControl().setLookAt(ctx.targetExactPos.x, ctx.targetExactPos.y + 1, ctx.targetExactPos.z);

		// Face body/head toward target
		setYawFromDirXZ(ctx, ctx.targetExactPos.x - ctx.mob.getX(), ctx.targetExactPos.z - ctx.mob.getZ());

		// airborne carry is computed
		setupNavToAirborne(ctx);

		// Within landing radius: stop forced horizontal movement (preserve Y)
		if (!isAtLandingPosition(ctx)) {
			ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

		} else { // landing reached; rinse and repeat
			ctx.mob.setDeltaMovement(ctx.mob.getDeltaMovement().x * 0.1, ctx.mob.getDeltaMovement().y, ctx.mob.getDeltaMovement().z * 0.1);

			// Prevent repeating the same jump
			ctx.failedJumpFromPos = ctx.jumpFromPos;

			JumpState.resetToGrounded(ctx, false, true);
		}
	}

	// Use the mob's position to decide if at landing position
	public static boolean isAtLandingPosition(JumpContext ctx) {
		if (!ctx.mob.onGround()) return false;

		// Ensure we are effectively on the same stand Y (allow small epsilon for slabs/steps)
		if (Math.abs(ctx.mob.position().y - ctx.landingPos.y) > 0.51D) return false;
		// Within landing radius
		return JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.landingPos) <= 0.2420D;
	}
}
