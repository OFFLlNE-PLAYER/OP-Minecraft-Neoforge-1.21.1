package net.offllneplayer.opminecraft.item;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.entity.SMBSuperFan;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.PutNBT;

import java.util.List;


public class SMBSuperFanItem extends Item {
    public SMBSuperFanItem(){
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide()) return InteractionResultHolder.sidedSuccess(itemstack, true);

        SMBSuperFan fan = new SMBSuperFan(player, level);
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

        PutNBT.writeWeaponDataToEntity(itemstack, fan, level);

        fan.setPos(spawnX, spawnY, spawnZ);
        fan.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.69F, 0.420F);
        level.addFreshEntity(fan);

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);

        float vol = Mth.nextFloat(RandomSource.create(), 0.6F, 1F);
        float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.2F);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.6F, tone);
        level.playSound(null, player.blockPosition(), RegistrySounds.SMB_SUPER_FAN_HIT.get(), SoundSource.PLAYERS, vol, tone);

        return InteractionResultHolder.sidedSuccess(itemstack, false);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.smb_super_fan.description_0"));
    }
}