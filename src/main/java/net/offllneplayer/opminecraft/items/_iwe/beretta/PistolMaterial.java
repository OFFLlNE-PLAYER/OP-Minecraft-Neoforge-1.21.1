package net.offllneplayer.opminecraft.items._iwe.beretta;

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
	
	GOLDEN_92FS(15, 3F, 3, 26, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),
	IRON_92FS(15, 3.5F, 3, 26, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.UNCOMMON, false,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),
	EMERALD_92FS(15, 4F, 4, 26, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),
	DIAMOND_92FS(15, 4.5F, 4, 26, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),
	NETHERITE_92FS(15, 5F, 4, 26, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),
	ONYX_92FS(14, 5F, 5, 26, 40, 12, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_2, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 1F, 0.86F, 0.0420F),
	TITAN_92FS(18, 4F, 4, 26, 36, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_3, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.420F, 1.1F, 0.0420F),

	VALENTINE_92FS(15, 4.5F, 4, 20, 34, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.SAMURAI_EDGE_1, RegistrySounds.SAMURAI_EDGE_R, RegistrySounds.SAMURAI_EDGE_0, 0.84F, 0.98F, 0.0420F),


	GOLDEN_M9A1(10, 3.5F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),
	IRON_M9A1(10, 3F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.UNCOMMON, false,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),
	EMERALD_M9A1(10, 4F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.RARE, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),
	DIAMOND_M9A1(10, 4.5F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),
	NETHERITE_M9A1(10, 5F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),
	ONYX_M9A1(10, 5.5F, 4, 24, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.069F),
	TITAN_M9A1(10, 6F, 4, 22, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.BERETTA_1, RegistrySounds.BERETTA_R, RegistrySounds.BERETTA_0, 0.98F, 1.04F, 0.0420F),


	GOLDEN_CZ75(18, 2F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),
	IRON_CZ75(18, 2.5F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.UNCOMMON, false,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),
	EMERALD_CZ75(18, 3F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.RARE, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),
	DIAMOND_CZ75(18, 3.5F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),
	NETHERITE_CZ75(18, 4F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),
	ONYX_CZ75(18, 4.5F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),
	TITAN_CZ75(18, 5F, 3, 20, 32, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 0.85F, 1.0420F, 0.0420F),


	ONYX_PROTEKTOR_TYPE_75(10, 5.5F, 6, 28, 40, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.PROTEKTOR_BERETTA_1, RegistrySounds.PROTEKTOR_BERETTA_R, RegistrySounds.PROTEKTOR_BERETTA_0, 1F, 1F, 0.0420F),

	TITAN_HANDCANNON(12, 5.5F, 6, 28, 40, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.B3R3TT4_1, RegistrySounds.B3R3TT4_R, RegistrySounds.B3R3TT4_0, 1F, 0.9F, 0.0420F),


	ONYX_IMI_DESERT_EAGLE(8, 10.5F, 6, 30, 45, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.IMI_DESERT_EAGLE_1, RegistrySounds.IMI_DESERT_EAGLE_R, RegistrySounds.IMI_DESERT_EAGLE_0, 1F, 1F, 0.0420F),

	TITAN_DESERT_EAGLE(8, 10.5F, 6, 30, 45, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.MAGNUM_1, RegistrySounds.MAGNUM_R, RegistrySounds.MAGNUM_0, 0.9F, 1F, 0.0420F),


	ONYX_RRAR(6, 12F, 6, 40, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.REVOLVER_1, RegistrySounds.REVOLVER_R, RegistrySounds.REVOLVER_0, 1.F, 0.86F, 0.0420F),

	TITAN_S_AND_W_M629C(6, 11F, 6, 36, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
			RegistrySounds.REVOLVER_1, RegistrySounds.VALENTINE_REVOLVER_R, RegistrySounds.VALENTINE_REVOLVER_0, 0.9F, 1F, 0.0420F),

	TITAN_COLT_ANACONDA(6, 12F, 6, 40, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
							RegistrySounds.REVOLVER_1, RegistrySounds.REVOLVER_R, RegistrySounds.REVOLVER_0, 1.F, 0.85F, 0.0420F),

	TITAN_S_AND_W_500(6, 11F, 6, 36, 60, 15, OP_TagKeyUtil.Blocks.EMPTY_BLOCK_TAG, 2F, Rarity.EPIC, true,
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

			case GOLDEN_92FS -> RegistryDamageTypes.HANDCANNON;
			case IRON_92FS -> RegistryDamageTypes.HANDCANNON;
			case EMERALD_92FS -> RegistryDamageTypes.HANDCANNON;
			case DIAMOND_92FS -> RegistryDamageTypes.HANDCANNON;
			case NETHERITE_92FS -> RegistryDamageTypes.HANDCANNON;
			case ONYX_92FS -> RegistryDamageTypes.SAMURAI_EDGE;
			case TITAN_92FS -> RegistryDamageTypes.SAMURAI_EDGE;
			case VALENTINE_92FS -> RegistryDamageTypes.SAMURAI_EDGE;

			case GOLDEN_M9A1 -> RegistryDamageTypes.HANDCANNON;
			case IRON_M9A1 -> RegistryDamageTypes.HANDCANNON;
			case EMERALD_M9A1 -> RegistryDamageTypes.HANDCANNON;
			case DIAMOND_M9A1 -> RegistryDamageTypes.HANDCANNON;
			case NETHERITE_M9A1 -> RegistryDamageTypes.HANDCANNON;
			case ONYX_M9A1 -> RegistryDamageTypes.HANDCANNON;
			case TITAN_M9A1 -> RegistryDamageTypes.HANDCANNON;

			case GOLDEN_CZ75 -> RegistryDamageTypes.HANDCANNON;
			case IRON_CZ75 -> RegistryDamageTypes.HANDCANNON;
			case EMERALD_CZ75 -> RegistryDamageTypes.HANDCANNON;
			case DIAMOND_CZ75 -> RegistryDamageTypes.HANDCANNON;
			case NETHERITE_CZ75 -> RegistryDamageTypes.HANDCANNON;
			case ONYX_CZ75 -> RegistryDamageTypes.HANDCANNON;
			case TITAN_CZ75 -> RegistryDamageTypes.HANDCANNON;


			case ONYX_PROTEKTOR_TYPE_75 -> RegistryDamageTypes.HANDCANNON;

			case TITAN_HANDCANNON -> RegistryDamageTypes.HANDCANNON;


			case ONYX_IMI_DESERT_EAGLE -> RegistryDamageTypes.MAGNUM;

			case TITAN_DESERT_EAGLE -> RegistryDamageTypes.MAGNUM;


			case ONYX_RRAR -> RegistryDamageTypes.MAGNUM;
			case TITAN_S_AND_W_M629C -> RegistryDamageTypes.MAGNUM;
			case TITAN_COLT_ANACONDA -> RegistryDamageTypes.MAGNUM;

			case TITAN_S_AND_W_500 -> RegistryDamageTypes.MAGNUM;
		};
	}

	public Item getRegisteredAmmo() {
		return switch(this) {
		
			case GOLDEN_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case IRON_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case EMERALD_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case DIAMOND_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case NETHERITE_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case ONYX_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case VALENTINE_92FS -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();

			case GOLDEN_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case IRON_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case EMERALD_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case DIAMOND_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case NETHERITE_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case ONYX_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_M9A1 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();

			case GOLDEN_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case IRON_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case EMERALD_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case DIAMOND_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case NETHERITE_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case ONYX_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();
			case TITAN_CZ75 -> RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get();


			case ONYX_PROTEKTOR_TYPE_75 -> RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get();

			case TITAN_HANDCANNON -> RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get();


			case TITAN_DESERT_EAGLE -> RegistryBIBI.FIFTY_AE_ROUNDS.get();

			case ONYX_IMI_DESERT_EAGLE -> RegistryBIBI.FOURTY_FOUR_S_AND_W_ROUNDS.get();

			case ONYX_RRAR -> RegistryBIBI.FOURTY_FOUR_S_AND_W_ROUNDS.get();
			case TITAN_S_AND_W_M629C -> RegistryBIBI.FOURTY_FOUR_S_AND_W_ROUNDS.get();
			case TITAN_COLT_ANACONDA -> RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get();

			case TITAN_S_AND_W_500 -> RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get();
		};
	}

	public Item getRegisteredRenderItem() {
		return RegistryBIBI.PISTOL_BULLET.get();
	}
}


