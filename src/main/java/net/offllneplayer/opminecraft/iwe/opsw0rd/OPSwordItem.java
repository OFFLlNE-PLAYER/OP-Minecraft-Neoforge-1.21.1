package net.offllneplayer.opminecraft.iwe.opsw0rd;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import net.offllneplayer.opminecraft.block.crying.essence.effect.ApplyCrying1_Method;
import net.offllneplayer.opminecraft.entity.sw0rd.StickSw0rd_Method;


public class OPSwordItem extends SwordItem {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
  /*[VARIABLES]*/
	private final OPSwordMaterialMap.OPSwordMaterial material;
	private final float damage;
	private final float attackSpeed;


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*/
  /*[BUILDER]*/
	public OPSwordItem(OPSwordMaterialMap.OPSwordMaterial material, float damage, float attackSpeed) {
		super(createTier(material), createItemProperties(material, damage, attackSpeed));

		// Set the instance fields
		this.material = material;
		this.damage = damage;
		this.attackSpeed = attackSpeed;

		// Associate this item with the material
		material.setRegisteredItem(this);
	}

	private static Properties createItemProperties(OPSwordMaterialMap.OPSwordMaterial material, float damage, float attackSpeed) {
		Properties itemProperties = new Properties()
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
	private static Tier createTier(OPSwordMaterialMap.OPSwordMaterial material) {
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

	public OPSwordMaterialMap.OPSwordMaterial getMaterial() {
		return material;
	}


	 /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/
  /*[Use Item OVERRIDES]*/
	@Override
	public InteractionResult useOn(UseOnContext context) {
		return StickSw0rd_Method.execute(context);
	}

	/*<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-<=-*/

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity sourceEntity) {

		if (!sourceEntity.level().isClientSide()) {
			if (material == OPSwordMaterialMap.CRYING) ApplyCrying1_Method.execute(entity);
		}

		return super.hurtEnemy(stack, entity, sourceEntity);
	}
}
