package net.offllneplayer.opminecraft.method.crying.essence;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.method.util.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public class CryingEssence_OnTick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {

		boolean Flowing_Collision_Happened = false;

		BlockState Get_Block_X_Y_Z = (world.getBlockState(BlockPos.containing(x, y, z)));
		BlockState Get_Block_x_plus_1 = (world.getBlockState(BlockPos.containing(x + 1, y, z)));
		BlockState Get_Block_x_minus_1 = (world.getBlockState(BlockPos.containing(x - 1, y, z)));
		BlockState Get_Block_y_plus_1 = (world.getBlockState(BlockPos.containing(x, y + 1, z)));
		BlockState Get_Block_y_minus_1 = (world.getBlockState(BlockPos.containing(x, y - 1, z)));
		BlockState Get_Block_z_plus_1 = (world.getBlockState(BlockPos.containing(x, y, z + 1)));
		BlockState Get_Block_z_minus_1 = (world.getBlockState(BlockPos.containing(x, y, z - 1)));


		if (Get_Block_X_Y_Z.getFluidState().isSource()) {

			if (Get_Block_x_plus_1.is(OP_TagKeyUtil.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_x_minus_1.is(OP_TagKeyUtil.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_y_plus_1.is(OP_TagKeyUtil.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_y_minus_1.is(OP_TagKeyUtil.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_z_plus_1.is(OP_TagKeyUtil.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_z_minus_1.is(OP_TagKeyUtil.Blocks.CRYING_OBSIDIAN_TRIGGERS)) {

				if ((world instanceof ServerLevel _level) && (!_level.isClientSide())) {

					_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRYING_EXPLODE.get(), SoundSource.MASTER, 1.0F, 1.0F);
					_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 1, 1, 1, 1);

					world.setBlock(BlockPos.containing(x, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);

					OPMinecraft.queueServerWork(10, () -> {

						LightningBolt lightningEntity = EntityType.LIGHTNING_BOLT.create(_level);
						lightningEntity.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
						_level.addFreshEntity(lightningEntity);
					});

					OPMinecraft.queueServerWork(20, () -> {

						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.DEEPSLATE_STEP, SoundSource.BLOCKS, 1.0F, 0.8F);

						DamageSource source = _level.damageSources().source(RegistryDamageTypes.CRYING_ESSENCE);
						_level.explode(source.getEntity(), x, y, z, 5.0f, true, Level.ExplosionInteraction.BLOCK);

						ItemEntity resinEntity = new ItemEntity(_level, x, y + 1, z, new ItemStack(RegistryIBBI.CRYING_RESIN.get()));
						resinEntity.setPickUpDelay(5);
						_level.addFreshEntity(resinEntity);

						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
					});
				}
			} else if (Get_Block_x_plus_1.is(OP_TagKeyUtil.Blocks.WATERS) ||
					Get_Block_x_minus_1.is(OP_TagKeyUtil.Blocks.WATERS) ||
					Get_Block_y_plus_1.is(OP_TagKeyUtil.Blocks.WATERS) ||
					Get_Block_y_minus_1.is(OP_TagKeyUtil.Blocks.WATERS) ||
					Get_Block_z_plus_1.is(OP_TagKeyUtil.Blocks.WATERS) ||
					Get_Block_z_minus_1.is(OP_TagKeyUtil.Blocks.WATERS)) {

				if ((world instanceof ServerLevel _level) && (!_level.isClientSide())) {

					_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRYING_EXPLODE.get(), SoundSource.MASTER, 1.0F, 1.0F);
					_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 1, 1, 1, 1);

					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);

					OPMinecraft.queueServerWork(10, () -> {

						LightningBolt lightningEntity = EntityType.LIGHTNING_BOLT.create(_level);
						lightningEntity.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
						_level.addFreshEntity(lightningEntity);
					});

					OPMinecraft.queueServerWork(20, () -> {

						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.8F);

						DamageSource source = _level.damageSources().source(RegistryDamageTypes.CRYING_ESSENCE);
						_level.explode(source.getEntity(), x, y, z, 3.0f, false, Level.ExplosionInteraction.BLOCK);

						ItemEntity resinEntity = new ItemEntity(_level, x, y + 1, z, new ItemStack(RegistryIBBI.CRYING_RESIN.get()));
						resinEntity.setPickUpDelay(5);
						_level.addFreshEntity(resinEntity);

						world.setBlock(BlockPos.containing(x, y, z), Blocks.BUDDING_AMETHYST.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.AMETHYST_CLUSTER.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
					});
				}
			}
		} else {

			BlockPos pos = BlockPos.containing(x, y, z);

			for (Direction dir : Direction.values()) {
				BlockPos np = pos.relative(dir);
				BlockState blockState = world.getBlockState(np);

				if ((blockState.is(Blocks.LAVA)) || (blockState.is(OP_TagKeyUtil.Blocks.WATERS)) || (blockState.is(OP_TagKeyUtil.Blocks.ICES))) {

					Flowing_Collision_Happened = true;
					double xOffset = Mth.nextDouble(RandomSource.create(), -0.2D, 0.2D);
					double yOffset = Mth.nextDouble(RandomSource.create(), -0.2D, 0.2D);
					double zOffset = Mth.nextDouble(RandomSource.create(), -0.2D, 0.2D);

					int howmanyParticles = Mth.nextInt(RandomSource.create(), 2, 6);

				if (blockState.is(Blocks.LAVA)) {

					if (world instanceof Level _level && !_level.isClientSide()) {
						_level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
					}
					if (world instanceof ServerLevel _slevel && !_slevel.isClientSide()) {
						_slevel.sendParticles(ParticleTypes.SMOKE, x, y, z, howmanyParticles, xOffset, yOffset, zOffset, 0.2);
					}

					world.setBlock(np, blockState.getFluidState().isSource() ? Blocks.NETHERRACK.defaultBlockState() : Blocks.DEEPSLATE.defaultBlockState(), 3);


				} else if (blockState.is(OP_TagKeyUtil.Blocks.WATERS)) {

					if (world instanceof Level _level && !_level.isClientSide()) {
						_level.playSound(null, pos, SoundEvents.DEEPSLATE_STEP, SoundSource.BLOCKS, 0.8F, 1.420F);
					}

					if (blockState.getFluidState().isSource()) {
						if (world instanceof ServerLevel _slevel && !_slevel.isClientSide()) {
							_slevel.sendParticles(ParticleTypes.SPIT, x, y, z, howmanyParticles, xOffset, yOffset, zOffset, 0.2);
						}
					} else {
						if (world instanceof ServerLevel _slevel && !_slevel.isClientSide()) {
							_slevel.sendParticles(ParticleTypes.DRIPPING_WATER, x, y, z, howmanyParticles, xOffset, yOffset, zOffset, 0.2);
						}
					}

					world.setBlock(np, blockState.getFluidState().isSource() ? Blocks.CALCITE.defaultBlockState() : Blocks.ICE.defaultBlockState(), 3);


				} else if (blockState.is(OP_TagKeyUtil.Blocks.ICES)) {

					if (world instanceof Level _level && !_level.isClientSide()) {
						_level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 0.69F, 1.269F);
					}
					if (world instanceof ServerLevel _slevel && !_slevel.isClientSide()) {
						_slevel.sendParticles(ParticleTypes.DRIPPING_WATER, x, y, z, howmanyParticles, xOffset, yOffset, zOffset, 0.2);
					}

					world.setBlock(np, Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
			}
				if (Flowing_Collision_Happened) {
					if (world instanceof ServerLevel _slevel && !_slevel.isClientSide()) {

						_slevel.sendParticles(ParticleTypes.LANDING_OBSIDIAN_TEAR, x, y, z, Mth.nextInt(RandomSource.create(), 2, 4),
								Mth.nextDouble(RandomSource.create(), -0.2D, 0.2D),
								Mth.nextDouble(RandomSource.create(), -0.2D, 0D),
								Mth.nextDouble(RandomSource.create(), -0.2D, 0.2D), 0.2);
					}

					world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				}
			}
		}
	}
}