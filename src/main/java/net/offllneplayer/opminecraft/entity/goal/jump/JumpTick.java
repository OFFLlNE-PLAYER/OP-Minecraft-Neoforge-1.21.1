package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

/**
 * Orchestrates the jump cycle: refresh capabilities/state, compute plan, commit/execute.
 * This goal package operates based on JumpContext and terrain.
 */
public final class JumpTick {

	// ---- GROUNDED: plan or execute (combined) ----
	public static void groundedTick(JumpContext ctx) {

        // in water or lava, no jumping
		if (ctx.mob.isInLiquid()) return;

		// Cooldown check
		if ((ctx.mob.tickCount - ctx.lastJumpTick) < JumpContext.JUMP_COOLDOWN) {
			return;
		}

		// Ranged hold, setup cooldown and return
		if (ctx.rangedHoldActive) {
			JumpState.resetToGrounded(ctx, true, false);
			return;
		}

		// Plan if no plan exists
		if (!ctx.hasPlan()) {
			JumpPlanner.decideJump(ctx, ctx.mob.level(), ctx.mob.blockPosition(), ctx.faceDir);
		}

		// Execute if there is a plan
		if (ctx.hasPlan()) {
			JumpExecutor.executeJump(ctx);

		//still no plan, cooldown and reset
		} else {
			JumpState.resetToGrounded(ctx, true, false);
		}
	}

  // ---- AIRBORNE: movement/steering ----
    public static void airborneTick(JumpContext ctx) {
        JumpExecutor.navToAirborne(ctx);
    }
}
