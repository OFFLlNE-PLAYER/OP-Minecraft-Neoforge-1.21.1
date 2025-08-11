package net.offllneplayer.opminecraft.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.items._item.balloon.BalloonActivate_Method;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;

import java.util.EnumSet;

/**
 * Intelligent balloon jumping goal that helps mobs navigate obstacles using balloon physics.
 */
public class GOAL_BalloonJump extends Goal {
	private final Mob mob;
	private int cooldownTicks;
	private int stuckTicks = 0;
	private int balloonActivationCooldown = 0;
	private Vec3 lastPosition;
	private static final int JUMP_COOLDOWN = 12;
	private static final int BALLOON_ACTIVATION_COOLDOWN = 20; // 1 second - consistent with player use
	private static final double STUCK_THRESHOLD = 0.02;
	private static final int MAX_STUCK_TIME = 30;

	public GOAL_BalloonJump(Mob mob) {
		this.mob = mob;
		this.setFlags(EnumSet.noneOf(Flag.class));
		this.lastPosition = mob.position();
	}

	@Override
	public boolean canUse() {
		return true;
	}

	@Override
	public boolean canContinueToUse() {
		return true;
	}

	@Override
	public void start() {
		super.start();
		cooldownTicks = 0;
		stuckTicks = 0;
		balloonActivationCooldown = 0;
		lastPosition = mob.position();
	}

	@Override
	public void stop() {
		// Never actually stop - this goal stays with the mob
	}

	@Override
	public void tick() {
		// Balloon activation logic - only when airborne
		if (holdsBalloon() && !mob.onGround()) {
			if (balloonActivationCooldown <= 0) {
				BalloonActivate_Method.execute(mob);
				balloonActivationCooldown = BALLOON_ACTIVATION_COOLDOWN;
			}
		}

		// Always count down the cooldown regardless of ground state
		if (balloonActivationCooldown > 0) {
			balloonActivationCooldown--;
		}

		// Jump logic - only if we have balloons
		if (!holdsBalloon()) {
			return;
		}

		updateStuckDetection();

		if (cooldownTicks > 0) {
			cooldownTicks--;
			return;
		}

		if (shouldJump()) {
			performJump();
			cooldownTicks = JUMP_COOLDOWN;
			stuckTicks = 0;
		}
	}


	/* ---------------------------------------- Intelligence Methods ---------------------------------------- */

	private boolean shouldJump() {
		if (!mob.onGround()) return false;

		// PRIORITY 1: Threat detection - jump away from close threats
		LivingEntity target = mob.getTarget();
		if (target != null) {
			Vec3 mobPos = mob.position();
			Vec3 targetPos = target.position();

			double horizontalDistance = Math.sqrt(
					Math.pow(targetPos.x - mobPos.x, 2) + Math.pow(targetPos.z - mobPos.z, 2)
			);

			// If target is within 3 blocks, jump with direction
			if (horizontalDistance <= 3.0) {
				BlockPos mobBlockPos = mob.blockPosition();
				Level level = mob.level();

				// Quick clearance check
				for (int i = 1; i <= 3; i++) {
					if (level.getBlockState(mobBlockPos.above(i)).isSolid()) {
						break;
					}
					if (i == 3) {
						return true; // Have clearance, will jump with threat evasion
					}
				}
			}

			// PRIORITY 2: Target-based jumping for higher targets
			double heightDiff = targetPos.y - mobPos.y;
			if (heightDiff >= 1.0 && horizontalDistance <= 2.0) {
				if (isOptimalJumpPosition(mobPos, targetPos, heightDiff)) {
					return true;
				}
			}
		}

		// PRIORITY 3: Stuck detection
		if (stuckTicks > MAX_STUCK_TIME) {
			return true; // Will jump toward target/path
		}

		// PRIORITY 4: Navigation jumping
		if (needsToJumpForNavigation()) {
			return true;
		}

		// PRIORITY 5: Obstacle detection
		if (hasJumpableObstacleAhead() || hasJumpableGapAhead()) {
			return true;
		}

		return false;
	}

	private void performJump() {
		if (!mob.level().isClientSide() && mob.onGround()) {
			Vec3 jumpDirection = calculateJumpDirection();

			mob.getJumpControl().jump();
			mob.setJumping(true);

			// Apply horizontal momentum in the calculated direction
			if (jumpDirection != null) {
				Vec3 currentMotion = mob.getDeltaMovement();
				mob.setDeltaMovement(currentMotion.x + jumpDirection.x * 0.3, currentMotion.y, currentMotion.z + jumpDirection.z * 0.3);
			}
		}
	}

	private Vec3 calculateJumpDirection() {
		LivingEntity target = mob.getTarget();
		Vec3 mobPos = mob.position();

		if (target != null) {
			Vec3 targetPos = target.position();
			double horizontalDistance = Math.sqrt(
					Math.pow(targetPos.x - mobPos.x, 2) + Math.pow(targetPos.z - mobPos.z, 2)
			);

			// Threat evasion - jump away from close targets
			if (horizontalDistance <= 3.0) {
				Vec3 awayFromThreat = mobPos.subtract(targetPos).normalize();
				return new Vec3(awayFromThreat.x, 0, awayFromThreat.z);
			}

			// Target approach - jump toward distant targets
			Vec3 towardTarget = targetPos.subtract(mobPos).normalize();
			return new Vec3(towardTarget.x, 0, towardTarget.z);
		}

		// Stuck recovery - try to move toward navigation path or random direction
		if (stuckTicks > MAX_STUCK_TIME) {
			PathNavigation navigation = mob.getNavigation();
			Path path = navigation.getPath();

			if (path != null && !path.isDone()) {
				Vec3 pathPos = path.getEntityPosAtNode(mob, Math.min(path.getNextNodeIndex() + 1, path.getNodeCount() - 1));
				if (pathPos != null) {
					Vec3 towardPath = pathPos.subtract(mobPos).normalize();
					return new Vec3(towardPath.x, 0, towardPath.z);
				}
			}

			// Random direction if no path available
			double angle = mob.getRandom().nextDouble() * Math.PI * 2;
			return new Vec3(Math.cos(angle), 0, Math.sin(angle));
		}

		// Default: jump using preferred probe direction (movement/path/look)
		Vec3 dir = getProbeDirection();
		return new Vec3(dir.x, 0, dir.z);
	}

	private boolean isOptimalJumpPosition(Vec3 mobPos, Vec3 targetPos, double heightDiff) {
		BlockPos mobBlockPos = mob.blockPosition();
		Level level = mob.level();

		// Check ceiling clearance
		int clearanceNeeded = (int)Math.ceil(heightDiff) + 1;
		for (int i = 1; i <= clearanceNeeded; i++) {
			// ensure the mob's AABB can move upward by i blocks without collision
			if (!level.noCollision(mob, mob.getBoundingBox().move(0, i, 0))) {
				return false;
			}
		}

		// Check directional alignment
		Vec3 lookDir = mob.getLookAngle();
		Vec3 toTarget = targetPos.subtract(mobPos).normalize();
		return lookDir.dot(toTarget) > 0.5;
	}

	private boolean needsToJumpForNavigation() {
		PathNavigation navigation = mob.getNavigation();
		Path path = navigation.getPath();

		if (path == null || path.isDone()) return false;

		Vec3 targetPos = path.getEntityPosAtNode(mob, Math.min(path.getNextNodeIndex() + 1, path.getNodeCount() - 1));
		if (targetPos == null) return false;

		BlockPos currentPos = mob.blockPosition();
		BlockPos targetBlockPos = BlockPos.containing(targetPos);

		int heightDiff = targetBlockPos.getY() - currentPos.getY();
		if (heightDiff >= 1 && heightDiff <= 2) {
			Level level = mob.level();

			for (int i = 1; i <= heightDiff + 1; i++) {
				// ensure vertical clearance above the mob's current position
				if (!level.noCollision(mob, mob.getBoundingBox().move(0, i, 0))) {
					return false;
				}
			}

			// landing must have a collidable surface below
			BlockPos below = targetBlockPos.below();
			return !level.getBlockState(below).getCollisionShape(level, below).isEmpty();
		}

		return false;
	}

	private boolean hasJumpableObstacleAhead() {
		BlockPos mobPos = mob.blockPosition();
		Level level = mob.level();
		Vec3 dir = getProbeDirection();

		for (int distance = 1; distance <= 2; distance++) {
			BlockPos checkPos = mobPos.offset(
					(int)Math.round(dir.x * distance),
					0,
					(int)Math.round(dir.z * distance)
			);

			BlockPos above1 = checkPos.above();
			BlockPos above2 = checkPos.above(2);

			boolean obstacleAtFeet = !level.getBlockState(checkPos).getCollisionShape(level, checkPos).isEmpty();
			boolean obstacleAtHead = !level.getBlockState(above1).getCollisionShape(level, above1).isEmpty();
			boolean spaceAbove2 = level.getBlockState(above2).getCollisionShape(level, above2).isEmpty();

			if ((obstacleAtFeet || obstacleAtHead) && spaceAbove2) {
				return true;
			}
		}

		return false;
	}

	private boolean hasJumpableGapAhead() {
		BlockPos mobPos = mob.blockPosition();
		Level level = mob.level();
		Vec3 dir = getProbeDirection();

		for (int distance = 1; distance <= 2; distance++) {
			BlockPos checkGround = mobPos.offset(
					(int)Math.round(dir.x * distance),
					-1,
					(int)Math.round(dir.z * distance)
			);

			boolean groundSolid = !level.getBlockState(checkGround).getCollisionShape(level, checkGround).isEmpty();
			if (!groundSolid) {
				for (int depth = 0; depth <= 3; depth++) {
					BlockPos below = checkGround.below(depth);
					if (!level.getBlockState(below).getCollisionShape(level, below).isEmpty()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private void updateStuckDetection() {
		if (!mob.onGround()) {
			stuckTicks = 0;
			lastPosition = mob.position();
			return;
		}

		Vec3 currentPos = mob.position();
		double distanceMoved = currentPos.distanceTo(lastPosition);
		double threshold = mob.getTarget() != null ? STUCK_THRESHOLD * 0.4 : STUCK_THRESHOLD;

		if (distanceMoved < threshold) {
			stuckTicks++;
		} else {
			stuckTicks = Math.max(0, stuckTicks - 3);
		}

		lastPosition = currentPos;
	}

	// Returns a horizontal unit vector representing the best direction to probe ahead.
	private Vec3 getProbeDirection() {
		Vec3 motion = mob.getDeltaMovement();
		if (motion.lengthSqr() > 1.0E-4) {
			Vec3 d = new Vec3(motion.x, 0, motion.z);
			if (d.lengthSqr() > 1.0E-6) return d.normalize();
		}

		PathNavigation navigation = mob.getNavigation();
		Path path = navigation.getPath();
		if (path != null && !path.isDone()) {
			Vec3 node = path.getEntityPosAtNode(mob, Math.min(path.getNextNodeIndex() + 1, path.getNodeCount() - 1));
			if (node != null) {
				Vec3 toward = node.subtract(mob.position());
				Vec3 d = new Vec3(toward.x, 0, toward.z);
				if (d.lengthSqr() > 1.0E-6) return d.normalize();
			}
		}

		Vec3 look = mob.getLookAngle();
		Vec3 d = new Vec3(look.x, 0, look.z);
		if (d.lengthSqr() > 1.0E-6) return d.normalize();

		// final fallback: don't leave zero vector
		return new Vec3(1, 0, 0);
	}

	private boolean holdsBalloon() {
		return (mob.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem) ||
				(mob.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem);
	}
}
