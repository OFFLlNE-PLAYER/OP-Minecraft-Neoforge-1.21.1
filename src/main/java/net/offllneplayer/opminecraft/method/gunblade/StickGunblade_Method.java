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
		if (player == null) return InteractionResult.PASS;

		// Set used stack
		ItemStack stack = context.getItemInHand();
		if (stack.isEmpty()) return InteractionResult.PASS;

		// Set hit position
		BlockPos hitPos = context.getClickedPos();
		Vec3 hitLocation = context.getClickLocation();
		double xPos = hitLocation.x;
		double yPos = hitLocation.y;
		double zPos = hitLocation.z;
		Direction face = context.getClickedFace();

		// Check no-stick tag
		BlockState state = level.getBlockState(hitPos);
		if (state.is(OP_TagKeyUtil.Blocks.SWORD_NO_STICK)) return InteractionResult.PASS;

		// Setup Entity
		StuckGunblade gun = new StuckGunblade(player, level, stack.copy());
		Vec3 spawnPos = Vec3.atCenterOf(hitPos).add(xPos, yPos, zPos);;
		double offset;
		float gunRotation = (face == Direction.NORTH || face == Direction.EAST) ? 0F : (face == Direction.SOUTH || face == Direction.WEST) ? 180F : 0F;

		// rotation adjustment
		if (face == Direction.NORTH || face == Direction.EAST) {
			gunRotation = 0F;
		} else if (face == Direction.SOUTH || face == Direction.WEST) {
			gunRotation = 180F;
		}

		// not crouching, gunblade falls
		if (!(player.isCrouching() || player.isShiftKeyDown())) {
			if (face == Direction.UP || face == Direction.DOWN) return InteractionResult.PASS;

			offset = 0.1969420D;

			if (face == Direction.NORTH || face == Direction.SOUTH) {
				xPos = hitLocation.x;
				zPos = hitLocation.z + (face.getStepZ() * offset); // offset z to make it fall
				spawnPos = new Vec3(xPos, hitLocation.y, zPos);

			} else if (face == Direction.EAST || face == Direction.WEST) {
				xPos = hitLocation.x + (face.getStepX() * offset);  // offset x to make it fall
				zPos = hitLocation.z;
				spawnPos = new Vec3(xPos, hitLocation.y, zPos);
			}

		} else { // if crouching stick gunblade in block

			offset = 0.420D;

			if (face == Direction.UP || face == Direction.DOWN) {

				float playerRotation = player.getYRot() % 360F;
				if (playerRotation < 0F) playerRotation += 360F;

				gunRotation = (360F - playerRotation + 90F) % 360F;

				offset = face == Direction.UP ? 0.180420D : 0D;

				spawnPos = new Vec3(hitLocation.x, hitLocation.y + offset, hitLocation.z);

			} else if (face == Direction.NORTH || face == Direction.SOUTH) {
				xPos = hitLocation.x;
				zPos = hitPos.getZ() + 0.5D + (face.getStepZ() * offset);
				spawnPos = new Vec3(xPos, hitLocation.y, zPos);

			} else if (face == Direction.EAST || face == Direction.WEST) {
				xPos = hitPos.getX() + 0.5D + (face.getStepX() * offset);
				zPos = hitLocation.z;
				spawnPos = new Vec3(xPos, hitLocation.y, zPos);
			}
		}

		// Set entity position
		gun.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

		// Set data
		gun.setStuckFace(face);
		gun.setRenderingRotation(gunRotation);
		gun.stuckPos = hitPos;
		gun.stuckBlock = level.getBlockState(hitPos).getBlock();

		level.addFreshEntity(gun);

		// Log Output.
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

