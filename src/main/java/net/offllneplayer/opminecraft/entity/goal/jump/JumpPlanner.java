package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * JumpPlanner: centralized jump decision maker.
 * - Scans all nearby jump options across types (elevation, gaps, controlled drop) in the four cardinals.
 * - Adds a melee-only swinging poke when the target is within 1 block horizontally and at maxUp+1 Y above: jump straight up to enable a vanilla melee swing even if unlandable.
 * - Scores primarily by closing distance to the target; elevation is rewarded but not hard-capped by desiredYGain.
 * - Prevents downward/sideways trolls by only allowing drops that improve XZ distance.
 * - Once a candidate wins, validates corridor/capabilities and writes jumpFromPos/landingPos to JumpContext for execution.
 */
public final class JumpPlanner {

    /**
	  * Decide and commit a jump for this tick.
	  * Scans all cardinal directions and all jump types (elevation, gaps, controlled drop) and scores them together.
	  * Elevation planning no longer hard-caps at desiredYGain; upward options are considered if they close distance.
	  * Drops are only considered if they strictly reduce horizontal distance to the target.
	  * Returns true if a validated plan was written to ctx (jumpFromPos, landingPos).
	  */
    public static void decideJump(JumpContext ctx, Level level, BlockPos mobPos, Direction baseGrid) {
        // Compute current stand
        BlockPos top = JumpUtils.getLandingTop(level, mobPos, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT);
        if (top == null) return;
        int mobY = (int)ctx.mob.getY();
        // Derive simple integer target Y like mob.blockPosition().getY()
        int targetY = BlockPos.containing(ctx.targetExactPos.x, ctx.targetExactPos.y, ctx.targetExactPos.z).getY();
        boolean needElev = mobY < targetY;
        boolean allowDrop = targetY < mobY;


        // Scan facings: compute toward cardinal and exclude its opposite to disreguard backward jumps immediately.
        Vec3 toTarget = new Vec3(ctx.targetExactPos.x - ctx.jumpFromPos.x, 0.0D, ctx.targetExactPos.z - ctx.jumpFromPos.z);
        Direction opposite = JumpUtils.cardinalFrom(toTarget, baseGrid).getOpposite();

        // Build the per-instance facings to check, exclude the opposite (backward) direction
        Direction[] cardinals = switch (opposite) {
            case NORTH -> new Direction[]{Direction.SOUTH, Direction.EAST, Direction.WEST};
            case SOUTH -> new Direction[]{Direction.NORTH, Direction.EAST, Direction.WEST};
            case EAST  -> new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST};
            case WEST  -> new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST};
            default    -> new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST};
        };

		  //setup scoring
		 Vec3 chosen = null;
		 Direction chosenGrid = null;
		 double bestScore = Double.MAX_VALUE;

		 // Helpers to track best per type
		 Vec3 bestElev = null; double bestElevScore = Double.MAX_VALUE;
		 Vec3 bestGap = null; double bestGapScore = Double.MAX_VALUE;
		 Vec3 bestDrop = null; double bestDropScore = Double.MAX_VALUE;

		 BlockPos stand = BlockPos.containing(ctx.jumpFromPos);

		 // Elevation: try each cardinal facing using planElevation which already laterally samples
			 if (needElev) {
				 for (Direction fd : cardinals) {
					 Vec3 elevLanding = planElevation(level, stand, fd, ctx.maxGap, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT, ctx.targetExactPos);
					 if (elevLanding == null) continue;
					 if (validateCandidatePos(ctx, level, stand, fd, elevLanding)) {
						 double score = scoreCandidatePos(ctx, stand, elevLanding, true, false);
						 if (score < bestElevScore) {
							 bestElevScore = score;
							 bestElev = elevLanding;
						 }
					 }
				 }
				 if (bestElev != null) {
					 bestScore = bestElevScore;
					 chosen = bestElev;
				 }
			 }

		 // Gap: enumerate straight for all cardinals, distances 2..maxGap+1
		 for (Direction fd : cardinals) {
			 Vec3 straight = planGapJump(level, stand, mobY, fd, ctx.maxGap, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT);
			 if (straight == null) continue;
			 if (validateCandidatePos(ctx, level, stand, fd, straight)) {
				 double score = scoreCandidatePos(ctx, stand, straight, false, false);
				 if (!ctx.balloonJump) {
					 double runup = JumpUtils.horizontalDistSqr(new Vec3(stand.getX() + 0.5, 0, stand.getZ() + 0.5), straight);
					 score += 0.5D * runup;
				 }
				 if (score < bestGapScore) {
					 bestGapScore = score;
					 bestGap = straight;
				 }
			 }
		 }
		 if (bestGap != null && bestGapScore < bestScore) {
			 bestScore = bestGapScore;
			 chosen = bestGap;
		 }

		 if (allowDrop) {
			 for (Direction fd : cardinals) {
				 Vec3 dropLanding = planJumpDown(level, stand, mobY, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT, fd);
				 if (dropLanding == null) continue;
				 if (validateCandidatePos(ctx, level, stand, fd, dropLanding)) {
					 double score = scoreCandidatePos(ctx, stand, dropLanding, false, true);
					 if (score < bestDropScore) {
						 bestDropScore = score;
						 bestDrop = dropLanding;
					 }
				 }
			 }
			 if (bestDrop != null && bestDropScore < bestScore) {
				 bestScore = bestDropScore;
				 chosen = bestDrop;
			 }
		 }

		 // decide jump if any exists
		 if (chosen != null) {
			 ctx.landingPos = chosen;

			// Fallback: melee-only swinging poke if no valid jump was selected and within range
		 } else if (!ctx.hasRangedWep) {
            double horizD2 = JumpUtils.horizontalDistSqr(ctx.jumpFromPos, ctx.targetExactPos);
            int dy = targetY - mobY;
            if (horizD2 <= (0.9D) && dy == (ctx.maxUp + 1)) {
                    Vec3 upward = new Vec3(ctx.jumpFromPos.x, ctx.jumpFromPos.y + ctx.maxUp, ctx.jumpFromPos.z);
                    Vec3 dirToTarget = ctx.targetExactPos.subtract(ctx.jumpFromPos).normalize();
                    Vec3 fwd = new Vec3(dirToTarget.x * 0.1F, 0.0D, dirToTarget.z * 0.1F);
                    ctx.landingPos = upward;
                    ctx.airCarryXZ = fwd;
            }
        }
	 }

	private static boolean validateCandidatePos(JumpContext ctx, Level level, BlockPos standPos, Direction grid, Vec3 landing) {
		if (landing == null || landing.equals(Vec3.ZERO)) return false;
		// Check clearance at current position and landing position
		if (JumpUtils.isNoPathHere(level, standPos, ctx.COLLISION_HEIGHT)) return false;
		BlockPos landingStand = BlockPos.containing(landing.x, landing.y, landing.z);
		if (JumpUtils.isNoPathHere(level, landingStand, ctx.COLLISION_HEIGHT)) return false;

		//check landingStand distance to target is not greater than current; prevent jumping away!
		if (JumpUtils.horizontalDistSqr(new Vec3(landingStand.getX() + 0.5, landingStand.getY(), landingStand.getZ() + 0.5), ctx.targetExactPos) >
				JumpUtils.horizontalDistSqr(ctx.jumpFromPos, ctx.targetExactPos)) return false;
		
		return validateTakeoffClearancetoLanding(ctx, level, grid, ctx.jumpFromPos, landing);
	}


	/** Simple corridor validator: ensure AIR columns strictly along the provided grid direction between takeoff and landing */
	private static boolean validateTakeoffClearancetoLanding(JumpContext ctx, Level level, Direction grid, Vec3 takeoff, Vec3 landing) {
		BlockPos start = BlockPos.containing(takeoff.x, takeoff.y, takeoff.z);
		BlockPos end = BlockPos.containing(landing.x, landing.y, landing.z);
		int dx = end.getX() - start.getX();
		int dz = end.getZ() - start.getZ();
		int h = ctx.COLLISION_HEIGHT;

		// Project distance onto the provided grid axis; require that landing is not behind the takeoff relative to grid.
		int steps;
		if (grid.getAxis() == Direction.Axis.X) {
			steps = Math.abs(dx);
			if ((grid == Direction.EAST && dx < 0) || (grid == Direction.WEST && dx > 0)) return false;
		} else { // Z axis
			steps = Math.abs(dz);
			if ((grid == Direction.SOUTH && dz < 0) || (grid == Direction.NORTH && dz > 0)) return false;
		}

		// Walk strictly along the provided grid direction, checking intermediate columns (exclude start, exclude end)
		for (int s = 1; s < steps; s++) {
			BlockPos col = start.relative(grid, s);
			if (JumpUtils.isNoPathHere(level, col, h)) return false;
		}

		// Vertical capability bounds relative to takeoff
		int takeoffY = start.getY();
		int landingY = end.getY();
		if (landingY > takeoffY + ctx.maxUp) return false;
		if (landingY < takeoffY - ctx.maxDown) return false;
		return true;
	}


    private static double scoreCandidatePos(JumpContext ctx, BlockPos standPos, Vec3 landing, boolean elevation, boolean isDrop) {
        // Prefer closeness to target; for elevation include vertical error, for gaps drop the Y component.
        double toTarget;
        if (elevation) {
            double dx = landing.x - ctx.targetExactPos.x;
            double dy = landing.y - ctx.targetExactPos.y;
            double dz = landing.z - ctx.targetExactPos.z;
            toTarget = dx*dx + dy*dy + dz*dz;
        } else {
            toTarget = JumpUtils.horizontalDistSqr(landing, ctx.targetExactPos);
        }
        Vec3 standCenter = new Vec3(standPos.getX() + 0.5D, 0.0D, standPos.getZ() + 0.5D);
        double runup = JumpUtils.horizontalDistSqr(standCenter, landing);
        double elevReward = 0.0D;
        if (elevation) {
            double gain = Math.max(0.0D, landing.y - standPos.getY());
            elevReward = gain * gain * 2.0D; // mirror planElevation
        }
        // Heavy penalty if the landing is below the target Y; even heavier for drops
        double targetY = BlockPos.containing(ctx.targetExactPos.x, ctx.targetExactPos.y, ctx.targetExactPos.z).getY();
        double verticalMiss = Math.max(0.0D, targetY - landing.y);
        double dropBias = isDrop ? 8.0D : 0.0D; // slight constant bias against drops
        double missPenalty = verticalMiss * (isDrop ? 128.0D : 16.0D);
        return toTarget + 0.25D * runup - elevReward + missPenalty + dropBias;
    }

    /**
     * Plan: elevation step toward desiredYGain
     */
    public static Vec3 planElevation(Level level, BlockPos mobPos, Direction fd, int maxGap, int maxUp, int maxDown, int collisionHeight, Vec3 targetExactPos) {
        Vec3 bestLanding = null;
        double bestScore = Double.MAX_VALUE;
        for (int fwd = 1; fwd < maxGap; fwd++) {
            for (int lat = -2; lat <= 2; lat++) {
                BlockPos candidate = mobPos.relative(fd, fwd).relative(fd.getClockWise(), lat);
                BlockPos landingTop = JumpUtils.getLandingTop(level, candidate, maxUp, maxDown, collisionHeight);
                if (landingTop != null) {
                    if (JumpUtils.isNoPathHere(level, landingTop.above(), collisionHeight)) continue;
                    int landingStandY = landingTop.getY() + 1;
                    if (landingStandY > mobPos.getY() + maxUp || landingStandY < mobPos.getY() - maxDown) continue;
                    if (landingStandY <= mobPos.getY()) continue;
                    // Allow elevation beyond desiredYLocal; rely on scoring to prefer closer-to-target results
                    // if (landingStandY > desiredYLocal) continue;
                    // Ensure corridor clearance between stand and candidate
                    boolean clear = true;
                    for (int step = 0; step < fwd; step++) {
                        BlockPos col = mobPos.relative(fd, step);
                        if (JumpUtils.isNoPathHere(level, col, collisionHeight)) { clear = false; break; }
                    }
                    if (!clear) continue;
                    double elevationGain = (landingTop.getY() + 1) - mobPos.getY();
                    Vec3 landingCenter = JumpUtils.surfaceCenter(landingTop);
                    double targetDist = JumpUtils.horizontalDistSqr(landingCenter, targetExactPos);
                    double score = targetDist - (elevationGain * elevationGain * 2.0);
                    if (score < bestScore) {
							  bestScore = score;
							  bestLanding = landingCenter;
						  }
                }
            }
        }
        return bestLanding;
    }

    /**
     * Plan: gap crossing (straight only). Returns landing center or null.
     */
	 public static Vec3 planGapJump(Level level, BlockPos mobPos, int mobY, Direction fd, int maxGap, int maxUp, int maxDown, int collisionHeight) {

		 int maxLanding = maxGap + 1;
		 Vec3 best = null;
		 double bestLocal = Double.MAX_VALUE;

		 for (int checkDist = 2; checkDist <= maxLanding; checkDist++) {
			 boolean anyGap = false;
			 int gaps = 0;
			 boolean canJump = true;

			 // Check the path for gaps and clearance in one pass
			 for (int fwd = 1; fwd < checkDist; fwd++) {
				 BlockPos pathPos = mobPos.relative(fd, fwd);
				 boolean gapBelow = JumpUtils.isGapOpen(level, pathPos.below());

				 if (gapBelow) {
					 anyGap = true;
					 gaps++;
				 }

				 // Path must be clear for jumping (air above ground)
				 if (JumpUtils.isNoPathHere(level, pathPos, collisionHeight)) {
					 canJump = false;
					 break;
				 }
			 }

			 // Must have at least one gap to jump over, not too many gaps, and clear path
			 if (anyGap && gaps <= maxGap && canJump) {
				 BlockPos candidate = mobPos.relative(fd, checkDist);
				 BlockPos landingTop = JumpUtils.getLandingTop(level, candidate, maxUp, maxDown, collisionHeight);

				 if (landingTop != null && !JumpUtils.isNoPathHere(level, landingTop.above(), collisionHeight)) {
					 int landingStandY = landingTop.getY() + 1;

					 if (landingStandY <= mobY + maxUp && landingStandY >= mobY - maxDown) {
						 Vec3 center = JumpUtils.surfaceCenter(landingTop);
						 double localScore = -checkDist; // prefer farther jumps
						 if (localScore < bestLocal) {
							 bestLocal = localScore;
							 best = center;
						 }
					 }
				 }
			 }
		 }
		 return best;
	 }


	/** Plan: downward pursuit (ledge jump-off) */
    public static Vec3 planJumpDown(Level level, BlockPos mobPos, int mobY, int maxUp, int maxDown, int collisionHeight, Direction faceDir) {
        // caller ensures allowDrop by comparing targetY and mobY; no check here
        BlockPos fwdCol = mobPos.relative(faceDir, 1);

        if (!JumpUtils.isGapOpen(level, fwdCol) || !JumpUtils.isGapOpen(level, fwdCol.above()) || !JumpUtils.isGapOpen(level, fwdCol.below())) return null;
        BlockPos landingTop = JumpUtils.getLandingTop(level, fwdCol, maxUp, maxDown, collisionHeight);
        if (landingTop == null) return null;
		 // check fwdCol and landing for clearance
		 if (JumpUtils.isNoPathHere(level, fwdCol.below(), collisionHeight+1) || JumpUtils.isNoPathHere(level, landingTop.above(), collisionHeight)) return null;

        int landingStandY = landingTop.getY() + 1;
        if (landingStandY < mobY && landingStandY >= mobY - maxDown) {
            return JumpUtils.surfaceCenter(landingTop);
        }
        return null;
    }
}