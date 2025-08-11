package net.offllneplayer.opminecraft.items._item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class SculkHammerItem extends Item {

    public SculkHammerItem() {super(new Properties()
                .rarity(Rarity.RARE)
                .durability(400)
                .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Ensure this runs only on the server side and the hammer is in the main hand
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            ItemStack hammer = player.getItemInHand(hand); // Hammer in main hand
            ItemStack offHandItem = player.getOffhandItem(); // Item in off-hand

            // Validate off-hand must have an items, be repairable, and be damaged
            if (!offHandItem.isEmpty() && offHandItem.isDamageableItem() && offHandItem.getDamageValue() > 0) {


                int durabilityroll = Mth.nextInt(RandomSource.create(), 5, 40);

                // Reduce hammer durability by roll, breaking it if empty durability is reached
                int newHammerDurability = hammer.getDamageValue() + durabilityroll;

                if (newHammerDurability >= hammer.getMaxDamage()) {
                    player.setItemInHand(hand, ItemStack.EMPTY); // Break the hammer
                    float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.1F);
                    level.playSound(null, player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, tone);
                    level.playSound(null, player.blockPosition(), SoundEvents.SCULK_BLOCK_BREAK, SoundSource.PLAYERS, 1.2F, tone);
                } else {
                    hammer.setDamageValue(newHammerDurability);
                }

                // Repair the off-hand items, ensuring it does not exceed full durability
                int offHandDamageValue = offHandItem.getDamageValue();
                offHandItem.setDamageValue(Math.max(offHandDamageValue - durabilityroll, 0));

                // Play the smithing table sound to indicate the repair action. dink dink dink.
                float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.2F);
                level.playSound(null, player.blockPosition(), SoundEvents.SMITHING_TABLE_USE, SoundSource.PLAYERS, 1.0F, tone);

                // Apply a short cooldown to prevent rapid reuse
                player.getCooldowns().addCooldown(this, 10);

                return InteractionResultHolder.success(hammer);
            }
        }
        return InteractionResultHolder.fail(player.getItemInHand(hand)); // Return failure if conditions aren't met
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.sculk_hammer.description_0"));
    }
}