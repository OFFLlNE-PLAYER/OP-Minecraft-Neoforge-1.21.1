package net.offllneplayer.opminecraft.method.crying.essence.effect;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.offllneplayer.opminecraft.OPMinecraft;

public class Crying2_OnTick_Method {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!entity.getType().is(EntityTypeTags.UNDEAD)) {
			if ((entity instanceof Player _plr ? _plr.getFoodData().getSaturationLevel() : 0) > 0) {
				if (entity instanceof Player _player)
					_player.getFoodData().setSaturation((float) ((entity instanceof Player _plr ? _plr.getFoodData().getSaturationLevel() : 0) - 2));
			} else {
				if ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) > 0) {
					if (entity instanceof Player _player)
						_player.getFoodData().setFoodLevel((int) ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) - 2));
				} else {
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.STARVE)), 2);
				}
			}
		} else {
			if (!(entity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(MobEffects.GLOWING))) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, 1));
			}
			if (!entity.isInvisible()) {
				entity.setInvisible(true);
			} else {
				OPMinecraft.queueServerWork(60, () -> {
					entity.setInvisible(false);
				});
			}
		}
	}
}

