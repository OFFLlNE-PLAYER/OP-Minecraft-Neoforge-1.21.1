package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.iwe.Gunblade.StuckGunblade;
import net.offllneplayer.opminecraft.method.UTIL.OP_TagKeyUtil;

public class StickGunblade_Method {
	public static InteractionResult execute(UseOnContext context) {
		Level level = context.getLevel();
		if (!(context.getPlayer() instanceof Player player) || level.isClientSide) return InteractionResult.PASS;

		// Only proceed if player is shift-clicking
		if (player == null || !player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}

		// Get the item from the player's active hand
		ItemStack stack = context.getItemInHand();
		if (stack.isEmpty()) {
			return InteractionResult.PASS;
		}

		// Get hit position details
		BlockPos hitPos = context.getClickedPos();
		Direction face = context.getClickedFace();

		// Check if the block is in the no-stick tag
		BlockState state = level.getBlockState(hitPos);
		if (state.is(OP_TagKeyUtil.Blocks.SWORD_NO_STICK)) {
			return InteractionResult.PASS;
		}

		// Create the entity directly with the appropriate constructor
		StuckGunblade gun = new StuckGunblade(player, level, stack.copy());

		// Setup Entity Position
		Vec3 spawnPos;
		Direction stuckDirection = Direction.NORTH;
		float gunRotation = 270F;

		// Get exact hit location for better positioning
		Vec3 hitLocation = context.getClickLocation();

		// Set position based on face direction
		if (face == Direction.UP || face == Direction.DOWN) {
			// Use block center with vertical offset
			double offset = 0.4D;
			spawnPos = Vec3.atCenterOf(hitPos).add(
				face.getStepX() * offset,
				face.getStepY() * offset,
				face.getStepZ() * offset
			);

			// For horizontal faces, use player rotation to determine entity rotation
			float playerRotation = player.getYRot() % 360F;
			if (playerRotation < 0F) playerRotation += 360F;

			// Check for diagonal boundaries first
			boolean isAtDiagonal = false;

			// South-West diagonal (around 45°)
			if (Math.abs(playerRotation - 45F) < 5F) {
				gunRotation = 45F;  // Half way between south (90°) and west (0°)
				isAtDiagonal = true;
			}
			// North-East diagonal (around 225°)
			else if (Math.abs(playerRotation - 225F) < 5F) {
				gunRotation = 225F;  // Half way between north (270°) and east (180°)
				isAtDiagonal = true;
			}
			// North-West diagonal (around 135°)
			else if (Math.abs(playerRotation - 135F) < 5F) {
				gunRotation = 315F;  // Opposite of 135° is 315° (135° + 180°)
				isAtDiagonal = true;
			}
			// South-East diagonal (around 315°)
			else if (Math.abs(playerRotation - 315F) < 5F) {
				gunRotation = 135F;  // Opposite of 315° is 135° (315° - 180°)
				isAtDiagonal = true;
			}

			// Only process cardinal directions if not at a diagonal
			if (!isAtDiagonal) {
				if (playerRotation >= 135 && playerRotation < 225F) {
					stuckDirection = Direction.NORTH;
					gunRotation = 270F;
				} else if ((playerRotation >= 315F && playerRotation <= 360F) || (playerRotation >= 0F && playerRotation < 45F)) {
					stuckDirection = Direction.SOUTH;
					gunRotation = 90F;
				} else if (playerRotation >= 225F && playerRotation < 315F) {
					stuckDirection = Direction.EAST;
					gunRotation = 180F;
				} else if (playerRotation >= 45F && playerRotation < 135F) {
					stuckDirection = Direction.WEST;
					gunRotation = 0F;
				}
			}


		} else { // For side faces
			// Use hit location for Y and offset for X/Z
			double offset = 0.4D;
			double xPos = hitPos.getX() + 0.5D + (face.getStepX() * offset);
			double zPos = hitPos.getZ() + 0.5D + (face.getStepZ() * offset);
			spawnPos = new Vec3(xPos, hitLocation.y, zPos);

			// For vertical faces, set direction based on face
			switch (face) {
				case NORTH -> stuckDirection = Direction.NORTH;
				case SOUTH -> stuckDirection = Direction.SOUTH;
				case WEST -> stuckDirection = Direction.WEST;
				case EAST -> stuckDirection = Direction.EAST;
				default -> stuckDirection = Direction.NORTH;
			}
		}

		// Set entity position and data
		gun.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
		gun.setStuckDirection(stuckDirection);
		gun.setStuckFace(face);
		gun.setRenderingRotation(gunRotation);
		gun.stuckPos = hitPos;
		gun.stuckBlock = level.getBlockState(hitPos).getBlock();

		level.addFreshEntity(gun);

		// Log what we're setting
		System.out.println("++++++++++++++++++++++");
		System.out.println("new instance of StuckGunblade");
		System.out.println("++++++++++++++++++++++");
		System.out.println("Player Facing: " + player.getDirection());
		System.out.println("Set entity stuckDirection to: " + gun.getStuckDirection());
		System.out.println("--------------------");
		System.out.println("Set entity stuckFace to: " + gun.getStuckFace());
		System.out.println("Set entity YRot to: " + gun.getRenderingRotation());
		System.out.println("======================");

		level.playSound(null, hitPos, RegistrySounds.BLADE_SLASH.get(), SoundSource.BLOCKS, 1F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.1F));

		stack.shrink(1);

		return InteractionResult.SUCCESS;
	}
}

