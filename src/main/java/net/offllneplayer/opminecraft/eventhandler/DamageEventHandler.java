package net.offllneplayer.opminecraft.eventhandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import net.offllneplayer.opminecraft.init.RegistryMobEffects;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.items._item.crash.akuaku.AkuAkuActivate_Method;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.items._item.crash.akuaku.AkuAkuReflect_Method;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;
import net.offllneplayer.opminecraft.items._item.balloon.PopBalloons_Method;
import net.offllneplayer.opminecraft.items._iwe.beretta.PistolItem;
import net.offllneplayer.opminecraft.items._iwe.gunblade.GunbladeItem;
import net.offllneplayer.opminecraft.items._iwe.gunblade.GunbladeShot_Method;
import net.offllneplayer.opminecraft.items._iwe.gunblade.PrototypeGunbladeShot_Method;

import static net.offllneplayer.opminecraft.eventhandler.spawnhandler.SpawnEnchantments.applyWeaponEnchantments;

@EventBusSubscriber
public class DamageEventHandler {

    private static final RandomSource RANDOM = RandomSource.create();

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
        /*[BALLOON POPPING]*/
        if (targetEntity instanceof LivingEntity livingEntity) {
            if (livingEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem
						|| livingEntity.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem) {
                PopBalloons_Method.execute(targetEntity);
            }
        }

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
            } else if (targetEntity.getMainHandItem().getItem() == RegistryBIBI.AKU_AKU_MASK.get()
                  || targetEntity.getOffhandItem().getItem() == RegistryBIBI.AKU_AKU_MASK.get()) {

                event.setCanceled(true);
                AkuAkuActivate_Method.execute(level, xtarg, ytarg, ztarg, targetEntity);
                return;
            }
        }

        /*--------------------------------------------------------------------------------------------*/
        /*[ITEM-BASED EFFECTS]*/
        if (sourceEntity instanceof LivingEntity livingAttacker && !(sourceEntity instanceof Player)) {
            Item held = livingAttacker.getMainHandItem().getItem();
            ItemStack heldStack = livingAttacker.getMainHandItem();

            /*--------------------------------------------------------------------------------------------*/
            /*[FIRE]*/
            if (held == Items.BLAZE_ROD) {
                targetEntity.setRemainingFireTicks(3 * 20);
            } else if (held == Items.BLAZE_POWDER || held == Items.FIRE_CHARGE) {
                targetEntity.setRemainingFireTicks(3 * 20);
                heldStack.shrink(1);
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[LAVA]*/
            else if (held == Items.LAVA_BUCKET) {
                targetEntity.setRemainingFireTicks(3 * 20);

                // Place lava at the target entity's position
                BlockPos targetPos = targetEntity.blockPosition();
                if (targetEntity.level().getBlockState(targetPos).isAir()) {
                    targetEntity.level().setBlock(targetPos, Blocks.LAVA.defaultBlockState(), 3);
                }

                // Replace lava bucket with empty bucket in attacker's hand
                livingAttacker.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET));
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
                heldStack.shrink(1);
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[WIND CHARGE]*/
            else if (held == Items.WIND_CHARGE) {

                Vec3 direction = targetEntity.position().subtract(livingAttacker.position()).normalize();

                float xpush = Mth.nextFloat(RandomSource.create(), 0.2F, 1.6F);
                float ypush = Mth.nextFloat(RandomSource.create(), 0.4F, 1.6F);
                float zpush = Mth.nextFloat(RandomSource.create(), 0.2F, 1.6F);

                float volume = (xpush + ypush + zpush - 0.8F) / 3;
                if (volume <= 0.7F) {
                    volume = 0.7F;
                } else if (volume >= 1F) {
                    volume = 1F;
                }
                targetEntity.push(direction.x * xpush, ypush, direction.z * zpush);


                heldStack.shrink(1);


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
            /*[PROTOTYPE GUNBLADE]*/
            else if (held == RegistryBIBI.PROTOTYPE_GUNBLADE.get()) {
                float tone = Mth.nextFloat(RandomSource.create(), 0.8F, 1F);
                level.playSound(null, xtarg, ytarg, ztarg, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.3F, tone);
                PrototypeGunbladeShot_Method.execute(level, xtarg, ytarg, ztarg, targetEntity, sourceEntity);
            }

            /*--------------------------------------------------------------------------------------------*/
            /*[GUNBLADE]*/
            else if (livingAttacker.getMainHandItem().getItem() instanceof GunbladeItem) {
                float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.1420F);
                level.playSound(null, xtarg, ytarg, ztarg, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.42F, tone);
                GunbladeShot_Method.execute(level, xtarg, ytarg, ztarg, targetEntity, sourceEntity);
            }
        }


        /*--------------------------------------------------------------------------------------------*/
        /*~[Wither Skeleton]~*/
        if (targetEntity instanceof WitherSkeleton witherSkeleton && sourceEntity instanceof LivingEntity livingAttacker) {
            ItemStack mainHand = witherSkeleton.getMainHandItem();

            // Only switch weapons if skeleton is holding a pistol that's out of ammo
            if (mainHand.getItem() instanceof PistolItem pistolItem && mainHand.getDamageValue() >= mainHand.getMaxDamage()) {
                ItemStack offHand = witherSkeleton.getOffhandItem();

                // Get the required ammo type for this pistol
                Item requiredAmmo = pistolItem.getPistolMaterial().getRegisteredAmmo();

                // Only switch if pistol is empty AND no matching ammo in off-hand
                if (offHand.isEmpty() || !offHand.is(requiredAmmo)) {
                    Item attackerWeapon = livingAttacker.getMainHandItem().getItem();
                    ItemStack newWeapon;

                    // If attacker has sword or axe AND it's a direct hit, give skeleton a melee weapon else bow
                    if (event.getSource().getDirectEntity() == sourceEntity && (attackerWeapon instanceof SwordItem || attackerWeapon instanceof AxeItem)) {
                        newWeapon = new ItemStack(Items.DIAMOND_AXE);
                    } else {
                        newWeapon = new ItemStack(Items.BOW);
                    }

                    // 50% chance to add enchantments
                    if (RANDOM.nextBoolean()) {
                        applyWeaponEnchantments(level, newWeapon);
                    }

                    witherSkeleton.setItemInHand(InteractionHand.MAIN_HAND, newWeapon);

                    // Clear off-hand to remove any leftover ammo
                    witherSkeleton.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

                    // Play sound effect for weapon switch
                    level.playSound(null, xtarg, ytarg, ztarg, SoundEvents.WOOL_PLACE, SoundSource.HOSTILE, 0.420F, 0.9F);
                }
            }
        }
    }
}

