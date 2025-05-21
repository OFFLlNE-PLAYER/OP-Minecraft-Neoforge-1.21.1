package net.offllneplayer.opminecraft.method.hatchet;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.method.UTIL.OP_TagKeyUtil;

import java.util.Map;


public class HatchetonHitEntity {

/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	public static void processUnbreaking(AbstractArrow projectile, Map<Enchantment, Integer> enchantments, RandomSource random) {
		if (enchantments == null) return;

		enchantments.entrySet().stream()
			.filter(e -> e.getKey().toString().contains("Unbreaking"))
			.findFirst()
			.ifPresent(entry -> {
				int level = entry.getValue();
				if (random.nextInt(level + 1) == 0) {
					int currentDamage = projectile.getPersistentData().getInt("DMG_VALU");
					projectile.getPersistentData().putInt("DMG_VALU", currentDamage + 1);
				}
			});
	}


/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	public static float calculateDamageBonus(LivingEntity target, Map<Enchantment, Integer> enchantments) {
		if (enchantments == null) return 0.0F;

		// Check for Sharpness
		float[] bonus = {0.0F}; // Using array to allow modification in lambda

		enchantments.entrySet().stream()
			.filter(e -> e.getKey().toString().contains("Sharpness"))
			.findFirst()
			.ifPresent(entry -> bonus[0] += entry.getValue());

		if (bonus[0] > 0) return bonus[0];

		// Check for Smite on applicable targets
		enchantments.entrySet().stream()
			.filter(e -> e.getKey().toString().contains("Smite"))
			.findFirst()
			.filter(entry -> target.getType().is(EntityTypeTags.SENSITIVE_TO_SMITE))
			.ifPresent(entry -> bonus[0] += 2F * entry.getValue());

		if (bonus[0] > 0) return bonus[0];

		// Check for Bane of Arthropods on applicable targets
		enchantments.entrySet().stream()
			.filter(e -> e.getKey().toString().contains("Bane of Arthropods"))
			.findFirst()
			.filter(entry -> target.getType().is(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS))
			.ifPresent(entry -> bonus[0] += 2F * entry.getValue());

		return bonus[0];
	}


/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	public static void applyFireAspect(LivingEntity target, Map<Enchantment, Integer> enchantments) {

		// Defensive check for null map
		if (enchantments == null) return;

		enchantments.entrySet().stream().filter(e -> e.getKey().toString().contains("Fire Aspect"))
			.findFirst().ifPresent(entry -> {
				int level = entry.getValue();
				target.igniteForTicks(80 * level);
			});
	}


	/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	public static void applyKnockback(AbstractArrow projectile, LivingEntity target, Map<Enchantment, Integer> enchantments) {
		if (enchantments == null) return;

		enchantments.entrySet().stream()
			.filter(e -> e.getKey().toString().contains("Knockback"))
			.findFirst()
			.ifPresent(entry -> {
				int level = entry.getValue();

				Vec3 knockbackDir;
				if (projectile.getDeltaMovement().length() > 0.1) {
					knockbackDir = projectile.getDeltaMovement().normalize();
				} else if (projectile.getOwner() != null) {
					knockbackDir = target.position().subtract(projectile.getOwner().position()).normalize();
				} else {
					knockbackDir = new Vec3(0, 0, 1).yRot((float) Math.toRadians(-projectile.getYRot()));
				}

				double strength = 0.5 * level;
				target.push(knockbackDir.x * strength, 0.1 * level, knockbackDir.z * strength);

				projectile.hasImpulse = true;
			});
	}


/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	public static void applyCleaving(AbstractArrow projectile, Map<Enchantment, Integer> enchantments, RandomSource random) {
		if (enchantments == null) return;

		if (!enchantments.entrySet().stream().anyMatch(e -> e.getKey().toString().contains("Sweeping Edge") && e.getValue() > 0)) {
			projectile.setDeltaMovement(projectile.getDeltaMovement().scale(Mth.randomBetween(random, -0.10420F, -0.08420F)));
			projectile.hasImpulse = true;
		}
	}


	/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
  /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	public static boolean miscEntityHit(Projectile projectile, Entity hitEntity, Level level, RandomSource random) {
		double x = hitEntity.getX();
		double y = hitEntity.getY();
		double z = hitEntity.getZ();

		if ((hitEntity instanceof ChestBoat) || (hitEntity instanceof MinecartChest)) {
			projectile.setDeltaMovement(projectile.getDeltaMovement().scale(-Mth.randomBetween(random, -0.1420F, -0.69420F)));
			projectile.hasImpulse = true;
			return true;
		} else if (hitEntity instanceof FallingBlockEntity fbe) {
			var state = fbe.getBlockState();
			level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(state.getBlock().asItem())));
			hitEntity.discard();
			return true;
		} else if (hitEntity instanceof Boat boat) {
			level.addFreshEntity(new ItemEntity(level, x, y, z, boat.getDropItem().getDefaultInstance()));
			hitEntity.discard();
			return true;
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
			return true;
		} else if (hitEntity instanceof Painting) {
			level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.PAINTING)));
			hitEntity.discard();
			return true;
		} else if (hitEntity instanceof LeashFenceKnotEntity) {
			level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.LEAD)));
			hitEntity.discard();
			return true;
		} else if (hitEntity.getType().is(OP_TagKeyUtil.Entities.IMPACT_PROJECTILES)) {
			if (hitEntity instanceof ItemSupplier supplier) {
				ItemStack drop = supplier.getItem();
				if (!drop.isEmpty()) {
					level.addFreshEntity(new ItemEntity(level, x, y, z, drop.copy()));
				}
			}
			hitEntity.discard();
			projectile.setDeltaMovement(projectile.getDeltaMovement().scale(-0.01));
			projectile.hasImpulse = true;
			return true;
		} else if (hitEntity instanceof EndCrystal crystal) {
			level.explode(projectile, crystal.getX(), crystal.getY(), crystal.getZ(), 6.0F, false, Level.ExplosionInteraction.BLOCK);
			crystal.discard();
			projectile.setDeltaMovement(projectile.getDeltaMovement().scale(-Mth.randomBetween(random, -0.420F, -1.1420F)));
			projectile.hasImpulse = true;
			return true;
		}

		return false;
	}


 /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
}
