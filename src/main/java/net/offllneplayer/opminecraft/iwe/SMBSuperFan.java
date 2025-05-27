package net.offllneplayer.opminecraft.iwe;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

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

import net.minecraft.network.chat.Component;

import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.level.ServerLevel;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import net.minecraft.stats.Stats;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;

import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryEntities;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import net.offllneplayer.opminecraft.UTIL.OP_NBTUtil;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitBlock;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetonHitEntity;

import java.util.List;
import java.util.Map;


public class SMBSuperFan {

  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
/*[~AS ITEM~]*/
    public static class SMBSuperFanItem extends TieredItem implements DispensibleProjectile {

         /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
       /*[BUILDER]*/
        public SMBSuperFanItem() {
            super(TOOL_TIER, new Properties()
               .attributes(SwordItem.createAttributes(TOOL_TIER, 4F, -2.69F))
               .stacksTo(1)
               .rarity(Rarity.EPIC));
        }

         /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
        /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
       /*[BASIC TOOL Item OVERRIDES]*/
        private static final Tier TOOL_TIER = new Tier() {
            @Override
            public int getUses() {return 420;}
            @Override
            public float getSpeed() {return 11F;}
            @Override
            public float getAttackDamageBonus() {return 0;}
            @Override
            public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;}
            @Override
            public int getEnchantmentValue() {return 20;}
            @Override
            public Ingredient getRepairIngredient() {return Ingredient.EMPTY;}
        };

         /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*-{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--*/
       /*[TOOLTIP EXTRAS]*/
        @Override
        @OnlyIn(Dist.CLIENT)
        public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, context, list, flag);
            list.add(Component.translatable("item.opminecraft.smb_super_fan.description_0"));
        }

          /*-{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--{}--*/
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
            double forwardOff = 0.7;
            double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.5 : 0.5;
            double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
            double spawnY = player.getY() + player.getEyeHeight();
            double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

            ThrownSMBSuperFan superFan = new ThrownSMBSuperFan(player, level, stack.copy());

            superFan.setPullRatio(pull);
            superFan.setPos(spawnX, spawnY, spawnZ);
            superFan.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * 2.5F, 0.420F);
            level.addFreshEntity(superFan);

            player.awardStat(Stats.ITEM_USED.get(this));
            stack.shrink(1);

            float vol = Mth.nextFloat(RandomSource.create(), 0.69F, 1F);
            float tone = Mth.nextFloat(RandomSource.create(), 0.9F, 1.2F);
            level.playSound(null, player.blockPosition(), RegistrySounds.SMB_SUPER_FAN_HIT.get(), SoundSource.PLAYERS, vol, tone);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F + pull * 0.2F);
        }


         /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
        /*-----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>*/
       /*[as Projectile]*/
        @Override
        public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
            ThrownSMBSuperFan fan = new ThrownSMBSuperFan(level, null);

            fan.setPos(pos.x(), pos.y(), pos.z());

            // Copy item data to entity
            fan.getPersistentData().putString("nayme", stack.getHoverName().getString());
            fan.getPersistentData().putInt("DMG_VALU", stack.getDamageValue());

            var enchants = stack.getComponents().get(DataComponents.ENCHANTMENTS);
            for (var entry : enchants.entrySet()) {
                entry.getKey().unwrapKey().ifPresent(key ->
                   fan.getPersistentData().putInt(key.location().toString(), entry.getIntValue())
                );
            }

            // Set velocity and rotation
            float speed = getDispenseSpeed();
            fan.setDeltaMovement(
               direction.getStepX() * speed,
               direction.getStepY() * speed + 0.1F,
               direction.getStepZ() * speed
            );

            float yRot = switch (direction) {
                case NORTH -> 0F;
                case SOUTH -> 180F;
                case WEST -> 90F;
                case EAST -> 270F;
                default -> 0F;
            };
            fan.setYRot(yRot);

            return fan;
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
    public static class ThrownSMBSuperFan extends AbstractArrow {

        /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
       /*[VARIABLES]*/
        private BlockPos stuckPos;
        private Block stuckBlock;
        private Direction stuckFace = null;
        private float rotation;
        private float pullRatio = 1F;

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
        public boolean isGrounded() {
            return inGround;
        }

        public void setPullRatio(float pullRatio) {
            this.pullRatio = pullRatio;
        }

        public Direction getStuckFace() {
            return stuckFace;
        }

        public float getRenderingRotation() {
            return rotation;
        }

         /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
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

            if (this.inGround && stuckPos != null && level().getBlockState(stuckPos).getBlock() != stuckBlock) {
                this.inGround = false;
                this.hasImpulse = true;
                this.setDeltaMovement(Vec3.ZERO);
            }

            if (!this.inGround) {
                rotation = (rotation - pullRatio * 20F) % 360F;
                if (rotation < 0) rotation += 360F;
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
                                this.getPersistentData().putInt("DMG_VALU", wd.dmgValue() - 1);
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

                    DamageSource fanDMG = level.damageSources().source(RegistryDamageTypes.SMB_SUPER_FAN, this, this.getOwner());
                    float dmg = 4F;
                    float enchantDmg = HatchetonHitEntity.calculateDamageBonus(living, enchs);
                    float damage = dmg + enchantDmg;

                    living.hurt(fanDMG, damage);

                    HatchetonHitEntity.processUnbreaking(this, enchs, this.random);

                    HatchetonHitEntity.applyFireAspect(living, enchs);
                    HatchetonHitEntity.applyKnockback(this, living, enchs);
                    HatchetonHitEntity.applyCleaving(this, enchs, this.random);

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
                HatchetonHitEntity.miscEntityHit(this, hitEntity, level, this.random);
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
            stuckFace = result.getDirection();
            super.onHitBlock(result);

            if (!level().isClientSide()) {

                // Use the utility SHAREDMETHODS for button interaction
                HatchetonHitBlock.handleButtonInteraction(result, level(), this);

                float tone = Mth.randomBetween(this.random, 1.2F, 1.420F);
                this.playSound(RegistrySounds.BLADE_STICK.get(), 0.1420F, tone);

                float tone2 = Mth.randomBetween(this.random, 0.85F, 1.2F);
                this.playSound(RegistrySounds.SMB_SUPER_FAN_HIT.get(), 1.0F, tone2);

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
    @OnlyIn(Dist.CLIENT)
    public static class ThrownSMBSuperFanRenderer extends EntityRenderer<ThrownSMBSuperFan> {

        public ThrownSMBSuperFanRenderer(EntityRendererProvider.Context context) {
            super(context);
        }

        @Override
        public void render(ThrownSMBSuperFan entity, float entityYaw, float partialTicks, PoseStack poseStack,
                           MultiBufferSource buffer, int packedLight) {
            poseStack.pushPose();
            poseStack.scale(2F, 2F, 2F);

            Direction dir = entity.getDirection();
            Direction stuckFace = entity.getStuckFace();

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

            ItemStack stack = new ItemStack(RegistryBIBI.SMB_SUPER_FAN.get());
            // Retrieve the BakedModel for the ItemStack.
            BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, entity.getCommandSenderWorld(), null, entity.getId());
            // Render the item using the obtained BakedModel.
            Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

            poseStack.popPose();
            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }

        @Override
        public ResourceLocation getTextureLocation(ThrownSMBSuperFan entity) {
            // This is not used by the item renderer but must be implemented.
            return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_smb_super_fan.png");
        }
    }


      /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
     /*-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
   /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
 /* -^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^--__--^ */
/*[ENTITY MODEL]*/
    @OnlyIn(Dist.CLIENT)
    public static class ThrownSMBSuperFanModel extends EntityModel<ThrownSMBSuperFan> {
        private final ModelPart root;

        public ThrownSMBSuperFanModel(ModelPart root) {
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
        public void setupAnim(ThrownSMBSuperFan entity, float v, float v1, float v2, float v3, float v4) {/*VOIDED vanilla nonsense*/}
    }


/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
}



