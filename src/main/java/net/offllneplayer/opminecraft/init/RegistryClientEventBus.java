
package net.offllneplayer.opminecraft.init;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;

import net.offllneplayer.opminecraft.client.particle.ParticleGunbladeShot;

import net.offllneplayer.opminecraft.iwe.sw0rd.StuckSw0rdRenderer;

import net.offllneplayer.opminecraft.iwe.pistol.PistolBulletRenderer;

import net.offllneplayer.opminecraft.iwe.gunblade.StuckGunbladeRenderer;
import net.offllneplayer.opminecraft.iwe.hatchet.ThrownHatchetRenderer;
import net.offllneplayer.opminecraft.iwe.opsw0rd.StuckOPSwordRenderer;
import net.offllneplayer.opminecraft.iwe.SMBSuperFan.ThrownSMBSuperFanRenderer;
import net.offllneplayer.opminecraft.iwe.tntstick.ThrownTNTStickRenderer;

import static net.offllneplayer.opminecraft.OPMinecraft.Mod_ID;
import static net.offllneplayer.opminecraft.init.RegistryFluids.CRYING_ESSENCE;
import static net.offllneplayer.opminecraft.init.RegistryFluids.FLOWING_CRYING_ESSENCE;


public class RegistryClientEventBus {

	@EventBusSubscriber(modid = Mod_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {

		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {

			ItemBlockRenderTypes.setRenderLayer( RegistryBIBI.GOLDEN_BED.get(), RenderType.cutoutMipped() );

		/*--------------------------------------------------------------------------------------------*/
			/*[Register Entity Renderers]*/

			EntityRenderers.register(RegistryEntities.PISTOL_BULLET.get(), PistolBulletRenderer::new);

			EntityRenderers.register(RegistryEntities.STUCK_GUNBLADE.get(), StuckGunbladeRenderer::new);
			EntityRenderers.register(RegistryEntities.STUCK_SW0RD.get(), StuckSw0rdRenderer::new);
			EntityRenderers.register(RegistryEntities.STUCK_OP_SWORD.get(), StuckOPSwordRenderer::new);

			EntityRenderers.register(RegistryEntities.THROWN_HATCHET.get(), ThrownHatchetRenderer::new);
			EntityRenderers.register(RegistryEntities.THROWN_TNT_STICK.get(), ThrownTNTStickRenderer::new);
			EntityRenderers.register(RegistryEntities.THROWN_SMB_SUPER_FAN.get(), ThrownSMBSuperFanRenderer::new);

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
