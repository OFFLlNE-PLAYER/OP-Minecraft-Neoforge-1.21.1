package net.offllneplayer.opminecraft.items._item.tou;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

public class TOU_OnTick_Method {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
            FoodData foodData = _player.getFoodData();
            foodData.setFoodLevel(20);
            foodData.setSaturation(20.0F);
        }
	}
}