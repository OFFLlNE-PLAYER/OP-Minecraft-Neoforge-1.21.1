package net.offllneplayer.opminecraft.method.crash.crates.nitro;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

import java.util.List;

public class Nitro_OnTick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);

		if (getBlockData(world, pos) < 100) {
			if (!world.isClientSide()) {
				updateBlockData(world, pos, getBlockData(world, pos) + Mth.nextInt(RandomSource.create(), 8, 26));
			}
		} else {
			if (world instanceof Level _level && !_level.isClientSide()) {
				updateBlockData(world, pos, 0);
				NitroIdleSound_Method.execute(world, x, y, z);
			}

			//Handle block replacement and entity teleportation
			if ((world.getBlockState(pos.above(1)).getBlock() == Blocks.AIR)
				&& (world.getBlockState(pos.above(2)).getBlock() == Blocks.AIR)
				&& (world.getBlockState(pos.above(3)).getBlock() == Blocks.AIR)) {
					world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					world.setBlock(pos.above(), RegistryBIBI.NITRO.get().defaultBlockState(), 3);
					teleportEntitiesAbove(world, x, y, z);
			}
		}
	}

	//Retrieve block entity data
	private static double getBlockData(LevelAccessor world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return (blockEntity != null) ? blockEntity.getPersistentData().getDouble("nitro_hop_number") : -1;
	}

	//Update block entity data and notify clients
	private static void updateBlockData(LevelAccessor world, BlockPos pos, double value) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null) {
			blockEntity.getPersistentData().putDouble("nitro_hop_number", value);
		}
		if (world instanceof Level _level) {
			_level.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}

	private static void teleportEntitiesAbove(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);
		AABB teleportBox = new AABB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1);

		// Get all entities whose bounding boxes intersect the teleportBox.
		List<Entity> nearbyEntities = world.getEntitiesOfClass(Entity.class, teleportBox, e -> true);

		for (Entity entity : nearbyEntities) {
			double newY = entity.getY() + 2.420;
			entity.teleportTo(entity.getX(), newY, entity.getZ());
			if (entity instanceof ServerPlayer player) {
				player.connection.teleport(entity.getX(), newY, entity.getZ(), entity.getYRot(), entity.getXRot());
			}
		}
	}
}
