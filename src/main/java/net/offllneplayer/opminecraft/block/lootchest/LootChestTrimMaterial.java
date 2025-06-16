package net.offllneplayer.opminecraft.block.lootchest;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;


public enum LootChestTrimMaterial {
	COPPER(Items.COPPER_INGOT, Rarity.COMMON, false, 16),
	IRON(Items.IRON_INGOT, Rarity.COMMON, false, 24),
	GOLD(Items.GOLD_INGOT, Rarity.UNCOMMON, false, 32),
	DIAMOND(Items.DIAMOND, Rarity.RARE, false, 40),
	NETHERITE(Items.NETHERITE_INGOT, Rarity.EPIC, true, 48),
	AMETHYST(Items.AMETHYST_SHARD, Rarity.COMMON, false, 48),
	EMERALD(Items.EMERALD, Rarity.RARE, false, 48),
	LAPIS(Items.LAPIS_LAZULI, Rarity.COMMON, false, 48),
	QUARTZ(Items.QUARTZ, Rarity.COMMON, false, 48),
	REDSTONE(Items.REDSTONE, Rarity.COMMON, false, 48);

	private final Item registeredItem;
	private final Rarity rarity;
	private final boolean fireResistant;
	private final int slots;


	LootChestTrimMaterial(Item registeredItem, Rarity rarity, boolean fireResistant, int slots) {
		this.registeredItem = registeredItem;
		this.rarity = rarity;
		this.fireResistant = fireResistant;
		this.slots = slots;
	}

	// Property getters
	public Item getRegisteredItem() { return registeredItem; }

	public Rarity getRarity() { return rarity; }

	public boolean isFireResistant() { return fireResistant; }
	public int getSlots() { return slots; }
}

