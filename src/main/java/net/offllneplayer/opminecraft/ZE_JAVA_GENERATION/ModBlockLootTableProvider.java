package net.offllneplayer.opminecraft.ZE_JAVA_GENERATION;


import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {

        /*
        dropSelf(ModBlocks.MAGIC_BLOCK.get());


        this.add(ModBlocks.BLACK_OPAL_ORE.get(),
                block -> createOreDrop(ModBlocks.BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
        this.add(ModBlocks.BLACK_OPAL_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.BLACK_OPAL_DEEPSLATE_ORE.get(), ModItems.RAW_BLACK_OPAL.get(), 2, 5));
        this.add(ModBlocks.BLACK_OPAL_END_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.BLACK_OPAL_END_ORE.get(), ModItems.RAW_BLACK_OPAL.get(), 4, 7));
        this.add(ModBlocks.BLACK_OPAL_NETHER_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.BLACK_OPAL_NETHER_ORE.get(), ModItems.RAW_BLACK_OPAL.get(), 3, 9));
*/

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return RegistryBIBI.BLOCKSREGISTRY.getEntries().stream().map(Holder::value)::iterator;
    }
}
