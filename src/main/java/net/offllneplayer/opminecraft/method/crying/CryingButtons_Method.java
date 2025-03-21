package net.offllneplayer.opminecraft.method.crying;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import java.util.Objects;

public class CryingButtons_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_furnace"))), SoundSource.MASTER, (float) 0.8, (float) 0.4);
			}
		}
	}
}
