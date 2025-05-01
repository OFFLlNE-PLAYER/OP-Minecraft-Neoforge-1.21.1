package net.offllneplayer.opminecraft.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.entity.SMBSuperFan;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.PutNBT;

import java.util.List;


public class SMBSuperFanItem extends TieredItem {
        private static final Tier TOOL_TIER = new Tier() {
            @Override
            public int getUses() {
                return 420;
            }
            @Override
            public float getSpeed() {
                return 11F;
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
                return Ingredient.EMPTY;
            }
        };

    public SMBSuperFanItem(){
            super(TOOL_TIER, new Properties()
                    .attributes(SwordItem.createAttributes(TOOL_TIER, 4F, -2.69F))
                    .stacksTo(1)
                    .rarity(Rarity.EPIC));
        }

        @Override
        public int getUseDuration(ItemStack itemstack, LivingEntity user) {
            return 60;
        }

        @Override
        public UseAnim getUseAnimation(ItemStack stack) {
            return UseAnim.SPEAR;
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }

        @Override
        public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
            if (!(user instanceof Player player) || level.isClientSide) return;

            int charge = this.getUseDuration(stack, user) - timeLeft;
            float pull  = Mth.clamp(charge / 20f, 0f, 1f);
            if (pull < 0.1f) return;

            float velocity = pull * 2.5f;

            double yawRad = Math.toRadians(player.getYRot());
            double fx = -Math.sin(yawRad), fz =  Math.cos(yawRad);
            double rz = -fx;
            double lat = (player.getUsedItemHand() == InteractionHand.MAIN_HAND ? -0.5 : 0.5);
            double x = player.getX() + fx * 0.7 + fz * lat;
            double y = player.getY() + 1.4;
            double z = player.getZ() + fz * 0.7 + rz * lat;

            SMBSuperFan superFan = new SMBSuperFan(player, level);
            PutNBT.writeWeaponDataToEntity(stack, superFan, level);
            superFan.setPullRatio(pull);
            superFan.setPos(x, y, z);
            superFan.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, velocity, 0.420F);
            level.addFreshEntity(superFan);

            player.awardStat(Stats.ITEM_USED.get(this));
            stack.shrink(1);

            float vol = Mth.nextFloat(RandomSource.create(), 0.6F, 1F);
            float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.2F);
            level.playSound(null, player.blockPosition(), RegistrySounds.SMB_SUPER_FAN_HIT.get(), SoundSource.PLAYERS, vol, tone);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0f, 1.0f + pull * 0.2F
            );
        }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.smb_super_fan.description_0"));
    }
}
