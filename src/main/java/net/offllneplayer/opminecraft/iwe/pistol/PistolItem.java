package net.offllneplayer.opminecraft.iwe.pistol;

import java.util.Random;


import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public class PistolItem extends TieredItem{

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private PistolGunMaterial pistolGunMaterial;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public PistolItem(PistolGunMaterial material) {
		super(createTier(material), createItemProperties(material));
		this.pistolGunMaterial = material;
	}

	private static Properties createItemProperties(PistolGunMaterial material) {
		Properties itemProperties = new Properties()
			.stacksTo(1)
			.durability(material.getDurability())
			.rarity(material.getRarity());

		if (material.isFireResistant()) {
			itemProperties.fireResistant();
		}

		return itemProperties;
	}


  	 /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC TOOL Item OVERRIDES]*/
	private static Tier createTier(PistolGunMaterial material) {
		return new Tier() {
			@Override
			public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
			@Override
			public int getUses() { return material.getDurability(); }
			@Override
			public float getSpeed() { return material.getMiningSpeed(); }
			@Override
			public TagKey<Block> getIncorrectBlocksForDrops() { return material.getIncorrectBlocksForDrops();}
			@Override
			public int getEnchantmentValue() { return material.getEnchantability(); }
			@Override
			public float getAttackDamageBonus() { return 0; }
		};
	}


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[Use Item OVERRIDES]*/
	 @Override
	 public int getUseDuration(ItemStack itemstack, LivingEntity user) {
		 return this.pistolGunMaterial.getAttackSpeed();
	 }

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[use]*/
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		player.startUsingItem(hand);
		return InteractionResultHolder.consume(stack);
	}

	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[releaseUsingItem]*/
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (!(entity instanceof Player player)) return stack;
		if (level.isClientSide) return stack;

		// Process the used hand
		InteractionHand usedHand = player.getUsedItemHand();
		processGun(stack, level, player, usedHand);

		// Check if we have the same gun in the other hand
		InteractionHand otherHand = usedHand == InteractionHand.MAIN_HAND ?
			InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
		ItemStack otherHandStack = player.getItemInHand(otherHand);

		// If we have the same type of gun in the other hand and it's not on cooldown, fire it too
		if (otherHandStack.getItem() == stack.getItem() && !otherHandStack.isEmpty() &&
			!player.getCooldowns().isOnCooldown(otherHandStack.getItem())) {

			player.getCooldowns().addCooldown(otherHandStack.getItem(), 20);
			processGun(otherHandStack, level, player, otherHand);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, 20);

		return stack;
	}

	/**
	 * Handles the gun use logic
	 */
	private void processGun(ItemStack stack, Level level, Player player, InteractionHand hand) {
		double yawRad = Math.toRadians(player.getYRot());
		double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
		double rightX = forwardZ, rightZ = -forwardX;
		double forwardOff = 0.420;
		double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.420 : 0.420;
		double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
		double spawnY = player.getY() + player.getEyeHeight() - 0.2420D;
		double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

		int gunMaxDurability = stack.getMaxDamage();
		int currentDamage = stack.getDamageValue();
		float randomFloat = level.getRandom().nextFloat();

		// Check if player is crouching (sneaking)
		if (player.isShiftKeyDown()) {
			// When crouching: Try to reload first (if needed), otherwise fire
			if (currentDamage > 0) {
				// Gun needs reloading
				reloadGun(stack, level, player, hand, randomFloat);
			} else {
				// Gun is full, fire it
				fireGun(stack, level, player, spawnX, spawnY, spawnZ, randomFloat);
			}
		} else {
			// Not crouching: Try to fire first, reload only if empty
			if (currentDamage < gunMaxDurability) {
				// Gun has ammo, fire it
				fireGun(stack, level, player, spawnX, spawnY, spawnZ, randomFloat);
			} else {
				// Gun is empty, reload it
				reloadGun(stack, level, player, hand, randomFloat);
			}
		}
	}

	/**
	 * Handles the gun reloading logic
	 */
	private void reloadGun(ItemStack stack, Level level, Player player, InteractionHand hand, float randomFloat) {
		int gunMaxDurability = stack.getMaxDamage();
		int currentDamage = stack.getDamageValue();

		// Don't reload if gun is already full
		if (currentDamage == 0) return;

		InteractionHand oppositeHand = hand == InteractionHand.MAIN_HAND ?
			InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
		ItemStack oppositeHandStack = player.getItemInHand(oppositeHand);

		// Check if the opposite hand holds the correct bullet type
		if (oppositeHandStack.is(this.pistolGunMaterial.getRegisteredItem())) {
			// Calculate how many bullets we need to fully reload
			int bulletsNeeded = currentDamage;
			int bulletCount = oppositeHandStack.getCount();
			int bulletsToUse = Math.min(bulletsNeeded, bulletCount);

			// Reload the gun
			if (bulletsToUse > 0) {
				// Subtract the used bullets from damage (adding them to the gun)
				stack.setDamageValue(currentDamage - bulletsToUse);
				oppositeHandStack.shrink(bulletsToUse);

				// Play reload sound
				level.playSound(null, player.getX(), player.getY(), player.getZ(),
					RegistrySounds.SAMURAI_EDGE_R, SoundSource.PLAYERS,
					0.8F, 0.9F + randomFloat * 0.2F);
			} else {
				// No bullets available
				level.playSound(null, player.getX(), player.getY(), player.getZ(),
					RegistrySounds.SAMURAI_EDGE_0, SoundSource.PLAYERS,
					0.8F, 0.9F + randomFloat * 0.2F);
			}
		} else {
			// Play empty clip sound - no bullets of the right type
			level.playSound(null, player.getX(), player.getY(), player.getZ(),
				RegistrySounds.SAMURAI_EDGE_0, SoundSource.PLAYERS,
				0.8F, 0.9F + randomFloat * 0.2F);
		}
	}

	/**
	 * Handles the gun firing logic
	 */
	private void fireGun(ItemStack stack, Level level, Player player, double spawnX, double spawnY, double spawnZ, float randomFloat) {
		int currentDamage = stack.getDamageValue();

		// Fire gun
		stack.setDamageValue(currentDamage + 1);

		// Create a new bullet with a fresh stack, not copying the original
		PistolBullet pistolBulletENT = new PistolBullet(player, level, stack.copy());
		pistolBulletENT.setPos(spawnX, spawnY, spawnZ);
		pistolBulletENT.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 14.20F, 0F);
		level.addFreshEntity(pistolBulletENT);

		int soundIndex = new Random().nextInt(4);
		level.playSound(null, player.getX(), player.getY(), player.getZ(),
			switch (soundIndex) {
				case 0 -> RegistrySounds.SAMURAI_EDGE_1;
				case 1 -> RegistrySounds.SAMURAI_EDGE_2;
				case 2 -> RegistrySounds.SAMURAI_EDGE_3;
				case 3 -> RegistrySounds.SAMURAI_EDGE_4;
				default -> RegistrySounds.SAMURAI_EDGE_1;},
			SoundSource.PLAYERS, 1, 0.9F + randomFloat * 0.2F);
	}


 /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
}