package net.offllneplayer.opminecraft.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonActivate_Method;

import java.util.EnumSet;

public class GOAL_BalloonJump extends Goal {

	// We run logic every tick
	private enum State { IDLE, PREPARE, JUMPING, AIRBORNE }

	private final Mob mob;
	private State state = State.IDLE;
	private int stateTicks = 0;

	private static final int JUMP_COOLDOWN = 26;
	private static final int PREPARE_TICKS_NORMAL = 2;
	private static final int PREPARE_TICKS_GAP = 1; // commit faster on edge
	private int lastJumpTick = -200;

	private final Analysis an = new Analysis();

	public GOAL_BalloonJump(Mob mob) {
		this.mob = mob;
		this.setFlags(EnumSet.noneOf(Flag.class));
	}

	private static class Analysis {
		boolean hasTarget;
		Vec3 mobPos = Vec3.ZERO;
		Vec3 targetPos = Vec3.ZERO;
		Vec3 horizDir = Vec3.ZERO;
		Direction faceDir = Direction.NORTH;

		double distance;
		double dy;

		boolean headroomOK;

		// Decisions (melee-only)
		boolean gapJump;
		boolean up1;
		boolean up2;

		// Runtime aids
		Vec3 landingHint = Vec3.ZERO;
		int prepareTicks = PREPARE_TICKS_NORMAL;
		boolean forceJumpNow = false; // edge trigger bypasses cooldown

		void reset() {
			gapJump = false;
			up1 = false;
			up2 = false;
			forceJumpNow = false;
			landingHint = Vec3.ZERO;
			prepareTicks = PREPARE_TICKS_NORMAL;
		}
	}

	/* ------------------------------- Scheduling ------------------------------- */

	@Override
	public boolean canUse() {
		return !mob.level().isClientSide() && holdsBalloon();
	}

	@Override
	public boolean canContinueToUse() {
		return holdsBalloon();
	}

	@Override
	public void start() {
		state = State.IDLE;
		stateTicks = 0;
	}

	@Override
	public void stop() {
		state = State.IDLE;
		stateTicks = 0;
		an.reset();
	}

	/* --------------------------------- Tick ---------------------------------- */

	@Override
	public void tick() {
		stateTicks++;

		LivingEntity target = mob.getTarget();
		updateAnalysis(target);

		switch (state) {
			case IDLE -> doIdle();
			case PREPARE -> doPrepare();
			case JUMPING -> doJumping();
			case AIRBORNE -> doAirborne();
		}
	}

	private void doIdle() {
		// If already airborne by any means, maintain balloon + steer
		if (!mob.onGround()) {
			ensureBalloonActive();
			state = State.AIRBORNE;
			stateTicks = 0;
			return;
		}

		if (!an.hasTarget) return;

		an.reset();
		if (!an.headroomOK) return;

		// Only melee logic
		meleeDecision();

		// Cooldown check, but allow bypass when on the ledge of a gap
		boolean cooldownReady = (mob.tickCount - lastJumpTick) >= JUMP_COOLDOWN;
		if (!(an.gapJump || an.up2 || an.up1)) return;
		if (!cooldownReady && !an.forceJumpNow) return;

		// Critical for gaps: stop pathing now and settle heading; also shorter PREPARE for gaps
		if (an.gapJump) {
			mob.getNavigation().stop();
			an.prepareTicks = PREPARE_TICKS_GAP;
		}
		alignLook(an.horizDir);

		state = State.PREPARE;
		stateTicks = 0;
	}

	private void doPrepare() {
		// Keep looking where we intend to go
		alignLook(an.horizDir);

		// If preparing a gap jump, freeze horizontal motion so we don't shuffle off the edge
		if (an.gapJump && mob.onGround()) {
			Vec3 cur = mob.getDeltaMovement();
			mob.setDeltaMovement(0.0, Math.max(0.0, cur.y * 0.5), 0.0);
		}

		if (stateTicks >= an.prepareTicks) {
			fireJumpImpulse();
			lastJumpTick = mob.tickCount;
			state = State.JUMPING;
			stateTicks = 0;
		}
	}

	private void doJumping() {
		ensureBalloonActive();

		// Keep navigation pointed to landing/target so the chase continues
		if (!an.landingHint.equals(Vec3.ZERO)) {
			mob.getNavigation().moveTo(an.landingHint.x, an.landingHint.y, an.landingHint.z, 1.22);
		} else if (an.hasTarget) {
			mob.getNavigation().moveTo(an.targetPos.x, an.targetPos.y, an.targetPos.z, 1.22);
		}

		if (!mob.onGround()) {
			state = State.AIRBORNE;
			stateTicks = 0;
		}
	}

	private void doAirborne() {
		ensureBalloonActive();

		// Forward bias mid-air; stronger while crossing gaps
		double nudgeMag = an.gapJump ? 0.05 : 0.02;
		if (an.hasTarget) {
			Vec3 cur = mob.getDeltaMovement();
			Vec3 bias = an.horizDir.scale(nudgeMag);
			mob.setDeltaMovement(cur.x + bias.x, cur.y, cur.z + bias.z);
		}

		// Keep pursuing
		if (!an.landingHint.equals(Vec3.ZERO)) {
			mob.getNavigation().moveTo(an.landingHint.x, an.landingHint.y, an.landingHint.z, 1.22);
		} else if (an.hasTarget) {
			mob.getNavigation().moveTo(an.targetPos.x, an.targetPos.y, an.targetPos.z, 1.22);
		}

		// Reset when landed
		if (mob.onGround()) {
			state = State.IDLE;
			stateTicks = 0;
			an.reset();
		}
	}

	/* ----------------------------- Melee decisions ----------------------------- */

	private void meleeDecision() {
		BlockPos bp = mob.blockPosition();

		// Check gap first
		boolean gap = isOneBlockGapAhead(mob.level(), bp, an.faceDir);
		if (gap) {
			an.gapJump = true;

			// Edge trigger: if we're close to the ledge, force jump now (bypass cooldown)
			double edgeDist = distanceToFrontEdge(bp, an.faceDir);
			if (edgeDist <= 0.40) {
				an.forceJumpNow = true;
			}

			// Pick landing top 2 blocks forward (scan up/down for the real surface)
			an.landingHint = findLandingTopCenter(mob.level(), bp.relative(an.faceDir, 2), 2, 2);
			return;
		}

		// Ledge mantling
		boolean up2 = hasUpStepAhead(mob.level(), bp, an.faceDir, 2);
		boolean up1 = hasUpStepAhead(mob.level(), bp, an.faceDir, 1);

		if ((an.dy >= 1.5 || an.distance < 4.25) && up2) {
			an.up2 = true;
			an.landingHint = Vec3.atCenterOf(bp.relative(an.faceDir).above(2));
			return;
		}

		if ((an.dy >= 0.5 || an.distance < 4.0) && up1) {
			an.up1 = true;
			an.landingHint = Vec3.atCenterOf(bp.relative(an.faceDir).above(1));
		}
	}

	/* ------------------------------ Jump impulse ------------------------------ */

	private void fireJumpImpulse() {
		// Forward/up tuned to reliably clear gaps and mantle 1–2 blocks
		double baseForward = 0.45;
		double baseUp = 0.42;
		double bonusUp = 0.0;
		double forwardScale = 1.0;

		if (an.gapJump) {
			forwardScale = 1.45; // aggressive push to cross the gap
			bonusUp = 0.20;      // a bit more loft helps clear the lip consistently
		} else if (an.up2) {
			forwardScale = 0.95;
			bonusUp = 0.38;
		} else if (an.up1) {
			forwardScale = 1.00;
			bonusUp = 0.18;
		} else {
			bonusUp = 0.08;
		}

		double speedScale = 1.0 + clamp01(horizontalSpeed() * 1.5);
		Vec3 forward = an.horizDir.scale(baseForward * forwardScale * speedScale);

		// Trigger jump and then override motion
		mob.getJumpControl().jump();
		Vec3 cur = mob.getDeltaMovement();
		Vec3 next = new Vec3(
				cur.x * 0.25 + forward.x,
				Math.max(cur.y, baseUp + bonusUp),
				cur.z * 0.25 + forward.z
		);
		mob.setDeltaMovement(next);
		alignLook(an.horizDir);

		// Keep chase going
		if (!an.landingHint.equals(Vec3.ZERO)) {
			mob.getNavigation().moveTo(an.landingHint.x, an.landingHint.y, an.landingHint.z, 1.22);
		} else if (an.hasTarget) {
			mob.getNavigation().moveTo(an.targetPos.x, an.targetPos.y, an.targetPos.z, 1.22);
		}
	}

	/* ------------------------------ Analysis/terrain ------------------------------ */

	private void updateAnalysis(LivingEntity target) {
		an.mobPos = mob.position();
		an.hasTarget = target != null && target.isAlive();

		if (!an.hasTarget) {
			an.horizDir = Vec3.ZERO;
			an.distance = 0;
			an.dy = 0;
			an.headroomOK = true;
			return;
		}

		an.targetPos = target.position();
		Vec3 to = an.targetPos.subtract(an.mobPos);
		Vec3 horiz = new Vec3(to.x, 0, to.z);
		an.horizDir = horiz.lengthSqr() > 1.0e-4 ? horiz.normalize() : Vec3.ZERO;
		an.faceDir = Direction.getNearest(an.horizDir.x, 0, an.horizDir.z);
		an.distance = an.mobPos.distanceTo(an.targetPos);
		an.dy = an.targetPos.y - an.mobPos.y;
		an.headroomOK = hasVerticalClearance(mob.level(), mob.blockPosition(), 3);
	}

	private boolean hasVerticalClearance(Level level, BlockPos pos, int height) {
		for (int i = 1; i <= height; i++) {
			BlockPos p = pos.above(i);
			if (!level.getBlockState(p).getCollisionShape(level, p).isEmpty()) return false;
		}
		return true;
	}

	// Gap: front and front-below are air; landing 2 forward has a solid surface (we find top),
	// and there's headroom at landing.
	private boolean isOneBlockGapAhead(Level level, BlockPos mobPos, Direction dir) {
		BlockPos front = mobPos.relative(dir);
		BlockPos frontBelow = front.below();

		boolean airFront = isAir(level, front) && isAir(level, frontBelow);
		if (!airFront) return false;

		BlockPos landingCol = mobPos.relative(dir, 2);
		BlockPos landingTop = findLandingTop(level, landingCol, 2, 2);
		if (landingTop == null) return false;

		boolean landingHeadroom = isAir(level, landingTop.above()) && isAir(level, landingTop.above(2));
		return landingHeadroom;
	}

	// Up-step: can we stand h blocks higher one forward, with headroom?
	private boolean hasUpStepAhead(Level level, BlockPos mobPos, Direction dir, int h) {
		h = Mth.clamp(h, 1, 2);
		BlockPos stand = mobPos.relative(dir).above(h);
		if (!isSolid(level, stand.below())) return false;
		return isAir(level, stand) && isAir(level, stand.above());
	}

	// Distance from mob position to the front edge of the current block along faceDir.
	private double distanceToFrontEdge(BlockPos mobPos, Direction dir) {
		double px = mob.getX();
		double pz = mob.getZ();
		return switch (dir) {
			case EAST -> (mobPos.getX() + 1.0) - px;
			case WEST -> px - mobPos.getX();
			case SOUTH -> (mobPos.getZ() + 1.0) - pz;
			case NORTH -> pz - mobPos.getZ();
			default -> 1.0;
		};
	}

	private BlockPos findLandingTop(Level level, BlockPos col, int searchDown, int searchUp) {
		BlockPos p = col;
		for (int i = 0; i < searchDown; i++) {
			if (isSolid(level, p)) return p;
			p = p.below();
		}
		p = col.above();
		for (int i = 0; i < searchUp; i++) {
			if (isSolid(level, p)) return p;
			p = p.above();
		}
		return null;
	}

	private Vec3 findLandingTopCenter(Level level, BlockPos col, int searchDown, int searchUp) {
		BlockPos top = findLandingTop(level, col, searchDown, searchUp);
		return top == null ? Vec3.atCenterOf(col) : Vec3.atCenterOf(top.above());
	}

	private boolean isAir(Level level, BlockPos pos) {
		BlockState s = level.getBlockState(pos);
		return s.getCollisionShape(level, pos).isEmpty();
	}

	private boolean isSolid(Level level, BlockPos pos) {
		BlockState s = level.getBlockState(pos);
		return !s.getCollisionShape(level, pos).isEmpty();
	}

	/* ------------------------------ Utilities ------------------------------ */

	private boolean holdsBalloon() {
		return (mob.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem) ||
				(mob.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem);
	}

	private void ensureBalloonActive() {
		BalloonActivate_Method.execute(mob);
	}

	private void alignLook(Vec3 dir) {
		if (dir.lengthSqr() < 1.0e-6) return;
		float yaw = (float) (Mth.atan2(dir.z, dir.x) * (180F / Math.PI)) - 90.0F;
		mob.setYRot(yaw);
		mob.setYHeadRot(yaw);
		mob.setYBodyRot(yaw);
	}

	private double horizontalSpeed() {
		Vec3 v = mob.getDeltaMovement();
		return Math.sqrt(v.x * v.x + v.z * v.z);
	}

	private static double clamp01(double v) {
		return v < 0 ? 0 : (v > 1 ? 1 : v);
	}
}
