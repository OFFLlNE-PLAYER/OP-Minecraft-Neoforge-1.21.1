package net.offllneplayer.opminecraft.method.furnaces;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.offllneplayer.opminecraft.method.furnaces.ontick.FurnacesAnime_Method;

public class FurnacesResetProgress_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack Stack_of_Slot_5 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_3 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_2 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_1 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_0 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_4 = ItemStack.EMPTY;
		Stack_of_Slot_0 = (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 0)).copy();
		Stack_of_Slot_1 = (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 1)).copy();
		Stack_of_Slot_2 = (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 2)).copy();
		Stack_of_Slot_3 = (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 3)).copy();
		if (Stack_of_Slot_0.getItem() == Blocks.AIR.asItem() || Stack_of_Slot_1.getItem() == Blocks.AIR.asItem() || Stack_of_Slot_2.getItem() == Blocks.AIR.asItem() || Stack_of_Slot_3.getItem() == Blocks.AIR.asItem()) {
			if (new Object() {
				public int getEnergyStored(LevelAccessor level, BlockPos pos) {
					if (level instanceof ILevelExtension _ext) {
						IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, pos, null);
						if (_entityStorage != null)
							return _entityStorage.getEnergyStored();
					}
					return 0;
				}
			}.getEnergyStored(world, BlockPos.containing(x, y, z)) > 0) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.MASTER, (float) 0.6, (float) 0.7);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.MASTER, (float) 0.6, (float) 0.7, false);
					}
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("NBT_Furnaces_Progress", 0);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			FurnacesAnime_Method.execute(world, x, y, z);
		} else if (!(Stack_of_Slot_0.getItem() == Stack_of_Slot_1.getItem()) && !(Stack_of_Slot_0.getItem() == Blocks.AIR.asItem()) && !(Stack_of_Slot_1.getItem() == Blocks.AIR.asItem())
				|| !(Stack_of_Slot_0.getItem() == Stack_of_Slot_2.getItem()) && !(Stack_of_Slot_0.getItem() == Blocks.AIR.asItem()) && !(Stack_of_Slot_2.getItem() == Blocks.AIR.asItem())
				|| !(Stack_of_Slot_0.getItem() == Stack_of_Slot_3.getItem()) && !(Stack_of_Slot_0.getItem() == Blocks.AIR.asItem()) && !(Stack_of_Slot_3.getItem() == Blocks.AIR.asItem())
				|| !(Stack_of_Slot_1.getItem() == Stack_of_Slot_2.getItem()) && !(Stack_of_Slot_1.getItem() == Blocks.AIR.asItem()) && !(Stack_of_Slot_2.getItem() == Blocks.AIR.asItem())
				|| !(Stack_of_Slot_1.getItem() == Stack_of_Slot_3.getItem()) && !(Stack_of_Slot_1.getItem() == Blocks.AIR.asItem()) && !(Stack_of_Slot_3.getItem() == Blocks.AIR.asItem())
				|| !(Stack_of_Slot_2.getItem() == Stack_of_Slot_3.getItem()) && !(Stack_of_Slot_2.getItem() == Blocks.AIR.asItem()) && !(Stack_of_Slot_3.getItem() == Blocks.AIR.asItem())) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("NBT_Furnaces_Progress", 0);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.MASTER, (float) 0.6, (float) 0.7);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.MASTER, (float) 0.6, (float) 0.7, false);
				}
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(0, _setstack);
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(1, _setstack);
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(2, _setstack);
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(3, _setstack);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, Stack_of_Slot_0);
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, Stack_of_Slot_1);
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, Stack_of_Slot_2);
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, Stack_of_Slot_3);
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
			FurnacesAnime_Method.execute(world, x, y, z);
		}
	}
}
