package net.offllneplayer.opminecraft.iwe.SMBSuperFan;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.iface.DispensibleProjectile;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import java.util.List;


public class SMBSuperFanItem extends TieredItem implements DispensibleProjectile {

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
		fan.getPersistentData().putString("N4M3", stack.getHoverName().getString());
		fan.getPersistentData().putInt("D4M4G3", stack.getDamageValue());

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

