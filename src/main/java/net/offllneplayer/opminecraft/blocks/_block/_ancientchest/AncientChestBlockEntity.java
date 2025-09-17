package net.offllneplayer.opminecraft.blocks._block._ancientchest;

import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

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

import net.offllneplayer.opminecraft.init.RegistryBlockEntities;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

import io.netty.buffer.Unpooled;

public class AncientChestBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*[DATA]*/
	private final AncientChestTrimMaterial trimMaterial;
	private NonNullList<ItemStack> invSlotCount;
	private final SidedInvWrapper handler = new SidedInvWrapper(this, null);

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*[BUILDERS]*/
	public AncientChestBlockEntity(BlockPos pos, BlockState state, AncientChestTrimMaterial trimMaterial) {
		super(RegistryBlockEntities.getLootChestType(trimMaterial), pos, state);
		this.trimMaterial = trimMaterial;
		this.invSlotCount = NonNullList.withSize(trimMaterial.getSlots(), ItemStack.EMPTY);
	}


	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/

	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*[DATA SYNC]*/
	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.loadAdditional(compound, lookupProvider);

		// If inventory needs resizing or initialization
		if (!this.tryLoadLootTable(compound)) {
			// Make sure inventory is properly sized for this trim material
			if (this.invSlotCount == null || this.invSlotCount.size() != this.trimMaterial.getSlots()) {
				this.invSlotCount = NonNullList.withSize(this.trimMaterial.getSlots(), ItemStack.EMPTY);
			}
		}

		ContainerHelper.loadAllItems(compound, this.invSlotCount, lookupProvider);
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.saveAdditional(compound, lookupProvider);

		// Save trim material type for reference
		compound.putString("trim_material", this.trimMaterial.name());

		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.invSlotCount, lookupProvider);
		}
	}



	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
		return this.saveWithFullMetadata(lookupProvider);
	}

	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[CONTAINER METHODS]*/
	@Override
	public int getContainerSize() {
		return invSlotCount.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.invSlotCount)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return Component.literal("ancient_chest");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		FriendlyByteBuf extraData = new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition);
		return new AncientChestInv(id, inventory, extraData);
	}


	@Override
	public Component getDisplayName() {
		return Component.literal("Loot Chest");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.invSlotCount;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.invSlotCount = stacks;
	}

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
	/*[ITEM HANDLING]*/
	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return this.canPlaceItem(index, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	public SidedInvWrapper getItemHandler() {
		return handler;
	}
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
}
