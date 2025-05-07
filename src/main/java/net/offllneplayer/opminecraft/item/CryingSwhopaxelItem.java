package net.offllneplayer.opminecraft.item;

import static java.util.Map.entry;

import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import net.offllneplayer.opminecraft.util.TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.method.crying.essence.effect.ApplyCrying1_Method;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class CryingSwhopaxelItem extends DiggerItem {
    private static final Tier TOOL_TIER = new Tier() {
        @Override
        public int getUses() { return 420;
        }
        @Override
        public float getSpeed() { return 11f;
        }
        @Override
        public float getAttackDamageBonus() { return 0;
        }
        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }
        @Override
        public int getEnchantmentValue() { return 20;
        }
        @Override
        public Ingredient getRepairIngredient() {return Ingredient.of(new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
        }
    };

    private static final Map<Block, Block> STRIPPABLES = Map.ofEntries(
            entry(Blocks.ACACIA_WOOD,     Blocks.STRIPPED_ACACIA_WOOD),
            entry(Blocks.ACACIA_LOG,      Blocks.STRIPPED_ACACIA_LOG),
            entry(Blocks.BAMBOO_BLOCK,    Blocks.STRIPPED_BAMBOO_BLOCK),
            entry(Blocks.BIRCH_WOOD,      Blocks.STRIPPED_BIRCH_WOOD),
            entry(Blocks.BIRCH_LOG,       Blocks.STRIPPED_BIRCH_LOG),
            entry(Blocks.CHERRY_WOOD,     Blocks.STRIPPED_CHERRY_WOOD),
            entry(Blocks.CHERRY_LOG,      Blocks.STRIPPED_CHERRY_LOG),
            entry(Blocks.CRIMSON_STEM,    Blocks.STRIPPED_CRIMSON_STEM),
            entry(Blocks.CRIMSON_HYPHAE,  Blocks.STRIPPED_CRIMSON_HYPHAE),
            entry(Blocks.DARK_OAK_WOOD,   Blocks.STRIPPED_DARK_OAK_WOOD),
            entry(Blocks.DARK_OAK_LOG,    Blocks.STRIPPED_DARK_OAK_LOG),
            entry(Blocks.JUNGLE_WOOD,     Blocks.STRIPPED_JUNGLE_WOOD),
            entry(Blocks.JUNGLE_LOG,      Blocks.STRIPPED_JUNGLE_LOG),
            entry(Blocks.MANGROVE_WOOD,   Blocks.STRIPPED_MANGROVE_WOOD),
            entry(Blocks.MANGROVE_LOG,    Blocks.STRIPPED_MANGROVE_LOG),
            entry(Blocks.OAK_WOOD,        Blocks.STRIPPED_OAK_WOOD),
            entry(Blocks.OAK_LOG,         Blocks.STRIPPED_OAK_LOG),
            entry(Blocks.SPRUCE_WOOD,     Blocks.STRIPPED_SPRUCE_WOOD),
            entry(Blocks.SPRUCE_LOG,      Blocks.STRIPPED_SPRUCE_LOG),
            entry(Blocks.WARPED_STEM,     Blocks.STRIPPED_WARPED_STEM),
            entry(Blocks.WARPED_HYPHAE,   Blocks.STRIPPED_WARPED_HYPHAE)
    );

    private static final Map<Block, Block> FLATTENABLES = Map.ofEntries(
            entry(Blocks.COARSE_DIRT, Blocks.DIRT),
            entry(Blocks.ROOTED_DIRT, Blocks.DIRT),
            entry(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH),
            entry(Blocks.DIRT,        Blocks.DIRT_PATH),
            entry(Blocks.MYCELIUM,    Blocks.DIRT_PATH),
            entry(Blocks.PODZOL,      Blocks.DIRT_PATH)
    );

    private static final Map<Block, Block> TILLABLES = Map.ofEntries(
            entry(Blocks.COARSE_DIRT, Blocks.FARMLAND),
            entry(Blocks.ROOTED_DIRT, Blocks.FARMLAND),
            entry(Blocks.GRASS_BLOCK, Blocks.FARMLAND),
            entry(Blocks.DIRT,        Blocks.FARMLAND),
            entry(Blocks.DIRT_PATH,   Blocks.FARMLAND),
            entry(Blocks.MYCELIUM,    Blocks.FARMLAND),
            entry(Blocks.PODZOL,      Blocks.FARMLAND)
    );


    public CryingSwhopaxelItem() {
        super(TOOL_TIER,
                TagKeyUtil.Blocks.CRYING_SWHOPAXEL,
                new Properties()
                        .attributes(DiggerItem.createAttributes(TOOL_TIER, 12F, -3.3F))
                        .rarity(Rarity.EPIC)
        );
    }

    @Override public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        ApplyCrying1_Method.execute(target);
        return result;
    }

    @Override public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        if (playerHasShieldUseIntent(context)) return InteractionResult.PASS;

        Optional<BlockState> override = evaluateNewBlockState(level, pos, player, level.getBlockState(pos), context);
        if (override.isPresent()) {
            if (player instanceof ServerPlayer sp) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(sp, pos, context.getItemInHand());
            }
            BlockState newState = override.get();
            level.setBlock(pos, newState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        BlockState state = level.getBlockState(pos);
        if (context.getClickedFace() == Direction.DOWN) return InteractionResult.PASS;

        BlockState toSet = null;

        if (state.is(Blocks.FIRE) || state.is(Blocks.SOUL_FIRE)) {
             if (!level.isClientSide()) {
                 level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
             }
             toSet = Blocks.AIR.defaultBlockState();

         } else
             if (state.getBlock() instanceof CampfireBlock && state.getValue(CampfireBlock.LIT)) {
             if (!level.isClientSide()) {
                 level.levelEvent(null, 1009, pos, 0);
             }
             CampfireBlock.dowse(player, level, pos, state);
             toSet = state.setValue(CampfireBlock.LIT, false);

         } else
             if (state.getBlock() instanceof RootedDirtBlock) {
             ItemEntity e = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.HANGING_ROOTS));
             e.setPickUpDelay(5);
             level.addFreshEntity(e);
         } else
             if (level.getBlockState(pos.above()).isAir()) {
                 if (player.isCrouching()) {
                    Block flat = FLATTENABLES.get(state.getBlock());
                    if (flat != null) {
                        toSet = flat.defaultBlockState();
                        if (!(state.getBlock() instanceof DirtPathBlock) && !(state.getBlock() instanceof FarmBlock)) {
                        level.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1f, 1f);
                        }
                    }
                 } else {
                    Block tilled = TILLABLES.get(state.getBlock());
                     if (tilled != null) {
                        toSet = tilled.defaultBlockState();
                        if (!(state.getBlock() instanceof FarmBlock)) {
                        level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1f, 1f);
                        }
                    }
                }
             }
        if (toSet != null) {
            if (!level.isClientSide()) {
                level.setBlock(pos, toSet, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, toSet));
                if (toSet.getBlock() != Blocks.AIR) {
                    context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }

    private Optional<BlockState> evaluateNewBlockState(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext p_40529_) {

        var strip = Optional.ofNullable(state.getToolModifiedState(p_40529_, ItemAbilities.AXE_STRIP, false));
        if (strip.isPresent()) {
            level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1F, 1F);
            return strip;
        }

        var scrape = Optional.ofNullable(state.getToolModifiedState(p_40529_, ItemAbilities.AXE_SCRAPE, false));
        if (scrape.isPresent()) {
            level.playSound(null, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1F, 1F);
            level.levelEvent(player, 3005, pos, 0);
            return scrape;
        }

        var waxOff = Optional.ofNullable(state.getToolModifiedState(p_40529_, ItemAbilities.AXE_WAX_OFF, false));
        if (waxOff.isPresent()) {
            level.playSound(null, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1F, 1F);
            level.levelEvent(player, 3004, pos, 0);
            return waxOff;
        }

        return Optional.empty();
    }

    private static boolean playerHasShieldUseIntent(UseOnContext p_40529_) {
        Player player = p_40529_.getPlayer();
        return p_40529_.getHand() == InteractionHand.MAIN_HAND && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }

    private Optional<BlockState> getStripped(BlockState state) {
        return Optional.ofNullable(STRIPPABLES.get(state.getBlock()))
                .map(b -> b.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));
    }

    @Override public boolean canPerformAction(ItemStack stack, ItemAbility ability) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(ability) || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(ability) || ItemAbilities.DEFAULT_HOE_ACTIONS.contains(ability);
    }
}
