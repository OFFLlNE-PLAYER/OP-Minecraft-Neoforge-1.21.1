package net.offllneplayer.opminecraft.iwe.gunblade;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.offllneplayer.opminecraft.block.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.entity.sw0rd.StickSw0rd_Method;
import net.offllneplayer.opminecraft.init.RegistryDataComponents;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.iwe.opsw0rd.OPSwordMaterial;

import java.util.List;


public class GunbladeItem extends SwordItem {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[VARIABLES]*/
	private final GunbladeMaterial material;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public GunbladeItem(GunbladeMaterial material) {
		super(createTier(material), createItemProperties(material));
		this.material = material;
		material.setRegisteredItem(this);
	}

	private static Item.Properties createItemProperties(GunbladeMaterial material) {
		Item.Properties itemProperties = new Item.Properties()
			.attributes(SwordItem.createAttributes(createTier(material), material.getAttackDamage(), material.getAttackSpeed()))
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
	 public GunbladeMaterial getMaterial() {
		 return material;
	 }


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC TOOL Item OVERRIDES]*/
	private static Tier createTier(GunbladeMaterial material) {
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
  /*[useOn]*/
	@Override
	public InteractionResult useOn(UseOnContext context) {
		return StickSw0rd_Method.execute(context);
	}


	 /*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
	/*x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-  */
  /*[hurtEnemy]*/
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


			if (material == GunbladeMaterial.CRYING) ApplyCrying1_Method.execute(entity);
		}

		return super.hurtEnemy(stack, entity, sourceEntity);
	}


	 /*x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-x<=-  */
	/*o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o<=-o */
  /*[interactLivingEntity]*/
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
