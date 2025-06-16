

package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;


import net.offllneplayer.opminecraft.client.gui.furnaces.*;
import net.offllneplayer.opminecraft.client.gui.lootchest.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryGUI {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(RegistryInventory.COPPER_FURNACE_GUI.get(), CopperFurnaceGUI::new);
		event.register(RegistryInventory.IRON_FURNACE_GUI.get(), IronFurnaceGUI::new);
		event.register(RegistryInventory.GOLD_FURNACE_GUI.get(), GoldFurnaceGUI::new);
		event.register(RegistryInventory.DIAMOND_FURNACE_GUI.get(), DiamondFurnaceGUI::new);
		event.register(RegistryInventory.NETHERITE_FURNACE_GUI.get(), NetheriteFurnaceGUI::new);

		event.register(RegistryInventory.COPPER_LOOT_CHEST_GUI.get(), CopperLootChestGUI::new);
		event.register(RegistryInventory.IRON_LOOT_CHEST_GUI.get(), IronLootChestGUI::new);
		event.register(RegistryInventory.GOLD_LOOT_CHEST_GUI.get(), GoldLootChestGUI::new);
		event.register(RegistryInventory.DIAMOND_LOOT_CHEST_GUI.get(), DiamondLootChestGUI::new);
		event.register(RegistryInventory.NETHERITE_LOOT_CHEST_GUI.get(), NetheriteLootChestGUI::new);
		event.register(RegistryInventory.N_LOOT_CHEST_GUI.get(), NLootChestGUI::new);
	}
}
