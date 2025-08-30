package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


/**
 * Miscellaneous helpers for the jump goal package: item/capability checks, LOS/distance.
 * Keep this class free of any context (JumpContext) mutations; all methods must be pure.
 */
public final class JumpUtils {

	// Collisions
	public static boolean isAir(Level level, BlockPos pos) { return level.isEmptyBlock(pos); }
	public static boolean isFluid(Level level, BlockPos pos) { return !level.getFluidState(pos).isEmpty(); }
	// Treat missing solid floor as an open gap for planning/hazard checks: any non-solid (air, fluid, or non-solid blocks like plants, carpets, powder snow)
	public static boolean isGapOpen(Level level, BlockPos pos) { return !isSolid(level, pos); }
	// Fluids (water/lava) are hazards/obstacles
	public static boolean isHazard(Level level, BlockPos pos) { return isFluid(level, pos); }

	public static boolean isSolid(Level level, BlockPos pos) {
		if (level.isEmptyBlock(pos)) return false;
		try {
			var state = level.getBlockState(pos);
			if (state.isFaceSturdy(level, pos, Direction.UP)) return true;
			var shape = state.getCollisionShape(level, pos);
			if (shape.isEmpty()) return false;
			return shape.max(Direction.Axis.Y) >= 0.5D;
		} catch (Exception e) { return false; }
	}

	/**
	 * Unified rejection: returns true if the vertical column (feet-air up to collisionHeight) at 'feet'
	 * contains any solid (non-air) block or any hazard (fluid). Use this to reject path columns, stand
	 * columns, and landing stand spaces uniformly.
	 */
	public static boolean isNoPathHere(Level level, BlockPos feet, int collisionHeight) {
		if (level == null || feet == null || collisionHeight <= 0) return false;
		for (int h = 0; h < collisionHeight; h++) {
			BlockPos p = feet.above(h);
			if (!isAir(level, p) || isHazard(level, p) || isSolid(level, p)) return true;
		}
		return false;
	}


	public static double horizontalDistSqr(Vec3 a, Vec3 b) {
		double dx=a.x-b.x, dz=a.z-b.z; return dx*dx + dz*dz;
	}

	public static Vec3 surfaceCenter(BlockPos top) { return new Vec3(top.getX()+0.5D, top.getY()+1.0D, top.getZ()+0.5D); }

	// Facing
	public static Vec3 exactToward(Mob mob, Vec3 landingPos, Vec3 targetExactPos) {
		Vec3 from = mob.position();
		Vec3 to = (landingPos != null && !landingPos.equals(Vec3.ZERO)) ? landingPos : new Vec3(targetExactPos.x, from.y, targetExactPos.z);
		Vec3 dir = new Vec3(to.x - from.x, 0.0D, to.z - from.z);
		if (dir.lengthSqr() < 1.0e-6) {
			Vec3 vv = mob.getViewVector(1.0F);
			Vec3 xz = new Vec3(vv.x, 0.0D, vv.z);
			return xz.lengthSqr() < 1.0e-6 ? Vec3.ZERO : xz.normalize();
		}
		return dir.normalize();
	}

	/** Quantize an exact horizontal vector into a cardinal Direction. */
	public static Direction cardinalFrom(Vec3 exact, Direction fallback) {
		if (exact == null || exact.lengthSqr() < 1.0e-6) return fallback;
		return Math.abs(exact.x) > Math.abs(exact.z)
				? (exact.x > 0 ? Direction.EAST : Direction.WEST)
				: (exact.z > 0 ? Direction.SOUTH : Direction.NORTH);
	}


	public static BlockPos getLandingTop(Level level, BlockPos col, int maxUp, int maxDown, int collisionHeight) {
		// Search upward first for a solid floor with clear stand-space (feet-air column) above it
		for (int up = 0; up <= maxUp; up++) {
			BlockPos p = col.above(up);
			if (JumpUtils.isSolid(level, p)) {
				// Validate stand-space using unified rejection (feet = p.above())
				if (!JumpUtils.isNoPathHere(level, p.above(), collisionHeight)) return p;
			}
		}
		// Then search downward for a solid floor with clear stand-space above it
		int searchDown = Math.max(0, maxDown);
		for (int down = 1; down <= searchDown; down++) {
			BlockPos p = col.below(down);
			if (JumpUtils.isSolid(level, p)) {
				if (!JumpUtils.isNoPathHere(level, p.above(), collisionHeight)) return p;
			}
		}
		return null;
	}


	public static boolean hasForwardOrDiagonalObstacle(Level level, BlockPos origin, Direction facing, int forwardLimit) {
		if (forwardLimit <= 0) return false;
		Direction right = facing.getClockWise();

		// Fluids (in front or on diagonals) are immediate obstacles
		for (int fwd = 1; fwd <= forwardLimit; fwd++) {
			BlockPos straight = origin.relative(facing, fwd);
			if (JumpUtils.isHazard(level, straight)) return true;
			BlockPos diagR = origin.relative(facing, fwd).relative(right, 1);
			BlockPos diagL = origin.relative(facing, fwd).relative(right, -1);
			if (JumpUtils.isHazard(level, diagR) || JumpUtils.isHazard(level, diagL)) return true;
		}

		// Treat any jumpable gap within capability as an obstacle so navigation halts before stepping in
		for (int fwd = 1; fwd <= forwardLimit; fwd++) {
			BlockPos belowStraight = origin.relative(facing, fwd).below();
			if (JumpUtils.isGapOpen(level, belowStraight)) return true;
			BlockPos belowDiagR = origin.relative(facing, fwd).relative(right, 1).below();
			BlockPos belowDiagL = origin.relative(facing, fwd).relative(right, -1).below();
			if (JumpUtils.isGapOpen(level, belowDiagR) || JumpUtils.isGapOpen(level, belowDiagL)) return true;
		}

		// Also treat as obstacle when the gap is too wide to jump (persist through the entire forwardLimit span PLUS one)
		boolean straightTooWide = true;
		for (int fwd = 1; fwd <= forwardLimit + 1; fwd++) {
			BlockPos below = origin.relative(facing, fwd).below();
			if (!JumpUtils.isGapOpen(level, below)) { straightTooWide = false; break; }
		}
		if (straightTooWide) return true;

		boolean rightDiagTooWide = true;
		boolean leftDiagTooWide = true;
		for (int fwd = 1; fwd <= forwardLimit + 1; fwd++) {
			if (rightDiagTooWide) {
				BlockPos rBelow = origin.relative(facing, fwd).relative(right, 1).below();
				if (!JumpUtils.isGapOpen(level, rBelow)) { rightDiagTooWide = false; }
			}
			if (leftDiagTooWide) {
				BlockPos lBelow = origin.relative(facing, fwd).relative(right, -1).below();
				if (!JumpUtils.isGapOpen(level, lBelow)) { leftDiagTooWide = false; }
			}
			if (!rightDiagTooWide && !leftDiagTooWide) break;
		}
		return rightDiagTooWide || leftDiagTooWide;
	}
}
