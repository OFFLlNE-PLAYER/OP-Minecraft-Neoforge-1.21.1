package net.offllneplayer.opminecraft.item;

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
import net.offllneplayer.opminecraft.init.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP = Map.of(

            Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
            Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS,
            Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
            Blocks.DIORITE, Blocks.POLISHED_DIORITE,
            Blocks.END_STONE, Blocks.END_STONE_BRICKS,
            Blocks.GRANITE, Blocks.POLISHED_GRANITE,
            Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER,
            Blocks.STONE, Blocks.STONE_BRICKS
    );

    private static final Map<Block, Block> CHISEL_MAP2 = Map.of(

            Blocks.STONE_BRICKS, RegistryIBBI.STONE_TILES.get(),

            RegistryIBBI.DENSE_STONE.get(), RegistryIBBI.DENSE_STONE_BRICKS.get(),
            RegistryIBBI.DENSE_STONE_BRICKS.get(), RegistryIBBI.DENSE_STONE_TILES.get(),

            Blocks.DIAMOND_BLOCK, RegistryIBBI.CHISELED_DIAMOND.get(),
            Blocks.GOLD_BLOCK, RegistryIBBI.CHISELED_GOLD.get(),
            Blocks.IRON_BLOCK, RegistryIBBI.CHISELED_IRON.get(),
            Blocks.NETHERITE_BLOCK, RegistryIBBI.CHISELED_NETHERITE.get()
    );

    public ChiselItem() {
        super(new Properties().stacksTo(1).durability(64).fireResistant().rarity(Rarity.RARE));
    }

    @Override
    public InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        } else if (CHISEL_MAP2.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP2.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
