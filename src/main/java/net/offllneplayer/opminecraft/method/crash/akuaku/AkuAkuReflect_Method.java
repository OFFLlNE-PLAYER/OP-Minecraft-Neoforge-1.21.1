package net.offllneplayer.opminecraft.method.crash.akuaku;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class AkuAkuReflect_Method {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceEntity, LivingEntity targetEntity, float incomingDmg) {
        if (!(sourceEntity instanceof LivingEntity attacker)) return;
        Level level = attacker.level();
        Direction.Axis axis = targetEntity.getDirection().getAxis();
        DamageSource AkuDmg = level.damageSources().source(RegistryDamageTypes.AKU_AKU, sourceEntity, targetEntity);
        attacker.hurt(AkuDmg, Math.max(0F, incomingDmg / 4F));

        if (level instanceof ServerLevel _level) {

            float xOffset = Mth.nextFloat(RandomSource.create(), -0.69F,  0.69F);
            float yOffset = Mth.nextFloat(RandomSource.create(), 0.420F,  1.20420F);
            float zOffset = Mth.nextFloat(RandomSource.create(), -0.69F,  0.69F);

            _level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RegistryBIBI.AKU_AKU_MASK.get())),
                    attacker.getX() + xOffset, attacker.getY() + yOffset, attacker.getZ() + zOffset,
                    1, 0, 0, 0, 0);

            Item[] feathers = {RegistryBIBI.ORANGE_FEATHER.get(), RegistryBIBI.PINK_FEATHER.get(), RegistryBIBI.PURPLE_FEATHER.get(), RegistryBIBI.YELLOW_FEATHER.get()};

            for (Item feather : feathers) {
                int sendChance = Mth.nextInt(RandomSource.create(), 1, 4);
                if (sendChance == 1) {
                    float xOffs = Mth.nextFloat(RandomSource.create(), 0,  2F);
                    float yOffs = Mth.nextFloat(RandomSource.create(), -0.420F,  0.420F);
                    float zOffs = Mth.nextFloat(RandomSource.create(), 0,  2F);
                    _level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(feather)), attacker.getX(), attacker.getY() + 1, attacker.getZ(),
                            Mth.nextInt(RandomSource.create(), 1, 3), xOffs, yOffs, zOffs, 0.69D);
                }
            }

            for (Item feather : feathers) {
                int sendChance = Mth.nextInt(RandomSource.create(), 1, 4);
                if (sendChance == 1) {
                    float xOffs = Mth.nextFloat(RandomSource.create(), -2F,  0);
                    float yOffs = Mth.nextFloat(RandomSource.create(), -0.420F,  0.420F);
                    float zOffs = Mth.nextFloat(RandomSource.create(), -2F,  0);
                    _level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(feather)), attacker.getX(), attacker.getY() + 1, attacker.getZ(),
                            Mth.nextInt(RandomSource.create(), 1, 3), xOffs, yOffs, zOffs, 0.69D
                    );
                }
            }

            float tone = Mth.nextFloat(RandomSource.create(), 0.420F, 1.0420F);
            level.playSound(null, x, y, z, RegistrySounds.PUNCH.get(), SoundSource.MASTER, 0.69F, tone);

            if (axis == Direction.Axis.Z) {
                attacker.push(0, 0.01, attacker.getZ() > targetEntity.getZ() ? -0.2420 : 0.2420);
            } else if (axis == Direction.Axis.X) {
                attacker.push(attacker.getX() > targetEntity.getX() ? -0.2420 : 0.2420, 0.01, 0);
            }
        }
    }
}