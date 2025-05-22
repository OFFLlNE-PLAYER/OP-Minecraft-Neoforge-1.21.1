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
		float gunRotation = 0F;

		// Get exact hit location for better positioning
		Vec3 hitLocation = context.getClickLocation();

		// use player rotation to determine entity rotation
		float playerRotation = player.getYRot() % 360F;
		if (playerRotation < 0F) playerRotation += 360F;

		// Set position based on face direction
		if (face == Direction.UP || face == Direction.DOWN) {

			// converts player rotation to gun rotation
			gunRotation = (360F - playerRotation + 90F) % 360F;

			// Use block center with vertical offset
			double offset = 0.4D;
			double xPos = face.getStepX() * offset;
			double yPos = face.getStepY() * offset;
			double zPos = face.getStepZ() * offset;
			spawnPos = Vec3.atCenterOf(hitPos).add(xPos, yPos, zPos);

		} else { // For side faces

			if (face == Direction.NORTH || face == Direction.EAST) {
				gunRotation = 0F;
			} else if (face == Direction.SOUTH || face == Direction.WEST) {
				gunRotation = 180F;
			}

			// Use hit location for Y and offset for X/Z
			double offset = 0.4D;
			double xPos = hitPos.getX() + 0.5D + (face.getStepX() * offset);
			double zPos = hitPos.getZ() + 0.5D + (face.getStepZ() * offset);
			spawnPos = new Vec3(xPos, hitLocation.y, zPos);
		}

		// Set entity position and data
		gun.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
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
		System.out.println("--------------------");
		System.out.println("Set entity stuckFace to: " + gun.getStuckFace());
		System.out.println("Set entity YRot to: " + gun.getRenderingRotation());
		System.out.println("======================");

		level.playSound(null, hitPos, RegistrySounds.BLADE_SLASH.get(), SoundSource.BLOCKS, 1F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.1F));

		stack.shrink(1);

		return InteractionResult.SUCCESS;
	}
}

