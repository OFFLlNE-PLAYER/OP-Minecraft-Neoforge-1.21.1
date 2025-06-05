package net.offllneplayer.opminecraft.iwe.tntstick;


import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public class TNTStickItem extends Item implements DispensibleProjectile {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private static final String FUSE_SOUND_PLAYING_TAG = "TNTFuseSoundPlaying";
	private static final int FUSE_DURATION = 100;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public TNTStickItem() {
		super(new Properties()
			.stacksTo(4)
			.rarity(Rarity.UNCOMMON));
	}

	 /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
  /*[HELP]*/
	private void stopFuseSound(Level level, double x, double y, double z) {
		if (!(level instanceof ServerLevel serverLevel)) return;

		double hearingRadius = 24.0;
		ResourceLocation soundId = RegistrySounds.TNT_FUSE.getId();
		ClientboundStopSoundPacket packet = new ClientboundStopSoundPacket(soundId, SoundSource.NEUTRAL);

		for (ServerPlayer player : serverLevel.getPlayers(p ->
			p.distanceToSqr(x, y, z) <= hearingRadius * hearingRadius)) {
			player.connection.send(packet);
		}
	}


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/* ====================================================================================*/
  /*[Durability Bar]*/
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isBarVisible(ItemStack stack) {
		// Only show the bar when the item is being used
		if (Minecraft.getInstance().player == null) return false;
		return Minecraft.getInstance().player.getUseItem() == stack;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public int getBarWidth(ItemStack stack) {
		// Get the player using the item
		Player player = Minecraft.getInstance().player;
		if (player == null || player.getUseItem() != stack) return 13; // Full bar when not in use

		// Calculate how much of the fuse duration has elapsed
		int remainingUseTicks = player.getUseItemRemainingTicks();
		// Map the remaining ticks to the bar width (13 = full, 0 = empty)
		return Math.round(13.0F * remainingUseTicks / (float)FUSE_DURATION);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public int getBarColor(ItemStack stack) {
		// Create a yellow to orange to red gradient as the fuse burns
		Player player = Minecraft.getInstance().player;
		if (player == null || player.getUseItem() != stack) return tntStickBarColor_Method.hexToInt("FFFF00"); // Yellow when not in use

		// Calculate progress as a value between 0 and 1
		float progress = 1.0F - (float) player.getUseItemRemainingTicks() / FUSE_DURATION;

		return tntStickBarColor_Method.countdownBarColor(progress,
			"BDFF2A",  // Early stage - pastel yellow (189, 255, 42)
			"A9B92A",  // Mid stage (169, 185, 42)
			"A90016",  // Late stage - dark red (169, 0, 22)
			"8B000C",  // Final base - darker red (139, 0, 12)
			"1E160A",  // Final pulse amounts (30, 22, 10)
			8.0F);     // Pulse frequency, higher = more pulses
	}



	 /* ====================================================================================*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[Use Item OVERRIDES]*/
	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity user) {return FUSE_DURATION;}
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.SPEAR;}

	 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[use]*/
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack tstack = player.getItemInHand(hand);
		InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
		ItemStack otherHandStack = player.getItemInHand(otherHand);

		// Check if player is crouching and other hand does not have flint and steel
		if (player.isCrouching() && !(otherHandStack.getItem() instanceof FlintAndSteelItem)) {
			if (player.isUnderWater() || player.getCooldowns().isOnCooldown(this)) {
				return InteractionResultHolder.fail(tstack);
			}

			if (!level.isClientSide) {
				// Create and setup the thrown TNT entity with minimum pull
				ThrownTNTStick tstick = new ThrownTNTStick(player, level, tstack.copy());
				float minPull = 0.420F;
				double yawRad = Math.toRadians(player.getYRot());
				double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
				double rightX = forwardZ, rightZ = -forwardX;
				double forwardOff = 0.7;
				double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.5 : 0.5;
				double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
				double spawnY = player.getY() + player.getEyeHeight() + 0.22D;
				double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

				tstick.setPullRatio(minPull);
				tstick.setPos(spawnX, spawnY, spawnZ);

				float randomVelo = Mth.nextFloat(RandomSource.create(), 1.69420F, 2.2420F);

				// Throw immediately with minimum pull
				tstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, minPull * randomVelo, 0.420F);
				level.addFreshEntity(tstick);

				player.awardStat(Stats.ITEM_USED.get(this));
				tstack.shrink(1);

				level.playSound(null, player.getX(), player.getY(), player.getZ(),
					SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.69F, 0.420F * randomVelo + minPull);

				player.getCooldowns().addCooldown(this, 20);
			}

			return InteractionResultHolder.sidedSuccess(tstack, level.isClientSide);
		}

		// Original code for when player is not crouching
		if (otherHandStack.getItem() instanceof FlintAndSteelItem) {
			if (player.isUnderWater() || player.getCooldowns().isOnCooldown(this)) {
				return InteractionResultHolder.fail(tstack);
			}
			level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 1F, 1F);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), RegistrySounds.TNT_FUSE.get(), SoundSource.NEUTRAL, 1F, 1.1420F);
			otherHandStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(otherHand));
			player.swing(otherHand, true);
			player.getCooldowns().addCooldown(this, 20);
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(tstack);
		}

		// If no flint and steel in other hand
		player.displayClientMessage(Component.literal("Jack Black's words echoed... FLINT AND STEEL!"), true);
		player.swing(hand, true);
		return InteractionResultHolder.fail(tstack);
	}


	 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
	/*-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___*/
  /*[on Use Tick]*/
	@Override
	public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
		if (!(user instanceof Player player)) return;

		int timeUsed = getUseDuration(stack, user) - remainingUseTicks;

		// Start playing sound on first tick
		if (timeUsed == 1) {
			player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, true);
		}

		// Calculate hand position for particles
		double yawRad = Math.toRadians(player.getYRot());
		double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
		double rightX = forwardZ, rightZ = -forwardX;
		double fuseProgress = 1.0F - (float) remainingUseTicks / FUSE_DURATION;
		double forwardOff = -0.8D + (0.14D * fuseProgress);
		double handOffset = player.getUsedItemHand() == InteractionHand.MAIN_HAND ? -0.3D : 0.3D;
		double particleX = player.getX() + forwardX * forwardOff + rightX * handOffset;
		double particleY = player.getY() + player.getEyeHeight() + 0.24D;
		double particleZ = player.getZ() + forwardZ * forwardOff + rightZ * handOffset;

		// Spawn flame particles at hand position every 2 ticks
		if (level.isClientSide() && timeUsed > 1 && timeUsed % 2 == 0) {
			level.addParticle(ParticleTypes.FLAME,
				particleX, particleY, particleZ,
				Mth.nextDouble(level.getRandom(), 0.01D, 0.02D),
				Mth.nextDouble(level.getRandom(), 0.01D, 0.02D),
				Mth.nextDouble(level.getRandom(), 0.01D, 0.02D));
		}


		// If we've reached the end of use duration
		if (remainingUseTicks <= 1) {
			player.swing(player.getUsedItemHand(), true);
			level.playSound(null, user.getX(), user.getY(), user.getZ(),
				SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.5F);
		}
	}

	 /*-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___-=- o_ <=--___*/
	/* X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-*/
  /*[on Stop Using]*/
	@Override
	public void onStopUsing(ItemStack stack, LivingEntity livingEntity, int timeLeft) {
		if (!(livingEntity instanceof Player player)) return;

		// Stop fuse sound if it was playing
		if (player.getPersistentData().getBoolean(FUSE_SOUND_PLAYING_TAG)) {
			stopFuseSound(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
			player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, false);
		}
	}

	 /* X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-=X-<=-*/
	/* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
  /*[release Using]*/
	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
		if (!(user instanceof Player player) || level.isClientSide) return;

		// Stop fuse sound if it was playing
		if (player.getPersistentData().getBoolean(FUSE_SOUND_PLAYING_TAG)) {
			stopFuseSound(level, user.getX(), user.getY(), user.getZ());
			player.getPersistentData().putBoolean(FUSE_SOUND_PLAYING_TAG, false);
		}

		int timeUsed = getUseDuration(stack, user) - timeLeft;
		float pull = Mth.clamp(timeUsed / (float) FUSE_DURATION, 0.420F, 1F);

		InteractionHand hand = player.getUsedItemHand();

		// Calculate spawn position offset from player
		double yawRad = Math.toRadians(player.getYRot());
		double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
		double rightX = forwardZ, rightZ = -forwardX;
		double forwardOff = 0.7;
		double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.5 : 0.5;
		double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
		double spawnY = player.getY() + player.getEyeHeight();
		double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

		// Create and setup the thrown TNT entity
		ThrownTNTStick tstick = new ThrownTNTStick(player, level, stack.copy());

		// Pass remaining fuse time to entity using our new setter SHAREDMETHODS
		int fuseTimeRemaining = FUSE_DURATION - timeUsed;
		tstick.setLitTime(fuseTimeRemaining);

		tstick.setPullRatio(pull);
		tstick.setPos(spawnX, spawnY, spawnZ);

		float randomVelo = Mth.nextFloat(RandomSource.create(), 1.69420F, 2.2420F);

		tstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * randomVelo, 0.420F);
		level.addFreshEntity(tstick);

		player.awardStat(Stats.ITEM_USED.get(this));
		stack.shrink(1);

		level.playSound(null, player.getX(), player.getY(), player.getZ(),
			SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.69F, 0.420F * randomVelo + pull);
	}

	 /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
	/*-----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>*/
  /*[as Projectile]*/
	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		ThrownTNTStick tstick = new ThrownTNTStick(null, level, stack.copy());
		tstick.setLitTime(100);  // Use our new setter
		tstick.setPos(pos.x(), pos.y(), pos.z());

		float randomVelo = Mth.nextFloat(RandomSource.create(), 1.69420F, 2.2420F);
		float speed = getDispenseSpeed() * randomVelo;
		RandomSource random = level.getRandom();
		float randomSpread = random.nextFloat() * 0.1F - 0.05F; // -0.05 to 0.05

		// Special handling for UP and DOWN directions
		if (direction == Direction.UP) {
			// For upward dispensers, send mostly upward with slight random horizontal spread
			float randomAngle = random.nextFloat() * ((float) Math.PI * 2);
			tstick.setDeltaMovement(
				Math.cos(randomAngle) * randomSpread * speed,
				speed, // Primarily upward movement
				Math.sin(randomAngle) * randomSpread * speed
			);
		} else if (direction == Direction.DOWN) {
			// For downward dispensers, send mostly downward with slight random horizontal spread
			float randomAngle = random.nextFloat() * ((float) Math.PI * 2);
			tstick.setDeltaMovement(
				Math.cos(randomAngle) * randomSpread * speed,
				-speed, // Primarily downward movement
				Math.sin(randomAngle) * randomSpread * speed
			);
		} else {
			// Horizontal dispensers (NORTH, SOUTH, EAST, WEST)
			float randomUpAngle = random.nextFloat() * 0.420F;
			tstick.setDeltaMovement(
				direction.getStepX() * speed + (direction.getStepX() == 0 ? randomSpread : 0),
				0.269F + randomUpAngle, // Slight upward arc for horizontal motion
				direction.getStepZ() * speed + (direction.getStepZ() == 0 ? randomSpread : 0)
			);
		}

		tstick.setYRot(random.nextFloat() * 360F);

		level.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL, 1.0F, 1.0F);

		return tstick;
	}
}
