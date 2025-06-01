package net.offllneplayer.opminecraft.iwe.opsw0rd;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OPSwordMaterialMap {

		public static class OPSwordMaterial {
			private final String name;
			private final TagKey<Block> incorrectBlocksForDrops;
			private final int durability;
			private final float miningSpeed;
			private final float attackDamage;
			private final int enchantability;
			private final Ingredient repairIngredient;
			private final Rarity rarity;
			private final boolean fireResistant;  // Changed to boolean
			private Item registeredItem;

			public OPSwordMaterial(String name, TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient, Rarity rarity, boolean fireResistant) {
				this.name = name;
				this.incorrectBlocksForDrops = incorrectBlocksForDrops;
				this.durability = durability;
				this.miningSpeed = miningSpeed;
				this.attackDamage = attackDamage;
				this.enchantability = enchantability;
				this.repairIngredient = repairIngredient;
				this.rarity = rarity;
				this.fireResistant = fireResistant;
				this.registeredItem = null; // Set later when the item is registered
			}

			// Getters for all properties
			public String getName() { return name; }
			public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
			public int getDurability() { return durability; }
			public float getMiningSpeed() { return miningSpeed; }
			public float getAttackDamage() { return attackDamage; }
			public int getEnchantability() { return enchantability; }
			public Ingredient getRepairIngredient() { return repairIngredient; }
			public Rarity getRarity() { return rarity; }
			public boolean isFireResistant() { return fireResistant; }

			public void setRegisteredItem(Item item) {
				this.registeredItem = item;
			}

			public Item getRegisteredItem() {
				return registeredItem;
			}
		}


		private static final Map<String, OPSwordMaterial> MATERIALS = new HashMap<>();

		// Register standard materials
		public static final OPSwordMaterial CLAY = register(new OPSwordMaterial(
			"clay", BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 4F, 5F, 10,
			Ingredient.of(Items.COBBLESTONE), Rarity.COMMON, false));

	public static final OPSwordMaterial CRYING = register(new OPSwordMaterial(
		"crying", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9F, 7F, 15,
		Ingredient.of(RegistryBIBI.CRYING_INGOT), Rarity.EPIC, false));

/*
	public static final OPSwordMaterial ONYX = register(new OPSwordMaterial(
		"onyx", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9F, 7F, 15,
		Ingredient.of(RegistryBIBI.ONYX_INGOT), Rarity.EPIC, true));

	public static final OPSwordMaterial TITAN = register(new OPSwordMaterial(
		"titan", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9F, 7F, 15,
		Ingredient.of(RegistryBIBI.TITANIUM_INGOT), Rarity.EPIC, true));
*/

		public static OPSwordMaterial register(OPSwordMaterial material) {
			MATERIALS.put(material.getName(), material);
			return material;
		}

		// Get a material by name
		public static OPSwordMaterial get(String name) {
			return MATERIALS.get(name);
		}

	public static Collection<OPSwordMaterialMap.OPSwordMaterial> getAll() {
		return MATERIALS.values();
	}
}
