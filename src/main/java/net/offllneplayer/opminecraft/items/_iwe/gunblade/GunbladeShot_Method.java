package net.offllneplayer.opminecraft.items._iwe.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryParticleTypes;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class GunbladeShot_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null) return;

		OPMinecraft.queueServerWork(3, () -> {
			if (world instanceof ServerLevel level) {
				if (!level.isClientSide()) {

					DamageSource source = level.damageSources().source(RegistryDamageTypes.GUNBLADE);
					level.explode(source.getEntity(), x, y, z, 1F, false, Level.ExplosionInteraction.MOB);

					level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.GUNBLADE_SHOT.get(), SoundSource.MASTER, 1.0F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.0420F));

					entity.hurt(new DamageSource(world.holderOrThrow(RegistryDamageTypes.GUNBLADE)), Mth.nextInt(RandomSource.create(), 4, 10));

					Direction.Axis axis = sourceentity.getDirection().getAxis();

					if (axis == Direction.Axis.Z) {
						entity.push(0, 0.01, sourceentity.getZ() > entity.getZ() ? -0.0420 : 0.0420);
					} else if (axis == Direction.Axis.X) {
						entity.push(sourceentity.getX() > entity.getX() ? -0.0420 : 0.0420, 0.01, 0);
					}
			}
		}
			if (world instanceof ServerLevel _level) {
				(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(69, _level, null, _stkprov -> {});
				_level.sendParticles(RegistryParticleTypes.PARTICLE_GUNBLADE_SHOT.get(), entity.getX(), entity.getY() + 0.420F, entity.getZ(), 1, 0, 0, 0, 0);
			}
		});
	}
}
