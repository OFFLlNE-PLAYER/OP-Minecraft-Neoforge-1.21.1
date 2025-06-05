package net.offllneplayer.opminecraft.iwe.pistol;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.init.RegistryBIBI;


public enum GunMaterial {
	SAMURAI_EDGE(Ingredient.of(Items.NETHERITE_INGOT), 59, 2F, BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1F, -2.4F, 15,
		Rarity.RARE, false);

	private final Ingredient repairIngredient;
	private final int durability;
	private final float miningSpeed;
	private final TagKey<Block> incorrectBlocksForDrops;
	private final float attackDamage;
	private final float attackSpeed;
	private final int enchantability;
	private final Rarity rarity;
	private final boolean fireResistant;
	private Item registeredItem;

	GunMaterial(Ingredient repairIngredient, int durability, float miningSpeed,
	            TagKey<Block> incorrectBlocksForDrops, float attackDamage, float attackSpeed, int enchantability,
	            Rarity rarity, boolean fireResistant) {
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

	// Property getters
	public Ingredient getRepairIngredient() { return repairIngredient; }
	public int getDurability() { return durability; }
	public float getMiningSpeed() { return miningSpeed; }
	public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
	public float getAttackDamage() { return attackDamage; }
	public float getAttackSpeed() { return attackSpeed; }
	public int getEnchantability() { return enchantability; }
	public Rarity getRarity() { return rarity; }
	public boolean isFireResistant() { return fireResistant; }

	public void setRegisteredItem(Item item) {this.registeredItem = item;}
	public Item getRegisteredItem() {return registeredItem;}
}
