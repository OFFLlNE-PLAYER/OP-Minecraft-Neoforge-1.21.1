package net.offllneplayer.opminecraft.items._iwe.sw0rd;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.init.RegistryBIBI;


public enum Sw0rdMaterial {
	WOODEN(Ingredient.of(Items.OAK_PLANKS), 59, 2F, BlockTags.INCORRECT_FOR_WOODEN_TOOL, 4F, -2.6F, 15, Rarity.COMMON, false),
	CLAY(Ingredient.of(Items.CLAY), 96, 3.5F, BlockTags.INCORRECT_FOR_STONE_TOOL, 4.5F, -2.6F, 16, Rarity.UNCOMMON, false),
	STONE(Ingredient.of(Items.COBBLESTONE), 131, 4F, BlockTags.INCORRECT_FOR_STONE_TOOL, 5F, -2.6F, 5, Rarity.UNCOMMON, false),
	GOLDEN(Ingredient.of(Items.GOLD_INGOT), 420, 11F, BlockTags.INCORRECT_FOR_GOLD_TOOL, 4.5F, -2.6F, 22, Rarity.RARE, false),
	IRON(Ingredient.of(Items.IRON_INGOT), 250, 6F, BlockTags.INCORRECT_FOR_IRON_TOOL, 6F, -2.6F, 14, Rarity.UNCOMMON, false),
	DIAMOND(Ingredient.of(Items.DIAMOND), 1561, 8F, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 6F, -2.6F, 10, Rarity.UNCOMMON, false),
	NETHERITE(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7F, -2.6F, 15, Rarity.EPIC, true);

	private final Ingredient repairIngredient;
	private final int durability;
	private final float miningSpeed;
	private final TagKey<Block> incorrectBlocksForDrops;
	private final float attackDamage;
	private final float attackSpeed;
	private final int enchantability;
	private final Rarity rarity;
	private final boolean fireResistant;


	Sw0rdMaterial(Ingredient repairIngredient, int durability, float miningSpeed, TagKey<Block> incorrectBlocksForDrops, float attackDamage, float attackSpeed, int enchantability, Rarity rarity, boolean fireResistant) {
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
			case WOODEN -> Items.WOODEN_SWORD;
			case CLAY -> RegistryBIBI.CLAY_SWORD.get();
			case STONE -> Items.STONE_SWORD;
			case IRON -> Items.IRON_SWORD;
			case GOLDEN -> Items.GOLDEN_SWORD;
			case DIAMOND -> Items.DIAMOND_SWORD;
			case NETHERITE -> Items.NETHERITE_SWORD;
		};
	}
}
