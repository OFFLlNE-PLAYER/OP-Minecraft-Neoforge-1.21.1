
package net.offllneplayer.opminecraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CryingIngotItem extends Item {
	public CryingIngotItem() {
		super(new Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	}
}
