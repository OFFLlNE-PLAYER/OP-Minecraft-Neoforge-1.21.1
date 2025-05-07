package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.init.*;
import net.offllneplayer.opminecraft.util.ProjectileEnchantUtil;
import net.offllneplayer.opminecraft.util.TagKeyUtil;
import net.offllneplayer.opminecraft.util.NBTUtil;

import java.util.List;
import java.util.Map;

public class SMBSuperFan extends AbstractArrow {

    public SMBSuperFan(EntityType<? extends SMBSuperFan> type, Level level) {
        super(type, level);
    }

    public SMBSuperFan(Level world, LivingEntity shooter) {
        super(RegistryEntities.SMB_SUPER_FAN.get(), world);
        this.setOwner(shooter);

        if (shooter != null) {
            this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
        }
        this.pickup = Pickup.DISALLOWED;
    }

    public SMBSuperFan(Player shooter, Level world, ItemStack stack) {
        this(world, shooter);

        CompoundTag data = this.getPersistentData();
        data.putString("nayme", stack.getHoverName().getString());
        data.putInt ("DMG_VALU", stack.getDamageValue());

        var enchants = stack.getComponents().get(DataComponents.ENCHANTMENTS);
        for (var entry : enchants.entrySet()) {
            entry.getKey().unwrapKey().ifPresent(key ->
                    data.putInt(key.location().toString(), entry.getIntValue())
            );
        }
    }

    public boolean isGrounded() {
        return inGround;
    }

    private float pullRatio = 1F;
    public void setPullRatio(float pullRatio) {
        this.pullRatio = pullRatio;
    }

    private BlockPos stuckPos;
    private Block stuckBlock;
    private Direction stuckFace = null;
    private float rotation;

    public Direction getStuckFace() {
        return stuckFace;
    }

    public float getRenderingRotation() {
        return rotation;
    }

/*--------------------------------------------------------------------------------------------*/
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

/*--------------------------------------------------------------------------------------------*/
    @Override
    public ItemStack getDefaultPickupItem() {
        return new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());
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
    protected void updateRotation() {/*VOIDED vanilla abstract arrow rot*/}
    @Override
    public void tickDespawn() {/*VOIDED despawning due to tick time*/}
    @Override
    public void checkDespawn() {/*VOIDED despawning due to player distance*/}
    @Override
    public void doPostHurtEffects(LivingEntity target) {/*VOIDED Discard entity on hit*/}

/*--------------------------------------------------------------------------------------------*/
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
            rotation = (rotation - pullRatio * 20F) % 360F;
            if (rotation < 0) rotation += 360F;
        }

        if (!this.level().isClientSide()) {
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
                AABB boundingBox = this.getBoundingBox().inflate(0.01420D);
                List<Entity> entities = level.getEntities(this, boundingBox, entity -> (entity instanceof LivingEntity) || (entity instanceof ItemEntity) || (entity instanceof ExperienceOrb));

                for (Entity entity : entities) {

                    if (entity instanceof ItemEntity) {// Discard and play hit sound
                        entity.discard();
                        float vol = Mth.randomBetween(this.random, 0.69F, 0.8F);
                        float tone = Mth.randomBetween(this.random, 1.0F, 1.3F);
                        this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);

                    }else if (entity instanceof ExperienceOrb) {
                        NBTUtil.WeaponData wd = NBTUtil.readItemStacktoClass(this.getPersistentData(), level);

                        if (wd.enchants().getOrDefault(Enchantments.MENDING, 0) > 0) {
                            this.getPersistentData().putInt("DMG_VALU", wd.dmgValue() - 1);
                        }

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

/*--------------------------------------------------------------------------------------------*/
@Override
public InteractionResult interact(Player player, InteractionHand hand) {
    if (!this.level().isClientSide()) {

        if (player.getItemInHand(hand).isEmpty()) {

            float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
            this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

            ItemStack fan = new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());
            CompoundTag nbt = this.getPersistentData();
            NBTUtil.enchantWeaponDataToItemstack(fan, nbt, this.level());

            player.setItemInHand(hand, fan);
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
        Level level = hitEntity.level();
        double x = hitEntity.getX();
        double y = hitEntity.getY();
        double z = hitEntity.getZ();

        if (hitEntity instanceof LivingEntity living) {
            if (level() instanceof ServerLevel serverLevel) {
                NBTUtil.WeaponData wd = NBTUtil.readItemStacktoClass(this.getPersistentData(), level);
                Map<Enchantment,Integer> enchs = wd.enchants();

                DamageSource fanDMG = level.damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());
                float dmg = 4F;
                float enchantDmg = ProjectileEnchantUtil.calculateDamageBonus(living, enchs);
                float damage = dmg + enchantDmg;

                living.hurt(fanDMG, damage);
                level.broadcastEntityEvent(this, (byte)3);

                ProjectileEnchantUtil.processUnbreaking(this, enchs, this.random);

                ProjectileEnchantUtil.applyFireAspect(living, enchs);
                ProjectileEnchantUtil.applyKnockback(this, living, enchs);
                ProjectileEnchantUtil.applyCleaving(this, enchs, this.random);

                ItemStack dummyStack = new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());
                NBTUtil.enchantWeaponDataToItemstack(dummyStack, this.getPersistentData(), level());
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, fanDMG, dummyStack);

                float tone = Mth.randomBetween(this.random, 1.1F, 1.2F);
                this.playSound(RegistrySounds.GUNBLADE_IN_DIRT.get(), 1.0F, tone);

                float tone2 = Mth.randomBetween(this.random, 0.9F, 1.1F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone2);
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

        } else  if (hitEntity.getType().is(TagKeyUtil.Entities.IMPACT_PROJECTILES)) {
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

/*--------------------------------------------------------------------------------------------*/
    @Override
    public void onHitBlock(BlockHitResult result) {
        stuckPos = result.getBlockPos();
        stuckBlock = level().getBlockState(stuckPos).getBlock();
        stuckFace = result.getDirection();
        super.onHitBlock(result);

        if (!level().isClientSide()) {
            BlockPos pos = result.getBlockPos();
            BlockState state = level().getBlockState(pos);
            Block block = state.getBlock();

            // Handle button interaction using the buttons tag
            if (block.builtInRegistryHolder().is(BlockTags.BUTTONS)) {
                if (state.hasProperty(ButtonBlock.POWERED) && !state.getValue(ButtonBlock.POWERED)) {
                    // Only press if not already pressed
                    BlockState pressedState = state.setValue(ButtonBlock.POWERED, true);
                    level().setBlock(pos, pressedState, 3);

                    level().playSound(null, pos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);

                    level().scheduleTick(pos, block, 30);
                    level().updateNeighborsAt(pos, block);

                    // Get the attached face direction
                    if (state.hasProperty(ButtonBlock.FACING) && state.hasProperty(ButtonBlock.FACE)) {
                        Direction direction = state.getValue(ButtonBlock.FACING);
                        AttachFace face = state.getValue(ButtonBlock.FACE);

                        // Convert AttachFace to Direction
                        Direction faceDirection;
                        switch (face) {
                            case FLOOR: faceDirection = Direction.DOWN;
                                break;
                            case CEILING: faceDirection = Direction.UP;
                                break;
                            case WALL:
                            default: faceDirection = direction.getOpposite();
                                break;
                        }

                        level().updateNeighborsAt(pos.relative(faceDirection), block);
                    }
                    // Create block event to notify neighbors
                    level().gameEvent(this.getOwner(), GameEvent.BLOCK_ACTIVATE, pos);
                }
            }

            float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
            this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);
            this.level().broadcastEntityEvent(this, (byte)3);
        }
    }
}
