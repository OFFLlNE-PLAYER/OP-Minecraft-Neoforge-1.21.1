package net.offllneplayer.opminecraft.handler;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.method.crash.akuaku.AkuAkuActivate_Method;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.DeclareTagKeys;

public class DamageEventHandler {
    @SubscribeEvent
    public void onEntityDamage(LivingIncomingDamageEvent event) {
    //HURT ENTITY
        LivingEntity entitytarget = event.getEntity();
        double xtarg = entitytarget.getX();
        double ytarg = entitytarget.getY();
        double ztarg = entitytarget.getZ();

    //ATTACKER
        Entity attacker = event.getSource().getEntity();
        Level world = entitytarget.level();  // Use level() to access the world
        boolean cancelaku = false;

/*--------------------------------------------------------------------------------------------*/
    /*[FIRE]*/
        if (attacker instanceof LivingEntity livingAttacker
                && (livingAttacker.getMainHandItem().is(Items.BLAZE_ROD)
                || livingAttacker.getMainHandItem().is(Items.FIRE_CHARGE)
                || livingAttacker.getMainHandItem().is(Items.LAVA_BUCKET))) {
            entitytarget.setRemainingFireTicks(3 * 20);
        }

/*--------------------------------------------------------------------------------------------*/
    /*[ICE]*/
        if (attacker instanceof LivingEntity livingAttacker
                && (livingAttacker.getMainHandItem().is(Items.ICE)
                || livingAttacker.getMainHandItem().is(Items.PACKED_ICE)
                || livingAttacker.getMainHandItem().is(Items.BLUE_ICE))) {
            entitytarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3 * 20, 1));
        }

/*--------------------------------------------------------------------------------------------*/
    /*[POISON]*/
        if (attacker instanceof LivingEntity livingAttacker
                && (livingAttacker.getMainHandItem().is(Items.POISONOUS_POTATO)
                || livingAttacker.getMainHandItem().is(Items.SPIDER_EYE))) {
            entitytarget.addEffect(new MobEffectInstance(MobEffects.POISON, 3 * 20, 1));  // Poison for 3 seconds at level 1
        }
/*--------------------------------------------------------------------------------------------*/
    /*[WIND CHARGE]*/
        if (attacker instanceof LivingEntity livingAttacker
                && livingAttacker.getMainHandItem().is(Items.WIND_CHARGE)) {
            Level level = livingAttacker.getCommandSenderWorld();

            livingAttacker.getMainHandItem().shrink(1);

            Vec3 direction = entitytarget.position().subtract(livingAttacker.position()).normalize();

            float xpush = Mth.nextFloat(RandomSource.create(), 0.2F, 1.6F);
            float ypush = Mth.nextFloat(RandomSource.create(), 0.4F, 1.6F);
            float zpush = Mth.nextFloat(RandomSource.create(), 0.2F, 1.6F);

            float volume = (xpush + ypush + zpush - 0.8F) / 3;
            if (volume <= 0.7F) {volume = 0.7F;} else if  (volume >= 1.1F) {volume = 1.1F;}
            entitytarget.push(direction.x * xpush, ypush, direction.z * zpush);

            float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.1F);

            level.playSound(null, xtarg, ytarg, ztarg, SoundEvents.WIND_CHARGE_BURST, SoundSource.HOSTILE, volume, tone);

            if (level instanceof ServerLevel _level) {
                for (int i = 0; i < 3; i++) {
                    int xOffset = Mth.nextInt(RandomSource.create(), 0, 1);
                    int yOffset = Mth.nextInt(RandomSource.create(), 0, 1);
                    int zOffset = Mth.nextInt(RandomSource.create(), 0, 1);

                    _level.sendParticles(ParticleTypes.CLOUD, entitytarget.getX(), entitytarget.getY(), entitytarget.getZ(), 1, xOffset, yOffset, zOffset, 1);
                    _level.sendParticles(ParticleTypes.EXPLOSION, entitytarget.getX(), entitytarget.getY(), entitytarget.getZ(), 1, xOffset, yOffset, zOffset, 1);
                }
            }
        }

/*--------------------------------------------------------------------------------------------*/
    /*[CRASH CRATES]*/
        if (event.getSource().is(DamageTypes.FALL)) {
            BlockState belowBlock = world.getBlockState(entitytarget.blockPosition().below());
            if (belowBlock.is(DeclareTagKeys.Blocks.CRASH_CRATES)) {
                event.setCanceled(true);
                cancelaku = true;
            }
        }

/*--------------------------------------------------------------------------------------------*/
    /*[AKUAKU]*/
        if (!cancelaku) {
            // Check if the Aku Aku Mask is in either hand
            if ((entitytarget.getMainHandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get()) || (entitytarget.getOffhandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get())) {
                // Cancel the damage event so no damage occurs
                event.setCanceled(true);
                // Execute the Aku Aku Mask procedure
                AkuAkuActivate_Method.execute(world, entitytarget.getX(), entitytarget.getY(), entitytarget.getZ(), entitytarget);
            }

        }
    }
}
