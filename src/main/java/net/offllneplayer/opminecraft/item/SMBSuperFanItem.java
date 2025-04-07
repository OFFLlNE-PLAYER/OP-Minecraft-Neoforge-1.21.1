package net.offllneplayer.opminecraft.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
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
import net.offllneplayer.opminecraft.entity.SMBSuperFan;

import java.util.List;


public class SMBSuperFanItem extends Item {
    public SMBSuperFanItem(){
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.3F);
        if (!level.isClientSide) {
            SMBSuperFan superFan = new SMBSuperFan(player, level);
            superFan.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(superFan);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.smb_super_fan.description_0"));
    }
}