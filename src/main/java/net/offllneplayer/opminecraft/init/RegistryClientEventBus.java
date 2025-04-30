
package net.offllneplayer.opminecraft.init;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;

import net.offllneplayer.opminecraft.client.SMBSuperFan.SMBSuperFanRenderer;
import net.offllneplayer.opminecraft.client.crying_hatchet.CryingHatchetRenderer;
import net.offllneplayer.opminecraft.client.dynamite.ThrownDynamiteStickRenderer;
import net.offllneplayer.opminecraft.client.particle.ParticleGunbladeShot;
import net.offllneplayer.opminecraft.client.tnt.ThrownTNTStickRenderer;
import static net.offllneplayer.opminecraft.OPMinecraft.Mod_ID;
import static net.offllneplayer.opminecraft.init.RegistryFluids.CRYING_ESSENCE;
import static net.offllneplayer.opminecraft.init.RegistryFluids.FLOWING_CRYING_ESSENCE;


public class RegistryClientEventBus {

	@EventBusSubscriber(modid = Mod_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {

		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {

			ItemBlockRenderTypes.setRenderLayer( RegistryIBBI.GOLDEN_BED.get(),
					RenderType.cutoutMipped() );

		/*--------------------------------------------------------------------------------------------*/
			/*[Register Entity Renderers]*/

			EntityRenderers.register(RegistryEntities.CRYING_HATCHET.get(), CryingHatchetRenderer::new);
			EntityRenderers.register(RegistryEntities.THROWN_DYNAMITE_STICK.get(), ThrownDynamiteStickRenderer::new);
			EntityRenderers.register(RegistryEntities.THROWN_TNT_STICK.get(), ThrownTNTStickRenderer::new);
			EntityRenderers.register(RegistryEntities.SMB_SUPER_FAN.get(), SMBSuperFanRenderer::new);

		/*--------------------------------------------------------------------------------------------*/
			/*[Register Liquid Renderers]*/
			ItemBlockRenderTypes.setRenderLayer(CRYING_ESSENCE.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_CRYING_ESSENCE.get(), RenderType.translucent());

		}
	/*-----------------------------------------------------------------------------------------------------------------------*/
		/*[Register Particles]*/
		@SubscribeEvent
		public static void registerParticles(RegisterParticleProvidersEvent event) {

			event.registerSpriteSet(RegistryParticleTypes.PARTICLE_GUNBLADE_SHOT.get(), ParticleGunbladeShot::provider);
		}
	}
}
