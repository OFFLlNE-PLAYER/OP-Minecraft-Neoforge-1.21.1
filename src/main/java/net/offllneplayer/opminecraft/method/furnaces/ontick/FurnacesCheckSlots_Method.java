package net.offllneplayer.opminecraft.method.furnaces.ontick;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryBIBI;

import java.util.Objects;

public class FurnacesCheckSlots_Method {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		ItemStack Stack_of_Slot_5 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_3 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_2 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_1 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_0 = ItemStack.EMPTY;
		ItemStack Stack_of_Slot_4 = ItemStack.EMPTY;
		double Number_of_Item = 0;
		double Number_of_Slot_5 = 0;
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
		Stack_of_Slot_5 = (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 5)).copy();
		Number_of_Item = 0;
		Number_of_Slot_5 = new Object() {
			public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).getCount();
				}
				return 0;
			}
		}.getAmount(world, BlockPos.containing(x, y, z), 5);
		if (Stack_of_Slot_0.getItem() == Blocks.COBBLESTONE.asItem() && (Stack_of_Slot_5.getItem() == RegistryBIBI.DENSE_STONE.get().asItem() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			Number_of_Item = 1;

		} else if (Stack_of_Slot_0.getItem() == Blocks.NETHERRACK.asItem() && (Stack_of_Slot_5.getItem() == Blocks.NETHER_BRICKS.asItem() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			Number_of_Item = 2;

		} else if (Stack_of_Slot_0.getItem() == Items.RAW_COPPER || Stack_of_Slot_0.getItem() == Items.COPPER_ORE || Stack_of_Slot_0.getItem() == Items.DEEPSLATE_COPPER_ORE
				&& (Stack_of_Slot_5.getItem() == Blocks.COPPER_BLOCK.asItem() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			Number_of_Item = 3;

		} else if (Stack_of_Slot_0.getItem() == Items.RAW_IRON || Stack_of_Slot_0.getItem() == Items.IRON_ORE || Stack_of_Slot_0.getItem() == Items.DEEPSLATE_IRON_ORE
				&& (Stack_of_Slot_5.getItem() == Blocks.IRON_BLOCK.asItem() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			Number_of_Item = 4;

		} else if (Stack_of_Slot_0.getItem() == Items.RAW_GOLD || Stack_of_Slot_0.getItem() == Items.GOLD_ORE || Stack_of_Slot_0.getItem() == Items.DEEPSLATE_GOLD_ORE || Stack_of_Slot_0.getItem() == Items.NETHER_GOLD_ORE
				&& (Stack_of_Slot_5.getItem() == Blocks.GOLD_BLOCK.asItem() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			Number_of_Item = 5;

		}  else if (Stack_of_Slot_0.getItem() == Items.ANCIENT_DEBRIS && (Stack_of_Slot_5.getItem() == RegistryBIBI.ANCIENT_CHUNK.get() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			Number_of_Item = 6;

		} else if (Stack_of_Slot_0.getItem() == Blocks.CRYING_OBSIDIAN.asItem() && (Stack_of_Slot_5.getItem() == RegistryBIBI.CRYING_INGOT.get() && Number_of_Slot_5 < 64 || Stack_of_Slot_5.getItem() == Blocks.AIR.asItem())) {
			if ((world instanceof Level _level) && (!_level.isClientSide())) {
				_level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crying_furnace"))), SoundSource.MASTER, (float) 1, (float) 0.8);
			}
			Number_of_Item = 7;
		}
		return Number_of_Item;
	}
}
