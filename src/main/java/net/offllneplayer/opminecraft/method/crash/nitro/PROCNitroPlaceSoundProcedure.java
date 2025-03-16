package net.offllneplayer.opminecraft.method.crash.nitro;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class PROCNitroPlaceSoundProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double random_noise_number = 0;
		random_noise_number = Mth.nextInt(RandomSource.create(), 1, 3);
		if (random_noise_number == 1) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_1")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_1")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
		if (random_noise_number == 2) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_2")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_2")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
		if (random_noise_number == 3) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_3")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_idle_3")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}
