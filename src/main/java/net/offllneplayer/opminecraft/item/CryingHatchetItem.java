package net.offllneplayer.opminecraft.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
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
import net.offllneplayer.opminecraft.util.DispensibleProjectile;

public class CryingHatchetItem extends TieredItem implements DispensibleProjectile {


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

        float pull = Mth.clamp((getUseDuration(stack, user) - timeLeft) / 20F, 0F, 1F);
        if (pull < 0.1F) return;

        InteractionHand hand = player.getUsedItemHand();

        double yawRad = Math.toRadians(player.getYRot());
        double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
        double rightX = forwardZ, rightZ = -forwardX;
        double forwardOff = 0.6;
        double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.3 : 0.3;
        double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
        double spawnY = player.getY() + player.getEyeHeight();
        double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

        CryingHatchet hatchet = new CryingHatchet(player, level, stack.copy());

        hatchet.setPullRatio(pull);
        hatchet.setPos(spawnX, spawnY, spawnZ);
        hatchet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * 2.5F, 0.420F);
        level.addFreshEntity(hatchet);

        player.awardStat(Stats.ITEM_USED.get(this));
        stack.shrink(1);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1, 1 + pull * 0.2F);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {

        CryingHatchet hatchet =  new CryingHatchet(null, level, stack.copy());

        hatchet.setPos(pos.x(), pos.y(), pos.z());

        float speed = getDispenseSpeed();
        hatchet.setDeltaMovement(direction.getStepX() * speed, direction.getStepY() * speed + 0.1F, direction.getStepZ() * speed);

        float yRot = switch (direction) {
            case NORTH -> 0F;
            case SOUTH -> 180F;
            case WEST -> 90F;
            case EAST -> 270F;
            default -> 0F;
        };
        hatchet.setYRot(yRot);

        return hatchet;
    }
}
