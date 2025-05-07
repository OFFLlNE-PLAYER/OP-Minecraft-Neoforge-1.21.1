package net.offllneplayer.opminecraft.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.entity.ThrownDynamiteStick;
import net.offllneplayer.opminecraft.init.RegistryDataComponents;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.DispensibleProjectile;


public class DynamiteStickItem extends Item implements DispensibleProjectile {

    // Tag name for the player persistent data
    private static final String FUSE_SOUND_PLAYING_TAG = "DynamiteFuseSoundPlaying";

    // The duration of the fuse sound in ticks (5 seconds = 100 ticks)
    private static final int FUSE_DURATION = 100;

    public DynamiteStickItem() {
        super(new Properties()
                .stacksTo(64)
                .rarity(Rarity.UNCOMMON));
    }

/*--------------------------------------------------------------------------------------------*/
    private void stopFuseSound(Level level, double x, double y, double z) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        double hearingRadius = 24.0;
        ResourceLocation soundId = RegistrySounds.DYNAMITE_FUSE.getId();
        ClientboundStopSoundPacket packet = new ClientboundStopSoundPacket(soundId, SoundSource.NEUTRAL);

        for (ServerPlayer player : serverLevel.getPlayers(p ->
                p.distanceToSqr(x, y, z) <= hearingRadius * hearingRadius)) {
            player.connection.send(packet);
        }
    }

/*--------------------------------------------------------------------------------------------*/
    @Override
    public int getUseDuration(ItemStack itemstack, LivingEntity user) {
        return FUSE_DURATION; // Exactly match fuse sound duration (5 seconds)
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack dynamiteStack = player.getItemInHand(hand);
        InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherHandStack = player.getItemInHand(otherHand);

        if (otherHandStack.getItem() instanceof FlintAndSteelItem) {

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 1F, 1F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), RegistrySounds.DYNAMITE_FUSE.get(), SoundSource.NEUTRAL, 1F, 1.1F);

            otherHandStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(otherHand));
            player.swing(otherHand, true);

            player.startUsingItem(hand);
            return InteractionResultHolder.consume(dynamiteStack);
        }

        return InteractionResultHolder.fail(dynamiteStack);
    }

/*--------------------------------------------------------------------------------------------*/
    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!(user instanceof Player player)) return;

        int timeUsed = getUseDuration(stack, user) - remainingUseTicks;

        // Start playing sound on first tick
        if (timeUsed == 1) {
            player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, true);
        }

        // If we've reached the end of use duration
        if (remainingUseTicks <= 1) {
            level.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.5F);
        }
    }

/*--------------------------------------------------------------------------------------------*/
    @Override
    public void onStopUsing(ItemStack stack, LivingEntity livingEntity, int timeLeft) {
        if (!(livingEntity instanceof Player player)) return;

        // Stop fuse sound if it was playing
        if (player.getPersistentData().getBoolean(FUSE_SOUND_PLAYING_TAG)) {
            stopFuseSound(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
            player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, false);
        }
    }

/*--------------------------------------------------------------------------------------------*/
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
        if (!(user instanceof Player player) || level.isClientSide) return;

        // Stop fuse sound if it was playing
        if (player.getPersistentData().getBoolean(FUSE_SOUND_PLAYING_TAG)) {
            stopFuseSound(level, user.getX(), user.getY(), user.getZ());
            player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, false);
        }

        int timeUsed = getUseDuration(stack, user) - timeLeft;
        float pull = Mth.clamp(timeUsed / (float)FUSE_DURATION, 0F, 1F);


        InteractionHand hand = player.getUsedItemHand();

        // Calculate spawn position offset from player
        double yawRad = Math.toRadians(player.getYRot());
        double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
        double rightX = forwardZ, rightZ = -forwardX;
        double forwardOff = 0.7;
        double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.5 : 0.5;
        double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
        double spawnY = player.getY() + player.getEyeHeight();
        double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

        // Create and setup the thrown dynamite entity
        ThrownDynamiteStick dstick = new ThrownDynamiteStick(player, level, stack.copy());

        // Pass remaining fuse time to entity - this is what makes explosion time dynamic
        int fuseTimeRemaining = FUSE_DURATION - timeUsed;
        dstick.getPersistentData().putInt("dynamite_lit_time", fuseTimeRemaining);

        dstick.setPullRatio(pull);
        dstick.setPos(spawnX, spawnY, spawnZ);
        float velo = Mth.nextFloat(RandomSource.create(), 2.420F, 2.69F);
        dstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull / 1.5F * velo, 0.420F);
        level.addFreshEntity(dstick);

        player.awardStat(Stats.ITEM_USED.get(this));
        stack.shrink(1);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1.0F, 1.1F + pull * 0.2F);
    }

/*--------------------------------------------------------------------------------------------*/
    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ThrownDynamiteStick dstick = new ThrownDynamiteStick(null, level, stack.copy());

        // Transfer lit time data from item to entity
        Integer litTime = stack.get(RegistryDataComponents.DYNAMITE_LIT_TIME.get());
        if (litTime != null && litTime > 0) {
            dstick.getPersistentData().putInt("dynamite_lit_time", litTime);
        }

        dstick.setPos(pos.x(), pos.y(), pos.z());

        float speed = getDispenseSpeed();
        dstick.setDeltaMovement(
                direction.getStepX() * speed,
                direction.getStepY() * speed + 0.1F,
                direction.getStepZ() * speed
        );

        return dstick;
    }
}
