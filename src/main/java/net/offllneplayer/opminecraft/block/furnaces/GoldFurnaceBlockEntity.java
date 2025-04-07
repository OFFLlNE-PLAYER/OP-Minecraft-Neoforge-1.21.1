package net.offllneplayer.opminecraft.block.furnaces;

import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.minecraft.tags.ItemTags;

import net.minecraft.resources.ResourceLocation;

import net.offllneplayer.opminecraft.init.RegistryBlockEntities;
import net.offllneplayer.opminecraft.world.inventory.GoldFurnaceInv;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

import io.netty.buffer.Unpooled;

public class GoldFurnaceBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	private final SidedInvWrapper handler = new SidedInvWrapper(this, null);

	public GoldFurnaceBlockEntity(BlockPos position, BlockState state) {
		super(RegistryBlockEntities.GOLD_FURNACE.get(), position, state);
	}

	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.loadAdditional(compound, lookupProvider);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks, lookupProvider);
		if (compound.get("fluidTank") instanceof CompoundTag compoundTag)
			fluidTank.readFromNBT(lookupProvider, compoundTag);
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.saveAdditional(compound, lookupProvider);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks, lookupProvider);
		}
		compound.put("fluidTank", fluidTank.writeToNBT(lookupProvider, new CompoundTag()));
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
		return this.saveWithFullMetadata(lookupProvider);
	}

	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return Component.literal("gold_furnace");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new GoldFurnaceInv(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Gold Furnace");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		if (index == 0) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
		if (index == 1) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
		if (index == 2) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
		if (index == 3) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
		if (index == 4) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_fuels")));
		if (index == 5) return false;
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}
	
	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
			if (direction == Direction.UP || direction == direction.NORTH || direction == direction.SOUTH || direction == direction.EAST || direction == direction.WEST){
				if (index == 0) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
				if (index == 1) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
				if (index == 2) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
				if (index == 3) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
				if (index == 4) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_fuels")));
			}
			return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		if (index == 4) return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:empty_bucket")));
		return index == 5;
	}

	public SidedInvWrapper getItemHandler() {
		return handler;
	}

	private final FluidTank fluidTank = new FluidTank(10000) {
		@Override
		protected void onContentsChanged() {
			super.onContentsChanged();
			setChanged();
			level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
		}
	};

	public FluidTank getFluidTank() {
		return fluidTank;
	}
}
