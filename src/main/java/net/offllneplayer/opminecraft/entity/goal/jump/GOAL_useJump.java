package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;


/**
 * Ai improvement goal.
 * Forces jumping with the purpose of aggressively pursuing the target position.
 * If Grounded and no jump necessary, immediately turns over to vanilla control.
 * This class is the empty head of the jump goal.
 * - _______________
 * * -- State machine:
 *  ** Grounded: compute plan if jumping is necessary.
 *  ** Pathing: execute the plan.
 *  ** Airborne: move/steer towards target. Revert to Grounded on landing.
 * - _________
 * * -- Classes:
 * _ JumpContext = forces capabilities respective to the mob and context (mêlée/ranged, balloon/non-balloon).
 * _ JumpState = state/flag management.
 * _ JumpTick = orchestrate the jump cycle based on the current state.
 * _ JumpPlanner = compute a jump plan given the current state and context.
 * _ JumpScanner = scan for a jump candidate given the current plan.
 * _ JumpExecutor = execute the finalized jump plan.
 * _ JumpUtils = misc. helpers.
 * -
 **/
public class GOAL_useJump extends Goal {
    private final JumpContext ctx;

    public GOAL_useJump(Mob mob) {
        this.ctx = new JumpContext(mob);
    }

    @Override
    public boolean canUse() {
        if (ctx.mob.level().isClientSide()) return false;
        return ctx.mob.getTarget() != null && ctx.mob.getTarget().isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        return ctx.mob.getTarget() != null && ctx.mob.getTarget().isAlive();
    }

    @Override
    public void start() {
        // Reset full context relevant to goal lifecycle to avoid stale state across activations
        ctx.landingPos = Vec3.ZERO;
        ctx.airCarryXZ = Vec3.ZERO;
        ctx.state = JumpContext.State.GROUNDED;
        ctx.wasOnGround = ctx.mob.onGround();
    }

    @Override
    public void stop() {
        // Clear plan and motion; also reset improvement tracking
        ctx.landingPos = Vec3.ZERO;
        ctx.airCarryXZ = Vec3.ZERO;
        ctx.state = JumpContext.State.GROUNDED;
        ctx.wasOnGround = ctx.mob.onGround();
    }

    @Override
    public void tick() {
		 var target = ctx.mob.getTarget();
       if (target == null || !target.isAlive()) return;
			
        // Refresh capabilities and state
       JumpContext.setupContext(ctx);
       JumpState.updateState(ctx);

       // Claims proper MOVE/LOOK/JUMP flags based on current state each tick
       JumpState.updateFlags(this, ctx);

		 // Handled by JumpState
       switch (ctx.state) {
         case GROUNDED:
               // Plan or execute (combined)
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
					JumpPlanner.decideJump(ctx, ctx.mob.level(), ctx.faceDir);
				}

				// Execute if there is a plan
				if (ctx.hasPlan()) {
					JumpExecutor.executeJump(ctx);

					//still no plan, cooldown and reset
				} else {
					JumpState.resetToGrounded(ctx, true, false);
				}
            break;

         case AIRBORNE:
             // Move/Steer
				// Within landing radius: stop forced horizontal movement (preserve Y)
				if (!JumpExecutor.isAtLandingPosition(ctx)) {

					// Stop any existing navigation immediately
					ctx.mob.getNavigation().stop();

					// aim toward the TARGET while airborne (not the landing)
					ctx.mob.getLookControl().setLookAt(ctx.targetExactPos.x, ctx.targetExactPos.y + 1, ctx.targetExactPos.z);

					// Face body/head toward target
					JumpExecutor.setYawFromDirXZ(ctx, ctx.targetExactPos.x - ctx.mob.getX(), ctx.targetExactPos.z - ctx.mob.getZ());

					// airborne carry is computed
					JumpExecutor.setAirborneMovement(ctx);

					// Force movement toward the landing
					ctx.mob.setDeltaMovement(ctx.airCarryXZ.x, ctx.mob.getDeltaMovement().y, ctx.airCarryXZ.z);

				} else { // landing reached; rinse and repeat
					ctx.mob.setDeltaMovement(ctx.mob.getDeltaMovement().x * 0.0420, ctx.mob.getDeltaMovement().y, ctx.mob.getDeltaMovement().z * 0.0420);
					JumpState.resetToGrounded(ctx, false, true);
				}
            break;
       }
    }
}
