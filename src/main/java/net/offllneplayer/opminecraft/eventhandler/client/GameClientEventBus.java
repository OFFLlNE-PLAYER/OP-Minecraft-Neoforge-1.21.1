
package net.offllneplayer.opminecraft.eventhandler.client;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

import net.offllneplayer.opminecraft.OPMinecraft;

import net.offllneplayer.opminecraft.items._iwe.pistol.PistolItem;
import net.offllneplayer.opminecraft.items._iwe.hatchet.HatchetItem;
import net.offllneplayer.opminecraft.items._iwe.SMBSuperFan.SMBSuperFanItem;

import net.minecraft.world.item.Item;


@EventBusSubscriber(modid = OPMinecraft.Mod_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class GameClientEventBus {


private enum ZoomType { PISTOL, HATCHET, FAN, NONE }

/*-----------------------------------------------------------------------------------------------------------------------*/
	private static ZoomType zoomTypeOf(Item item) {
		if (item instanceof PistolItem) return ZoomType.PISTOL;
		if (item instanceof HatchetItem) return ZoomType.HATCHET;
		if (item instanceof SMBSuperFanItem) return ZoomType.FAN;
		return ZoomType.NONE;
	}

/*-----------------------------------------------------------------------------------------------------------------------*/
/*[Ranged weapon zoom]*/
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
	 	if (event.getPlayer().isUsingItem()) {
      Item usingItem = event.getPlayer().getUseItem().getItem();
      ZoomType type = zoomTypeOf(usingItem);
      if (type == ZoomType.NONE) return;

		int ticksUsingItem = event.getPlayer().getTicksUsingItem();
		float fovModifier = 1F;

		// Use tick-based caps per item to avoid seconds conversion
		int maxTicksOfZoom = switch (type) {
			case PISTOL -> 6;   // 0.3s
			case HATCHET -> 20; // 1.0s
			case FAN -> 20;     // 1.0s
			default -> 20;
		};

		// Normalize by ticks, clamp so it locks at cap
		float progress = Math.min(ticksUsingItem, maxTicksOfZoom) / (float) maxTicksOfZoom; // 0..1 over maxTicksOfZoom
		progress *= progress; // bow-like easing
		fovModifier *= 1.0F - progress * 0.15F; // slight zoom like vanilla bow

		event.setNewFovModifier(fovModifier);
	 }
}

/*-----------------------------------------------------------------------------------------------------------------------*/
}
