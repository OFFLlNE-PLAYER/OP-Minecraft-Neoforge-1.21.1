package net.offllneplayer.opminecraft.method.crying;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.offllneplayer.opminecraft.init.RegistryMobEffects;

public class ApplyCrying2_Method {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(RegistryMobEffects.CRYING_II, 120, 1));
	}
}
