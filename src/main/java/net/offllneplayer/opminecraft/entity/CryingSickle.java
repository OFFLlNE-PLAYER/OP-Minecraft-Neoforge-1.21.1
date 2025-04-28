package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.PutNBT;

public class CryingSickle extends AbstractArrow {

    public CryingSickle(LivingEntity shooter, Level level) {
        super(RegistryEntities.CRYING_SICKLE.get(), shooter, level, new ItemStack(RegistryIBBI.CRYING_SICKLE.get()), null);
        this.pickup = Pickup.DISALLOWED;
    }

    public CryingSickle(EntityType<? extends AbstractArrow> entityType, Level level) {
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
        return new ItemStack(RegistryIBBI.CRYING_SICKLE.get());
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        if (!this.level().isClientSide()) {
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

                this.setDeltaMovement(this.getDeltaMovement().scale(-0.1));
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
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level().isClientSide()) {
            if (player.getItemInHand(hand).isEmpty()) {

                float tone = Mth.randomBetween(this.random, 1.35F, 1.5F);
                this.playSound(RegistrySounds.GUNBLADE_IN_DIRT.get(), 0.6F, tone);

                ItemStack blade = new ItemStack(RegistryIBBI.CRYING_SICKLE.get());
                CompoundTag nbt = this.getPersistentData();
                PutNBT.writeWeaponDataToItemstack(blade, nbt, this.level());

                player.setItemInHand(hand, blade);
                this.discard();

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }


    private BlockPos stuckPos;
    private Block stuckBlock;
    private Direction stuckFace = null;
    private float rotation;

    @Override
    public void onHitBlock(BlockHitResult result) {
        stuckPos   = result.getBlockPos();
        stuckBlock = level().getBlockState(stuckPos).getBlock();
        this.stuckFace = result.getDirection();
        super.onHitBlock(result);

        if (!level().isClientSide()) {
            float tone = Mth.randomBetween(this.random, 1.2F, 1.4F);
            this.playSound(RegistrySounds.GUNBLADE_SLASH.get(), 1.0F, tone);
            this.level().broadcastEntityEvent(this, (byte)3);
        }
    }

    public Direction getStuckFace() {
        return stuckFace;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.inGround && stuckPos != null) {
            if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                this.inGround      = false;
                this.hasImpulse    = true;
                this.setDeltaMovement(Vec3.ZERO);
            }
        }

        if (!inGround) {
            rotation = (rotation + 15F) % 360F;
        }
    }

    public float getRenderingRotation() {
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void updateRotation() {
        // skip the vanilla yaw/pitch alignment
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