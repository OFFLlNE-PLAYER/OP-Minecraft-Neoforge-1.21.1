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
    ALEXANDRITE(
        RegistryBIBI.BUDDING_ALEXANDRITE_BLOCK,
        RegistryBIBI.SMALL_ALEXANDRITE_BUD,
        RegistryBIBI.MEDIUM_ALEXANDRITE_BUD,
        RegistryBIBI.LARGE_ALEXANDRITE_BUD,
        RegistryBIBI.ALEXANDRITE_CLUSTER,
        RegistryBIBI.CRUDE_ALEXANDRITE,
        MapColor.COLOR_PURPLE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff8a2be2
    ),
    ANDALUSITE(
        RegistryBIBI.BUDDING_ANDALUSITE_BLOCK,
        RegistryBIBI.SMALL_ANDALUSITE_BUD,
        RegistryBIBI.MEDIUM_ANDALUSITE_BUD,
        RegistryBIBI.LARGE_ANDALUSITE_BUD,
        RegistryBIBI.ANDALUSITE_CLUSTER,
        RegistryBIBI.CRUDE_ANDALUSITE,
        MapColor.COLOR_ORANGE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffffa000
    ),
    AQUAMARINE(
        RegistryBIBI.BUDDING_AQUAMARINE_BLOCK,
        RegistryBIBI.SMALL_AQUAMARINE_BUD,
        RegistryBIBI.MEDIUM_AQUAMARINE_BUD,
        RegistryBIBI.LARGE_AQUAMARINE_BUD,
        RegistryBIBI.AQUAMARINE_CLUSTER,
        RegistryBIBI.CRUDE_AQUAMARINE,
        MapColor.COLOR_LIGHT_BLUE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff4ec3ff
    ),
    CHRYSOBERYL(
        RegistryBIBI.BUDDING_CHRYSOBERYL_BLOCK,
        RegistryBIBI.SMALL_CHRYSOBERYL_BUD,
        RegistryBIBI.MEDIUM_CHRYSOBERYL_BUD,
        RegistryBIBI.LARGE_CHRYSOBERYL_BUD,
        RegistryBIBI.CHRYSOBERYL_CLUSTER,
        RegistryBIBI.CRUDE_CHRYSOBERYL,
        MapColor.COLOR_LIGHT_GREEN,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffffff00
    ),
    CORUNDUM(
        RegistryBIBI.BUDDING_CORUNDUM_BLOCK,
        RegistryBIBI.SMALL_CORUNDUM_BUD,
        RegistryBIBI.MEDIUM_CORUNDUM_BUD,
        RegistryBIBI.LARGE_CORUNDUM_BUD,
        RegistryBIBI.CORUNDUM_CLUSTER,
        RegistryBIBI.CRUDE_CORUNDUM,
        MapColor.COLOR_RED,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffffffff
    ),
    CYMOPHANE(
        RegistryBIBI.BUDDING_CYMOPHANE_BLOCK,
        RegistryBIBI.SMALL_CYMOPHANE_BUD,
        RegistryBIBI.MEDIUM_CYMOPHANE_BUD,
        RegistryBIBI.LARGE_CYMOPHANE_BUD,
        RegistryBIBI.CYMOPHANE_CLUSTER,
        RegistryBIBI.CRUDE_CYMOPHANE,
        MapColor.COLOR_YELLOW,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffffc000
    ),
    DIAMOND(
        RegistryBIBI.BUDDING_DIAMOND_BLOCK,
        RegistryBIBI.SMALL_DIAMOND_BUD,
        RegistryBIBI.MEDIUM_DIAMOND_BUD,
        RegistryBIBI.LARGE_DIAMOND_BUD,
        RegistryBIBI.DIAMOND_CLUSTER,
        RegistryBIBI.CRUDE_DIAMOND,
        MapColor.COLOR_LIGHT_BLUE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff9be5ff
    ),
    DRAGONITE(
        RegistryBIBI.BUDDING_DRAGONITE_BLOCK,
        RegistryBIBI.SMALL_DRAGONITE_BUD,
        RegistryBIBI.MEDIUM_DRAGONITE_BUD,
        RegistryBIBI.LARGE_DRAGONITE_BUD,
        RegistryBIBI.DRAGONITE_CLUSTER,
        RegistryBIBI.CRUDE_DRAGONITE,
        MapColor.COLOR_PURPLE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff7f00ff
    ),
    EMERALD(
        RegistryBIBI.BUDDING_EMERALD_BLOCK,
        RegistryBIBI.SMALL_EMERALD_BUD,
        RegistryBIBI.MEDIUM_EMERALD_BUD,
        RegistryBIBI.LARGE_EMERALD_BUD,
        RegistryBIBI.EMERALD_CLUSTER,
        RegistryBIBI.CRUDE_EMERALD,
        MapColor.COLOR_GREEN,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff00ff00
    ),
    JADEITE(
        RegistryBIBI.BUDDING_JADEITE_BLOCK,
        RegistryBIBI.SMALL_JADEITE_BUD,
        RegistryBIBI.MEDIUM_JADEITE_BUD,
        RegistryBIBI.LARGE_JADEITE_BUD,
        RegistryBIBI.JADEITE_CLUSTER,
        RegistryBIBI.CRUDE_JADEITE,
        MapColor.COLOR_GREEN,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff00aa00
    ),
    OPALITE(
        RegistryBIBI.BUDDING_OPALITE_BLOCK,
        RegistryBIBI.SMALL_OPALITE_BUD,
        RegistryBIBI.MEDIUM_OPALITE_BUD,
        RegistryBIBI.LARGE_OPALITE_BUD,
        RegistryBIBI.OPALITE_CLUSTER,
        RegistryBIBI.CRUDE_OPALITE,
        MapColor.COLOR_LIGHT_GRAY,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffb0ffff
    ),
    PADPARADSCHA(
        RegistryBIBI.BUDDING_PADPARADSCHA_BLOCK,
        RegistryBIBI.SMALL_PADPARADSCHA_BUD,
        RegistryBIBI.MEDIUM_PADPARADSCHA_BUD,
        RegistryBIBI.LARGE_PADPARADSCHA_BUD,
        RegistryBIBI.PADPARADSCHA_CLUSTER,
        RegistryBIBI.CRUDE_PADPARADSCHA,
        MapColor.COLOR_PINK,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffff80aa
    ),
    RUBY(
        RegistryBIBI.BUDDING_RUBY_BLOCK,
        RegistryBIBI.SMALL_RUBY_BUD,
        RegistryBIBI.MEDIUM_RUBY_BUD,
        RegistryBIBI.LARGE_RUBY_BUD,
        RegistryBIBI.RUBY_CLUSTER,
        RegistryBIBI.CRUDE_RUBY,
        MapColor.COLOR_RED,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffff0000
    ),
    SAPPHIRE(
        RegistryBIBI.BUDDING_SAPPHIRE_BLOCK,
        RegistryBIBI.SMALL_SAPPHIRE_BUD,
        RegistryBIBI.MEDIUM_SAPPHIRE_BUD,
        RegistryBIBI.LARGE_SAPPHIRE_BUD,
        RegistryBIBI.SAPPHIRE_CLUSTER,
        RegistryBIBI.CRUDE_SAPPHIRE,
        MapColor.COLOR_BLUE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff0000ff
    ),
    SCAPOLITE(
        RegistryBIBI.BUDDING_SCAPOLITE_BLOCK,
        RegistryBIBI.SMALL_SCAPOLITE_BUD,
        RegistryBIBI.MEDIUM_SCAPOLITE_BUD,
        RegistryBIBI.LARGE_SCAPOLITE_BUD,
        RegistryBIBI.SCAPOLITE_CLUSTER,
        RegistryBIBI.CRUDE_SCAPOLITE,
        MapColor.COLOR_LIGHT_GRAY,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xffddd9c5
    ),
    STAUROLITE(
        RegistryBIBI.BUDDING_STAUROLITE_BLOCK,
        RegistryBIBI.SMALL_STAUROLITE_BUD,
        RegistryBIBI.MEDIUM_STAUROLITE_BUD,
        RegistryBIBI.LARGE_STAUROLITE_BUD,
        RegistryBIBI.STAUROLITE_CLUSTER,
        RegistryBIBI.CRUDE_STAUROLITE,
        MapColor.COLOR_BROWN,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff7a5230
    ),
    TANZANITE(
        RegistryBIBI.BUDDING_TANZANITE_BLOCK,
        RegistryBIBI.SMALL_TANZANITE_BUD,
        RegistryBIBI.MEDIUM_TANZANITE_BUD,
        RegistryBIBI.LARGE_TANZANITE_BUD,
        RegistryBIBI.TANZANITE_CLUSTER,
        RegistryBIBI.CRUDE_TANZANITE,
        MapColor.COLOR_BLUE,
        SoundType.AMETHYST,
            2.5F, 2.5F,
            2F, 2F,
        0xff000080
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
    private final int beaconColor;

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
        float clusterHardness, float clusterResistance,
        int beaconColor
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
        this.beaconColor = beaconColor;
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

    public Integer beaconColor() { return beaconColor; }
}
