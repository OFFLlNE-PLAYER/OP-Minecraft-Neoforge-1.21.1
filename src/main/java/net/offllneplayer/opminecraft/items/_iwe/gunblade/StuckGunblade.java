package net.offllneplayer.opminecraft.items._iwe.gunblade;

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
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.blocks._block.crying.essence.method.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.items._iwe.sw0rd.PopSwordItem_Method;
import net.offllneplayer.opminecraft.items._iwe.sw0rd.Stuck_Sword_OnClick_Method;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.UTIL.Projectile.OP_ProjectileonHitBlockUtil;
import net.offllneplayer.opminecraft.UTIL.Projectile.OP_ProjectileonHitEntityUtil;

import java.util.Map;


public class StuckGunblade extends AbstractArrow {

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
  /*[DATA]*/
	private static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Byte> STUCK_FACE = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<BlockPos> STUCK_POS = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.FLOAT);


	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private GunbladeMaterial material;
	private ItemStack bladeStack;
	private float dmg;


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDERS]*/
	public StuckGunblade(EntityType<? extends StuckGunblade> type, Level level) {
		super(type, level);
		this.material = getMaterialFromName();
		this.bladeStack = new ItemStack(RegistryBIBI.TITAN_GUNBLADE.get());
	}

	public StuckGunblade(Level world, LivingEntity shooter) {
		super(RegistryEntities.STUCK_GUNBLADE.get(), world);

		this.setOwner(shooter);
		this.material = GunbladeMaterial.TITAN;
		this.entityData.set(MATERIAL_NAME, "TITAN");
		this.bladeStack = new ItemStack(RegistryBIBI.TITAN_GUNBLADE.get());

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public StuckGunblade(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		if (stack.getItem() instanceof GunbladeItem gunbladeItem) {
			this.material = gunbladeItem.getMaterial();
			this.entityData.set(MATERIAL_NAME, this.material.name());
			this.bladeStack = gunbladeItem.getMaterial().getRegisteredItem().getDefaultInstance();
		} else {
			// Fallback to default material if the items is not a SwordItem
			this.material = GunbladeMaterial.TITAN;
			this.entityData.set(MATERIAL_NAME, "TITAN");
			this.bladeStack = RegistryBIBI.TITAN_GUNBLADE.get().getDefaultInstance();
		}

		CompoundTag data = this.getPersistentData();
		data.putString("N4M3", stack.getHoverName().getString());
		data.putInt("D4M4G3", stack.getDamageValue());

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

	private GunbladeMaterial getMaterialFromName() {
		try {
			return GunbladeMaterial.valueOf(getMaterialName());
		} catch (IllegalArgumentException e) {
			return GunbladeMaterial.NETHERITE;
		}
	}

	public Direction getStuckFace() { return Direction.from3DDataValue(this.entityData.get(STUCK_FACE));}
	public BlockPos getStuckPos() { return this.entityData.get(STUCK_POS);}
	public float getRenderingRotation() { return this.entityData.get(ROTATION);}

	public void setStuckFace(Direction face) { this.entityData.set(STUCK_FACE, (byte) face.get3DDataValue());}
	public void setStuckPos(BlockPos pos) { this.entityData.set(STUCK_POS, pos);}
	public void setRenderingRotation(float rotation) {this.entityData.set(ROTATION, rotation);}


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
  /*[DATA SYNC]*/
	 @Override
	 protected void defineSynchedData(SynchedEntityData.Builder builder) {
		 super.defineSynchedData(builder);
		 builder.define(MATERIAL_NAME, "TITAN");
		 builder.define(STUCK_FACE, (byte) Direction.UP.get3DDataValue());
		 builder.define(STUCK_POS, BlockPos.ZERO);
		 builder.define(ROTATION, 0.0F);
	 }

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("material_name", this.entityData.get(MATERIAL_NAME));
		compound.putByte("stuck_face", this.entityData.get(STUCK_FACE));
		BlockPos pos = this.entityData.get(STUCK_POS);
		if (pos != null && pos != BlockPos.ZERO) {
			compound.putInt("stuck_pos_x", pos.getX());
			compound.putInt("stuck_pos_y", pos.getY());
			compound.putInt("stuck_pos_z", pos.getZ());
		}
		compound.putFloat("rotation", this.entityData.get(ROTATION));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("material_name")) {
			String materialName = compound.getString("material_name");
			this.entityData.set(MATERIAL_NAME, materialName);
			this.material = getMaterialFromName();
			this.bladeStack = getMaterialFromName().getRegisteredItem().getDefaultInstance();
		}
		if (compound.contains("stuck_face")) {
			this.entityData.set(STUCK_FACE, compound.getByte("stuck_face"));
		}
		if (compound.contains("stuck_pos_x") && compound.contains("stuck_pos_y") && compound.contains("stuck_pos_z")) {
			BlockPos pos = new BlockPos(
				compound.getInt("stuck_pos_x"),
				compound.getInt("stuck_pos_y"),
				compound.getInt("stuck_pos_z")
			);
			this.entityData.set(STUCK_POS, pos);
		}
		if (compound.contains("rotation")) {
			this.entityData.set(ROTATION, compound.getFloat("rotation"));
		}
	}


	 /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC Entity OVERRIDES]*/
	@Override
	public ItemStack getDefaultPickupItem() {
		Item materialItem = this.getMaterialFromName().getRegisteredItem();
		return new ItemStack(materialItem != null ? materialItem : RegistryBIBI.TITAN_GUNBLADE.get());
	}

	@Override
	public boolean canBeCollidedWith() {return true;}
	@Override
	public boolean isAttackable() {return true;}
	@Override
	public boolean isInvulnerableTo(DamageSource source) {return true;}
	@Override
	public boolean isPickable() {return true;}
	@Override
	public float getPickRadius() {return 0.2F;}
	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {return true;}
	@Override
	public boolean displayFireAnimation() {return false;}
	@Override
	protected void updateRotation() {/*VOIDED vanilla abstract arrow rot*/}
	@Override
	public void tickDespawn() {/*VOIDED despawning due to tick time*/}
	@Override
	public void checkDespawn() {/*VOIDED despawning due to player distance*/}
	@Override
	public void doPostHurtEffects(LivingEntity target) {/*VOIDED Discard entity on hit*/}

	@Override
	public boolean shouldBeSaved() { return true; }


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
  /*[HITBOX]*/
	@Override
	public void setPos(double x, double y, double z) {
		super.setPos(x, y, z);
		float shortHalf = 0.0420F;
		float longHalf = 0.1420F;
		float height = 0.75420F;
		float offset = 0.32F;
		Direction stuckFace = this.getStuckFace();

		if (stuckFace == Direction.UP) {
			double cos = Math.cos(Math.toRadians(this.getRenderingRotation()));
			double sin = Math.sin(Math.toRadians(this.getRenderingRotation()));
			double dx = longHalf * Math.abs(cos) + shortHalf * Math.abs(sin);
			double dz = longHalf * Math.abs(sin) + shortHalf * Math.abs(cos);

			// Ensure minimum dimensions
			dx = Math.max(dx, 0.0420F);  // Minimum width
			dz = Math.max(dz, 0.0420F);  // Minimum depth

			this.setBoundingBox(new AABB(
				x - dx,
				y - height, //down
				z - dz,
				x + dx,
				y + height + offset, // up
				z + dz
			));
		} else if (stuckFace == Direction.DOWN) {
			double cos = Math.cos(Math.toRadians(this.getRenderingRotation()));
			double sin = Math.sin(Math.toRadians(this.getRenderingRotation()));
			double dx = longHalf * Math.abs(cos) + shortHalf * Math.abs(sin);
			double dz = longHalf * Math.abs(sin) + shortHalf * Math.abs(cos);

			// Ensure minimum dimensions
			dx = Math.max(dx, 0.0420F);  // Minimum width
			dz = Math.max(dz, 0.0420F);  // Minimum depth

			this.setBoundingBox(new AABB(
				x - dx,
				y - height - offset, // down
				z - dz,
				x + dx,
				y + height, // up
				z + dz
			));
		} else if (stuckFace == Direction.NORTH) {
			this.setBoundingBox(new AABB(
				x - longHalf,
				y - shortHalf,
				z - height - offset, // out
				x + longHalf,
				y + shortHalf,
				z + height // in
			));
		} else if (stuckFace == Direction.SOUTH) {
			this.setBoundingBox(new AABB(
				x - longHalf,
				y - shortHalf,
				z - height, // in
				x + longHalf,
				y + shortHalf,
				z + height + offset // out
			));
		} else if (stuckFace == Direction.EAST) {
			this.setBoundingBox(new AABB(
				x - height, // in
				y - shortHalf,
				z - longHalf,
				x + height + offset, // out
				y + shortHalf,
				z + longHalf
			));
		} else if (stuckFace == Direction.WEST) {
			this.setBoundingBox(new AABB(
				x - height - offset, // out
				y - shortHalf,
				z - longHalf,
				x + height, // in
				y + shortHalf,
				z + longHalf
			));
		}
	}


	 /*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
	/*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
  /*[tick]*/
	@Override
	public void tick() {
		super.tick();

		BlockPos stuckPos = this.getStuckPos();
		BlockState stuckBlock = this.level().getBlockState(stuckPos);

		if (this.inGround && stuckPos != null && (stuckBlock.is(OP_TagKeyUtil.Blocks.SWORD_NO_STICK))) {
			this.inGround = false;
			this.setDeltaMovement(Vec3.ZERO);
		}

		if (!this.inGround) {
			this.hasImpulse = true;
			this.setStuckPos(this.blockPosition());
			this.setStuckFace(Direction.UP);

			float currentRotation = this.getRenderingRotation();
			float newRotation = (random.nextBoolean() ? currentRotation + 10F : currentRotation - 10F) % 360F;
			if (newRotation < 0) newRotation += 360F;
			this.setRenderingRotation(newRotation);
		}
		this.setPos(this.getX(), this.getY(), this.getZ());
	}


	 /*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
	/*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
  /*[interact]*/
	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		return Stuck_Sword_OnClick_Method.execute(this.level(), this, player, hand);
	}


	 /*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
	/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
  /*[on Hit Entity]*/
	@Override
	public void onHitEntity(EntityHitResult result) {
		Level level = this.level();

		if (!level.isClientSide()) {
			DamageSource bladeDMG = level().damageSources().source(RegistryDamageTypes.GUNBLADE, this, this.getOwner());
			Entity hitEntity = result.getEntity();

			if (hitEntity instanceof LivingEntity living) {
				if (this.level() instanceof ServerLevel serverLevel) {
					OP_NBTUtil.WeaponData wd = OP_NBTUtil.readItemStacktoClass(this.getPersistentData(), level);
					Map<Enchantment, Integer> enchs = wd.enchants();

					OP_ProjectileonHitEntityUtil.processUnbreaking(this, enchs, random);
					OP_ProjectileonHitEntityUtil.applyFireAspect(living, enchs);
					OP_ProjectileonHitEntityUtil.applyKnockback(this, living, enchs);
					OP_ProjectileonHitEntityUtil.applyCleaving(this, enchs, random);

					OP_NBTUtil.enchantWeaponDataToItemstack(this.bladeStack, this.getPersistentData(), level);
					EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, bladeDMG, this.bladeStack);

					float enchantDmg = OP_ProjectileonHitEntityUtil.calculateDamageBonus(living, enchs);
					float damage = dmg + enchantDmg;
					living.hurt(bladeDMG, damage);

					if (material == GunbladeMaterial.CRYING) ApplyCrying1_Method.execute(result.getEntity());
				}
			} else { // non-living entities
				OP_ProjectileonHitEntityUtil.miscEntityHit(this, hitEntity, level, random);
			}
		}
		level.broadcastEntityEvent(this, (byte) 3);
	}


	 /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	/*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
  /*[on Hit Block]*/
	 @Override
	 public void onHitBlock(BlockHitResult result) {

		 if (PopSwordItem_Method.execute(result, level(), this)) {
			 return;
		 }

		 BlockPos hitPos = result.getBlockPos();
		 this.setStuckPos(hitPos);
		 this.setStuckFace(result.getDirection());

		 super.onHitBlock(result);

		 if (!level().isClientSide()) {
			 // Use the utility SHAREDMETHODS for button interaction
			 OP_ProjectileonHitBlockUtil.handleButtonInteraction(result, level(), this);

			 // Spawn blocks particles at the hit location
			 ((ServerLevel)level()).sendParticles(
				 new BlockParticleOption(ParticleTypes.BLOCK, this.level().getBlockState(hitPos)),
				 result.getLocation().x,
				 result.getLocation().y,
				 result.getLocation().z,
				 10, // particle count
				 0.1D, // spread X
				 0.1D, // spread Y
				 0.1D, // spread Z
				 0.05D // speed
			 );

			 float tone = Mth.randomBetween(this.random, 1.3F, 1.420F);
			 this.playSound(RegistrySounds.BLADE_STICK.get(), 0.2420F, tone);
			 this.level().broadcastEntityEvent(this, (byte) 3);
		 }
	 }
}
