package net.offllneplayer.opminecraft.iwe.hatchet;


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
import net.offllneplayer.opminecraft.block.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import java.util.Map;

public class ThrownHatchet extends AbstractArrow {

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
  /*[DATA]*/
	private static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(ThrownHatchet.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Byte> STUCK_FACE = SynchedEntityData.defineId(ThrownHatchet.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<BlockPos> STUCK_POS = SynchedEntityData.defineId(ThrownHatchet.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(ThrownHatchet.class, EntityDataSerializers.FLOAT);


	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*----------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private HatchetMaterial material;
	private DamageSource hatchetDMG = level().damageSources().source(RegistryDamageTypes.HATCHET, this, this.getOwner());
	private ItemStack hatchetStack;

	private float pullRatio = 1F;
	private float rotation;

	 /*---------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDERS]*/
	public ThrownHatchet(EntityType<? extends ThrownHatchet> type, Level level) {
		super(type, level);
		// Default to golden material for compatibility
		this.material = HatchetMaterial.GOLDEN;
		this.hatchetStack = new ItemStack(RegistryBIBI.GOLDEN_HATCHET.get());
	}

	public ThrownHatchet(Level world, LivingEntity shooter) {
		super(RegistryEntities.THROWN_HATCHET.get(), world);
		this.setOwner(shooter);
		this.material = HatchetMaterial.GOLDEN;
		this.entityData.set(MATERIAL_NAME, "GOLDEN");
		this.hatchetStack = new ItemStack(RegistryBIBI.GOLDEN_HATCHET.get());

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public ThrownHatchet(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		if (stack.getItem() instanceof HatchetItem hatchetItem) {
			this.material = hatchetItem.getMaterial();
			this.entityData.set(MATERIAL_NAME, this.material.name());
			this.hatchetStack = hatchetItem.getMaterial().getRegisteredItem().getDefaultInstance();
		} else {
			// Fallback to default material if the item is not a HatchetItem
			this.material = HatchetMaterial.GOLDEN;
			this.entityData.set(MATERIAL_NAME, "GOLDEN");
			this.hatchetStack = RegistryBIBI.GOLDEN_HATCHET.get().getDefaultInstance();
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


	 /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
  /*[HELP]*/
	public boolean isGrounded() {
		return inGround;
	}

	public void setPullRatio(float pullRatio) {
		this.pullRatio = pullRatio;
	}

	public String getMaterialName() {return this.entityData.get(MATERIAL_NAME);}

	private HatchetMaterial getMaterialFromName() {
		try {
			return HatchetMaterial.valueOf(getMaterialName());
		} catch (IllegalArgumentException e) {
			return HatchetMaterial.GOLDEN;
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
		builder.define(MATERIAL_NAME, "GOLDEN");
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
			this.hatchetStack = getMaterialFromName().getRegisteredItem().getDefaultInstance();
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
		return this.getMaterialFromName().getRegisteredItem().getDefaultInstance();
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

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
  /*[HITBOX]*/
	@Override
	public void setPos(double x, double y, double z) {
		super.setPos(x, y, z);

		float shortHalf = 0.05F;
		float longHalf = 0.420F;
		float height = 0.420F;

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

			rotation = (rotation - pullRatio * 20F) % 360F;
			if (rotation < 0) rotation += 360F;
			this.setRenderingRotation(rotation);
		}
		this.setPos(this.getX(), this.getY(), this.getZ());
	}


	 /*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
	/*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
  /*[interact]*/
	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.level().isClientSide()) {
			if (player.getItemInHand(hand).isEmpty()) {

				float tone = Mth.randomBetween(this.random, 1.35F, 1.5F);
				this.playSound(RegistrySounds.BLADE_SLASH.get(), 0.6F, tone);

				// Create item stack based on material
				ItemStack blade = this.getDefaultPickupItem().copy();
				CompoundTag nbt = this.getPersistentData();
				OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, this.level());

				player.setItemInHand(hand, blade);
				this.discard();

				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	 /*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
	/*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
  /*[on Hit Entity]*/
	@Override
	public void onHitEntity(EntityHitResult result) {
		Level level = this.level();

		if (!level.isClientSide()) {
			Entity hitEntity = result.getEntity();

			if (hitEntity instanceof LivingEntity living) {
				if (this.level() instanceof ServerLevel serverLevel) {
					OP_NBTUtil.WeaponData wd = OP_NBTUtil.readItemStacktoClass(this.getPersistentData(), level);
					Map<Enchantment, Integer> enchs = wd.enchants();

					HatchetonHitEntity.processUnbreaking(this, enchs, random);
					HatchetonHitEntity.applyFireAspect(living, enchs);
					HatchetonHitEntity.applyKnockback(this, living, enchs);
					HatchetonHitEntity.applyCleaving(this, enchs, random);

					OP_NBTUtil.enchantWeaponDataToItemstack(this.hatchetStack, this.getPersistentData(), level);
					EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, hatchetDMG, this.hatchetStack);

					float enchantDmg = HatchetonHitEntity.calculateDamageBonus(living, enchs);
					float dmg = this.material.getAttackDamage() + enchantDmg;

					living.hurt(hatchetDMG, dmg);

					if (material == HatchetMaterial.CRYING) {
						ApplyCrying1_Method.execute(result.getEntity());
					}

				}
			} else { // non-living entities
				HatchetonHitEntity.miscEntityHit(this, hitEntity, level, random);
			}
		}
		level.broadcastEntityEvent(this, (byte) 3);
	}


	 /*--x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---*/
	/*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
  /*[on Hit Block]*/
	@Override
	public void onHitBlock(BlockHitResult result) {
		BlockPos hitPos = result.getBlockPos();

		this.setStuckPos(hitPos);
		this.setStuckFace(result.getDirection());

		super.onHitBlock(result);

		if (!level().isClientSide()) {

			// Use the utility SHAREDMETHODS for button interaction
			HatchetonHitBlock.handleButtonInteraction(result, level(), this);

			// Spawn block particles at the hit location
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
