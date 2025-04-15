package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class FallingGunbladeCollision_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ArmorStand)
				&& !(entity instanceof Arrow)
				&& !(entity instanceof DragonFireball)
				&& !(entity instanceof EvokerFangs)
				&& !(entity instanceof ThrownExperienceBottle)
				&& !(entity instanceof ExperienceOrb)
				&& !(entity instanceof HangingEntity)
				&& !(entity instanceof ItemEntity)
				&& !(entity instanceof Display.ItemDisplay)
				&& !(entity instanceof Marker)
				&& !(entity instanceof ThrownPotion)
				&& !(entity instanceof SpectralArrow)) {

			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(RegistryIBBI.FALLING_GUNBLADE.get().defaultBlockState()));
			entity.push((Mth.nextDouble(RandomSource.create(), -0.3, 0.3)), 0.01, (Mth.nextDouble(RandomSource.create(), -0.3, 0.3)));
			entity.hurt(new DamageSource(world.holderOrThrow(RegistryDamageTypes.GUNBLADE)), Mth.nextInt(RandomSource.create(), 20, 40));
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.GUNBLADE_SHOT.get(), SoundSource.MASTER, 1, Mth.nextFloat(RandomSource.create(), 0.8420F, 0.9420F));
				}
			}
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, 1, Level.ExplosionInteraction.BLOCK);
		}
	}
}
