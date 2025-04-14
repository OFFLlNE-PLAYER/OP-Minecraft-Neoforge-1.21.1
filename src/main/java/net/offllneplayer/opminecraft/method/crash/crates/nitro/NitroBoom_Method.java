package net.offllneplayer.opminecraft.method.crash.crates.nitro;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import net.offllneplayer.opminecraft.init.RegistrySounds;

public class NitroBoom_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.destroyBlock(BlockPos.containing(x, y, z), false);
		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.NITRO_BOOM.get(), SoundSource.MASTER, 0.5F, 1F);
			_level.explode(null, x, (y + 0.5), z, 2, Level.ExplosionInteraction.BLOCK);
		}
	}
}
