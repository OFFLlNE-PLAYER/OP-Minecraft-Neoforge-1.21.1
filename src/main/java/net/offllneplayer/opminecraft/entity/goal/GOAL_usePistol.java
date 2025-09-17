package net.offllneplayer.opminecraft.entity.goal;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.*;
import net.offllneplayer.opminecraft.eventhandler.spawnhandler.SpawnEnchantments;
import net.offllneplayer.opminecraft.items._iwe.pistol.PistolItem;
import net.offllneplayer.opminecraft.items._iwe.pistol.PistolMaterial;

import java.util.EnumSet;


/**
 *  Goal which enables minecraft entities to use pistols, simple as that.
 *  When mob is out of ammo and has reloaded, switches to dynamic sidearm - remember - it's faster than reloading!
 *  When the main-hand of the user does not contain a pistol, passes to vanilla behavior. (regular mêlée/bow engagement)
 */
public class GOAL_usePistol extends Goal {
	private final Mob mob;
	private final double speedModifier;
	private final float attackRadiusSqr;
	private int cooldownTicks = 0;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;

	public GOAL_usePistol(Mob mob, double speedModifier, float attackRadius) {
		this.mob = mob;
		this.speedModifier = speedModifier;
		this.attackRadiusSqr = attackRadius * attackRadius;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		// don't run this goal if its running already
		if (this.cooldownTicks > 0) {
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
		
		// Get material for ammo check from the pistol item
		PistolMaterial material = ((PistolItem)pistol.getItem()).getPistolMaterial();
		ItemStack offhand = this.mob.getOffhandItem();

		// Check if we have any usable ammo
		boolean hasAmmoInGun = pistol.getDamageValue() < pistol.getMaxDamage();
		boolean hasAmmoInOffhand = material != null && offhand.is(material.getRegisteredAmmo()) && offhand.getCount() > 0;

		// Can use if pistol has ammo OR if we have correct ammo in offhand for reloading
		return (hasAmmoInGun) || (pistol.getDamageValue() == pistol.getMaxDamage() && (hasAmmoInOffhand));// Pass to next goal when completely out of ammo
	}


	@Override
	public boolean canContinueToUse() {
		// Must have pistol in main hand
		ItemStack pistol = this.mob.getMainHandItem();
		if (pistol.isEmpty() || !(pistol.getItem() instanceof PistolItem)) {
			return false;
		}

		// Get material for ammo check from the pistol item
		PistolMaterial material = ((PistolItem)pistol.getItem()).getPistolMaterial();
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

		// Always continue if we're actively using the items
		if (this.mob.isUsingItem()) {
			return true;
		}

		// Continue if we're still in attack cooldown
		if (this.cooldownTicks > 0) {
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
		this.cooldownTicks = 0;
		if (this.mob.isUsingItem()) {
			this.mob.stopUsingItem();
		}

		// Add weapon switching logic when stopping due to being out of ammo
		ItemStack pistol = this.mob.getMainHandItem();
		LivingEntity target = this.mob.getTarget();

		if (target != null && pistol.getItem() instanceof PistolItem pistolItem &&
				pistol.getDamageValue() >= pistol.getMaxDamage()) {

			ItemStack offhand = this.mob.getOffhandItem();
			Item requiredAmmo = pistolItem.getPistolMaterial().getRegisteredAmmo();

			// Only switch if pistol is empty AND no matching ammo in off-hand
			if (offhand.isEmpty() || !offhand.is(requiredAmmo)) {
				ItemStack newWeapon;
				Item targetWeapon = target.getMainHandItem().getItem();

				if (targetWeapon instanceof SwordItem || targetWeapon instanceof AxeItem) {
					newWeapon = new ItemStack(Items.DIAMOND_AXE);
				} else {
					newWeapon = new ItemStack(Items.BOW);
				}

				if (RandomSource.create().nextBoolean()) {
					SpawnEnchantments.applyWeaponEnchantments(this.mob.level(), newWeapon);
				}

				this.mob.setItemInHand(InteractionHand.MAIN_HAND, newWeapon);
				this.mob.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

				double x = this.mob.getX();
				double y = this.mob.getY();
				double z = this.mob.getZ();
				this.mob.level().playSound(null, x, y, z, SoundEvents.WOOL_PLACE, SoundSource.HOSTILE, 0.420F, 0.9F);
			}
		}
	}


	@Override
	public void tick() {
		// Early exits - check all invalid states first
		LivingEntity target = this.mob.getTarget();
		if (target == null) return;

		ItemStack pistol = this.mob.getMainHandItem();
		if (pistol.isEmpty()) return;

		if (pistol.getItem() instanceof PistolItem pistolItem) {

			PistolMaterial material = pistolItem.getPistolMaterial();
			ItemStack offhand = this.mob.getOffhandItem();
			boolean hasAmmoInGun = pistol.getDamageValue() < pistol.getMaxDamage();
			boolean hasAmmoInOffhand = offhand.is(material.getRegisteredAmmo()) && offhand.getCount() > 0;

			// If we have no ammo at all, exit (should never get here, this is fallback exit)
			if (!hasAmmoInGun && !hasAmmoInOffhand) return;

			// Decrement attack time
			if (this.cooldownTicks > 0) {
				this.cooldownTicks--;
			}

			double distanceSqr = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
			boolean hasLineOfSight = this.mob.getSensing().hasLineOfSight(target);

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

				// Set aggressive state for aiming animation
				this.mob.setAggressive(true);

				// Cancel shot if line of sight is lost
				if (hasAmmoInGun && !hasLineOfSight) {
					this.mob.stopUsingItem();
					this.cooldownTicks = 10; // Small cooldown before trying again
					return;
				}

				// Release use (fire) after useTime; let releaseUsing handle reload vs fire logic
				if (useTime >= PistolItem.useTime) {
					boolean wasEmpty = pistol.getDamageValue() >= pistol.getMaxDamage();


					int remainingUseTicks = pistol.getUseDuration(this.mob) - useTime;
					pistolItem.releaseUsing(pistol, this.mob.level(), this.mob, remainingUseTicks);

					// Set appropriate cooldown based on whether we reloaded or fired
					if (wasEmpty) {
						// just reloaded - use reload cooldown
						this.cooldownTicks = material.getReloadCooldown();
					} else {
						// just fired - use fire cooldown
						this.cooldownTicks = material.getFireCooldown();
					}

					this.mob.stopUsingItem();
				}
			} else { // Not using item, start using
				if (this.cooldownTicks == 0) {
					this.mob.setAggressive(true);
					this.mob.startUsingItem(InteractionHand.MAIN_HAND);
				}
			}
		}
	}
}
