package net.offllneplayer.opminecraft.item.tou;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryMobEffects;

public class TOUActivate_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;

		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
			if (_entity.level() instanceof ServerLevel _level) {
				if (_entity instanceof Player _player) {
					FoodData foodData = _player.getFoodData();
					if (foodData.getFoodLevel() <= 0) {
						
						// Set hunger to max
						foodData.setFoodLevel(20);
						foodData.setSaturation(20.0F);
						
						// Add effect
						_entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 360, 2));
						_entity.addEffect(new MobEffectInstance(RegistryMobEffects.TOTEM_OF_UNCRYING, 360, 1, false, true));
						
						// Play sound
						_level.playSound(null, BlockPos.containing(_entity.getX(), _entity.getY(), _entity.getZ()), SoundEvents.TOTEM_USE, SoundSource.MASTER, 0.420F, 1.1420F);
						
						// Create purple particles
						_level.sendParticles(ParticleTypes.PORTAL, _entity.getX(), _entity.getY(), _entity.getZ(), 3, 2, 0, 2, 1);
						_level.sendParticles(ParticleTypes.WITCH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
						_level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 5, 2, 0, 2, 1);
						_level.sendParticles(ParticleTypes.DRAGON_BREATH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 2, 0, 2, 1);
						
						// Consume the totem
						ItemStack mainHandItem = _entity.getItemInHand(InteractionHand.MAIN_HAND);
						ItemStack offHandItem = _entity.getItemInHand(InteractionHand.OFF_HAND);
						
						if (mainHandItem.getItem() == RegistryBIBI.TOTEM_OF_UNCRYING.get()) {
							mainHandItem.setCount(mainHandItem.getCount() - 1);
							_player.getInventory().setChanged();
						} else if (offHandItem.getItem() == RegistryBIBI.TOTEM_OF_UNCRYING.get()) {
							offHandItem.setCount(offHandItem.getCount() - 1);
							_player.getInventory().setChanged();
						}
					}
				}
			}
		}
	}
}