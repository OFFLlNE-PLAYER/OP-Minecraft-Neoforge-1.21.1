package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.world.entity.LivingEntity;
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
        if (ctx.mob.getJumpControl() == null) return false;
        LivingEntity t = ctx.mob.getTarget();
        return t != null && t.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity t = ctx.mob.getTarget();
        return t != null && t.isAlive();
    }

    @Override
    public void start() {
        // Reset full context relevant to goal lifecycle to avoid stale state across activations
        ctx.landingPos = Vec3.ZERO;
        ctx.jumpFromPos = Vec3.ZERO;
        ctx.airCarryXZ = Vec3.ZERO;
        ctx.lastTakeoffErrorSqr = Double.MAX_VALUE;
        ctx.takeoffNotImprovingTicks = 0;
        ctx.state = JumpContext.State.GROUNDED;
        ctx.wasOnGround = ctx.mob.onGround();
    }

    @Override
    public void stop() {
        // Clear plan and motion; also reset improvement tracking
        ctx.landingPos = Vec3.ZERO;
        ctx.jumpFromPos = Vec3.ZERO;
        ctx.airCarryXZ = Vec3.ZERO;
        ctx.state = JumpContext.State.GROUNDED;
        ctx.wasOnGround = ctx.mob.onGround();
    }

    @Override
    public void tick() {
        LivingEntity target = ctx.mob.getTarget();
        if (target == null || !target.isAlive()) return;

        // Refresh capabilities and state
        JumpContext.setupContext(ctx);
        JumpState.updateState(ctx);

        // Claims proper MOVE/LOOK/JUMP flags based on current state each tick
        JumpState.updateFlags(this, ctx);

        switch (ctx.state) {
            case GROUNDED:
                // Plan
                JumpTick.groundedTick(ctx);
                break;
            case PATHFINDING:
                // Execute
                JumpTick.pathfindTick(ctx);
                break;
            case AIRBORNE:
					// Move/Steer
					JumpTick.airborneTick(ctx);
                break;
        }
    }
}
