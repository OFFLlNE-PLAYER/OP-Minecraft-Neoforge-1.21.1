package net.offllneplayer.opminecraft.item.balloon;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class BalloonItem extends Item {
    private final BalloonColor color;
    
    public BalloonItem(BalloonColor color) {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON));
        this.color = color;
    }
    
    /**
     * Gets the color of this balloon
     * @return The balloon color
     */
    public BalloonColor getColor() {
        return color;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.balloon.description_0"));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
        return 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, net.minecraft.world.InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        // Apply effect if item is in main hand or offhand
        if (selected || slot == 40) {
            BalloonActivate_Method.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
        }
    }
}