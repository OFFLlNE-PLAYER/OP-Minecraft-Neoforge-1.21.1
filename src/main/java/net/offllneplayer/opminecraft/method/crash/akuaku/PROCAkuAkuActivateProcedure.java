package net.offllneplayer.opminecraft.method.crash.akuaku;


import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
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

public class PROCAkuAkuActivateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
			// Get the item in the main hand
			ItemStack mainHandItem = _entity.getItemInHand(InteractionHand.MAIN_HAND);
			// Get the item in the off hand
			ItemStack offHandItem = _entity.getItemInHand(InteractionHand.OFF_HAND);

			// Initialize variable to track if inventory needs updating
			boolean updateInventory = false;

			// Set health and invulnerable state
			_entity.setHealth(_entity.getMaxHealth());
			_entity.setInvulnerable(true);

			// Play sound on the server
			if (_entity.level() instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(_entity.getX(), _entity.getY(), _entity.getZ()),
							BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:aku_aku")), SoundSource.MASTER, 0.8f, 1);
				}
			}

			// Play particles (on server side only)
			if (_entity.level() instanceof ServerLevel _level) {
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY(), _entity.getZ(), 3, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.WHITE_ASH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
				_level.sendParticles(ParticleTypes.POOF, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 2, 0, 2, 1);
			}

			// Check if Aku Aku Mask is in the main hand
			if (mainHandItem.getItem() == RegistryIBBI.AKU_AKU_MASK.get()) {
				// Decrease stack count in main hand
				mainHandItem.setCount(mainHandItem.getCount() - 1);

				// Apply the Aku Aku effect
				_entity.addEffect(new MobEffectInstance(RegistryMobEffects.AKU_AKU, 140, 1, false, true));

				// Mark for inventory update
				updateInventory = true;
			}
			// Check if Aku Aku Mask is in the off hand (only if it's not in the main hand)
			else if (offHandItem.getItem() == RegistryIBBI.AKU_AKU_MASK.get()) {
				// Decrease stack count in off hand
				offHandItem.setCount(offHandItem.getCount() - 1);

				// Apply the Aku Aku effect
				_entity.addEffect(new MobEffectInstance(RegistryMobEffects.AKU_AKU, 140, 1, false, true));

				// Mark for inventory update
				updateInventory = true;
			}

			// If it's a player, update the inventory
			if (updateInventory && _entity instanceof Player _player) {
				_player.getInventory().setChanged();
			}
		}
	}
}
