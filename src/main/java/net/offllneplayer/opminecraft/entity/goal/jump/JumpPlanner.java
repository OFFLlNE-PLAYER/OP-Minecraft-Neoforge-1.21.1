package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.UTIL.debug.LogColors;

/**
 * JumpPlanner: centralized jump decision maker.
 * - Scans all nearby jump options across types (elevation, gaps, controlled drop) in the four cardinals.
 * - Adds a melee-only swinging poke when the target is within 1 block horizontally and at maxUp+1 Y above: jump straight up to enable a vanilla melee swing even if unlandable.
 * - Scores primarily by closing distance to the target; elevation is rewarded but not hard-capped by desiredYGain.
 * - Prevents downward/sideways trolls by only allowing drops that improve XZ distance.
 * - Once a candidate wins, validates corridor/capabilities and writes jumpFromPos/landingPos to JumpContext for execution.
 */
public final class JumpPlanner {

    private static void logScoredCandidate(JumpContext ctx, String type, Direction fd, Vec3 landing, double score) {
        try {
            double dXZ = JumpUtils.horizontalDistSqr(landing, ctx.targetExactPos);
            double dY = landing.y - ctx.targetExactPos.y;
            LogColors.debugBlue("[OP_DEBUG_useJump.plan] scored type=" + type + " dir=" + fd + " landing=" + landing + " score=" + String.format(java.util.Locale.ROOT, "%.3f", score) + " dXZ2=" + String.format(java.util.Locale.ROOT, "%.3f", dXZ) + " dY=" + String.format(java.util.Locale.ROOT, "%.3f", dY));
        } catch (Throwable ignored) {}
    }

    private static void debugDecide(String type) {
            LogColors.debugBlueBold("[OP_DEBUG_useJump.plan] decideJump checking:" + type);
    }

    private static void debugFinalize(JumpContext ctx) {
            LogColors.debugBlue("[OP_DEBUG_useJump.plan] finalized takeoff=" + ctx.jumpFromPos + " landing=" + ctx.landingPos);
    }


    /**
     * Decide and commit a jump for this tick.
     * Scans all cardinal directions and all jump types (elevation, gaps, controlled drop) and scores them together.
     * Elevation planning no longer hard-caps at desiredYGain; upward options are considered if they close distance.
     * Drops are only considered if they strictly reduce horizontal distance to the target.
     * Returns true if a validated plan was written to ctx (jumpFromPos, landingPos).
     */
    public static boolean decideJump(JumpContext ctx, Level level, BlockPos mobPos, Direction baseGrid) {
        // Compute current stand
        BlockPos top = JumpUtils.getLandingTop(level, mobPos, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT);
        if (top == null) return false;
        BlockPos stand = top.above();
        int mobY = stand.getY();
        // Derive simple integer target Y like mob.blockPosition().getY()
        int targetY = BlockPos.containing(ctx.targetExactPos.x, ctx.targetExactPos.y, ctx.targetExactPos.z).getY();
        boolean needElev = (mobY < targetY);
        boolean allowDrop = targetY < mobY;


        // Scan facings: 4 cardinals
        Direction[] cardinals = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

        // Helpers to track best per type
        Vec3 bestElev = null; double bestElevScore = Double.MAX_VALUE;
        Vec3 bestGap = null; double bestGapScore = Double.MAX_VALUE;
        Vec3 bestDrop = null; double bestDropScore = Double.MAX_VALUE;

        // Elevation: try each cardinal facing using planElevation which already laterally samples
        if (needElev) {
            for (Direction fd : cardinals) {
                Vec3 elevLanding = planElevation(level, stand, fd, ctx.maxGap, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT, ctx.targetExactPos);
                if (elevLanding == null) continue;
                // Pre-finalize checks (takeoff + corridor + failed jumpfrom avoidance)
                if (!prevalidateCandidate(ctx, level, stand, top, fd, elevLanding)) continue;
                // Lethargy filter: if candidate landing is not closer than current, skip scoring entirely
                double curD2 = JumpUtils.horizontalDistSqr(JumpUtils.surfaceCenter(top), ctx.targetExactPos);
                double candD2 = JumpUtils.horizontalDistSqr(elevLanding, ctx.targetExactPos);
                if (candD2 >= curD2 - 1.0e-6) { try{ LogColors.debugBlue("[OP_DEBUG_useJump.plan] skip elev not-closer dir="+fd+" landing="+elevLanding+" curD2="+String.format(java.util.Locale.ROOT, "%.3f", curD2)+" candD2="+String.format(java.util.Locale.ROOT, "%.3f", candD2)); }catch(Throwable ignored){} continue; }
                double score = scoreCandidate(ctx, stand, elevLanding, true, false);
                logScoredCandidate(ctx, "elev", fd, elevLanding, score);
                if (score < bestElevScore) {
						 bestElevScore = score;
						 bestElev = elevLanding;
					 }
            }
            if (bestElev != null) {
                if (finalizeAndCommit(ctx, level, stand, top, facingToward(stand, bestElev, baseGrid), bestElev)) {
                    debugDecide("elevation");
                    return true;
                }
            }
        }

        // Gap: enumerate straight for all cardinals, distances 2..maxGap+1
        // For non-balloon mobs: score the closest valid per direction (bias via score tweak below).
        // For balloon mobs: planGapJump internally considers all distances up to maxGap+1 and we keep the best per facing to compare globally.
        for (Direction fd : cardinals) {
            Vec3 straight = planGapJump(level, stand, mobY, fd, ctx.maxGap, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT);
            if (prevalidateCandidate(ctx, level, stand, top, fd, straight)) {
                // Lethargy filter: only consider gap if it strictly closes horizontal distance vs current stand
                double curD2 = JumpUtils.horizontalDistSqr(JumpUtils.surfaceCenter(top), ctx.targetExactPos);
                double candD2 = JumpUtils.horizontalDistSqr(straight, ctx.targetExactPos);
                if (candD2 >= curD2 - 1.0e-6) { try{ LogColors.debugBlue("[OP_DEBUG_useJump.plan] skip gap not-closer dir="+fd+" landing="+straight+" curD2="+String.format(java.util.Locale.ROOT, "%.3f", curD2)+" candD2="+String.format(java.util.Locale.ROOT, "%.3f", candD2)); }catch(Throwable ignored){} continue; }
                // Non-balloon: prefer the closest valid (shortest) candidate by inflating score for longer checkDist.
                // Balloon: allow farther candidates to compete normally.
                double baseScore = scoreCandidate(ctx, stand, straight, false, false);
                double score = baseScore;
                if (!ctx.balloonJump) {
                    // Apply a mild extra cost proportional to distance from stand to landing to bias toward closer jump for non-balloon
                    double runup = JumpUtils.horizontalDistSqr(new Vec3(stand.getX()+0.5,0,stand.getZ()+0.5), straight);
                    score += 0.5D * runup; // strengthen "closest first" preference for non-balloon
                }
                logScoredCandidate(ctx, "gap", fd, straight, score);
                if (score < bestGapScore) {
                     bestGapScore = score;
                     bestGap = straight;
                 }
            }
        }

        // Drop: test forward-1 drop for each cardinal, but only consider if it actually closes horizontal distance
        if (allowDrop) {
            for (Direction fd : cardinals) {
                Vec3 dropLanding = planJumpDown(level, stand, mobY, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT, fd);
                if (dropLanding == null) continue;
                // Require strict improvement in XZ distance to target to avoid sideways/downward trolling
                double curD2 = JumpUtils.horizontalDistSqr(JumpUtils.surfaceCenter(top), ctx.targetExactPos);
                double dropD2 = JumpUtils.horizontalDistSqr(dropLanding, ctx.targetExactPos);
                if (dropD2 >= curD2 - 1.0e-6) continue;
                if (!prevalidateCandidate(ctx, level, stand, top, fd, dropLanding)) continue;
                // Redundant given allowDrop and earlier check, but keep the lethargy filter consistent
                double curD2b = JumpUtils.horizontalDistSqr(JumpUtils.surfaceCenter(top), ctx.targetExactPos);
                double candD2b = JumpUtils.horizontalDistSqr(dropLanding, ctx.targetExactPos);
                if (candD2b >= curD2b - 1.0e-6) { try{ LogColors.debugBlue("[OP_DEBUG_useJump.plan] skip drop not-closer dir="+fd+" landing="+dropLanding); }catch(Throwable ignored){} continue; }
                double score = scoreCandidate(ctx, stand, dropLanding, false, true);
                logScoredCandidate(ctx, "down", fd, dropLanding, score);
                if (score < bestDropScore) {
						 bestDropScore = score;
						 bestDrop = dropLanding;
					 }
            }
        }

        // Choose globally best among elevation/gap/drop (in that tie-break order)
        Vec3 chosen = null;
		  String chosenType = null;
        double bestScoreAll = Double.MAX_VALUE;
        if (bestElev != null && bestElevScore < bestScoreAll) { bestScoreAll = bestElevScore; chosen = bestElev; chosenType = "elevation"; }
        if (bestGap != null && bestGapScore < bestScoreAll) { bestScoreAll = bestGapScore; chosen = bestGap; chosenType = "gap"; }
        if (bestDrop != null && bestDropScore < bestScoreAll) { bestScoreAll = bestDropScore; chosen = bestDrop; chosenType = "down"; }
        if (chosen != null) {
            if (finalizeAndCommit(ctx, level, stand, top, facingToward(stand, chosen, baseGrid), chosen)) {
                try { LogColors.debugBlueBold("[OP_DEBUG_useJump.plan] FINAL type=" + chosenType + " score=" + String.format(java.util.Locale.ROOT, "%.3f", bestScoreAll) + " takeoff=" + ctx.jumpFromPos + " landing=" + ctx.landingPos); } catch (Throwable ignored) {}
                debugDecide(chosenType);
                return true;
            }
        }
        // Fallback: melee-only swinging poke if no valid jump was selected
        if (!ctx.hasRangedWep) {
            Vec3 mobCenter = JumpUtils.surfaceCenter(top);
            double horizD2 = JumpUtils.horizontalDistSqr(mobCenter, ctx.targetExactPos);
            int dy = targetY - mobY;
            if (horizD2 <= (0.9D) && dy == (ctx.maxUp + 1)) {
                int clearance = Math.max(1, ctx.maxUp + ctx.COLLISION_HEIGHT);
                if (!JumpUtils.isNoPathHere(level, stand, clearance)) {
                    Vec3 takeoff = mobCenter;
                    Vec3 upward = new Vec3(mobCenter.x, mobCenter.y + ctx.maxUp, mobCenter.z);
                    Vec3 dirToTarget = ctx.targetExactPos.subtract(mobCenter).normalize();
                    Vec3 fwd = new Vec3(dirToTarget.x * 0.1F, 0.0D, dirToTarget.z * 0.1F);
                    ctx.jumpFromPos = takeoff;
                    ctx.landingPos = upward;
                    ctx.airCarryXZ = fwd;
                    try { LogColors.debugBlueBold("[OP_DEBUG_useJump.plan] FINAL type=poke (fallback) takeoff=" + takeoff + " landing=" + upward); } catch (Throwable ignored) {}
                    debugDecide("poke");
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean prevalidateCandidate(JumpContext ctx, Level level, BlockPos standPos, BlockPos topForFallback, Direction grid, Vec3 landing) {
        if (landing == null || landing.equals(Vec3.ZERO)) return false;
        // No targetY gate here; capability bounds and corridor validation will decide feasibility
        int hMax = ctx.COLLISION_HEIGHT;
        if (JumpUtils.isNoPathHere(level, standPos, hMax)) return false;
        BlockPos landingStand = BlockPos.containing(landing.x, landing.y, landing.z);
        if (JumpUtils.isNoPathHere(level, landingStand, hMax)) return false;
        Vec3 takeoff = plannedTakeoffVec(level, standPos, ctx.maxGap + 1, grid, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT);
        if (takeoff == null) takeoff = JumpUtils.surfaceCenter(topForFallback);
        if (ctx.failedJumpFromPos != null && !ctx.failedJumpFromPos.equals(Vec3.ZERO)) {
            if (JumpUtils.horizontalDistSqr(takeoff, ctx.failedJumpFromPos) <= 0.0069D) return false;
        }
        return validateSimpleCorridor(ctx, level, takeoff, landing, grid);
    }

    private static double scoreCandidate(JumpContext ctx, BlockPos standPos, Vec3 landing, boolean elevation, boolean isDrop) {
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
        double targetY = net.minecraft.core.BlockPos.containing(ctx.targetExactPos.x, ctx.targetExactPos.y, ctx.targetExactPos.z).getY();
        double verticalMiss = Math.max(0.0D, targetY - landing.y);
        double dropBias = isDrop ? 8.0D : 0.0D; // slight constant bias against drops
        double missPenalty = verticalMiss * (isDrop ? 128.0D : 16.0D);
        return toTarget + 0.25D * runup - elevReward + missPenalty + dropBias;
    }

    private static Direction facingToward(BlockPos fromStand, Vec3 landing, Direction fallback) {
        int dx = (int)Math.signum((int)Math.floor(landing.x) - fromStand.getX());
        int dz = (int)Math.signum((int)Math.floor(landing.z) - fromStand.getZ());
        Vec3 dir = new Vec3(dx, 0, dz);
        return JumpUtils.cardinalFrom(dir, fallback);
    }

    /** Finalize a candidate landing into ctx by computing a takeoff and validating corridor/columns */
    private static boolean finalizeAndCommit(JumpContext ctx, Level level, BlockPos standPos, BlockPos topForFallback, Direction grid, Vec3 landing) {
        if (landing == null || landing.equals(Vec3.ZERO)) return false;
        // Minimal safety: ensure AIR columns at stand and landing for the mob's collision height
        int hMax = ctx.COLLISION_HEIGHT;
        if (JumpUtils.isNoPathHere(level, standPos, hMax)) return false;
        BlockPos landingStand = BlockPos.containing(landing.x, landing.y, landing.z);
        if (JumpUtils.isNoPathHere(level, landingStand, hMax)) return false;

        // Compute takeoff from stand toward landing; fallback to surface center of provided top block
        Vec3 standCenter = new Vec3(standPos.getX() + 0.5D, 0.0D, standPos.getZ() + 0.5D);
        Vec3 toLandingFromStand = new Vec3(landing.x - standCenter.x, 0.0D, landing.z - standCenter.z);
        Vec3 takeoff = plannedTakeoffVec(level, standPos,ctx.maxGap + 1, grid, ctx.maxUp, ctx.maxDown, ctx.COLLISION_HEIGHT);
        if (takeoff == null) {
            takeoff = JumpUtils.surfaceCenter(topForFallback);
        }

        // Avoid reusing the last failed takeoff (if remembered)
        if (ctx.failedJumpFromPos != null && !ctx.failedJumpFromPos.equals(Vec3.ZERO)) {
            double failD2 = JumpUtils.horizontalDistSqr(takeoff, ctx.failedJumpFromPos);
            // ~0.08 blocks tolerance in XZ (squared ~0.0064)
            if (failD2 <= 0.0064D) {
                try { LogColors.debugBlue("[OP_DEBUG_useJump.plan] skip candidate: matches failed jumpFrom " + ctx.failedJumpFromPos); } catch (Throwable ignored) {}
                return false;
            }
        }

        // Validate a simple corridor of AIR columns between takeoff and landing
        if (!validateSimpleCorridor(ctx, level, takeoff, landing, grid)) return false;

        // Commit to context
        ctx.jumpFromPos = takeoff;
        ctx.landingPos = landing;
        // Compute air carry vector for consistent horizontal motion while airborne
        Vec3 dir = new Vec3(landing.x - takeoff.x, 0.0D, landing.z - takeoff.z);
        if (dir.lengthSqr() > 1.0e-6) {
            Vec3 norm = dir.normalize();
            Vec3 carry = new Vec3(norm.x * JumpContext.AIR_CARRY, 0.0D, norm.z * JumpContext.AIR_CARRY);
            // Clamp to airborne max speed bounds (component-wise magnitude)
            double max = JumpContext.AIRBORNE_MAX_SPEED;
            double cx = Math.max(-max, Math.min(max, carry.x));
            double cz = Math.max(-max, Math.min(max, carry.z));
            ctx.airCarryXZ = new Vec3(cx, 0.0D, cz);
        } else {
            ctx.airCarryXZ = Vec3.ZERO;
        }
        // Initialize approach tracking and clear any prior failure so the new plan is picked up reliably
        ctx.lastTakeoffErrorSqr = JumpUtils.horizontalDistSqr(ctx.mob.position(), ctx.jumpFromPos);
        ctx.takeoffNotImprovingTicks = 0;
        ctx.planFailedRecently = false;
        ctx.failedJumpFromPos = Vec3.ZERO;
        debugFinalize(ctx);
        return true;
    }

    /** Simple corridor validator: ensure AIR columns strictly along the provided grid direction between takeoff and landing */
    private static boolean validateSimpleCorridor(JumpContext ctx, Level level, Vec3 takeoff, Vec3 landing, Direction grid) {
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
                boolean anyGap = false; boolean pathClear = true; int gaps = 0;
                for (int fwd = 1; fwd < checkDist; fwd++) {
                    BlockPos col = mobPos.relative(fd, fwd);
                    boolean gap = JumpUtils.isGapOpen(level, col.below());
                    if (gap) { anyGap = true; gaps++; }
                    if (!gap || JumpUtils.isNoPathHere(level, col, collisionHeight)) { pathClear = false; break; }
                }
                if (!(gaps <= maxGap && anyGap && pathClear)) {
                    // skip
                } else {
                     boolean corridorClear = true;
                     for (int step = 0; step < checkDist; step++) {
                         BlockPos col = mobPos.relative(fd, step);
                         if (JumpUtils.isNoPathHere(level, col, collisionHeight)) {
                             corridorClear = false;
                             break;
                         }
                     }
                     if (corridorClear) {
                         BlockPos candidate = mobPos.relative(fd, checkDist);
                         BlockPos landingTop = JumpUtils.getLandingTop(level, candidate, maxUp, maxDown, collisionHeight);
                         if (landingTop != null && !JumpUtils.isNoPathHere(level, landingTop.above(), collisionHeight)) {
                          int landingStandY = landingTop.getY() + 1;
                          // Respect capability bounds only (no separate targetY gating)
                          if (landingStandY <= mobY + maxUp && landingStandY >= mobY - maxDown) {
                              Vec3 center = JumpUtils.surfaceCenter(landingTop);
                              // Local heuristic: prefer farther within capability (closes more XZ), but leave final scoring to caller
                              double localScore = -checkDist; // larger distance -> lower score
                              if (localScore < bestLocal) { bestLocal = localScore; best = center; }
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
        if (JumpUtils.isNoPathHere(level, landingTop.above(), collisionHeight)) return null;
        int landingStandY = landingTop.getY() + 1;
        if (landingStandY < mobY && landingStandY >= mobY - maxDown) {
            return JumpUtils.surfaceCenter(landingTop);
        }
        return null;
    }


    public static Vec3 plannedTakeoffVec(Level level, BlockPos origin, int maxSteps, Direction faceDir, int maxUp, int maxDown, int collisionHeight) {
        Direction facing = faceDir;
        Vec3 lastStand = null; BlockPos lastTop = null; boolean reason = false;
        for (int fwd = 0; fwd < maxSteps; fwd++) {
            BlockPos cur = origin.relative(facing, fwd);
            BlockPos next = origin.relative(facing, fwd + 1);
            BlockPos top = JumpUtils.getLandingTop(level, cur, maxUp, maxDown, collisionHeight);
            if (top != null) { lastTop = top; lastStand = JumpUtils.surfaceCenter(top); }
            boolean dropAhead = JumpUtils.isGapOpen(level, next.below());
            if (dropAhead && lastStand != null) { reason = true; return lastStand; }
            BlockPos nextTop = JumpUtils.getLandingTop(level, next, maxUp, maxDown, collisionHeight);
            if (nextTop != null && lastTop != null && nextTop.getY() > lastTop.getY() && lastStand != null) { reason = true; return lastStand; }
        }
        return reason ? lastStand : null;
    }
}