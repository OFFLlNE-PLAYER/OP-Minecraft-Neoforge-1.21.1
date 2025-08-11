package net.offllneplayer.opminecraft.items._iwe.SMBSuperFan;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.UTIL.Projectile.OP_ProjectileonHitBlockUtil;
import net.offllneplayer.opminecraft.UTIL.Projectile.OP_ProjectileonHitEntityUtil;

import java.util.List;
import java.util.Map;


public class ThrownSMBSuperFan extends AbstractArrow {


	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*[DATA]*/
	private static final EntityDataAccessor<Byte> STUCK_FACE = SynchedEntityData.defineId(ThrownSMBSuperFan.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<BlockPos> STUCK_POS = SynchedEntityData.defineId(ThrownSMBSuperFan.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(ThrownSMBSuperFan.class, EntityDataSerializers.FLOAT);


	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	 private DamageSource fanDMG = level().damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDERS]*/
	public ThrownSMBSuperFan(EntityType<? extends ThrownSMBSuperFan> type, Level level) {
		super(type, level);
	}

	public ThrownSMBSuperFan(Level world, LivingEntity shooter) {
		super(RegistryEntities.THROWN_SMB_SUPER_FAN.get(), world);
		this.setOwner(shooter);

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public ThrownSMBSuperFan(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

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
		 builder.define(STUCK_FACE, (byte) Direction.UP.get3DDataValue());
		 builder.define(STUCK_POS, BlockPos.ZERO);
		 builder.define(ROTATION, 0.0F);
	 }

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
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
	public ItemStack getDefaultPickupItem() {return new ItemStack(RegistryBIBI.SMB_SUPER_FAN.get());}
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

		float shortHalf = 0.1F;
		float longHalf = 0.74F;
		float height = 0.74F;

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
	private int customTickCounter = 0;

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

			float currentRotation = this.getRenderingRotation();
			float newRotation = (currentRotation - 15F) % 360F;
			if (newRotation < 0) newRotation += 360F;
			this.setRenderingRotation(newRotation);
		}


		if (!this.level().isClientSide()) {
			customTickCounter++;

			// Every 20 ticks (1 second) play idle sound.
			if (customTickCounter % 20 == 0) {
				float vol = Mth.randomBetween(this.random, 0.2F, 0.420F);
				float tone = Mth.randomBetween(this.random, 0.9F, 1.0F);
				this.playSound(RegistrySounds.SMB_SUPER_FAN_IDLE.get(), vol, tone);
			}

			// Every 10 ticks (0.5 seconds) process nearby entities.
			if (customTickCounter % 10 == 0) {
				Level level = this.getCommandSenderWorld();
				AABB boundingBox = this.getBoundingBox().inflate(0.01420D);
				List<Entity> entities = level.getEntities(this, boundingBox, entity -> (entity instanceof LivingEntity) || (entity instanceof ItemEntity) || (entity instanceof ExperienceOrb));

				for (Entity entity : entities) {

					if (entity instanceof ItemEntity) {// Discard and play hit sound
						entity.discard();
						float vol = Mth.randomBetween(this.random, 0.69F, 0.8F);
						float tone = Mth.randomBetween(this.random, 1.0F, 1.3F);
						this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);

					} else if (entity instanceof ExperienceOrb) {
						OP_NBTUtil.WeaponData wd = OP_NBTUtil.readItemStacktoClass(this.getPersistentData(), level);

						if (wd.enchants().getOrDefault(Enchantments.MENDING, 0) > 0) {
							this.getPersistentData().putInt("D4M4G3", wd.dmgValue() - 1);
						}

						entity.discard();
						float vol = Mth.randomBetween(this.random, 0.6F, 1F);
						float tone = Mth.randomBetween(this.random, 1.0F, 1.3F);
						this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);

					} else if (entity instanceof LivingEntity) {// Hurt living entities and play hit sound
						DamageSource fanDamage = level.damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());
						entity.hurt(fanDamage, 2.0F);

						float vol = Mth.randomBetween(this.random, 0.8F, 1.05F);
						float tone = Mth.randomBetween(this.random, 0.8F, 1.1F);
						this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);
					}
				}
			}
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

				float tone = Mth.randomBetween(this.random, 0.85F, 1.2F);
				this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

				ItemStack fan = new ItemStack(RegistryBIBI.SMB_SUPER_FAN.get());
				CompoundTag nbt = this.getPersistentData();
				OP_NBTUtil.enchantWeaponDataToItemstack(fan, nbt, this.level());

				player.setItemInHand(hand, fan);
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
		Entity hitEntity = result.getEntity();
		Level level = hitEntity.level();
		double x = hitEntity.getX();
		double y = hitEntity.getY();
		double z = hitEntity.getZ();

		if (hitEntity instanceof LivingEntity living) {
			if (level() instanceof ServerLevel serverLevel) {
				OP_NBTUtil.WeaponData wd = OP_NBTUtil.readItemStacktoClass(this.getPersistentData(), level);
				Map<Enchantment, Integer> enchs = wd.enchants();

				float dmg = 4F;
				float enchantDmg = OP_ProjectileonHitEntityUtil.calculateDamageBonus(living, enchs);
				float damage = dmg + enchantDmg;

				living.hurt(fanDMG, damage);

				OP_ProjectileonHitEntityUtil.processUnbreaking(this, enchs, this.random);
				OP_ProjectileonHitEntityUtil.applyFireAspect(living, enchs);
				OP_ProjectileonHitEntityUtil.applyKnockback(this, living, enchs);
				OP_ProjectileonHitEntityUtil.applyCleaving(this, enchs, this.random);

				ItemStack dummyStack = new ItemStack(RegistryBIBI.SMB_SUPER_FAN.get());
				OP_NBTUtil.enchantWeaponDataToItemstack(dummyStack, this.getPersistentData(), level());
				EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, fanDMG, dummyStack);

				float tone = Mth.randomBetween(this.random, 1.2F, 1.420F);
				this.playSound(RegistrySounds.BLADE_SLASH.get(), 0.1420F, tone);

				float tone2 = Mth.randomBetween(this.random, 0.9F, 1.1F);
				this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone2);
			}
		} else {
			// Handle non-living entities utility class
			OP_ProjectileonHitEntityUtil.miscEntityHit(this, hitEntity, level, this.random);
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
			OP_ProjectileonHitBlockUtil.handleButtonInteraction(result, level(), this);

			float tone = Mth.randomBetween(this.random, 1.2F, 1.420F);
			this.playSound(RegistrySounds.BLADE_STICK.get(), 0.1420F, tone);

			float tone2 = Mth.randomBetween(this.random, 0.85F, 1.2F);
			this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone2);

			this.level().broadcastEntityEvent(this, (byte) 3);
		}
	}
}
