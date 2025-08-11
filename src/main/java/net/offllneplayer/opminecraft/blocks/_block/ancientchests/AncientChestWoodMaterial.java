package net.offllneplayer.opminecraft.blocks._block.ancientchests;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;


public enum AncientChestWoodMaterial {
	ACACIA(Items.ACACIA_PLANKS, Rarity.COMMON, false),
	BAMBOO(Items.BAMBOO_PLANKS, Rarity.COMMON, false),
	BIRCH(Items.BIRCH_PLANKS, Rarity.COMMON, false),
	CHERRY(Items.CHERRY_PLANKS, Rarity.COMMON, false),
	DARK_OAK(Items.DARK_OAK_PLANKS, Rarity.COMMON, false),
	JUNGLE(Items.JUNGLE_PLANKS, Rarity.COMMON, false),
	MANGROVE(Items.MANGROVE_PLANKS, Rarity.COMMON, false),
	OAK(Items.OAK_PLANKS, Rarity.COMMON, false),
	SPRUCE(Items.SPRUCE_PLANKS, Rarity.COMMON, false),

	CRIMSON(Items.CRIMSON_PLANKS, Rarity.COMMON, true),
	WARPED(Items.WARPED_PLANKS, Rarity.COMMON, true);

	private final Item RegisteredItem;
	private final Rarity rarity;
	private final boolean fireResistant;

	AncientChestWoodMaterial(Item registeredItem, Rarity rarity, boolean fireResistant) {

		this.RegisteredItem = registeredItem;
		this.rarity = rarity;
		this.fireResistant = fireResistant;
	}

	// Property getters
	public Item getRegisteredItem() {
		return RegisteredItem;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public boolean isFireResistant() {
		return fireResistant;
	}
}
