package net.offllneplayer.opminecraft.iwe.hatchet;

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
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import net.minecraft.stats.Stats;

import net.minecraft.tags.TagKey;

import net.minecraft.util.Mth;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
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
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.block.crying.essence.effect.ApplyCrying1_Method;

import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.Map;

public class Hatchet {

  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
/*[~AS ITEM~]*/
   public static class HatchetItem extends TieredItem implements DispensibleProjectile {

     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*[VARIABLES]*/
     private final HatchetMaterialMap.HatchetMaterial material;
     private final float damage;
     private final float attackSpeed;


     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
   /*[BUILDER]*/
     public HatchetItem(HatchetMaterialMap.HatchetMaterial material, float damage, float attackSpeed) {
         super(createTier(material), createItemProperties(material, damage, attackSpeed));

         // Set the instance fields
         this.material = material;
         this.damage = damage;
         this.attackSpeed = attackSpeed;

         // Associate this item with the material
         material.setRegisteredItem(this);
      }

      private static Item.Properties createItemProperties(HatchetMaterialMap.HatchetMaterial material, float damage, float attackSpeed) {
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
      private static Tier createTier(HatchetMaterialMap.HatchetMaterial material) {
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

     public HatchetMaterialMap.HatchetMaterial getMaterial() {
        return material;
     }


     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
   /*[Use Item OVERRIDES]*/
      @Override
      public int getUseDuration(ItemStack itemstack, LivingEntity user) {
         return 60;
      }
      @Override
      public UseAnim getUseAnimation(ItemStack stack) {
         return UseAnim.SPEAR;
      }

     /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
    /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
   /*[use]*/
      @Override
      public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
         player.startUsingItem(hand);
         return InteractionResultHolder.consume(player.getItemInHand(hand));
      }


     /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
    /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
   /*[release Using]*/
      @Override
      public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
         if (!(user instanceof Player player) || level.isClientSide) return;

         float pull = Mth.clamp((getUseDuration(stack, user) - timeLeft) / 20F, 0F, 1F);
         if (pull < 0.1F) return;

         InteractionHand hand = player.getUsedItemHand();

         double yawRad = Math.toRadians(player.getYRot());
         double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
         double rightX = forwardZ, rightZ = -forwardX;
         double forwardOff = 0.6;
         double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.3 : 0.3;
         double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
         double spawnY = player.getY() + player.getEyeHeight();
         double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

         ThrownHatchet hatchet = new ThrownHatchet(player, level, stack.copy());

         hatchet.setPullRatio(pull);
         hatchet.setPos(spawnX, spawnY, spawnZ);
         hatchet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * 2.5F, 0.420F);
         level.addFreshEntity(hatchet);

         player.awardStat(Stats.ITEM_USED.get(this));
         stack.shrink(1);

         level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1, 1 + pull * 0.2F);
      }


     /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
    /*-----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>*/
   /*[as Projectile]*/
      @Override
      public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {

         ThrownHatchet hatchet =  new ThrownHatchet(null, level, stack.copy());

         hatchet.setPos(pos.x(), pos.y(), pos.z());

         float speed = getDispenseSpeed();
         hatchet.setDeltaMovement(direction.getStepX() * speed, direction.getStepY() * speed + 0.1F, direction.getStepZ() * speed);

         float yRot = switch (direction) {
            case NORTH -> 0F;
            case SOUTH -> 180F;
            case WEST -> 90F;
            case EAST -> 270F;
            default -> 0F;
         };
         hatchet.setYRot(yRot);

         return hatchet;
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
       public static class ThrownHatchet extends AbstractArrow {

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


    /*---[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]------[x]----*/
   /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
/*[ENTITY RENDERER]*/
    @OnlyIn(Dist.CLIENT)
    public static class ThrownHatchetRenderer extends EntityRenderer<ThrownHatchet> {
      public ThrownHatchetRenderer(EntityRendererProvider.Context context) {
         super(context);
      }

      @Override
      public void render(ThrownHatchet entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
         poseStack.pushPose();
         Direction dir = entity.getDirection();
         Direction stuckFace = entity.getStuckDirection();

         if (!entity.isGrounded()) {
            if (dir == Direction.NORTH) poseStack.mulPose(Axis.YP.rotationDegrees(90));
            if (dir == Direction.SOUTH) poseStack.mulPose(Axis.YP.rotationDegrees(270));
            if (dir == Direction.EAST) poseStack.mulPose(Axis.YP.rotationDegrees(180));
            if (dir == Direction.WEST) poseStack.mulPose(Axis.YP.rotationDegrees(0));
            poseStack.mulPose(Axis.ZP.rotationDegrees(entity.rotation));
         } else {
            if (dir == Direction.NORTH) poseStack.mulPose(Axis.YP.rotationDegrees(270));
            if (dir == Direction.SOUTH) poseStack.mulPose(Axis.YP.rotationDegrees(90));
            if (dir == Direction.EAST) poseStack.mulPose(Axis.YP.rotationDegrees(0));
            if (dir == Direction.WEST) poseStack.mulPose(Axis.YP.rotationDegrees(180));

            if (stuckFace == Direction.NORTH) {
               poseStack.mulPose(Axis.XP.rotationDegrees(180));
               poseStack.mulPose(Axis.ZP.rotationDegrees(90));
            } else if (stuckFace == Direction.SOUTH) {
               poseStack.mulPose(Axis.XP.rotationDegrees(180));
               poseStack.mulPose(Axis.ZP.rotationDegrees(90));
            } else if (stuckFace == Direction.EAST) {
               poseStack.mulPose(Axis.XP.rotationDegrees(180));
               poseStack.mulPose(Axis.ZP.rotationDegrees(90));
            } else if (stuckFace == Direction.WEST) {
               poseStack.mulPose(Axis.XP.rotationDegrees(180));
               poseStack.mulPose(Axis.ZP.rotationDegrees(90));
            } else if (stuckFace == Direction.UP) {
               poseStack.mulPose(Axis.XP.rotationDegrees(180));
            } else if (stuckFace == Direction.DOWN) {
               poseStack.mulPose(Axis.XP.rotationDegrees(180));
               poseStack.mulPose(Axis.ZP.rotationDegrees(180));
            }
         }

         // Get material from synchronized data
         String materialName = entity.getEntityData().get(ThrownHatchet.MATERIAL_NAME);
         HatchetMaterialMap.HatchetMaterial material = HatchetMaterialMap.get(materialName);
         Item hatchetItem = material.getRegisteredItem();

         // Create stack with proper item
         ItemStack hatchetStack = new ItemStack(hatchetItem != null ? hatchetItem : RegistryBIBI.GOLDEN_HATCHET.get());

         BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(hatchetStack, entity.getCommandSenderWorld(), null, entity.getId());
         Minecraft.getInstance().getItemRenderer().render(hatchetStack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

         poseStack.popPose();
         super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
      }

      @Override
      public ResourceLocation getTextureLocation(ThrownHatchet entity) {
         String materialName = entity.getEntityData().get(ThrownHatchet.MATERIAL_NAME);
         return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_" + materialName + "_hatchet.png");
      }
   }


 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
}
