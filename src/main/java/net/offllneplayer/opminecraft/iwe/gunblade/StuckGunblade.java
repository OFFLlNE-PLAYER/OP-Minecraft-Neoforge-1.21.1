package net.offllneplayer.opminecraft.iwe.gunblade;

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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.block.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.entity.sw0rd.Stuck_Sw0rd_OnClick_Method;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitBlock;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitEntity;

import java.util.Map;


public class StuckGunblade extends AbstractArrow {

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*[DATA]*/
	private static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Byte> STUCK_FACE = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.FLOAT);

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[VARIABLES]*/

	private BlockPos stuckPos;
	private Block stuckBlock;

	// Store the material of the blade
	private GunbladeMaterialMap.GunbladeMaterial material;
	private float dmg;
	private ItemStack bladeStack;


	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*[BUILDERS]*/
	public StuckGunblade(EntityType<? extends StuckGunblade> type, Level level) {
		super(type, level);
		// Default to netherite material for compatibility
		this.material = GunbladeMaterialMap.TITAN;
		this.updateBladeStackmaterial();
	}

	public StuckGunblade(Level world, LivingEntity shooter) {
		super(RegistryEntities.STUCK_GUNBLADE.get(), world);
		this.setOwner(shooter);
		// Default to netherite material
		this.material = GunbladeMaterialMap.TITAN;
		this.entityData.set(MATERIAL_NAME, "titan");
		this.updateBladeStackmaterial();

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public StuckGunblade(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		// Try to get material from the item if it's a BladeItem
		if (stack.getItem() instanceof GunbladeItem gunbladeItem) {
			this.material = gunbladeItem.getMaterial();
			this.entityData.set(MATERIAL_NAME, this.material.getName());
		}

		this.updateBladeStackmaterial();

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

	// update the bladeStack based on material
	public GunbladeMaterialMap.GunbladeMaterial getMaterial() {return material;}

	public void updateBladeStackmaterial() {
		Item materialItem = null;

		if (material == GunbladeMaterialMap.ONYX) {
			materialItem = RegistryBIBI.ONYX_GUNBLADE.get();
		} else if (material == GunbladeMaterialMap.TITAN) {
			materialItem = RegistryBIBI.TITAN_GUNBLADE.get();
		} else if (material == GunbladeMaterialMap.CRYING) {
			materialItem = RegistryBIBI.CRYING_GUNBLADE.get();
		} else {
			materialItem = RegistryBIBI.TITAN_GUNBLADE.get(); // Fallback
		}

		this.bladeStack = new ItemStack(materialItem);
		this.dmg = this.material.getAttackDamage();
	}

	public boolean isGrounded() {
		return inGround;
	}

	public String getMaterialName() {
		return this.entityData.get(MATERIAL_NAME);
	}

	public void setStuckFace(Direction face) {
		this.entityData.set(STUCK_FACE, (byte)face.get3DDataValue());
	}

	public void setRenderingRotation(float rotation) {
		this.entityData.set(ROTATION, rotation);
	}

	public void setStuckPos(BlockPos pos) {
		this.stuckPos = pos;
	}

	public void setStuckBlock(Block block) {
		this.stuckBlock = block;
	}

	public Direction getStuckFace() {
		return Direction.from3DDataValue(this.entityData.get(STUCK_FACE));
	}

	public float getRenderingRotation() {
		return this.entityData.get(ROTATION);
	}


	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*[DATA SYNC]*/
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(MATERIAL_NAME, "titan");
		builder.define(STUCK_FACE, (byte)Direction.UP.get3DDataValue());
		builder.define(ROTATION, 0.0F);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("material_name", this.entityData.get(MATERIAL_NAME));
		compound.putByte("stuck_face", this.entityData.get(STUCK_FACE));
		compound.putFloat("rotation", this.entityData.get(ROTATION));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("material_name")) {
			String materialName = compound.getString("material_name");
			this.entityData.set(MATERIAL_NAME, materialName);
			this.material = GunbladeMaterialMap.get(materialName);
			this.updateBladeStackmaterial();
		}
		if (compound.contains("stuck_face")) {
			this.entityData.set(STUCK_FACE, compound.getByte("stuck_face"));
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
		// Get the correct item for this material
		Item materialItem = GunbladeMaterialMap.get(this.entityData.get(MATERIAL_NAME)).getRegisteredItem();
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

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]--[]-*/
	/*[HITBOX]*/
	@Override
	public void setPos(double x, double y, double z) {
		super.setPos(x, y, z);
		float shortHalf = 0.0420F;
		float longHalf = 0.1420F;
		float height = 0.72F;
		float offset = 0.1420F;
		Direction stuckFace = this.getStuckFace();

		if (stuckFace == Direction.UP) {
			double cos = Math.cos(Math.toRadians(this.getRenderingRotation()));
			double sin = Math.sin(Math.toRadians(this.getRenderingRotation()));
			double dx = longHalf * Math.abs(cos) + shortHalf * Math.abs(sin);
			double dz = longHalf * Math.abs(sin) + shortHalf * Math.abs(cos);

			// Ensure minimum dimensions
			dx = Math.max(dx, 0.0420F);  // Minimum width
			dz = Math.max(dz, 0.0420F);  // Minimum depth

			// Add offset in direction of rotation
			x -= offset * cos;
			z += offset * sin;

			this.setBoundingBox(new AABB(
				x - dx,
				y - height,
				z - dz,
				x + dx,
				y + height,
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

			// Add offset in direction of rotation
			x += offset * cos;
			z -= offset * sin;

			this.setBoundingBox(new AABB(
				x - dx,
				y - height,
				z - dz,
				x + dx,
				y + height,
				z + dz
			));
		} else if (stuckFace == Direction.NORTH) {
			x -= offset;
			this.setBoundingBox(new AABB(
				x - longHalf,
				y - shortHalf,
				z - height,
				x + longHalf,
				y + shortHalf,
				z + height
			));
		} else if (stuckFace == Direction.SOUTH) {
			x += offset;
			this.setBoundingBox(new AABB(
				x - longHalf,
				y - shortHalf,
				z - height,
				x + longHalf,
				y + shortHalf,
				z + height
			));
		} else if (stuckFace == Direction.EAST) {
			z -= offset;
			this.setBoundingBox(new AABB(
				x - height,
				y - shortHalf,
				z - longHalf,
				x + height,
				y + shortHalf,
				z + longHalf
			));
		} else if (stuckFace == Direction.WEST) {
			z += offset;
			this.setBoundingBox(new AABB(
				x - height,
				y - shortHalf,
				z - longHalf,
				x + height,
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

		if (this.inGround && stuckPos != null && level().getBlockState(stuckPos).getBlock() != stuckBlock) {
			this.inGround = false;
			this.hasImpulse = true;
			this.setDeltaMovement(Vec3.ZERO);
		}

		if (!this.inGround) {

			this.setStuckFace(Direction.UP);

			float currentRotation = this.getRenderingRotation();
			float newRotation;
			newRotation = (random.nextBoolean() ? currentRotation + 10F : currentRotation - 10F) % 360F;
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
		return Stuck_Sw0rd_OnClick_Method.execute(this.level(), this, player, hand);
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

					HatchetonHitEntity.processUnbreaking(this, enchs, random);
					HatchetonHitEntity.applyFireAspect(living, enchs);
					HatchetonHitEntity.applyKnockback(this, living, enchs);
					HatchetonHitEntity.applyCleaving(this, enchs, random);

					OP_NBTUtil.enchantWeaponDataToItemstack(this.bladeStack, this.getPersistentData(), level);
					EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, bladeDMG, this.bladeStack);

					float enchantDmg = HatchetonHitEntity.calculateDamageBonus(living, enchs);
					float damage = dmg + enchantDmg;
					living.hurt(bladeDMG, damage);

					if (material == GunbladeMaterialMap.CRYING) {
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
		stuckPos = result.getBlockPos();
		stuckBlock = level().getBlockState(stuckPos).getBlock();

		this.setStuckFace(result.getDirection());

		super.onHitBlock(result);

		if (!level().isClientSide()) {

			// Use the utility SHAREDMETHODS for button interaction
			HatchetonHitBlock.handleButtonInteraction(result, level(), this);

			float tone = Mth.randomBetween(this.random, 1.3F, 1.420F);
			this.playSound(RegistrySounds.BLADE_STICK.get(), 0.2420F, tone);
			this.level().broadcastEntityEvent(this, (byte) 3);
		}
	}
}
