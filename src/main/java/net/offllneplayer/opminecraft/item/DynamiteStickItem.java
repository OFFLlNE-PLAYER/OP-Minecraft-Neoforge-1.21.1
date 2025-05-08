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
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.DispensibleProjectile;


public class DynamiteStickItem extends Item implements DispensibleProjectile {

    // Tag name for the player persistent data
    private static final String FUSE_SOUND_PLAYING_TAG = "DynamiteFuseSoundPlaying";

    // The duration of the fuse sound in ticks (5 seconds = 100 ticks)
    private static final int FUSE_DURATION = 100;

    public DynamiteStickItem() {
        super(new Properties()
                .stacksTo(4)
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
        return FUSE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }


    /*--------------------------------------------------------------------------------------------*/
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack dynamiteStack = player.getItemInHand(hand);
        InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherHandStack = player.getItemInHand(otherHand);

        if (otherHandStack.getItem() instanceof FlintAndSteelItem) {
            if (player.isUnderWater() || player.getCooldowns().isOnCooldown(this)) {
                return InteractionResultHolder.fail(dynamiteStack);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 1F, 1F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), RegistrySounds.DYNAMITE_FUSE.get(), SoundSource.NEUTRAL, 1F, 1.1420F);

            otherHandStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(otherHand));
            player.swing(otherHand, true);

            player.getCooldowns().addCooldown(this, 40);

            player.startUsingItem(hand);
            return InteractionResultHolder.consume(dynamiteStack);
        }

        // If no flint and steel in other hand
        player.displayClientMessage(net.minecraft.network.chat.Component.literal("Jack Black's words echoed... FLINT AND STEEL!"), true);
        player.swing(hand, true);
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
        float pull = Mth.clamp(timeUsed / (float) FUSE_DURATION, 0.420F, 1.0420F);

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

        // Pass remaining fuse time to entity using our new setter method
        int fuseTimeRemaining = FUSE_DURATION - timeUsed;
        dstick.setLitTime(fuseTimeRemaining);

        dstick.setPullRatio(pull);
        dstick.setPos(spawnX, spawnY, spawnZ);
        float velo = Mth.nextFloat(RandomSource.create(), 1.420F, 2.0420F);
        dstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * velo, 0.420F);
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
        dstick.setLitTime(100);  // Use our new setter
        dstick.setPos(pos.x(), pos.y(), pos.z());


        float speed = getDispenseSpeed() * 0.8F;
        RandomSource random = level.getRandom();
        float randomSpread = random.nextFloat() * 0.1F - 0.05F; // -0.05 to 0.05

        // Special handling for UP and DOWN directions
        if (direction == Direction.UP) {
            // For upward dispensers, send mostly upward with slight random horizontal spread
            float randomAngle = random.nextFloat() * ((float) Math.PI * 2);
            dstick.setDeltaMovement(
                    Math.cos(randomAngle) * randomSpread * speed,
                    speed, // Primarily upward movement
                    Math.sin(randomAngle) * randomSpread * speed
            );
        } else if (direction == Direction.DOWN) {
            // For downward dispensers, send mostly downward with slight random horizontal spread
            float randomAngle = random.nextFloat() * ((float) Math.PI * 2);
            dstick.setDeltaMovement(
                    Math.cos(randomAngle) * randomSpread * speed,
                    -speed, // Primarily downward movement
                    Math.sin(randomAngle) * randomSpread * speed
            );
        } else {
            // Horizontal dispensers (NORTH, SOUTH, EAST, WEST)
            float randomUpAngle = random.nextFloat() * 0.420F;
            dstick.setDeltaMovement(
                    direction.getStepX() * speed + (direction.getStepX() == 0 ? randomSpread : 0),
                    0.2F + randomUpAngle, // Slight upward arc for horizontal motion
                    direction.getStepZ() * speed + (direction.getStepZ() == 0 ? randomSpread : 0)
            );
        }

        // Set rotation based on direction
        float yRot = switch (direction) {
            case NORTH -> 0F;
            case SOUTH -> 180F;
            case WEST -> 90F;
            case EAST -> 270F;
            case UP, DOWN -> random.nextFloat() * 360F; // Random rotation for up/down
            default -> 0F;
        };
        dstick.setYRot(yRot);

        level.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL, 1.0F, 1.0F);

        return dstick;
    }
}
