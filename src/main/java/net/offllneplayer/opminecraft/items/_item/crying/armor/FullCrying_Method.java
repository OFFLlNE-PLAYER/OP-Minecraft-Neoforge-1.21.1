package net.offllneplayer.opminecraft.items._item.crying.armor;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.offllneplayer.opminecraft.init.RegistryBIBI;

public class FullCrying_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean Full_Set_Logic = false;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == RegistryBIBI.CRYING_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == RegistryBIBI.CRYING_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == RegistryBIBI.CRYING_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == RegistryBIBI.CRYING_BOOTS.get()) {
			Full_Set_Logic = true;
		} else {
			Full_Set_Logic = false;
		}
		if (Full_Set_Logic) {
			FullCryingDrip_Method.execute(world, x, y, z);
		}
	}
}
