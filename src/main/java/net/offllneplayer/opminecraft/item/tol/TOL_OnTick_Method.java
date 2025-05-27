package net.offllneplayer.opminecraft.item.tol;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class TOL_OnTick_Method {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
            _entity.setHealth(_entity.getMaxHealth());
        }
	}
}
