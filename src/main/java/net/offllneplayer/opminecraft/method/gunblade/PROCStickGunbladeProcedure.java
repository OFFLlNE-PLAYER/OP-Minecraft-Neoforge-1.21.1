package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

public class PROCStickGunbladeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState Get_Block_At_x_y_z_Before_Tick = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_yplus1_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_yminus1_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_y_zminus1 = Blocks.AIR.defaultBlockState();
		BlockState Block_At_xminus1_y_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_xplus1_y_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_y_zplus1 = Blocks.AIR.defaultBlockState();
		Direction Block_Face_Direction = Direction.NORTH;
		boolean sword_is_getting_stuck_logic = false;
		String which_hand = "";
		double Damage_to_apply = 0;
		double special_location_of_gunblade = 0;
		Get_Block_At_x_y_z_Before_Tick = (world.getBlockState(BlockPos.containing(x, y, z)));
		Block_At_xplus1_y_z = (world.getBlockState(BlockPos.containing(x + 1, y, z)));
		Block_At_xminus1_y_z = (world.getBlockState(BlockPos.containing(x - 1, y, z)));
		Block_At_x_yplus1_z = (world.getBlockState(BlockPos.containing(x, y + 1, z)));
		Block_At_x_yminus1_z = (world.getBlockState(BlockPos.containing(x, y - 1, z)));
		Block_At_x_y_zplus1 = (world.getBlockState(BlockPos.containing(x, y, z + 1)));
		Block_At_x_y_zminus1 = (world.getBlockState(BlockPos.containing(x, y, z - 1)));
		Block_Face_Direction = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection();
		if (!Get_Block_At_x_y_z_Before_Tick.is(BlockTags.create(ResourceLocation.parse("opminecraft:sword_no_stick")))) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == RegistryIBBI.GUNBLADE.get()) {
				Damage_to_apply = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDamageValue();
				which_hand = "main";
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == RegistryIBBI.GUNBLADE.get()) {
				which_hand = "off";
				Damage_to_apply = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getDamageValue();
			}
			if (Block_Face_Direction == Direction.EAST) {
				if (Block_At_xplus1_y_z.is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable"))) && (world.getBlockState(BlockPos.containing(x + 2, y, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
					{
						BlockPos _bp = BlockPos.containing(x + 1, y, z);
						BlockState _bs = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						Direction _dir = Direction.EAST;
						BlockPos _pos = BlockPos.containing(x + 1, y, z);
						BlockState _bs = world.getBlockState(_pos);
						Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
							world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
						} else {
							_property = _bs.getBlock().getStateDefinition().getProperty("axis");
							if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
								world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
						}
					}
					if (Block_At_xplus1_y_z.getBlock() == Blocks.WATER || Block_At_xplus1_y_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp24 && Block_At_xplus1_y_z.getValue(_getbp24)) {
						{
							BlockPos _pos = BlockPos.containing(x + 1, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x + 1, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("DMG_VALU", Damage_to_apply);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					sword_is_getting_stuck_logic = true;
					special_location_of_gunblade = x + 1;
					if ((which_hand).equals("main")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else if ((which_hand).equals("off")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			} else if (Block_Face_Direction == Direction.WEST) {
				if (Block_At_xminus1_y_z.is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable"))) && (world.getBlockState(BlockPos.containing(x - 2, y, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
					{
						BlockPos _bp = BlockPos.containing(x - 1, y, z);
						BlockState _bs = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						Direction _dir = Direction.WEST;
						BlockPos _pos = BlockPos.containing(x - 1, y, z);
						BlockState _bs = world.getBlockState(_pos);
						Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
							world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
						} else {
							_property = _bs.getBlock().getStateDefinition().getProperty("axis");
							if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
								world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
						}
					}
					if (Block_At_xminus1_y_z.getBlock() == Blocks.WATER || Block_At_xminus1_y_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp154 && Block_At_xminus1_y_z.getValue(_getbp154)) {
						{
							BlockPos _pos = BlockPos.containing(x - 1, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x - 1, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("DMG_VALU", Damage_to_apply);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					sword_is_getting_stuck_logic = true;
					special_location_of_gunblade = x - 1;
					if ((which_hand).equals("main")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else if ((which_hand).equals("off")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(special_location_of_gunblade, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			} else if (Block_Face_Direction == Direction.UP) {
				if (Block_At_x_yplus1_z.is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable"))) && (world.getBlockState(BlockPos.containing(x, y + 2, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
					{
						BlockPos _bp = BlockPos.containing(x, y + 1, z);
						BlockState _bs = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						Direction _dir = Direction.UP;
						BlockPos _pos = BlockPos.containing(x, y + 1, z);
						BlockState _bs = world.getBlockState(_pos);
						Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
							world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
						} else {
							_property = _bs.getBlock().getStateDefinition().getProperty("axis");
							if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
								world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
						}
					}
					if (Block_At_x_yplus1_z.getBlock() == Blocks.WATER || Block_At_x_yplus1_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp284 && Block_At_x_yplus1_z.getValue(_getbp284)) {
						{
							BlockPos _pos = BlockPos.containing(x, y + 1, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y + 1, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("DMG_VALU", Damage_to_apply);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					sword_is_getting_stuck_logic = true;
					special_location_of_gunblade = y + 1;
					if ((which_hand).equals("main")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else if ((which_hand).equals("off")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			} else if (Block_Face_Direction == Direction.DOWN) {
				if (Block_At_x_yminus1_z.is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable"))) && (world.getBlockState(BlockPos.containing(x, y - 2, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
					{
						BlockPos _bp = BlockPos.containing(x, y - 1, z);
						BlockState _bs = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						Direction _dir = Direction.DOWN;
						BlockPos _pos = BlockPos.containing(x, y - 1, z);
						BlockState _bs = world.getBlockState(_pos);
						Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
							world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
						} else {
							_property = _bs.getBlock().getStateDefinition().getProperty("axis");
							if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
								world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
						}
					}
					if (Block_At_x_yminus1_z.getBlock() == Blocks.WATER || Block_At_x_yminus1_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp414 && Block_At_x_yminus1_z.getValue(_getbp414)) {
						{
							BlockPos _pos = BlockPos.containing(x, y - 1, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y - 1, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("DMG_VALU", Damage_to_apply);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					sword_is_getting_stuck_logic = true;
					special_location_of_gunblade = y - 1;
					if ((which_hand).equals("main")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else if ((which_hand).equals("off")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, special_location_of_gunblade, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			} else if (Block_Face_Direction == Direction.SOUTH) {
				if (Block_At_x_y_zplus1.is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable"))) && (world.getBlockState(BlockPos.containing(x, y, z + 2))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z + 1);
						BlockState _bs = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						Direction _dir = Direction.SOUTH;
						BlockPos _pos = BlockPos.containing(x, y, z + 1);
						BlockState _bs = world.getBlockState(_pos);
						Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
							world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
						} else {
							_property = _bs.getBlock().getStateDefinition().getProperty("axis");
							if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
								world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
						}
					}
					if (Block_At_x_y_zplus1.getBlock() == Blocks.WATER || Block_At_x_y_zplus1.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp544 && Block_At_x_y_zplus1.getValue(_getbp544)) {
						{
							BlockPos _pos = BlockPos.containing(x, y, z + 1);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z + 1);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("DMG_VALU", Damage_to_apply);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					sword_is_getting_stuck_logic = true;
					special_location_of_gunblade = z + 1;
					if ((which_hand).equals("main")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else if ((which_hand).equals("off")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			} else if (Block_Face_Direction == Direction.NORTH) {
				if (Block_At_x_y_zminus1.is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable"))) && (world.getBlockState(BlockPos.containing(x, y, z - 2))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z - 1);
						BlockState _bs = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						Direction _dir = Direction.NORTH;
						BlockPos _pos = BlockPos.containing(x, y, z - 1);
						BlockState _bs = world.getBlockState(_pos);
						Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
							world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
						} else {
							_property = _bs.getBlock().getStateDefinition().getProperty("axis");
							if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
								world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
						}
					}
					if (Block_At_x_y_zminus1.getBlock() == Blocks.WATER || Block_At_x_y_zminus1.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp674 && Block_At_x_y_zminus1.getValue(_getbp674)) {
						{
							BlockPos _pos = BlockPos.containing(x, y, z - 1);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z - 1);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("DMG_VALU", Damage_to_apply);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					sword_is_getting_stuck_logic = true;
					special_location_of_gunblade = z - 1;
					if ((which_hand).equals("main")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else if ((which_hand).equals("off")) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("bane", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("smiite", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sharp", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.UNBREAKING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("unbreakin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("lootin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("sweepin", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("firey", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("knickerbocker", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi",
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("mendi", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE)) != 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
											.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.VANISHING_CURSE))));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, special_location_of_gunblade);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("vanish", 0);
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			}
			if (sword_is_getting_stuck_logic) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:gunblade_in_dirt")), SoundSource.MASTER, (float) 0.3,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1));
					}
				}
				if ((which_hand).equals("main")) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				} else if ((which_hand).equals("off")) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
			}
		}
	}
}
