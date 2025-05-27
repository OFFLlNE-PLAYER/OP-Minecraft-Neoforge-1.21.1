package net.offllneplayer.opminecraft.entity.sw0rd;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.iwe.gunblade.Gunblade.StuckGunblade;
import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;

public class Stuck_Sw0rd_OnClick_Method {
	public static void execute(Level level, Entity sw0rd, Player player, InteractionHand hand) {
		if (level.isClientSide()) return;

		if (player.getItemInHand(hand).isEmpty()) {
			// Play sound effect
			float tone = Mth.randomBetween(level.getRandom(), 1.35F, 1.5F);
			level.playSound(null, sw0rd, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.6F, tone);

			// Create item stack based on material (assuming gunbladeEntity is a StuckGunblade)
			if (sw0rd instanceof StuckGunblade stuckGunblade) {
				ItemStack blade = stuckGunblade.getDefaultPickupItem().copy();
				CompoundTag nbt = stuckGunblade.getPersistentData();

				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);

				// Give the item to the player
				player.setItemInHand(hand, blade);
				player.getInventory().setChanged();

				// Remove the entity
				stuckGunblade.discard();

			} else if (sw0rd instanceof StuckSw0rd stucksw0rd) {
				ItemStack blade = stucksw0rd.getDefaultPickupItem().copy();
				CompoundTag nbt = stucksw0rd.getPersistentData();

				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);

				// Give the item to the player
				player.setItemInHand(hand, blade);
				player.getInventory().setChanged();

				// Remove the entity
				stucksw0rd.discard();
			}
		}
	}
}


