package net.offllneplayer.opminecraft.iwe.hatchet;


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
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import java.util.Map;


public class ThrownHatchet extends AbstractArrow {

	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*[DATA]*/
	private static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(ThrownHatchet.class, EntityDataSerializers.STRING);


	/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[VARIABLES]*/

	private BlockPos stuckPos;
	private Block stuckBlock;
	private Direction stuckDirection = Direction.NORTH;

	private float pullRatio = 1F;
	private float rotation;

	// Store the material of the hatchet
	private HatchetMaterialMap.HatchetMaterial material;
	private float dmg;
	private ItemStack hatchetStack;


	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*[BUILDERS]*/
	public ThrownHatchet(EntityType<? extends ThrownHatchet> type, Level level) {
		super(type, level);
		// Default to netherite material for compatibility
		this.material = HatchetMaterialMap.NETHERITE;
		this.updateHatchetStack();
	}

	public ThrownHatchet(Level world, LivingEntity shooter) {
		super(RegistryEntities.THROWN_HATCHET.get(), world);
		this.setOwner(shooter);
		// Default to netherite material
		this.material = HatchetMaterialMap.NETHERITE;
		this.entityData.set(MATERIAL_NAME, "netherite");
		this.updateHatchetStack();

		if (shooter != null) {
			this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
		}
		this.pickup = Pickup.DISALLOWED;
	}

	public ThrownHatchet(Player shooter, Level world, ItemStack stack) {
		this(world, shooter);

		// Try to get material from the item if it's a HatchetItem
		if (stack.getItem() instanceof HatchetItem hatchetItem) {
			this.material = hatchetItem.getMaterial();
			this.entityData.set(MATERIAL_NAME, this.material.getName());
		}

		this.updateHatchetStack();

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

	// update the hatchetStack based on material
	public HatchetMaterialMap.HatchetMaterial getMaterial() {return material;}
	private void updateHatchetStack() {
		Item materialItem = null;

		if (material == HatchetMaterialMap.WOODEN) {
			materialItem = RegistryBIBI.WOODEN_HATCHET.get();
		} else if (material == HatchetMaterialMap.STONE) {
			materialItem = RegistryBIBI.STONE_HATCHET.get();
		} else if (material == HatchetMaterialMap.IRON) {
			materialItem = RegistryBIBI.IRON_HATCHET.get();
		} else if (material == HatchetMaterialMap.GOLDEN) {
			materialItem = RegistryBIBI.GOLDEN_HATCHET.get();
		} else if (material == HatchetMaterialMap.DIAMOND) {
			materialItem = RegistryBIBI.DIAMOND_HATCHET.get();
		} else if (material == HatchetMaterialMap.NETHERITE) {
			materialItem = RegistryBIBI.NETHERITE_HATCHET.get();
		} else if (material == HatchetMaterialMap.CRYING) {
			materialItem = RegistryBIBI.CRYING_HATCHET.get();
		} else {
			materialItem = RegistryBIBI.GOLDEN_HATCHET.get(); // Fallback
		}

		this.hatchetStack = new ItemStack(materialItem);
		this.dmg = this.material.getAttackDamage();
	}

	public void setPullRatio(float pullRatio) {
		this.pullRatio = pullRatio;
	}

	public boolean isGrounded() {
		return inGround;
	}

	public Direction getStuckDirection() {
		return stuckDirection != null ? stuckDirection : Direction.NORTH;
	}

	public String getMaterialName() {
		return this.entityData.get(MATERIAL_NAME);
	}

	public float getRenderingRotation() {
		return rotation;
	}

	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*[DATA SYNC]*/
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(MATERIAL_NAME, "golden");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("material_name", this.entityData.get(MATERIAL_NAME));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("material_name")) {
			String materialName = compound.getString("material_name");
			this.entityData.set(MATERIAL_NAME, materialName);

			// Update material field from saved data
			this.material = HatchetMaterialMap.get(materialName);
			this.updateHatchetStack();
		}
	}


	/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[BASIC Entity OVERRIDES]*/
	@Override
	public ItemStack getDefaultPickupItem() {
		// Use the material that was synchronized from server to client
		String materialName = this.entityData.get(MATERIAL_NAME);
		HatchetMaterialMap.HatchetMaterial mat = HatchetMaterialMap.get(materialName);

		// Get the correct item for this material
		Item materialItem = mat.getRegisteredItem();
		return new ItemStack(materialItem != null ? materialItem : RegistryBIBI.NETHERITE_HATCHET.get());
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

		if (this.inGround && stuckPos != null && level().getBlockState(stuckPos).getBlock() != stuckBlock) {
			this.inGround = false;
			this.hasImpulse = true;
			this.setDeltaMovement(Vec3.ZERO);
		}

		if (!this.inGround) {
			rotation = (rotation - pullRatio * 20F) % 360F;
			if (rotation < 0) rotation += 360F;
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
			DamageSource hatchetDMG = level().damageSources().source(RegistryDamageTypes.HATCHET, this, this.getOwner());
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
					float damage = dmg + enchantDmg;
					living.hurt(hatchetDMG, damage);

					if (material == HatchetMaterialMap.CRYING) {
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
		stuckDirection = result.getDirection();
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
