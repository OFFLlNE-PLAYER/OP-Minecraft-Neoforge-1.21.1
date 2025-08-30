package net.offllneplayer.opminecraft.items._iwe.beretta;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.UTIL.Projectile.OP_ProjectileonHitBlockUtil;

import java.util.Random;


public class PistolBullet extends AbstractArrow {


	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*[DATA]*/
	 private static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(PistolBullet.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(PistolBullet.class, EntityDataSerializers.FLOAT);


	  /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*[VARIABLES]*/
	private PistolMaterial material;
	DamageSource bulletDamageSource;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDERS]*/
	public PistolBullet(EntityType<? extends PistolBullet> type, Level level) {
		super(type, level);
	}

	public PistolBullet(Level world, LivingEntity shooter) {
		super(RegistryEntities.PISTOL_BULLET.get(), world);
		this.setOwner(shooter);

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public PistolBullet(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		try {
			String materialName = stack.getItem().builtInRegistryHolder().key().location().getPath().toUpperCase();
			this.entityData.set(MATERIAL_NAME, materialName);
			this.material = PistolMaterial.valueOf(materialName);
		} catch (IllegalArgumentException e) {
			// Fallback for items that don't have a matching PistolMaterial enum
			this.entityData.set(MATERIAL_NAME, "VALENTINE_BERETTA");
			this.material = PistolMaterial.VALENTINE_BERETTA;
		}

		this.bulletDamageSource = this.level().damageSources().source(this.material.getDamageType(), this, this.getOwner());


		CompoundTag data = this.getPersistentData();

		var enchants = stack.getComponents().get(DataComponents.ENCHANTMENTS);
		for (var entry : enchants.entrySet()) {
			entry.getKey().unwrapKey().ifPresent(key ->
				data.putInt(key.location().toString(), entry.getIntValue())
			);
		}
	}

	//FOR ENTITIES
	public PistolBullet(Mob mob, Level level, ItemStack stack) {
		this(level, mob); // Call the LivingEntity constructor

		try {
			String materialName = stack.getItem().builtInRegistryHolder().key().location().getPath().toUpperCase();
			this.entityData.set(MATERIAL_NAME, materialName);
			this.material = PistolMaterial.valueOf(materialName);
		} catch (IllegalArgumentException e) {
			// Fallback for items that don't have a matching PistolMaterial enum
			this.entityData.set(MATERIAL_NAME, "VALENTINE_BERETTA");
			this.material = PistolMaterial.VALENTINE_BERETTA;
		}

		this.bulletDamageSource = this.level().damageSources().source(this.material.getDamageType(), this, this.getOwner());

		CompoundTag data = this.getPersistentData();

		var enchants = stack.getComponents().get(DataComponents.ENCHANTMENTS);
		for (var entry : enchants.entrySet()) {
			entry.getKey().unwrapKey().ifPresent(key ->
					data.putInt(key.location().toString(), entry.getIntValue())
			);
		}
	}



	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
  /*[HELP]*/
	public boolean isGrounded() {
		return inGround;
	}

	public String getMaterialName() {return this.entityData.get(MATERIAL_NAME);}

	public PistolMaterial getMaterialFromName() {
		try {
			return PistolMaterial.valueOf(getMaterialName());
		} catch (IllegalArgumentException e) {
			return PistolMaterial.VALENTINE_BERETTA;
		}
	}

	public float getRenderingRotation() { return this.entityData.get(ROTATION);}
	public void setRenderingRotation(float rotation) {this.entityData.set(ROTATION, rotation);}


	  /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	 /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
   /*[DATA SYNC]*/
	 @Override
	 protected void defineSynchedData(SynchedEntityData.Builder builder) {
		 super.defineSynchedData(builder);
		 builder.define(MATERIAL_NAME, "VALENTINE_BERETTA");
		 builder.define(ROTATION, 180.0F);
	 }

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("material_name", this.entityData.get(MATERIAL_NAME));
		compound.putFloat("rotation", this.entityData.get(ROTATION));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("material_name")) {
			this.entityData.set(MATERIAL_NAME, compound.getString("material_name"));
		}
		if (compound.contains("rotation")) {
			this.entityData.set(ROTATION, compound.getFloat("rotation"));
		}
	}


	 /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC Entity OVERRIDES]*/
	 @Override
	 public ItemStack getDefaultPickupItem() { return new ItemStack(RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get()); }
	@Override
	public boolean canBeCollidedWith() {return true;}
	@Override
	public boolean isAttackable() {return true;}
	@Override
	public boolean isInvulnerableTo(DamageSource source) {return true;}
	@Override
	public boolean isPickable() {return false;}
	@Override
	public float getPickRadius() {return 0;}
	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {return super.shouldRenderAtSqrDistance(distance);}
	@Override
	public boolean displayFireAnimation() {return false;}
	@Override
	protected void updateRotation() {/*VOIDED vanilla abstract arrow rot*/}

	@Override
	public boolean shouldBeSaved() {
		// Never write this entity to chunk NBT
		return false;
	}

	@Override
	public void checkDespawn() {
		if (this.tickCount > 420) {
			this.discard();
		}
	}

	@Override
	public void tickDespawn() {
		if (this.tickCount > 420) { // 21 seconds age
			this.discard();
		}
	}


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
  /*[HITBOX]*/
	@Override
	public void setPos(double x, double y, double z) {
		super.setPos(x, y, z);

		float shortHalf = 0.01F;
		float longHalf = 0.02F;
		float height = 0.01F;

		Direction dir = Direction.fromYRot(this.getYRot());

		if (dir == Direction.NORTH || dir == Direction.SOUTH) {
			this.setBoundingBox(new AABB(x - shortHalf, y - height, z - longHalf, x + shortHalf, y + height, z + longHalf));
		} else {
			this.setBoundingBox(new AABB(x - longHalf, y - height, z - shortHalf, x + longHalf, y + height, z + shortHalf));
		}
	}


	 /*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
	/*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
  /*[tick]*/
	 @Override
	 public void tick() {
		 // Store original delta movement before super.tick() changes it
		 Vec3 originalDeltaMovement = this.getDeltaMovement();

		 super.tick();

			 if (!this.level().isClientSide) {
				 if (this.tickCount > 200) { // 10 seconds
					 this.discard();
					 return;
				 }

				 // Server: preemptively despawn if the next two chunks along motion (favored axis) are not loaded.
				 Vec3 motion = this.getDeltaMovement();
				 double dx = motion.x;
				 double dz = motion.z;
				 if (dx != 0.0D || dz != 0.0D) {
					 // Determine current chunk
					 int currChunkX = Mth.floor(this.getX()) >> 4;
					 int currChunkZ = Mth.floor(this.getZ()) >> 4;
					 // Choose favored axis: the one with the larger magnitude of movement (tie -> X)
					 double absX = Math.abs(dx);
					 double absZ = Math.abs(dz);
					 int stepX = 0;
					 int stepZ = 0;
					 if (absX >= absZ && dx != 0.0D) {
						 stepX = dx > 0.0D ? 1 : -1;
					 } else if (absZ > absX && dz != 0.0D) {
						 stepZ = dz > 0.0D ? 1 : -1;
					 }
					 int nextChunkX = currChunkX + stepX;
					 int nextChunkZ = currChunkZ + stepZ;
					 int next2ChunkX = currChunkX + (stepX * 2);
					 int next2ChunkZ = currChunkZ + (stepZ * 2);
					 if (stepX != 0 || stepZ != 0) {
						 ServerLevel sl = (ServerLevel) this.level();
						 boolean nextLoaded = sl.getChunkSource().getChunkNow(nextChunkX, nextChunkZ) != null;
						 boolean next2Loaded = sl.getChunkSource().getChunkNow(next2ChunkX, next2ChunkZ) != null;
						 if (!nextLoaded || !next2Loaded) {
							 this.discard();
							 return;
						 }
					 }
				 }

			 // If not in ground and not above build limit, adjust for reduced gravity
			 if (this.getY() <= this.level().getMaxBuildHeight()) {
				 // Get the current delta movement after super.tick() has applied gravity
				 Vec3 currentDeltaMovement = this.getDeltaMovement();

				 // Calculate how much gravity was applied (difference in Y component)
				 double gravityApplied = currentDeltaMovement.y - originalDeltaMovement.y;

				 // Counteract 97% of the applied gravity
				 double reducedGravity = gravityApplied * 0.969;

				 // Set the new delta movement with reduced gravity effect
				 this.setDeltaMovement(currentDeltaMovement.x, currentDeltaMovement.y + reducedGravity, currentDeltaMovement.z);
			 }

			 if (!this.inGround) {
				 float currentRotation = this.getRenderingRotation();
				 float newRotation = (currentRotation + 10F) % 360F;
				 if (newRotation < 0) newRotation += 360F;
				 this.setRenderingRotation(newRotation);
			 }

			 this.setPos(this.getX(), this.getY(), this.getZ());
		 }
	 }


	 /*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
	/*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
  /*[interact]*/
	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		return InteractionResult.PASS;
	}


	 /*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
	/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
  /*[on Hit Entity]*/
	 @Override
	 public void onHitEntity(EntityHitResult result) {
		 Entity hitEntity = result.getEntity();
		 Level level = hitEntity.level();
		 double x = hitEntity.getX();
		 double y = hitEntity.getY();
		 double z = hitEntity.getZ();

		 if (hitEntity instanceof LivingEntity living) {
			 if (level() instanceof ServerLevel serverLevel) {
				 living.hurt(bulletDamageSource, this.material.getAttackDamage());

				 if (!(hitEntity.getType() == EntityType.SKELETON || hitEntity.getType() == EntityType.WITHER_SKELETON || hitEntity.getType() == EntityType.STRAY)) {

					 // Play flesh rip sound for non-skeleton entities
					 float vol = Mth.randomBetween(this.random, 0.420F, 0.69F);
					 float tone = Mth.randomBetween(this.random, 0.8420F, 1.1420F);
					 int soundIndex = new Random().nextInt(2);

					 level.playSound(null, living.getX(), living.getY(), living.getZ(),
							 switch (soundIndex) {
								 case 0 -> RegistrySounds.FLESHRIP_0;
								 case 1 -> RegistrySounds.FLESHRIP_1;
								 default -> RegistrySounds.FLESHRIP_1;
							 },
							 SoundSource.PLAYERS, vol, tone);
				 }
			 }

			 level.broadcastEntityEvent(this, (byte) 3);
			 this.discard();
		 }
	 }



	 /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	/*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
  /*[on Hit Block]*/
	@Override
	public void onHitBlock(BlockHitResult result) {
		BlockPos hitPos = result.getBlockPos();

		if (!level().isClientSide()) {
			// Use the utility SHAREDMETHODS for button interaction
			OP_ProjectileonHitBlockUtil.handleButtonInteraction(result, level(), this);

			if (level().getBlockState(hitPos).is(OP_TagKeyUtil.Blocks.BULLET_FRAGILE)) {
				level().destroyBlock(hitPos, false);
				level().levelEvent(2001, hitPos, Block.getId(level().getBlockState(hitPos)));
			} else {

				this.discard();

				// Spawn blocks particles at the hit location
				((ServerLevel)level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, this.level().getBlockState(hitPos)), result.getLocation().x, result.getLocation().y, result.getLocation().z,
					10, 0.1D, 0.1D, 0.1D, 0.05D);
			}

			this.level().broadcastEntityEvent(this, (byte) 3);
		}
	}
}
