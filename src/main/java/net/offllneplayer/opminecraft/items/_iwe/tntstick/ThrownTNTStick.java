package net.offllneplayer.opminecraft.items._iwe.tntstick;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;


public class ThrownTNTStick extends AbstractArrow {

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
  /*[DATA]*/

	private static final EntityDataAccessor<Integer> TNT_LIT_TIMER = SynchedEntityData.defineId(ThrownTNTStick.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Float> TNT_SPIN_ROTATION = SynchedEntityData.defineId(ThrownTNTStick.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> TNT_GROUNDED_zROTATION = SynchedEntityData.defineId(ThrownTNTStick.class, EntityDataSerializers.FLOAT);

	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private BlockPos stuckPos;
	private Block stuckBlock;
	private Direction stuckDirection = Direction.NORTH;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDERS]*/
	public ThrownTNTStick(EntityType<? extends ThrownTNTStick> type, Level level) {
		super(type, level);
		this.noPhysics = false;
	}

	public ThrownTNTStick(Level world, LivingEntity shooter) {
		super(RegistryEntities.THROWN_TNT_STICK.get(), world);
		this.setOwner(shooter);
		this.noPhysics = false;


		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
			// Set initial direction based on shooter's facing
			this.stuckDirection = Direction.fromYRot(shooter.getYRot());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public ThrownTNTStick(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		this.entityData.set(TNT_LIT_TIMER, -1);
		this.entityData.set(TNT_SPIN_ROTATION, 12F);
		this.entityData.set(TNT_GROUNDED_zROTATION, 0F);

	}


	 /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
  /*[HELP]*/
	public boolean isGrounded() {
		 return inGround;
	 }

	public int getLitTime() {
		return this.entityData.get(TNT_LIT_TIMER);
	}
	public Direction getStuckDirection() {
		return stuckDirection != null ? stuckDirection : Direction.NORTH;
	}
	public float getSpinRotation() {
		return this.entityData.get(TNT_SPIN_ROTATION);
	}
	public float getGroundedzRotation() {
		return this.entityData.get(TNT_GROUNDED_zROTATION);
	}

	public void setLitTime(int time) {
		this.entityData.set(TNT_LIT_TIMER, time);
	}
	public void setSpinRotation(float rotation) {
		this.entityData.set(TNT_SPIN_ROTATION, rotation);
	}
	public void setGroundedzRotation(float rotation) {
		this.entityData.set(TNT_GROUNDED_zROTATION, rotation);
	}


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC Entity OVERRIDES]*/
	@Override
	public ItemStack getDefaultPickupItem() { return new ItemStack(RegistryBIBI.TNT_STICK.get());}
	@Override
	public boolean canBeCollidedWith() { return true; }
	@Override
	public boolean isAttackable() { return true; }
	@Override
	public boolean isInvulnerableTo(DamageSource source) { return true; }
	@Override
	public boolean displayFireAnimation() { return false; }
	@Override
	public boolean isPickable() { return true; }
	@Override
	public float getPickRadius() { return 0.2F; }
	@Override
	public boolean shouldRenderAtSqrDistance(double distance) { return true; }
	@Override
	protected void updateRotation() {/*VOIDED vanilla abstract arrow rot*/}
	@Override
	public void doPostHurtEffects(LivingEntity target) {/*VOIDED*/}

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
  /*[DATA SYNC]*/
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TNT_LIT_TIMER, -1); // Default to -1 (not lit)
		builder.define(TNT_SPIN_ROTATION, 12F);
		builder.define(TNT_GROUNDED_zROTATION, 0F);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("tnt_lit_timer", this.getLitTime());
		compound.putFloat("tnt_spin_rotation", this.getSpinRotation());
		compound.putFloat("tnt_grounded_zrotation", this.getGroundedzRotation());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("tnt_lit_timer")) {
			this.setLitTime(compound.getInt("tnt_lit_timer"));
		}
		if (compound.contains("tnt_spin_rotation")) {
			this.setSpinRotation(compound.getFloat("tnt_spin_rotation"));
		}
		if (compound.contains("tnt_grounded_zrotation")) {
			this.setGroundedzRotation(compound.getFloat("tnt_grounded_zrotation"));
		}
	}

	 /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
  /*[HITBOX]*/
	@Override
	public void setPos(double x, double y, double z) {
		super.setPos(x, y, z);
		updateHitbox();
	}

	private void updateHitbox() {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();

		if (this.inGround) {
			Direction dir = this.getStuckDirection();

			if (dir == Direction.NORTH) {
				this.setBoundingBox(new AABB(
					x - 0.11420D,    // Left
					y - 0.16D,    // Bottom
					z - 0.420D,   // Front/fuse
					x + 0.11420D,    // Right
					y + 0.072D, // Top (flat on ground)
					z + 0.25D // Back
				));
			} else if (dir == Direction.SOUTH) {
				this.setBoundingBox(new AABB(
					x - 0.11420D,    // Left
					y - 0.16D,    // Bottom
					z - 0.25D,   // Front
					x + 0.11420D,    // Right
					y + 0.072D, // Top
					z + 0.420D // Back/fuse
				));
			} else if (dir == Direction.EAST) {
				this.setBoundingBox(new AABB(
					x - 0.25D,   // Front
					y - 0.16D,    // Bottom
					z - 0.11420D,    // Left
					x + 0.420D,   // Back/fuse
					y + 0.072D, // Top
					z + 0.11420D // Right
				));
			} else if (dir == Direction.WEST) {
				this.setBoundingBox(new AABB(
					x - 0.420D,   // Front/fuse
					y - 0.16D,    // Bottom
					z - 0.11420D,    // Left
					x + 0.25D,   // Back
					y + 0.072D, // Top
					z + 0.11420D // Right
				));
			}
		} else {
			// In flight
			this.setBoundingBox(new AABB(
				x - 0.25D,    // Left
				y - 0.16D,    // Bottom
				z - 0.25D,   // Back
				x + 0.25D,    // Right
				y + 0.5D, // Top (flat on ground)
				z + 0.25D    // Front
			));
		}
	}

	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
  /*--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--*/
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!this.level().isClientSide() && !this.isRemoved()) {
			// Check if damage is from explosion
			if (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.FIREBALL) ||
				source.is(DamageTypes.FIREWORKS) || source.is(DamageTypes.PLAYER_EXPLOSION)) {
				
				// Apply push force away from explosion
				Vec3 thisPos = this.position();
				Vec3 sourcePos;
				double distance;
				
				// Get source position - either from the entity or from the damage source
				if (source.getEntity() != null) {
					sourcePos = source.getEntity().position();
				} else if (source.getSourcePosition() != null) {
					sourcePos = source.getSourcePosition();
				} else {
					// If no source position available, use a position slightly below the TNT stick
					sourcePos = thisPos.subtract(0, 2.0, 0);
				}
				
				// Calculate distance and direction
				distance = thisPos.distanceTo(sourcePos);
				Vec3 pushDirection = thisPos.subtract(sourcePos).normalize();
				
				// Calculate push strength based on distance from explosion
				double maxDistance = 6; // Maximum effective distance in blocks
				double maxStrength = 1.5; // At distance 0: strength = 1.5 (maximum push)
				double minStrength = 0.2; // At distance 6+ blocks: strength = 0.2 (minimum push)
				double pushStrength = Math.max(minStrength, maxStrength - (distance / maxDistance));
				
				// Apply the push force
				this.setDeltaMovement(pushDirection.scale(pushStrength));
				this.hasImpulse = true;
				
				// If the TNT stick is in the ground, unstick it
				if (this.inGround) {
					this.inGround = false;
				}

				// Only light the TNT if it's not already lit
				if (this.getLitTime() <= 0) {
					// Set a fuse time (80 ticks = 4 seconds)
					this.setLitTime(80);

					// Play the TNT ignition sound
					this.playSound(RegistrySounds.TNT_FUSE.get(), 1.0F, 1.0F);
				}
			}
		}
		return false;
	}


	 /*--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X-*/
	/*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
  /*[tick]*/
	@Override
	public void tick() {
		super.tick();

		// Update spin rotation in flight
		if (!this.inGround) {
			// Get the current velocity magnitude
			Vec3 motion = this.getDeltaMovement();
			float velocity = (float) motion.length();

			// Base rotation speed - scales with initial pull ratio
			float baseRotationSpeed = 12F;

			// Velocity factor - tnt spins faster when moving faster
			// We use a minimum factor to prevent it from stopping spinning completely while still in air
			float velocityFactor = Math.max(0.5F, Math.min(1.0F, velocity * 2.420F));

			// Calculate the final rotation speed
			float rotationSpeed = baseRotationSpeed * velocityFactor;

			// Update the spin rotation
			this.setSpinRotation((this.getSpinRotation() + rotationSpeed) % 360F);
		}

		// If the blocks we're stuck in changes, unstick
		if (this.inGround && stuckPos != null) {
			if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
				this.inGround = false;
				this.hasImpulse = true;
				this.setDeltaMovement(Vec3.ZERO);
			}
		}

		if (!this.level().isClientSide()) {
			// Handle countdown
			int litTime = this.getLitTime();

			if (litTime > 0) {
				if (this.isInWaterRainOrBubble()) {
					this.setLitTime(-1);
					this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);

				} else {
					this.setLitTime(litTime - 1);
				}

				if (litTime % 40 == 0) {
					// Play sizzle sound periodically
					float tone = 1.69F + 0.31F * (1F - litTime / 100F);
					this.playSound(RegistrySounds.TNT_SIZZLE.get(), 0.8F, tone);
				}
			}
		}
	}


	/*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
  /*-- ____________________________________________________________________________________________--*/
	@Override
	public void baseTick() {
		super.baseTick();

		if (!this.level().isClientSide()) {
			BlockState state = this.level().getBlockState(blockPosition());

			if (state.is(BlockTags.FIRE) || state.is(Blocks.SOUL_FIRE) || state.is(Blocks.MAGMA_BLOCK) || this.isInLava() || getLitTime() == 0) {
				this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, false, Level.ExplosionInteraction.TNT);
				this.discard();
			}
		}
	}


	 /*-- ____________________________________________________________________________________________--*/
	/*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
  /*[interact]*/
	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.level().isClientSide()) {
			ItemStack handItem = player.getItemInHand(hand);
			ItemStack tntStickItem = new ItemStack(RegistryBIBI.TNT_STICK.get());
			
			// Check if player is holding TNT sticks with count < 4
			if (handItem.getItem() == RegistryBIBI.TNT_STICK.get() && handItem.getCount() < 4) {
				// Add to existing stack
				handItem.grow(1);
				this.discard();
				return InteractionResult.SUCCESS;
			} 
			// Check if hand is empty
			else if (handItem.isEmpty()) {
				// Put in empty hand
				player.setItemInHand(hand, tntStickItem);
				this.discard();
				return InteractionResult.SUCCESS;
			} 
			// Try to add to inventory if hands are full or stack is at max
			else {
				// Try to add to inventory
				boolean added = player.getInventory().add(tntStickItem);
				if (added) {
					this.discard();
					return InteractionResult.SUCCESS;
				}
				// If inventory is full, leave on ground (do nothing)
			}
		}
		return InteractionResult.PASS;
	}

	 /*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
	/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
  /*[on Hit Entity]*/
	@Override
	public void onHitEntity(EntityHitResult result) {
		if (this.level().isClientSide()) return;

		Entity hitEntity = result.getEntity();

		// Simple bounce with reduced velocity
		Vec3 currentPos = this.position();
		Vec3 entityPos = hitEntity.position();
		Vec3 motion = this.getDeltaMovement();

		// Calculate bounce direction away from entity
		Vec3 bounceDir = currentPos.subtract(entityPos).normalize();

		this.setDeltaMovement(bounceDir.x * motion.x * 0.5, motion.y * 0.2, bounceDir.z * motion.z * 0.5);
		this.hasImpulse = true;

		// Play a wood/tnt bouncing sound
		float tone = Mth.randomBetween(this.random, 0.8F, 1.2F);
		this.playSound(SoundEvents.BAMBOO_HIT, 0.6F, tone);
	}

	 /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	/*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
  /*[on Hit Block]*/
	@Override
	public void onHitBlock(BlockHitResult result) {

		Direction hitFace = result.getDirection();
		Vec3 motion = this.getDeltaMovement();
		float motionLength = (float) motion.length();

		stuckPos = result.getBlockPos();
		stuckBlock = level().getBlockState(stuckPos).getBlock();

		boolean forceRest = false;

		float tone = Mth.randomBetween(this.random, 0.3F, 0.420F);

		if (hitFace == Direction.UP) {
			// Use a smooth transition between bouncing and resting
			float tooLowForBounce = 0.169F;

			// Initialize bounce counter if it doesn't exist
			if (!this.getPersistentData().contains("lowBounceCount")) {
				this.getPersistentData().putInt("lowBounceCount", 0);
			}

			int bounceCount = this.getPersistentData().getInt("lowBounceCount");
			bounceCount++;
			this.getPersistentData().putInt("lowBounceCount", bounceCount);

			if (motionLength > tooLowForBounce && bounceCount <= 3) {
				// Bounce!
				Vec3 bounceVec = new Vec3(
					motion.x * Mth.nextFloat(this.random, 0.369F * motionLength, 0.420F * motionLength), // X horizontal momentum retention
					-motion.y * Mth.nextFloat(this.random, 0.420F * motionLength, 0.69F * motionLength),  // Bounce with negative motion
					motion.z * Mth.nextFloat(this.random, 0.369F * motionLength, 0.420F * motionLength)  // Z horizontal momentum retention
				);

				this.setDeltaMovement(bounceVec);
				this.inGround = false;
				this.hasImpulse = true;

				this.playSound(SoundEvents.BAMBOO_HIT, Math.min(0.2F * motionLength, 0.6F), tone);

			} else {
				// Go to resting position

				float randomGroundedzRotation;

				// Set the z roll based on motion
				if (Math.abs(motion.x) > Math.abs(motion.z)) {
					this.stuckDirection = motion.x > 0 ? Direction.EAST : Direction.WEST;
					randomGroundedzRotation =
						this.random.nextInt(5) == 0 ? -170F :
							this.random.nextInt(5) == 1 ? -175.8F :
								this.random.nextInt(5) == 2 ? 180F :
									this.random.nextInt(5) == 3 ? 184.20F : 190F;

				} else {
					this.stuckDirection = motion.z > 0 ? Direction.SOUTH : Direction.NORTH;
					randomGroundedzRotation =
						this.random.nextInt(5) == 0 ? -10F :
							this.random.nextInt(5) == 1 ? -5.2F :
								this.random.nextInt(5) == 2 ? -4.6F :
									this.random.nextInt(5) == 3 ? 0F : 4.8F;
				}

				this.inGround = true;
				this.getPersistentData().putInt("lowBounceCount", 0);
				this.setGroundedzRotation(randomGroundedzRotation);
				this.setYRot(stuckDirection.toYRot());
				this.setDeltaMovement(Vec3.ZERO);

				this.playSound(SoundEvents.BAMBOO_PLACE, 0.2F, tone);
			}

		} else if (hitFace != Direction.DOWN) {

			// reflection with dampening based on speed
			float dampening = 0.420F + motionLength * 0.1420F;
			dampening = Math.min(dampening, 0.69420F);

			Vec3 normal = Vec3.atLowerCornerOf(hitFace.getNormal());
			Vec3 reflected = motion.subtract(normal.scale(2 * motion.dot(normal))).scale(dampening);

			this.setDeltaMovement(reflected);
			this.inGround = false;
			this.hasImpulse = true;

			this.playSound(SoundEvents.BAMBOO_HIT, Math.min(0.2F * motionLength, 0.6F), tone);
		}

		// Always update the hitbox at the end of collision processing
		updateHitbox();
	}
}
