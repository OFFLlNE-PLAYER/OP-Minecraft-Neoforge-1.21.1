
package net.offllneplayer.opminecraft.init;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.offllneplayer.opminecraft.client.dynamite.ThrownDynamiteStickRenderer;
import net.offllneplayer.opminecraft.client.tnt.ThrownTNTStickRenderer;
import net.offllneplayer.opminecraft.fluid.CryingEssenceFluid;
import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryClientEventBus {

	public static final DeferredRegister<Fluid> FLUIDSREGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, OPMinecraft.Mod_ID);

	public static final DeferredHolder<Fluid, FlowingFluid> CRYING_ESSENCE = FLUIDSREGISTRY.register("crying_essence", CryingEssenceFluid.Source::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_CRYING_ESSENCE = FLUIDSREGISTRY.register("flowing_crying_essence", CryingEssenceFluid.Flowing::new);

	@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {

			EntityRenderers.register(RegistryEntities.THROWN_DYNAMITE_STICK.get(), ThrownDynamiteStickRenderer::new);

			EntityRenderers.register(RegistryEntities.THROWN_TNT_STICK.get(), ThrownTNTStickRenderer::new);
			
			ItemBlockRenderTypes.setRenderLayer(CRYING_ESSENCE.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_CRYING_ESSENCE.get(), RenderType.translucent());

		}
	}
}
