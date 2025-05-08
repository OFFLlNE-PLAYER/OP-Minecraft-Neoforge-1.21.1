package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.RegistryDataComponents;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public class ThrownDynamiteStick extends AbstractArrow {

    private static final EntityDataAccessor<Integer> DATA_LIT_TIME = SynchedEntityData.defineId(ThrownDynamiteStick.class, EntityDataSerializers.INT);

    // Standard entity data
    private BlockPos stuckPos;
    private Block stuckBlock;
    private Direction stuckDirection = Direction.NORTH; // Initialize with a default value

    // Rotation for the spinning in air
    private float spinRotation = 0F;

    // Pull ratio from throw
    private float pullRatio = 1F;


    public ThrownDynamiteStick(EntityType<? extends ThrownDynamiteStick> type, Level level) {
        super(type, level);
        this.noPhysics = false;
    }

    public ThrownDynamiteStick(Level world, LivingEntity shooter) {
        super(RegistryEntities.THROWN_DYNAMITE_STICK.get(), world);
        this.setOwner(shooter);
        this.noPhysics = false;

        if (shooter != null) {
            this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
            // Set initial direction based on shooter's facing
            this.stuckDirection = Direction.fromYRot(shooter.getYRot());
        }
        this.pickup = Pickup.DISALLOWED;
    }

    public ThrownDynamiteStick(Player shooter, Level world, ItemStack stack) {
        this(world, shooter);
        // Set the lit time using our synchronized data
        this.entityData.set(DATA_LIT_TIME, stack.get(RegistryDataComponents.DYNAMITE_LIT_TIME.get()));
    }

    // Getter/setter methods for the lit time
    public int getLitTime() {
        return this.entityData.get(DATA_LIT_TIME);
    }

    public void setLitTime(int time) {
        this.entityData.set(DATA_LIT_TIME, time);
    }

    public boolean isGrounded() {
        return inGround;
    }

    public void setPullRatio(float pullRatio) {
        this.pullRatio = pullRatio;
    }

    public Direction getStuckDirection() {
        return stuckDirection != null ? stuckDirection : Direction.NORTH;
    }

    public float getSpinRotation() {
        return spinRotation;
    }


/*--------------------------------------------------------------------------------------------*/

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_LIT_TIME, -1); // Default to -1 (not lit)
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("dynamite_lit_time", this.getLitTime());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("dynamite_lit_time")) {
            this.setLitTime(compound.getInt("dynamite_lit_time"));
        }
    }

/*--------------------------------------------------------------------------------------------*/
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
            // Ensure we have a valid direction
            Direction dir = this.getStuckDirection();

            if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                // Lying along Z axis
                this.setBoundingBox(new AABB(
                        x - 0.105D,    // Left
                        y - 0.16D,    // Bottom
                        z - 0.42D,   // Front/fuse
                        x + 0.105D,    // Right
                        y + 0.07D, // Top (flat on ground)
                        z + 0.25D // Back
                ));
            } else {
                // Lying along X axis
                this.setBoundingBox(new AABB(
                        x - 0.25D,   // Left
                        y - 0.16D,    // Bottom
                        z - 0.0225D,    // Front/fuse
                        x + 0.25D,   // Right
                        y + 0.07D, // Top (flat on ground)
                        z + 0.105D // Back
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

    @Override
    public ItemStack getDefaultPickupItem() {
        return new ItemStack(RegistryIBBI.DYNAMITE_STICK.get());
    }
    @Override
    public boolean canBeCollidedWith() { return true; }
    @Override
    public boolean isAttackable() { return true; }
    @Override
    public boolean isInvulnerableTo(DamageSource source) { return true; }
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

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void tick() {
        super.tick();

        // Update spin rotation in flight
        if (!this.inGround) {
            spinRotation = (spinRotation + pullRatio * 12F) % 360F;
        }

        // If the block we're stuck in changes, unstick
        if (this.inGround && stuckPos != null) {
            if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                this.inGround = false;
                this.hasImpulse = true;
                this.setDeltaMovement(Vec3.ZERO);
            }
        }

        if (this.isOnFire() && this.getLitTime() == -1) {

            this.setLitTime(100);
            this.playSound(RegistrySounds.DYNAMITE_FUSE.get(), 1F, 1.1420F);
        }

        if (this.isInLava()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Level.ExplosionInteraction.TNT);
            this.discard();
            return;
        }

        // Handle countdown to explosion
        if (!this.level().isClientSide()) {
            // Server-side code
            int litTime = this.getLitTime();

            if (litTime > 0) {
                if (this.isInWaterRainOrBubble()) {
                    this.setLitTime(-1);
                    this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
                    return;
                }

                if (litTime % 40 == 0) {
                    // Play sizzle sound periodically
                    float tone = 1.69F + 0.31F * (1F - litTime / 100F);
                    this.playSound(RegistrySounds.DYNAMITE_SIZZLE.get(), 1.0F, tone);
                }

                // Decrement the timer
                this.setLitTime(litTime - 1);
            } else if (litTime == 0) {
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Level.ExplosionInteraction.TNT);
                this.discard();
            }
        }
    }


    /*--------------------------------------------------------------------------------------------*/
    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level().isClientSide()) {
            if (player.getItemInHand(hand).isEmpty()) {
                ItemStack dynamite = new ItemStack(RegistryIBBI.DYNAMITE_STICK.get());
                dynamite.set(RegistryDataComponents.DYNAMITE_LIT_TIME.get(), -1);
                player.setItemInHand(hand, dynamite);
                this.discard();
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

/*--------------------------------------------------------------------------------------------*/
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

/*--------------------------------------------------------------------------------------------*/
    @Override
    public void onHitBlock(BlockHitResult result) {
        if (this.level().isClientSide()) return;

        Direction hitFace = result.getDirection();
        Vec3 motion = this.getDeltaMovement();

        stuckPos = result.getBlockPos();
        stuckBlock = level().getBlockState(stuckPos).getBlock();

        if (hitFace == Direction.UP) {
            float motionLength = (float) motion.length();

            // If we have meaningful velocity when hitting the ground, bounce
            if (motionLength > 0.2420F) {
                // Create bounce effect - reduce vertical speed and maintain some horizontal momentum
                float bounceStrength = Math.min(0.6F * motionLength, 0.9F);  // Scale bounce with speed
                Vec3 bounceVec = new Vec3(
                        motion.x * (0.69D + motionLength * 0.1), // More horizontal momentum retention at higher speeds
                        -motion.y * bounceStrength,  // Bounce height scales with impact speed
                        motion.z * (0.69D + motionLength * 0.1)  // More horizontal momentum retention at higher speeds
                );

                this.setDeltaMovement(bounceVec);
                this.inGround = false;
                this.hasImpulse = true;

                float tone = Mth.randomBetween(this.random, 0.3F, 0.420F);
                this.playSound(SoundEvents.BAMBOO_HIT, Math.min(0.2F * motionLength, 0.6F), tone);

                return;
            }

            // When velocity is low enough, go to resting position
            this.inGround = true;
            this.hasImpulse = false;
            this.spinRotation = 0F;


            // Set the facing direction based on motion
            if (Math.abs(motion.x) > Math.abs(motion.z)) {
                stuckDirection = motion.x > 0 ? Direction.EAST : Direction.WEST;
            } else {
                stuckDirection = motion.z > 0 ? Direction.SOUTH : Direction.NORTH;
            }

            this.setYRot(stuckDirection.toYRot());
            this.setDeltaMovement(Vec3.ZERO);

            float tone = Mth.randomBetween(this.random, 0.3F, 0.420F);
            this.playSound(SoundEvents.BAMBOO_PLACE, 0.2F, tone);

        } else if (hitFace != Direction.DOWN) {

            // reflection with dampening based on speed
            float motionLength = (float) motion.length();
            float dampening = 0.7f + motionLength * 0.1f;
            dampening = Math.min(dampening, 0.95f);

            Vec3 normal = Vec3.atLowerCornerOf(hitFace.getNormal());
            Vec3 reflected = motion.subtract(normal.scale(2 * motion.dot(normal))).scale(dampening);

            this.setDeltaMovement(reflected);
            this.inGround = false;
            this.hasImpulse = true;

            float tone = Mth.randomBetween(this.random, 0.3F, 0.420F);
            this.playSound(SoundEvents.BAMBOO_HIT, Math.min(0.2F * motionLength, 0.6F), tone);
        }

        updateHitbox();
    }
}

