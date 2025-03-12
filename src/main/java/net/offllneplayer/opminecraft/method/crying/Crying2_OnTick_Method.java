package net.offllneplayer.opminecraft.method.crying;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

public class Crying2_OnTick_Method {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plr ? _plr.getFoodData().getSaturationLevel() : 0) > 0) {
            Player _player = (Player) entity;
            Player _plr = (Player) entity;
            _player.getFoodData().setSaturation((float) (_plr.getFoodData().getSaturationLevel() - 2));
		} else {
			if ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) > 0) {
                Player _player = (Player) entity;
                Player _plr = (Player) entity;
                _player.getFoodData().setFoodLevel((int) (_plr.getFoodData().getFoodLevel() - 2));
			} else {
				entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.STARVE)), 2);
			}
		}
	}
}
