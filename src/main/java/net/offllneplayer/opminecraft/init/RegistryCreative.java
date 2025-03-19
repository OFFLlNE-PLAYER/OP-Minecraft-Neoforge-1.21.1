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

                event.accept(RegistryIBBI.BLOCK_OF_CHARCOAL);

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

//colored block go here

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
                event.accept(RegistryIBBI.CRYING_SICKLE);
                event.accept(RegistryIBBI.CRYING_PAXEL);

                event.accept(RegistryIBBI.TNT_STICK);
                event.accept(RegistryIBBI.DYNAMITE_STICK);
                event.accept(RegistryIBBI.SCULK_HAMMER);

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
                event.accept(RegistryIBBI.TOTEM_OF_LIFE);

            }
            if (event.getTabKey()==CreativeModeTabs.FOOD_AND_DRINKS) {

                event.accept(RegistryIBBI.WUMPA_FRUIT);

            }
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {

                event.accept(RegistryIBBI.CRYING_INGOT);
                event.accept(RegistryIBBI.CRYING_SMITHING_TEMPLATE);
            }
        }
    public static void register(IEventBus eventBus) {
        CREATIVETABSREGISTRY.register(eventBus);
    }
}
