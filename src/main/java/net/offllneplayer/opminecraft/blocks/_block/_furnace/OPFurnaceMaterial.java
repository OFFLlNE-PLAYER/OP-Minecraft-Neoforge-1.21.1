package net.offllneplayer.opminecraft.blocks._block._furnace;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;


public enum OPFurnaceMaterial {
	COPPER("Copper", MapColor.COLOR_ORANGE, SoundType.COPPER, 3F, 6F),
	IRON("Iron", MapColor.METAL, SoundType.METAL, 5F, 6F),
	GOLD("Gold", MapColor.GOLD, SoundType.METAL, 3F, 6F),
	DIAMOND("Diamond", MapColor.DIAMOND, SoundType.METAL, 5F, 6F),
	NETHERITE("Netherite", MapColor.COLOR_BLACK, SoundType.NETHERITE_BLOCK, 50F, 1200F);

	private final String displayName;
	private final MapColor mapColor;
	private final SoundType soundType;
	private final float hardness;
	private final float blastResistance;

	OPFurnaceMaterial(String displayName, MapColor mapColor, SoundType soundType, float hardness, float blastResistance) {
		this.displayName = displayName;
		this.mapColor = mapColor;
		this.soundType = soundType;
		this.hardness = hardness;
		this.blastResistance = blastResistance;
	}

	// Property getters
	public String getDisplayName() { return displayName; }
	public MapColor getMapColor() { return mapColor; }
	public SoundType getSoundType() { return soundType; }
	public float getHardness() { return hardness; }
	public float getBlastResistance() { return blastResistance; }
}

