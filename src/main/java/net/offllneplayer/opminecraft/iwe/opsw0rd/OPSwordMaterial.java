package net.offllneplayer.opminecraft.iwe.opsw0rd;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

public enum OPSwordMaterial {
	CLAY(Ingredient.of(Items.COBBLESTONE), 200, 4F, BlockTags.INCORRECT_FOR_STONE_TOOL, 5F, -2.4F, 10,
		Rarity.COMMON, false),

	CRYING(Ingredient.of(RegistryBIBI.CRYING_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7F, -2.4F, 15,
		Rarity.EPIC, false);

    /*
    ONYX(Ingredient.of(RegistryBIBI.ONYX_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7F, -2.4F, 15,
         Rarity.EPIC, true),

    TITAN(Ingredient.of(RegistryBIBI.TITANIUM_INGOT), 2031, 9F, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 7F, -2.4F, 15,
          Rarity.EPIC, true);
    */

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

	OPSwordMaterial(Ingredient repairIngredient, int durability, float miningSpeed,
	                TagKey<Block> incorrectBlocksForDrops, float attackDamage, float attackSpeed,
	                int enchantability, Rarity rarity, boolean fireResistant) {
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
