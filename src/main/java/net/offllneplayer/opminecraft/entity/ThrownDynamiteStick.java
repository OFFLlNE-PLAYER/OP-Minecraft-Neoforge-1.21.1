package net.offllneplayer.opminecraft.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;


public class ThrownDynamiteStick extends ThrowableItemProjectile {
    private float rotation;
    public Vec2 groundedOffset;

    public ThrownDynamiteStick(LivingEntity shooter, Level level) {
        super(RegistryEntities.THROWN_DYNAMITE_STICK.get(), shooter, level);
    }

    public ThrownDynamiteStick(Level level, double x, double y, double z) {
        super(RegistryEntities.THROWN_DYNAMITE_STICK.get(), x, y, z, level);
    }

    public ThrownDynamiteStick(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryIBBI.DYNAMITE_STICK.get();
    }

    /*
    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(RegistryIBBI.DYNAMITE_STICK.get());
    }
*/

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2);
        Vec3 vec3 = new Vec3(getX(), getY(), getZ());
        this.level().explode(null, level().damageSources().badRespawnPointExplosion(vec3), null, vec3, 5.0F, true, Level.ExplosionInteraction.BLOCK);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            Vec3 vec3 = new Vec3(getX(), getY(), getZ());
            this.level().explode(null, level().damageSources().badRespawnPointExplosion(vec3), null, vec3, 5.0F, true, Level.ExplosionInteraction.BLOCK);
            this.discard();
        }

    }
}