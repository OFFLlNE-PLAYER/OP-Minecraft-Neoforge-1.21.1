package net.offllneplayer.opminecraft.iwe.beretta;

import java.util.List;
import java.util.Random;


import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public class PistolItem extends TieredItem{

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private PistolMaterial pistolMaterial;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public PistolItem(PistolMaterial material) {
		super(createTier(material), createItemProperties(material));
		this.pistolMaterial = material;
	}

	private static Properties createItemProperties(PistolMaterial material) {
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
	private static Tier createTier(PistolMaterial material) {
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


	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.opminecraft.beretta.description_0"));
	}


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[Use Item OVERRIDES]*/
	 @Override
	 public int getUseDuration(ItemStack itemstack, LivingEntity user) {
		 return this.pistolMaterial.getAttackSpeed();
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

		InteractionHand usedHand = player.getUsedItemHand();

		// Check if we have a beretta in the other hand
		InteractionHand otherHand = usedHand == InteractionHand.MAIN_HAND ?
			InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
		ItemStack otherHandStack = player.getItemInHand(otherHand);

		if (otherHandStack.is(OP_TagKeyUtil.Items.BERETTAS) && !otherHandStack.isEmpty() &&
			!player.getCooldowns().isOnCooldown(otherHandStack.getItem())) {

			// Check if both berettas are the same type
			boolean sameGunType = stack.getItem() == otherHandStack.getItem();

			// Compare durabilities - lower damage value means more durability remaining
			int stackDamage = stack.getDamageValue();
			int otherStackDamage = otherHandStack.getDamageValue();

			if (otherStackDamage < stackDamage) {
				// Other hand gun has more durability, use it
				processGun(otherHandStack, level, player, otherHand);
				player.awardStat(Stats.ITEM_USED.get(otherHandStack.getItem()));

				// Add cooldown to both guns if they're different types
				player.getCooldowns().addCooldown(otherHandStack.getItem(), 20);
				if (!sameGunType) {
					player.getCooldowns().addCooldown(this, 20);
				}
			} else {
				// Used hand gun has more or equal durability, use it
				processGun(stack, level, player, usedHand);
				player.awardStat(Stats.ITEM_USED.get(this));

				// Add cooldown to both guns if they're different types
				player.getCooldowns().addCooldown(this, 20);
				if (!sameGunType) {
					player.getCooldowns().addCooldown(otherHandStack.getItem(), 20);
				}
			}
		} else {
			// Default to used hand if other hand doesn't have a beretta
			processGun(stack, level, player, usedHand);
			player.awardStat(Stats.ITEM_USED.get(this));
			player.getCooldowns().addCooldown(this, 20);
		}

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
			// Check if reload is possible before attempting it
			InteractionHand oppositeHand = hand == InteractionHand.MAIN_HAND ?
					InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
			ItemStack oppositeHandStack = player.getItemInHand(oppositeHand);
			boolean hasAmmoInOtherHand = oppositeHandStack.is(this.pistolMaterial.getRegisteredItem()) &&
					!oppositeHandStack.isEmpty();

			// When crouching: Try to reload only if needed AND possible, otherwise fire if has ammo
			if (currentDamage > 0 && hasAmmoInOtherHand) {
				// Gun needs reloading and player has ammo
				reloadGun(stack, level, player, hand, randomFloat);
			} else if (currentDamage < gunMaxDurability) {
				// Gun has ammo, fire it
				fireGun(stack, level, player, spawnX, spawnY, spawnZ, randomFloat);
			} else {
				// Gun is empty and no ammo to reload, play empty sound
				playEmptySound(level, player, randomFloat);
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
		int currentDamage = stack.getDamageValue();

		// Don't reload if gun is already full
		if (currentDamage == 0) return;

		InteractionHand oppositeHand = hand == InteractionHand.MAIN_HAND ?
				InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
		ItemStack oppositeHandStack = player.getItemInHand(oppositeHand);

		// Check if the opposite hand holds the correct bullet type
		if (oppositeHandStack.is(this.pistolMaterial.getRegisteredItem())) {
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
				playReloadSound(level, player, randomFloat);
			} else {
				// No bullets available
				playEmptySound(level, player, randomFloat);
			}
		} else {
			// Play empty clip sound - no bullets of the right type
			playEmptySound(level, player, randomFloat);
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

		float vol = 1F;
		float basePitch = 1F;
		float pitchVariance = 0.0420F;
		int soundIndex = 0;

		// Sound selection varies based on beretta material
		if (this.pistolMaterial == PistolMaterial.TITAN_BERETTA ||
				this.pistolMaterial == PistolMaterial.REDFIELD_BERETTA ||
				this.pistolMaterial == PistolMaterial.WESKER_BERETTA ||
				this.pistolMaterial == PistolMaterial.VALENTINE_BERETTA) {
			soundIndex = new Random().nextInt(4);
		} else if (this.pistolMaterial == PistolMaterial.TITAN_HANDCANNON) {
			soundIndex = 5;
		}

		// Define the sound based on the soundIndex
		var sound = switch (soundIndex) {
			case 0 -> RegistrySounds.SAMURAI_EDGE_1;
			case 1 -> RegistrySounds.SAMURAI_EDGE_2;
			case 2 -> RegistrySounds.SAMURAI_EDGE_3;
			case 3 -> RegistrySounds.SAMURAI_EDGE_4;
			case 4 -> RegistrySounds.HANDCANNON_1;
			case 5 -> RegistrySounds.HANDCANNON_2;
			default -> RegistrySounds.SAMURAI_EDGE_1;
		};

		// Set volume and basePitch based on beretta material
		if (this.pistolMaterial == PistolMaterial.TITAN_BERETTA) {
			vol = 0.9F;
			basePitch = 0.88F;
			pitchVariance = 0.06F;
		} else if (this.pistolMaterial == PistolMaterial.REDFIELD_BERETTA) {
			vol = 0.9F;
			basePitch = 0.9F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.WESKER_BERETTA) {
			vol = 0.7F;
			basePitch = 0.94F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.VALENTINE_BERETTA) {
			vol = 0.8F;
			basePitch = 0.98F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.TITAN_HANDCANNON) {
			vol = 1F;
			basePitch = 1F;
			pitchVariance = 0.0420F;
		}

		// Play the sound with the determined parameters
		level.playSound(null, player.getX(), player.getY(), player.getZ(),
				sound, SoundSource.PLAYERS, vol, basePitch + randomFloat * pitchVariance);
	}

	private void playReloadSound(Level level, Player player, float randomFloat) {
		float vol = 1F;
		float basePitch = 1F;
		float pitchVariance = 0.0420F;
		var sound = RegistrySounds.SAMURAI_EDGE_R;

		// Set volume and basePitch based on beretta material
		if (this.pistolMaterial == PistolMaterial.TITAN_BERETTA) {
			vol = 0.9F;
			basePitch = 0.88F;
			pitchVariance = 0.06F;
		} else if (this.pistolMaterial == PistolMaterial.REDFIELD_BERETTA) {
			vol = 0.9F;
			basePitch = 0.9F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.WESKER_BERETTA) {
			vol = 0.7F;
			basePitch = 0.94F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.VALENTINE_BERETTA) {
			vol = 0.8F;
			basePitch = 0.98F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.TITAN_HANDCANNON) {
			sound = RegistrySounds.HANDCANNON_R;
			vol = 1F;
			basePitch = 1F;
			pitchVariance = 0.0420F;
		}

		level.playSound(null, player.getX(), player.getY(), player.getZ(),
				sound, SoundSource.PLAYERS, vol, basePitch + randomFloat * pitchVariance);
	}

	private void playEmptySound(Level level, Player player, float randomFloat) {
		float vol = 0.8F;
		float basePitch = 0.9F;
		float pitchVariance = 0.2F;
		var sound = RegistrySounds.SAMURAI_EDGE_0;

		// Set volume and basePitch based on beretta material
		if (this.pistolMaterial == PistolMaterial.TITAN_BERETTA) {
			vol = 0.9F;
			basePitch = 0.88F;
			pitchVariance = 0.06F;
		} else if (this.pistolMaterial == PistolMaterial.REDFIELD_BERETTA) {
			vol = 0.9F;
			basePitch = 0.9F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.WESKER_BERETTA) {
			vol = 0.7F;
			basePitch = 0.94F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.VALENTINE_BERETTA) {
			vol = 0.8F;
			basePitch = 0.98F;
			pitchVariance = 0.0420F;
		} else if (this.pistolMaterial == PistolMaterial.TITAN_HANDCANNON) {
			sound = RegistrySounds.HANDCANNON_0;
			vol = 1F;
			basePitch = 1F;
			pitchVariance = 0.0420F;
		}

		level.playSound(null, player.getX(), player.getY(), player.getZ(),
				sound, SoundSource.PLAYERS, vol, basePitch + randomFloat * pitchVariance);
	}


 /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
}