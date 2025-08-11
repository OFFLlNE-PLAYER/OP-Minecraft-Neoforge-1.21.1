package net.offllneplayer.opminecraft.items._iwe.gunblade;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.init.RegistryBIBI;


public enum GunbladeMaterial {
	GOLDEN(Ingredient.of(Items.GOLD_INGOT), 420, 11F, BlockTags.INCORRECT_FOR_GOLD_TOOL, 6F, -3.0F, 22, Rarity.EPIC, false),
	DIAMOND(Ingredient.of(Items.DIAMOND), 1561, 8F, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 9F, -3.0F, 10, Rarity.UNCOMMON, false),
	NETHERITE(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 10F, -3.0F, 15, Rarity.EPIC, true),
	ONYX(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 10.5F, -3.0F, 20, Rarity.EPIC, true),
	TITAN(Ingredient.of(Items.NETHERITE_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 11F, -3.0F, 15, Rarity.EPIC, true),
	CRYING(Ingredient.of(RegistryBIBI.CRYING_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 10F, -3.0F, 18, Rarity.EPIC, false);

	private final Ingredient repairIngredient;
	private final int durability;
	private final float miningSpeed;
	private final TagKey<Block> incorrectBlocksForDrops;
	private final float attackDamage;
	private final float attackSpeed;
	private final int enchantability;
	private final Rarity rarity;
	private final boolean fireResistant;


	GunbladeMaterial(Ingredient repairIngredient, int durability, float miningSpeed, TagKey<Block> incorrectBlocksForDrops, float attackDamage, float attackSpeed, int enchantability, Rarity rarity, boolean fireResistant) {
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
			case GOLDEN -> RegistryBIBI.GOLDEN_GUNBLADE.get();
			case DIAMOND -> RegistryBIBI.DIAMOND_GUNBLADE.get();
			case NETHERITE -> RegistryBIBI.NETHERITE_GUNBLADE.get();
			case ONYX -> RegistryBIBI.ONYX_GUNBLADE.get();
			case TITAN -> RegistryBIBI.TITAN_GUNBLADE.get();
			case CRYING -> RegistryBIBI.CRYING_GUNBLADE.get();
		};
	}
}

