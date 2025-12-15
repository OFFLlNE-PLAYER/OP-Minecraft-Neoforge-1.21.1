package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.offllneplayer.opminecraft.OPMinecraft;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RegistryCreative {

    public static final DeferredRegister<CreativeModeTab> CREATIVETABSREGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OPMinecraft.Mod_ID);

    @SubscribeEvent
        public static void addCreative(BuildCreativeModeTabContentsEvent event) {


// ~~~BUILDING BLOCKS TAB~~~
            if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) { // ~~~BUILDING BLOCKS TAB~~~

					event.accept(RegistryBIBI.BLOCK_OF_TEST);

					event.accept(RegistryBIBI.BLOCK_OF_CHARCOAL);
					event.accept(RegistryBIBI.CHARCOAL_BUTTON);
					event.accept(RegistryBIBI.CHARCOAL_FENCE);
					event.accept(RegistryBIBI.CHARCOAL_FENCE_GATE);
					event.accept(RegistryBIBI.CHARCOAL_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CHARCOAL_SLAB);
					event.accept(RegistryBIBI.CHARCOAL_STAIRS);
					event.accept(RegistryBIBI.CHARCOAL_TRAPDOOR);
					event.accept(RegistryBIBI.CHARCOAL_WALL);

					event.accept(RegistryBIBI.CHARCOAL_BRICKS);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_BUTTON);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_FENCE);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_FENCE_GATE);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_SLAB);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_STAIRS);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_TRAPDOOR);
					event.accept(RegistryBIBI.CHARCOAL_BRICK_WALL);

					event.accept(RegistryBIBI.CHARCOAL_TILES);
					event.accept(RegistryBIBI.CHARCOAL_TILE_BUTTON);
					event.accept(RegistryBIBI.CHARCOAL_TILE_FENCE);
					event.accept(RegistryBIBI.CHARCOAL_TILE_FENCE_GATE);
					event.accept(RegistryBIBI.CHARCOAL_TILE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CHARCOAL_TILE_SLAB);
					event.accept(RegistryBIBI.CHARCOAL_TILE_STAIRS);
					event.accept(RegistryBIBI.CHARCOAL_TILE_TRAPDOOR);
					event.accept(RegistryBIBI.CHARCOAL_TILE_WALL);

					event.accept(RegistryBIBI.CHISELED_DIAMOND);
					event.accept(RegistryBIBI.CHISELED_GOLD);
					event.accept(RegistryBIBI.CHISELED_IRON);
					event.accept(RegistryBIBI.CHISELED_NETHERITE);

					event.accept(RegistryBIBI.CRYING_BRICKS);
					event.accept(RegistryBIBI.CRYING_BRICK_BUTTON);
					event.accept(RegistryBIBI.CRYING_BRICK_FENCE);
					event.accept(RegistryBIBI.CRYING_BRICK_FENCE_GATE);
					event.accept(RegistryBIBI.CRYING_BRICK_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CRYING_BRICK_SLAB);
					event.accept(RegistryBIBI.CRYING_BRICK_STAIRS);
					event.accept(RegistryBIBI.CRYING_BRICK_TRAPDOOR);
					event.accept(RegistryBIBI.CRYING_BRICK_WALL);

					event.accept(RegistryBIBI.CRYING_TILES);
					event.accept(RegistryBIBI.CRYING_TILE_BUTTON);
					event.accept(RegistryBIBI.CRYING_TILE_FENCE);
					event.accept(RegistryBIBI.CRYING_TILE_FENCE_GATE);
					event.accept(RegistryBIBI.CRYING_TILE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CRYING_TILE_SLAB);
					event.accept(RegistryBIBI.CRYING_TILE_STAIRS);
					event.accept(RegistryBIBI.CRYING_TILE_TRAPDOOR);
					event.accept(RegistryBIBI.CRYING_TILE_WALL);

					event.accept(RegistryBIBI.SLATE);
					event.accept(RegistryBIBI.SLATE_BUTTON);
					event.accept(RegistryBIBI.SLATE_FENCE);
					event.accept(RegistryBIBI.SLATE_FENCE_GATE);
					event.accept(RegistryBIBI.SLATE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SLATE_SLAB);
					event.accept(RegistryBIBI.SLATE_STAIRS);
					event.accept(RegistryBIBI.SLATE_TRAPDOOR);
					event.accept(RegistryBIBI.SLATE_WALL);

					event.accept(RegistryBIBI.COBBLED_SLATE);
					event.accept(RegistryBIBI.COBBLED_SLATE_BUTTON);
					event.accept(RegistryBIBI.COBBLED_SLATE_FENCE);
					event.accept(RegistryBIBI.COBBLED_SLATE_FENCE_GATE);
					event.accept(RegistryBIBI.COBBLED_SLATE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.COBBLED_SLATE_SLAB);
					event.accept(RegistryBIBI.COBBLED_SLATE_STAIRS);
					event.accept(RegistryBIBI.COBBLED_SLATE_TRAPDOOR);
					event.accept(RegistryBIBI.COBBLED_SLATE_WALL);

					event.accept(RegistryBIBI.SMOOTH_SLATE);
					event.accept(RegistryBIBI.SMOOTH_SLATE_BUTTON);
					event.accept(RegistryBIBI.SMOOTH_SLATE_FENCE);
					event.accept(RegistryBIBI.SMOOTH_SLATE_FENCE_GATE);
					event.accept(RegistryBIBI.SMOOTH_SLATE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SMOOTH_SLATE_SLAB);
					event.accept(RegistryBIBI.SMOOTH_SLATE_STAIRS);
					event.accept(RegistryBIBI.SMOOTH_SLATE_TRAPDOOR);
					event.accept(RegistryBIBI.SMOOTH_SLATE_WALL);

					event.accept(RegistryBIBI.SLATE_BRICKS);
					event.accept(RegistryBIBI.SLATE_BRICK_BUTTON);
					event.accept(RegistryBIBI.SLATE_BRICK_FENCE);
					event.accept(RegistryBIBI.SLATE_BRICK_FENCE_GATE);
					event.accept(RegistryBIBI.SLATE_BRICK_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SLATE_BRICK_SLAB);
					event.accept(RegistryBIBI.SLATE_BRICK_STAIRS);
					event.accept(RegistryBIBI.SLATE_BRICK_TRAPDOOR);
					event.accept(RegistryBIBI.SLATE_BRICK_WALL);

					event.accept(RegistryBIBI.SLATE_TILES);
					event.accept(RegistryBIBI.SLATE_TILE_BUTTON);
					event.accept(RegistryBIBI.SLATE_TILE_FENCE);
					event.accept(RegistryBIBI.SLATE_TILE_FENCE_GATE);
					event.accept(RegistryBIBI.SLATE_TILE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SLATE_TILE_SLAB);
					event.accept(RegistryBIBI.SLATE_TILE_STAIRS);
					event.accept(RegistryBIBI.SLATE_TILE_TRAPDOOR);
					event.accept(RegistryBIBI.SLATE_TILE_WALL);

					event.accept(RegistryBIBI.BLOCK_OF_ONYX);
					event.accept(RegistryBIBI.BLOCK_OF_TITANIUM);
					event.accept(RegistryBIBI.ONYX_BLOCK);
					event.accept(RegistryBIBI.TITANIUM_BLOCK);

					event.accept(RegistryBIBI.STONE_TILES);
					event.accept(RegistryBIBI.STONE_TILE_BUTTON);
					event.accept(RegistryBIBI.STONE_TILE_FENCE);
					event.accept(RegistryBIBI.STONE_TILE_FENCE_GATE);
					event.accept(RegistryBIBI.STONE_TILE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.STONE_TILE_SLAB);
					event.accept(RegistryBIBI.STONE_TILE_STAIRS);
					event.accept(RegistryBIBI.STONE_TILE_TRAPDOOR);
					event.accept(RegistryBIBI.STONE_TILE_WALL);

					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_BUTTON);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_FENCE);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_FENCE_GATE);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_SLAB);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_STAIRS);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_TRAPDOOR);
					event.accept(RegistryBIBI.SMOOTH_DEEPSLATE_WALL);

					
					// Alexandrite blocks
					event.accept(RegistryBIBI.ALEXANDRITE_BUTTON);
					event.accept(RegistryBIBI.ALEXANDRITE_FENCE);
					event.accept(RegistryBIBI.ALEXANDRITE_FENCE_GATE);
					event.accept(RegistryBIBI.ALEXANDRITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.ALEXANDRITE_SLAB);
					event.accept(RegistryBIBI.ALEXANDRITE_STAIRS);
					event.accept(RegistryBIBI.ALEXANDRITE_TRAPDOOR);
					event.accept(RegistryBIBI.ALEXANDRITE_WALL);

					// Andalusite blocks
					event.accept(RegistryBIBI.ANDALUSITE_BUTTON);
					event.accept(RegistryBIBI.ANDALUSITE_FENCE);
					event.accept(RegistryBIBI.ANDALUSITE_FENCE_GATE);
					event.accept(RegistryBIBI.ANDALUSITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.ANDALUSITE_SLAB);
					event.accept(RegistryBIBI.ANDALUSITE_STAIRS);
					event.accept(RegistryBIBI.ANDALUSITE_TRAPDOOR);
					event.accept(RegistryBIBI.ANDALUSITE_WALL);

					// Aquamarine blocks
					event.accept(RegistryBIBI.AQUAMARINE_BUTTON);
					event.accept(RegistryBIBI.AQUAMARINE_FENCE);
					event.accept(RegistryBIBI.AQUAMARINE_FENCE_GATE);
					event.accept(RegistryBIBI.AQUAMARINE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.AQUAMARINE_SLAB);
					event.accept(RegistryBIBI.AQUAMARINE_STAIRS);
					event.accept(RegistryBIBI.AQUAMARINE_TRAPDOOR);
					event.accept(RegistryBIBI.AQUAMARINE_WALL);

					// Chrysoberyl blocks
					event.accept(RegistryBIBI.CHRYSOBERYL_BUTTON);
					event.accept(RegistryBIBI.CHRYSOBERYL_FENCE);
					event.accept(RegistryBIBI.CHRYSOBERYL_FENCE_GATE);
					event.accept(RegistryBIBI.CHRYSOBERYL_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CHRYSOBERYL_SLAB);
					event.accept(RegistryBIBI.CHRYSOBERYL_STAIRS);
					event.accept(RegistryBIBI.CHRYSOBERYL_TRAPDOOR);
					event.accept(RegistryBIBI.CHRYSOBERYL_WALL);

					// Corundum blocks
					event.accept(RegistryBIBI.CORUNDUM_BUTTON);
					event.accept(RegistryBIBI.CORUNDUM_FENCE);
					event.accept(RegistryBIBI.CORUNDUM_FENCE_GATE);
					event.accept(RegistryBIBI.CORUNDUM_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CORUNDUM_SLAB);
					event.accept(RegistryBIBI.CORUNDUM_STAIRS);
					event.accept(RegistryBIBI.CORUNDUM_TRAPDOOR);
					event.accept(RegistryBIBI.CORUNDUM_WALL);

					// Cymophane blocks
					event.accept(RegistryBIBI.CYMOPHANE_BUTTON);
					event.accept(RegistryBIBI.CYMOPHANE_FENCE);
					event.accept(RegistryBIBI.CYMOPHANE_FENCE_GATE);
					event.accept(RegistryBIBI.CYMOPHANE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.CYMOPHANE_SLAB);
					event.accept(RegistryBIBI.CYMOPHANE_STAIRS);
					event.accept(RegistryBIBI.CYMOPHANE_TRAPDOOR);
					event.accept(RegistryBIBI.CYMOPHANE_WALL);

					// Diamond blocks
					event.accept(RegistryBIBI.DIAMOND_BUTTON);
					event.accept(RegistryBIBI.DIAMOND_FENCE);
					event.accept(RegistryBIBI.DIAMOND_FENCE_GATE);
					event.accept(RegistryBIBI.DIAMOND_PRESSURE_PLATE);
					event.accept(RegistryBIBI.DIAMOND_SLAB);
					event.accept(RegistryBIBI.DIAMOND_STAIRS);
					event.accept(RegistryBIBI.DIAMOND_TRAPDOOR);
					event.accept(RegistryBIBI.DIAMOND_WALL);

					// Dragonite blocks
					event.accept(RegistryBIBI.DRAGONITE_BUTTON);
					event.accept(RegistryBIBI.DRAGONITE_FENCE);
					event.accept(RegistryBIBI.DRAGONITE_FENCE_GATE);
					event.accept(RegistryBIBI.DRAGONITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.DRAGONITE_SLAB);
					event.accept(RegistryBIBI.DRAGONITE_STAIRS);
					event.accept(RegistryBIBI.DRAGONITE_TRAPDOOR);
					event.accept(RegistryBIBI.DRAGONITE_WALL);

					// Emerald blocks
					event.accept(RegistryBIBI.EMERALD_BUTTON);
					event.accept(RegistryBIBI.EMERALD_FENCE);
					event.accept(RegistryBIBI.EMERALD_FENCE_GATE);
					event.accept(RegistryBIBI.EMERALD_PRESSURE_PLATE);
					event.accept(RegistryBIBI.EMERALD_SLAB);
					event.accept(RegistryBIBI.EMERALD_STAIRS);
					event.accept(RegistryBIBI.EMERALD_TRAPDOOR);
					event.accept(RegistryBIBI.EMERALD_WALL);

					// Jadeite blocks
					event.accept(RegistryBIBI.JADEITE_BUTTON);
					event.accept(RegistryBIBI.JADEITE_FENCE);
					event.accept(RegistryBIBI.JADEITE_FENCE_GATE);
					event.accept(RegistryBIBI.JADEITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.JADEITE_SLAB);
					event.accept(RegistryBIBI.JADEITE_STAIRS);
					event.accept(RegistryBIBI.JADEITE_TRAPDOOR);
					event.accept(RegistryBIBI.JADEITE_WALL);

					// Opalite blocks
					event.accept(RegistryBIBI.OPALITE_BUTTON);
					event.accept(RegistryBIBI.OPALITE_FENCE);
					event.accept(RegistryBIBI.OPALITE_FENCE_GATE);
					event.accept(RegistryBIBI.OPALITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.OPALITE_SLAB);
					event.accept(RegistryBIBI.OPALITE_STAIRS);
					event.accept(RegistryBIBI.OPALITE_TRAPDOOR);
					event.accept(RegistryBIBI.OPALITE_WALL);

					// Padparadscha blocks
					event.accept(RegistryBIBI.PADPARADSCHA_BUTTON);
					event.accept(RegistryBIBI.PADPARADSCHA_FENCE);
					event.accept(RegistryBIBI.PADPARADSCHA_FENCE_GATE);
					event.accept(RegistryBIBI.PADPARADSCHA_PRESSURE_PLATE);
					event.accept(RegistryBIBI.PADPARADSCHA_SLAB);
					event.accept(RegistryBIBI.PADPARADSCHA_STAIRS);
					event.accept(RegistryBIBI.PADPARADSCHA_TRAPDOOR);
					event.accept(RegistryBIBI.PADPARADSCHA_WALL);

					// Ruby blocks
					event.accept(RegistryBIBI.RUBY_BUTTON);
					event.accept(RegistryBIBI.RUBY_FENCE);
					event.accept(RegistryBIBI.RUBY_FENCE_GATE);
					event.accept(RegistryBIBI.RUBY_PRESSURE_PLATE);
					event.accept(RegistryBIBI.RUBY_SLAB);
					event.accept(RegistryBIBI.RUBY_STAIRS);
					event.accept(RegistryBIBI.RUBY_TRAPDOOR);
					event.accept(RegistryBIBI.RUBY_WALL);

					// Sapphire blocks
					event.accept(RegistryBIBI.SAPPHIRE_BUTTON);
					event.accept(RegistryBIBI.SAPPHIRE_FENCE);
					event.accept(RegistryBIBI.SAPPHIRE_FENCE_GATE);
					event.accept(RegistryBIBI.SAPPHIRE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SAPPHIRE_SLAB);
					event.accept(RegistryBIBI.SAPPHIRE_STAIRS);
					event.accept(RegistryBIBI.SAPPHIRE_TRAPDOOR);
					event.accept(RegistryBIBI.SAPPHIRE_WALL);

					// Scapolite blocks
					event.accept(RegistryBIBI.SCAPOLITE_BUTTON);
					event.accept(RegistryBIBI.SCAPOLITE_FENCE);
					event.accept(RegistryBIBI.SCAPOLITE_FENCE_GATE);
					event.accept(RegistryBIBI.SCAPOLITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.SCAPOLITE_SLAB);
					event.accept(RegistryBIBI.SCAPOLITE_STAIRS);
					event.accept(RegistryBIBI.SCAPOLITE_TRAPDOOR);
					event.accept(RegistryBIBI.SCAPOLITE_WALL);

					// Staurolite blocks
					event.accept(RegistryBIBI.STAUROLITE_BUTTON);
					event.accept(RegistryBIBI.STAUROLITE_FENCE);
					event.accept(RegistryBIBI.STAUROLITE_FENCE_GATE);
					event.accept(RegistryBIBI.STAUROLITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.STAUROLITE_SLAB);
					event.accept(RegistryBIBI.STAUROLITE_STAIRS);
					event.accept(RegistryBIBI.STAUROLITE_TRAPDOOR);
					event.accept(RegistryBIBI.STAUROLITE_WALL);

					//Tanzanite blocks
					event.accept(RegistryBIBI.TANZANITE_BUTTON);
					event.accept(RegistryBIBI.TANZANITE_FENCE);
					event.accept(RegistryBIBI.TANZANITE_FENCE_GATE);
					event.accept(RegistryBIBI.TANZANITE_PRESSURE_PLATE);
					event.accept(RegistryBIBI.TANZANITE_SLAB);
					event.accept(RegistryBIBI.TANZANITE_STAIRS);
					event.accept(RegistryBIBI.TANZANITE_TRAPDOOR);
					event.accept(RegistryBIBI.TANZANITE_WALL);

            }

// ~~~COLORED BLOCKS TAB~~~
            if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {  // ~~~COLORED BLOCKS TAB~~~

					event.accept(RegistryBIBI.GOLDEN_BED);

					event.accept(RegistryBIBI.GOLDEN_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_RED.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.IRON_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_RED.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.DIAMOND_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_RED.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.NETHERITE_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_RED.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.ONYX_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_RED.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.TITAN_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_RED.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_YELLOW.get());


			}

// ~~~NATURAL BLOCKS TAB~~~
            if (event.getTabKey()==CreativeModeTabs.NATURAL_BLOCKS) { // ~~~NATURAL BLOCKS TAB~~~

                event.accept(RegistryBIBI.FLOWERING_PITCHER_PLANT);
                event.accept(RegistryBIBI.WUMPA_PLANT);

            // ores
            event.accept(RegistryBIBI.DEEPSLATE_ONYX_ORE);
            event.accept(RegistryBIBI.DEEPSLATE_TITANIUM_ORE);
					 
            event.accept(RegistryBIBI.DEEPSLATE_ALEXANDRITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_AMETHYST_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_ANDALUSITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_AQUAMARINE_ORE);
            event.accept(RegistryBIBI.DEEPSLATE_CHRYSOBERYL_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_CORUNDUM_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_CYMOPHANE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_DRAGONITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_JADEITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_OPALITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_PADPARADSCHA_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_RUBY_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_SAPPHIRE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_SCAPOLITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_STAUROLITE_ORE);
				event.accept(RegistryBIBI.DEEPSLATE_TANZANITE_ORE);


                // Alexandrite geode
                event.accept(RegistryBIBI.ALEXANDRITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_ALEXANDRITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_ALEXANDRITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_ALEXANDRITE_BUD);
                event.accept(RegistryBIBI.LARGE_ALEXANDRITE_BUD);
                event.accept(RegistryBIBI.ALEXANDRITE_CLUSTER);

                // Andalusite geode
                event.accept(RegistryBIBI.ANDALUSITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_ANDALUSITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_ANDALUSITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_ANDALUSITE_BUD);
                event.accept(RegistryBIBI.LARGE_ANDALUSITE_BUD);
                event.accept(RegistryBIBI.ANDALUSITE_CLUSTER);

                // Aquamarine geode
                event.accept(RegistryBIBI.AQUAMARINE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_AQUAMARINE_BLOCK);
                event.accept(RegistryBIBI.SMALL_AQUAMARINE_BUD);
                event.accept(RegistryBIBI.MEDIUM_AQUAMARINE_BUD);
                event.accept(RegistryBIBI.LARGE_AQUAMARINE_BUD);
                event.accept(RegistryBIBI.AQUAMARINE_CLUSTER);

                // Chrysoberyl geode
                event.accept(RegistryBIBI.CHRYSOBERYL_BLOCK);
                event.accept(RegistryBIBI.BUDDING_CHRYSOBERYL_BLOCK);
                event.accept(RegistryBIBI.SMALL_CHRYSOBERYL_BUD);
                event.accept(RegistryBIBI.MEDIUM_CHRYSOBERYL_BUD);
                event.accept(RegistryBIBI.LARGE_CHRYSOBERYL_BUD);
                event.accept(RegistryBIBI.CHRYSOBERYL_CLUSTER);

                // Corundum geode
                event.accept(RegistryBIBI.CORUNDUM_BLOCK);
                event.accept(RegistryBIBI.BUDDING_CORUNDUM_BLOCK);
                event.accept(RegistryBIBI.SMALL_CORUNDUM_BUD);
                event.accept(RegistryBIBI.MEDIUM_CORUNDUM_BUD);
                event.accept(RegistryBIBI.LARGE_CORUNDUM_BUD);
                event.accept(RegistryBIBI.CORUNDUM_CLUSTER);

                // Cymophane geode
                event.accept(RegistryBIBI.CYMOPHANE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_CYMOPHANE_BLOCK);
                event.accept(RegistryBIBI.SMALL_CYMOPHANE_BUD);
                event.accept(RegistryBIBI.MEDIUM_CYMOPHANE_BUD);
                event.accept(RegistryBIBI.LARGE_CYMOPHANE_BUD);
                event.accept(RegistryBIBI.CYMOPHANE_CLUSTER);

                // Diamond geode
                event.accept(RegistryBIBI.DIAMOND_BLOCK);
                event.accept(RegistryBIBI.BUDDING_DIAMOND_BLOCK);
                event.accept(RegistryBIBI.SMALL_DIAMOND_BUD);
                event.accept(RegistryBIBI.MEDIUM_DIAMOND_BUD);
                event.accept(RegistryBIBI.LARGE_DIAMOND_BUD);
                event.accept(RegistryBIBI.DIAMOND_CLUSTER);

                // Dragonite geode
                event.accept(RegistryBIBI.DRAGONITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_DRAGONITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_DRAGONITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_DRAGONITE_BUD);
                event.accept(RegistryBIBI.LARGE_DRAGONITE_BUD);
                event.accept(RegistryBIBI.DRAGONITE_CLUSTER);

                // Emerald geode
                event.accept(RegistryBIBI.EMERALD_BLOCK);
                event.accept(RegistryBIBI.BUDDING_EMERALD_BLOCK);
                event.accept(RegistryBIBI.SMALL_EMERALD_BUD);
                event.accept(RegistryBIBI.MEDIUM_EMERALD_BUD);
                event.accept(RegistryBIBI.LARGE_EMERALD_BUD);
                event.accept(RegistryBIBI.EMERALD_CLUSTER);

                // Jadeite geode
                event.accept(RegistryBIBI.JADEITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_JADEITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_JADEITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_JADEITE_BUD);
                event.accept(RegistryBIBI.LARGE_JADEITE_BUD);
                event.accept(RegistryBIBI.JADEITE_CLUSTER);

                // Opalite geode
                event.accept(RegistryBIBI.OPALITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_OPALITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_OPALITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_OPALITE_BUD);
                event.accept(RegistryBIBI.LARGE_OPALITE_BUD);
                event.accept(RegistryBIBI.OPALITE_CLUSTER);

                // Padparadscha geode
                event.accept(RegistryBIBI.PADPARADSCHA_BLOCK);
                event.accept(RegistryBIBI.BUDDING_PADPARADSCHA_BLOCK);
                event.accept(RegistryBIBI.SMALL_PADPARADSCHA_BUD);
                event.accept(RegistryBIBI.MEDIUM_PADPARADSCHA_BUD);
                event.accept(RegistryBIBI.LARGE_PADPARADSCHA_BUD);
                event.accept(RegistryBIBI.PADPARADSCHA_CLUSTER);

                // Ruby geode
                event.accept(RegistryBIBI.RUBY_BLOCK);
                event.accept(RegistryBIBI.BUDDING_RUBY_BLOCK);
                event.accept(RegistryBIBI.SMALL_RUBY_BUD);
                event.accept(RegistryBIBI.MEDIUM_RUBY_BUD);
                event.accept(RegistryBIBI.LARGE_RUBY_BUD);
                event.accept(RegistryBIBI.RUBY_CLUSTER);

                // Sapphire geode
                event.accept(RegistryBIBI.SAPPHIRE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_SAPPHIRE_BLOCK);
                event.accept(RegistryBIBI.SMALL_SAPPHIRE_BUD);
                event.accept(RegistryBIBI.MEDIUM_SAPPHIRE_BUD);
                event.accept(RegistryBIBI.LARGE_SAPPHIRE_BUD);
                event.accept(RegistryBIBI.SAPPHIRE_CLUSTER);

                // Scapolite geode
                event.accept(RegistryBIBI.SCAPOLITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_SCAPOLITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_SCAPOLITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_SCAPOLITE_BUD);
                event.accept(RegistryBIBI.LARGE_SCAPOLITE_BUD);
                event.accept(RegistryBIBI.SCAPOLITE_CLUSTER);

                // Staurolite geode
                event.accept(RegistryBIBI.STAUROLITE_BLOCK);
                event.accept(RegistryBIBI.BUDDING_STAUROLITE_BLOCK);
                event.accept(RegistryBIBI.SMALL_STAUROLITE_BUD);
                event.accept(RegistryBIBI.MEDIUM_STAUROLITE_BUD);
                event.accept(RegistryBIBI.LARGE_STAUROLITE_BUD);
                event.accept(RegistryBIBI.STAUROLITE_CLUSTER);

					//Tanzanite geode
					event.accept(RegistryBIBI.TANZANITE_BLOCK);
					event.accept(RegistryBIBI.BUDDING_TANZANITE_BLOCK);
					event.accept(RegistryBIBI.SMALL_TANZANITE_BUD);
					event.accept(RegistryBIBI.MEDIUM_TANZANITE_BUD);
					event.accept(RegistryBIBI.LARGE_TANZANITE_BUD);
					event.accept(RegistryBIBI.TANZANITE_CLUSTER);
            }

// ~~~FUNCTIONAL BLOCKS TAB~~~
            if (event.getTabKey()==CreativeModeTabs.FUNCTIONAL_BLOCKS) { // ~~~FUNCTIONAL BLOCKS TAB~~~

					event.accept(RegistryBIBI.ACACIA_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.ACACIA_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.BAMBOO_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BAMBOO_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.BIRCH_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.BIRCH_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.CHERRY_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CHERRY_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.DARK_OAK_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.DARK_OAK_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.JUNGLE_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.JUNGLE_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.MANGROVE_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.MANGROVE_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.OAK_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.OAK_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.SPRUCE_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.SPRUCE_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.CRIMSON_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.CRIMSON_NETHERITE_TRIM_ANCIENT_CHEST.get());

					event.accept(RegistryBIBI.WARPED_COPPER_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_AMETHYST_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_IRON_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_LAPIS_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_GOLD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_EMERALD_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_REDSTONE_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_DIAMOND_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_QUARTZ_TRIM_ANCIENT_CHEST.get());
					event.accept(RegistryBIBI.WARPED_NETHERITE_TRIM_ANCIENT_CHEST.get());


					event.accept(RegistryBIBI.COPPER_FURNACE.get());
					event.accept(RegistryBIBI.DIAMOND_FURNACE.get());
					event.accept(RegistryBIBI.GOLD_FURNACE.get());
					event.accept(RegistryBIBI.IRON_FURNACE.get());
					event.accept(RegistryBIBI.NETHERITE_FURNACE.get());

					event.accept(RegistryBIBI.GOLDEN_BED);


					event.accept(RegistryBIBI.GOLDEN_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_RED.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.GOLDEN_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.IRON_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_RED.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.IRON_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.DIAMOND_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_RED.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.DIAMOND_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.NETHERITE_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_RED.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.NETHERITE_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.ONYX_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_RED.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.ONYX_OPLAMP_YELLOW.get());

					event.accept(RegistryBIBI.TITAN_OPLAMP_BLACK.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_BLUE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_BROWN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_CYAN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_GOLDEN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_GRAY.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_GREEN.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_LIGHT_BLUE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_LIGHT_GRAY.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_LIME.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_MAGENTA.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_ORANGE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_PINK.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_PURPLE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_RED.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_WHITE.get());
					event.accept(RegistryBIBI.TITAN_OPLAMP_YELLOW.get());
            }

// ~~~REDSTONE BLOCKS TAB~~~
            if (event.getTabKey()==CreativeModeTabs.REDSTONE_BLOCKS) { // ~~~REDSTONE BLOCKS TAB~~~

				//Redstone blocks go here ~(haha I'll probably never touch this)

            }

// ~~~TOOLS AND UTILITIES TAB~~~
            if (event.getTabKey()==CreativeModeTabs.TOOLS_AND_UTILITIES) { // ~~~TOOLS AND UTILITIES TAB~~~

					event.accept(RegistryBIBI.CHISEL);

					event.accept(RegistryBIBI.AKU_AKU_CRATE);
					event.accept(RegistryBIBI.BOUNCE_CRATE);
					event.accept(RegistryBIBI.CRASH_CRATE);
					event.accept(RegistryBIBI.CRASH_TNT);
					event.accept(RegistryBIBI.NITRO);

					event.accept(RegistryBIBI.BLOCK_OF_CRYING_INGOTS);

					event.accept(RegistryBIBI.CRYING_ESSENCE);
					event.accept(RegistryBIBI.CRYING_ESSENCE_BUCKET);

					event.accept(RegistryBIBI.CRYING_AXE);
					event.accept(RegistryBIBI.CRYING_PICKAXE);
					event.accept(RegistryBIBI.CRYING_SHOVEL);
					event.accept(RegistryBIBI.CRYING_HOE);
					event.accept(RegistryBIBI.CRYING_PAXEL);
					event.accept(RegistryBIBI.CRYING_SWHOPAXEL);


					event.accept(RegistryBIBI.SCULK_HAMMER);


					// Balloons
					event.accept(RegistryBIBI.BALLOON_WHITE);
					event.accept(RegistryBIBI.BALLOON_ORANGE);
					event.accept(RegistryBIBI.BALLOON_MAGENTA);
					event.accept(RegistryBIBI.BALLOON_LIGHT_BLUE);
					event.accept(RegistryBIBI.BALLOON_YELLOW);
					event.accept(RegistryBIBI.BALLOON_LIME);
					event.accept(RegistryBIBI.BALLOON_PINK);
					event.accept(RegistryBIBI.BALLOON_GRAY);
					event.accept(RegistryBIBI.BALLOON_LIGHT_GRAY);
					event.accept(RegistryBIBI.BALLOON_CYAN);
					event.accept(RegistryBIBI.BALLOON_PURPLE);
					event.accept(RegistryBIBI.BALLOON_BLUE);
					event.accept(RegistryBIBI.BALLOON_BROWN);
					event.accept(RegistryBIBI.BALLOON_GREEN);
					event.accept(RegistryBIBI.BALLOON_RED);
					event.accept(RegistryBIBI.BALLOON_BLACK);


					// Music disks
					event.accept(RegistryBIBI.MD_P1NG_P0NG);
					event.accept(RegistryBIBI.MD_CANT_GET_YOU_OUT_OF_MY_HEAD);
					event.accept(RegistryBIBI.MD_AQUAA);
					event.accept(RegistryBIBI.MD_DRUNK_RHYTHM);

			}

// ~~~COMBAT TAB~~~
            if (event.getTabKey() == CreativeModeTabs.COMBAT) { // ~~~COMBAT TAB~~~

					event.accept(RegistryBIBI.CLAY_SWORD.get());

					event.accept(RegistryBIBI.CLAYMORE.get());
					event.accept(RegistryBIBI.CRYING_SWORD.get());

					event.accept(RegistryBIBI.WOODEN_HATCHET.get());
					event.accept(RegistryBIBI.CLAY_HATCHET.get());
					event.accept(RegistryBIBI.STONE_HATCHET.get());
					event.accept(RegistryBIBI.IRON_HATCHET.get());
					event.accept(RegistryBIBI.GOLDEN_HATCHET.get());
					event.accept(RegistryBIBI.EMERALD_HATCHET.get());
					event.accept(RegistryBIBI.DIAMOND_HATCHET.get());
					event.accept(RegistryBIBI.NETHERITE_HATCHET.get());
					event.accept(RegistryBIBI.ONYX_HATCHET.get());
					event.accept(RegistryBIBI.TITAN_HATCHET.get());
					event.accept(RegistryBIBI.CRYING_HATCHET.get());

               event.accept(RegistryBIBI.CRYING_HELMET);
               event.accept(RegistryBIBI.CRYING_CHESTPLATE);
               event.accept(RegistryBIBI.CRYING_LEGGINGS);
               event.accept(RegistryBIBI.CRYING_BOOTS);
					event.accept(RegistryBIBI.CRYING_HORSE_ARMOR);

               event.accept(RegistryBIBI.KAUPENJOE_SMITHING_TEMPLATE);

               event.accept(RegistryBIBI.PROTOTYPE_GUNBLADE);
               event.accept(RegistryBIBI.GOLDEN_GUNBLADE.get());
               event.accept(RegistryBIBI.IRON_GUNBLADE.get());
               event.accept(RegistryBIBI.EMERALD_GUNBLADE.get());
               event.accept(RegistryBIBI.DIAMOND_GUNBLADE.get());
               event.accept(RegistryBIBI.NETHERITE_GUNBLADE.get());
               event.accept(RegistryBIBI.CRYING_GUNBLADE.get());
               event.accept(RegistryBIBI.ONYX_GUNBLADE.get());
               event.accept(RegistryBIBI.TITAN_GUNBLADE.get());


					event.accept(RegistryBIBI.GOLDEN_CZ75.get());
					event.accept(RegistryBIBI.IRON_CZ75.get());
					event.accept(RegistryBIBI.EMERALD_CZ75.get());
					event.accept(RegistryBIBI.DIAMOND_CZ75.get());
					event.accept(RegistryBIBI.NETHERITE_CZ75.get());
					event.accept(RegistryBIBI.ONYX_CZ75.get());
					event.accept(RegistryBIBI.TITAN_CZ75.get());

               event.accept(RegistryBIBI.GOLDEN_92FS.get());
					event.accept(RegistryBIBI.IRON_92FS.get());
					event.accept(RegistryBIBI.EMERALD_92FS.get());
               event.accept(RegistryBIBI.DIAMOND_92FS.get());
					event.accept(RegistryBIBI.NETHERITE_92FS.get());

					event.accept(RegistryBIBI.ONYX_92FS.get());
					event.accept(RegistryBIBI.TITAN_92FS.get());
					event.accept(RegistryBIBI.VALENTINE_92FS.get());


					event.accept(RegistryBIBI.GOLDEN_M9A1.get());
					event.accept(RegistryBIBI.IRON_M9A1.get());
					event.accept(RegistryBIBI.EMERALD_M9A1.get());
					event.accept(RegistryBIBI.DIAMOND_M9A1.get());
					event.accept(RegistryBIBI.NETHERITE_M9A1.get());
					event.accept(RegistryBIBI.ONYX_M9A1.get());
					event.accept(RegistryBIBI.TITAN_M9A1.get());


					event.accept(RegistryBIBI.GOLDEN_PROTEKTOR_TYPE_75.get());
					event.accept(RegistryBIBI.IRON_PROTEKTOR_TYPE_75.get());
					event.accept(RegistryBIBI.EMERALD_PROTEKTOR_TYPE_75.get());
					event.accept(RegistryBIBI.DIAMOND_PROTEKTOR_TYPE_75.get());
					event.accept(RegistryBIBI.NETHERITE_PROTEKTOR_TYPE_75.get());
					event.accept(RegistryBIBI.ONYX_PROTEKTOR_TYPE_75.get());
					event.accept(RegistryBIBI.TITAN_PROTEKTOR_TYPE_75.get());

					event.accept(RegistryBIBI.GOLDEN_HANDCANNON.get());
					event.accept(RegistryBIBI.IRON_HANDCANNON.get());
					event.accept(RegistryBIBI.EMERALD_HANDCANNON.get());
					event.accept(RegistryBIBI.DIAMOND_HANDCANNON.get());
					event.accept(RegistryBIBI.NETHERITE_HANDCANNON.get());
					event.accept(RegistryBIBI.ONYX_HANDCANNON.get());
					event.accept(RegistryBIBI.TITAN_HANDCANNON.get());

					event.accept(RegistryBIBI.GOLDEN_IMI_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.IRON_IMI_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.EMERALD_IMI_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.DIAMOND_IMI_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.NETHERITE_IMI_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.ONYX_IMI_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.TITAN_IMI_DESERT_EAGLE.get());
					

					event.accept(RegistryBIBI.GOLDEN_RRAR.get());
					event.accept(RegistryBIBI.IRON_RRAR.get());
					event.accept(RegistryBIBI.EMERALD_RRAR.get());
					event.accept(RegistryBIBI.DIAMOND_RRAR.get());
					event.accept(RegistryBIBI.NETHERITE_RRAR.get());
					event.accept(RegistryBIBI.ONYX_RRAR.get());
					event.accept(RegistryBIBI.TITAN_RRAR.get());

					
					event.accept(RegistryBIBI.GOLDEN_COLT_ANACONDA.get());
					event.accept(RegistryBIBI.IRON_COLT_ANACONDA.get());
					event.accept(RegistryBIBI.EMERALD_COLT_ANACONDA.get());
					event.accept(RegistryBIBI.DIAMOND_COLT_ANACONDA.get());
					event.accept(RegistryBIBI.NETHERITE_COLT_ANACONDA.get());
					event.accept(RegistryBIBI.ONYX_COLT_ANACONDA.get());
					event.accept(RegistryBIBI.TITAN_COLT_ANACONDA.get());

					
					event.accept(RegistryBIBI.GOLDEN_S_AND_W_M629C.get());
					event.accept(RegistryBIBI.IRON_S_AND_W_M629C.get());
					event.accept(RegistryBIBI.EMERALD_S_AND_W_M629C.get());
					event.accept(RegistryBIBI.DIAMOND_S_AND_W_M629C.get());
					event.accept(RegistryBIBI.NETHERITE_S_AND_W_M629C.get());
					event.accept(RegistryBIBI.ONYX_S_AND_W_M629C.get());
					event.accept(RegistryBIBI.TITAN_S_AND_W_M629C.get());

					
					event.accept(RegistryBIBI.GOLDEN_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.IRON_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.EMERALD_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.DIAMOND_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.NETHERITE_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.ONYX_DESERT_EAGLE.get());
					event.accept(RegistryBIBI.TITAN_DESERT_EAGLE.get());

					
					event.accept(RegistryBIBI.GOLDEN_S_AND_W_500.get());
					event.accept(RegistryBIBI.IRON_S_AND_W_500.get());
					event.accept(RegistryBIBI.EMERALD_S_AND_W_500.get());
					event.accept(RegistryBIBI.DIAMOND_S_AND_W_500.get());
					event.accept(RegistryBIBI.NETHERITE_S_AND_W_500.get());
					event.accept(RegistryBIBI.ONYX_S_AND_W_500.get());
					event.accept(RegistryBIBI.TITAN_S_AND_W_500.get());

					event.accept(RegistryBIBI.JAR_GUNPOWDER_A.get());
					event.accept(RegistryBIBI.JAR_GUNPOWDER_B.get());
					event.accept(RegistryBIBI.JAR_GUNPOWDER_C.get());
					event.accept(RegistryBIBI.JAR_GUNPOWDER_D.get());

					event.accept(RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get());
					event.accept(RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get());

					event.accept(RegistryBIBI.FOURTY_FOUR_S_AND_W_ROUNDS.get());
					event.accept(RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get());
					event.accept(RegistryBIBI.FIFTY_AE_ROUNDS.get());


					event.accept(RegistryBIBI.AKU_AKU_MASK);

					event.accept(RegistryBIBI.SMB_SUPER_FAN);

					event.accept(RegistryBIBI.TNT_STICK);

					event.accept(RegistryBIBI.TOTEM_OF_LIFE);
					event.accept(RegistryBIBI.TOTEM_OF_UNCRYING);
            }

// ~~~FOOD TAB~~~
            if (event.getTabKey()==CreativeModeTabs.FOOD_AND_DRINKS) { // ~~~FOOD TAB~~~

					event.accept(RegistryBIBI.WUMPA_FRUIT);

            }

// ~~~INGREDIENTS TAB~~~
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) { // ~~~INGREDIENTS TAB~~~

					event.accept(RegistryBIBI.TEST_ITEM);

					event.accept(RegistryBIBI.RAW_ONYX);
					event.accept(RegistryBIBI.RAW_TITANIUM);

					event.accept(RegistryBIBI.ONYX_INGOT);
					event.accept(RegistryBIBI.TITANIUM_INGOT);

					event.accept(RegistryBIBI.CRYING_INGOT);
					event.accept(RegistryBIBI.CRYING_SMITHING_TEMPLATE);
					event.accept(RegistryBIBI.CRYING_RESIN);

					event.accept(RegistryBIBI.FEATHER_BLACK);
					event.accept(RegistryBIBI.FEATHER_BLUE);
					event.accept(RegistryBIBI.FEATHER_BROWN);
					event.accept(RegistryBIBI.FEATHER_CYAN);
					event.accept(RegistryBIBI.FEATHER_GRAY);
					event.accept(RegistryBIBI.FEATHER_GREEN);
					event.accept(RegistryBIBI.FEATHER_LIGHT_BLUE);
					event.accept(RegistryBIBI.FEATHER_LIGHT_GRAY);
					event.accept(RegistryBIBI.FEATHER_LIME);
					event.accept(RegistryBIBI.FEATHER_MAGENTA);
					event.accept(RegistryBIBI.FEATHER_ORANGE);
					event.accept(RegistryBIBI.FEATHER_PINK);
					event.accept(RegistryBIBI.FEATHER_PURPLE);
					event.accept(RegistryBIBI.FEATHER_RED);
					event.accept(RegistryBIBI.FEATHER_YELLOW);
					event.accept(RegistryBIBI.FEATHER_GOLDEN);

					event.accept(RegistryBIBI.JAR_GUNPOWDER_A);
					event.accept(RegistryBIBI.JAR_GUNPOWDER_B);
					event.accept(RegistryBIBI.JAR_GUNPOWDER_C);
					event.accept(RegistryBIBI.JAR_GUNPOWDER_D);

					event.accept(RegistryBIBI.CRUDE_ALEXANDRITE);
					event.accept(RegistryBIBI.ALEXANDRITE);
					event.accept(RegistryBIBI.FLAWLESS_ALEXANDRITE);
					event.accept(RegistryBIBI.PERFECT_ALEXANDRITE);
					event.accept(RegistryBIBI.BRILLIANT_ALEXANDRITE);

					event.accept(RegistryBIBI.CRUDE_ANDALUSITE);
					event.accept(RegistryBIBI.ANDALUSITE);
					event.accept(RegistryBIBI.FLAWLESS_ANDALUSITE);
					event.accept(RegistryBIBI.PERFECT_ANDALUSITE);
					event.accept(RegistryBIBI.BRILLIANT_ANDALUSITE);

					event.accept(RegistryBIBI.CRUDE_AQUAMARINE);
					event.accept(RegistryBIBI.AQUAMARINE);
					event.accept(RegistryBIBI.FLAWLESS_AQUAMARINE);
					event.accept(RegistryBIBI.PERFECT_AQUAMARINE);
					event.accept(RegistryBIBI.BRILLIANT_AQUAMARINE);

					event.accept(RegistryBIBI.CRUDE_CHRYSOBERYL);
					event.accept(RegistryBIBI.CHRYSOBERYL);
					event.accept(RegistryBIBI.FLAWLESS_CHRYSOBERYL);
					event.accept(RegistryBIBI.PERFECT_CHRYSOBERYL);
					event.accept(RegistryBIBI.BRILLIANT_CHRYSOBERYL);

					event.accept(RegistryBIBI.CRUDE_CORUNDUM);
					event.accept(RegistryBIBI.CORUNDUM);
					event.accept(RegistryBIBI.FLAWLESS_CORUNDUM);
					event.accept(RegistryBIBI.PERFECT_CORUNDUM);
					event.accept(RegistryBIBI.BRILLIANT_CORUNDUM);

					event.accept(RegistryBIBI.CRUDE_CYMOPHANE);
					event.accept(RegistryBIBI.CYMOPHANE);
					event.accept(RegistryBIBI.FLAWLESS_CYMOPHANE);
					event.accept(RegistryBIBI.PERFECT_CYMOPHANE);
					event.accept(RegistryBIBI.BRILLIANT_CYMOPHANE);

					event.accept(RegistryBIBI.CRUDE_DRAGONITE);
					event.accept(RegistryBIBI.DRAGONITE);
					event.accept(RegistryBIBI.FLAWLESS_DRAGONITE);
					event.accept(RegistryBIBI.PERFECT_DRAGONITE);
					event.accept(RegistryBIBI.BRILLIANT_DRAGONITE);

					event.accept(RegistryBIBI.CRUDE_JADEITE);
					event.accept(RegistryBIBI.JADEITE);
					event.accept(RegistryBIBI.FLAWLESS_JADEITE);
					event.accept(RegistryBIBI.PERFECT_JADEITE);
					event.accept(RegistryBIBI.BRILLIANT_JADEITE);

					event.accept(RegistryBIBI.CRUDE_OPALITE);
					event.accept(RegistryBIBI.OPALITE);
					event.accept(RegistryBIBI.FLAWLESS_OPALITE);
					event.accept(RegistryBIBI.PERFECT_OPALITE);
					event.accept(RegistryBIBI.BRILLIANT_OPALITE);

					event.accept(RegistryBIBI.CRUDE_PADPARADSCHA);
					event.accept(RegistryBIBI.PADPARADSCHA);
					event.accept(RegistryBIBI.FLAWLESS_PADPARADSCHA);
					event.accept(RegistryBIBI.PERFECT_PADPARADSCHA);
					event.accept(RegistryBIBI.BRILLIANT_PADPARADSCHA);

					event.accept(RegistryBIBI.CRUDE_RUBY);
					event.accept(RegistryBIBI.RUBY);
					event.accept(RegistryBIBI.FLAWLESS_RUBY);
					event.accept(RegistryBIBI.PERFECT_RUBY);
					event.accept(RegistryBIBI.BRILLIANT_RUBY);

					event.accept(RegistryBIBI.CRUDE_SAPPHIRE);
					event.accept(RegistryBIBI.SAPPHIRE);
					event.accept(RegistryBIBI.FLAWLESS_SAPPHIRE);
					event.accept(RegistryBIBI.PERFECT_SAPPHIRE);
					event.accept(RegistryBIBI.BRILLIANT_SAPPHIRE);

					event.accept(RegistryBIBI.CRUDE_SCAPOLITE);
					event.accept(RegistryBIBI.SCAPOLITE);
					event.accept(RegistryBIBI.FLAWLESS_SCAPOLITE);
					event.accept(RegistryBIBI.PERFECT_SCAPOLITE);
					event.accept(RegistryBIBI.BRILLIANT_SCAPOLITE);

					event.accept(RegistryBIBI.CRUDE_STAUROLITE);
					event.accept(RegistryBIBI.STAUROLITE);
					event.accept(RegistryBIBI.FLAWLESS_STAUROLITE);
					event.accept(RegistryBIBI.PERFECT_STAUROLITE);
					event.accept(RegistryBIBI.BRILLIANT_STAUROLITE);

					event.accept(RegistryBIBI.CRUDE_TANZANITE);
					event.accept(RegistryBIBI.TANZANITE);
					event.accept(RegistryBIBI.FLAWLESS_TANZANITE);
					event.accept(RegistryBIBI.PERFECT_TANZANITE);
					event.accept(RegistryBIBI.BRILLIANT_TANZANITE);

					event.accept(RegistryBIBI.CRUDE_AMETHYST);
					event.accept(RegistryBIBI.FLAWLESS_AMETHYST);
					event.accept(RegistryBIBI.PERFECT_AMETHYST);
					event.accept(RegistryBIBI.BRILLIANT_AMETHYST);

					event.accept(RegistryBIBI.CRUDE_EMERALD);
					event.accept(RegistryBIBI.FLAWLESS_EMERALD);
					event.accept(RegistryBIBI.PERFECT_EMERALD);
					event.accept(RegistryBIBI.BRILLIANT_EMERALD);

					event.accept(RegistryBIBI.CRUDE_DIAMOND);
					event.accept(RegistryBIBI.FLAWLESS_DIAMOND);
					event.accept(RegistryBIBI.PERFECT_DIAMOND);
					event.accept(RegistryBIBI.BRILLIANT_DIAMOND);

					event.accept(RegistryBIBI.GEMSTONE_DUST);
            }

// ~~~SPAWN EGGS TABS~~~
      	  if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) { // ~~~SPAWN EGGS TABS~~~

            //Spawn eggs go here!

			  }
        }

    public static void register(IEventBus eventBus) {
        CREATIVETABSREGISTRY.register(eventBus);
    }
}
