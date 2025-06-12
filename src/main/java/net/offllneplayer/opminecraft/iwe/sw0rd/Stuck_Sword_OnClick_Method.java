package net.offllneplayer.opminecraft.iwe.sw0rd;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.iwe.gunblade.StuckGunblade;
import net.offllneplayer.opminecraft.iwe.opsw0rd.StuckOPSword;

public class Stuck_Sword_OnClick_Method {
	public static InteractionResult execute(Level level, Entity swordEntity, Player player, InteractionHand hand) {
		if (level.isClientSide()) return InteractionResult.PASS;

		if (player.getItemInHand(hand).isEmpty()) {
			boolean tookSword = false;
			ItemStack blade = ItemStack.EMPTY;
			CompoundTag nbt = null;

			if (swordEntity instanceof StuckGunblade stuckGunblade) {
				blade = stuckGunblade.getDefaultPickupItem().copy();
				nbt = stuckGunblade.getPersistentData();
				tookSword = true;

			} else if (swordEntity instanceof StuckOPSword stuckopsword) {
				 blade = stuckopsword.getDefaultPickupItem().copy();
				 nbt = stuckopsword.getPersistentData();
				tookSword = true;


			} else if (swordEntity instanceof StuckSw0rd stucksw0rd) {
				blade = stucksw0rd.getDefaultPickupItem().copy();
				nbt = stucksw0rd.getPersistentData();
				tookSword = true;
			}

			if (tookSword){
				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);
				player.setItemInHand(hand, blade);
				player.getInventory().setChanged();

				swordEntity.discard();

				float tone = Mth.randomBetween(level.getRandom(), 1.35F, 1.5F);
				level.playSound(null, swordEntity, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.6F, tone);

				return InteractionResult.SUCCESS;

			} else {
				return InteractionResult.PASS;
			}
		} else {
			return InteractionResult.PASS;
		}
	}
}


