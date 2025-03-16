
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.offllneplayer.opminecraft.client.particle.PARTICLEGunbladeShotParticle;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(RegistryParticleTypes.PARTICLE_GUNBLADE_SHOT.get(), PARTICLEGunbladeShotParticle::provider);
	}
}
