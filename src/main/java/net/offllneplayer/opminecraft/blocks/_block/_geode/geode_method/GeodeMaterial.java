package net.offllneplayer.opminecraft.blocks._block._geode.geode_method;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

/**
 * Encapsulates per-material geode components (buds and cluster) so growth logic can be reused.
 */
public enum GeodeMaterial {
	CRYSTAL(
		RegistryBIBI.BUDDING_CRYSTAL_BLOCK,
		RegistryBIBI.SMALL_CRYSTAL_BUD,
		RegistryBIBI.MEDIUM_CRYSTAL_BUD,
		RegistryBIBI.LARGE_CRYSTAL_BUD,
		RegistryBIBI.CRYSTAL_CLUSTER,
		RegistryBIBI.CRYSTAL_SHARD,
		MapColor.ICE,
		SoundType.AMETHYST,
			2F, 2F,
			2F, 2F
	);

    private final DeferredBlock<Block> buddingBlock;
    private final DeferredBlock<Block> smallBud;
    private final DeferredBlock<Block> mediumBud;
    private final DeferredBlock<Block> largeBud;
    private final DeferredBlock<Block> cluster;
    private final DeferredItem<Item> shard;

    private final MapColor mapColor;
    private final SoundType sound;

    private final float solidHardness; private final float solidResistance;
    private final float clusterHardness; private final float clusterResistance;

    GeodeMaterial(
        DeferredBlock<Block> buddingBlock,
        DeferredBlock<Block> smallBud,
        DeferredBlock<Block> mediumBud,
        DeferredBlock<Block> largeBud,
        DeferredBlock<Block> cluster,
        DeferredItem<Item> shard,
        MapColor mapColor,
        SoundType sound,
        float solidHardness, float solidResistance,
        float clusterHardness, float clusterResistance
    ) {
        this.buddingBlock = buddingBlock;
        this.smallBud = smallBud;
        this.mediumBud = mediumBud;
        this.largeBud = largeBud;
        this.cluster = cluster;
        this.shard = shard;
        this.mapColor = mapColor;
        this.sound = sound;
        this.solidHardness = solidHardness; this.solidResistance = solidResistance;
        this.clusterHardness = clusterHardness; this.clusterResistance = clusterResistance;
    }

    public Block buddingBlock() { return buddingBlock.get(); }
    public Block smallBud() { return smallBud.get(); }
    public Block mediumBud() { return mediumBud.get(); }
    public Block largeBud() { return largeBud.get(); }
    public Block cluster() { return cluster.get(); }
    public Item shard() { return shard.get(); }

    public MapColor mapColor() { return mapColor; }
    public SoundType sound() { return sound; }

    public float solidHardness() { return solidHardness; }
    public float solidResistance() { return solidResistance; }

    public float clusterHardness() { return clusterHardness; }
    public float clusterResistance() { return clusterResistance; }
}
