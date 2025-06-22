package net.offllneplayer.opminecraft.iwe.opsw0rd;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import net.offllneplayer.opminecraft.block.crying.essence.method.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.iwe.sw0rd.StickSword_Method;


public class OPSwordItem extends SwordItem {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[VARIABLES]*/
	private final OPSwordMaterial material;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public OPSwordItem(OPSwordMaterial material) {
			super(createTier(material), createItemProperties(material));
		this.material = material;
		}

		private static Item.Properties createItemProperties(OPSwordMaterial material) {
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
	  public OPSwordMaterial getMaterial() {
		  return material;
	  }


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC TOOL Item OVERRIDES]*/
	private static Tier createTier(OPSwordMaterial material) {
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
	public InteractionResult useOn(UseOnContext context) {
		return StickSword_Method.execute(context);
	}

	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity sourceEntity) {

		if (!sourceEntity.level().isClientSide()) {
			if (material == OPSwordMaterial.CRYING) ApplyCrying1_Method.execute(entity);
		}

		return super.hurtEnemy(stack, entity, sourceEntity);
	}
}
