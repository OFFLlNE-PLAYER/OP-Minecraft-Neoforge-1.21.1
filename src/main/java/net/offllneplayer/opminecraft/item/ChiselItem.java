package net.offllneplayer.opminecraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.offllneplayer.opminecraft.init.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP = Map.ofEntries(

            Map.entry(Blocks.ANDESITE, Blocks.POLISHED_ANDESITE),

            Map.entry(Blocks.BASALT, Blocks.SMOOTH_BASALT),

            Map.entry(Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE),
            Map.entry(Blocks.DEEPSLATE, Blocks.POLISHED_DEEPSLATE),
            Map.entry(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_TILES),

            Map.entry(Blocks.DIORITE, Blocks.POLISHED_DIORITE),
            Map.entry(Blocks.GRANITE, Blocks.POLISHED_GRANITE),
            Map.entry(Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

            Map.entry(Blocks.END_STONE, Blocks.END_STONE_BRICKS),

            Map.entry(Blocks.NETHER_BRICKS, Blocks.CHISELED_NETHER_BRICKS),

            Map.entry(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS),

            Map.entry(Blocks.SANDSTONE, Blocks.SMOOTH_SANDSTONE),
            Map.entry(Blocks.RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE),

            Map.entry(Blocks.TUFF, Blocks.POLISHED_TUFF),

            Map.entry(Blocks.STONE, Blocks.STONE_BRICKS),
            Map.entry(RegistryIBBI.DENSE_STONE.get(), RegistryIBBI.DENSE_STONE_BRICKS.get()),

            Map.entry(Blocks.DIAMOND_BLOCK, RegistryIBBI.CHISELED_DIAMOND.get()),
            Map.entry(Blocks.GOLD_BLOCK, RegistryIBBI.CHISELED_GOLD.get()),
            Map.entry(Blocks.IRON_BLOCK, RegistryIBBI.CHISELED_IRON.get()),
            Map.entry(Blocks.NETHERITE_BLOCK, RegistryIBBI.CHISELED_NETHERITE.get()),

            Map.entry(RegistryIBBI.CRYING_BRICKS.get(), RegistryIBBI.CRYING_TILES.get())
    );

    private static final Map<Block, Block> CHISEL_MAP2 = Map.of(

            Blocks.POLISHED_ANDESITE, Blocks.ANDESITE,

            Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS,
            Blocks.POLISHED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS,

            Blocks.POLISHED_DIORITE, Blocks.DIORITE,
            Blocks.POLISHED_GRANITE, Blocks.GRANITE,

            Blocks.END_STONE_BRICKS, Blocks.END_STONE,

            Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_PILLAR,

            Blocks.STONE_BRICKS, RegistryIBBI.STONE_TILES.get(),
            RegistryIBBI.DENSE_STONE_BRICKS.get(), RegistryIBBI.DENSE_STONE_TILES.get(),

            Blocks.POLISHED_TUFF, Blocks.CHISELED_TUFF
    );

    private static final Map<Block, Block> CHISEL_MAP3 = Map.ofEntries(

            Map.entry(Blocks.SMOOTH_BASALT, Blocks.POLISHED_BASALT),

            Map.entry(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CHISELED_POLISHED_BLACKSTONE),
            Map.entry(Blocks.DEEPSLATE_TILES, Blocks.CHISELED_DEEPSLATE),

            Map.entry(Blocks.SMOOTH_SANDSTONE, Blocks.CHISELED_SANDSTONE),
            Map.entry(Blocks.SMOOTH_RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE),

            Map.entry(Blocks.QUARTZ_PILLAR, Blocks.CHISELED_QUARTZ_BLOCK),

            Map.entry(Blocks.CHISELED_TUFF, Blocks.CHISELED_TUFF_BRICKS),
            Map.entry(  Blocks.TUFF_BRICKS, Blocks.TUFF),

            Map.entry(RegistryIBBI.STONE_TILES.get(), Blocks.CHISELED_STONE_BRICKS),

            Map.entry(Blocks.OXIDIZED_CHISELED_COPPER, Blocks.OXIDIZED_COPPER),

            Map.entry(RegistryIBBI.CHISELED_DIAMOND.get(), Blocks.DIAMOND_BLOCK),
            Map.entry(RegistryIBBI.CHISELED_GOLD.get(), Blocks.GOLD_BLOCK),
            Map.entry(RegistryIBBI.CHISELED_IRON.get(), Blocks.IRON_BLOCK),
            Map.entry(RegistryIBBI.CHISELED_NETHERITE.get(), Blocks.NETHERITE_BLOCK),

            Map.entry(RegistryIBBI.CRYING_TILES.get(), RegistryIBBI.CRYING_BRICKS.get())
    );

    private static final Map<Block, Block> CHISEL_MAP4 = Map.ofEntries(

            Map.entry(Blocks.POLISHED_BASALT, Blocks.BASALT),

            Map.entry(Blocks.CHISELED_POLISHED_BLACKSTONE, Blocks.BLACKSTONE),
            Map.entry(Blocks.CHISELED_DEEPSLATE, Blocks.DEEPSLATE),

            Map.entry(Blocks.OXIDIZED_CHISELED_COPPER, Blocks.OXIDIZED_COPPER),

            Map.entry(Blocks.CHISELED_NETHER_BRICKS, Blocks.NETHER_BRICKS),

            Map.entry(Blocks.CHISELED_SANDSTONE, Blocks.SANDSTONE),
            Map.entry(Blocks.CHISELED_RED_SANDSTONE, Blocks.RED_SANDSTONE),

            Map.entry(Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK),

            Map.entry(Blocks.CHISELED_TUFF_BRICKS, Blocks.TUFF_BRICKS),

            Map.entry(Blocks.CHISELED_STONE_BRICKS, Blocks.STONE),
            Map.entry(RegistryIBBI.DENSE_STONE_TILES.get(), RegistryIBBI.DENSE_STONE.get())
    );

    private static final List<Map<Block, Block>> chiselMaps = List.of(CHISEL_MAP, CHISEL_MAP2, CHISEL_MAP3, CHISEL_MAP4);

    public ChiselItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(64)
                .rarity(Rarity.RARE));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Block clickedBlock = level.getBlockState(pos).getBlock();

        for (Map<Block, Block> map : chiselMaps) {
            if (map.containsKey(clickedBlock)) {
                if (level instanceof ServerLevel serverLevel) {
                    BlockState newState = map.get(clickedBlock).defaultBlockState();
                    serverLevel.setBlockAndUpdate(pos, newState);

                    context.getItemInHand().hurtAndBreak(1, serverLevel, context.getPlayer(),
                            item -> Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                    serverLevel.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}