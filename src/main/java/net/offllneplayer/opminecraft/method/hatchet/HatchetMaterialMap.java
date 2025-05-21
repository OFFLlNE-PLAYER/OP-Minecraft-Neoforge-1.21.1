package net.offllneplayer.opminecraft.method.hatchet;

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

public class HatchetMaterialMap {

		public static class HatchetMaterial {
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

			public HatchetMaterial(String name, TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient, Rarity rarity, boolean fireResistant) {
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


		private static final Map<String, HatchetMaterial> MATERIALS = new HashMap<>();

		// Register standard materials
		public static final HatchetMaterial WOOD = register(new HatchetMaterial(
			"wood", BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2F, 1F, 15,
			Ingredient.of(Items.OAK_PLANKS), Rarity.COMMON, false));

		public static final HatchetMaterial STONE = register(new HatchetMaterial(
			"stone", BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4F, 2F, 5,
			Ingredient.of(Items.COBBLESTONE), Rarity.COMMON, false));

		public static final HatchetMaterial IRON = register(new HatchetMaterial(
			"iron", BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6F, 3F, 14,
			Ingredient.of(Items.IRON_INGOT), Rarity.COMMON, false));

		public static final HatchetMaterial GOLDEN = register(new HatchetMaterial(
			"golden", BlockTags.INCORRECT_FOR_GOLD_TOOL, 420, 11F, 5F, 20,
			Ingredient.of(Items.GOLD_INGOT), Rarity.EPIC, false));

		public static final HatchetMaterial DIAMOND = register(new HatchetMaterial(
			"diamond", BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8F, 6F, 10,
			Ingredient.of(Items.DIAMOND), Rarity.UNCOMMON, false));

		public static final HatchetMaterial NETHERITE = register(new HatchetMaterial(
			"netherite", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9F, 7F, 15,
			Ingredient.of(Items.NETHERITE_INGOT), Rarity.RARE, true));  // Only Netherite is fire resistant

		public static final HatchetMaterial CRYING = register(new HatchetMaterial(
			"crying", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9F, 7F, 15,
			Ingredient.of(RegistryBIBI.CRYING_INGOT), Rarity.RARE, false));

		// Register a material and return it for convenience
		public static HatchetMaterial register(HatchetMaterial material) {
			MATERIALS.put(material.getName(), material);
			return material;
		}

		// Get a material by name
		public static HatchetMaterial get(String name) {
			return MATERIALS.get(name);
		}

		// Get all registered materials
		public static Collection<HatchetMaterial> getAll() {
			return MATERIALS.values();
		}
	}
