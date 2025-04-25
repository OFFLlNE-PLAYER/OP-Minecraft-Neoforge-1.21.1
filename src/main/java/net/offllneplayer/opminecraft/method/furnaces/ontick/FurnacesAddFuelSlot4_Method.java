package net.offllneplayer.opminecraft.method.furnaces.ontick;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

public class FurnacesAddFuelSlot4_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack Stack_of_Slot_4 = ItemStack.EMPTY;
		Stack_of_Slot_4 = (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				if (world instanceof ILevelExtension _ext) {
					IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
					if (_itemHandler != null)
						return _itemHandler.getStackInSlot(slotid).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 4)).copy();
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9000) {
			if (Stack_of_Slot_4.getItem() == Items.LAVA_BUCKET) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.NEUTRAL, 1, 1);

				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 1000), IFluidHandler.FluidAction.EXECUTE);
				}
				OPMinecraft.queueServerWork(1, () -> {
					if ((world instanceof Level _level) && (!_level.isClientSide())) {
							_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.9, 1);
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
						_setstack.setCount(1);
						_itemHandlerModifiable.setStackInSlot(4, _setstack);
					}
				});
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.getItem() == Blocks.COAL_BLOCK.asItem()) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.8, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 700), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.getItem() == RegistryIBBI.BLOCK_OF_CHARCOAL.asItem()) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.8, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 700), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.getItem() == Blocks.DRIED_KELP_BLOCK.asItem()) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.7, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 420), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.getItem() == Items.BLAZE_ROD) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.6, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 200), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.getItem() == Items.COAL || Stack_of_Slot_4.getItem() == Items.CHARCOAL) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.5, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 75), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.is(ItemTags.create(ResourceLocation.parse("minecraft:logs")))) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.4, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 50), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.is(ItemTags.create(ResourceLocation.parse("minecraft:planks")))) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.4, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 15), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.is(ItemTags.create(ResourceLocation.parse("minecraft:wooden_slabs")))) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.4, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 10), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
		if (new Object() {
			public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
				if (level instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
					if (_fluidHandler != null)
						return _fluidHandler.getFluidInTank(tank).getAmount();
				}
				return 0;
			}
		}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) <= 9900) {
			if (Stack_of_Slot_4.getItem() == Items.STICK) {
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.NEUTRAL, (float) 0.3, 1);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 4;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 2), IFluidHandler.FluidAction.EXECUTE);
				}
			}
		}
	}
}
