package net.offllneplayer.opminecraft.iwe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
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

import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.level.ServerLevel;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import net.minecraft.stats.Stats;

import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;

import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import net.offllneplayer.opminecraft.method.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.method.util.OP_NBTUtil;
import net.offllneplayer.opminecraft.method.util.OP_ProjectileUtil;

import java.util.Map;


public class CryingHatchet {

  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
/*[~AS ITEM~]*/
   public static class CryingHatchetItem extends TieredItem implements DispensibleProjectile {

     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
   /*[BUILDER]*/
      public CryingHatchetItem() {
         super(TOOL_TIER, new Properties()
            .attributes(SwordItem.createAttributes(TOOL_TIER, 4F, -2.69F))
            .stacksTo(1)
            .rarity(Rarity.EPIC));
      }

      /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
     /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*[BASIC TOOL Item OVERRIDES]*/
      private static final Tier TOOL_TIER = new Tier() {
         @Override
         public int getUses() {
            return 420;
         }
         @Override
         public float getSpeed() {
            return 11F;
         }
         @Override
         public float getAttackDamageBonus() {
            return 0;
         }
         @Override
         public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
         }
         @Override
         public int getEnchantmentValue() {
            return 20;
         }
         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(RegistryIBBI.CRYING_INGOT.get()));
         }
      };


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

         ThrownCryingHatchet hatchet = new ThrownCryingHatchet(player, level, stack.copy());

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

         ThrownCryingHatchet hatchet =  new ThrownCryingHatchet(null, level, stack.copy());

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
    public static class ThrownCryingHatchet extends AbstractArrow {

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*[VARIABLES]*/
        private BlockPos stuckPos;
        private Block stuckBlock;
        private Direction stuckDirection = Direction.NORTH;

        private float pullRatio = 1F;
        private float rotation;

     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
   /*[BUILDERS]*/
        public ThrownCryingHatchet(EntityType<? extends ThrownCryingHatchet> type, Level level) {
            super(type, level);
        }

        public ThrownCryingHatchet(Level world, LivingEntity shooter) {
            super(RegistryEntities.THROWN_CRYING_HATCHET.get(), world);
            this.setOwner(shooter);

            if (shooter != null) {
                this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
            }
            this.pickup = Pickup.DISALLOWED;
        }

        public ThrownCryingHatchet(Player shooter, Level world, ItemStack stack) {
            this(world, shooter);

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
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*[BASIC Entity OVERRIDES]*/
        @Override
        public ItemStack getDefaultPickupItem() {return new ItemStack(RegistryIBBI.CRYING_HATCHET.get());}
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

            if (this.inGround && stuckPos != null) {
                if (level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                    this.inGround = false;
                    this.hasImpulse = true;
                    this.setDeltaMovement(Vec3.ZERO);
                }
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

                    ItemStack blade = new ItemStack(RegistryIBBI.CRYING_HATCHET.get());
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
            if (this.level().isClientSide()) return;
            Entity hitEntity = result.getEntity();
            Level level = hitEntity.level();
            double x = hitEntity.getX();
            double y = hitEntity.getY();
            double z = hitEntity.getZ();

            if (hitEntity instanceof LivingEntity living) {
                if (level() instanceof ServerLevel serverLevel) {
                    OP_NBTUtil.WeaponData wd = OP_NBTUtil.readItemStacktoClass(this.getPersistentData(), level);
                    Map<Enchantment, Integer> enchs = wd.enchants();

                    DamageSource hatchetDMG = level.damageSources().source(RegistryDamageTypes.HATCHET, this, this.getOwner());
                    float dmg = 9F;
                    float enchantDmg = OP_ProjectileUtil.calculateDamageBonus(living, enchs);
                    float damage = dmg + enchantDmg;

                    living.hurt(hatchetDMG, damage);


                   OP_ProjectileUtil.processUnbreaking(this, enchs, this.random);

                   OP_ProjectileUtil.applyFireAspect(living, enchs);
                   OP_ProjectileUtil.applyKnockback(this, living, enchs);
                   OP_ProjectileUtil.applyCleaving(this, enchs, this.random);

                    ItemStack dummyStack = new ItemStack(RegistryIBBI.CRYING_HATCHET.get());
                    OP_NBTUtil.enchantWeaponDataToItemstack(dummyStack, this.getPersistentData(), level());
                    EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, living, hatchetDMG, dummyStack);

                   ApplyCrying1_Method.execute(living);

                   level.broadcastEntityEvent(this, (byte) 3);

                    float tone = Mth.randomBetween(this.random, 1.420F, 1.69F);
                    this.playSound(RegistrySounds.BLADE_SLASH.get(), 0.169F, tone);
                }
            } else {
               // Handle non-living entities utility class
              OP_ProjectileUtil.miscEntityHit(this, hitEntity, level, this.random);
              level.broadcastEntityEvent(this, (byte) 3);
            }
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
                BlockPos pos = result.getBlockPos();
                BlockState state = level().getBlockState(pos);
                Block block = state.getBlock();

                // Handle button interaction using the buttons tag
                if (block.builtInRegistryHolder().is(BlockTags.BUTTONS)) {
                    if (state.hasProperty(ButtonBlock.POWERED) && !state.getValue(ButtonBlock.POWERED)) {
                        // Only press if not already pressed
                        BlockState pressedState = state.setValue(ButtonBlock.POWERED, true);
                        level().setBlock(pos, pressedState, 3);

                        level().playSound(null, pos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, 1F);

                        level().scheduleTick(pos, block, 30);
                        level().updateNeighborsAt(pos, block);

                        // Get the attached face direction
                        if (state.hasProperty(ButtonBlock.FACING) && state.hasProperty(ButtonBlock.FACE)) {
                            Direction direction = state.getValue(ButtonBlock.FACING);
                            AttachFace face = state.getValue(ButtonBlock.FACE);

                            // Convert AttachFace to Direction
                            Direction faceDirection;
                            switch (face) {
                                case FLOOR:
                                    faceDirection = Direction.DOWN;
                                    break;
                                case CEILING:
                                    faceDirection = Direction.UP;
                                    break;
                                case WALL:
                                default:
                                    faceDirection = direction.getOpposite();
                                    break;
                            }

                            level().updateNeighborsAt(pos.relative(faceDirection), block);
                        }
                        // Create block event to notify neighbors
                        level().gameEvent(this.getOwner(), GameEvent.BLOCK_ACTIVATE, pos);
                    }
                }

                float tone = Mth.randomBetween(this.random, 1.3F, 1.420F);
                this.playSound(RegistrySounds.BLADE_STICK.get(), 0.420F, tone);
                this.level().broadcastEntityEvent(this, (byte) 3);
            }
        }
    }


      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
/*[ENTITY RENDERER]*/
   public static class ThrownCryingHatchetRenderer extends EntityRenderer<ThrownCryingHatchet> {
      public ThrownCryingHatchetRenderer(EntityRendererProvider.Context context) {
         super(context);
      }

      @Override
      public void render(ThrownCryingHatchet entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
         poseStack.pushPose();

         Direction dir = entity.getDirection();
         Direction stuckFace = entity.getStuckDirection();

         if (!entity.isGrounded()) {
            if (dir == Direction.NORTH) poseStack.mulPose(Axis.YP.rotationDegrees(90));
            if (dir == Direction.SOUTH) poseStack.mulPose(Axis.YP.rotationDegrees(270));
            if (dir == Direction.EAST) poseStack.mulPose(Axis.YP.rotationDegrees(180));
            if (dir == Direction.WEST) poseStack.mulPose(Axis.YP.rotationDegrees(0));
            poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getRenderingRotation()));
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

         ItemStack stack = new ItemStack(RegistryIBBI.CRYING_HATCHET.get());
         BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, entity.getCommandSenderWorld(), null, entity.getId());
         Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

         poseStack.popPose();
         super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
      }

      @Override
      public ResourceLocation getTextureLocation(ThrownCryingHatchet entity) {
         return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_crying_hatchet.png");
      }
   }


      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /* -^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^ */
/*[ENTITY MODEL]*/
      public class ThrownCryingHatchetModel extends EntityModel<ThrownCryingHatchet> {
         private final ModelPart root;

         public ThrownCryingHatchetModel(ModelPart root) {
            this.root = root;
         }

         @Override
         public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
            root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
         }

         public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDefinition = new MeshDefinition();
            PartDefinition rootDef = meshDefinition.getRoot();
            rootDef.addOrReplaceChild("root",
               CubeListBuilder.create()
                  .texOffs(0, 0)
                  .addBox(-8.0F, -8.0F, -0.4F, 16, 16, 0.8F),
               PartPose.offset(0.0F, 0.0F, 0.0F)
            );
            return LayerDefinition.create(meshDefinition, 16, 16);
         }

         @Override
         public void setupAnim(ThrownCryingHatchet entity, float v, float v1, float v2, float v3, float v4) {/*VOIDED vanilla nonsense*/}
      }


/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
}
