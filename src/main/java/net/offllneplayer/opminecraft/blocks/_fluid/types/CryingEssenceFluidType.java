
package net.offllneplayer.opminecraft.blocks._fluid.types;

import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.Rarity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;

import net.offllneplayer.opminecraft.init.RegistryFluids;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CryingEssenceFluidType extends FluidType {
	public CryingEssenceFluidType() {
		super(Properties.create()
				.fallDistanceModifier(0F)
				.canExtinguish(true)
				.supportsBoating(true)
				.canHydrate(true)
				.motionScale(0.01420D)
				.lightLevel(10)
				.density(1420)
				.rarity(Rarity.EPIC)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
				.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}

	@SubscribeEvent
	public static void registerFluidTypeExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL_TEXTURE = ResourceLocation.parse("opminecraft:block/text_crying_essence_still");
			private static final ResourceLocation FLOWING_TEXTURE = ResourceLocation.parse("opminecraft:block/text_crying_essence_flowing");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL_TEXTURE;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOWING_TEXTURE;
			}
		}, RegistryFluids.CRYING_ESSENCE_TYPE.get());
	}
}
