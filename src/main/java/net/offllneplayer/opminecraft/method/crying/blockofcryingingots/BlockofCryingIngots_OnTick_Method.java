package net.offllneplayer.opminecraft.method.crying.blockofcryingingots;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.DeclareTagKeys;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

import java.util.List;
import java.util.Comparator;
import java.util.Objects;

public class BlockofCryingIngots_OnTick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(DeclareTagKeys.Blocks.SOUL_FIRE)
				|| (world.getBlockState(BlockPos.containing(x + 1, y, z))).is(DeclareTagKeys.Blocks.SOUL_FIRE)
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(DeclareTagKeys.Blocks.SOUL_FIRE)
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(DeclareTagKeys.Blocks.SOUL_FIRE)
				|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).is(DeclareTagKeys.Blocks.SOUL_FIRE)
				|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).is(DeclareTagKeys.Blocks.SOUL_FIRE)) {

			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_explode"))), SoundSource.MASTER, (float) 1, (float) 1);
			}

			if (world instanceof ServerLevel _level) {
				_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 3, 3, 3, 1);
			}

			OPMinecraft.queueServerWork(20, () -> {

				if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == RegistryIBBI.BLOCK_OF_CRYING_INGOTS.get()) {

					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator instanceof ServerPlayer _plr14 && _plr14.level() instanceof ServerLevel
								&& _plr14.getAdvancements().getOrStartProgress(Objects.requireNonNull(_plr14.server.getAdvancements().get(ResourceLocation.parse("opminecraft:adv_melt_block_of_crying_ingots")))).isDone())) {
							if (entityiterator instanceof ServerPlayer _player) {
								AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("opminecraft:adv_melt_block_of_crying_ingots"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						}
					}

				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_furnace"))), SoundSource.NEUTRAL, (float)0.8, (float) 0.2);
					_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.underwater.exit"))), SoundSource.NEUTRAL, 1, (float) 0.2);
					_level.explode(null, x, y, z, 1, Level.ExplosionInteraction.BLOCK);
				}

				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
					entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
					_level.addFreshEntity(entityToSpawn);
				}

				for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 0, 2); index0++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
						entityToSpawn.setPickUpDelay(5);
						_level.addFreshEntity(entityToSpawn);
					}
				}

				world.setBlock(BlockPos.containing(x, y, z), RegistryIBBI.CRYING_ESSENCE.get().defaultBlockState(), 3);
				}
			});

		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.LAVA
				|| (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.LAVA
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.LAVA
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.LAVA
				|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.LAVA
				|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.LAVA) {

			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_explode"))), SoundSource.MASTER, (float) 1, (float) 1);
			}

			if (world instanceof ServerLevel _level) {
				_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 3, 3, 3, 1);
			}

			OPMinecraft.queueServerWork(20, () -> {

				if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == RegistryIBBI.BLOCK_OF_CRYING_INGOTS.get()) {

					if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.deepslate.step"))), SoundSource.MASTER, 1, (float) 0.8);
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.underwater.exit"))), SoundSource.NEUTRAL, 1, (float) 0.2);
						_level.explode(null, x, y, z, 5, true, Level.ExplosionInteraction.BLOCK);
					}

					if (world instanceof ServerLevel _level) {
						LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
						entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
						;
						_level.addFreshEntity(entityToSpawn);
					}

					for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 0, 2); index0++) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
							entityToSpawn.setPickUpDelay(5);
							_level.addFreshEntity(entityToSpawn);
						}
					}

				world.setBlock(BlockPos.containing(x, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
				}
		});

		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.WATER) {

			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_explode"))), SoundSource.MASTER, (float) 1, (float) 1);
			}

			if (world instanceof ServerLevel _level) {
					_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 3, 3, 3, 1);
			}

			OPMinecraft.queueServerWork(20, () -> {

				if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == RegistryIBBI.BLOCK_OF_CRYING_INGOTS.get()) {

					if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.chime"))), SoundSource.MASTER, 1, (float) 0.8);
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.underwater.exit"))), SoundSource.NEUTRAL, 1, (float) 0.2);
						_level.explode(null, x, y, z, 3, Level.ExplosionInteraction.BLOCK);
					}

					if (world instanceof ServerLevel _level) {
						LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
						entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
						_level.addFreshEntity(entityToSpawn);
					}

					for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 0, 2); index0++) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
							entityToSpawn.setPickUpDelay(5);
							_level.addFreshEntity(entityToSpawn);
						}
					}

				world.setBlock(BlockPos.containing(x, y, z), Blocks.BUDDING_AMETHYST.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.AMETHYST_CLUSTER.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
			});
		}
	}
}

