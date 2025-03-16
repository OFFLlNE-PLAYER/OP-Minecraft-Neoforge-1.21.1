package net.offllneplayer.opminecraft.method.totemoflife;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class TOL_OnTick_Method {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
	}
}
