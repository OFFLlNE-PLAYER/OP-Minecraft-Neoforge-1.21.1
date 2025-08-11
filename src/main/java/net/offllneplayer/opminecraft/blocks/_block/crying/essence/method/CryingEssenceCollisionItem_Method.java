package net.offllneplayer.opminecraft.blocks._block.crying.essence.method;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.RegistryBIBI;

import java.util.Comparator;
import java.util.List;

public class CryingEssenceCollisionItem_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return;
		double Number_of_Item = 0;

		if ((world.getBlockState(BlockPos.containing(x, y, z))).getFluidState().isSource()) {
			if ((entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem() == Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE) {
				Number_of_Item = 1;
			} else if ((entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem() == Items.GOLDEN_APPLE) {
				Number_of_Item = 2;
			}

			if (Number_of_Item == 1 || Number_of_Item == 2) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.MASTER, 1, (float) 0.2);
					entity.discard();
				}

				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);

				// Netherite Smithing Template
					if (Number_of_Item == 1) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(RegistryBIBI.CRYING_SMITHING_TEMPLATE.get()));
							entityToSpawn.setPickUpDelay(5);
							_level.addFreshEntity(entityToSpawn);
						}
						{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();

							for (Entity entityiterator : _entfound) {
								if (!(entityiterator instanceof ServerPlayer _plr10 && _plr10.level() instanceof ServerLevel
									&& _plr10.getAdvancements().getOrStartProgress(_plr10.server.getAdvancements().get(ResourceLocation.parse("opminecraft:adv_crying_smithing_template"))).isDone())) {
									if (entityiterator instanceof ServerPlayer _player) {
										AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("opminecraft:adv_crying_smithing_template"));
										if (_adv != null) {
											AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
											if (!_ap.isDone()) {
												for (String criteria : _ap.getRemainingCriteria()) {
													_player.getAdvancements().award(_adv, criteria);
												}
											}
										}
									}
								}
							}
						}
					} else // Golden Apple
						if (Number_of_Item == 2) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
							entityToSpawn.setPickUpDelay(5);
							_level.addFreshEntity(entityToSpawn);
						}
						{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();

							for (Entity entityiterator : _entfound) {
								if (!(entityiterator instanceof ServerPlayer _plr14 && _plr14.level() instanceof ServerLevel
									&& _plr14.getAdvancements().getOrStartProgress(_plr14.server.getAdvancements().get(ResourceLocation.parse("opminecraft:adv_golden_apple"))).isDone())) {
										if (entityiterator instanceof ServerPlayer _player) {
											AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("opminecraft:adv_golden_apple"));
											if (_adv != null) {
												AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
												if (!_ap.isDone()) {
													for (String criteria : _ap.getRemainingCriteria()) {
															_player.getAdvancements().award(_adv, criteria);
													}
												}
											}
										}
									}
								}
							}
						}
			}
		}
	}
}
