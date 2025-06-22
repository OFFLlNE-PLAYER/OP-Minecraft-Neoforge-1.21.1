package net.offllneplayer.opminecraft.block.furnaces;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.offllneplayer.opminecraft.init.RegistryBlockEntities;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class OPFurnaceBlockEntity extends RandomizableContainerBlockEntity implements net.minecraft.world.WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	private final SidedInvWrapper handler = new SidedInvWrapper(this, null);
	private final OPFurnaceMaterial material;

	// FluidTank for the furnace
	private final FluidTank fluidTank = new FluidTank(10000) {
		@Override
		protected void onContentsChanged() {
			super.onContentsChanged();
			setChanged();
			if (level != null) {
				level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
			}
		}
	};

	public OPFurnaceBlockEntity(BlockPos position, BlockState state, OPFurnaceMaterial material) {
		super(RegistryBlockEntities.getOPFurnaceType(material), position, state);
		this.material = material;
	}

	// Getter for the material
	public OPFurnaceMaterial getFurnaceMaterial() {
		return this.material;
	}


	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.loadAdditional(compound, lookupProvider);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks, lookupProvider);

		// Load fluid tank data
		if (compound.get("fluidTank") instanceof CompoundTag compoundTag)
			fluidTank.readFromNBT(lookupProvider, compoundTag);
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.saveAdditional(compound, lookupProvider);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks, lookupProvider);
		}

		// Save fluid tank data
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
		return Component.literal(material.name().toLowerCase() + "_furnace");
	}

	@Override
	public Component getDisplayName() {
		return Component.literal(material.getDisplayName() + " Furnace");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new OPFurnaceInv(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
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
		// Slots 0-3: Smeltable items
		if (index >= 0 && index <= 3) {
			return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_smeltables")));
		}
		// Slot 4: Fuel
		if (index == 4) {
			return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:op_fuels")));
		}
		// Slot 5: Output slot - cannot place items directly
		if (index == 5) {
			return false;
		}
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		if (direction == Direction.UP || direction == Direction.NORTH ||
				direction == Direction.SOUTH || direction == Direction.EAST ||
				direction == Direction.WEST) {

			// Use the same logic as canPlaceItem
			return canPlaceItem(index, stack);
		}
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		// Can take empty buckets from fuel slot
		if (index == 4) {
			return stack.is(ItemTags.create(ResourceLocation.parse("opminecraft:empty_bucket")));
		}
		// Can take from output slot
		return index == 5;
	}

	public SidedInvWrapper getItemHandler() {
		return handler;
	}

	public FluidTank getFluidTank() {
		return fluidTank;
	}
}
