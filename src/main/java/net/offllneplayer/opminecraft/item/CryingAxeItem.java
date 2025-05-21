
package net.offllneplayer.opminecraft.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.method.crying.essence.effect.ApplyCrying1_Method;


public class CryingAxeItem extends AxeItem {
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
			return Ingredient.of(new ItemStack(RegistryBIBI.CRYING_INGOT.get()));
		}
	};

	public CryingAxeItem() {
		super(TOOL_TIER, new Properties()
				.attributes(AxeItem.createAttributes(TOOL_TIER, 9.5F, -2.9F))
				.rarity(Rarity.EPIC));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);

		ApplyCrying1_Method.execute(entity);

		return retval;
	}
}
