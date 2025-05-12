package net.offllneplayer.opminecraft.method._handler;

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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.init.RegistryMobEffects;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.method.crash.akuaku.AkuAkuActivate_Method;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.method.util.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.method.crash.akuaku.AkuAkuReflect_Method;
import net.offllneplayer.opminecraft.method.gunblade.GunbladeShot_Method;
import net.offllneplayer.opminecraft.method.gunblade.PrototypeGunbladeShot_Method;

@EventBusSubscriber
public class DamageEventHandler {
    @SubscribeEvent
    public static void onEntityDamage(LivingIncomingDamageEvent event) {

        Entity sourceEntity = event.getSource().getEntity();
        LivingEntity targetEntity = event.getEntity();
        Level level = targetEntity.level();
        float incomingDmg = event.getAmount();

        double xtarg = targetEntity.getX();
        double ytarg = targetEntity.getY();
        double ztarg = targetEntity.getZ();

        boolean cancelaku = false;

        /*--------------------------------------------------------------------------------------------*/
        /*[CRASH CRATES]*/
        if (event.getSource().is(DamageTypes.FALL)) {
            BlockState belowBlock = level.getBlockState(targetEntity.blockPosition().below());
            if (belowBlock.is(OP_TagKeyUtil.Blocks.CRASH_CRATES)) {
                event.setCanceled(true);
                cancelaku = true;
            }
        }

        /*--------------------------------------------------------------------------------------------*/
        /*[AKUAKU]*/
        if (!cancelaku) {
            if (targetEntity.hasEffect(RegistryMobEffects.AKU_AKU)) {
                if (!level.isClientSide() && sourceEntity != null) {
                    AkuAkuReflect_Method.execute(level, xtarg, ytarg, ztarg, sourceEntity, targetEntity, incomingDmg);
                }
                event.setCanceled(true);
                return;
            } else if (targetEntity.getMainHandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get()
                    || targetEntity.getOffhandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get()) {

                event.setCanceled(true);
                AkuAkuActivate_Method.execute(level, xtarg, ytarg, ztarg, targetEntity);
                return;
            }
        }

        /*--------------------------------------------------------------------------------------------*/
        /*[ITEM-BASED EFFECTS]*/
        if (sourceEntity instanceof LivingEntity livingAttacker && !(sourceEntity instanceof Player)) {
            Item held = livingAttacker.getMainHandItem().getItem();

            /*--------------------------------------------------------------------------------------------*/
            /*[FIRE]*/
            if (held == Items.BLAZE_ROD || held == Items.BLAZE_POWDER || held == Items.FIRE_CHARGE || held == Items.LAVA_BUCKET) {
                targetEntity.setRemainingFireTicks(3 * 20);
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[ICE]*/
            else if (held == Items.ICE || held == Items.PACKED_ICE || held == Items.BLUE_ICE) {
                targetEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3 * 20, 1));
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[POISON]*/
            else if (held == Items.POISONOUS_POTATO || held == Items.SPIDER_EYE) {
                targetEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 3 * 20, 1));
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[WIND CHARGE]*/
            else if (held == Items.WIND_CHARGE) {

                livingAttacker.getMainHandItem().shrink(1);

                Vec3 direction = targetEntity.position().subtract(livingAttacker.position()).normalize();

                float xpush = Mth.nextFloat(RandomSource.create(), 0.2F, 1.6F);
                float ypush = Mth.nextFloat(RandomSource.create(), 0.4F, 1.6F);
                float zpush = Mth.nextFloat(RandomSource.create(), 0.2F, 1.6F);

                float volume = (xpush + ypush + zpush - 0.8F) / 3;
                if (volume <= 0.7F) { volume = 0.7F; }
                else if (volume >= 1F) { volume = 1F; }
                targetEntity.push(direction.x * xpush, ypush, direction.z * zpush);

                float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.1F);

                level.playSound(null, xtarg, ytarg, ztarg, SoundEvents.WIND_CHARGE_BURST, SoundSource.HOSTILE, volume, tone);

                if (level instanceof ServerLevel _level) {
                    for (int i = 0; i < 3; i++) {
                        int xOffset = Mth.nextInt(RandomSource.create(), 0, 1);
                        int yOffset = Mth.nextInt(RandomSource.create(), 0, 1);
                        int zOffset = Mth.nextInt(RandomSource.create(), 0, 1);

                        _level.sendParticles(ParticleTypes.CLOUD, targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(), 1, xOffset, yOffset, zOffset, 1);
                        _level.sendParticles(ParticleTypes.EXPLOSION, targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(), 1, xOffset, yOffset, zOffset, 1);
                    }
                }
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[GUNBLADE]*/
            else if (held == RegistryIBBI.GUNBLADE.get()) {
                float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.1420F);
                level.playSound(null, xtarg, ytarg, ztarg, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.42F, tone);
                GunbladeShot_Method.execute(level, xtarg, ytarg, ztarg, targetEntity, sourceEntity);
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[PROTOTYPE GUNBLADE]*/
            else if (held == RegistryIBBI.PROTOTYPE_GUNBLADE.get()) {
                float tone = Mth.nextFloat(RandomSource.create(), 0.8F, 1F);
                level.playSound(null, xtarg, ytarg, ztarg, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.3F, tone);
                PrototypeGunbladeShot_Method.execute(level, xtarg, ytarg, ztarg, targetEntity, sourceEntity);
            }
        }
    }
}
