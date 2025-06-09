package net.offllneplayer.opminecraft.iwe.pistol;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistryBIBI;


public enum GunMaterial {
	TITAN_SAMURAI_EDGE(16, 2F, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 6F, 4, 15, Rarity.EPIC, true),
	VALENTINE_SAMURAI_EDGE(15, 2F, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 6F, 4, 15, Rarity.EPIC, true);

	private final int durability;
	private final float miningSpeed;
	private final TagKey<Block> incorrectBlocksForDrops;
	private final float attackDamage;
	private final int attackSpeed;
	private final int enchantability;
	private final Rarity rarity;
	private final boolean fireResistant;

	GunMaterial(int durability, float miningSpeed,
	            TagKey<Block> incorrectBlocksForDrops, float attackDamage, int attackSpeed, int enchantability,
	            Rarity rarity, boolean fireResistant) {
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
	public int getDurability() { return durability; }
	public float getMiningSpeed() { return miningSpeed; }
	public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
	public float getAttackDamage() { return attackDamage; }
	public int getAttackSpeed() { return attackSpeed; }
	public int getEnchantability() { return enchantability; }
	public Rarity getRarity() { return rarity; }
	public boolean isFireResistant() { return fireResistant; }

	public Item getRegisteredItem() {
		return switch(this) {
			case TITAN_SAMURAI_EDGE -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case VALENTINE_SAMURAI_EDGE -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
		};
	}
	public Item getRegisteredRenderItem() {
		return switch(this) {
			case TITAN_SAMURAI_EDGE -> RegistryBIBI.SAMURAI_EDGE_BULLET.get();
			case VALENTINE_SAMURAI_EDGE -> RegistryBIBI.SAMURAI_EDGE_BULLET.get();
		};
	}
}
