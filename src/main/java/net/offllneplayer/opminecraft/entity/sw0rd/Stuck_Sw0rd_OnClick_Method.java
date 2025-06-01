package net.offllneplayer.opminecraft.entity.sw0rd;

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

public class Stuck_Sw0rd_OnClick_Method {
	public static InteractionResult execute(Level level, Entity sw0rd, Player player, InteractionHand hand) {
		if (level.isClientSide()) return InteractionResult.PASS;

		if (player.getItemInHand(hand).isEmpty()) {
			boolean tookSword = false;


			if (sw0rd instanceof StuckGunblade stuckGunblade) {

				ItemStack blade = stuckGunblade.getDefaultPickupItem().copy();
				CompoundTag nbt = stuckGunblade.getPersistentData();

				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);
				player.setItemInHand(hand, blade);
				tookSword = true;

			} else if (sw0rd instanceof StuckOPSword stuckopsword) {

				ItemStack blade = stuckopsword.getDefaultPickupItem().copy();
				CompoundTag nbt = stuckopsword.getPersistentData();

				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);
				player.setItemInHand(hand, blade);
				tookSword = true;


			} else if (sw0rd instanceof StuckSw0rd stucksw0rd) {

				ItemStack blade = stucksw0rd.getDefaultPickupItem().copy();
				CompoundTag nbt = sw0rd.getPersistentData();

				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);
				player.setItemInHand(hand, blade);
				tookSword = true;
			}

			if (tookSword){

				float tone = Mth.randomBetween(level.getRandom(), 1.35F, 1.5F);
				level.playSound(null, sw0rd, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.6F, tone);

				player.getInventory().setChanged();
				sw0rd.discard();
				return InteractionResult.SUCCESS;

			} else {
				return InteractionResult.PASS;
			}
		} else {
			return InteractionResult.PASS;
		}
	}
}


