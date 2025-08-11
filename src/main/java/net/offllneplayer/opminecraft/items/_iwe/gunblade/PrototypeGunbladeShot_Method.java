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
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryParticleTypes;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class PrototypeGunbladeShot_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.3F, Mth.nextFloat(RandomSource.create(), 0.8F, 1F));
			}
		}
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == RegistryBIBI.PROTOTYPE_GUNBLADE.get()) {

			OPMinecraft.queueServerWork(4, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.GUNBLADE_SHOT.get(), SoundSource.MASTER, 1, Mth.nextFloat(RandomSource.create(), 0.70420F, 0.80420F));
					}
				}
				entity.hurt(new DamageSource(world.holderOrThrow(RegistryDamageTypes.GUNBLADE)), Mth.nextInt(RandomSource.create(), 2, 7));
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.Z) {
					entity.push(0, 0.01, (-0.02));
				}
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.Z) {
					entity.push(0, 0.01, 0.02);
				}
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.X) {
					entity.push(0.02, 0.01, 0);
				}
				if ((sourceentity.getDirection()).getAxis() == Direction.Axis.X) {
					entity.push((-0.02), 0.01, 0);
				}
				if (world instanceof ServerLevel _level) {
					(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {});
					_level.sendParticles(RegistryParticleTypes.PARTICLE_GUNBLADE_SHOT.get(), entity.getX(), entity.getY() + 0.420F, entity.getZ(), 1, 0, 0, 0, 0);
				}
			});
		}
	}
}
