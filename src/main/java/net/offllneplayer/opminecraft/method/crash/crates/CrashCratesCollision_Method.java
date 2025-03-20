package net.offllneplayer.opminecraft.method.crash.crates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.method.crash.crates.akuaku.AkuAkuCrateBreak_Method;
import net.offllneplayer.opminecraft.method.crash.crates.crate.CrashCrateBreak_Method;
import net.offllneplayer.opminecraft.method.crash.crates.tnt.PROCPrimeTNTProcedure;

import java.util.Objects;

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

			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.CRASH_CRATE.get()) {
				entity.fallDistance = 0;
				entity.push(0, 0.8, 0);
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_bounce"))), SoundSource.MASTER, 1, 1);
				}
				world.destroyBlock(BlockPos.containing(x, y, z), false);
				OPMinecraft.queueServerWork(4, () -> {
					CrashCrateBreak_Method.execute(world, x, y, z);
				});
			} else    /*--------------------------------------------------------------------------------------------*/
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.BOUNCE_CRATE.get()) {
					entity.fallDistance = 0;
					entity.push(0, 1.3, 0);
					if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_bounce"))), SoundSource.MASTER, 1, (float) 0.9);
					}
				} else    /*--------------------------------------------------------------------------------------------*/
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.AKU_AKU_CRATE.get()) {
						entity.fallDistance = 0;
						entity.push(0, 0.8, 0);
						if ((world instanceof Level _level) && (!_level.isClientSide())) {
							_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_bounce"))), SoundSource.MASTER, 1, 1);
						}
						world.destroyBlock(BlockPos.containing(x, y, z), false);
						OPMinecraft.queueServerWork(4, () -> {
							if ((world instanceof Level _level) && (!_level.isClientSide())) {
								_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_break"))), SoundSource.MASTER, 1, 1);
							}
							AkuAkuCrateBreak_Method.execute(world, x, y, z);
						});
					} else    /*--------------------------------------------------------------------------------------------*/
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.CRASH_TNT.get()) {
							entity.fallDistance = 0;
							entity.push(0, 0.8, 0);
							if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("tntstate") instanceof IntegerProperty _getip16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip16) : -1) == 0) {
								PROCPrimeTNTProcedure.execute(world, x, y, z);
							} else {
								if ((world instanceof Level _level) && (!_level.isClientSide())) {
									_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_bounce"))), SoundSource.MASTER, 1, 1);
								}
							}
						} else    /*--------------------------------------------------------------------------------------------*/
							if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.NITRO.get()) {
								if (!entity.isShiftKeyDown()) {
									world.destroyBlock(BlockPos.containing(x, y, z), false);
									if ((world instanceof Level _level) && (!_level.isClientSide())) {
										_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_boom"))), SoundSource.MASTER, (float) 0.6, 1);
									}
									if (world instanceof Level _level && !_level.isClientSide())
										_level.explode(null, x, (y + 1), z, 2, true, Level.ExplosionInteraction.BLOCK);
								}
							}
		} else    /*--------------------------------------------------------------------------------------------*/
			if ((entity instanceof Arrow) || (entity instanceof SpectralArrow) || (entity instanceof ThrownPotion) || (entity instanceof ThrownExperienceBottle) || (entity instanceof Snowball)
					|| (entity instanceof ThrownEgg) || (entity instanceof ThrownEnderpearl)) {

				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.CRASH_TNT.get()) {
					if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("tntstate") instanceof IntegerProperty _getip16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip16) : -1) == 0) {
						PROCPrimeTNTProcedure.execute(world, x, y, z);
					}
				}else    /*--------------------------------------------------------------------------------------------*/
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == RegistryIBBI.NITRO.get()) {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
						if ((world instanceof Level _level) && (!_level.isClientSide())) {
							_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_boom"))), SoundSource.MASTER, (float) 0.6, 1);
						}
						if (world instanceof Level _level && !_level.isClientSide()) {
							_level.explode(null, x, (y + 1), z, 2, true, Level.ExplosionInteraction.BLOCK);
						}
					}
			}

	}
}

