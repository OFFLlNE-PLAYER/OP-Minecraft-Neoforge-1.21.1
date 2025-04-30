package net.offllneplayer.opminecraft.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.entity.CryingHatchet;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.util.PutNBT;


public class CryingHatchetItem extends SwordItem {

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
                .attributes(AxeItem.createAttributes(TOOL_TIER, 8.5F, -2.69F))
                .stacksTo(1)
                .rarity(Rarity.EPIC));
    }

    @Override
    public int getEnchantmentValue() { return 20;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide()) return InteractionResultHolder.sidedSuccess(itemstack, true);

        CryingHatchet hatchet = new CryingHatchet(player, level);
        double verticalOffset = 1.4;
        double forwardOffset = 0.7;
        double lateralOffset = 0.5;
        double yawRad = Math.toRadians(player.getYRot());
        double forwardX = -Math.sin(yawRad);
        double forwardZ = Math.cos(yawRad);
        double rightX = forwardZ;
        double rightZ = -forwardX;
        if (hand == InteractionHand.MAIN_HAND) lateralOffset = -lateralOffset;

        double spawnX = player.getX() + forwardX * forwardOffset + rightX * lateralOffset;
        double spawnY = player.getY() + verticalOffset;
        double spawnZ = player.getZ() + forwardZ * forwardOffset + rightZ * lateralOffset;

        PutNBT.writeWeaponDataToEntity(itemstack, hatchet, level);

        hatchet.setPos(spawnX, spawnY, spawnZ);
        hatchet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.69F, 0.420F);
        level.addFreshEntity(hatchet);

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);

        float vol = Mth.nextFloat(RandomSource.create(), 0.6F, 1F);
        float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.2F);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, vol, tone);

        return InteractionResultHolder.sidedSuccess(itemstack, false);
    }
}