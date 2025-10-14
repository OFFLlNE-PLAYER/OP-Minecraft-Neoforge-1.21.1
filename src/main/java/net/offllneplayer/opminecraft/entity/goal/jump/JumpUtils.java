package net.offllneplayer.opminecraft.entity.goal.jump;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


/**
 * Miscellaneous helpers for the jump goal package: item/capability checks, LOS/distance.
 * Keep this class free of any context (JumpContext) mutations; all methods must be pure.
 */
public final class JumpUtils {

	// Collisions
	public static boolean isSolid(Level level, BlockPos pos) {
		if (level.isEmptyBlock(pos)) return false;
		var state = level.getBlockState(pos);
		if (state.isFaceSturdy(level, pos, Direction.UP)) return true;
		return !state.getCollisionShape(level, pos).isEmpty();
	}


	/**
	 * Unified rejection: returns true if the vertical column (feet-air up to collisionHeight) at 'feet'
	 * contains any hazard (fluid) or any true solid (colliding) block. Non-air blocks with empty collision
	 * (e.g., carpets, tall grass, flowers, snow layers, torches) are allowed.
	 * Use this to reject path columns, stand columns, and landing stand spaces uniformly.
	 */
	public static boolean isNoPathHere(Level level, BlockPos feet, int collisionHeight) {
		if (level == null || feet == null || collisionHeight <= 0) return false;
		for (int h = 0; h < collisionHeight; h++) {
			BlockPos p = feet.above(h);
			if (!level.isEmptyBlock(p) || isSolid(level, p) || !level.getFluidState(p).isEmpty() || !level.getBlockState(p).canBeReplaced()) return true;
		}
		return false;
	}

	// Treat missing solid floor as an open gap for planning/hazard checks: any non-solid (air, fluid, or non-solid blocks like plants, carpets, powder snow)
	public static boolean isGapOpen(Level level, BlockPos pos) { return !isSolid(level, pos); }

	public static double horizontalDistSqr(Vec3 a, Vec3 b) {
		double dx=a.x-b.x, dz=a.z-b.z; return dx*dx + dz*dz;
	}

	public static Vec3 surfaceCenter(BlockPos top) { return new Vec3(top.getX()+0.5D, top.getY()+1.0D, top.getZ()+0.5D); }

	/** Quantize an exact horizontal vector into a cardinal Direction. */
	public static Direction cardinalFrom(Vec3 exact, Direction fallback) {
		if (exact == null || exact.lengthSqr() < 1.0e-6) return fallback;
		return Math.abs(exact.x) > Math.abs(exact.z)
				? (exact.x > 0 ? Direction.EAST : Direction.WEST)
				: (exact.z > 0 ? Direction.SOUTH : Direction.NORTH);
	}
}
