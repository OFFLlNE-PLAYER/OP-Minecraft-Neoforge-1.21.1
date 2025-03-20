package net.offllneplayer.opminecraft.method.crash.wumpafruit;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class WumpaFruit_OnTick_Method {
	public static void execute(Entity entity) {
		if (entity == null) return;

		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {

			int hungerroll =  Mth.nextInt(RandomSource.create(), 1, 10);
			int darkroll =  Mth.nextInt(RandomSource.create(), 1, 40);
			int nightroll = Mth.nextInt(RandomSource.create(), 1, 40);
			int confusionroll =  Mth.nextInt(RandomSource.create(), 1, 20);
			int moveroll =  Mth.nextInt(RandomSource.create(), 1, 40);
			int burproll =  Mth.nextInt(RandomSource.create(), 1, 32);
			int starveroll =  Mth.nextInt(RandomSource.create(), 1, 10);

			int sideeffectduration = Mth.nextInt(RandomSource.create(), 20, 200);

			if (hungerroll == 1 && !_entity.hasEffect(MobEffects.HUNGER)) _entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 2));

			if (darkroll == 1 && !_entity.hasEffect(MobEffects.DARKNESS)) _entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, sideeffectduration, 2));
			if (nightroll == 1 && !_entity.hasEffect(MobEffects.NIGHT_VISION)) _entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, sideeffectduration, 1));
			if (confusionroll == 1 && !_entity.hasEffect(MobEffects.CONFUSION)) _entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, sideeffectduration, 2));
			if (moveroll == 1 && !_entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, sideeffectduration, 1));

			if (burproll == 1) {
				float tone = Mth.nextFloat(RandomSource.create(), 0.6f, 1.1f);
				_entity.level().playSound(null, _entity.getX(), _entity.getY(), _entity.getZ(), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.burp"))), SoundSource.MASTER, 0.7f, tone);
			}

			if (starveroll == 1 && entity instanceof Player _player) {
				if (_player.getFoodData().getFoodLevel() == 0) {
					_player.hurt(_player.damageSources().starve(), 2.0F);
				} else {
					_player.getFoodData().setFoodLevel(_player.getFoodData().getFoodLevel() - 4);
				}
			}
		}
	}
}
