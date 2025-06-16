package net.offllneplayer.opminecraft.iwe.hatchet;


import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
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


public class HatchetItem extends TieredItem implements DispensibleProjectile {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private final HatchetMaterial material;


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public HatchetItem(HatchetMaterial material) {
		super(createTier(material), createItemProperties(material));
		this.material = material;
	}

	private static Item.Properties createItemProperties(HatchetMaterial material) {
		Item.Properties itemProperties = new Item.Properties()
			.attributes(AxeItem.createAttributes(createTier(material), material.getAttackDamage(), material.getAttackSpeed()))
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
	public HatchetMaterial getMaterial() {
		return material;
	}


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC TOOL Item OVERRIDES]*/
	private static Tier createTier(HatchetMaterial material) {
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

		ThrownHatchet hatchet = new ThrownHatchet(player, level, stack.copy());

		InteractionHand hand = player.getUsedItemHand();
		double yawRad = Math.toRadians(player.getYRot());
		double forwardX = -Math.sin(yawRad), forwardZ = Math.cos(yawRad);
		double rightX = forwardZ, rightZ = -forwardX;
		double forwardOff = 0.6;
		double lateralOff = hand == InteractionHand.MAIN_HAND ? -0.3 : 0.3;
		double spawnX = player.getX() + forwardX * forwardOff + rightX * lateralOff;
		double spawnY = player.getY() + player.getEyeHeight();
		double spawnZ = player.getZ() + forwardZ * forwardOff + rightZ * lateralOff;

		hatchet.setPos(spawnX, spawnY, spawnZ);

		hatchet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, pull * 2.2F, 0.1420F);
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