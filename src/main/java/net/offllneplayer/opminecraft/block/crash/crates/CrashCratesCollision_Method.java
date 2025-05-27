package net.offllneplayer.opminecraft.block.crash.crates;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.block.crash.crates.akuaku.AkuAkuCrateBreak_Method;
import net.offllneplayer.opminecraft.block.crash.crates.crate.CrashCrateBreak_Method;
import net.offllneplayer.opminecraft.block.crash.crates.nitro.NitroBoom_Method;
import net.offllneplayer.opminecraft.block.crash.crates.crashtnt.CrashTNTPrime_Method;

public class CrashCratesCollision_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;
		if (!(entity instanceof ArmorStand)
		&& !(entity instanceof Arrow)
		&& !(entity instanceof EvokerFangs)
		&& !(entity instanceof ThrownExperienceBottle)
		&& !(entity instanceof ExperienceOrb)
		&& !(entity instanceof HangingEntity)
		&& !(entity instanceof ItemEntity)
		&& !(entity instanceof Display.ItemDisplay)
		&& !(entity instanceof Marker)
		&& !(entity instanceof ThrownPotion)
		&& !(entity instanceof Snowball)
		&& !(entity instanceof ThrownEgg)
		&& !(entity instanceof ThrownEnderpearl)
		&& !(entity instanceof SpectralArrow)) {

			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.CRASH_CRATE.get()) {
				entity.fallDistance = 0;
				entity.push(0, 0.8, 0);
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRASH_CRATE_BOUNCE.get(), SoundSource.MASTER, 1.0F, 1.0F);
				}
				world.destroyBlock(BlockPos.containing(x, y, z), false);
				OPMinecraft.queueServerWork(4, () -> {
					CrashCrateBreak_Method.execute(world, x, y, z);
				});
			} else    /*--------------------------------------------------------------------------------------------*/
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.BOUNCE_CRATE.get()) {
					entity.fallDistance = 0;
					entity.push(0, 1.3, 0);
					if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRASH_CRATE_BOUNCE.get(), SoundSource.MASTER, 1.0F, 1.0F);
					}
				} else    /*--------------------------------------------------------------------------------------------*/
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.AKU_AKU_CRATE.get()) {
						entity.fallDistance = 0;
						entity.push(0, 0.8, 0);
						if ((world instanceof Level _level) && (!_level.isClientSide())) {
							_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRASH_CRATE_BOUNCE.get(), SoundSource.MASTER, 1.0F, 1.0F);
						}
						world.destroyBlock(BlockPos.containing(x, y, z), false);
						OPMinecraft.queueServerWork(4, () -> {
							if ((world instanceof Level _level) && (!_level.isClientSide())) {
								_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRASH_CRATE_BREAK.get(), SoundSource.MASTER, 1.0F, 1.0F);
							}
							AkuAkuCrateBreak_Method.execute(world, x, y, z);
						});
				} else    /*--------------------------------------------------------------------------------------------*/
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.CRASH_TNT.get()) {
						entity.fallDistance = 0;
						entity.push(0, 0.8, 0);

						if (((world.getBlockState(BlockPos.containing(x, y, z)))
						.getBlock().getStateDefinition().getProperty("tntstate")
						instanceof IntegerProperty _getip16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip16) : -1) == 0) {

							CrashTNTPrime_Method.execute(world, x, y, z);
						} else {
								if ((world instanceof Level _level) && (!_level.isClientSide())) {
									_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRASH_CRATE_BOUNCE.get(), SoundSource.MASTER, 1.0F, 1.0F);
								}
							}
						} else    /*--------------------------------------------------------------------------------------------*/
							if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.NITRO.get()) {
								if (!entity.isShiftKeyDown()) {
									world.destroyBlock(BlockPos.containing(x, y, z), false);
									if ((world instanceof Level _level) && (!_level.isClientSide())) {
										NitroBoom_Method.execute(world, x, y, z);
									}
								}
							}
		} else    /*--------------------------------------------------------------------------------------------*/
			if ((entity instanceof Arrow)
					|| (entity instanceof SpectralArrow)
					|| (entity instanceof ThrownPotion)
					|| (entity instanceof ThrownExperienceBottle)
					|| (entity instanceof Snowball)
					|| (entity instanceof ThrownEgg)
					|| (entity instanceof ThrownEnderpearl)) {

				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.CRASH_TNT.get()) {
					if (((world.getBlockState(BlockPos.containing(x, y, z)))
					.getBlock().getStateDefinition().getProperty("tntstate")
					instanceof IntegerProperty _getip16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip16) : -1) == 0) {

						CrashTNTPrime_Method.execute(world, x, y, z);
					}
				}else    /*--------------------------------------------------------------------------------------------*/
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryBIBI.NITRO.get()) {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
						if ((world instanceof Level _level) && (!_level.isClientSide())) {
							NitroBoom_Method.execute(world, x, y, z);
				}
			}
		}
	}
}
