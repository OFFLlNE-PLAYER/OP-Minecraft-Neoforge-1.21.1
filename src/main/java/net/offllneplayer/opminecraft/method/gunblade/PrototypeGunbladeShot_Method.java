package net.offllneplayer.opminecraft.method.gunblade;

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
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistryParticleTypes;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class PrototypeGunbladeShot_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.GUNBLADE_IN_DIRT.get(), SoundSource.MASTER, 0.3F, Mth.nextFloat(RandomSource.create(), 0.8F, 1.1F));
			}
		}
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == RegistryIBBI.PROTOTYPE_GUNBLADE.get()) {
			OPMinecraft.queueServerWork(3, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.GUNBLADE_SHOT.get(), SoundSource.MASTER, 1, Mth.nextFloat(RandomSource.create(), 0.7420F, 0.9420F));
					}
				}
				world.addParticle((RegistryParticleTypes.PARTICLE_GUNBLADE_SHOT.get()), x, (y + 1), z, 0, 0, 0);
			});
			OPMinecraft.queueServerWork(4, () -> {
				entity.hurt(new DamageSource(world.holderOrThrow(RegistryDamageTypes.GUNBLADE)), Mth.nextInt(RandomSource.create(), 2, 7));
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.Z) {
					entity.push(0, 0.01, (-0.05));
				}
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.Z) {
					entity.push(0, 0.01, 0.05);
				}
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.X) {
					entity.push(0.05, 0.01, 0);
				}
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.X) {
					entity.push((-0.05), 0.01, 0);
				}
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, x, (y + 1), z, 1, Level.ExplosionInteraction.MOB);
				if (world instanceof ServerLevel _level) {
					(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
					});
				}
			});
		}
	}
}
