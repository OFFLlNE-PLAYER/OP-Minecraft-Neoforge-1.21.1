package net.offllneplayer.opminecraft.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.TridentItem;

import java.util.EnumSet;

/*** AI improving goal for any mob that can jump.
 * Improves motion and forces jumping over obstacles to reach a target it normally would not reach with vanilla AI.
 *
 * Baseline behavior:
 *  *  - Uses a cooldown for "lazy" jumping checks every 2 seconds. DO NOT BYPASS FOR EXECUTING A JUMP. (exits early if the cooldown is not yet ready)
 *  *  - Exits early if the mob does not have a target.
 *  *  - Sets up jumping capability for the mob depending on whether it is holding a Balloon in either hand.
 *  *  - Analyzes the current position and target entity position.
 *  *  - Plans a target location closer to the target based on current jumping capability and positions. (prefer vertical jumps over horizontal gaps if y value isn't the same)
 *  *  - When below target, plans multi-step paths using Navigation to reach jump positions before executing jumps
	 *  *  - Executes jumps by calling fireJump(), which computes a constant horizontal carry toward the target and then triggers a vanilla jump.
	 *  *  - While airborne, enforces consistent horizontal motion (XZ) toward the planned landing/target; vertical motion remains vanilla.
 *  *  - Dynamically claims MOVE only while airborne OR pathfinding to jump positions to avoid blocking other MOVE goals (e.g., attacks) while grounded.
 *  *  - Gets/uses current facing for calculations.
 *  *  - Forces entity's movement speed while airborne.
 *-~--~--~--~--~--~--~--~--~--~--~--~--~--~--~--~--~--~--~--~-
 * ~ base (no balloon):
 *  - Sets fields for jumping to 1-block vertical (landing check fwd=1, up=1) and up to 2-block horizontal gap clearance (landing check at 3rd block, up 0-1), including diagonal blocks.
 *
 * ~ Enhanced behavior (while holding a Balloon in either hand):
 *  - Modifies fields for new vertical and horizontal jumping capabilities.
 *  - Allows up to 3-block vertical jumps (fwd=1, up=3) and up to 4-block horizontal gap clearance (landing check at 5th block, up 0-1), including diagonal blocks.
 *  - Enables downward pursuit - force jump off ledges toward a lower target without checking distance. (the mob should jump off its current ledge toward the lower target)
 *  - Enhanced pathfinding - can search wider areas and attempt more ambitious jump sequences when balloon-enabled
 */
public class GOAL_useJump extends Goal {
 
 	private final Mob mob;
 
 	// Tunable thresholds (use consistently across the class)
 	private static final double TAKEOFF_REACH = 0.40D;            // unified distance to consider reached takeoff / at jump position
 	private static final double TAKEOFF_REACH_SQR = TAKEOFF_REACH * TAKEOFF_REACH;
 	private static final double AIRBORNE_EASE_RADIUS = 0.50D;     // distance to start easing horizontal motion while airborne
 	private static final double AIRBORNE_MAX_SPEED = 0.80D;       // clamp max air speed (horiz magnitude)

	private static final int JUMP_COOLDOWN_TICKS = 40; // Cooldown for "lazy" planning
	private int lastJumpTick = -200;
	
	private int maxUp  = 1; // baseline: 1-block vertical
	private int maxGap = 2; // baseline: 2-block horizontal gap clearance
	private int maxDown = 4; // baseline: search depth downward

	private boolean balloonJump = false;

	// [RUN-TIME CALC STATE]
	private Vec3 landingPos = Vec3.ZERO;      // Planned landing position (center of air cell above standable top) or ZERO for none
	private Vec3 jumpFromPos = Vec3.ZERO;     // World-space center we want to reach before jumping (PATHFINDING TARGET)
	private Vec3 airCarryXZ = Vec3.ZERO;      // Stored horizontal carry enforced while AIRBORNE
	private Vec3 targetExactPos = Vec3.ZERO;  // Current tick target position snapshot
	private Vec3 committedLanding = Vec3.ZERO; // Landing latched at takeoff; used for in-air steering
	private Direction faceDir = Direction.NORTH; // Cardinal facing used by calcPos plans
	private boolean wasOnGround = true; // track landing events while PATHFINDING

	/* ------------------------------- [STATE / FLAGS] ------------------------------- */
	private enum JumpState {GROUNDED, PATHFINDING, AIRBORNE}
	private JumpState state = JumpState.GROUNDED;

 public GOAL_useJump(Mob mob) {
		this.mob = mob;
		// Initialize cooldown so the goal does not jump immediately on spawn
		this.lastJumpTick = mob.tickCount; // first plan only after initial cooldown window
		this.setFlags(EnumSet.of(Flag.JUMP));
		// Initialize internal state
		this.state = JumpState.GROUNDED;
	}

	/* ------------------------------- [GOAL OVERRIDES] ------------------------------- */

	@Override
	public boolean canUse() {
		if (mob.level().isClientSide()) return false;

		// Check if mob can actually jump
		if (mob.getJumpControl() == null) return false;

		LivingEntity targetEntity = mob.getTarget();
		return targetEntity != null && targetEntity.isAlive();
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity targetEntity = mob.getTarget();
		return targetEntity != null && targetEntity.isAlive();
	}

	@Override
	public void start() {
		landingPos = Vec3.ZERO;
		jumpFromPos = Vec3.ZERO;
		airCarryXZ = Vec3.ZERO;
		committedLanding = Vec3.ZERO;
		state = JumpState.GROUNDED;
		// While grounded we do not claim MOVE so other goals can act
		this.setFlags(EnumSet.of(Flag.JUMP));
		// Defer planning on goal start to avoid immediate jump on spawn
		lastJumpTick = mob.tickCount;
		// Initialize ground tracker
		wasOnGround = mob.onGround();
	}

	@Override
	public void stop() {
		landingPos = Vec3.ZERO;
		jumpFromPos = Vec3.ZERO;
		airCarryXZ = Vec3.ZERO;
		committedLanding = Vec3.ZERO;
		state = JumpState.GROUNDED; // reset to grounded state on stop
		// Relinquish MOVE when stopping
		this.setFlags(EnumSet.of(Flag.JUMP));
		// Reset cooldown baseline on stop to avoid immediate re-trigger if goal restarts same tick
		lastJumpTick = mob.tickCount;
		// Update ground tracker
		wasOnGround = mob.onGround();
	}

	/* -------------------------- Tick -------------------------- */

	@Override
	public void tick() {
		LivingEntity targetEntity = mob.getTarget();
		if (targetEntity == null || !targetEntity.isAlive()) return;

		targetExactPos = targetEntity.position();

		setupBalloon(); // re-assign fields for balloon logic

		// Update state from physical condition once per tick
		updateState();

		// Dynamically adjust flags based on state
		updateFlags();

		boolean cooldownReady = (mob.tickCount - lastJumpTick) >= JUMP_COOLDOWN_TICKS;

		switch (state) {
			case GROUNDED:
				if (cooldownReady) {
					// Plan and execute a jump or start pathfinding to a jump position
					planNextAction();
				}
				break;

			case PATHFINDING:
				// Check if we've reached the jump position or navigation failed
 			if (mob.getNavigation().isDone() || mob.getNavigation().isStuck()) {
 				if (isAtJumpPosition()) {
 					// Linger at the edge; jump lazily when cooldown is ready
 					if (cooldownReady) {
 						fireJump();
 					} else {
 						// Hold position; do not reset cooldown or re-path
 						mob.getLookControl().setLookAt(targetExactPos.x, targetExactPos.y, targetExactPos.z);
 					}
 				} else {
 					// Navigation failed, reset and try again
 					jumpFromPos = Vec3.ZERO;
 					state = JumpState.GROUNDED;
 					lastJumpTick = mob.tickCount; // Apply cooldown backoff
 				}
 			}
				break;

			case AIRBORNE:
				moveTo();
				break;
		}
	}

	/* ------------------------------- [State Management] ------------------------------- */

	private void updateState() {
		boolean onGroundNow = mob.onGround();
		boolean landed = onGroundNow && !wasOnGround;   // transition: off-ground -> on-ground
		boolean tookOff = !onGroundNow && wasOnGround;  // transition: on-ground -> off-ground

		if (landed) {
			// Resolve what we were doing just before landing
			switch (state) {
				case AIRBORNE:
					// Landed from a committed jump: reset to grounded, clear steer/carry
					resetToGrounded(false, false);
					break;
				case PATHFINDING:
					// We were pathing but ended up falling/landing; if not at planned jump pos, back off and reset
					if (!isAtJumpPosition()) {
						resetToGrounded(true, true);
					}
					break;
				default:
					// Already grounded: no change
					break;
			}
		} else if (tookOff) {
			// We just left the ground: mark airborne
			state = JumpState.AIRBORNE;
		}

		// update last known ground state after evaluating transitions
		wasOnGround = onGroundNow;
	}

	// reset helper: sets GROUNDED, clears transient fields; optional cooldown and nav stop
	private void resetToGrounded(boolean applyCooldown, boolean stopNavigation) {
		if (stopNavigation) {
			mob.getNavigation().stop();
		}
		state = JumpState.GROUNDED;
		landingPos = Vec3.ZERO;
		jumpFromPos = Vec3.ZERO;
		airCarryXZ = Vec3.ZERO;
		committedLanding = Vec3.ZERO;
		if (applyCooldown) {
			lastJumpTick = mob.tickCount; // backoff before replanning
		}
	}

	private void updateFlags() {
		if (state == JumpState.GROUNDED) {
			this.setFlags(EnumSet.of(Flag.JUMP));
		} else {
			// PATHFINDING or AIRBORNE - claim MOVE
			this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
		}
	}

	/* ------------------------------- [Multi-step Planning] ------------------------------- */

	/**
	 * Plans the next action - either direct jump, or pathfinding to elevation for multi-step jumping
	 */
	private void planNextAction() {
		Level level = mob.level();
		BlockPos mobPos = mob.blockPosition();
		int targetY = (int) Math.floor(targetExactPos.y);

		// 1) Ranged gating
		if (rangedHoldGate()) return;

		// 2) Compute candidate landing for the current tile (calcPos sets faceDir internally)
		calcPos();

		// 3) If we already have a plan (landingPos set), path to takeoff or jump now
		if (handleImmediatePlan(level, mobPos)) return;

		// 4) Otherwise search a small neighborhood for a better jump-from tile and pick the best
		JumpSelection selection = searchBetterJumpFrom(level, mobPos, targetY);
		if (selection != null && selection.jumpFrom != null) {
			pathToJumpFrom(selection.jumpFrom, selection.landing);
			return;
		}

		// 5) Give up this cycle, back off via cooldown
		lastJumpTick = mob.tickCount;
	}

	// ---------- (planNextAction helpers) ----------
	/* ------------------------------ [Ranged specific setup] ------------------------------ */
	private boolean rangedHoldGate() {
		if (shouldHoldRangedPosition()) {
			lastJumpTick = mob.tickCount; // back off planning to let ranged attack goals act
			return true;
		}
		return false;
	}

	private boolean shouldHoldRangedPosition() {
		if (!isRangedAttacker()) return false;
		LivingEntity target = mob.getTarget();
		if (target == null || !target.isAlive()) return false;
		if (!mob.hasLineOfSight(target)) return false;
		int mobY = mob.blockPosition().getY();
		int targetY = target.blockPosition().getY();
		if (!(mobY >= targetY && mobY <= targetY + 4)) return false;
		// Hold only when within a good ranged band to avoid drifting to melee
		Vec3 m = mob.position();
		Vec3 t = target.position();
		double dx = m.x - t.x, dz = m.z - t.z;
		double dist2 = dx*dx + dz*dz;
		double min = 6.0D, max = 18.0D;
		return dist2 >= (min*min) && dist2 <= (max*max);
	}


	/* ------------------------------ [Calculate landingPos] ------------------------------ */

	/* Planner method
	 * PRIORITY ORDER: upward elevation (positive y), horizontal gaps (x and z axis), balloon drop (negative y), random hop (1/100), fallback (zero).
	 * Calculates landing position for a jump toward targetExactPos.
	 */
	private void calcPos() {
		Level level = mob.level();
		BlockPos mobPos = mob.blockPosition();
		int mobY = mobPos.getY();
		int targetY = (int)Math.floor(targetExactPos.y);

		// Reset landing position
		landingPos = Vec3.ZERO;

		// Get facing direction toward target
		Vec3 toward = getFacingTowardTarget();

		// Priority 1: Upward elevation steps
		boolean ranged = isRangedAttacker();
		if (ranged) {
			int desiredYLocal = targetY + 4; // try to gain up to +4 above target
			if (mobY < desiredYLocal) {
				Vec3 elevationJump = planElevation(level, mobPos, desiredYLocal);
				if (elevationJump != null) {
					landingPos = elevationJump;
					return;
				}
			}
		} else if (targetY > mobY) {
			Vec3 elevationJump = planElevation(level, mobPos, targetY);
			if (elevationJump != null) {
				landingPos = elevationJump;
				return;
			}
		}

		// Priority 2: Horizontal traverse (straight)
		Vec3 straightGap = planStraightGap(level, mobPos, mobY);
		if (straightGap != null) {
			landingPos = straightGap;
			return;
		}

		// Priority 3: Diagonal gap crossing
		Vec3 diagonalGap = planDiagonalGap(level, mobPos, mobY, toward);
		if (diagonalGap != null) {
			landingPos = diagonalGap;
			return;
		}

		// Priority 4: Balloon-enabled downward pursuit
		Vec3 balloonDrop = planBalloonDrop(targetY, mobY);
		if (balloonDrop != null) {
			landingPos = balloonDrop;
			return;
		}
	}

	/* --------------------------- [calcPos getters] ---------------------------- */

	/*
	 * - compute cardinal facing toward the target
	 * - Builds a horizontal vector from mob to target; if degenerate, falls back to mob.getDirection().
	 * - Chooses faceDir by dominant axis (E/W vs N/S) and returns the horizontal unit vector used for biasing.
	 */
	private Vec3 getFacingTowardTarget() {
		Vec3 mobPos = mob.position();
		Vec3 toTarget = new Vec3(targetExactPos.x - mobPos.x, 0, targetExactPos.z - mobPos.z);

		if (toTarget.lengthSqr() < 0.01) {
			// Degenerate case - use mob's current facing
			faceDir = mob.getDirection();
		} else {
			// Choose dominant cardinal direction
			if (Math.abs(toTarget.x) > Math.abs(toTarget.z)) {
				faceDir = toTarget.x > 0 ? Direction.EAST : Direction.WEST;
			} else {
				faceDir = toTarget.z > 0 ? Direction.SOUTH : Direction.NORTH;
			}
		}

		return Vec3.atLowerCornerOf(faceDir.getNormal());
	}


	/* --------------------------- [Calcpos plans] --------------------------- */

	// Prefer small up/level steps toward desiredY; forward 1–2 (baseline/balloon) and lateral 0/±1/±2
	/*
	 * Plan: elevation step toward desiredY
	 * - Searches a neighborhood in cardinal facing with conservative forward ranges.
	 * - Only considers level or upward steps (no down in this phase).
	 * - Enforces capability (up to maxUp), headroom (2 blocks), and no over-climb of desiredY.
	 * - Returns the center of the standable air cell above the landing top; null if none.
	 */
	private Vec3 planElevation(Level level, BlockPos mobPos, int desiredYLocal) {
		Vec3 bestLanding = null;
		double bestScore = Double.MAX_VALUE;

		// Conservative forward ranges for elevation
		int maxFwd = 2; // Same for both balloon and non-balloon for elevation planning

		for (int fwd = 1; fwd <= maxFwd; fwd++) {
			for (int lat = -2; lat <= 2; lat++) {
				BlockPos candidate = mobPos.relative(faceDir, fwd).relative(faceDir.getClockWise(), lat);
				BlockPos landingTop = getLandingTop(level, candidate, maxUp, false); // No deep drops for elevation

				if (landingTop != null) {
					int standY = landingTop.getY() + 1;

					// Only level or upward steps
					if (standY <= mobPos.getY()) continue;

					// Don't over-climb the target
					if (standY > desiredYLocal + 1) continue;

					// Score by elevation gain and proximity to target
					double elevationGain = standY - mobPos.getY();
					double targetDist = candidate.distSqr(BlockPos.containing(targetExactPos));
					double score = targetDist - (elevationGain * elevationGain * 4.0);

					if (score < bestScore) {
						bestScore = score;
						bestLanding = surfaceCenter(landingTop);
					}
				}
			}
		}

		return bestLanding;
	}

	/*
	 * Plan: straight horizontal gap crossing
	 * - Detects gaps by ensuring there's a dropoff ahead and then looking for landing spots
	 * - Checks that the gap area has proper clearance for jumping
	 * - Finds landing at landingCheckDistance (2nd or 3rd block baseline, up to 5th block balloon).
	 * - Vertical allowance:
	 *   - Without balloon: landing standY may be 0, -1, or -2 relative to current.
	 *   - With balloon: landing standY may be up to +1 above current, or any amount lower.
	 * - Returns center of the air block above the landing top; null if no candidate.
	 */
	private Vec3 planStraightGap(Level level, BlockPos mobPos, int mobY) {
		// First, ensure there's actually a gap (no solid ground immediately ahead)
		boolean hasGap = false;
		for (int fwd = 1; fwd < maxGap+1; fwd++) {
			BlockPos checkGround = mobPos.relative(faceDir, fwd);
			// Any air or fluid ahead counts as a gap edge (distance down irrelevant)
			if (isGapOpen(level, checkGround)) {
				hasGap = true;
				break;
			}
		}

		if (!hasGap) return null; // No gap detected

		// Distance limits - check landings at specific distances (include 2 for 1-block gaps)
		int[] checkDistances = balloonJump ?
				new int[]{2, 3, 4, 5} : // Balloon: allow 2..5 within capability
				new int[]{2,3}; // Regular: allow 1-2 block gaps (2-3)

		for (int checkDist : checkDistances) {
			if (checkDist > maxGap+1) continue; // Stay within capability

			// Ensure air column clearance for this candidate distance
			boolean pathClear = true;
			for (int fwd = 1; fwd < checkDist; fwd++) {
				BlockPos checkCol = mobPos.relative(faceDir, fwd);
				// Gap clearance rule (11): non-balloon requires y=0 clear; balloon requires y=0 and y+1 clear
				if (balloonJump) {
					if (!isGapOpen(level, checkCol) || !isGapOpen(level, checkCol.above())) {
						pathClear = false;
						break;
					}
				} else {
					if (!isGapOpen(level, checkCol)) {
						pathClear = false;
						break;
					}
				}
			}
			if (!pathClear) continue;

			// Find landing spot at this distance
			BlockPos candidate = mobPos.relative(faceDir, checkDist);
			BlockPos landingTop = getLandingTop(level, candidate, maxUp, balloonJump);
			if (landingTop == null) continue;
			int landingY = landingTop.getY() + 1;

			// If the target is not lower (same or higher Y), avoid stepping down: require level or upward landing
			int targetYLocal = (int) Math.floor(targetExactPos.y);
			boolean targetNotLower = targetYLocal >= mobY;
			if (targetNotLower && landingY < mobY) continue;

			// Apply vertical allowance rules
			if (balloonJump) {
				// Balloon: ≤ +1 up, any drop (except blocked above when target not lower)
				if (landingY > mobY + 1) continue;
			} else {
				// No balloon: +1, 0, -1, -2 relative to current (except blocked above when target not lower)
				int verticalDiff = landingY - mobY;
				if (verticalDiff < -2 || verticalDiff > 1) continue;
			}

			// Headroom is enforced inside getLandingTop (3 blocks)
			return surfaceCenter(landingTop);
		}

		return null;
	}

	/*
	 * Plan: diagonal gap crossing with lateral bias toward the target side
	 * - Computes left/right bias from the horizontal vector toward the target to decide which diagonal to try first.
	 * - Distance limits:
	 *   - Regular (no balloon): only 1-block diagonal gaps (landing check at 2nd block).
	 *   - Balloon: up to 3-block diagonal gaps (landing check up to 5th block), capped by maxGap.
	 * - For each candidate (lat ±1), ensure every intermediate forward and diagonal column is an air column.
	 * - Verifies landing has proper headroom and applies correct vertical rules.
	 * - Picks the landing closest in XZ distance to the target; returns its center; null if none.
	 */
	private Vec3 planDiagonalGap(Level level, BlockPos mobPos, int mobY, Vec3 toward) {
		// Compute lateral bias toward target
		Vec3 rightDir = Vec3.atLowerCornerOf(faceDir.getClockWise().getNormal());
		double lateralBias = toward.dot(rightDir);
		int[] laterals = lateralBias > 0 ? new int[]{1, -1} : new int[]{-1, 1};

		Vec3 bestLanding = null;
		double bestDistToTarget = Double.MAX_VALUE;

		// Distance limits - check landings at specific distances
		int[] checkDistances = balloonJump ?
				new int[]{2, 3, 4, 5} : // Balloon: allow 2..5 within capability
				new int[]{2}; // Regular: allow 1-block diagonal (2)

		for (int lat : laterals) {
			for (int checkDist : checkDistances) {
				if (checkDist > maxGap+1) continue; // Stay within capability

				BlockPos candidate = mobPos.relative(faceDir, checkDist).relative(faceDir.getClockWise(), lat);

				// First check if there's actually a gap for diagonal jumping
				boolean hasGap = false;
				for (int checkFwd = 1; checkFwd < checkDist; checkFwd++) {
					BlockPos diagonalGround = mobPos.relative(faceDir, checkFwd).relative(faceDir.getClockWise(), lat);
					if (isGapOpen(level, diagonalGround)) {
						hasGap = true;
						break;
					}
				}

				if (!hasGap) continue;

				// Verify all intermediate columns have proper clearance for jump trajectory
				boolean pathClear = true;
				for (int checkFwd = 1; checkFwd < checkDist; checkFwd++) {
					BlockPos straightCol = mobPos.relative(faceDir, checkFwd);
					BlockPos diagonalCol = mobPos.relative(faceDir, checkFwd).relative(faceDir.getClockWise(), lat);

					// Gap clearance non-balloon requires y=0; balloon requires y=0 and y+1 for both lanes
					if (balloonJump) {
						if (!isGapOpen(level, straightCol) || !isGapOpen(level, straightCol.above()) ||
								!isGapOpen(level, diagonalCol) || !isGapOpen(level, diagonalCol.above())) {
							pathClear = false;
							break;
						}
					} else {
						if (!isGapOpen(level, straightCol) || !isGapOpen(level, diagonalCol)) {
							pathClear = false;
							break;
						}
					}
				}

				if (!pathClear) continue;

				BlockPos landingTop = getLandingTop(level, candidate, maxUp, balloonJump);
				if (landingTop != null) {
					int landingY = landingTop.getY() + 1;

					// If the target is not lower (same or higher Y), avoid stepping down: require level or upward landing
					int targetYLocal = (int) Math.floor(targetExactPos.y);
					boolean targetNotLower = targetYLocal >= mobY;
					if (targetNotLower && landingY < mobY) continue;

					// Apply vertical rules
					if (balloonJump) {
						// Balloon: ≤ +1 up, any drop (except blocked above when target not lower)
						if (landingY > mobY + 1) continue;
					} else {
						// No balloon: +1, 0, -1, -2, -3, -4 (except blocked above when target not lower)
						int verticalDiff = landingY - mobY;
						if (verticalDiff < -4 || verticalDiff > 1) continue;
					}

					// Pick closest to target
					double distToTarget = candidate.distSqr(BlockPos.containing(targetExactPos));
					if (distToTarget < bestDistToTarget) {
						bestDistToTarget = distToTarget;
						bestLanding = surfaceCenter(landingTop);
					}
				}
			}
		}

		return bestLanding;
	}

	/*
	 * Plan: balloon-led downward pursuit (ledge step-off)
	 * - Requires holding a balloon; only triggers when the target is meaningfully below the mob.
	 * - Detects an immediate ledge where the next column(s) have feet+head clear (air column) 1 or 2 blocks ahead in faceDir.
	 * - When a ledge is detected, commit the jump and steer directly toward the target while falling.
	 * - Returns the target position to steer toward; null if no ledge is found.
	 */
	private Vec3 planBalloonDrop(int targetY, int mobY) {
		if (!balloonJump || targetY >= mobY) return null;
		return targetExactPos;
	}


	/* --------------------------- [plan getters] ---------------------------- */

	// Try to find a standable top surface
	// Enhanced search depth for balloons (16) vs regular (4)
	private BlockPos getLandingTop(Level level, BlockPos col, int maxUp, boolean allowDeepDrop) {
		// Search upward first
		for (int up = 0; up <= maxUp; up++) {
			BlockPos checkPos = col.above(up);
			if (isSolid(level, checkPos)
					&& isAir(level, checkPos.above())
					&& isAir(level, checkPos.above(2))
					&& isAir(level, checkPos.above(3))) {
				return checkPos;
			}
		}

		// Search downward - use configured maxDown, but clamp to 4 when deep drops are not allowed
		int searchDown = allowDeepDrop ? this.maxDown : Math.min(this.maxDown, 4);
		for (int down = 1; down <= searchDown; down++) {
			BlockPos checkPos = col.below(down);
			if (isSolid(level, checkPos)
					&& isAir(level, checkPos.above())
					&& isAir(level, checkPos.above(2))
					&& isAir(level, checkPos.above(3))) {
				return checkPos;
			}
		}

		return null;
	}


	// Return true if handled (either pathing started or jump fired)
	private boolean handleImmediatePlan(Level level, BlockPos mobPos) {
		if (!landingPos.equals(Vec3.ZERO)) {
			Vec3 takeoff = findTakeoffBeforeTraverse(level, mobPos, this.faceDir, this.maxGap+1);
			if (takeoff != null && horizontalDistSqr(mob.position(), takeoff) > TAKEOFF_REACH_SQR) {
				// Path to the takeoff first to ensure a proper approach
				jumpFromPos = takeoff;
				navTo(jumpFromPos);
				state = JumpState.PATHFINDING;
				return true;
			}
			// Already at the takeoff (or none found), choose surface center of current and only jump if within reach
			BlockPos curTop = getLandingTop(level, mobPos, 1, false);
			jumpFromPos = (curTop != null) ? surfaceCenter(curTop) : Vec3.atCenterOf(mobPos);
			if (isAtJumpPosition()) {
				fireJump();
			} else {
				// Align precisely to the jump-from before jumping to avoid early takeoffs
				navTo(jumpFromPos);
				state = JumpState.PATHFINDING;
			}
			return true;
		}
		return false;
	}

	// Lightweight ring scan: return the first acceptable jump-from/landing pair, respecting priority via evaluateJumpFromPosition
	private JumpSelection searchBetterJumpFrom(Level level, BlockPos mobPos, int targetY) {
		int maxRadius = balloonJump ? 8 : 6;
		for (int r = 1; r <= maxRadius; r++) {
			for (int dx = -r; dx <= r; dx++) {
				for (int dz = -r; dz <= r; dz++) {
					if (Math.max(Math.abs(dx), Math.abs(dz)) != r) continue; // ring only
					BlockPos candidate = mobPos.offset(dx, 0, dz);
					// Must be standable at candidate (allow +1 for slabs/stairs)
					BlockPos top = getLandingTop(level, candidate, 1, false);
					if (top == null) continue;
					BlockPos stand = top.above();
					Vec3 landing = evaluateJumpFromPosition(level, stand, targetY);
					if (landing == null) continue;
					// Ranged: avoid choosing landings that end too close to target
					if (isRangedAttacker()) {
						double h2 = new Vec3(landing.x - targetExactPos.x, 0, landing.z - targetExactPos.z).lengthSqr();
						if (h2 < (6.0D * 6.0D)) continue;
					}
					// Prefer to jump from a proper takeoff block from this stand position
					Direction standFacing = computeFaceDirFrom(stand);
					Vec3 takeoffFromStand = findTakeoffBeforeTraverse(level, stand, standFacing, this.maxGap+1);
					Vec3 jumpFromCandidate = (takeoffFromStand != null) ? takeoffFromStand : surfaceCenter(stand.below());
					return new JumpSelection(jumpFromCandidate, landing);
				}
			}
		}
		return null;
	}

	// Find the best takeoff block before a traverse (gap, step-up, diagonal approach)
	private Vec3 findTakeoffBeforeTraverse(Level level, BlockPos origin, Direction facing, int maxSteps) {
		Vec3 lastStandable = null;
		BlockPos lastTop = null;
		boolean foundReason = false;
		for (int fwd = 0; fwd < maxSteps; fwd++) {
			BlockPos current = origin.relative(facing, fwd);
			BlockPos next = origin.relative(facing, fwd + 1);
			// Be tolerant: consider slabs/stairs by allowing up to +1
			BlockPos top = getLandingTop(level, current, 1, false);
			if (top != null) {
				lastTop = top;
				lastStandable = surfaceCenter(top);
			}
			// Do NOT break on null; we might be traversing partials — keep scanning a few steps
			// Early return when a drop/gap is detected ahead (any air or fluid ahead qualifies for gap)
			boolean dropAhead = isGapOpen(level, next);
			if (dropAhead && lastStandable != null) {
				foundReason = true;
				return lastStandable;
			}
			// If the next tile is a higher step, current is a good takeoff
			BlockPos nextTop = getLandingTop(level, next, 1, false);
			if (nextTop != null && lastTop != null && nextTop.getY() > lastTop.getY() && lastStandable != null) {
				foundReason = true;
				return lastStandable;
			}
		}
		return foundReason ? lastStandable : null;
	}

	private void pathToJumpFrom(Vec3 from, Vec3 landing) {
		jumpFromPos = from;
		landingPos = landing;
		navTo(jumpFromPos);
		state = JumpState.PATHFINDING;
	}

	private static final class JumpSelection {
		final Vec3 jumpFrom;
		final Vec3 landing;
		JumpSelection(Vec3 jumpFrom, Vec3 landing) {
			this.jumpFrom = jumpFrom;
			this.landing = landing;
		}
	}

	/**
	 * Evaluates if a position offers good jumping opportunities toward the target
	 */
	private Vec3 evaluateJumpFromPosition(Level level, BlockPos jumpFromPos, int targetY) {
		// Temporarily compute facing from this stand position toward current target
		Direction prevFace = this.faceDir;
		this.faceDir = computeFaceDirFrom(jumpFromPos);
		try {
			// Priority: elevation, straight, diagonal
			Vec3 toward = new Vec3(targetExactPos.x - (jumpFromPos.getX() + 0.5), 0, targetExactPos.z - (jumpFromPos.getZ() + 0.5));
			int desiredYLocal = targetY;
			if (isRangedAttacker()) desiredYLocal = targetY + 4;
			if (jumpFromPos.getY() < desiredYLocal) {
				Vec3 elev = planElevation(level, jumpFromPos, desiredYLocal);
				if (elev != null) return elev;
			}
			Vec3 straight = planStraightGap(level, jumpFromPos, jumpFromPos.getY());
			if (straight != null) return straight;
			Vec3 diag = planDiagonalGap(level, jumpFromPos, jumpFromPos.getY(), toward.lengthSqr() > 1.0e-6 ? toward.normalize() : toward);
			return diag;
		} finally {
			this.faceDir = prevFace;
		}
	}

	private Direction computeFaceDirFrom(BlockPos origin) {
		Vec3 from = new Vec3(origin.getX() + 0.5, 0, origin.getZ() + 0.5);
		Vec3 toTarget = new Vec3(targetExactPos.x - from.x, 0, targetExactPos.z - from.z);
		if (toTarget.lengthSqr() < 0.01) return faceDir;
		if (Math.abs(toTarget.x) > Math.abs(toTarget.z)) {
			return toTarget.x > 0 ? Direction.EAST : Direction.WEST;
		} else {
			return toTarget.z > 0 ? Direction.SOUTH : Direction.NORTH;
		}
	}

	private boolean isAtJumpPosition() {
		if (jumpFromPos.equals(Vec3.ZERO)) return false;
		return horizontalDistSqr(mob.position(), jumpFromPos) <= TAKEOFF_REACH_SQR;
	}

	private static double horizontalDistSqr(Vec3 a, Vec3 b) {
		double dx = a.x - b.x;
		double dz = a.z - b.z;
		return dx * dx + dz * dz;
	}


	/* ------------------------------- [Forced Movement] -------------------------------- */

	// Centralized navigation helper: accept a surfaceCenter and path to the ground Y beneath it
	private void navTo(Vec3 surfaceCenter) {
		// Compute a slightly-above-ground target Y to avoid air-node targeting quirks
		double groundY = surfaceCenter.y - 1.0D + 0.0625D;
		// Always path at speed scale 1.0 as per spec
		mob.getNavigation().moveTo(surfaceCenter.x, groundY, surfaceCenter.z, 1.0D);
		// Face the navigation target for clarity
		mob.getLookControl().setLookAt(surfaceCenter.x, surfaceCenter.y, surfaceCenter.z);
	}

	/**
	 * Triggers a single committed jump toward the planned landing.
	 * - Stops Navigation to avoid control conflicts.
	 * - Requires landingPos to be planned; aborts if none.
	 * - Sets a consistent horizontal carry vector toward the committed landing (XZ only) and performs a vanilla jump.
	 */
	private void fireJump() {
		// Ensure Navigation does not fight the takeoff
		mob.getNavigation().stop();

		// Do not jump without a planned landing; back off and re-plan
		if (landingPos.equals(Vec3.ZERO)) {
			// Reset to grounded and apply cooldown to avoid immediate retrigger
			resetToGrounded(true, true);
			return;
		}

		// Latch the landing we intend to reach while airborne
		committedLanding = landingPos;
		Vec3 steerPos = committedLanding;

		// Compute and store a one-time horizontal carry toward steerPos
		Vec3 from = mob.position();
		Vec3 dir = new Vec3(steerPos.x - from.x, 0, steerPos.z - from.z);
		if (dir.lengthSqr() > 1.0e-6) {
			dir = dir.normalize();
			double carry = balloonJump ? 0.30D : 0.26D; // ensure reliable 2-gap at same Y for non-balloon
			airCarryXZ = new Vec3(dir.x * carry, 0.0, dir.z * carry);
		} else {
			airCarryXZ = Vec3.ZERO;
		}

		// Orient look toward the steer position
		mob.getLookControl().setLookAt(steerPos.x, steerPos.y, steerPos.z);

		// Guard: only jump if grounded
		if (!mob.onGround()) {
			return; // wait until we are grounded at takeoff
		}
		// Execute vanilla jump (affects vertical component)
		mob.getJumpControl().jump();
		mob.setJumping(true);

		// Update state and consume cooldown
		state = JumpState.AIRBORNE;
		lastJumpTick = mob.tickCount;
	}

	/**
	 * While airborne keep horizontal motion consistent by enforcing the stored airCarryXZ.
	 * Vertical component (Y) remains controlled by vanilla physics/effects.
	 */
	private void moveTo() {
		// Only steer if this goal committed a jump (non-zero air carry) and we have a committed landing
		if (airCarryXZ.equals(Vec3.ZERO) || committedLanding.equals(Vec3.ZERO)) return;
		// Steer strictly toward the committed landing selected at takeoff
		Vec3 steerPos = committedLanding;
		Vec3 from = mob.position();
		
		// Current motion
		Vec3 cur = mob.getDeltaMovement();
		double curX = cur.x;
		double curZ = cur.z;
		
		// Horizontal distance to steer target
		double dx = steerPos.x - from.x;
		double dz = steerPos.z - from.z;
		double distXZ = Math.hypot(dx, dz);
		
		// When the entity has essentially reached the target horizontally, stop pushing and halt horizontal motion entirely
		if (distXZ <= AIRBORNE_EASE_RADIUS) {
			// Stop applying additional air carry to prevent overshoot and halt XZ motion
			airCarryXZ = Vec3.ZERO;
			curX = 0.0D;
			curZ = 0.0D;
			mob.setDeltaMovement(curX, cur.y, curZ);
			mob.getLookControl().setLookAt(steerPos.x, steerPos.y, steerPos.z);
			return;
		}
		
		// Blend gently toward the desired air carry to avoid jerky acceleration
		double blend = 0.5D; // 50% toward target carry per tick
		double desiredX = airCarryXZ.x;
		double desiredZ = airCarryXZ.z;
		double newX = curX * (1.0D - blend) + desiredX * blend;
		double newZ = curZ * (1.0D - blend) + desiredZ * blend;
		
		// Clamp to a lower max speed in air to reduce overall aerial motion
		double horiz = Math.hypot(newX, newZ);
		double max = AIRBORNE_MAX_SPEED;
		if (horiz > max) {
			double scale = max / horiz;
			newX *= scale;
			newZ *= scale;
		}
		mob.setDeltaMovement(newX, cur.y, newZ);
		
		// Keep looking toward steer target for readability and intent
		mob.getLookControl().setLookAt(steerPos.x, steerPos.y, steerPos.z);
	}


	/* ------------------------------- [Utilities] -------------------------------- */

	private boolean holdsBalloon() {
		return (mob.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem)
				|| (mob.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem);
	}

	private void setupBalloon() {
		if (holdsBalloon()) {
			balloonJump = true;
			maxUp = 3;
			maxGap = 4;
			maxDown = 16;
		} else {
			balloonJump = false;
			maxUp = 1;
			maxGap = 2;
			maxDown = 4;
		}
	}

	private boolean isRangedAttacker() {
		var main = mob.getItemInHand(InteractionHand.MAIN_HAND).getItem();
		var off = mob.getItemInHand(InteractionHand.OFF_HAND).getItem();
		return (main instanceof BowItem || main instanceof CrossbowItem || main instanceof TridentItem
				|| off instanceof BowItem || off instanceof CrossbowItem || off instanceof TridentItem);
	}

	private Vec3 surfaceCenter(BlockPos top) {
		return new Vec3(top.getX() + 0.5D, top.getY() + 1.0D, top.getZ() + 0.5D);
	}


	/* ------------------------------- [Collision Help] -------------------------------- */

	private boolean isAir(Level level, BlockPos pos) {
		return level.isEmptyBlock(pos);
	}
	
	private boolean isFluid(Level level, BlockPos pos) {
		return !level.getFluidState(pos).isEmpty();
	}
	
	// For gap detection/clearance only: treat fluids as passable like air
	private boolean isGapOpen(Level level, BlockPos pos) {
		return isAir(level, pos) || isFluid(level, pos);
	}

	private boolean isSolid(Level level, BlockPos pos) {
		if (level.isEmptyBlock(pos)) return false;

		try {
			var state = level.getBlockState(pos);
			if (state.isFaceSturdy(level, pos, Direction.UP)) return true;

			var shape = state.getCollisionShape(level, pos);
			if (shape.isEmpty()) return false;

			double top = shape.max(Direction.Axis.Y);
			return top >= 0.5D;
		} catch (Exception e) {
			// Handle edge cases near world borders or during chunk loading
			return false;
		}
	}
}
