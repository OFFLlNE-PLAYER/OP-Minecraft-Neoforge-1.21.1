package net.offllneplayer.opminecraft.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class TempestEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<TempestEnchantmentEffect> CODEC = MapCodec.unit(TempestEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {

        int boost_chance= 0;
        int base_chance = Mth.nextInt(RandomSource.create(), 1, 80);

        //prevent crashing if someone attempts to go over 10 lmao
        if (enchantmentLevel > 10) enchantmentLevel = 10;

        for (int i = 0; i < Math.min(enchantmentLevel, 10); i++) {
            boost_chance= (boost_chance + Mth.nextInt(RandomSource.create(), 1, 10));
        }
        int chance_of_lightning = base_chance + boost_chance;
        if (chance_of_lightning > 60) {EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), MobSpawnType.TRIGGERED);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
