package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.method.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.util.PutNBT;

public class CryingHatchet extends AbstractArrow {

    public CryingHatchet(LivingEntity shooter, Level level) {
        super(RegistryEntities.CRYING_HATCHET.get(), shooter, level, new ItemStack(RegistryIBBI.CRYING_HATCHET.get()), null);
        this.pickup = Pickup.DISALLOWED;
    }

    public CryingHatchet(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void setPos(double x, double y, double z) {
        super.setPos(x, y, z);

        float shortHalf = 0.05F;
        float longHalf = 0.420F;
        float height = 0.420F;

        Direction dir = Direction.fromYRot(this.getYRot());

        if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            this.setBoundingBox(new AABB(x - shortHalf, y - height, z - longHalf, x + shortHalf, y + height, z + longHalf));
        } else {
            this.setBoundingBox(new AABB(x - longHalf, y - height, z - shortHalf, x + longHalf, y + height, z + shortHalf));
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
        return new ItemStack(RegistryIBBI.CRYING_HATCHET.get());
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        if (this.level().isClientSide()) return;
        Entity hitEntity = result.getEntity();

        if (hitEntity instanceof EndCrystal crystal) {
            this.level().explode(this, crystal.getX(), crystal.getY(), crystal.getZ(), 6.0F, false, Level.ExplosionInteraction.BLOCK);
            crystal.discard();
        }

        if (hitEntity instanceof Projectile || hitEntity instanceof ItemFrame || hitEntity instanceof AbstractMinecart) {
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.01));
            this.hasImpulse = true;

        } else {
            if (hitEntity instanceof LivingEntity living) {
                DamageSource hatchetDMG = this.level().damageSources().source(RegistryDamageTypes.HATCHET, this, this.getOwner());
                CompoundTag data = this.getPersistentData();
                int DMGVALU = data.getInt("DMG_VALU");
                int unbreakinLevel = data.getInt("unbreakin");
                float dmg = 9F;
                int sharpLevel = data.getInt("sharp");
                int smiteLevel = data.getInt("smiite");
                int baneLevel = data.getInt("bane");
                int fireyLevel = data.getInt("firey");
                int knickerbockerLevel = data.getInt("knickerbocker");
                int sweepinLevel = data.getInt("sweepin");

                if (this.random.nextInt(unbreakinLevel + 1) == 0) {
                    data.putInt("DMG_VALU", DMGVALU + 1);
                }

                if (sharpLevel > 0) {
                    dmg += sharpLevel;
                } else
                    if (smiteLevel > 0 && living.getType().is(EntityTypeTags.SENSITIVE_TO_SMITE)) {
                    dmg += 2F * smiteLevel;
                } else
                    if (baneLevel > 0 && living.getType().is(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) {
                    dmg += 2F * baneLevel;
                }

                living.hurt(hatchetDMG, dmg);
                this.level().broadcastEntityEvent(this, (byte) 3);

                if (fireyLevel > 0) living.igniteForTicks(80 * fireyLevel);

                if (knickerbockerLevel > 0) {
                    Vec3 pushDir = this.getDeltaMovement().normalize().scale(knickerbockerLevel * 1D);
                    living.push(pushDir.x, 0.1D, pushDir.z);
                }

                if (sweepinLevel < 1) {
                    this.setDeltaMovement(this.getDeltaMovement().scale(-0.01));
                    this.hasImpulse = true;
                }

                ApplyCrying1_Method.execute(living);

                float tone = Mth.randomBetween(this.random, 1.1F, 1.2F);
                this.playSound(RegistrySounds.GUNBLADE_IN_DIRT.get(), 1.0F, tone);
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

                ItemStack blade = new ItemStack(RegistryIBBI.CRYING_HATCHET.get());
                CompoundTag nbt = this.getPersistentData();
                PutNBT.enchantWeaponDataToItemstack(blade, nbt, this.level());

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


    private float pullRatio = 1.0f; // default if nothing set

    /** Called by the item when it spawns the entity. */
    public void setPullRatio(float pullRatio) {
        this.pullRatio = pullRatio;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.inGround && stuckPos != null) {
            if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                this.inGround   = false;
                this.hasImpulse = true;
                this.setDeltaMovement(Vec3.ZERO);
            }
        }

        if (!this.inGround) {
            // use pullRatio to scale your rotation instead of raw speed
            float degreesPerPull = 20F; // tweak to taste
            rotation = (rotation - pullRatio * degreesPerPull) % 360F;
            if (rotation < 0) rotation += 360F;
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
