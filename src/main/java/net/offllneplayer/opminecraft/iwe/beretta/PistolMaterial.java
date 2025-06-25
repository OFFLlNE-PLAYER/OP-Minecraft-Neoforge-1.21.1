package net.offllneplayer.opminecraft.iwe.beretta;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public enum PistolMaterial {
	REDFIELD_BERETTA(14, 5.5F, 5, 12, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_4, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 1F, 0.86F, 0.0420F),

	WESKER_BERETTA(18, 4F, 3, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_3, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.420F, 1.1F, 0.0420F),

	VALENTINE_BERETTA(15, 5F, 4, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_2, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.8F, 0.98F, 0.0420F),

	TITAN_BERETTA(16, 4.5F, 4, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_1, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.9F, 0.9F, 0.06F),

	TITAN_HANDCANNON(12, 6F, 5, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_2, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 1F, 0.78F, 0.0420F),

	TITAN_DESERT_EAGLE(8, 10F, 6, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.MAGNUM_1, RegistrySounds.MAGNUM_R, RegistrySounds.MAGNUM_0, 1F, 1.1F, 0.0420F);

	private final int durability;
	private final float attackDamage;
	private final int attackSpeed;
	private final int enchantability;
	private final TagKey<Block> incorrectBlocksForDrops;
	private final float miningSpeed;
	private final Rarity rarity;
	private final boolean fireResistant;
	private final DeferredHolder<SoundEvent, SoundEvent> fireSound;
	private final DeferredHolder<SoundEvent, SoundEvent> reloadSound;
	private final DeferredHolder<SoundEvent, SoundEvent> emptySound;
	private final float vol;
	private final float basePitch;
	private final float pitchVariance;

	PistolMaterial(int durability, float attackDamage, int attackSpeed, int enchantability, TagKey<Block> incorrectBlocksForDrops, float miningSpeed, Rarity rarity, boolean fireResistant,
						DeferredHolder<SoundEvent, SoundEvent> fireSound, DeferredHolder<SoundEvent, SoundEvent> reloadSound, DeferredHolder<SoundEvent, SoundEvent> emptySound,
						float vol, float basePitch, float pitchVariance) {
		this.durability = durability;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.enchantability = enchantability;
		this.incorrectBlocksForDrops = incorrectBlocksForDrops;
		this.miningSpeed = miningSpeed;
		this.rarity = rarity;
		this.fireResistant = fireResistant;
		this.fireSound = fireSound;
		this.reloadSound = reloadSound;
		this.emptySound = emptySound;
		this.vol = vol;
		this.basePitch = basePitch;
		this.pitchVariance = pitchVariance;
	}

	// Property getters
	public int getDurability() { return durability; }
	public float getAttackDamage() { return attackDamage; }
	public int getAttackSpeed() { return attackSpeed; }
	public int getEnchantability() { return enchantability; }
	public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
	public float getMiningSpeed() { return miningSpeed; }
	public Rarity getRarity() { return rarity; }
	public boolean isFireResistant() { return fireResistant; }

	// Sound and audio getters - Note the .get() calls
	public SoundEvent getFireSound() { return fireSound.get(); }
	public SoundEvent getReloadSound() { return reloadSound.get(); }
	public SoundEvent getEmptySound() { return emptySound.get(); }
	public float getVolume() { return vol; }
	public float getBasePitch() { return basePitch; }
	public float getPitchVariance() { return pitchVariance; }

	public ResourceKey<DamageType> getDamageType() {
		return switch(this) {
			case REDFIELD_BERETTA -> RegistryDamageTypes.SAMURAI_EDGE;
			case WESKER_BERETTA -> RegistryDamageTypes.SAMURAI_EDGE;
			case VALENTINE_BERETTA -> RegistryDamageTypes.SAMURAI_EDGE;
			case TITAN_BERETTA -> RegistryDamageTypes.SAMURAI_EDGE;
			case TITAN_HANDCANNON -> RegistryDamageTypes.HANDCANNON;
			case TITAN_DESERT_EAGLE -> RegistryDamageTypes.MAGNUM;
		};
	}

	public Item getRegisteredAmmo() {
		return switch(this) {
			case REDFIELD_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case WESKER_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case VALENTINE_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_HANDCANNON -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_DESERT_EAGLE -> RegistryBIBI.FIFTY_AE_ROUNDS.get();
		};
	}

	public Item getRegisteredRenderItem() {
		return switch(this) {
			case REDFIELD_BERETTA -> RegistryBIBI.PISTOL_BULLET.get();
			case WESKER_BERETTA -> RegistryBIBI.PISTOL_BULLET.get();
			case VALENTINE_BERETTA -> RegistryBIBI.PISTOL_BULLET.get();
			case TITAN_BERETTA -> RegistryBIBI.PISTOL_BULLET.get();
			case TITAN_HANDCANNON -> RegistryBIBI.PISTOL_BULLET.get();
			case TITAN_DESERT_EAGLE -> RegistryBIBI.PISTOL_BULLET.get();
		};
	}
}

