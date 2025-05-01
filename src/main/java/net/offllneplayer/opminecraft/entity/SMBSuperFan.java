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
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.offllneplayer.opminecraft.util.DeclareTagKeys;
import net.offllneplayer.opminecraft.util.PutNBT;

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

        float shortHalf = 0.1F;
        float longHalf = 0.74F;
        float height = 0.74F;

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
        return new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        if (this.level().isClientSide()) return;
        Entity hitEntity = result.getEntity();
        Level level = hitEntity.level();
        double x = hitEntity.getX(), y = hitEntity.getY(), z = hitEntity.getZ();

        if (hitEntity instanceof LivingEntity living) {
            DamageSource fanDamage = this.level().damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());
            float dmg = 4F;

            PutNBT.WeaponData data = PutNBT.readWeaponData(this);

            if (this.random.nextInt(data.unbreakinLevel() + 1) == 0) {
                this.getPersistentData().putInt("DMG_VALU", data.DMGVALU() + 1);
            }

            if (data.sharpLevel() > 0) {
                dmg += data.sharpLevel();
            } else if (data.smiteLevel() > 0 && living.getType().is(EntityTypeTags.SENSITIVE_TO_SMITE)) {
                dmg += 2F * data.smiteLevel();
            } else if (data.baneLevel() > 0 && living.getType().is(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) {
                dmg += 2F * data.baneLevel();
            }

            living.hurt(fanDamage, dmg);
            this.level().broadcastEntityEvent(this, (byte) 3);

            if (data.fireyLevel() > 0) living.igniteForTicks(80 * data.fireyLevel());

            if (data.knickerbockerLevel() > 0) {
                Vec3 pushDir = this.getDeltaMovement().normalize().scale(data.knickerbockerLevel() * 1D);
                living.push(pushDir.x, 0.1D, pushDir.z);
            }

            if (data.sweepinLevel() < 1) {
                this.setDeltaMovement(this.getDeltaMovement().scale(-0.01));
                this.hasImpulse = true;
            }

            float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
            this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);


        } else if ((hitEntity instanceof ChestBoat) || (hitEntity instanceof MinecartChest)) {
            this.setDeltaMovement(this.getDeltaMovement().scale(-Mth.randomBetween(this.random, -0.1420F, -0.69420F)));
            this.hasImpulse = true;

        } else if (hitEntity instanceof FallingBlockEntity fbe) {
            var state = fbe.getBlockState();
            level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(state.getBlock().asItem())));
            hitEntity.discard();

        } else if (hitEntity instanceof Boat boat) {
            level.addFreshEntity(new ItemEntity(level, x, y, z, boat.getDropItem().getDefaultInstance()));
            hitEntity.discard();

        } else if (hitEntity instanceof net.minecraft.world.entity.vehicle.AbstractMinecart cart) {
            if (cart instanceof net.minecraft.world.entity.vehicle.MinecartFurnace) {
                level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.FURNACE_MINECART)));
            } else if (cart instanceof net.minecraft.world.entity.vehicle.MinecartHopper) {
                level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.HOPPER_MINECART)));
            } else if (cart instanceof net.minecraft.world.entity.vehicle.MinecartTNT) {
                level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.TNT_MINECART)));
            } else {
                level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.MINECART)));
            }
            hitEntity.discard();

        } else if (hitEntity instanceof Painting) {
            level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.PAINTING)));
            hitEntity.discard();

        } else if (hitEntity instanceof LeashFenceKnotEntity) {
            level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.LEAD)));
            hitEntity.discard();

        } else  if (hitEntity.getType().is(DeclareTagKeys.Entities.IMPACT_PROJECTILES)) {
            if (hitEntity instanceof ItemSupplier supplier) {
                ItemStack drop = supplier.getItem();
                if (!drop.isEmpty()) {
                    level.addFreshEntity(new ItemEntity(level, x, y, z, drop.copy()));
                }
            }
            hitEntity.discard();

            this.setDeltaMovement(this.getDeltaMovement().scale(-0.01));
            this.hasImpulse = true;

        } else if (hitEntity instanceof EndCrystal crystal) {
            this.level().explode(this, crystal.getX(), crystal.getY(), crystal.getZ(), 6.0F, false, Level.ExplosionInteraction.BLOCK);
            crystal.discard();

            this.setDeltaMovement(this.getDeltaMovement().scale(-Mth.randomBetween(this.random, -0.420F, -1.1420F)));
            this.hasImpulse = true;
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

                float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

                ItemStack fan = new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());
                CompoundTag nbt = this.getPersistentData();
                PutNBT.enchantWeaponDataToItemstack(fan, nbt, this.level());

                player.setItemInHand(hand, fan);
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
            float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
            this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);
            this.level().broadcastEntityEvent(this, (byte)3);
        }
    }

    public Direction getStuckFace() {
        return stuckFace;
    }


    private float pullRatio = 1.0f;
    public void setPullRatio(float pullRatio) {
        this.pullRatio = pullRatio;
    }

    private int customTickCounter = 0;

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
            float degreesPerPull = 20F;
            rotation = (rotation - pullRatio * degreesPerPull) % 360F;
            if (rotation < 0) rotation += 360F;
        }

        if (!this.getCommandSenderWorld().isClientSide()) {
            customTickCounter++;

            // Every 20 ticks (1 second) play idle sound.
            if (customTickCounter % 20 == 0) {
                float vol = Mth.randomBetween(this.random, 0.2F, 0.420F);
                float tone = Mth.randomBetween(this.random, 0.9F, 1.0F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_IDLE.get(), vol, tone);
            }

            // Every 10 ticks (0.5 seconds) process nearby entities.
            if (customTickCounter % 10 == 0) {
                Level level = this.getCommandSenderWorld();
                AABB boundingBox = this.getBoundingBox().inflate(0.1D);
                List<Entity> entities = level.getEntities(this, boundingBox,
                        entity -> (entity instanceof LivingEntity) || (entity instanceof ItemEntity));

                for (Entity entity : entities) {

                    if (entity instanceof ItemEntity || entity instanceof ExperienceOrb) {// Discard item entities and play hit sound
                        entity.discard();
                        float vol = Mth.randomBetween(this.random, 0.6F, 1F);
                        float tone = Mth.randomBetween(this.random, 1.0F, 1.3F);
                        this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);

                    } else if (entity instanceof LivingEntity) {// Hurt living entities and play hit sound
                        DamageSource fanDamage = level.damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());
                        entity.hurt(fanDamage, 2.0F);

                        float vol = Mth.randomBetween(this.random, 0.8F, 1.05F);
                        float tone = Mth.randomBetween(this.random, 0.8F, 1.1F);
                        this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);
                    }
                }
            }
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
