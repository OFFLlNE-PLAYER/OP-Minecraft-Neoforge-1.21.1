package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

/**
 * Executes the current jump plan (navigate path to take off and fire jump) without any planning logic.
 * Purely consumes JumpContext and finalized scanner positions.
 */
public final class JumpExecutor {

	// y distance within expected position
	public static final double WITHIN_EXPECTED_Y = 0.51D;


	/** Decide movement type */
   public static void executeJump(JumpContext ctx) {
     	// Must have a current plan
    	if (!ctx.hasPlan()) return;

		ctx.faceDir = JumpUtils.cardinalFrom(JumpUtils.exactToward(ctx.mob, ctx.jumpFromPos, ctx.targetExactPos), ctx.faceDir);

		fireJump(ctx);

		// Prevent repeating the same jump
		ctx.recordAvoidJumpFrom(ctx.jumpFromPos);
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

		// Stop any existing navigation immediately
		ctx.mob.getNavigation().stop();

		// Look at the jumpto center (+1 so we don't look at the ground)
		ctx.mob.getLookControl().setLookAt(ctx.landingPos.x, ctx.landingPos.y + 1, ctx.landingPos.z);

		// Face toward jumpto (or target) center
		setYawFromDirXZ(ctx, ctx.landingPos.x - ctx.mob.getX(), ctx.landingPos.z - ctx.mob.getZ());

		// Ensure the airborne carry is freshly computed (consistent use of setupNavToAirborne)
		setupNavToAirborne(ctx);
		ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

		// FIRE
		ctx.mob.getJumpControl().jump();
		ctx.mob.setJumping(true);
		ctx.state = JumpContext.State.AIRBORNE;
		ctx.lastJumpTick = ctx.mob.tickCount;
	}

	// Control airborne movement toward the landing while in AIRBORNE state
	public static void navToAirborne(JumpContext ctx) {

		// Stop any existing navigation immediately
		ctx.mob.getNavigation().stop();

		// aim toward the TARGET while airborne (not the landing)
		ctx.mob.getLookControl().setLookAt(ctx.targetExactPos.x, ctx.targetExactPos.y + 1, ctx.targetExactPos.z);

		// Face body/head toward target
		setYawFromDirXZ(ctx, ctx.targetExactPos.x - ctx.mob.getX(), ctx.targetExactPos.z - ctx.mob.getZ());

		// Within landing radius: stop forced horizontal movement (preserve Y)
		if (!isAtLandingPosition(ctx)) {

			// airborne carry is computed
			setupNavToAirborne(ctx);

			// Force movement toward the landing
			ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

		} else { // landing reached; rinse and repeat
			ctx.mob.setDeltaMovement(ctx.mob.getDeltaMovement().x * 0.0420, ctx.mob.getDeltaMovement().y, ctx.mob.getDeltaMovement().z * 0.0420);
			JumpState.resetToGrounded(ctx, false, true);
		}
	}


	// Use the mob's position to decide if at landing position
	public static boolean isAtLandingPosition(JumpContext ctx) {
		if (!ctx.mob.onGround()) return false;

		// Ensure we are effectively on the same stand Y (allow small epsilon for slabs/steps)
  if (Math.abs(ctx.mob.position().y - ctx.landingPos.y) > WITHIN_EXPECTED_Y) return false;
		// Within landing radius
		return JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.landingPos) <= 0.2420D;
	}
}
