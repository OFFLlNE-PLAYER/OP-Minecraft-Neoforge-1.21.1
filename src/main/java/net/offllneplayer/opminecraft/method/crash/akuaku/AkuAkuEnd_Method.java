package net.offllneplayer.opminecraft.method.crash.akuaku;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistrySounds;

public class AkuAkuEnd_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return;

		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			float tone = Mth.nextFloat(RandomSource.create(), 1F, 1.069F);
			_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), RegistrySounds.AKU_AKU_DIES.get(), SoundSource.MASTER, 0.6F, tone);
		}
	}
}
