package net.offllneplayer.opminecraft.util;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public class ProjectileEnchantUtil {

    public static void processUnbreaking(AbstractArrow projectile, Map<Enchantment, Integer> enchantments, RandomSource random) {
        int unbreakingLevel = enchantments.getOrDefault(Enchantments.UNBREAKING, 0);
        if (random.nextInt(unbreakingLevel + 1) == 0) {
            int currentDamage = projectile.getPersistentData().getInt("DMG_VALU");
            projectile.getPersistentData().putInt("DMG_VALU", currentDamage + 1);
        }
    }

    public static float calculateDamageBonus(LivingEntity target, Map<Enchantment, Integer> enchantments) {

        float bonus = 0.0F;

        if (enchantments.getOrDefault(Enchantments.SHARPNESS, 0) > 0) {
            bonus += enchantments.get(Enchantments.SHARPNESS);
        } else if (enchantments.getOrDefault(Enchantments.SMITE, 0) > 0
                && target.getType().is(EntityTypeTags.SENSITIVE_TO_SMITE)) {
            bonus += 2F * enchantments.get(Enchantments.SMITE);
        } else if (enchantments.getOrDefault(Enchantments.BANE_OF_ARTHROPODS, 0) > 0
                && target.getType().is(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) {
            bonus += 2F * enchantments.get(Enchantments.BANE_OF_ARTHROPODS);
        }

        return bonus;
    }

    public static boolean applyFireAspect(LivingEntity target, Map<Enchantment, Integer> enchantments) {
        if (enchantments.getOrDefault(Enchantments.FIRE_ASPECT, 0) > 0) {
            target.igniteForTicks(80 * enchantments.get(Enchantments.FIRE_ASPECT));
            return true;
        }
        return false;
    }

    public static boolean applyKnockback(AbstractArrow projectile, LivingEntity target, Map<Enchantment, Integer> enchantments) {
        if (enchantments.getOrDefault(Enchantments.KNOCKBACK, 0) > 0) {
            int level = enchantments.get(Enchantments.KNOCKBACK);

            Vec3 knockbackDir;
            if (projectile.getDeltaMovement().length() > 0.1) {
                // Use projectile direction if it's moving fast enough
                knockbackDir = projectile.getDeltaMovement().normalize();
            } else if (projectile.getOwner() != null) {
                knockbackDir = target.position().subtract(projectile.getOwner().position()).normalize();
            } else {
                knockbackDir = new Vec3(0, 0, 1).yRot((float)Math.toRadians(-projectile.getYRot()));
            }

            double strength = 0.5 * level;
            target.push(knockbackDir.x * strength, 0.1 * level, knockbackDir.z * strength);

            projectile.hasImpulse = true;
            return true;
        }
        return false;
    }

    public static boolean applyCleaving(AbstractArrow projectile, Map<Enchantment, Integer> enchantments, RandomSource random) {
        // Bounce the projectile if it doesn't have Sweeping Edge
        if (enchantments.getOrDefault(Enchantments.SWEEPING_EDGE, 0) < 1) {
            projectile.setDeltaMovement(projectile.getDeltaMovement().scale(-Mth.randomBetween(random, -0.10420F, -0.01420F)));
            projectile.hasImpulse = true;
            return true;
        }
        return false;
    }
}
