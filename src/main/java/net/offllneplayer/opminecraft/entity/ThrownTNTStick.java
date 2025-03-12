package net.offllneplayer.opminecraft.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;


public class ThrownTNTStick extends ThrowableItemProjectile {

    public ThrownTNTStick(LivingEntity shooter, Level level) {
        super(RegistryEntities.THROWN_TNT_STICK.get(), shooter, level);
    }

    public ThrownTNTStick(Level level, double x, double y, double z) {
        super(RegistryEntities.THROWN_TNT_STICK.get(), x, y, z, level);
    }

    public ThrownTNTStick(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryIBBI.TNT_STICK.get();
    }


    public boolean isPickable() {
        return true;
    }

    public float getPickRadius() {
        return this.isPickable() ? 1.0F : 0.0F;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);

            if (!this.onGround()) {
                entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2);

                if (entity instanceof Player) {

                    if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler) {
                        ItemStack _setstack = new ItemStack(RegistryIBBI.TNT_STICK.get()).copy();
                        _setstack.setCount(1);
                        _modHandler.setStackInSlot(0, _setstack);

                        this.level().playLocalSound(getX(), getY(), getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.MASTER, (float) 0.8, (float) 1.4, false);

                        this.discard();

                    } else {

                        if (!this.isInWater()) {
                            Vec3 vec3 = new Vec3(getX(), getY(), getZ());
                            this.level().explode(null, level().damageSources().badRespawnPointExplosion(vec3), null, vec3, 3.0F, true, Level.ExplosionInteraction.BLOCK);

                            this.discard();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);



            if (!this.isInWater()) {

                Vec3 vec3 = new Vec3(getX(), getY(), getZ());
                this.level().explode(null, level().damageSources().badRespawnPointExplosion(vec3), null, vec3, 3.0F, true, Level.ExplosionInteraction.BLOCK);
                this.discard();

                }else {
                Level level = level();
                ItemEntity entityToSpawn = new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(RegistryIBBI.TNT_STICK.get()));
                entityToSpawn.setPickUpDelay(5);
                level.addFreshEntity(entityToSpawn);

                this.discard();
            }
        }
    }
}

