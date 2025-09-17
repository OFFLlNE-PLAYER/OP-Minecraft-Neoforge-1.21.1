package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * State/flag management for GOAL_useJump.
 */
public final class JumpState {

    // Tolerance for detecting increasing distance to landing (matches 0.1 close-enough policy squared)
    private static final double INCREASING_TOL_SQR = 0.0420D;

    public static void updateState(JumpContext ctx) {

        // While airborne, if we are getting farther from the landing, abort and reset to grounded
        if (ctx.state == JumpContext.State.AIRBORNE && !ctx.mob.onGround() && ctx.landingPos != null && !ctx.landingPos.equals(Vec3.ZERO)) {
            double d2 = JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.landingPos);
            if (!Double.isNaN(ctx.lastLandingDistSqr)) {
                if (d2 > ctx.lastLandingDistSqr + INCREASING_TOL_SQR) {
                    // Getting farther away from landing: reset to grounded (no cooldown, stop nav)
                    resetToGrounded(ctx, true, true);
                    ctx.lastLandingDistSqr = Double.NaN;
                    return;
                }
            }
            ctx.lastLandingDistSqr = d2;
        }

        boolean landed = ctx.mob.onGround() && !ctx.wasOnGround;
		 ctx.wasOnGround = ctx.mob.onGround();

		 //reset to grounded if we landed
		 // Clear any existing plan and airborne carry so next cycle plans fresh
        if (landed && ctx.state == JumpContext.State.AIRBORNE) {

            ctx.state = JumpContext.State.GROUNDED;
            ctx.landingPos = Vec3.ZERO;
            ctx.jumpFromPos = Vec3.ZERO;
            ctx.airCarryXZ = Vec3.ZERO;
            ctx.lastLandingDistSqr = Double.NaN;
        }
    }

    public static void updateFlags(Goal goal, JumpContext ctx) {
        if (ctx.state == JumpContext.State.AIRBORNE) {
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
        ctx.lastLandingDistSqr = Double.NaN;
    }
}
