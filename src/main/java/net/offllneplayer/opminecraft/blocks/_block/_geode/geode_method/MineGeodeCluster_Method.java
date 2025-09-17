package net.offllneplayer.opminecraft.blocks._block._geode.geode_method;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelAccessor;

import net.offllneplayer.opminecraft.UTIL.SilkTouchCheck_Method;

public class MineGeodeCluster_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity, GeodeMaterial material) {
		if (entity == null) return false;

		boolean silkTouch = SilkTouchCheck_Method.hasSilkTouch(world, x, y, z, entity);
		ItemStack tool = (entity instanceof LivingEntity _liv ? _liv.getMainHandItem() : ItemStack.EMPTY);
		if (tool.isCorrectToolForDrops(world.getBlockState(BlockPos.containing(x, y, z)))) {
			if (!silkTouch) {
				int fortuneLevel = tool.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE));
				if (fortuneLevel > 0) {
					int count = Mth.nextInt(RandomSource.create(), 1, (int) (fortuneLevel + Mth.nextInt(RandomSource.create(), 0, fortuneLevel)));
					for (int i = 0; i < count; i++) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(material.shard()));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
				} else {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(material.shard()));
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
				}
			}
		}
		return silkTouch;
	}
}
