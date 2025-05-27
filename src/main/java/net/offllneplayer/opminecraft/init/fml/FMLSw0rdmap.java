package net.offllneplayer.opminecraft.init.fml;

import net.minecraft.world.item.Items;
import net.offllneplayer.opminecraft.entity.sw0rd.Sw0rdMaterialMap;

public class FMLSw0rdmap {

	public static void Sw0rdMap() {
		// Register vanilla swords to the frign material map
		Sw0rdMaterialMap.WOODEN.setRegisteredItem(Items.WOODEN_SWORD);
		Sw0rdMaterialMap.STONE.setRegisteredItem(Items.STONE_SWORD);
		Sw0rdMaterialMap.IRON.setRegisteredItem(Items.IRON_SWORD);
		Sw0rdMaterialMap.GOLDEN.setRegisteredItem(Items.GOLDEN_SWORD);
		Sw0rdMaterialMap.DIAMOND.setRegisteredItem(Items.DIAMOND_SWORD);
		Sw0rdMaterialMap.NETHERITE.setRegisteredItem(Items.NETHERITE_SWORD);
	}
}
