package net.offllneplayer.opminecraft.method.crash.akuaku;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class PROCAkuAkuOnTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.setInvulnerable(true);
	}
}
