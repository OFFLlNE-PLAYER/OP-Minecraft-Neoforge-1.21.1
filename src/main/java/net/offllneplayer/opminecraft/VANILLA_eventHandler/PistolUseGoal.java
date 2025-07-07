package net.offllneplayer.opminecraft.VANILLA_eventHandler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.offllneplayer.opminecraft.iwe.beretta.PistolItem;
import net.offllneplayer.opminecraft.iwe.beretta.PistolMaterial;

import java.util.EnumSet;

public class PistolUseGoal extends Goal {
	private final Mob mob;
	private final double speedModifier;
	private final float attackRadiusSqr;
	private int attackTime = 0; // Changed from -1 to 0
	private int seeTime;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;

	public PistolUseGoal(Mob mob, double speedModifier, float attackRadius) {
		this.mob = mob;
		this.speedModifier = speedModifier;
		this.attackRadiusSqr = attackRadius * attackRadius;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		// don't run this goal if its running already
		if (this.attackTime > 0) {
			return false;
		}

		// Must have target and line of sight
		if (this.mob.getTarget() == null || !this.mob.getSensing().hasLineOfSight(this.mob.getTarget())) {
			return false;
		}

		// Must have pistol in main hand - if not, pass to next goal
		ItemStack pistol = this.mob.getMainHandItem();
		if (pistol.isEmpty() || !(pistol.getItem() instanceof PistolItem)) {
			return false;
		}

		// Get material for ammo check
		PistolMaterial material = getPistolMaterial();
		ItemStack offhand = this.mob.getOffhandItem();

		// Can use if pistol has ammo OR if we have correct ammo in offhand for reloading
		if (pistol.getDamageValue() < pistol.getMaxDamage() ||
				(material != null && offhand.is(material.getRegisteredAmmo()) && offhand.getCount() > 0)) {
			return true;
		}

		return false; // Pass to next goal when completely out of ammo
	}

	@Override
	public boolean canContinueToUse() {
		// Must have pistol in main hand
		ItemStack pistol = this.mob.getMainHandItem();
		if (pistol.isEmpty() || !(pistol.getItem() instanceof PistolItem)) {
			return false;
		}

		// Get material for ammo check
		PistolMaterial material = getPistolMaterial();
		ItemStack offhand = this.mob.getOffhandItem();

		// Check if we have any usable ammo
		boolean hasAmmoInGun = pistol.getDamageValue() < pistol.getMaxDamage();
		boolean hasAmmoInOffhand = material != null && offhand.is(material.getRegisteredAmmo()) && offhand.getCount() > 0;

		// If we have no ammo at all, stop immediately
		if (!hasAmmoInGun && !hasAmmoInOffhand) {
			return false;
		}

		// Must have target
		if (this.mob.getTarget() == null) {
			return false;
		}

		// Always continue if we're actively using the item
		if (this.mob.isUsingItem()) {
			return true;
		}

		// Continue if we're still in attack cooldown
		if (this.attackTime > 0) {
			return true;
		}

		// Continue if we have a target and line of sight
		if (this.mob.getSensing().hasLineOfSight(this.mob.getTarget())) {
			return true;
		}

		// Continue if we're still moving to target
		return !this.mob.getNavigation().isDone();
	}



	@Override
	public void start() {
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
		this.mob.setAggressive(false);
		this.seeTime = 0;
		this.attackTime = 0;
		if (this.mob.isUsingItem()) {
			this.mob.stopUsingItem();
		}
	}

	private PistolMaterial getPistolMaterial() {
		ItemStack pistol = this.mob.getMainHandItem();
		if (pistol.getItem() instanceof PistolItem pistolItem) {
			try {
				String materialName = pistol.getItem().builtInRegistryHolder().key().location().getPath().toUpperCase();
				return PistolMaterial.valueOf(materialName);
			} catch (IllegalArgumentException e) {
				return PistolMaterial.VALENTINE_BERETTA;
			}
		}
		return null;
	}

	@Override
	public void tick() {
		// Early exits - check all invalid states first
		LivingEntity target = this.mob.getTarget();
		if (target == null) return;

		PistolMaterial material = getPistolMaterial();
		if (material == null) return;

		ItemStack pistol = this.mob.getMainHandItem();
		if (pistol.isEmpty() || !(pistol.getItem() instanceof PistolItem)) return;

		ItemStack offhand = this.mob.getOffhandItem();
		boolean hasAmmoInGun = pistol.getDamageValue() < pistol.getMaxDamage();
		boolean hasAmmoInOffhand = material != null && offhand.is(material.getRegisteredAmmo()) && offhand.getCount() > 0;

		// If we have no ammo at all, exit early
		if (!hasAmmoInGun && !hasAmmoInOffhand) {
			return;
		}

		// Decrement attack time
		if (this.attackTime > 0) {
			this.attackTime--;
		}

		double distanceSqr = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
		boolean hasLineOfSight = this.mob.getSensing().hasLineOfSight(target);

		// Update see time for line of sight
		if (hasLineOfSight) {
			++this.seeTime;
		} else {
			this.seeTime = 0;
		}

		// Movement and strafing logic
		if (!(distanceSqr > (double)this.attackRadiusSqr)) {
			this.mob.getNavigation().stop();
			++this.strafingTime;
		} else {
			this.mob.getNavigation().moveTo(target, this.speedModifier);
			this.strafingTime = -1;
		}

		if (this.strafingTime >= 20) {
			if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
				this.strafingClockwise = !this.strafingClockwise;
			}
			if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
				this.strafingBackwards = !this.strafingBackwards;
			}
			this.strafingTime = 0;
		}

		if (this.strafingTime > -1) {
			if (distanceSqr > (double)(this.attackRadiusSqr * 0.75F)) {
				this.strafingBackwards = false;
			} else if (distanceSqr < (double)(this.attackRadiusSqr * 0.25F)) {
				this.strafingBackwards = true;
			}
			this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
			this.mob.lookAt(target, 30.0F, 30.0F);
		} else {
			this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
		}

		// Animation and attack logic
		if (this.mob.isUsingItem()) {
			int useTime = this.mob.getTicksUsingItem();
			int requiredTicks = material.getAttackSpeed();

			// Set aggressive state for aiming animation
			this.mob.setAggressive(true);

			// Check if gun is empty (reloading) or has ammo (shooting)
			boolean gunEmpty = pistol.getDamageValue() >= pistol.getMaxDamage();

			// Cancel shot if line of sight is lost while shooting (but allow reloading to continue)
			if (!gunEmpty && !hasLineOfSight) {
				this.mob.stopUsingItem();
				this.mob.setAggressive(false);
				this.attackTime = 10; // Small cooldown before trying again
				return;
			}

			// Fire/reload when we reach the required ticks
			if (useTime >= requiredTicks) {
				boolean wasEmpty = pistol.getDamageValue() >= pistol.getMaxDamage();

				// Release the item - let releaseUsing handle reload vs fire logic
				if (pistol.getItem() instanceof PistolItem pistolItem) {
					int remainingUseTicks = pistol.getUseDuration(this.mob) - useTime;
					pistolItem.releaseUsing(pistol, this.mob.level(), this.mob, remainingUseTicks);
				}

				this.mob.stopUsingItem();
				this.mob.setAggressive(false);

				// Set appropriate cooldown based on whether we reloaded or fired
				if (wasEmpty) {
					// We just reloaded - use reload cooldown
					this.attackTime = material.getReloadCooldown();
				} else {
					// We just fired - use fire cooldown
					this.attackTime = material.getFireCooldown();
				}
			}
		} else {
			// Not using item
			this.mob.setAggressive(false);

			if (this.attackTime <= 0) {
				// Check if gun is empty and needs reloading
				boolean gunEmpty = pistol.getDamageValue() >= pistol.getMaxDamage();

				// Only start using if we actually have ammo or reload capability
				if (gunEmpty && hasAmmoInOffhand) {
					// Gun is empty but we have ammo to reload
					this.mob.startUsingItem(InteractionHand.MAIN_HAND);
					this.mob.setAggressive(true);
				} else if (!gunEmpty && hasLineOfSight) {
					// Gun has ammo and we have line of sight to shoot
					this.mob.startUsingItem(InteractionHand.MAIN_HAND);
					this.mob.setAggressive(true);
				}
			}
		}
	}
}

