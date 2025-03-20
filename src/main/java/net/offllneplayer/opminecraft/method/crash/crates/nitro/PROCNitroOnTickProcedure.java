package net.offllneplayer.opminecraft.method.crash.crates.nitro;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryIBBI;

import java.util.List;
import java.util.Comparator;

public class PROCNitroOnTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);

		if (getBlockData(world, pos, "nitro_hop_number") < 100) {
			if (!world.isClientSide()) {
				updateBlockData(world, pos, "nitro_hop_number", getBlockData(world, pos, "nitro_hop_number") + Mth.nextInt(RandomSource.create(), 8, 26));
			}
		} else {
			if (!world.isClientSide()) {
				updateBlockData(world, pos, "nitro_hop_number", 0);
			}

			// Play one of three random sounds
			if (world instanceof Level _level && !_level.isClientSide()) {
				int randomIndex = Mth.nextInt(RandomSource.create(), 1, 3);
				ResourceLocation soundId = ResourceLocation.parse("opminecraft:nitro_idle_" + randomIndex);
				_level.playSound(null, pos, BuiltInRegistries.SOUND_EVENT.get(soundId), SoundSource.MASTER, 1, 1);
			}

			// Handle block replacement and entity teleportation
			if (world.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				world.setBlock(pos.above(), RegistryIBBI.NITRO.get().defaultBlockState(), 3);
				teleportEntitiesAbove(world, x, y, z);
			}
		}
	}

	// **Helper Method: Retrieve block entity data**
	private static double getBlockData(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return (blockEntity != null) ? blockEntity.getPersistentData().getDouble(tag) : -1;
	}

	// **Helper Method: Update block entity data and notify clients**
	private static void updateBlockData(LevelAccessor world, BlockPos pos, String tag, double value) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null) {
			blockEntity.getPersistentData().putDouble(tag, value);
		}
		if (world instanceof Level _level) {
			_level.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}

	// **Helper Method: Teleport nearby entities upward**
	private static void teleportEntitiesAbove(LevelAccessor world, double x, double y, double z) {
		Vec3 center = new Vec3(x, y + 1.5, z);
		List<Entity> nearbyEntities = world.getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(0.5), e -> true)
				.stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(center))).toList();

		for (Entity entity : nearbyEntities) {
			entity.teleportTo(entity.getX(), entity.getY() + 1.1, entity.getZ());
			if (entity instanceof ServerPlayer player) {
				player.connection.teleport(entity.getX(), entity.getY() + 1.1, entity.getZ(), entity.getYRot(), entity.getXRot());
			}
		}
	}
}
