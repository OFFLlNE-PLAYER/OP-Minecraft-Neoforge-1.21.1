package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

/**
 * Orchestrates the jump cycle: refresh capabilities/state, compute plan, commit/execute.
 * This goal package operates based on JumpContext and terrain.
 */
public final class JumpTick {

	// ---- GROUNDED: planning only ----
 public static void groundedTick(JumpContext ctx) {

        // Refresh capability-derived context each tick
        JumpContext.setupContext(ctx);

		if (ctx.mob.isInLiquid()) return;

		// Wait for cooldown before planning a new jump
		if ((ctx.mob.tickCount - ctx.lastJumpTick) < JumpContext.JUMP_COOLDOWN) {
			return;
		}

		// Recent failure, setup cooldown and return
		if (ctx.planFailedRecently) {
			JumpState.resetToGrounded(ctx, true, false);
			ctx.planFailedRecently = false;
			return;
		}

		// Back out and let state management handle transition to PATHFINDING
		if (ctx.hasPlan()) {
			return;
		}

		// Compute toward/grid for this tick to avoid stale facing
		Vec3 toward = JumpUtils.exactToward(ctx.mob, ctx.landingPos, ctx.targetExactPos);
		var grid = JumpUtils.cardinalFrom(toward, ctx.faceDir);
		ctx.faceDir = grid;

		// Ranged hold
		if (JumpContext.rangedHoldGate(ctx, grid)) {
			ctx.mob.setDeltaMovement(0,  ctx.mob.getDeltaMovement().y, 0);
			ctx.mob.getNavigation().stop();
			return;
		}

  // No plan; Try to make one, but only if a jump could plausibly improve pursuit
        // Quick pre-check: if there is no forward/diagonal obstacle within capability and target is not above, skip planning
        int mobY = ctx.mob.blockPosition().getY();
        int targetY = BlockPos.containing(ctx.targetExactPos.x, ctx.targetExactPos.y, ctx.targetExactPos.z).getY();
        boolean needElevNow = mobY < targetY;
        int forwardLimit = Math.min(ctx.maxGap + 1, ctx.balloonJump ? 6 : 3);
        if (!needElevNow && !JumpUtils.hasForwardOrDiagonalObstacle(ctx.mob.level(), ctx.mob.blockPosition(), grid, forwardLimit)) {
            // nothing forcing a jump this tick; let vanilla pursue
            return;
        }
		JumpPlanner.decideJump(ctx, ctx.mob.level(), ctx.mob.blockPosition(), grid);
	}


	// ---- PATHFINDING: execution ----
 public static void pathfindTick(JumpContext ctx) {

         // Refresh capability-derived context each tick
         JumpContext.setupContext(ctx);

		// Bounce back to GROUNDED and let the next cycle plan
		if (!ctx.hasPlan() || ctx.mob.isInLiquid()) {
			JumpState.resetToGrounded(ctx, false, false);
			return;
		}

		// Cooldown gate: if still cooling down, do nothing this tick (let vanilla AI act)
		if ((ctx.mob.tickCount - ctx.lastJumpTick) < JumpContext.JUMP_COOLDOWN) {
			return;
		}

		// Recent failure, cooldown and return to GROUNDED
		if (ctx.planFailedRecently) {
			JumpState.resetToGrounded(ctx, true, false);
			ctx.planFailedRecently = false;
			return;
		}

		// Compute toward/grid for this tick to avoid stale facing
		ctx.faceDir = JumpUtils.cardinalFrom(JumpUtils.exactToward(ctx.mob, ctx.landingPos, ctx.targetExactPos), ctx.faceDir);

		// Ranged hold
		if (JumpContext.rangedHoldGate(ctx, ctx.faceDir)) {
			ctx.mob.setDeltaMovement(0,  ctx.mob.getDeltaMovement().y, 0);
			JumpState.resetToGrounded(ctx, true, true);
			return;
		}

		// Distance from mob current to jumpFromPos
	 	double currentTakeoffErrorSqr = JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.jumpFromPos);

		// If distance does not improve for 1 second, fail plan and cooldown
      if (currentTakeoffErrorSqr < ctx.lastTakeoffErrorSqr) {
          ctx.lastTakeoffErrorSqr = currentTakeoffErrorSqr;
          ctx.takeoffNotImprovingTicks = 0;
      } else {
			ctx.takeoffNotImprovingTicks++;
			if (ctx.takeoffNotImprovingTicks >= 20) {
				JumpState.failPlanAndCooldown(ctx);
				return;
			}
		}

	// Execute the plan
	JumpExecutor.executeNavOrJump(ctx);
	}


  // ---- AIRBORNE: movement/steering ----
    public static void airborneTick(JumpContext ctx) {
        JumpExecutor.navToAirborne(ctx);
    }
}
