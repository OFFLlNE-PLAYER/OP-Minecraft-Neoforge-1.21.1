package net.offllneplayer.opminecraft.entity.sw0rd;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
import net.offllneplayer.opminecraft.iwe.opsw0rd.StuckOPSword;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.iwe.gunblade.StuckGunblade;

public class StickSw0rd_Method {
	public static InteractionResult execute(UseOnContext context) {

		Level level = context.getLevel();

		if (!(context.getPlayer() instanceof Player player) || level.isClientSide) return InteractionResult.PASS;
		if (player == null) return InteractionResult.PASS;

		// Setup used stack
		ItemStack stack = context.getItemInHand();
		if (stack.isEmpty()) return InteractionResult.PASS;

		boolean putsword = false;

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
		Vec3 spawnPos = Vec3.atCenterOf(hitPos).add(xPos, yPos, zPos);;
		double offset;
		float sw0rdRotation = (face == Direction.NORTH || face == Direction.EAST) ? 0F : (face == Direction.SOUTH || face == Direction.WEST) ? 180F : 0F;

		// rotation adjustment
		if (face == Direction.NORTH || face == Direction.EAST) {
			sw0rdRotation = 0F;
		} else if (face == Direction.SOUTH || face == Direction.WEST) {
			sw0rdRotation = 180F;
		}

		// not crouching, sw0rd falls
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

		} else { // if crouching stick sw0rd in block

			offset = 0.420D;

			if (face == Direction.UP || face == Direction.DOWN) {

				float playerRotation = player.getYRot() % 360F;
				if (playerRotation < 0F) playerRotation += 360F;

				sw0rdRotation = (360F - playerRotation) % 360F;

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

		if (stack.is(OP_TagKeyUtil.Items.GUNBLADES)) {

			sw0rdRotation = (sw0rdRotation + 90F) % 360F;

			StuckGunblade gun = new StuckGunblade(player, level, stack.copy());
			gun.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

			gun.setStuckFace(face);
			gun.setRenderingRotation(sw0rdRotation);
			gun.setStuckPos(hitPos);
			gun.setStuckBlock(level.getBlockState(hitPos).getBlock());

			level.addFreshEntity(gun);
			putsword = true;

		} else if (stack.is(OP_TagKeyUtil.Items.OP_SWORDS)) {

			StuckOPSword op_sword = new StuckOPSword(player, level, stack.copy());
			op_sword.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

			op_sword.setStuckFace(face);
			op_sword.setRenderingRotation(sw0rdRotation);
			op_sword.stuckPos = hitPos;
			op_sword.stuckBlock = level.getBlockState(hitPos).getBlock();

			level.addFreshEntity(op_sword);
			putsword = true;

		} else if (stack.is(OP_TagKeyUtil.Items.VANILLA_SW0RDS)) {

			StuckSw0rd sw0rd = new StuckSw0rd(player, level, stack.copy());
			sw0rd.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

			sw0rd.setStuckFace(face);
			sw0rd.setRenderingRotation(sw0rdRotation);
			sw0rd.stuckPos = hitPos;
			sw0rd.stuckBlock = level.getBlockState(hitPos).getBlock();

			level.addFreshEntity(sw0rd);
			putsword = true;
		}

		if (putsword) {

			level.playSound(null, hitPos, RegistrySounds.BLADE_SLASH.get(), SoundSource.BLOCKS, 0.69F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.1F));
			stack.shrink(1);

			return InteractionResult.SUCCESS;

		} else {
			return InteractionResult.PASS;
		}
	}
}

