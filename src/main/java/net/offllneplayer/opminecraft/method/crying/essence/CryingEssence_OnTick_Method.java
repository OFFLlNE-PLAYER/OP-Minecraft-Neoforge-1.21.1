package net.offllneplayer.opminecraft.method.crying.essence;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.DeclareTagKeys;

import java.util.Objects;

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

			if (Get_Block_x_plus_1.is(DeclareTagKeys.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_x_minus_1.is(DeclareTagKeys.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_y_plus_1.is(DeclareTagKeys.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_y_minus_1.is(DeclareTagKeys.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_z_plus_1.is(DeclareTagKeys.Blocks.CRYING_OBSIDIAN_TRIGGERS) ||
					Get_Block_z_minus_1.is(DeclareTagKeys.Blocks.CRYING_OBSIDIAN_TRIGGERS)) {

				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
					_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_explode"))), SoundSource.MASTER, (float)1, (float) 1);
				}
				OPMinecraft.queueServerWork(20, () -> {

					if (world instanceof ServerLevel _level) {
						_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 3, 3, 3, 1);
					}

					if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.deepslate.step"))), SoundSource.MASTER, 1, (float) 0.8);
						_level.explode(null, x, y, z, 5, true, Level.ExplosionInteraction.BLOCK);

						world.setBlock(BlockPos.containing(x, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
					}
				});
			} else if (Get_Block_x_plus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_x_minus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_y_plus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_y_minus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_z_plus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_z_minus_1.is(DeclareTagKeys.Blocks.WATERS)) {

				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.BUDDING_AMETHYST.defaultBlockState(), 3);
					_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_explode"))), SoundSource.MASTER, (float) 1, (float) 1);
				}

				OPMinecraft.queueServerWork(20, () -> {

					if (world instanceof ServerLevel _level) {
						_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 3, 3, 3, 1);
					}

					if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.chime"))), SoundSource.MASTER, 1, (float) 0.8);

						_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.BLOCK);

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

		} else if (!Get_Block_X_Y_Z.getFluidState().isSource()) {

			if (Get_Block_x_plus_1.is(Blocks.LAVA)
					|| Get_Block_x_minus_1.is(Blocks.LAVA)
					|| Get_Block_y_plus_1.is(Blocks.LAVA)
					|| Get_Block_y_minus_1.is(Blocks.LAVA)
					|| Get_Block_z_plus_1.is(Blocks.LAVA)
					|| Get_Block_z_minus_1.is(Blocks.LAVA)) {

				Flowing_Collision_Happened = true;

				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.lava.extinguish"))), SoundSource.MASTER, 1, 1);
				}

				if (Get_Block_x_plus_1.is(Blocks.LAVA)) {
					if (Get_Block_x_plus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.NETHERRACK.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_x_minus_1.is(Blocks.LAVA)) {
					if (Get_Block_x_minus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.NETHERRACK.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_y_plus_1.is(Blocks.LAVA)) {
					if (Get_Block_y_plus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.NETHERRACK.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_y_minus_1.is(Blocks.LAVA)) {
					if (Get_Block_y_minus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.NETHERRACK.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_z_plus_1.is(Blocks.LAVA)) {
					if (Get_Block_z_plus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.NETHERRACK.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.DEEPSLATE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_z_minus_1.is(Blocks.LAVA)) {
					if (Get_Block_z_minus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.NETHERRACK.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.DEEPSLATE.defaultBlockState(), 3);
					}
				}
			}
			if (Get_Block_x_plus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_x_minus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_y_plus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_y_minus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_z_plus_1.is(DeclareTagKeys.Blocks.WATERS) ||
					Get_Block_z_minus_1.is(DeclareTagKeys.Blocks.WATERS)) {

				Flowing_Collision_Happened = true;

				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.wet_grass.break"))), SoundSource.MASTER, (float) 0.6, (float) 1.5);
				}

				if (Get_Block_x_plus_1.is(DeclareTagKeys.Blocks.WATERS)) {
					if (Get_Block_x_plus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.ICE.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.CALCITE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_x_minus_1.is(DeclareTagKeys.Blocks.WATERS)) {
					if (Get_Block_x_minus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.ICE.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.CALCITE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_y_plus_1.is(DeclareTagKeys.Blocks.WATERS)) {
					if (Get_Block_y_plus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.ICE.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.CALCITE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_y_minus_1.is(DeclareTagKeys.Blocks.WATERS)) {
					if (Get_Block_y_minus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.ICE.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.CALCITE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_z_plus_1.is(DeclareTagKeys.Blocks.WATERS)) {
					if (Get_Block_z_plus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.ICE.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.CALCITE.defaultBlockState(), 3);
					}
				}
				if (Get_Block_z_minus_1.is(DeclareTagKeys.Blocks.WATERS)) {
					if (Get_Block_z_minus_1.getFluidState().isSource()) {
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.ICE.defaultBlockState(), 3);
					} else {
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.CALCITE.defaultBlockState(), 3);
					}
				}
			}
			if (Get_Block_x_plus_1.is(DeclareTagKeys.Blocks.ICES) ||
					Get_Block_x_minus_1.is(DeclareTagKeys.Blocks.ICES) ||
					Get_Block_y_plus_1.is(DeclareTagKeys.Blocks.ICES) ||
					Get_Block_y_minus_1.is(DeclareTagKeys.Blocks.ICES) ||
					Get_Block_z_plus_1.is(DeclareTagKeys.Blocks.ICES) ||
					Get_Block_z_minus_1.is(DeclareTagKeys.Blocks.ICES)) {

				Flowing_Collision_Happened = true;

				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.resonate"))), SoundSource.MASTER, (float) 0.6, (float) 1.3);
				}

				if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(DeclareTagKeys.Blocks.ICES)) {
					world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
				if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(DeclareTagKeys.Blocks.ICES)) {
					world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(DeclareTagKeys.Blocks.ICES)) {
					world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(DeclareTagKeys.Blocks.ICES)) {
					world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
				if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(DeclareTagKeys.Blocks.ICES)) {
					world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
				if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(DeclareTagKeys.Blocks.ICES)) {
					world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
				}
			}

			if (Flowing_Collision_Happened) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 4, 6, 4, 1);
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}

		}
	}
}
