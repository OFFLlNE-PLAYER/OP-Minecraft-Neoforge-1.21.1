package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.UTIL.debug.LogColors;

import java.util.EnumSet;

/**
 * State/flag management for GOAL_useJump.
 */
public final class JumpState {

    public static void updateState(JumpContext ctx) {
            JumpContext.State prev = ctx.state;
        boolean onGroundNow = ctx.mob.onGround();
        boolean landed = onGroundNow && !ctx.wasOnGround;

		 ctx.wasOnGround = onGroundNow;

		 // Grounded with a plan -> Pathfinding (unless we are explicitly holding for ranged)
        // PATHFINDING with no plan -> GROUNDED so it plans
        boolean hasPlan = !(ctx.landingPos.equals(Vec3.ZERO) || ctx.jumpFromPos.equals(Vec3.ZERO));

        if (hasPlan && ctx.state == JumpContext.State.GROUNDED) {
			 ctx.state = JumpContext.State.PATHFINDING;
		 } else if (ctx.state == JumpContext.State.PATHFINDING && !hasPlan) {
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
        // State change debug (print only on change)
        if (prev != ctx.state) {
            String who = ctx.mob.getName() != null ? ctx.mob.getName().getString() : ctx.mob.toString();
			  LogColors.debugBlueBold("[OP_DEBUG_useJump.state] " + who + " state=" + prev + " -> " + ctx.state);
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

			// Debug reason for reset
				 LogColors.debugBlueBold("[OP_DEBUG_useJump.state] ~ ~ ~ resetToGrounded(" + (applyCooldown ? "cooldown" : "noCooldown") + "," + (stopNavigation ? "stopNav":"keepNav"));
		  // ---------------------------

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
		Vec3 toStore = ctx.jumpFromPos;
		if (!toStore.equals(Vec3.ZERO)) {
			ctx.failedJumpFromPos = toStore;
		}
		ctx.planFailedRecently = true;

		String who = ctx.mob.getName() != null ? ctx.mob.getName().getString() : ctx.mob.toString();
		LogColors.debugBlueBold("[OP_DEBUG_useJump.state] ~ ~ ~ failPlanAndCooldown for:" + who);

		resetToGrounded(ctx, true, true);
	}
}
