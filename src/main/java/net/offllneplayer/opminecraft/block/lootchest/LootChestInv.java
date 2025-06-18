
package net.offllneplayer.opminecraft.block.lootchest;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import net.offllneplayer.opminecraft.init.RegistryInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LootChestInv extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;

	private String woodType = "mangrove";
	private String trimType = "netherite";

	/**
	 * Extracts the wood type from the registry path
	 */
	private String extractWoodType(String regPath) {
		// Method implementation unchanged
		if (regPath.contains("acacia")) {
			return "acacia";
		} else if (regPath.contains("bamboo")) {
			return "bamboo";
		} else if (regPath.contains("birch")) {
			return "birch";
		} else if (regPath.contains("cherry")) {
			return "cherry";
		} else if (regPath.contains("dark_oak")) {
			return "dark_oak";
		} else if (regPath.contains("jungle")) {
			return "jungle";
		} else if (regPath.contains("mangrove")) {
			return "mangrove";
		} else if (regPath.contains("oak")) {
			return "oak";
		} else if (regPath.contains("spruce")) {
			return "spruce";
		}
		// Nether wood types at the end
		else if (regPath.contains("crimson")) {
			return "crimson";
		} else if (regPath.contains("warped")) {
			return "warped";
		} else {
			return "mangrove"; // Default to oak if no match
		}
	}

	/**
	 * Extracts the trim type from the registry path
	 */
	private String extractTrimType(String regPath) {
		// Method implementation unchanged
		if (regPath.contains("copper_trim")) {
			return "copper";
		} else if (regPath.contains("iron_trim")) {
			return "iron";
		} else if (regPath.contains("gold_trim")) {
			return "gold";
		} else if (regPath.contains("diamond_trim")) {
			return "diamond";
		} else if (regPath.contains("netherite_trim")) {
			return "netherite";
		} else if (regPath.contains("amethyst_trim")) {
			return "amethyst";
		} else if (regPath.contains("emerald_trim")) {
			return "emerald";
		} else if (regPath.contains("lapis_trim")) {
			return "lapis";
		} else if (regPath.contains("quartz_trim")) {
			return "quartz";
		} else if (regPath.contains("redstone_trim")) {
			return "redstone";
		} else {
			return "netherite"; // Default to copper if no match
		}
	}

	/**
	 * Gets the wood type for this chest
	 */
	public String getWoodType() {
		return this.woodType;
	}

	/**
	 * Gets the trim type for this chest
	 */
	public String getTrimType() {
		return this.trimType;
	}

	public LootChestInv(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(RegistryInventory.N_LOOT_CHEST_GUI.get(), id);
		this.entity = inv.player;
		this.world = inv.player.level();

		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
			access = ContainerLevelAccess.create(world, pos);

			// Extract wood and trim type from the block's registry path
			Block block = world.getBlockState(pos).getBlock();
			ResourceLocation regName = BuiltInRegistries.BLOCK.getKey(block);
			String regPath = regName.getPath();
			this.woodType = extractWoodType(regPath);
			this.trimType = extractTrimType(regPath);
		}

		if (pos != null) {
			boundBlockEntity = this.world.getBlockEntity(pos);
			if (boundBlockEntity instanceof LootChestBlockEntity lootChest) {
				// Use the inventory from the block entity
				this.internal = new InvWrapper(lootChest);
				this.bound = true;

			} else {
				// Fallback if block entity not found or not the right type
				int inventorySize = getInventorySize();
				this.internal = new ItemStackHandler(inventorySize);
			}
		} else {
			// No position provided, just create a default inventory
			int inventorySize = getInventorySize();
			this.internal = new ItemStackHandler(inventorySize);
		}

		// Handle binding to item/entity if needed
		if (extraData != null && extraData.readableBytes() > 0) {
			if (extraData.readableBytes() == 1) { // bound to item
				byte hand = extraData.readByte();
				ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
				this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());
				IItemHandler cap = itemstack.getCapability(Capabilities.ItemHandler.ITEM);
				if (cap != null) {
					this.internal = cap;
					this.bound = true;
				}
			} else if (extraData.readableBytes() > 1) { // bound to entity
				extraData.readByte(); // drop padding
				boundEntity = world.getEntity(extraData.readVarInt());
				if (boundEntity != null) {
					IItemHandler cap = boundEntity.getCapability(Capabilities.ItemHandler.ENTITY);
					if (cap != null) {
						this.internal = cap;
						this.bound = true;
					}
				}
			}
		}

		// Initialize slots based on trim type
		if (trimType.equals("copper")) {
			// Copper chest - 16 slots (2 rows of 8)
			// Row 1 (slots 0-7)
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 12, 10)));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 30, 10)));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 48, 10)));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 66, 10)));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 84, 10)));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 102, 10)));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 120, 10)));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 138, 10)));
			// Row 2 (slots 8-15)
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 12, 28)));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 30, 28)));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 48, 28)));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 66, 28)));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 84, 28)));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 102, 28)));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 120, 28)));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 138, 28)));

			// Player inventory (adjusted for copper chest height)
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, -4 + 8 + sj * 18, -31 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, -4 + 8 + si * 18, -31 + 142));


		} else if (trimType.equals("iron")) {
			// Iron chest - 24 slots (3 rows of 8)
			// Row 1 (slots 0-7)
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 12, 7)));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 30, 7)));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 48, 7)));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 66, 7)));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 84, 7)));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 102, 7)));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 120, 7)));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 138, 7)));
			// Row 2 (slots 8-15)
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 12, 25)));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 30, 25)));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 48, 25)));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 66, 25)));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 84, 25)));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 102, 25)));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 120, 25)));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 138, 25)));
			// Row 3 (slots 16-23)
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 12, 43)));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 30, 43)));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 48, 43)));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 66, 43)));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 84, 43)));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 102, 43)));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 120, 43)));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 138, 43)));
			// Player inventory (adjusted for iron chest height)
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, -4 + 8 + sj * 18, -19 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, -4 + 8 + si * 18, -19 + 142));


		} else if (trimType.equals("gold")) {
			// Gold chest - 32 slots (4 rows of 8)
			// Row 1 (slots 0-7)
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 12, 9)));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 30, 9)));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 48, 9)));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 66, 9)));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 84, 9)));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 102, 9)));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 120, 9)));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 138, 9)));
			// Row 2 (slots 8-15)
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 12, 27)));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 30, 27)));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 48, 27)));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 66, 27)));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 84, 27)));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 102, 27)));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 120, 27)));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 138, 27)));
			// Row 3 (slots 16-23)
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 12, 45)));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 30, 45)));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 48, 45)));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 66, 45)));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 84, 45)));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 102, 45)));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 120, 45)));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 138, 45)));
			// Row 4 (slots 24-31)
			this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 12, 63)));
			this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 30, 63)));
			this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 48, 63)));
			this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 66, 63)));
			this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 84, 63)));
			this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 102, 63)));
			this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 120, 63)));
			this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 138, 63)));

			// Player inventory (adjusted for gold chest height)
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, -4 + 8 + sj * 18, 3 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, -4 + 8 + si * 18, 3 + 142));

		} else if (trimType.equals("diamond")) {
			// Diamond chest - 40 slots (5 rows of 8)
			// Row 1 (slots 0-7)
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 12, 5)));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 30, 5)));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 48, 5)));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 66, 5)));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 84, 5)));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 102, 5)));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 120, 5)));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 138, 5)));
			// Row 2 (slots 8-15)
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 12, 23)));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 30, 23)));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 48, 23)));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 66, 23)));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 84, 23)));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 102, 23)));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 120, 23)));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 138, 23)));
			// Row 3 (slots 16-23)
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 12, 41)));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 30, 41)));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 48, 41)));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 66, 41)));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 84, 41)));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 102, 41)));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 120, 41)));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 138, 41)));
			// Row 4 (slots 24-31)
			this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 12, 59)));
			this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 30, 59)));
			this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 48, 59)));
			this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 66, 59)));
			this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 84, 59)));
			this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 102, 59)));
			this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 120, 59)));
			this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 138, 59)));
			// Row 5 (slots 32-39)
			this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 12, 77)));
			this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 30, 77)));
			this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 48, 77)));
			this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 66, 77)));
			this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 84, 77)));
			this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 102, 77)));
			this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 120, 77)));
			this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 138, 77)));


			// Player inventory (adjusted for diamond chest height)
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, -4 + 8 + sj * 18, 15 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, -4 + 8 + si * 18, 15 + 142));

		} else {
			// Netherite chest (default case) - 48 slots (6 rows of 8)
			// Row 1 (slots 0-7)
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 12, 5)));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 30, 5)));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 48, 5)));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 66, 5)));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 84, 5)));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 102, 5)));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 120, 5)));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 138, 5)));
			// Row 2 (slots 8-15)
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 12, 23)));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 30, 23)));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 48, 23)));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 66, 23)));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 84, 23)));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 102, 23)));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 120, 23)));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 138, 23)));
			// Row 3 (slots 16-23)
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 12, 41)));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 30, 41)));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 48, 41)));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 66, 41)));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 84, 41)));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 102, 41)));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 120, 41)));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 138, 41)));
			// Row 4 (slots 24-31)
			this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 12, 59)));
			this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 30, 59)));
			this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 48, 59)));
			this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 66, 59)));
			this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 84, 59)));
			this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 102, 59)));
			this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 120, 59)));
			this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 138, 59)));
			// Row 5 (slots 32-39)
			this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 12, 77)));
			this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 30, 77)));
			this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 48, 77)));
			this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 66, 77)));
			this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 84, 77)));
			this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 102, 77)));
			this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 120, 77)));
			this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 138, 77)));
			// Row 6 (slots 40-47)
			this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 12, 95)));
			this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 30, 95)));
			this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 48, 95)));
			this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 66, 95)));
			this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 84, 95)));
			this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 102, 95)));
			this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 120, 95)));
			this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 138, 95)));
			// Player inventory (adjusted for netherite chest height)
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, -4 + 8 + sj * 18, 31 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, -4 + 8 + si * 18, 31 + 142));
		}
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);

		// Get the inventory size based on trim type instead of hardcoding 40
		int inventorySize = getInventorySize();

		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();

			// If clicking a slot in the chest inventory
			if (index < inventorySize) {
				// Try to move to player inventory
				if (!this.moveItemStackTo(itemstack1, inventorySize, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			}
			// If clicking a slot in the player inventory
			else if (!this.moveItemStackTo(itemstack1, 0, inventorySize, false)) {
				// If it's in the main inventory (not hotbar)
				if (index < inventorySize + 27) {
					// Try to move to hotbar
					if (!this.moveItemStackTo(itemstack1, inventorySize + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				}
				// If it's in the hotbar
				else {
					// Try to move to main inventory
					if (!this.moveItemStackTo(itemstack1, inventorySize, inventorySize + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();

			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int k = slot.getMaxStackSize(itemstack);
					if (j <= k) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < k) {
						p_38904_.shrink(k - itemstack.getCount());
						itemstack.setCount(k);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					i--;
				} else {
					i++;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					int l = slot1.getMaxStackSize(p_38904_);
					slot1.setByPlayer(p_38904_.split(Math.min(p_38904_.getCount(), l)));
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					i--;
				} else {
					i++;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					playerIn.drop(internal.getStackInSlot(j), false);
					if (internal instanceof IItemHandlerModifiable ihm)
						ihm.setStackInSlot(j, ItemStack.EMPTY);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					playerIn.getInventory().placeItemBackInInventory(internal.getStackInSlot(i));
					if (internal instanceof IItemHandlerModifiable ihm)
						ihm.setStackInSlot(i, ItemStack.EMPTY);
				}
			}
		}
	}

	@Override
	public Map<Integer, Slot> get() {
		return customSlots;
	}

	// Get inventory size based on trim type
	private int getInventorySize() {
		if (trimType.equals("copper")) return 16;
		else if (trimType.equals("iron")) return 24;
		else if (trimType.equals("gold")) return 32;
		else if (trimType.equals("diamond")) return 40;
		else return 48; // netherite, amethyst, emerald, etc.
	}
}
