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

import java.util.Objects;

public class ChiselItem extends Item {
    public ChiselItem() {
        super(new Properties().stacksTo(1).durability(64).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) { return InteractionResult.PASS; }

        BlockPos pos = context.getClickedPos();
        BlockState state = serverLevel.getBlockState(pos);
        Block block = state.getBlock();
        Block newBlock = null;

        if (block == Blocks.ANDESITE) { newBlock = Blocks.POLISHED_ANDESITE; }
        else if (block == Blocks.POLISHED_ANDESITE) { newBlock = Blocks.ANDESITE; }
        else if (block == Blocks.BASALT) { newBlock = Blocks.SMOOTH_BASALT; }
        else if (block == Blocks.SMOOTH_BASALT) { newBlock = Blocks.POLISHED_BASALT; }
        else if (block == Blocks.POLISHED_BASALT) { newBlock = Blocks.BASALT; }
        else if (block == Blocks.BLACKSTONE) { newBlock = Blocks.POLISHED_BLACKSTONE; }
        else if (block == Blocks.POLISHED_BLACKSTONE) { newBlock = Blocks.POLISHED_BLACKSTONE_BRICKS; }
        else if (block == Blocks.POLISHED_BLACKSTONE_BRICKS) { newBlock = Blocks.CHISELED_POLISHED_BLACKSTONE; }
        else if (block == Blocks.CHISELED_POLISHED_BLACKSTONE) { newBlock = Blocks.BLACKSTONE; }
        else if (block == Blocks.DEEPSLATE) { newBlock = Blocks.POLISHED_DEEPSLATE; }
        else if (block == Blocks.POLISHED_DEEPSLATE) { newBlock = Blocks.DEEPSLATE_BRICKS; }
        else if (block == Blocks.DEEPSLATE_BRICKS) { newBlock = Blocks.DEEPSLATE_TILES; }
        else if (block == Blocks.DEEPSLATE_TILES) { newBlock = Blocks.CHISELED_DEEPSLATE; }
        else if (block == Blocks.CHISELED_DEEPSLATE) { newBlock = Blocks.DEEPSLATE; }
        else if (block == Blocks.DIORITE) { newBlock = Blocks.POLISHED_DIORITE; }
        else if (block == Blocks.POLISHED_DIORITE) { newBlock = Blocks.DIORITE; }
        else if (block == Blocks.GRANITE) { newBlock = Blocks.POLISHED_GRANITE; }
        else if (block == Blocks.POLISHED_GRANITE) { newBlock = Blocks.GRANITE; }
        else if (block == Blocks.END_STONE) { newBlock = Blocks.END_STONE_BRICKS; }
        else if (block == Blocks.END_STONE_BRICKS) { newBlock = Blocks.END_STONE; }
        else if (block == Blocks.NETHER_BRICKS) { newBlock = Blocks.CHISELED_NETHER_BRICKS; }
        else if (block == Blocks.CHISELED_NETHER_BRICKS) { newBlock = Blocks.NETHER_BRICKS; }
        else if (block == Blocks.QUARTZ_BLOCK) { newBlock = Blocks.QUARTZ_BRICKS; }
        else if (block == Blocks.QUARTZ_BRICKS) { newBlock = Blocks.QUARTZ_PILLAR; }
        else if (block == Blocks.QUARTZ_PILLAR) { newBlock = Blocks.CHISELED_QUARTZ_BLOCK; }
        else if (block == Blocks.CHISELED_QUARTZ_BLOCK) { newBlock = Blocks.QUARTZ_BLOCK; }
        else if (block == Blocks.SANDSTONE) { newBlock = Blocks.SMOOTH_SANDSTONE; }
        else if (block == Blocks.SMOOTH_SANDSTONE) { newBlock = Blocks.CHISELED_SANDSTONE; }
        else if (block == Blocks.CHISELED_SANDSTONE) { newBlock = Blocks.SANDSTONE; }
        else if (block == Blocks.RED_SANDSTONE) { newBlock = Blocks.SMOOTH_RED_SANDSTONE; }
        else if (block == Blocks.SMOOTH_RED_SANDSTONE) { newBlock = Blocks.CHISELED_RED_SANDSTONE; }
        else if (block == Blocks.CHISELED_RED_SANDSTONE) { newBlock = Blocks.RED_SANDSTONE; }
        else if (block == Blocks.TUFF) { newBlock = Blocks.POLISHED_TUFF; }
        else if (block == Blocks.POLISHED_TUFF) { newBlock = Blocks.CHISELED_TUFF; }
        else if (block == Blocks.CHISELED_TUFF) { newBlock = Blocks.CHISELED_TUFF_BRICKS; }
        else if (block == Blocks.CHISELED_TUFF_BRICKS) { newBlock = Blocks.TUFF; }
        else if (block == Blocks.STONE) { newBlock = Blocks.STONE_BRICKS; }
        else if (block == Blocks.STONE_BRICKS) { newBlock = RegistryBIBI.STONE_TILES.get(); }
        else if (block == RegistryBIBI.STONE_TILES.get()) { newBlock = Blocks.CHISELED_STONE_BRICKS; }
        else if (block == Blocks.CHISELED_STONE_BRICKS) { newBlock = Blocks.STONE; }
        else if (block == RegistryBIBI.DENSE_STONE.get()) { newBlock = RegistryBIBI.DENSE_STONE_BRICKS.get(); }
        else if (block == RegistryBIBI.DENSE_STONE_BRICKS.get()) { newBlock = RegistryBIBI.DENSE_STONE_TILES.get(); }
        else if (block == RegistryBIBI.DENSE_STONE_TILES.get()) { newBlock = RegistryBIBI.DENSE_STONE.get(); }
        else if (block == Blocks.DIAMOND_BLOCK) { newBlock = RegistryBIBI.CHISELED_DIAMOND.get(); }
        else if (block == RegistryBIBI.CHISELED_DIAMOND.get()) { newBlock = Blocks.DIAMOND_BLOCK; }
        else if (block == Blocks.GOLD_BLOCK) { newBlock = RegistryBIBI.CHISELED_GOLD.get(); }
        else if (block == RegistryBIBI.CHISELED_GOLD.get()) { newBlock = Blocks.GOLD_BLOCK; }
        else if (block == Blocks.IRON_BLOCK) { newBlock = RegistryBIBI.CHISELED_IRON.get(); }
        else if (block == RegistryBIBI.CHISELED_IRON.get()) { newBlock = Blocks.IRON_BLOCK; }
        else if (block == Blocks.NETHERITE_BLOCK) { newBlock = RegistryBIBI.CHISELED_NETHERITE.get(); }
        else if (block == RegistryBIBI.CHISELED_NETHERITE.get()) { newBlock = Blocks.NETHERITE_BLOCK; }
        else if (block == RegistryBIBI.CRYING_BRICKS.get()) { newBlock = RegistryBIBI.CRYING_TILES.get(); }
        else if (block == RegistryBIBI.CRYING_TILES.get()) { newBlock = RegistryBIBI.CRYING_BRICKS.get(); }

        if (newBlock != null) {
            serverLevel.setBlockAndUpdate(pos, newBlock.defaultBlockState());
            context.getItemInHand().hurtAndBreak(1, serverLevel, context.getPlayer(),
                    item -> Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            serverLevel.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
