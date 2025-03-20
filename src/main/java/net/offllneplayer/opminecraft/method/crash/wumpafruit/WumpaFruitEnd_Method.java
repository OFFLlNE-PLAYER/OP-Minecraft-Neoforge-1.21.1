package net.offllneplayer.opminecraft.method.crash.wumpafruit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Objects;

public class WumpaFruitEnd_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()){
			int durationroll = Mth.nextInt(RandomSource.create(), 3, 10);
			_entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, durationroll, 1));
	}
		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			float tone = Mth.nextFloat(RandomSource.create(), 0.7f, 1.05f);
			_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.burp"))), SoundSource.MASTER, (float) 0.6, tone);
		}
	}
}
