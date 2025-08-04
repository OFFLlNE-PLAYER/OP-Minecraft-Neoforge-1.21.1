package net.offllneplayer.opminecraft.item.crash.akuaku;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryMobEffects;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class AkuAkuActivate_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;

		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
			if (_entity.level() instanceof ServerLevel _level) {

				_entity.addEffect(new MobEffectInstance(RegistryMobEffects.AKU_AKU, 400, 0, false, true));

				_level.playSound(null, BlockPos.containing(_entity.getX(), _entity.getY(), _entity.getZ()), RegistrySounds.AKU_AKU.get(), SoundSource.MASTER, 0.8f, 1);

				double yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 1, 1.69D, yOffset, 1.69D, 0.69D);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 1, -1.69D, yOffset, -1.69D, 0.69D);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 1, 1.69D, yOffset, -1.69D, 0.69D);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.CHERRY_LEAVES, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 1, -1.69D, yOffset, 1.69D, 0.69D);

				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.WHITE_ASH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 1, yOffset, 1, 0.8D);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.WHITE_ASH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, -1, yOffset, -1, 0.8D);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.WHITE_ASH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 1, yOffset, -1, 0.8D);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.WHITE_ASH, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, -1, yOffset, 1, 0.8D);

				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.POOF, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 0.69D, yOffset, 0.69D, 1);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.POOF, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, -0.69D, yOffset, -0.69D, 1);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.POOF, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, 0.69D, yOffset, -0.69D, 1);
				yOffset = Mth.nextDouble(RandomSource.create(), -1.420D,  1.420D);
				_level.sendParticles(ParticleTypes.POOF, _entity.getX(), _entity.getY() + 1, _entity.getZ(), 2, -0.69D, yOffset, 0.69D, 1);

				Item[] feathers = {RegistryBIBI.FEATHER_ORANGE.get(), RegistryBIBI.FEATHER_PINK.get(), RegistryBIBI.FEATHER_PURPLE.get(), RegistryBIBI.FEATHER_YELLOW.get()};

				for (Item feather : feathers) {
					int sendChance = Mth.nextInt(RandomSource.create(), 1, 4);
					if (sendChance == 1) {
						float xOffs = Mth.nextFloat(RandomSource.create(), 0,  2F);
						float yOffs = Mth.nextFloat(RandomSource.create(), -0.420F,  0.420F);
						float zOffs = Mth.nextFloat(RandomSource.create(), 0,  2F);
						_level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(feather)), entity.getX(), entity.getY() + 1, entity.getZ(),
								Mth.nextInt(RandomSource.create(), 1, 3), xOffs, yOffs, zOffs, 0.69D
						);
					}
				}

				for (Item feather : feathers) {
					int sendChance = Mth.nextInt(RandomSource.create(), 1, 4);
					if (sendChance == 1) {
						float xOffs = Mth.nextFloat(RandomSource.create(), -2F,  0);
						float yOffs = Mth.nextFloat(RandomSource.create(), -0.420F,  0.420F);
						float zOffs = Mth.nextFloat(RandomSource.create(), -2F,  0);
						_level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(feather)), entity.getX(), entity.getY() + 1, entity.getZ(),
								Mth.nextInt(RandomSource.create(), 1, 3), xOffs, yOffs, zOffs, 0.69D
						);
					}
				}

			ItemStack mainHandItem = _entity.getItemInHand(InteractionHand.MAIN_HAND);
			ItemStack offHandItem = _entity.getItemInHand(InteractionHand.OFF_HAND);

				if (mainHandItem.getItem() == RegistryBIBI.AKU_AKU_MASK.get()) {
					mainHandItem.setCount(mainHandItem.getCount() - 1);
					if (_entity instanceof Player _player) {
						_player.getInventory().setChanged();
					}

				} else if (offHandItem.getItem() == RegistryBIBI.AKU_AKU_MASK.get()) {
					offHandItem.setCount(offHandItem.getCount() - 1);
					if (_entity instanceof Player _player) {
						_player.getInventory().setChanged();
					}
				}
			}
		}
	}
}
