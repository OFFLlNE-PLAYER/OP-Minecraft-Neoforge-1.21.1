
package net.offllneplayer.opminecraft.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import net.offllneplayer.opminecraft.init.RegistryDataComponents;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.method.gunblade.GunbladeShot_Method;
import net.offllneplayer.opminecraft.method.gunblade.StickGunblade_Method;

import java.util.List;

public class GunbladeItem extends SwordItem {
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 420;
		}

		@Override
		public float getSpeed() {
			return 10f;
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
			return Ingredient.of();
		}
	};

	public GunbladeItem() {
		super(TOOL_TIER, new Item.Properties().attributes(SwordItem.createAttributes(TOOL_TIER, 7f, -2f)).fireResistant());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.opminecraft.gunblade.description_0"));
		list.add(Component.translatable("item.opminecraft.gunblade.description_1"));
		list.add(Component.translatable("item.opminecraft.gunblade.description_2"));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {

		Player player = context.getPlayer();

		if (player == null || !player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}

		StickGunblade_Method.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), player);
		return InteractionResult.SUCCESS;
	}

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
