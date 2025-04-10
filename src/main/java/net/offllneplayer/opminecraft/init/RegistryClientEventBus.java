
package net.offllneplayer.opminecraft.init;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.offllneplayer.opminecraft.client.SMBSuperFan.SMBSuperFanRenderer;
import net.offllneplayer.opminecraft.client.dynamite.ThrownDynamiteStickRenderer;
import net.offllneplayer.opminecraft.client.tnt.ThrownTNTStickRenderer;

import static net.offllneplayer.opminecraft.OPMinecraft.Mod_ID;
import static net.offllneplayer.opminecraft.init.RegistryFluids.CRYING_ESSENCE;
import static net.offllneplayer.opminecraft.init.RegistryFluids.FLOWING_CRYING_ESSENCE;


public class RegistryClientEventBus {

	@EventBusSubscriber(modid = Mod_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {

			EntityRenderers.register(RegistryEntities.THROWN_DYNAMITE_STICK.get(), ThrownDynamiteStickRenderer::new);

			EntityRenderers.register(RegistryEntities.THROWN_TNT_STICK.get(), ThrownTNTStickRenderer::new);

			EntityRenderers.register(RegistryEntities.SMB_SUPER_FAN.get(), SMBSuperFanRenderer::new);

			ItemBlockRenderTypes.setRenderLayer(CRYING_ESSENCE.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_CRYING_ESSENCE.get(), RenderType.translucent());

		}
	}
}
