package net.offllneplayer.opminecraft.method.crying.armor;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class FullCryingDrip_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double Crying_Block_Random_Int = 0;
		Crying_Block_Random_Int = Mth.nextInt(RandomSource.create(), 1, 100);
		if (Crying_Block_Random_Int == 1) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0, 0.79)), (z + Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 2) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0, 0.79)), (z - Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 3) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x - Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0, 0.79)), (z + Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 4) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x - Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0, 0.79)), (z - Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 5) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0.8, 1.6)), (z + Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 6) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x + Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0.8, 1.6)), (z - Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 7) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x - Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0.8, 1.6)), (z + Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
		if (Crying_Block_Random_Int == 8) {
			world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (x - Mth.nextDouble(RandomSource.create(), 0, 0.4)), (y + Mth.nextDouble(RandomSource.create(), 0.8, 1.6)), (z - Mth.nextDouble(RandomSource.create(), 0, 0.4)), 0,
					(Mth.nextInt(RandomSource.create(), 7, 9)), 0);
		}
	}
}
