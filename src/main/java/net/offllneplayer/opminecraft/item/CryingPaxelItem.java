package net.offllneplayer.opminecraft.item;

import com.google.common.collect.ImmutableMap;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.offllneplayer.opminecraft.init.DeclareTagKeys;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.method.crying.essence.effect.ApplyCrying1_Method;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class CryingPaxelItem extends DiggerItem {

    private static final Tier TOOL_TIER = new Tier() {
        @Override
        public int getUses() {
            return 420;
        }

        @Override
        public float getSpeed() {
            return 11f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
        }
    };

    protected static final Map<Block, Block> STRIPPABLES = new ImmutableMap.Builder<Block, Block>()
        .put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
        .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
        .put(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
        .put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
        .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
        .put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD)
        .put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG)
        .put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
        .put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
        .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
        .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
        .put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
        .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
        .put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD)
        .put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG)
        .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
        .put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
        .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
        .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
        .put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
        .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
       .build();

    public CryingPaxelItem() {
        super(TOOL_TIER, DeclareTagKeys.Blocks.CRYING_PAXEL, new Properties()
                .attributes(DiggerItem.createAttributes(TOOL_TIER, 10.5f, -3.2f))
                .rarity(Rarity.EPIC));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);

        ApplyCrying1_Method.execute(entity);

        return retval;
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        Player player = pContext.getPlayer();


        if (playerHasShieldUseIntent(pContext)) {
            return InteractionResult.PASS;
        } else {
            Optional<BlockState> optional = this.evaluateNewBlockState(level, blockpos, player, level.getBlockState(blockpos), pContext);
            if (optional.isEmpty()) {
                BlockState blockstate = level.getBlockState(blockpos);
                if (pContext.getClickedFace() == Direction.DOWN) {
                    return InteractionResult.PASS;

                } else {

                    BlockState blockstate1 = blockstate.getToolModifiedState(pContext, ItemAbilities.SHOVEL_FLATTEN, false);
                    BlockState blockstate2 = blockstate.getToolModifiedState(pContext, ItemAbilities.HOE_TILL, false);
                    BlockState blockstatetoset = null;

                    if (level.getBlockState(blockpos.above()).isAir()) {

                        if (player.isCrouching()) {
                            Map<Block, Block> FLATTENABLES = new ImmutableMap.Builder<Block, Block>()
                                .put(Blocks.COARSE_DIRT, Blocks.DIRT)
                                .put(Blocks.ROOTED_DIRT, Blocks.DIRT)
                                .put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH)
                                .put(Blocks.DIRT, Blocks.DIRT_PATH)
                                .put(Blocks.FARMLAND, Blocks.DIRT)
                                .put(Blocks.MYCELIUM, Blocks.DIRT_PATH)
                                .put(Blocks.PODZOL, Blocks.DIRT_PATH)
                               .build();

                            blockstatetoset = blockstate1;

                            if (!(blockstate.getBlock() instanceof DirtPathBlock) && !(blockstate.getBlock() instanceof FarmBlock)) {
                                level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                            }

                        } else {

                            Map<Block, Block> FLATTENABLES = new ImmutableMap.Builder<Block, Block>()
                                    .put(Blocks.COARSE_DIRT, Blocks.DIRT)
                                    .put(Blocks.ROOTED_DIRT, Blocks.DIRT)
                                    .put(Blocks.GRASS_BLOCK, Blocks.FARMLAND)
                                    .put(Blocks.DIRT, Blocks.FARMLAND)
                                    .put(Blocks.DIRT_PATH, Blocks.FARMLAND)
                                    .put(Blocks.MYCELIUM, Blocks.FARMLAND)
                                    .put(Blocks.PODZOL, Blocks.FARMLAND)
                                   .build();

                            blockstatetoset = blockstate2;

                            if (!(blockstate.getBlock() instanceof FarmBlock)) {
                                level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                            }
                        }

                    } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                        if (!level.isClientSide()) {
                            level.levelEvent(null, 1009, blockpos, 0);
                        }

                        CampfireBlock.dowse(pContext.getPlayer(), level, blockpos, blockstate);
                        blockstatetoset = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));

                    } else if (blockstate.getBlock() instanceof RootedDirtBlock) {
                        int x = blockpos.getX();
                        int y = blockpos.getY();
                        int z = blockpos.getZ();
                            ItemEntity entityToSpawn = new ItemEntity(level, x, y, z, new ItemStack(Items.HANGING_ROOTS));
                            entityToSpawn.setPickUpDelay(5);
                            level.addFreshEntity(entityToSpawn);
                    }

                    if (blockstatetoset != null) {
                        if (!level.isClientSide) {
                            level.setBlock(blockpos, blockstatetoset, 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstatetoset));
                            if (player != null) {
                                pContext.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
                            }
                        }

                        return InteractionResult.sidedSuccess(level.isClientSide);
                    } else {
                        return InteractionResult.PASS;
                    }
                }
            } else {
                ItemStack itemstack = pContext.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                }

                level.setBlock(blockpos, optional.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
    }

    private Optional<BlockState> evaluateNewBlockState(Level pLevel, BlockPos pPos, @Nullable Player pPlayer, BlockState pState, UseOnContext p_40529_) {
        Optional<BlockState> optional = Optional.ofNullable(pState.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ItemAbilities.AXE_STRIP, false));
        if (optional.isPresent()) {
            pLevel.playSound(pPlayer, pPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return optional;
        } else {
            Optional<BlockState> optional1 = Optional.ofNullable(pState.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ItemAbilities.AXE_SCRAPE, false));
            if (optional1.isPresent()) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.levelEvent(pPlayer, 3005, pPos, 0);
                return optional1;
            } else {
                Optional<BlockState> optional2 = Optional.ofNullable(pState.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ItemAbilities.AXE_WAX_OFF, false));
                if (optional2.isPresent()) {
                    pLevel.playSound(pPlayer, pPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    pLevel.levelEvent(pPlayer, 3004, pPos, 0);
                    return optional2;
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    private static boolean playerHasShieldUseIntent(UseOnContext p_345141_) {
        Player player = p_345141_.getPlayer();
        return p_345141_.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }

    private Optional<BlockState> getStripped(BlockState pUnstrippedState) {
        return Optional.ofNullable(STRIPPABLES.get(pUnstrippedState.getBlock()))
                .map(p_150689_ -> p_150689_.defaultBlockState().setValue(RotatedPillarBlock.AXIS, pUnstrippedState.getValue(RotatedPillarBlock.AXIS)));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility) || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility) || ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
    }
}

