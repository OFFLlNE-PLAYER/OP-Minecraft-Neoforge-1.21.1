package net.offllneplayer.opminecraft.items._item.crash.akuaku;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryMobEffects;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class AkuAkuUse_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;

		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.AKU_AKU.get(), SoundSource.MASTER, 1.0F, 1.0F);

		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.CHERRY_LEAVES, x, y, z, 3, 3, 3, 3, 1);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 3, 3, 3, 3, 1);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.POOF, x, y, z, 3, 3, 3, 3, 1);

		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == RegistryBIBI.AKU_AKU_MASK.get()) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(RegistryMobEffects.AKU_AKU, 100, 1, false, true));
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == RegistryBIBI.AKU_AKU_MASK.get()) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(RegistryMobEffects.AKU_AKU, 100, 1, false, true));
		}
	}
}
