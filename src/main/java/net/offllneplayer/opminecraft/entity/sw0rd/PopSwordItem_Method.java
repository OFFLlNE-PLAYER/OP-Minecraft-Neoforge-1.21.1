package net.offllneplayer.opminecraft.entity.sw0rd;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.iwe.gunblade.StuckGunblade;
import net.offllneplayer.opminecraft.iwe.opsw0rd.StuckOPSword;

public class PopSwordItem_Method {
	public static boolean execute(BlockHitResult result, Level level, Entity swordEntity) {
		if (level.isClientSide()) return false;

		BlockPos hitPos = result.getBlockPos();
		BlockState blockState = level.getBlockState(hitPos);

		// Check if the block is in the sword_no_stick tag
		if (blockState.is(OP_TagKeyUtil.Blocks.SWORD_NO_STICK)) {
			boolean popSword = false;
			ItemStack blade = ItemStack.EMPTY;
			CompoundTag nbt = null;

			// Check the entity type and get the appropriate item
			if (swordEntity instanceof StuckGunblade stuckGunblade) {
				blade = stuckGunblade.getDefaultPickupItem().copy();
				nbt = stuckGunblade.getPersistentData();
				popSword = true;

			} else if (swordEntity instanceof StuckOPSword stuckOPSword) {
				blade = stuckOPSword.getDefaultPickupItem().copy();
				nbt = stuckOPSword.getPersistentData();
				popSword = true;

			} else if (swordEntity instanceof StuckSw0rd stuckSw0rd) {
				blade = stuckSw0rd.getDefaultPickupItem().copy();
				nbt = swordEntity.getPersistentData();
				popSword = true;
			}

			if (popSword) {
				// Calculate drop position based on the hit face
				Direction face = result.getDirection();
				BlockPos dropPos = hitPos.relative(face.getOpposite());

				// Apply NBT data to the itemstack
				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);

				// Create and spawn the item entity
				ItemEntity poppedSword = new ItemEntity(level, dropPos.getX() + 0.5, dropPos.getY() + 0.5, dropPos.getZ() + 0.5, blade);
				level.addFreshEntity(poppedSword);

				// Play the sound
				float tone = Mth.randomBetween(level.getRandom(), 0.8F, 1F);
				level.playSound(null, hitPos, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.69F, tone);

				// Remove the entity
				swordEntity.discard();
				return true;
			}
		}

		return false;
	}
}
