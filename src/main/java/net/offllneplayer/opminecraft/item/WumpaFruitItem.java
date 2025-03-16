
package net.offllneplayer.opminecraft.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.offllneplayer.opminecraft.method.crash.wumpa.PROCWumpaFruitProcedure;

public class WumpaFruitItem extends Item {
	public WumpaFruitItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(2).saturationModifier(2f).alwaysEdible().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		PROCWumpaFruitProcedure.execute(world, x, y, z, entity);
		return retval;
	}
}
