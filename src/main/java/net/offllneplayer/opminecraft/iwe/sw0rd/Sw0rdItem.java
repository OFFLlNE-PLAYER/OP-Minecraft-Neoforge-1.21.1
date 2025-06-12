package net.offllneplayer.opminecraft.iwe.sw0rd;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class Sw0rdItem extends SwordItem {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*[VARIABLES]*/
	private final Sw0rdMaterial material;

	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public Sw0rdItem(Sw0rdMaterial material) {
			super(createTier(material), createItemProperties(material));
		this.material = material;
		}

		private static Properties createItemProperties(Sw0rdMaterial material) {
			Properties itemProperties = new Properties()
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
	  public Sw0rdMaterial getMaterial() {
		  return material;
	  }


	 /*^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^*/
	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[BASIC TOOL Item OVERRIDES]*/
	private static Tier createTier(Sw0rdMaterial material) {
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
}
