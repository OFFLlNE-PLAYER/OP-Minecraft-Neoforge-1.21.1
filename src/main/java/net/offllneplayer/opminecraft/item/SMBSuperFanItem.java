package net.offllneplayer.opminecraft.item;

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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.entity.SMBSuperFan;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import java.util.List;


public class SMBSuperFanItem extends Item {
    public SMBSuperFanItem(){
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.3F, 1.1F);

        if (!level.isClientSide) {
            SMBSuperFan superFan = new SMBSuperFan(player, level);

            // Offsets:
            double verticalOffset = 1.4;   // Shoulder level (adjust as needed)
            double forwardOffset = 0.7;    // How far forward from the torso
            double lateralOffset = 0.5;    // How far to the side from the torso

            // Get player's yaw in radians:
            double yawRad = Math.toRadians(player.getYRot());

            // Calculate forward vector (horizontal only)
            double forwardX = -Math.sin(yawRad);
            double forwardZ =  Math.cos(yawRad);

            // Calculate right vector (perpendicular to forward)
            double rightX = forwardZ;
            double rightZ = -forwardX;

            // If using the main-hand, reverse the lateral offset
            if (hand == InteractionHand.MAIN_HAND) {
                lateralOffset = -lateralOffset;
            }

            // Final spawn coordinates:
            double spawnX = player.getX() + forwardX * forwardOffset + rightX * lateralOffset;
            double spawnY = player.getY() + verticalOffset;
            double spawnZ = player.getZ() + forwardZ * forwardOffset + rightZ * lateralOffset;

            // Set the projectile's starting position
            superFan.setPos(spawnX, spawnY, spawnZ);
            superFan.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.69F, 0.420F);
            level.addFreshEntity(superFan);

            float vol = Mth.nextFloat(RandomSource.create(), 0.6F, 1F);
            float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.2F);
            level.playSound(null, player.blockPosition(), RegistrySounds.SMB_SUPER_FAN_HIT.get(), SoundSource.PLAYERS, vol, tone);

            player.awardStat(Stats.ITEM_USED.get(this));
            itemstack.consume(1, player);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.smb_super_fan.description_0"));
    }
}