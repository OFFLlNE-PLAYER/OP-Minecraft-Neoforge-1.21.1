
package net.offllneplayer.opminecraft.item.crying;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CryingSmithingTemplateItem extends Item {
	public CryingSmithingTemplateItem() {
		super(new Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	}
}
