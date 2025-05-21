package net.offllneplayer.opminecraft.method.gunblade;

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

public class GunbladeMaterialMap {

		public static class GunbladeMaterial {
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

			public GunbladeMaterial(String name, TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient, Rarity rarity, boolean fireResistant) {
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


		private static final Map<String, GunbladeMaterial> MATERIALS = new HashMap<>();

		// Register standard materials

	public static final GunbladeMaterial CRYING = register(new GunbladeMaterial(
		"crying", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1310, 10F, 9F, 20,
		Ingredient.of(RegistryBIBI.CRYING_INGOT), Rarity.COMMON, false));

		public static final GunbladeMaterial ONYX = register(new GunbladeMaterial(
			"onyx", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 59, 2F, 8F, 18,
			Ingredient.of(Items.OAK_PLANKS), Rarity.COMMON, false));

		public static final GunbladeMaterial TITAN = register(new GunbladeMaterial(
			"titan", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1310, 10F, 9F, 20,
			Ingredient.of(Items.COBBLESTONE), Rarity.COMMON, false));

		// Register a material and return it for convenience
		public static GunbladeMaterial register(GunbladeMaterial material) {
			MATERIALS.put(material.getName(), material);
			return material;
		}

		// Get a material by name
		public static GunbladeMaterial get(String name) {
			return MATERIALS.get(name);
		}

		// Get all registered materials
		public static Collection<GunbladeMaterial> getAll() {
			return MATERIALS.values();
		}
	}
