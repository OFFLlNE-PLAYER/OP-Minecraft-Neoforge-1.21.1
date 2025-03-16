package net.offllneplayer.opminecraft.method.crying.blockofcryingingots;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class BlockOfCryingIngots_OnCLIENTTick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		int driproll = Mth.nextInt(RandomSource.create(), 1, 16);
		if (driproll == 1) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, x, (y + (Mth.nextDouble(RandomSource.create(), 0, 1))), (z + (Mth.nextDouble(RandomSource.create(), 0, 1))), 0, (Mth.nextInt(RandomSource.create(), 3, 5)), 0);
		}
		if (driproll == 2) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + (Mth.nextDouble(RandomSource.create(), 0, 1))), (y + (Mth.nextDouble(RandomSource.create(), 0, 1))), z, 0, (Mth.nextInt(RandomSource.create(), 3, 5)), 0);
		}
		if (driproll == 3) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + 1), (y + (Mth.nextDouble(RandomSource.create(), 0, 1))), (z + (Mth.nextDouble(RandomSource.create(), 0, 1))), 0, (Mth.nextInt(RandomSource.create(), 3, 5)), 0);
		}
		if (driproll == 4) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + (Mth.nextDouble(RandomSource.create(), 0, 1))), (y + (Mth.nextDouble(RandomSource.create(), 0, 1))), (z + 1), 0, (Mth.nextInt(RandomSource.create(), 3, 5)), 0);
		}
	}
}
