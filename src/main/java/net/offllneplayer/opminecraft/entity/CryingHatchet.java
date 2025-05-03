package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.decoration.Painting;
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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.*;
import net.offllneplayer.opminecraft.method.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.util.DeclareTagKeys;
import net.offllneplayer.opminecraft.util.PutNBT;

import java.util.Map;

public class CryingHatchet extends AbstractArrow {

    public CryingHatchet(EntityType<? extends CryingHatchet> type, Level level) {
        super(type, level);
    }

    public CryingHatchet(Level world, LivingEntity shooter) {
        super(RegistryEntities.CRYING_HATCHET.get(), world);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
        this.pickup = Pickup.DISALLOWED;
    }

    public CryingHatchet(Player shooter, Level world, ItemStack stack) {
        this(world, shooter);

        CompoundTag data = this.getPersistentData();
        data.putString("nayme",    stack.getHoverName().getString());
        data.putInt   ("DMG_VALU", stack.getDamageValue());

        var enchants = stack.getComponents().get(DataComponents.ENCHANTMENTS);
        for (var entry : enchants.entrySet()) {
            entry.getKey().unwrapKey().ifPresent(key ->
                    data.putInt(key.location().getPath(), entry.getIntValue())
            );
        }
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
        Level level = hitEntity.level();
        double x = hitEntity.getX();
        double y = hitEntity.getY();
        double z = hitEntity.getZ();

        if (hitEntity instanceof LivingEntity living) {
            if (level() instanceof ServerLevel serverLevel) {
                PutNBT.WeaponData wd = PutNBT.readWeaponData(this.getPersistentData(), level);
                Map<Enchantment, Integer> enchs = wd.enchantments();

                if (this.random.nextInt(enchs.getOrDefault(Enchantments.UNBREAKING, 0) + 1) == 0) {
                    this.getPersistentData().putInt("DMG_VALU", wd.dmgValue() + 1);
                }

                float dmg = 9F;
                if (enchs.getOrDefault(Enchantments.SHARPNESS, 0) > 0) {
                    dmg += enchs.get(Enchantments.SHARPNESS);
                } else if (enchs.getOrDefault(Enchantments.SMITE, 0) > 0
                        && living.getType().is(EntityTypeTags.SENSITIVE_TO_SMITE)) {
                    dmg += 2F * enchs.get(Enchantments.SMITE);
                } else if (enchs.getOrDefault(Enchantments.BANE_OF_ARTHROPODS, 0) > 0
                        && living.getType().is(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) {
                    dmg += 2F * enchs.get(Enchantments.BANE_OF_ARTHROPODS);
                }

                DamageSource hatchetDMG = level.damageSources().source(RegistryDamageTypes.HATCHET, this, this.getOwner());
                living.hurt(hatchetDMG, dmg);
                level.broadcastEntityEvent(this, (byte)3);

                if (enchs.getOrDefault(Enchantments.FIRE_ASPECT, 0) > 0) {
                    living.igniteForTicks(80 * enchs.get(Enchantments.FIRE_ASPECT));
                }

                if (enchs.getOrDefault(Enchantments.KNOCKBACK, 0) > 0) {
                    Vec3 pushDir = this.getDeltaMovement().normalize().scale(enchs.get(Enchantments.KNOCKBACK));
                    living.push(pushDir.x, 0.1, pushDir.z);
                }

                if (enchs.getOrDefault(Enchantments.SWEEPING_EDGE, 0) < 1) {
                    this.setDeltaMovement(
                            this.getDeltaMovement()
                                    .scale(-Mth.randomBetween(this.random, -0.10420F, -0.01420F))
                    );
                    this.hasImpulse = true;
                }

                ItemStack stack = new ItemStack(RegistryIBBI.CRYING_HATCHET.get());
                PutNBT.enchantWeaponDataToItemstack(stack, this.getPersistentData(), level());
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, hatchetDMG, stack);

                ApplyCrying1_Method.execute(living);

                float tone = Mth.randomBetween(this.random, 1.1F, 1.2F);
                this.playSound(RegistrySounds.GUNBLADE_IN_DIRT.get(), 1.0F, tone);
            }
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


    private float pullRatio = 1F;
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
            rotation = (rotation - pullRatio * 20F) % 360F;
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
