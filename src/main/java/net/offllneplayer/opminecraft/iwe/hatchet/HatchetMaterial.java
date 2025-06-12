package net.offllneplayer.opminecraft.iwe.hatchet;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import net.offllneplayer.opminecraft.init.RegistryBIBI;


public enum HatchetMaterial {
	WOODEN(Ingredient.of(Items.OAK_PLANKS), 59, 2F, BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1F, -2.4F, 15, Rarity.COMMON, false),
	STONE(Ingredient.of(Items.COBBLESTONE), 131, 4F, BlockTags.INCORRECT_FOR_STONE_TOOL, 2F, -2.4F, 5, Rarity.COMMON, false),

	IRON(Ingredient.of(Items.IRON_INGOT), 250, 6F, BlockTags.INCORRECT_FOR_IRON_TOOL, 3F, -2.4F, 14, Rarity.COMMON, false),
	GOLDEN(Ingredient.of(Items.GOLD_INGOT), 420, 11F, BlockTags.INCORRECT_FOR_GOLD_TOOL, 5F, -2.4F, 22, Rarity.EPIC, false),
	DIAMOND(Ingredient.of(Items.DIAMOND), 1561, 8F, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 6F, -2.4F, 10, Rarity.UNCOMMON, false),
	NETHERITE(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7F, -2.4F, 15, Rarity.EPIC, true),
	ONYX(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7.5F, -2.4F, 20, Rarity.EPIC, true),
	TITAN(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8F, -2.4F, 15, Rarity.EPIC, true),
	CRYING(Ingredient.of(RegistryBIBI.CRYING_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7F, -2.4F, 18, Rarity.EPIC, false);

	private final Ingredient repairIngredient;
	private final int durability;
	private final float miningSpeed;
	private final TagKey<Block> incorrectBlocksForDrops;
	private final float attackDamage;
	private final float attackSpeed;
	private final int enchantability;
	private final Rarity rarity;
	private final boolean fireResistant;


	HatchetMaterial(Ingredient repairIngredient, int durability, float miningSpeed, TagKey<Block> incorrectBlocksForDrops, float attackDamage, float attackSpeed, int enchantability, Rarity rarity, boolean fireResistant) {
		this.repairIngredient = repairIngredient;
		this.durability = durability;
		this.miningSpeed = miningSpeed;
		this.incorrectBlocksForDrops = incorrectBlocksForDrops;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.enchantability = enchantability;
		this.rarity = rarity;
		this.fireResistant = fireResistant;
	}


	public Ingredient getRepairIngredient() { return repairIngredient; }
	public int getDurability() { return durability; }
	public float getMiningSpeed() { return miningSpeed; }
	public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
	public float getAttackDamage() { return attackDamage; }
	public float getAttackSpeed() { return attackSpeed; }
	public int getEnchantability() { return enchantability; }
	public Rarity getRarity() { return rarity; }
	public boolean isFireResistant() { return fireResistant; }

	public Item getRegisteredItem() {
		return switch(this) {
			case WOODEN -> RegistryBIBI.WOODEN_HATCHET.get();
			case STONE -> RegistryBIBI.STONE_HATCHET.get();
			case IRON -> RegistryBIBI.IRON_HATCHET.get();
			case GOLDEN -> RegistryBIBI.GOLDEN_HATCHET.get();
			case DIAMOND -> RegistryBIBI.DIAMOND_HATCHET.get();
			case NETHERITE -> RegistryBIBI.NETHERITE_HATCHET.get();
			case ONYX -> RegistryBIBI.ONYX_HATCHET.get();
			case TITAN -> RegistryBIBI.TITAN_HATCHET.get();
			case CRYING -> RegistryBIBI.CRYING_HATCHET.get();
		};
	}
}
