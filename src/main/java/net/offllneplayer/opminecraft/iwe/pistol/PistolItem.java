package net.offllneplayer.opminecraft.iwe.pistol;


import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;


public class PistolItem extends TieredItem implements DispensibleProjectile {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private final GunMaterial material;


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public PistolItem(GunMaterial material) {
		super(createTier(material), createItemProperties(material));
		this.material = material;
		material.setRegisteredItem(this);
	}

	private static Properties createItemProperties(GunMaterial material) {
		Properties itemProperties = new Properties()
			.stacksTo(1)
			.rarity(material.getRarity());

		if (material.isFireResistant()) {
			itemProperties.fireResistant();
		}

		return itemProperties;
	}


  	 /*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
	/*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
  /*[HELP]*/
	public GunMaterial getMaterial() {
		return material;
	}


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC TOOL Item OVERRIDES]*/
	private static Tier createTier(GunMaterial material) {
		return new Tier() {
			@Override
			public Ingredient getRepairIngredient() { return material.getRepairIngredient(); }
			@Override
			public int getUses() { return material.getDurability(); }
			@Override
			public float getSpeed() { return material.getMiningSpeed(); }
			@Override
			public TagKey<Block> getIncorrectBlocksForDrops() { return material.getIncorrectBlocksForDrops(); }
			@Override
			public int getEnchantmentValue() { return material.getEnchantability(); }
			@Override
			public float getAttackDamageBonus() { return 0; }
		};
	}


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[Use Item OVERRIDES]*/
	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity user) {
		return 1;
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
		 ItemStack stack = player.getItemInHand(hand);

		 if (level.isClientSide) return InteractionResultHolder.consume(stack);

		 double yawRad = Math.toRadians(player.getYRot());
		 double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
		 double rightX = forwardZ, rightZ = -forwardX;
		 double forwardOff = 0.6;
		 double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.3 : 0.3;
		 double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
		 double spawnY = player.getY() + player.getEyeHeight();
		 double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

		 Bullet bulletENT = new Bullet(player, level, stack.copy());

		 bulletENT.setPos(spawnX, spawnY, spawnZ);
		 bulletENT.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 6F, 0.420F);
		 level.addFreshEntity(bulletENT);

		 player.awardStat(Stats.ITEM_USED.get(this));
		 stack.shrink(1);

		 level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1, 1);

		 return InteractionResultHolder.success(stack);
	 }



	 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
	/* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
  /*[release Using]*/



	 /* ----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_*/
	/*-----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>----x-=>*/
  /*[as Projectile]*/
	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {

		Bullet bulletENT =  new Bullet(null, level, stack.copy());

		bulletENT.setPos(pos.x(), pos.y(), pos.z());

		float speed = getDispenseSpeed();
		bulletENT.setDeltaMovement(direction.getStepX() * speed, direction.getStepY() * speed + 0.1F, direction.getStepZ() * speed);

		float yRot = switch (direction) {
			case NORTH -> 0F;
			case SOUTH -> 180F;
			case WEST -> 90F;
			case EAST -> 270F;
			default -> 0F;
		};
		bulletENT.setYRot(yRot);

		return bulletENT;
	}
}