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
            if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {

                event.accept(RegistryIBBI.BLOCK_OF_TEST);

                event.accept(RegistryIBBI.BLOCK_OF_CHARCOAL);
                event.accept(RegistryIBBI.CHARCOAL_BUTTON);
                event.accept(RegistryIBBI.CHARCOAL_FENCE);
                event.accept(RegistryIBBI.CHARCOAL_FENCE_GATE);
                event.accept(RegistryIBBI.CHARCOAL_PRESSURE_PLATE);
                event.accept(RegistryIBBI.CHARCOAL_SLAB);
                event.accept(RegistryIBBI.CHARCOAL_STAIRS);
                event.accept(RegistryIBBI.CHARCOAL_TRAPDOOR);
                event.accept(RegistryIBBI.CHARCOAL_WALL);

                event.accept(RegistryIBBI.CHARCOAL_BRICKS);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_BUTTON);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_FENCE);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_FENCE_GATE);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_PRESSURE_PLATE);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_SLAB);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_STAIRS);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_TRAPDOOR);
                event.accept(RegistryIBBI.CHARCOAL_BRICK_WALL);

                event.accept(RegistryIBBI.CHARCOAL_TILES);
                event.accept(RegistryIBBI.CHARCOAL_TILE_BUTTON);
                event.accept(RegistryIBBI.CHARCOAL_TILE_FENCE);
                event.accept(RegistryIBBI.CHARCOAL_TILE_FENCE_GATE);
                event.accept(RegistryIBBI.CHARCOAL_TILE_PRESSURE_PLATE);
                event.accept(RegistryIBBI.CHARCOAL_TILE_SLAB);
                event.accept(RegistryIBBI.CHARCOAL_TILE_STAIRS);
                event.accept(RegistryIBBI.CHARCOAL_TILE_TRAPDOOR);
                event.accept(RegistryIBBI.CHARCOAL_TILE_WALL);

                event.accept(RegistryIBBI.CHISELED_DIAMOND);
                event.accept(RegistryIBBI.CHISELED_GOLD);
                event.accept(RegistryIBBI.CHISELED_IRON);
                event.accept(RegistryIBBI.CHISELED_NETHERITE);

                event.accept(RegistryIBBI.CRYING_BRICKS);
                event.accept(RegistryIBBI.CRYING_BRICK_BUTTON);
                event.accept(RegistryIBBI.CRYING_BRICK_FENCE);
                event.accept(RegistryIBBI.CRYING_BRICK_FENCE_GATE);
                event.accept(RegistryIBBI.CRYING_BRICK_PRESSURE_PLATE);
                event.accept(RegistryIBBI.CRYING_BRICK_SLAB);
                event.accept(RegistryIBBI.CRYING_BRICK_STAIRS);
                event.accept(RegistryIBBI.CRYING_BRICK_TRAPDOOR);
                event.accept(RegistryIBBI.CRYING_BRICK_WALL);

                event.accept(RegistryIBBI.CRYING_TILES);
                event.accept(RegistryIBBI.CRYING_TILE_BUTTON);
                event.accept(RegistryIBBI.CRYING_TILE_FENCE);
                event.accept(RegistryIBBI.CRYING_TILE_FENCE_GATE);
                event.accept(RegistryIBBI.CRYING_TILE_PRESSURE_PLATE);
                event.accept(RegistryIBBI.CRYING_TILE_SLAB);
                event.accept(RegistryIBBI.CRYING_TILE_STAIRS);
                event.accept(RegistryIBBI.CRYING_TILE_TRAPDOOR);
                event.accept(RegistryIBBI.CRYING_TILE_WALL);

                event.accept(RegistryIBBI.DENSE_STONE);
                event.accept(RegistryIBBI.DENSE_STONE_BUTTON);
                event.accept(RegistryIBBI.DENSE_STONE_FENCE);
                event.accept(RegistryIBBI.DENSE_STONE_FENCE_GATE);
                event.accept(RegistryIBBI.DENSE_STONE_PRESSURE_PLATE);
                event.accept(RegistryIBBI.DENSE_STONE_SLAB);
                event.accept(RegistryIBBI.DENSE_STONE_STAIRS);
                event.accept(RegistryIBBI.DENSE_STONE_TRAPDOOR);
                event.accept(RegistryIBBI.DENSE_STONE_WALL);

                event.accept(RegistryIBBI.DENSE_STONE_BRICKS);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_BUTTON);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_FENCE);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_FENCE_GATE);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_PRESSURE_PLATE);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_SLAB);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_STAIRS);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_TRAPDOOR);
                event.accept(RegistryIBBI.DENSE_STONE_BRICK_WALL);

                event.accept(RegistryIBBI.DENSE_STONE_TILES);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_BUTTON);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_FENCE);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_FENCE_GATE);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_PRESSURE_PLATE);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_SLAB);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_STAIRS);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_TRAPDOOR);
                event.accept(RegistryIBBI.DENSE_STONE_TILE_WALL);

                event.accept(RegistryIBBI.BLOCK_OF_ONYX);

                event.accept(RegistryIBBI.STONE_TILES);
                event.accept(RegistryIBBI.STONE_TILE_BUTTON);
                event.accept(RegistryIBBI.STONE_TILE_FENCE);
                event.accept(RegistryIBBI.STONE_TILE_FENCE_GATE);
                event.accept(RegistryIBBI.STONE_TILE_PRESSURE_PLATE);
                event.accept(RegistryIBBI.STONE_TILE_SLAB);
                event.accept(RegistryIBBI.STONE_TILE_STAIRS);
                event.accept(RegistryIBBI.STONE_TILE_TRAPDOOR);
                event.accept(RegistryIBBI.STONE_TILE_WALL);

            }
            if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {

                event.accept(RegistryIBBI.GOLDEN_BED);

                event.accept(RegistryIBBI.ONYX_LAMP_BLACK);
                event.accept(RegistryIBBI.ONYX_LAMP_BLUE);
                event.accept(RegistryIBBI.ONYX_LAMP_BROWN);
                event.accept(RegistryIBBI.ONYX_LAMP_CYAN);
                event.accept(RegistryIBBI.ONYX_LAMP_GRAY);
                event.accept(RegistryIBBI.ONYX_LAMP_GREEN);
                event.accept(RegistryIBBI.ONYX_LAMP_LIGHT_BLUE);
                event.accept(RegistryIBBI.ONYX_LAMP_LIGHT_GRAY);
                event.accept(RegistryIBBI.ONYX_LAMP_LIME);
                event.accept(RegistryIBBI.ONYX_LAMP_MAGENTA);
                event.accept(RegistryIBBI.ONYX_LAMP_ORANGE);
                event.accept(RegistryIBBI.ONYX_LAMP_PINK);
                event.accept(RegistryIBBI.ONYX_LAMP_PURPLE);
                event.accept(RegistryIBBI.ONYX_LAMP_RED);
                event.accept(RegistryIBBI.ONYX_LAMP_WHITE);
                event.accept(RegistryIBBI.ONYX_LAMP_YELLOW);

            }
            if (event.getTabKey()==CreativeModeTabs.NATURAL_BLOCKS) {

                event.accept(RegistryIBBI.FLOWERING_PITCHER_PLANT);
                event.accept(RegistryIBBI.WUMPA_PLANT);

            }
            if (event.getTabKey()==CreativeModeTabs.FUNCTIONAL_BLOCKS) {

                event.accept(RegistryIBBI.COPPER_FURNACE);
                event.accept(RegistryIBBI.DIAMOND_FURNACE);
                event.accept(RegistryIBBI.GOLD_FURNACE);
                event.accept(RegistryIBBI.IRON_FURNACE);
                event.accept(RegistryIBBI.NETHERITE_FURNACE);

                event.accept(RegistryIBBI.GOLDEN_BED);

                event.accept(RegistryIBBI.ONYX_LAMP_BLACK);
                event.accept(RegistryIBBI.ONYX_LAMP_BLUE);
                event.accept(RegistryIBBI.ONYX_LAMP_BROWN);
                event.accept(RegistryIBBI.ONYX_LAMP_CYAN);
                event.accept(RegistryIBBI.ONYX_LAMP_GRAY);
                event.accept(RegistryIBBI.ONYX_LAMP_GREEN);
                event.accept(RegistryIBBI.ONYX_LAMP_LIGHT_BLUE);
                event.accept(RegistryIBBI.ONYX_LAMP_LIGHT_GRAY);
                event.accept(RegistryIBBI.ONYX_LAMP_LIME);
                event.accept(RegistryIBBI.ONYX_LAMP_MAGENTA);
                event.accept(RegistryIBBI.ONYX_LAMP_ORANGE);
                event.accept(RegistryIBBI.ONYX_LAMP_PINK);
                event.accept(RegistryIBBI.ONYX_LAMP_PURPLE);
                event.accept(RegistryIBBI.ONYX_LAMP_RED);
                event.accept(RegistryIBBI.ONYX_LAMP_WHITE);
                event.accept(RegistryIBBI.ONYX_LAMP_YELLOW);

            }
            if (event.getTabKey()==CreativeModeTabs.REDSTONE_BLOCKS) {

//Redstone block go here ~(hah I'll probably never touch this)

            }
            if (event.getTabKey()==CreativeModeTabs.TOOLS_AND_UTILITIES) {

                event.accept(RegistryIBBI.CHISEL);

                event.accept(RegistryIBBI.AKU_AKU_CRATE);
                event.accept(RegistryIBBI.BOUNCE_CRATE);
                event.accept(RegistryIBBI.CRASH_CRATE);
                event.accept(RegistryIBBI.CRASH_TNT);
                event.accept(RegistryIBBI.NITRO);

                event.accept(RegistryIBBI.BLOCK_OF_CRYING_INGOTS);

                event.accept(RegistryIBBI.CRYING_ESSENCE);
                event.accept(RegistryIBBI.CRYING_ESSENCE_BUCKET);

                event.accept(RegistryIBBI.CRYING_AXE);
                event.accept(RegistryIBBI.CRYING_PICKAXE);
                event.accept(RegistryIBBI.CRYING_SHOVEL);
                event.accept(RegistryIBBI.CRYING_HOE);
                event.accept(RegistryIBBI.CRYING_PAXEL);
                event.accept(RegistryIBBI.CRYING_SWHOPAXEL);

                event.accept(RegistryIBBI.CRYING_SICKLE);

                event.accept(RegistryIBBI.TNT_STICK);
                event.accept(RegistryIBBI.DYNAMITE_STICK);

                event.accept(RegistryIBBI.SCULK_HAMMER);

                event.accept(RegistryIBBI.SMB_SUPER_FAN);

            }
            if (event.getTabKey() == CreativeModeTabs.COMBAT) {

                event.accept(RegistryIBBI.CRYING_SWORD);

                event.accept(RegistryIBBI.CRYING_HELMET);
                event.accept(RegistryIBBI.CRYING_CHESTPLATE);
                event.accept(RegistryIBBI.CRYING_LEGGINGS);
                event.accept(RegistryIBBI.CRYING_BOOTS);
                event.accept(RegistryIBBI.CRYING_HORSE_ARMOR);

                event.accept(RegistryIBBI.KAUPENJOE_SMITHING_TEMPLATE);

                event.accept(RegistryIBBI.AKU_AKU_MASK);


                event.accept(RegistryIBBI.GUNBLADE);
                event.accept(RegistryIBBI.PROTOTYPE_GUNBLADE);
                event.accept(RegistryIBBI.STUCK_GUNBLADE);

                event.accept(RegistryIBBI.TOTEM_OF_LIFE);
                // event.accept(RegistryIBBI.TOTEM_OF_UNCRYING);
            }
            if (event.getTabKey()==CreativeModeTabs.FOOD_AND_DRINKS) {

                event.accept(RegistryIBBI.WUMPA_FRUIT);

            }
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {

                event.accept(RegistryIBBI.TEST_ITEM);

                event.accept(RegistryIBBI.ANCIENT_CHUNK);

                event.accept(RegistryIBBI.CRUDE_ALEXANDRITE);
                event.accept(RegistryIBBI.ALEXANDRITE);
                event.accept(RegistryIBBI.FLAWLESS_ALEXANDRITE);
                event.accept(RegistryIBBI.PERFECT_ALEXANDRITE);

                event.accept(RegistryIBBI.CRUDE_ANDALUSITE);
                event.accept(RegistryIBBI.ANDALUSITE);
                event.accept(RegistryIBBI.FLAWLESS_ANDALUSITE);
                event.accept(RegistryIBBI.PERFECT_ANDALUSITE);

                event.accept(RegistryIBBI.CRUDE_CHRYSOBERYL);
                event.accept(RegistryIBBI.CHRYSOBERYL);
                event.accept(RegistryIBBI.FLAWLESS_CHRYSOBERYL);
                event.accept(RegistryIBBI.PERFECT_CHRYSOBERYL);

                event.accept(RegistryIBBI.CRUDE_CORUNDUM);
                event.accept(RegistryIBBI.CORUNDUM);
                event.accept(RegistryIBBI.FLAWLESS_CORUNDUM);
                event.accept(RegistryIBBI.PERFECT_CORUNDUM);

                event.accept(RegistryIBBI.CRUDE_CYMOPHANE);
                event.accept(RegistryIBBI.CYMOPHANE);
                event.accept(RegistryIBBI.FLAWLESS_CYMOPHANE);
                event.accept(RegistryIBBI.PERFECT_CYMOPHANE);

                event.accept(RegistryIBBI.CRUDE_PADPARADSCHA);
                event.accept(RegistryIBBI.PADPARADSCHA);
                event.accept(RegistryIBBI.FLAWLESS_PADPARADSCHA);
                event.accept(RegistryIBBI.PERFECT_PADPARADSCHA);

                event.accept(RegistryIBBI.CRUDE_RUBY);
                event.accept(RegistryIBBI.RUBY);
                event.accept(RegistryIBBI.FLAWLESS_RUBY);
                event.accept(RegistryIBBI.PERFECT_RUBY);

                event.accept(RegistryIBBI.CRUDE_SAPPHIRE);
                event.accept(RegistryIBBI.SAPPHIRE);
                event.accept(RegistryIBBI.FLAWLESS_SAPPHIRE);
                event.accept(RegistryIBBI.PERFECT_SAPPHIRE);

                event.accept(RegistryIBBI.CRUDE_SCAPOLITE);
                event.accept(RegistryIBBI.SCAPOLITE);
                event.accept(RegistryIBBI.FLAWLESS_SCAPOLITE);
                event.accept(RegistryIBBI.PERFECT_SCAPOLITE);

                event.accept(RegistryIBBI.CRUDE_STAUROLITE);
                event.accept(RegistryIBBI.STAUROLITE);
                event.accept(RegistryIBBI.FLAWLESS_STAUROLITE);
                event.accept(RegistryIBBI.PERFECT_STAUROLITE);

                event.accept(RegistryIBBI.CRUDE_TANZANITE);
                event.accept(RegistryIBBI.TANZANITE);
                event.accept(RegistryIBBI.FLAWLESS_TANZANITE);
                event.accept(RegistryIBBI.PERFECT_TANZANITE);

                event.accept(RegistryIBBI.CRUDE_EMERALD);
                event.accept(RegistryIBBI.FLAWLESS_EMERALD);
                event.accept(RegistryIBBI.PERFECT_EMERALD);

                event.accept(RegistryIBBI.CRUDE_DIAMOND);
                event.accept(RegistryIBBI.FLAWLESS_DIAMOND);
                event.accept(RegistryIBBI.PERFECT_DIAMOND);

                event.accept(RegistryIBBI.GEMSTONE_DUST);

                event.accept(RegistryIBBI.CRYING_INGOT);
                event.accept(RegistryIBBI.CRYING_SMITHING_TEMPLATE);
                event.accept(RegistryIBBI.CRYING_RESIN);

                event.accept(RegistryIBBI.BLACK_FEATHER);
                event.accept(RegistryIBBI.BLUE_FEATHER);
                event.accept(RegistryIBBI.BROWN_FEATHER);
                event.accept(RegistryIBBI.CYAN_FEATHER);
                event.accept(RegistryIBBI.GRAY_FEATHER);
                event.accept(RegistryIBBI.GREEN_FEATHER);
                event.accept(RegistryIBBI.LIGHT_BLUE_FEATHER);
                event.accept(RegistryIBBI.LIGHT_GRAY_FEATHER);
                event.accept(RegistryIBBI.LIME_FEATHER);
                event.accept(RegistryIBBI.MAGENTA_FEATHER);
                event.accept(RegistryIBBI.ORANGE_FEATHER);
                event.accept(RegistryIBBI.PINK_FEATHER);
                event.accept(RegistryIBBI.PURPLE_FEATHER);
                event.accept(RegistryIBBI.RED_FEATHER);
                event.accept(RegistryIBBI.YELLOW_FEATHER);
                event.accept(RegistryIBBI.GOLDEN_FEATHER);

            }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {

            //Spawn eggs go here!

        }
        }
    public static void register(IEventBus eventBus) {
        CREATIVETABSREGISTRY.register(eventBus);
    }
}
