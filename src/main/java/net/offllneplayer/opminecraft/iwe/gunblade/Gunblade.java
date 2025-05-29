package net.offllneplayer.opminecraft.iwe.gunblade;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.offllneplayer.opminecraft.init.*;
import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.block.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitBlock;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitEntity;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.entity.sw0rd.StickSw0rd_Method;
import net.offllneplayer.opminecraft.entity.sw0rd.Stuck_Sw0rd_OnClick_Method;

import java.util.List;
import java.util.Map;

public class Gunblade {


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
  /*[~AS ITEM~]*/
	public static class GunbladeItem extends SwordItem {

		 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
		 /*[VARIABLES]*/
		 private final GunbladeMaterialMap.GunbladeMaterial material;
		 private final float damage;
		 private final float attackSpeed;


		  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
		 /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	   /*[BUILDER]*/
		 public GunbladeItem(GunbladeMaterialMap.GunbladeMaterial material, float damage, float attackSpeed) {
			 super(createTier(material), createItemProperties(material, damage, attackSpeed));

			 // Set the instance fields
			 this.material = material;
			 this.damage = damage;
			 this.attackSpeed = attackSpeed;

			 // Associate this item with the material
			 material.setRegisteredItem(this);
		 }

		 private static Item.Properties createItemProperties(GunbladeMaterialMap.GunbladeMaterial material, float damage, float attackSpeed) {
			 Item.Properties itemProperties = new Item.Properties()
				 .attributes(SwordItem.createAttributes(createTier(material), damage, attackSpeed))
				 .stacksTo(1)
				 .rarity(material.getRarity());

			 if (material.isFireResistant()) {
				 itemProperties.fireResistant();
			 }

			 return itemProperties;
		 }


		   /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	     /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
		 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	   /*[BASIC TOOL Item OVERRIDES]*/
		 private static Tier createTier(GunbladeMaterialMap.GunbladeMaterial material) {
			 return new Tier() {
				 @Override
				 public int getUses() { return material.getDurability(); }
				 @Override
				 public float getSpeed() { return material.getMiningSpeed(); }
				 @Override
				 public float getAttackDamageBonus() { return 0; }
				 @Override
				 public TagKey<Block> getIncorrectBlocksForDrops() { return material.getIncorrectBlocksForDrops(); }
				 @Override
				 public int getEnchantmentValue() { return material.getEnchantability(); }
				 @Override
				 public Ingredient getRepairIngredient() { return material.getRepairIngredient(); }
			 };
		 }

		 public GunbladeMaterialMap.GunbladeMaterial getMaterial() {
			 return material;
		 }


		 @Override
		 @OnlyIn(Dist.CLIENT)
		 public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			 super.appendHoverText(itemstack, context, list, flag);
			 list.add(Component.translatable("item.opminecraft.gunblade.description_0"));
			 list.add(Component.translatable("item.opminecraft.gunblade.description_1"));
			 list.add(Component.translatable("item.opminecraft.gunblade.description_2"));
		 }


		  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
		 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
		/*[Use Item OVERRIDES]*/
		  @Override
		  public InteractionResult useOn(UseOnContext context) {
			  return StickSw0rd_Method.execute(context);
		  }

		 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/

		@Override
		public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity sourceEntity) {

			Level level = sourceEntity.level();

			if (!level.isClientSide()) {

				Long lastHitTime = stack.get(RegistryDataComponents.GUNBLADE_LAST_HIT_TIME.get());
				long currentTime = level.getGameTime();

				if (lastHitTime != null && currentTime - lastHitTime <= 20L) {
					sourceEntity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.420F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.0420F));
				}
				stack.set(RegistryDataComponents.GUNBLADE_LAST_HIT_TIME.get(), level.getGameTime());
			}

			return super.hurtEnemy(stack, entity, sourceEntity);
		}


		@Override
		public InteractionResult interactLivingEntity(ItemStack stack, Player sourceEntity, LivingEntity entity, InteractionHand hand) {

			Level level = sourceEntity.level();

			if (!level.isClientSide()) {
				if (sourceEntity.getCooldowns().isOnCooldown(stack.getItem())) {
					return InteractionResult.PASS;
				}

				Long lastHitTime = stack.get(RegistryDataComponents.GUNBLADE_LAST_HIT_TIME.get());
				long currentTime = level.getGameTime();

				if (lastHitTime != null && currentTime - lastHitTime <= 40L) {

					stack.remove(RegistryDataComponents.GUNBLADE_LAST_HIT_TIME.get());
					stack.set(RegistryDataComponents.GUNBLADE_LAST_HIT_TIME.get(), null);

					GunbladeShot_Method.execute(level, entity.getX(), entity.getY(), entity.getZ(), entity, sourceEntity);

					sourceEntity.getCooldowns().addCooldown(stack.getItem(), 40);

					return InteractionResult.sidedSuccess(level.isClientSide());
				}
			}
			return InteractionResult.FAIL;
		}
	}


			/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
		  /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
		 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	 /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[~AS ENTITY~]*/
	public static class StuckGunblade extends AbstractArrow {

		/*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
	  /*[DATA]*/
		public static final EntityDataAccessor<String> MATERIAL_NAME = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.STRING);
		public static final EntityDataAccessor<Byte> STUCK_FACE = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.BYTE);
		public static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(StuckGunblade.class, EntityDataSerializers.FLOAT);

		 /*-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x-~x~-~x-~x*/
		/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	  /*[VARIABLES]*/

		public BlockPos stuckPos;
		public Block stuckBlock;

		// Store the material of the blade
		public GunbladeMaterialMap.GunbladeMaterial material;
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
			data.putString("nayme", stack.getHoverName().getString());
			data.putInt("DMG_VALU", stack.getDamageValue());

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

		public void setStuckFace(Direction face) {
			this.entityData.set(STUCK_FACE, (byte)face.get3DDataValue());
		}

		public void setRenderingRotation(float rotation) {
			this.entityData.set(ROTATION, rotation);
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
		}


		 /*- _______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______-=- -=-_______ -*/
		/*-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>-=>*/
	  /*[interact]*/
		@Override
		public InteractionResult interact(Player player, InteractionHand hand) {
			Stuck_Sw0rd_OnClick_Method.execute(this.level(), this, player, hand);
			return player.getItemInHand(hand).isEmpty() ? InteractionResult.SUCCESS : InteractionResult.PASS;
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


		/*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
     /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
  /*[ENTITY RENDERER]*/
	@OnlyIn(Dist.CLIENT)
	public static class StuckGunbladeRenderer extends EntityRenderer<StuckGunblade> {
		public StuckGunbladeRenderer(EntityRendererProvider.Context context) {
			super(context);
		}

			@Override
			public void render(StuckGunblade entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
				poseStack.pushPose();

				Direction stuckFace = entity.getStuckFace();
				float rotation = entity.getRenderingRotation();

				if (!entity.isGrounded()) {
					poseStack.mulPose(Axis.XP.rotationDegrees(0));
					poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
					poseStack.mulPose(Axis.ZP.rotationDegrees(45));

				} else {
					if (stuckFace == Direction.NORTH) {
						poseStack.mulPose(Axis.XP.rotationDegrees(270));
						poseStack.mulPose(Axis.YP.rotationDegrees(0));
						poseStack.mulPose(Axis.ZP.rotationDegrees(45));
					} else if (stuckFace == Direction.SOUTH) {
						poseStack.mulPose(Axis.XP.rotationDegrees(90));
						poseStack.mulPose(Axis.YP.rotationDegrees(180));
						poseStack.mulPose(Axis.ZP.rotationDegrees(45));
					} else if (stuckFace == Direction.EAST) {
						poseStack.mulPose(Axis.XP.rotationDegrees(270));
						poseStack.mulPose(Axis.YP.rotationDegrees(0));
						poseStack.mulPose(Axis.ZP.rotationDegrees(315));
					} else if (stuckFace == Direction.WEST) {
						poseStack.mulPose(Axis.XP.rotationDegrees(90));
						poseStack.mulPose(Axis.YP.rotationDegrees(180));
						poseStack.mulPose(Axis.ZP.rotationDegrees(315));
					} else if (stuckFace == Direction.UP) {
						poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
						poseStack.mulPose(Axis.ZP.rotationDegrees(45));
					} else if (stuckFace == Direction.DOWN) {
						poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // dynamic
						poseStack.mulPose(Axis.ZP.rotationDegrees(225));
					}
				}

			String materialName = entity.getEntityData().get(StuckGunblade.MATERIAL_NAME);
			GunbladeMaterialMap.GunbladeMaterial material = GunbladeMaterialMap.get(materialName);
			Item bladeItem = material.getRegisteredItem();

			ItemStack bladeStack = new ItemStack(bladeItem != null ? bladeItem : RegistryBIBI.TITAN_GUNBLADE.get());

			BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(bladeStack, entity.getCommandSenderWorld(), null, entity.getId());
			Minecraft.getInstance().getItemRenderer().render(bladeStack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

			poseStack.popPose();
			super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
		}

		@Override
		public ResourceLocation getTextureLocation(StuckGunblade entity) {
			String materialName = entity.getEntityData().get(StuckGunblade.MATERIAL_NAME);
			return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_" + materialName + "_gunblade.png");
		}
	}


 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
}
