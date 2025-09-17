package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;
import net.offllneplayer.opminecraft.items._iwe.pistol.PistolItem;

/**
 * Per-mob state container for the jump goal.
 * all mutable state lives here and is updated by planner/scanner methods.
 */
public final class JumpContext {

	public final Mob mob;

	// Jump cooldown, gates planning/pathfinding
	public static final int JUMP_COOLDOWN = 20;

 public enum State { GROUNDED, AIRBORNE }

   public static final double RANGED_MAX_HOLD_CHECK_DIST = 256.0D; // 16 blocks

   // Capability flags
   public boolean balloonJump = false; // has balloon check
   public boolean hasRangedWep = false; // has melee/ranged check
   public boolean rangedHoldActive = false; // computed hold gate for this tick
 
   // Limits / heuristics
   public int desiredYGain = 0; // the difference in height the mob using jump goal wants to be at for jumping to be satisfied and pass to vanilla ai
   public int maxUp = 1; // max planned y gain for jumping; sets the jump height
   public int maxGap = 2; // max planned gap for jumping; sets the jump distance
   public int maxDown = 6; // max planned y loss for jumping; defines dropping selections

   // Collision
   public int COLLISION_HEIGHT = 2; //required free vertical blocks above standing block

   // Positions
   public Vec3 landingPos = Vec3.ZERO;
   public Vec3 jumpFromPos = Vec3.ZERO;
   public Vec3 airCarryXZ = Vec3.ZERO;
   public Vec3 targetExactPos = Vec3.ZERO;
 
   // Tracking: previous tick's horizontal distance to landing (for airborne regression detection)
   public double lastLandingDistSqr = Double.NaN;

   // Facing
   public Direction faceDir = Direction.NORTH;

   // State / timers
   public State state = State.GROUNDED;
   public int lastJumpTick = -200; // last time a jump was fired or we enforced jump cooldown
   public boolean wasOnGround = true;

	// Prevalidated takeoff for the current candidate (used to avoid recomputation between prevalidate and finalize)
	public Vec3 prevalidatedTakeoff = Vec3.ZERO;

	// Store last two jump-from positions (failed or successful) to avoid reusing next cycle
	public Vec3 failedJumpFromPos = Vec3.ZERO;
	public Vec3 failedJumpFromPosOld = Vec3.ZERO;

	/**
	 * Record a jump-from position that should be avoided on the next planning cycle.
	 * If there is already a non-zero value, bump it to the Old slot, then store the new one.
	 */
	public void recordAvoidJumpFrom(Vec3 from) {
		if (from == null || from.equals(Vec3.ZERO)) return;
		if (this.failedJumpFromPos != null && !this.failedJumpFromPos.equals(Vec3.ZERO)) {
			this.failedJumpFromPosOld = this.failedJumpFromPos;
		}
		this.failedJumpFromPos = from;
	}

	public JumpContext(Mob mob) {
		this.mob = mob;
		this.lastJumpTick = mob.tickCount;
	}

	public boolean hasPlan() {
		return !(this.landingPos.equals(Vec3.ZERO));
	}

	// ---- Capabilities ----
	public static void setupContext(JumpContext ctx) {

		int mobY = ctx.mob.blockPosition().getY();

		ctx.jumpFromPos = ctx.mob.position();
		ctx.targetExactPos = ctx.mob.getTarget().position();

		boolean hasBalloon = isBalloonAttacker(ctx.mob);
		boolean hasRanged = isRangedAttacker(ctx.mob);

		ctx.balloonJump = hasBalloon;
		ctx.maxUp = hasBalloon ? 3 : 1;
		ctx.maxGap = hasBalloon ? 5 : 2;
		ctx.maxDown = hasBalloon ? 14 : 6;
		ctx.COLLISION_HEIGHT = hasBalloon ? 3 : 2;

		// Wither Skeleton: taller collision height
		if (ctx.mob instanceof WitherSkeleton) {
			ctx.COLLISION_HEIGHT = hasBalloon ? 4 : 3;
		}

		ctx.hasRangedWep = hasRanged;

		// Compute fresh facing and hold gate for this tick
		Vec3 toward = JumpUtils.exactToward(ctx.mob, ctx.landingPos, ctx.targetExactPos);
		ctx.faceDir = JumpUtils.cardinalFrom(toward, ctx.faceDir);
		ctx.rangedHoldActive = rangedHoldGate(ctx, ctx.faceDir);

		// Determine desiredYGain based on simple integer Y of the target (match mob.blockPosition().getY())
		int targetY = BlockPos.containing(ctx.targetExactPos.x, ctx.targetExactPos.y, ctx.targetExactPos.z).getY();
		ctx.desiredYGain = hasRanged ? ((mobY >= targetY + 4) ? 0 : 4) : (mobY < targetY ? 1 : 0);
	}

	// Capability checks
	public static boolean isBalloonAttacker(Mob mob) {
		var main = mob.getMainHandItem().getItem();
		var off = mob.getOffhandItem().getItem();
		return (main instanceof BalloonItem)
				|| (off instanceof BalloonItem);
	}

	// Ranged utilities: determine if this mob type can actually use the ranged item it holds.
	// Only main-hand items count for ranged capability (vanilla AI does not use off-hand weapons for ranged attacks).
	public static boolean isRangedAttacker(Mob mob) {

		// Skeleton family: uses bows; allow pistols from this mod
		if (mob instanceof Skeleton
				|| mob instanceof WitherSkeleton
				|| mob instanceof Stray
				|| mob instanceof Bogged) {
			return (mob.getMainHandItem().getItem() instanceof BowItem) || (mob.getOffhandItem().getItem() instanceof BowItem) || (mob.getMainHandItem().getItem() instanceof PistolItem);
		}
		// Pillager and Piglin: uses crossbows; also allow pistols
		if (mob instanceof Pillager || mob instanceof Piglin) {
			return (mob.getMainHandItem().getItem() instanceof CrossbowItem) || (mob.getMainHandItem().getItem() instanceof PistolItem);
		}
		// Drowned: can throw tridents (treat as ranged); optionally allow pistol if equipped via your systems
		if (mob instanceof Drowned) {
			return (mob.getMainHandItem().getItem() instanceof TridentItem);
		}
		// Else not a valid ranged user
		return false;
	}

	public static boolean rangedHoldGate(JumpContext ctx, Direction grid) {
		if (!ctx.hasRangedWep) return false;

		LivingEntity target = ctx.mob.getTarget();
		if (target == null || !target.isAlive() || !ctx.mob.hasLineOfSight(target)) return false;

		int mobY = ctx.mob.blockPosition().getY();
		int targetY = target.blockPosition().getY();
		// Hold band: always hold when at or above targetY and up to +4 above (independent of desiredYGain)
		if (!(mobY >= targetY && mobY <= targetY + 4)) return false;

		// Ranged distance band (applies to both balloon and non-balloon): hold position up to 12 blocks away from target
		double dist2 = JumpUtils.horizontalDistSqr(ctx.mob.position(), target.position());
		if (dist2 < JumpContext.RANGED_MAX_HOLD_CHECK_DIST) return true;

		return JumpUtils.hasForwardOrDiagonalObstacle(ctx.mob.level(), ctx.mob.blockPosition(), grid, Math.min(ctx.maxGap + 1, (int) Math.ceil(Math.sqrt(dist2))));
	}
}
