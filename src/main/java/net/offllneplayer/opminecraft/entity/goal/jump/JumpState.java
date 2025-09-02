package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * State/flag management for GOAL_useJump.
 */
public final class JumpState {

    public static void updateState(JumpContext ctx) {

        boolean landed = ctx.mob.onGround() && !ctx.wasOnGround;
		 ctx.wasOnGround = ctx.mob.onGround();

		 // Grounded with a plan -> Pathfinding (unless we are explicitly holding for ranged)
        // PATHFINDING with no plan -> GROUNDED so it plans
        if (ctx.hasPlan() && ctx.state == JumpContext.State.GROUNDED) {
			 ctx.state = JumpContext.State.PATHFINDING;
		 } else if (ctx.state == JumpContext.State.PATHFINDING && !ctx.hasPlan()) {
			 ctx.state = JumpContext.State.GROUNDED;
		 }

		 //reset to grounded if we landed
		 // Clear any existing plan and airborne carry so next cycle plans fresh
        if (landed && ctx.state == JumpContext.State.AIRBORNE) {
                    ctx.state = JumpContext.State.GROUNDED;
            ctx.landingPos = Vec3.ZERO;
            ctx.jumpFromPos = Vec3.ZERO;
            ctx.airCarryXZ = Vec3.ZERO;
            ctx.lastTakeoffErrorSqr = Double.MAX_VALUE;
            ctx.takeoffNotImprovingTicks = 0;
            ctx.planFailedRecently = false;
            // Successful landing; clear any remembered failed landing avoidance
            ctx.failedJumpFromPos = Vec3.ZERO;
        }
    }

    public static void updateFlags(Goal goal, JumpContext ctx) {
        if (ctx.state == JumpContext.State.PATHFINDING) {
            goal.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
        } else if (ctx.state == JumpContext.State.AIRBORNE) {
            goal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		  } else { // GROUNDED state - relinquish all flags
			  goal.setFlags(EnumSet.noneOf(Goal.Flag.class));
		  }
	 }

    public static void resetToGrounded(JumpContext ctx, boolean applyCooldown, boolean stopNavigation) {
		 if (applyCooldown) ctx.lastJumpTick = ctx.mob.tickCount;
       if (stopNavigation) ctx.mob.getNavigation().stop();

       ctx.state = JumpContext.State.GROUNDED;

		// Clear current motion state for a fresh plan
    	ctx.landingPos = Vec3.ZERO;
		ctx.jumpFromPos = Vec3.ZERO;
		ctx.airCarryXZ = Vec3.ZERO;
      ctx.lastTakeoffErrorSqr = Double.MAX_VALUE;
		ctx.takeoffNotImprovingTicks = 0;
    }

	// --- plan failure handling ---
	public static void failPlanAndCooldown(JumpContext ctx) {
		// Record failure using the jump-from (takeoff) position so we avoid reusing the same jump-from next cycle
		ctx.failedJumpFromPos = ctx.jumpFromPos;
		ctx.planFailedRecently = true;

		// plan failed; apply cooldown and reset
		resetToGrounded(ctx, true, true);
	}
}
