package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.Mob;
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

		ctx.faceDir = JumpUtils.cardinalFrom(exactToward(ctx.mob, ctx.jumpFromPos, ctx.landingPos), ctx.faceDir);

		fireJump(ctx);

		// Prevent repeating the same jumps (stores 2)
		ctx.recordAvoidLanding(ctx.landingPos);
	}


	// Facing
	public static Vec3 exactToward(Mob mob, Vec3 landingPos, Vec3 targetExactPos) {
		Vec3 from = mob.position();
		Vec3 to = (landingPos != null && !landingPos.equals(Vec3.ZERO)) ? landingPos : new Vec3(targetExactPos.x, from.y, targetExactPos.z);
		Vec3 dir = new Vec3(to.x - from.x, 0.0D, to.z - from.z);
		if (dir.lengthSqr() < 1.0e-6) {
			Vec3 vv = mob.getViewVector(1.0F);
			Vec3 xz = new Vec3(vv.x, 0.0D, vv.z);
			return xz.lengthSqr() < 1.0e-6 ? Vec3.ZERO : xz.normalize();
		}
		return dir.normalize();
	}


	// Fire the actual jump to the intended landing position
	public static void fireJump(JumpContext ctx) {

		// Stop any existing navigation immediately
		ctx.mob.getNavigation().stop();

		// Look at the jumpto center (+1 so we don't look at the ground)
		ctx.mob.getLookControl().setLookAt(ctx.landingPos.x, ctx.landingPos.y + 1, ctx.landingPos.z);

		// Face toward jumpto (or target) center
		setYawFromDirXZ(ctx, ctx.landingPos.x - ctx.mob.getX(), ctx.landingPos.z - ctx.mob.getZ());

		// Freshly computed airborne carry
		setAirborneMovement(ctx);

		// FIRE
		ctx.mob.getJumpControl().jump();
		ctx.mob.setJumping(true);

		// Move toward landing
		ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

		// Mark as airborne
		ctx.state = JumpContext.State.AIRBORNE;
		ctx.lastJumpTick = ctx.mob.tickCount;
	}


	// Use the mob's position to decide if at landing position
	public static boolean isAtLandingPosition(JumpContext ctx) {
		if (!ctx.mob.onGround()) return false;

		// Ensure we are effectively on the same stand Y (allow small epsilon for slabs/steps)
		if (Math.abs(ctx.mob.position().y - ctx.landingPos.y) > WITHIN_EXPECTED_Y) return false;
		// Within landing radius
		return JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.landingPos) <= 0.2420D;
	}


	// set body/head/entity yaw from an XZ direction (keep visuals and motion aligned)
	public static void setYawFromDirXZ(JumpContext ctx, double dx, double dz) {
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
	public static void setAirborneMovement(JumpContext ctx) {
		// Direction (horizontal) from takeoff to landing
		Vec3 dir = new Vec3(ctx.landingPos.x - ctx.jumpFromPos.x, 0.0D, ctx.landingPos.z - ctx.jumpFromPos.z);

		// Get mob's max capable movement speed attribute (generic.movement_speed)
		double baseSpeed = ctx.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
		double speed = baseSpeed;

		// tiny dampened boost without balloon for those pesky 2 block gaps
		double distance = JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.landingPos);
		if (distance > 0.5D && !ctx.hasBalloon) {
			// Boost decreases as distance increases (dampening effect)
			double maxBoost = 0.3; // 30% max boost
			double dampeningFactor = Math.min(1.0, 2.0 / distance); // stronger dampening for longer jumps
			speed = baseSpeed * (1.0 + maxBoost * dampeningFactor);
		}

		ctx.airCarryXZ = new Vec3(dir.normalize().x * speed, 0.0D, dir.normalize().z * speed);

	}
}
