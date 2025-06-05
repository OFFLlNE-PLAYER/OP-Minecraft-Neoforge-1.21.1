package net.offllneplayer.opminecraft.iwe.pistol;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetMaterial;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitBlock;
import net.offllneplayer.opminecraft.iwe.hatchet.ThrownHatchet;


public class Bullet extends AbstractArrow {


	 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*[DATA]*/
	 private static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(Bullet.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(Bullet.class, EntityDataSerializers.FLOAT);


	  /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*[VARIABLES]*/
	private GunMaterial material;
	private float dmg;
	private ItemStack hatchetStack;

	private float pullRatio = 1F;
	private float rotation;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDERS]*/
	public Bullet(EntityType<? extends Bullet> type, Level level) {
		super(type, level);

		this.material = GunMaterial.SAMURAI_EDGE;
		this.updateGunStack();
	}

	public Bullet(Level world, LivingEntity shooter) {
		super(RegistryEntities.SE_BULLET.get(), world);
		this.setOwner(shooter);

		this.material = GunMaterial.SAMURAI_EDGE;
		this.entityData.set(MATERIAL_NAME, "SAMURAI_EDGE");
		this.updateGunStack();

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public Bullet(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		ResourceLocation regName = BuiltInRegistries.ITEM.getKey(stack.getItem());
		String regPath = regName.getPath();

		if (regPath.contains("wooden_hatchet")) {
			this.material = GunMaterial.SAMURAI_EDGE;
			this.entityData.set(MATERIAL_NAME, "SAMURAI_EDGE");
		} else {
			this.material = GunMaterial.SAMURAI_EDGE;
			this.entityData.set(MATERIAL_NAME, "SAMURAI_EDGE");
		}

		this.updateGunStack();

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

	private GunMaterial getMaterialFromName() {
		try {
			return GunMaterial.valueOf(getMaterialName());
		} catch (IllegalArgumentException e) {
			return GunMaterial.SAMURAI_EDGE;
		}
	}

	private void updateGunStack() {
		Item materialItem = this.getMaterialFromName().getRegisteredItem();
		this.hatchetStack = new ItemStack(materialItem != null ? materialItem : RegistryBIBI.SAMURAI_EDGE.get());
	}

	public float getRenderingRotation() { return this.entityData.get(ROTATION);}
	public void setRenderingRotation(float rotation) {this.entityData.set(ROTATION, rotation);}


	  /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	 /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
   /*[DATA SYNC]*/
	 @Override
	 protected void defineSynchedData(SynchedEntityData.Builder builder) {
		 super.defineSynchedData(builder);
		 builder.define(MATERIAL_NAME, "SAMURAI_EDGE");
		 builder.define(ROTATION, 0.0F);
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
			String materialName = compound.getString("material_name");
			this.entityData.set(MATERIAL_NAME, materialName);
			this.material = getMaterialFromName();
			this.updateGunStack();
		}
		if (compound.contains("rotation")) {
			this.entityData.set(ROTATION, compound.getFloat("rotation"));
		}
	}


	 /*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC Entity OVERRIDES]*/
	@Override
	public ItemStack getDefaultPickupItem() {return new ItemStack(RegistryBIBI.SE_BULLET.get());}
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

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
  /*[HITBOX]*/
	@Override
	public void setPos(double x, double y, double z) {
		super.setPos(x, y, z);

		float shortHalf = 0.1F;
		float longHalf = 0.1F;
		float height = 0.1F;

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

		if (!this.inGround) {
			float currentRotation = this.getRenderingRotation();
			float newRotation = (currentRotation * 40F) % 360F;
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
		if (!this.level().isClientSide()) {

			if (player.getItemInHand(hand).isEmpty()) {

				float tone = Mth.randomBetween(this.random, 1.269F, 1.42F);
				this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone);

				ItemStack bullet = new ItemStack(RegistryBIBI.SE_BULLET.get());

				player.setItemInHand(hand, bullet);
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

				// Hurt living entities and play hit sound
				DamageSource fanDamage = level.damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());
				hitEntity.hurt(fanDamage, 2.0F);

				float vol = Mth.randomBetween(this.random, 0.8F, 1.05F);
				float tone = Mth.randomBetween(this.random, 0.8F, 1.1F);
				this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), vol, tone);
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

		super.onHitBlock(result);

		if (!level().isClientSide()) {
			// Use the utility SHAREDMETHODS for button interaction
			HatchetonHitBlock.handleButtonInteraction(result, level(), this);

			if (level().getBlockState(hitPos).is(OP_TagKeyUtil.Blocks.BULLET_FRAGILE)) {
				level().destroyBlock(hitPos, true);
				level().levelEvent(2001, hitPos, Block.getId(level().getBlockState(hitPos)));
			}

			float tone = Mth.randomBetween(this.random, 1.2F, 1.420F);
			this.playSound(RegistrySounds.BLADE_STICK.get(), 0.1420F, tone);

			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}
}
