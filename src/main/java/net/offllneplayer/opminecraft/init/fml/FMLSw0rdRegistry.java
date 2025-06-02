package net.offllneplayer.opminecraft.init.fml;

import net.minecraft.world.item.Items;
import net.offllneplayer.opminecraft.entity.sw0rd.Sw0rdMaterial;

public class FMLSw0rdRegistry {

	public static void VanillaSw0rds() {
		Sw0rdMaterial.WOODEN.setRegisteredItem(Items.WOODEN_SWORD);
		Sw0rdMaterial.STONE.setRegisteredItem(Items.STONE_SWORD);
		Sw0rdMaterial.IRON.setRegisteredItem(Items.IRON_SWORD);
		Sw0rdMaterial.GOLDEN.setRegisteredItem(Items.GOLDEN_SWORD);
		Sw0rdMaterial.DIAMOND.setRegisteredItem(Items.DIAMOND_SWORD);
		Sw0rdMaterial.NETHERITE.setRegisteredItem(Items.NETHERITE_SWORD);
	}
}
