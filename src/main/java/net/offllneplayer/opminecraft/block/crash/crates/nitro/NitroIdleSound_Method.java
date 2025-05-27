package net.offllneplayer.opminecraft.block.crash.crates.nitro;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistrySounds;

public class NitroIdleSound_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level && !_level.isClientSide()) {
			_level.playSound(null, BlockPos.containing(x, y, z), new SoundEvent[]
					{RegistrySounds.NITRO_IDLE_1.get(), RegistrySounds.NITRO_IDLE_2.get(), RegistrySounds.NITRO_IDLE_3.get()}
					[Mth.nextInt(RandomSource.create(), 0, 2)], SoundSource.MASTER, 1.0F, 1.0F);
		}
	}
}
