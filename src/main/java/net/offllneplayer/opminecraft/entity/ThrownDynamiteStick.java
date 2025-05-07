package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
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


public class ThrownDynamiteStick extends AbstractArrow {

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
        CompoundTag data = this.getPersistentData();
        data.putInt("dynamite_lit_time", stack.getDamageValue());
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
    public void setPos(double x, double y, double z) {
        super.setPos(x, y, z);
        updateHitbox();
    }

    private void updateHitbox() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        double width = 0.21D;
        double length = 0.5D;

        if (this.inGround) {
            // Ensure we have a valid direction
            Direction dir = this.getStuckDirection();
            System.out.println("Stuck Direction: " + dir); // Keep the debug line

            if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                // Lying along Z axis
                this.setBoundingBox(new AABB(
                        x - width / 2,    // Left
                        y - 0.15,    // Bottom
                        z - length - 0.17 / 2,   // Front/fuse
                        x + width / 2,    // Right
                        y + 0.15D /2, // Top (flat on ground)
                        z + length / 2 // Back
                ));
            } else {
                // Lying along X axis
                this.setBoundingBox(new AABB(
                        x - length / 2,   // Left
                        y - 0.15,    // Bottom
                        z - width + 0.17 / 2,    // Front/fuse
                        x + length / 2,   // Right
                        y + 0.15D / 2, // Top (flat on ground)
                        z + width / 2 // Back
                ));
            }
        } else {
            // In flight
            this.setBoundingBox(new AABB(
                    x - length / 2,    // Left
                    y,    // Bottom
                    z - length / 2,   // Back
                    x + length / 2,    // Right
                    y + length, // Top (flat on ground)
                    z + length / 2    // Front
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
    public boolean displayFireAnimation() { return false; }
    @Override
    protected void updateRotation() {/*VOIDED vanilla abstract arrow rot*/}
    @Override
    public void tickDespawn() {/*VOIDED despawning due to tick time*/}
    @Override
    public void checkDespawn() {/*VOIDED despawning due to player distance*/}
    @Override
    public void doPostHurtEffects(LivingEntity target) {/*VOIDED Discard entity on hit*/}

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void tick() {
        super.tick();

        // Handle countdown to explosion
        if (!this.level().isClientSide()) {
            int litTime = this.getPersistentData().getInt("dynamite_lit_time");
            if (litTime > 0) {
                this.getPersistentData().putInt("dynamite_lit_time", litTime - 1);
            } else {
                    if (!this.isInWater()) {
                        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Level.ExplosionInteraction.TNT);
                        this.discard();
                        return;

                }else {
                    Level level = level();
                    ItemEntity entityToSpawn = new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(RegistryIBBI.DYNAMITE_STICK.get()));
                    entityToSpawn.setPickUpDelay(5);
                    level.addFreshEntity(entityToSpawn);

                    this.discard();
                    return;
                }
            }
        }

        // Update spin rotation in flight
        if (!this.inGround) {
            spinRotation = (spinRotation + pullRatio * 10F) % 360F;
        }

        // If the block we're stuck in changes, unstick
        if (this.inGround && stuckPos != null) {
            if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                this.inGround = false;
                this.hasImpulse = true;
                this.setDeltaMovement(Vec3.ZERO);
            }
        }
    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level().isClientSide()) {
            if (player.getItemInHand(hand).isEmpty()) {
                ItemStack dynamite = new ItemStack(RegistryIBBI.DYNAMITE_STICK.get());
                dynamite.set(RegistryDataComponents.DYNAMITE_LIT_TIME.get(), this.getPersistentData().getInt("dynamite_lit_time"));
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
            if (motionLength > 0.2f) {
                // Create bounce effect - reduce vertical speed and maintain some horizontal momentum
                float bounceStrength = 0.4f;  // Controls how high it bounces
                Vec3 bounceVec = new Vec3(
                        motion.x * 0.8,  // Maintain most horizontal momentum but with some friction
                        -motion.y * bounceStrength,  // Reverse vertical momentum with dampening
                        motion.z * 0.8   // Maintain most horizontal momentum but with some friction
                );

                this.setDeltaMovement(bounceVec);
                this.inGround = false;  // Keep it active
                this.hasImpulse = true;

                // Play bounce sound
                float tone = Mth.randomBetween(this.random, 0.8F, 1.2F);
                this.playSound(SoundEvents.BAMBOO_HIT, 0.5F, tone);

                return;  // Skip the rest of the method and continue bouncing
            }

            // When velocity is low enough, go to resting position
            this.inGround = true;

            // Set the facing direction based on motion
            if (Math.abs(motion.x) > Math.abs(motion.z)) {
                stuckDirection = motion.x > 0 ? Direction.EAST : Direction.WEST;
            } else {
                stuckDirection = motion.z > 0 ? Direction.SOUTH : Direction.NORTH;
            }

            this.setYRot(stuckDirection.toYRot());
            this.setDeltaMovement(Vec3.ZERO);

            // Play a thud sound
            float tone = Mth.randomBetween(this.random, 0.420F, 0.69F);
            this.playSound(SoundEvents.BAMBOO_PLACE, 0.69F, tone);

        } else if (hitFace != Direction.DOWN) {
            // For walls, bounce with dampening
            Vec3 normal = Vec3.atLowerCornerOf(hitFace.getNormal());

            // Simple reflection with dampening
            Vec3 reflected = motion.subtract(normal.scale(2 * motion.dot(normal))).scale(0.7);

            this.setDeltaMovement(reflected);
            this.inGround = false;
            this.hasImpulse = true;

            // Play hit sound
            float tone = Mth.randomBetween(this.random, 0.420F, 0.69F);
            this.playSound(SoundEvents.BAMBOO_HIT, 0.420F, tone);
        }

        // Make sure the hitbox is updated
        updateHitbox();
    }
}
