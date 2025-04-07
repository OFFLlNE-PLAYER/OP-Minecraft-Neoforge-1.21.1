package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryParticleTypes;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.Objects;

public class PROCPrototypeGunbladeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:gunblade_in_dirt"))), SoundSource.MASTER, (float) 0.4,
						(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1));
			}
		}
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == RegistryIBBI.PROTOTYPE_GUNBLADE.get()) {
			OPMinecraft.queueServerWork(3, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:gunblade_shot"))), SoundSource.MASTER, 1, (float) Mth.nextDouble(RandomSource.create(), 1.1, 1.2));
					}
				}
				world.addParticle((SimpleParticleType) (RegistryParticleTypes.PARTICLE_GUNBLADE_SHOT.get()), x, (y + 1), z, 0, 0, 0);
			});
			OPMinecraft.queueServerWork(4, () -> {
				entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.PLAYER_EXPLOSION)), Mth.nextInt(RandomSource.create(), 2, 7));
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
