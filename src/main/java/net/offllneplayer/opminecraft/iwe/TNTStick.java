package net.offllneplayer.opminecraft.iwe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Position;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;

import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import net.minecraft.stats.Stats;

import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;

import net.offllneplayer.opminecraft.client.ModModelLayers;

import net.offllneplayer.opminecraft.init.RegistryDataComponents;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.method.UTIL.OP_getBarColorUtil;

import java.util.stream.StreamSupport;


public class TNTStick {

  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
/*[~AS ITEM~]*/
    public static class TNTStickItem extends Item implements DispensibleProjectile {

     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*[VARIABLES]*/
        private static final String FUSE_SOUND_PLAYING_TAG = "TNTFuseSoundPlaying";
        private static final int FUSE_DURATION = 100;

      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
    /*[BUILDER]*/
        public TNTStickItem() {
            super(new Properties()
                    .stacksTo(4)
                    .rarity(Rarity.UNCOMMON));
        }

      /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
     /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
    /*[HELP]*/
        private void stopFuseSound(Level level, double x, double y, double z) {
            if (!(level instanceof ServerLevel serverLevel)) return;

            double hearingRadius = 24.0;
            ResourceLocation soundId = RegistrySounds.TNT_FUSE.getId();
            ClientboundStopSoundPacket packet = new ClientboundStopSoundPacket(soundId, SoundSource.NEUTRAL);

            for (ServerPlayer player : serverLevel.getPlayers(p ->
                    p.distanceToSqr(x, y, z) <= hearingRadius * hearingRadius)) {
                player.connection.send(packet);
            }
        }


      /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
     /* ====================================================================================*/
    /*[Durability Bar]*/
       @Override
       public boolean isBarVisible(ItemStack stack) {
          // Only show the bar when the item is being used
          if (Minecraft.getInstance().player == null) return false;
          return Minecraft.getInstance().player.getUseItem() == stack;
       }

       @Override
       public int getBarWidth(ItemStack stack) {
          // Get the player using the item
          Player player = Minecraft.getInstance().player;
          if (player == null || player.getUseItem() != stack) return 13; // Full bar when not in use

          // Calculate how much of the fuse duration has elapsed
          int remainingUseTicks = player.getUseItemRemainingTicks();
          // Map the remaining ticks to the bar width (13 = full, 0 = empty)
          return Math.round(13.0F * remainingUseTicks / (float)FUSE_DURATION);
       }

     @Override
     public int getBarColor(ItemStack stack) {
        // Create a yellow to orange to red gradient as the fuse burns
        Player player = Minecraft.getInstance().player;
        if (player == null || player.getUseItem() != stack) return OP_getBarColorUtil.hexToInt("FFFF00"); // Yellow when not in use

        // Calculate progress as a value between 0 and 1
        float progress = 1.0F - (float) player.getUseItemRemainingTicks() / FUSE_DURATION;

        return OP_getBarColorUtil.calculateBarColor(progress,
           "BDFF2A",  // Early stage - pastel yellow (189, 255, 42)
           "A9B92A",  // Mid stage (169, 185, 42)
           "A90016",  // Late stage - dark red (169, 0, 22)
           "8B000C",  // Final base - darker red (139, 0, 12)
           "1E160A",  // Final pulse amounts (30, 22, 10)
           8.0F);     // Pulse frequency, higher = more pulses
     }



     /* ====================================================================================*/
     /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
    /*[Use Item OVERRIDES]*/
      @Override
      public int getUseDuration(ItemStack itemstack, LivingEntity user) {return FUSE_DURATION;}
     @Override
     public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.SPEAR;}

      /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
     /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
    /*[use]*/
      @Override
      public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
          ItemStack tstack = player.getItemInHand(hand);
          InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
          ItemStack otherHandStack = player.getItemInHand(otherHand);

          // Check if player is crouching and other hand does not have flint and steel
          if (player.isCrouching() && !(otherHandStack.getItem() instanceof FlintAndSteelItem)) {
              if (player.isUnderWater() || player.getCooldowns().isOnCooldown(this)) {
                  return InteractionResultHolder.fail(tstack);
              }

              if (!level.isClientSide) {
                  // Create and setup the thrown TNT entity with minimum pull
                  ThrownTNTStick tstick = new ThrownTNTStick(player, level, tstack.copy());
                  float minPull = 0.420F;
                  double yawRad = Math.toRadians(player.getYRot());
                  double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
                  double rightX = forwardZ, rightZ = -forwardX;
                  double forwardOff = 0.7;
                  double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.5 : 0.5;
                  double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
                  double spawnY = player.getY() + player.getEyeHeight() + 0.22D;
                  double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

                  tstick.setPullRatio(minPull);
                  tstick.setPos(spawnX, spawnY, spawnZ);

                  float randomVelo = Mth.nextFloat(RandomSource.create(), 1.69420F, 2.2420F);

                  // Throw immediately with minimum pull
                  tstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, minPull * randomVelo, 0.420F);
                  level.addFreshEntity(tstick);

                  player.awardStat(Stats.ITEM_USED.get(this));
                  tstack.shrink(1);

                  level.playSound(null, player.getX(), player.getY(), player.getZ(),
                          SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.69F, 0.420F * randomVelo + minPull);

                  player.getCooldowns().addCooldown(this, 20);
              }

              return InteractionResultHolder.sidedSuccess(tstack, level.isClientSide);
          }

          // Original code for when player is not crouching
          if (otherHandStack.getItem() instanceof FlintAndSteelItem) {
              if (player.isUnderWater() || player.getCooldowns().isOnCooldown(this)) {
                  return InteractionResultHolder.fail(tstack);
              }
              level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 1F, 1F);
              level.playSound(null, player.getX(), player.getY(), player.getZ(), RegistrySounds.TNT_FUSE.get(), SoundSource.NEUTRAL, 1F, 1.1420F);
              otherHandStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(otherHand));
              player.swing(otherHand, true);
              player.getCooldowns().addCooldown(this, 20);
              player.startUsingItem(hand);
              return InteractionResultHolder.consume(tstack);
          }

          // If no flint and steel in other hand
          player.displayClientMessage(Component.literal("Jack Black's words echoed... FLINT AND STEEL!"), true);
          player.swing(hand, true);
          return InteractionResultHolder.fail(tstack);
      }


      /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
     /*-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___*/
    /*[on Use Tick]*/
      @Override
      public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
          if (!(user instanceof Player player)) return;

          int timeUsed = getUseDuration(stack, user) - remainingUseTicks;

          // Start playing sound on first tick 
          if (timeUsed == 1) {
              player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, true);
          }

// Calculate hand position for particles
          double yawRad = Math.toRadians(player.getYRot());
          double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
          double rightX = forwardZ, rightZ = -forwardX;
          double fuseProgress = 1.0F - (float) remainingUseTicks / FUSE_DURATION;
          double forwardOff = -0.8D + (0.14D * fuseProgress);
          double handOffset = player.getUsedItemHand() == InteractionHand.MAIN_HAND ? -0.3D : 0.3D;
          double particleX = player.getX() + forwardX * forwardOff + rightX * handOffset;
          double particleY = player.getY() + player.getEyeHeight() + 0.24D;
          double particleZ = player.getZ() + forwardZ * forwardOff + rightZ * handOffset;

// Spawn flame particles at hand position every 2 ticks
          if (level.isClientSide() && timeUsed > 1 && timeUsed % 2 == 0) {
              level.addParticle(ParticleTypes.FLAME,
                      particleX, particleY, particleZ,
                      Mth.nextDouble(level.getRandom(), 0.01D, 0.02D),
                      Mth.nextDouble(level.getRandom(), 0.01D, 0.02D),
                      Mth.nextDouble(level.getRandom(), 0.01D, 0.02D));
          }


          // If we've reached the end of use duration
          if (remainingUseTicks <= 1) {
              player.swing(player.getUsedItemHand(), true);
              level.playSound(null, user.getX(), user.getY(), user.getZ(),
                      SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.5F);
          }
      }

      /*-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___*/
     /* X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-*/
    /*[on Stop Using]*/
        @Override
        public void onStopUsing(ItemStack stack, LivingEntity livingEntity, int timeLeft) {
            if (!(livingEntity instanceof Player player)) return;

            // Stop fuse sound if it was playing
            if (player.getPersistentData().getBoolean(FUSE_SOUND_PLAYING_TAG)) {
                stopFuseSound(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
                player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, false);
            }
        }

      /* X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-*/
     /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
    /*[release Using]*/
        @Override
        public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
            if (!(user instanceof Player player) || level.isClientSide) return;

            // Stop fuse sound if it was playing
            if (player.getPersistentData().getBoolean(FUSE_SOUND_PLAYING_TAG)) {
                stopFuseSound(level, user.getX(), user.getY(), user.getZ());
                player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, false);
            }

            int timeUsed = getUseDuration(stack, user) - timeLeft;
            float pull = Mth.clamp(timeUsed / (float) FUSE_DURATION, 0.420F, 1F);

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

            // Create and setup the thrown TNT entity
            ThrownTNTStick tstick = new ThrownTNTStick(player, level, stack.copy());

            // Pass remaining fuse time to entity using our new setter method
            int fuseTimeRemaining = FUSE_DURATION - timeUsed;
            tstick.setLitTime(fuseTimeRemaining);

            tstick.setPullRatio(pull);
            tstick.setPos(spawnX, spawnY, spawnZ);

            float randomVelo = Mth.nextFloat(RandomSource.create(), 1.69420F, 2.2420F);

            tstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * randomVelo, 0.420F);
            level.addFreshEntity(tstick);

            player.awardStat(Stats.ITEM_USED.get(this));
            stack.shrink(1);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.69F, 0.420F * randomVelo + pull);
        }

      /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
     /*-----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>*/
    /*[as Projectile]*/
        @Override
        public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
            ThrownTNTStick tstick = new ThrownTNTStick(null, level, stack.copy());
            tstick.setLitTime(100);  // Use our new setter
            tstick.setPos(pos.x(), pos.y(), pos.z());

            float randomVelo = Mth.nextFloat(RandomSource.create(), 1.69420F, 2.2420F);
            float speed = getDispenseSpeed() * randomVelo;
            RandomSource random = level.getRandom();
            float randomSpread = random.nextFloat() * 0.1F - 0.05F; // -0.05 to 0.05

            // Special handling for UP and DOWN directions
            if (direction == Direction.UP) {
                // For upward dispensers, send mostly upward with slight random horizontal spread
                float randomAngle = random.nextFloat() * ((float) Math.PI * 2);
                tstick.setDeltaMovement(
                        Math.cos(randomAngle) * randomSpread * speed,
                        speed, // Primarily upward movement
                        Math.sin(randomAngle) * randomSpread * speed
                );
            } else if (direction == Direction.DOWN) {
                // For downward dispensers, send mostly downward with slight random horizontal spread
                float randomAngle = random.nextFloat() * ((float) Math.PI * 2);
                tstick.setDeltaMovement(
                        Math.cos(randomAngle) * randomSpread * speed,
                        -speed, // Primarily downward movement
                        Math.sin(randomAngle) * randomSpread * speed
                );
            } else {
                // Horizontal dispensers (NORTH, SOUTH, EAST, WEST)
                float randomUpAngle = random.nextFloat() * 0.420F;
                tstick.setDeltaMovement(
                        direction.getStepX() * speed + (direction.getStepX() == 0 ? randomSpread : 0),
                        0.269F + randomUpAngle, // Slight upward arc for horizontal motion
                        direction.getStepZ() * speed + (direction.getStepZ() == 0 ? randomSpread : 0)
                );
            }

            tstick.setYRot(random.nextFloat() * 360F);

            level.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL, 1.0F, 1.0F);

            return tstick;
        }
    }


       /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
      /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*[~AS ENTITY~]*/
    public static class ThrownTNTStick extends AbstractArrow {

     /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
    /*[DATA]*/

        private static final EntityDataAccessor<Integer> TNT_LIT_TIMER = SynchedEntityData.defineId(ThrownTNTStick.class, EntityDataSerializers.INT);
        private static final EntityDataAccessor<Float> TNT_SPIN_ROTATION = SynchedEntityData.defineId(ThrownTNTStick.class, EntityDataSerializers.FLOAT);
        private static final EntityDataAccessor<Float> TNT_GROUNDED_zROTATION = SynchedEntityData.defineId(ThrownTNTStick.class, EntityDataSerializers.FLOAT);

      /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*[VARIABLES]*/
        private BlockPos stuckPos;
        private Block stuckBlock;
        private Direction stuckDirection = Direction.NORTH;
        private float pullRatio = 1F;

      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
    /*[BUILDERS]*/
        public ThrownTNTStick(EntityType<? extends ThrownTNTStick> type, Level level) {
            super(type, level);
            this.noPhysics = false;
        }

        public ThrownTNTStick(Level world, LivingEntity shooter) {
            super(RegistryEntities.THROWN_TNT_STICK.get(), world);
            this.setOwner(shooter);
            this.noPhysics = false;


            if (shooter != null) {
                this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
                // Set initial direction based on shooter's facing
                this.stuckDirection = Direction.fromYRot(shooter.getYRot());
            }
            this.pickup = Pickup.DISALLOWED;
        }

          public ThrownTNTStick(Player shooter, Level world, ItemStack stack) {
             this(world, shooter);

             this.entityData.set(TNT_LIT_TIMER, -1);
             this.entityData.set(TNT_SPIN_ROTATION, 12F);
             this.entityData.set(TNT_GROUNDED_zROTATION, 0F);

          }


      /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
     /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
    /*[HELP]*/
        public int getLitTime() {
            return this.entityData.get(TNT_LIT_TIMER);
        }

        public void setLitTime(int time) {
            this.entityData.set(TNT_LIT_TIMER, time);
        }

        public void setPullRatio(float pullRatio) {
            this.pullRatio = pullRatio;
        }

        public boolean isGrounded() {
            return inGround;
        }

        public Direction getStuckDirection() {
            return stuckDirection != null ? stuckDirection : Direction.NORTH;
        }

        public float getSpinRotation() {
            return this.entityData.get(TNT_SPIN_ROTATION);
        }

        public void setSpinRotation(float rotation) {
            this.entityData.set(TNT_SPIN_ROTATION, rotation);
        }

        public float getGroundedzRotation() {
            return this.entityData.get(TNT_GROUNDED_zROTATION);
        }

        public void setGroundedzRotation(float rotation) {
            this.entityData.set(TNT_GROUNDED_zROTATION, rotation);
        }


      /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*[BASIC Entity OVERRIDES]*/
        @Override
        public ItemStack getDefaultPickupItem() { return new ItemStack(RegistryBIBI.TNT_STICK.get());}
        @Override
        public boolean canBeCollidedWith() { return true; }
        @Override
        public boolean isAttackable() { return true; }
        @Override
        public boolean isInvulnerableTo(DamageSource source) { return true; }
        @Override
        public boolean displayFireAnimation() { return false; }
        @Override
        public boolean isPickable() { return true; }
        @Override
        public float getPickRadius() { return 0.2F; }
        @Override
        public boolean shouldRenderAtSqrDistance(double distance) { return true; }
        @Override
        protected void updateRotation() {/*VOIDED vanilla abstract arrow rot*/}
        @Override
        public void doPostHurtEffects(LivingEntity target) {/*VOIDED Discard entity on hit*/}

      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
    /*[DATA SYNC]*/
        @Override
        protected void defineSynchedData(SynchedEntityData.Builder builder) {
            super.defineSynchedData(builder);
            builder.define(TNT_LIT_TIMER, -1); // Default to -1 (not lit)
            builder.define(TNT_SPIN_ROTATION, 12F);
            builder.define(TNT_GROUNDED_zROTATION, 0F);
        }

        @Override
        public void addAdditionalSaveData(CompoundTag compound) {
            super.addAdditionalSaveData(compound);
            compound.putInt("tnt_lit_timer", this.getLitTime());
            compound.putFloat("tnt_spin_rotation", this.getSpinRotation());
            compound.putFloat("tnt_grounded_zrotation", this.getGroundedzRotation());
        }

        @Override
        public void readAdditionalSaveData(CompoundTag compound) {
            super.readAdditionalSaveData(compound);
            if (compound.contains("tnt_lit_timer")) {
                this.setLitTime(compound.getInt("tnt_lit_timer"));
            }
            if (compound.contains("tnt_spin_rotation")) {
                this.setSpinRotation(compound.getFloat("tnt_spin_rotation"));
            }
            if (compound.contains("tnt_grounded_zrotation")) {
                this.setGroundedzRotation(compound.getFloat("tnt_grounded_zrotation"));
            }
        }

      /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
     /*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
    /*[HITBOX]*/
        @Override
        public void setPos(double x, double y, double z) {
            super.setPos(x, y, z);
            updateHitbox();
        }

        private void updateHitbox() {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();

            if (this.inGround) {
                Direction dir = this.getStuckDirection();

                if (dir == Direction.NORTH) {
                    this.setBoundingBox(new AABB(
                            x - 0.11420D,    // Left
                            y - 0.16D,    // Bottom
                            z - 0.420D,   // Front/fuse
                            x + 0.11420D,    // Right
                            y + 0.072D, // Top (flat on ground)
                            z + 0.25D // Back
                    ));
                } else if (dir == Direction.SOUTH) {
                    this.setBoundingBox(new AABB(
                            x - 0.11420D,    // Left
                            y - 0.16D,    // Bottom
                            z - 0.25D,   // Front
                            x + 0.11420D,    // Right
                            y + 0.072D, // Top
                            z + 0.420D // Back/fuse
                    ));
                } else if (dir == Direction.EAST) {
                    this.setBoundingBox(new AABB(
                            x - 0.25D,   // Front
                            y - 0.16D,    // Bottom
                            z - 0.11420D,    // Left
                            x + 0.420D,   // Back/fuse
                            y + 0.072D, // Top
                            z + 0.11420D // Right
                    ));
                } else if (dir == Direction.WEST) {
                    this.setBoundingBox(new AABB(
                            x - 0.420D,   // Front/fuse
                            y - 0.16D,    // Bottom
                            z - 0.11420D,    // Left
                            x + 0.25D,   // Back
                            y + 0.072D, // Top
                            z + 0.11420D // Right
                    ));
                }
            } else {
                // In flight
                this.setBoundingBox(new AABB(
                        x - 0.25D,    // Left
                        y - 0.16D,    // Bottom
                        z - 0.25D,   // Back
                        x + 0.25D,    // Right
                        y + 0.5D, // Top (flat on ground)
                        z + 0.25D    // Front
                ));
            }
        }

      /*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
     /*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
    /*[tick]*/
        @Override
        public void tick() {
            super.tick();

              // Update spin rotation in flight
            if (!this.inGround) {
                // Get the current velocity magnitude
                Vec3 motion = this.getDeltaMovement();
                float velocity = (float) motion.length();

                // Base rotation speed - scales with initial pull ratio
                float baseRotationSpeed = 12F;

                // Velocity factor - tnt spins faster when moving faster
                // We use a minimum factor to prevent it from stopping spinning completely while still in air
                float velocityFactor = Math.max(0.5F, Math.min(1.0F, velocity * 2.420F));

                // Calculate the final rotation speed
                float rotationSpeed = baseRotationSpeed * velocityFactor;

                // Update the spin rotation
                this.setSpinRotation((this.getSpinRotation() + rotationSpeed) % 360F);
            }

            // If the block we're stuck in changes, unstick
            if (this.inGround && stuckPos != null) {
                if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                    this.inGround = false;
                    this.hasImpulse = true;
                    this.setDeltaMovement(Vec3.ZERO);
                }
            }

            if (!this.level().isClientSide()) {
                // Handle countdown
                int litTime = this.getLitTime();

                if (litTime > 0) {
                    if (this.isInWaterRainOrBubble()) {
                        this.setLitTime(-1);
                        this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);

                    } else {
                        this.setLitTime(litTime - 1);
                    }

                    if (litTime % 40 == 0) {
                        // Play sizzle sound periodically
                        float tone = 1.69F + 0.31F * (1F - litTime / 100F);
                        this.playSound(RegistrySounds.TNT_SIZZLE.get(), 0.8F, tone);
                    }
                }
            }
        }


          /*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
         /*-- ____________________________________________________________________________________________--*/
          @Override
          public void baseTick() {
             super.baseTick();

             if (!this.level().isClientSide()) {
                BlockState state = this.level().getBlockState(blockPosition());

                if (state.is(BlockTags.FIRE) || state.is(Blocks.SOUL_FIRE) || state.is(Blocks.MAGMA_BLOCK) || this.isInLava() || getLitTime() == 0) {
                   this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, false, Level.ExplosionInteraction.TNT);
                   this.discard();
                }
             }
          }


      /*-- ____________________________________________________________________________________________--*/
     /*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
    /*[interact]*/
        @Override
        public InteractionResult interact(Player player, InteractionHand hand) {
            if (!this.level().isClientSide()) {
                if (player.getItemInHand(hand).isEmpty()) {
                    ItemStack tnt = new ItemStack(RegistryBIBI.TNT_STICK.get());
                   player.setItemInHand(hand, tnt);
                    this.discard();
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }

      /*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
     /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
    /*[on Hit Entity]*/
        @Override
        public void onHitEntity(EntityHitResult result) {
            if (this.level().isClientSide()) return;

            Entity hitEntity = result.getEntity();

            // Simple bounce with reduced velocity
            Vec3 currentPos = this.position();
            Vec3 entityPos = hitEntity.position();
            Vec3 motion = this.getDeltaMovement();

            // Calculate bounce direction away from entity
            Vec3 bounceDir = currentPos.subtract(entityPos).normalize();

            this.setDeltaMovement(bounceDir.x * motion.x * 0.5, motion.y * 0.2, bounceDir.z * motion.z * 0.5);
            this.hasImpulse = true;

            // Play a wood/tnt bouncing sound
            float tone = Mth.randomBetween(this.random, 0.8F, 1.2F);
            this.playSound(SoundEvents.BAMBOO_HIT, 0.6F, tone);
        }

      /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
     /*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
    /*[on Hit Block]*/
        @Override
        public void onHitBlock(BlockHitResult result) {

            Direction hitFace = result.getDirection();
            Vec3 motion = this.getDeltaMovement();
            float motionLength = (float) motion.length();

            stuckPos = result.getBlockPos();
            stuckBlock = level().getBlockState(stuckPos).getBlock();

            boolean forceRest = false;

            float tone = Mth.randomBetween(this.random, 0.3F, 0.420F);

            if (hitFace == Direction.UP) {
                // Use a smooth transition between bouncing and resting
                float tooLowForBounce = 0.169F;

                // Initialize bounce counter if it doesn't exist
                if (!this.getPersistentData().contains("lowBounceCount")) {
                    this.getPersistentData().putInt("lowBounceCount", 0);
                }

                int bounceCount = this.getPersistentData().getInt("lowBounceCount");
                bounceCount++;
                this.getPersistentData().putInt("lowBounceCount", bounceCount);

                if (motionLength > tooLowForBounce && bounceCount <= 3) {
                    // Bounce!
                    Vec3 bounceVec = new Vec3(
                            motion.x * Mth.nextFloat(this.random, 0.369F * motionLength, 0.420F * motionLength), // X horizontal momentum retention
                            -motion.y * Mth.nextFloat(this.random, 0.420F * motionLength, 0.69F * motionLength),  // Bounce with negative motion
                            motion.z * Mth.nextFloat(this.random, 0.369F * motionLength, 0.420F * motionLength)  // Z horizontal momentum retention
                    );

                    this.setDeltaMovement(bounceVec);
                    this.inGround = false;
                    this.hasImpulse = true;

                    this.playSound(SoundEvents.BAMBOO_HIT, Math.min(0.2F * motionLength, 0.6F), tone);

                } else {
                    // Go to resting position

                    float randomGroundedzRotation;

                    // Set the z roll based on motion
                    if (Math.abs(motion.x) > Math.abs(motion.z)) {
                        this.stuckDirection = motion.x > 0 ? Direction.EAST : Direction.WEST;
                        randomGroundedzRotation =
                            this.random.nextInt(5) == 0 ? -170F :
                            this.random.nextInt(5) == 1 ? -175.8F :
                            this.random.nextInt(5) == 2 ? 180F :
                            this.random.nextInt(5) == 3 ? 184.20F : 190F;

                    } else {
                        this.stuckDirection = motion.z > 0 ? Direction.SOUTH : Direction.NORTH;
                        randomGroundedzRotation =
                            this.random.nextInt(5) == 0 ? -10F :
                            this.random.nextInt(5) == 1 ? -5.2F :
                            this.random.nextInt(5) == 2 ? -4.6F :
                            this.random.nextInt(5) == 3 ? 0F : 4.8F;
                    }

                    this.inGround = true;
                    this.getPersistentData().putInt("lowBounceCount", 0);
                    this.setGroundedzRotation(randomGroundedzRotation);
                    this.setYRot(stuckDirection.toYRot());
                    this.setDeltaMovement(Vec3.ZERO);

                    this.playSound(SoundEvents.BAMBOO_PLACE, 0.2F, tone);
                }

            } else if (hitFace != Direction.DOWN) {

                // reflection with dampening based on speed
                float dampening = 0.420F + motionLength * 0.1420F;
                dampening = Math.min(dampening, 0.95F);

                Vec3 normal = Vec3.atLowerCornerOf(hitFace.getNormal());
                Vec3 reflected = motion.subtract(normal.scale(2 * motion.dot(normal))).scale(dampening);

                this.setDeltaMovement(reflected);
                this.inGround = false;
                this.hasImpulse = true;

                this.playSound(SoundEvents.BAMBOO_HIT, Math.min(0.2F * motionLength, 0.6F), tone);
            }

            // Always update the hitbox at the end of collision processing
            updateHitbox();
        }
    }


      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
/*[ENTITY RENDERER]*/
    public static class ThrownTNTStickRenderer extends EntityRenderer<ThrownTNTStick> {

        private final ThrownTNTStickModel model;

        public ThrownTNTStickRenderer(EntityRendererProvider.Context context) {
            super(context);
            this.model = new ThrownTNTStickModel(context.bakeLayer(ModModelLayers.THROWN_TNT_STICK));
        }

        @Override
        public void render(ThrownTNTStick entity, float entityYaw, float partialTicks, PoseStack poseStack,
                           MultiBufferSource bufferSource, int packedLight) {
            poseStack.pushPose();

            Direction dir;

            if (entity.isGrounded()) {
                // Handle the grounded state
                dir = entity.getStuckDirection();

                poseStack.mulPose(Axis.YP.rotationDegrees(dir.toYRot()));
                poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getGroundedzRotation()));

                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(90));
                } else if (dir == Direction.EAST || dir == Direction.WEST) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(270));
                }

             

                if (entity.level().isClientSide() && !(entity.getLitTime() == -1)) {
                    if (entity.getLitTime() < 60) {
                        double offset = 0.32D + (0.4D * (entity.getLitTime() / 100.0D));
                        double extraParticleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
                        double extraParticleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
                        double extraParticleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
                        entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.010420D, 0.0D);
                    }
                    if (entity.getLitTime() < 40) {
                        double offset = 0.3D + (0.1420D * (entity.getLitTime() / 100.0D));
                        double extraParticleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
                        double extraParticleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
                        double extraParticleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
                        entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.01420D, 0.0D);
                    }
                }


            } else {
                // Handle the in-flight state

                dir = entity.getMotionDirection();

                if (dir == Direction.UP || dir == Direction.DOWN) dir = Direction.NORTH;
                
                if (dir == Direction.NORTH) {
                    poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                    poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() - entity.getSpinRotation()));
                } else if (dir == Direction.SOUTH) {
                    poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                    poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() + entity.getSpinRotation()));
                } else if (dir == Direction.EAST) {
                    poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getYRot() - entity.getSpinRotation()));
                    poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
                } else if (dir == Direction.WEST) {
                    poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getYRot() - entity.getSpinRotation()));
                    poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
                }
            }

            poseStack.translate(-0.420D, -0.2D, 0.420D);


            if (entity.level().isClientSide() && !(entity.getLitTime() == -1)) {
                if (entity.getLitTime() > 0) {
                    double offset = 0.24D + (0.420D * (entity.getLitTime() / 100.0D));
                    double particleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
                    double particleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
                    double particleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
                    entity.level().addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, 0.010420D, 0.0D);
                }
                if (entity.getLitTime() < 30) {
                    double offset = -0.26D + (0.1420D * (entity.getLitTime() / 100.0D));
                    double extraParticleX = entity.getX() + (dir == Direction.EAST ? offset : dir == Direction.WEST ? -offset : 0);
                    double extraParticleY = entity.getY() + (dir == Direction.UP ? offset : dir == Direction.DOWN ? -offset : 0);
                    double extraParticleZ = entity.getZ() + (dir == Direction.SOUTH ? offset : dir == Direction.NORTH ? -offset : 0);
                    entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.02420D, 0.0D);
                }
                if (entity.getLitTime() < 15) {
                    double extraParticleX = entity.getX();
                    double extraParticleY = entity.getY();
                    double extraParticleZ = entity.getZ();
                    entity.level().addParticle(ParticleTypes.FLAME, extraParticleX, extraParticleY, extraParticleZ, 0.0D, 0.0169D, 0.0D);
                }
            }

            // Render the model once for either case
            VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                    bufferSource, this.model.renderType(this.getTextureLocation(entity)), false, false);
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
            super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
        }

        @Override
        public ResourceLocation getTextureLocation(ThrownTNTStick entity) {
            if (entity.getLitTime() > 0) {
                return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_tnt_stick_lit.png");
            } else {
                return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "textures/entity/text_thrown_tnt_stick.png");
            }
        }
    }


     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
   /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
/* -^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^ */
     /*[ENTITY MODEL]*/
    public static class ThrownTNTStickModel extends EntityModel<ThrownTNTStick> {
        private final ModelPart tntstick;

        public ThrownTNTStickModel(ModelPart root) {
            this.tntstick = root.getChild("tntstick");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition bb_main = partdefinition.addOrReplaceChild("tntstick", CubeListBuilder.create()
                    .texOffs(20, 21).addBox(5.0F, 0.0F, -8.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(3, 12).addBox(5.5F, 8.0F, -7.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(27, 9).addBox(5.6F, 10.8F, -6.5F, 0.8F, 1.5F, 0.85F, new CubeDeformation(0.0F))
                    .texOffs(27, 9).addBox(5.5F, 12.2F, -6.1F, 0.6F, 1.0F, 0.6F, new CubeDeformation(0.0F))
                    .texOffs(26, 10).addBox(5.7F, 9.0F, -7.0F, 1.3F, 1.8F, 1.3F, new CubeDeformation(0.0F)),
                    PartPose.offset(0.0F, 0.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 32, 32);
        }

        @Override
        public void setupAnim(TNTStick.ThrownTNTStick entity, float v, float v1, float v2, float v3, float v4) {/*VOIDED vanilla nonsense*/}
        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
            tntstick.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }
    }


/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
}
