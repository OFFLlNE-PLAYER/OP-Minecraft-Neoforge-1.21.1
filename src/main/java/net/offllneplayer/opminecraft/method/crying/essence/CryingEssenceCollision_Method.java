
package net.offllneplayer.opminecraft.method.crying.essence;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.effect.MobEffectInstance;

import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistryMobEffects;

public class CryingEssenceCollision_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return;
		boolean Full_Set_Logic = false;

		if (entity instanceof ItemEntity) {
			CryingEssenceCollisionItem_Method.execute(world, x, y, z, entity);
		} else {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == RegistryIBBI.CRYING_HELMET.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == RegistryIBBI.CRYING_CHESTPLATE.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == RegistryIBBI.CRYING_LEGGINGS.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == RegistryIBBI.CRYING_BOOTS.get()) {
				Full_Set_Logic = true;
			}

			if (!Full_Set_Logic) {
				if ((entity instanceof LivingEntity _livEnt9) && !(_livEnt9.hasEffect(RegistryMobEffects.CRYING_II))) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
						_entity.addEffect(new MobEffectInstance(RegistryMobEffects.CRYING_II, 60, 1));
					}
				}
			}
		}
	}
}
