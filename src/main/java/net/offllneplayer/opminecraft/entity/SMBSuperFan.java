package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import java.util.List;


public class SMBSuperFan extends AbstractArrow {

    public SMBSuperFan(LivingEntity shooter, Level level) {
        super(RegistryEntities.SMB_SUPER_FAN.get(), shooter, level, new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get()), null);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    public SMBSuperFan(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void setPos(double x, double y, double z) {
        super.setPos(x, y, z);

        float shortHalf = 0.1f;
        float longHalf = 0.36f;
        float height = 0.36f;

        Direction dir = Direction.fromYRot(this.getYRot());

        if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            this.setBoundingBox(new AABB(
                    x - shortHalf, y - height, z - longHalf,
                    x + shortHalf, y + height, z + longHalf
            ));
        } else {
            this.setBoundingBox(new AABB(
                    x - longHalf, y - height, z - shortHalf,
                    x + longHalf, y + height, z + shortHalf
            ));
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isAttackable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public float getPickRadius() {
        return 0.2F;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public ItemStack getDefaultPickupItem() {
        return new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        if (!this.level().isClientSide) {
            Entity entity = result.getEntity();

            // Handle bounce logic
            if (entity instanceof Projectile ||
                    entity instanceof ItemFrame ||
                    entity instanceof AbstractMinecart ||
                    entity instanceof EndCrystal ||
                    entity instanceof LivingEntity) {

                if (entity instanceof EndCrystal crystal) {
                    this.level().explode(this, crystal.getX(), crystal.getY(), crystal.getZ(), 6.0F, false, Level.ExplosionInteraction.BLOCK);
                    crystal.discard();
                }


                float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

                this.setDeltaMovement(this.getDeltaMovement().scale(-0.4));
                this.hasImpulse = true;

                if (entity instanceof LivingEntity) {
                    DamageSource fanDamage = this.level().damageSources().source(
                            RegistryDamageTypes.SMB_SUPER_FAN,
                            this,
                            this.getOwner()
                    );

                    entity.hurt(fanDamage, 2.0F);
                    this.level().broadcastEntityEvent(this, (byte) 3);
                }
            }
        }
    }

    @Override
    public void doPostHurtEffects(LivingEntity target) {
        // Prevents arrow from discarding itself after hitting a target.
    }

    @Override
    public void onHitBlock(BlockHitResult result) {
        if (!this.level().isClientSide) {

            super.onHitBlock(result);

            float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
            this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

            this.level().broadcastEntityEvent(this, (byte)3);
        }
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level().isClientSide) {
            ItemStack stack = player.getItemInHand(hand);

            if (stack.isEmpty()) {

                float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

                player.setItemInHand(hand, new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get()));
                this.discard();
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }


    private int customTickCounter = 0;

    @Override
    public void tick() {
        super.tick();

        if (!this.getCommandSenderWorld().isClientSide) {
            customTickCounter++;

            // Every 20 ticks (1 second) play idle sound.
            if (customTickCounter % 20 == 0) {
                float vol = Mth.randomBetween(this.random, 0.2F, 0.420F);
                float tone = Mth.randomBetween(this.random, 0.9F, 1.05F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_IDLE.get(), vol, tone);
            }

            // Every 10 ticks (0.5 seconds) process nearby entities.
            if (customTickCounter % 10 == 0) {
                Level level = this.getCommandSenderWorld();
                AABB boundingBox = this.getBoundingBox().inflate(0.1D);
                List<Entity> entities = level.getEntities(this, boundingBox,
                        entity -> (entity instanceof LivingEntity) || (entity instanceof ItemEntity));

                for (Entity entity : entities) {

                    if (entity instanceof ItemEntity) {// Discard item entities and play hit sound
                        entity.discard();
                        float vol = Mth.randomBetween(this.random, 0.6F, 1F);
                        float tone = Mth.randomBetween(this.random, 1.0F, 1.3F);
                        this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);

                    } else if (entity instanceof LivingEntity) {// Hurt living entities and play hit sound
                        DamageSource fanDamage = level.damageSources().source(
                                RegistryDamageTypes.SMB_SUPER_FAN,
                                this,
                                this.getOwner()
                        );

                        entity.hurt(fanDamage, 2.0F);
                        float vol = Mth.randomBetween(this.random, 0.8F, 1.05F);
                        float tone = Mth.randomBetween(this.random, 0.8F, 1.1F);
                        this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);
                    }
                }
            }
        }
    }

    @Override
    public void tickDespawn() {
        // Do nothing — disables auto-despawn logic
    }

    @Override
    public void checkDespawn() {
        // Prevent despawning due to player distance
    }
}