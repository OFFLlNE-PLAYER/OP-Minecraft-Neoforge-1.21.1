package net.offllneplayer.opminecraft.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.entity.CryingHatchet;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.util.PutNBT;


public class CryingHatchetItem extends TieredItem {

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
            return Ingredient.of(new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
        }
    };

    public CryingHatchetItem() {
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

        CryingHatchet hatchet = new CryingHatchet(player, level);
        PutNBT.writeWeaponDataToEntity(stack, hatchet, level);
        hatchet.setPullRatio(pull);
        hatchet.setPos(x, y, z);
        hatchet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, velocity, 0.420F);
        level.addFreshEntity(hatchet);

        player.awardStat(Stats.ITEM_USED.get(this));
        stack.shrink(1);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0f, 1.0f + pull * 0.2F
        );
    }
}
