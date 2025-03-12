package net.offllneplayer.opminecraft.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.entity.ThrownTNTStick;


public class TNTStickItem extends Item implements ProjectileItem {
    public TNTStickItem(){
        super(new Properties().stacksTo(4).rarity(Rarity.UNCOMMON));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL, 0.5F, 0.6F);
        if (!level.isClientSide) {
            ThrownTNTStick tntstick = new ThrownTNTStick(player, level);
            tntstick.setItem(itemstack);
            tntstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 0.8F, 3F);
            level.addFreshEntity(tntstick);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ThrownTNTStick tntstick = new ThrownTNTStick(level, pos.x(), pos.y(), pos.z());
        tntstick.setItem(stack);
        return tntstick;
    }
}