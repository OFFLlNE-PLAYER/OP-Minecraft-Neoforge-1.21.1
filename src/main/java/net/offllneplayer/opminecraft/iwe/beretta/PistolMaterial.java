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
	REDFIELD_BERETTA(14, 5F, 5, 24, 40, 12, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_2, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 1F, 0.86F, 0.0420F),

	WESKER_BERETTA(18, 4F, 4, 22, 36, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_3, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.420F, 1.1F, 0.0420F),

	VALENTINE_BERETTA(15, 4.5F, 3, 20, 34, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_1, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.84F, 0.98F, 0.0420F),

	GOLDEN_BERETTA(9, 3.5F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),

	DIAMOND_BERETTA(14, 4.5F, 4, 26, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),

	ONYX_BERETTA(12, 4F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),

	TITAN_BERETTA(16, 4.5F, 4, 22, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),

	TITAN_HANDCANNON(12, 5.5F, 6, 28, 40, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.B3R3TT4_1, RegistrySounds.B3R3TT4_R, RegistrySounds.B3R3TT4_0, 1F, 0.9F, 0.0420F),

	TITAN_DESERT_EAGLE(8, 10.5F, 6, 30, 45, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.MAGNUM_1, RegistrySounds.MAGNUM_R, RegistrySounds.MAGNUM_0, 0.9F, 1F, 0.0420F),

	TITAN_REVOLVER(6, 12F, 6, 40, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.REVOLVER_1, RegistrySounds.REVOLVER_R, RegistrySounds.REVOLVER_0, 1.F, 0.85F, 0.0420F),


	ONYX_PROTEKTOR_BERETTA(16, 4.5F, 4, 22, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.9F, 1.02F, 0.0420F),

	ONYX_PROTEKTOR_HANDCANNON(12, 5.5F, 6, 28, 40, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 1F, 0.92F, 0.0420F),

	ONYX_PROTEKTOR_DESERT_EAGLE(8, 10.5F, 6, 30, 45, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.MAGNUM_1, RegistrySounds.MAGNUM_R, RegistrySounds.MAGNUM_0, 0.86F, 0.92F, 0.0420F),

	ONYX_PROTEKTOR_REVOLVER(6, 12F, 6, 40, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.REVOLVER_1, RegistrySounds.REVOLVER_R, RegistrySounds.REVOLVER_0, 1.F, 0.86F, 0.0420F),


	PROTEKTOR_BERETTA(16, 4.5F, 4, 22, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.9F, 1.01F, 0.0420F),

	PROTEKTOR_HANDCANNON(12, 5.5F, 6, 28, 40, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 1F, 0.9F, 0.0420F),

	PROTEKTOR_DESERT_EAGLE(8, 10.5F, 6, 30, 45, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.MAGNUM_1, RegistrySounds.MAGNUM_R, RegistrySounds.MAGNUM_0, 0.96F, 0.86F, 0.0420F),

	PROTEKTOR_REVOLVER(6, 12F, 6, 40, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.REVOLVER_1, RegistrySounds.REVOLVER_R, RegistrySounds.REVOLVER_0, 1.F, 0.85F, 0.0420F),

	VALENTINE_REVOLVER(6, 11F, 6, 36, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.REVOLVER_1, RegistrySounds.VALENTINE_REVOLVER_R, RegistrySounds.VALENTINE_REVOLVER_0, 0.9F, 1F, 0.0420F);

	private final int durability;
	private final float attackDamage;
	private final int attackSpeed;
	private final int fireCooldown;
	private final int reloadCooldown;
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

	PistolMaterial(int durability, float attackDamage, int attackSpeed, int fireCooldown, int reloadCooldown, int enchantability, TagKey<Block> incorrectBlocksForDrops, float miningSpeed, Rarity rarity, boolean fireResistant,
						DeferredHolder<SoundEvent, SoundEvent> fireSound, DeferredHolder<SoundEvent, SoundEvent> reloadSound, DeferredHolder<SoundEvent, SoundEvent> emptySound,
						float vol, float basePitch, float pitchVariance) {
		this.durability = durability;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.fireCooldown = fireCooldown;
		this.reloadCooldown = reloadCooldown;
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
	public int getFireCooldown() { return fireCooldown; }
	public int getReloadCooldown() { return reloadCooldown; }
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
			case GOLDEN_BERETTA -> RegistryDamageTypes.HANDCANNON;
			case DIAMOND_BERETTA -> RegistryDamageTypes.HANDCANNON;
			case ONYX_BERETTA -> RegistryDamageTypes.HANDCANNON;
			case TITAN_BERETTA -> RegistryDamageTypes.HANDCANNON;
			case TITAN_HANDCANNON -> RegistryDamageTypes.HANDCANNON;
			case TITAN_DESERT_EAGLE -> RegistryDamageTypes.MAGNUM;
			case TITAN_REVOLVER -> RegistryDamageTypes.MAGNUM;
			case ONYX_PROTEKTOR_BERETTA -> RegistryDamageTypes.HANDCANNON;
			case ONYX_PROTEKTOR_HANDCANNON -> RegistryDamageTypes.HANDCANNON;
			case ONYX_PROTEKTOR_DESERT_EAGLE -> RegistryDamageTypes.MAGNUM;
			case ONYX_PROTEKTOR_REVOLVER -> RegistryDamageTypes.MAGNUM;
			case PROTEKTOR_BERETTA -> RegistryDamageTypes.HANDCANNON;
			case PROTEKTOR_HANDCANNON -> RegistryDamageTypes.HANDCANNON;
			case PROTEKTOR_DESERT_EAGLE -> RegistryDamageTypes.MAGNUM;
			case PROTEKTOR_REVOLVER -> RegistryDamageTypes.MAGNUM;
			case VALENTINE_REVOLVER -> RegistryDamageTypes.MAGNUM;
		};
	}

	public Item getRegisteredAmmo() {
		return switch(this) {
			case REDFIELD_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case WESKER_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case VALENTINE_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();

			case GOLDEN_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case DIAMOND_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case ONYX_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_HANDCANNON -> RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get();
			case TITAN_DESERT_EAGLE -> RegistryBIBI.FIFTY_AE_ROUNDS.get();
			case TITAN_REVOLVER -> RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get();

			case ONYX_PROTEKTOR_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case ONYX_PROTEKTOR_HANDCANNON -> RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get();
			case ONYX_PROTEKTOR_DESERT_EAGLE -> RegistryBIBI.FIFTY_AE_ROUNDS.get();
			case ONYX_PROTEKTOR_REVOLVER -> RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get();

			case PROTEKTOR_BERETTA -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case PROTEKTOR_HANDCANNON -> RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get();
			case PROTEKTOR_DESERT_EAGLE -> RegistryBIBI.FIFTY_AE_ROUNDS.get();
			case PROTEKTOR_REVOLVER -> RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get();

			case VALENTINE_REVOLVER -> RegistryBIBI.FOURTY_FOUR_S_AND_W_ROUNDS.get();
		};
	}

	public Item getRegisteredRenderItem() {
		return RegistryBIBI.PISTOL_BULLET.get();
	}
}


