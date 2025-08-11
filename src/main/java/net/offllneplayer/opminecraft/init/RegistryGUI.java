

package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;


import net.offllneplayer.opminecraft.blocks._block.furnaces.OPFurnaceGUI;
import net.offllneplayer.opminecraft.blocks._block.ancientchests.AncientChestGUI;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryGUI {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {

		event.register(RegistryInventory.OP_FURNACE_GUI.get(), OPFurnaceGUI::new);

		event.register(RegistryInventory.ANCIENT_CHEST_GUI.get(), AncientChestGUI::new);
	}
}
