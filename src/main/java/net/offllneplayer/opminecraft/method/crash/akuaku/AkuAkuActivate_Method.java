package net.offllneplayer.opminecraft.method.crash.akuaku;


import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryMobEffects;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

import java.util.Objects;

public class AkuAkuActivate_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;

		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
			if (_entity.level() instanceof ServerLevel _level) {

				_entity.setInvulnerable(true);
				_entity.addEffect(new MobEffectInstance(RegistryMobEffects.AKU_AKU, 400, 1, false, true));

				_level.playSound(null, BlockPos.containing(_entity.getX(), _entity.getY(), _entity.getZ()),
						Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:aku_aku"))), SoundSource.MASTER, 0.8f, 1);

				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY(), _entity.getZ(), 3, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.WHITE_ASH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.POOF, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 2, 0, 2, 1);
			}

			ItemStack mainHandItem = _entity.getItemInHand(InteractionHand.MAIN_HAND);
			ItemStack offHandItem = _entity.getItemInHand(InteractionHand.OFF_HAND);

				if (mainHandItem.getItem() == RegistryIBBI.AKU_AKU_MASK.get()) {
					mainHandItem.setCount(mainHandItem.getCount() - 1);
					if (_entity instanceof Player _player) {
						_player.getInventory().setChanged();
					}

				} else if (offHandItem.getItem() == RegistryIBBI.AKU_AKU_MASK.get()) {
					offHandItem.setCount(offHandItem.getCount() - 1);
					if (_entity instanceof Player _player) {
						_player.getInventory().setChanged();
					}
				}
			}
	}
}
