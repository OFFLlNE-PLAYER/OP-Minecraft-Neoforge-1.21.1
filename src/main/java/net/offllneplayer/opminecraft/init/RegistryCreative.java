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

                event.accept(RegistryBIBI.DENSE_STONE);
                event.accept(RegistryBIBI.DENSE_STONE_BUTTON);
                event.accept(RegistryBIBI.DENSE_STONE_FENCE);
                event.accept(RegistryBIBI.DENSE_STONE_FENCE_GATE);
                event.accept(RegistryBIBI.DENSE_STONE_PRESSURE_PLATE);
                event.accept(RegistryBIBI.DENSE_STONE_SLAB);
                event.accept(RegistryBIBI.DENSE_STONE_STAIRS);
                event.accept(RegistryBIBI.DENSE_STONE_TRAPDOOR);
                event.accept(RegistryBIBI.DENSE_STONE_WALL);

                event.accept(RegistryBIBI.DENSE_STONE_BRICKS);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_BUTTON);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_FENCE);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_FENCE_GATE);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_PRESSURE_PLATE);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_SLAB);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_STAIRS);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_TRAPDOOR);
                event.accept(RegistryBIBI.DENSE_STONE_BRICK_WALL);

                event.accept(RegistryBIBI.DENSE_STONE_TILES);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_BUTTON);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_FENCE);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_FENCE_GATE);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_PRESSURE_PLATE);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_SLAB);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_STAIRS);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_TRAPDOOR);
                event.accept(RegistryBIBI.DENSE_STONE_TILE_WALL);

                event.accept(RegistryBIBI.BLOCK_OF_ONYX);

                event.accept(RegistryBIBI.STONE_TILES);
                event.accept(RegistryBIBI.STONE_TILE_BUTTON);
                event.accept(RegistryBIBI.STONE_TILE_FENCE);
                event.accept(RegistryBIBI.STONE_TILE_FENCE_GATE);
                event.accept(RegistryBIBI.STONE_TILE_PRESSURE_PLATE);
                event.accept(RegistryBIBI.STONE_TILE_SLAB);
                event.accept(RegistryBIBI.STONE_TILE_STAIRS);
                event.accept(RegistryBIBI.STONE_TILE_TRAPDOOR);
                event.accept(RegistryBIBI.STONE_TILE_WALL);

            }
            if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {  // ~~~COLORED BLOCKS TAB~~~

                event.accept(RegistryBIBI.GOLDEN_BED);

                event.accept(RegistryBIBI.ONYX_LAMP_BLACK);
                event.accept(RegistryBIBI.ONYX_LAMP_BLUE);
                event.accept(RegistryBIBI.ONYX_LAMP_BROWN);
                event.accept(RegistryBIBI.ONYX_LAMP_CYAN);
                event.accept(RegistryBIBI.ONYX_LAMP_GRAY);
                event.accept(RegistryBIBI.ONYX_LAMP_GREEN);
                event.accept(RegistryBIBI.ONYX_LAMP_LIGHT_BLUE);
                event.accept(RegistryBIBI.ONYX_LAMP_LIGHT_GRAY);
                event.accept(RegistryBIBI.ONYX_LAMP_LIME);
                event.accept(RegistryBIBI.ONYX_LAMP_MAGENTA);
                event.accept(RegistryBIBI.ONYX_LAMP_ORANGE);
                event.accept(RegistryBIBI.ONYX_LAMP_PINK);
                event.accept(RegistryBIBI.ONYX_LAMP_PURPLE);
                event.accept(RegistryBIBI.ONYX_LAMP_RED);
                event.accept(RegistryBIBI.ONYX_LAMP_WHITE);
                event.accept(RegistryBIBI.ONYX_LAMP_YELLOW);

            }
            if (event.getTabKey()==CreativeModeTabs.NATURAL_BLOCKS) { // ~~~NATURAL BLOCKS TAB~~~

                event.accept(RegistryBIBI.FLOWERING_PITCHER_PLANT);
                event.accept(RegistryBIBI.WUMPA_PLANT);

            }
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


                event.accept(RegistryBIBI.COPPER_FURNACE);
                event.accept(RegistryBIBI.DIAMOND_FURNACE);
                event.accept(RegistryBIBI.GOLD_FURNACE);
                event.accept(RegistryBIBI.IRON_FURNACE);
                event.accept(RegistryBIBI.NETHERITE_FURNACE);

                event.accept(RegistryBIBI.GOLDEN_BED);

                event.accept(RegistryBIBI.ONYX_LAMP_BLACK);
                event.accept(RegistryBIBI.ONYX_LAMP_BLUE);
                event.accept(RegistryBIBI.ONYX_LAMP_BROWN);
                event.accept(RegistryBIBI.ONYX_LAMP_CYAN);
                event.accept(RegistryBIBI.ONYX_LAMP_GRAY);
                event.accept(RegistryBIBI.ONYX_LAMP_GREEN);
                event.accept(RegistryBIBI.ONYX_LAMP_LIGHT_BLUE);
                event.accept(RegistryBIBI.ONYX_LAMP_LIGHT_GRAY);
                event.accept(RegistryBIBI.ONYX_LAMP_LIME);
                event.accept(RegistryBIBI.ONYX_LAMP_MAGENTA);
                event.accept(RegistryBIBI.ONYX_LAMP_ORANGE);
                event.accept(RegistryBIBI.ONYX_LAMP_PINK);
                event.accept(RegistryBIBI.ONYX_LAMP_PURPLE);
                event.accept(RegistryBIBI.ONYX_LAMP_RED);
                event.accept(RegistryBIBI.ONYX_LAMP_WHITE);
                event.accept(RegistryBIBI.ONYX_LAMP_YELLOW);

            }
            if (event.getTabKey()==CreativeModeTabs.REDSTONE_BLOCKS) { // ~~~REDSTONE BLOCKS TAB~~~

//Redstone block go here ~(hah I'll probably never touch this)

            }
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

                event.accept(RegistryBIBI.TNT_STICK);

                event.accept(RegistryBIBI.SCULK_HAMMER);

                event.accept(RegistryBIBI.SMB_SUPER_FAN);

            }
            if (event.getTabKey() == CreativeModeTabs.COMBAT) { // ~~~COMBAT TAB~~~

                event.accept(RegistryBIBI.CLAY_SWORD.get());

                event.accept(RegistryBIBI.CLAYMORE.get());
                event.accept(RegistryBIBI.CRYING_SWORD.get());

                event.accept(RegistryBIBI.WOODEN_HATCHET.get());
                event.accept(RegistryBIBI.STONE_HATCHET.get());
                event.accept(RegistryBIBI.IRON_HATCHET.get());
                event.accept(RegistryBIBI.GOLDEN_HATCHET.get());
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

                event.accept(RegistryBIBI.AKU_AKU_MASK);

                event.accept(RegistryBIBI.PROTOTYPE_GUNBLADE);
                event.accept(RegistryBIBI.GOLDEN_GUNBLADE.get());
                event.accept(RegistryBIBI.DIAMOND_GUNBLADE.get());
                event.accept(RegistryBIBI.NETHERITE_GUNBLADE.get());
                event.accept(RegistryBIBI.CRYING_GUNBLADE.get());
                event.accept(RegistryBIBI.ONYX_GUNBLADE.get());
                event.accept(RegistryBIBI.TITAN_GUNBLADE.get());


                event.accept(RegistryBIBI.REDFIELD_BERETTA.get());
                event.accept(RegistryBIBI.WESKER_BERETTA.get());
                event.accept(RegistryBIBI.VALENTINE_BERETTA.get());

                event.accept(RegistryBIBI.GOLDEN_BERETTA.get());
                event.accept(RegistryBIBI.DIAMOND_BERETTA.get());
                event.accept(RegistryBIBI.ONYX_BERETTA.get());

                event.accept(RegistryBIBI.TITAN_BERETTA.get());
                event.accept(RegistryBIBI.TITAN_HANDCANNON.get());
                event.accept(RegistryBIBI.TITAN_DESERT_EAGLE.get());
                event.accept(RegistryBIBI.TITAN_REVOLVER.get());

                event.accept(RegistryBIBI.PROTEKTOR_BERETTA.get());
                event.accept(RegistryBIBI.PROTEKTOR_HANDCANNON.get());
                event.accept(RegistryBIBI.PROTEKTOR_DESERT_EAGLE.get());
                event.accept(RegistryBIBI.PROTEKTOR_REVOLVER.get());
                
                event.accept(RegistryBIBI.VALENTINE_REVOLVER.get());

                event.accept(RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get());
                event.accept(RegistryBIBI.TENmm_PARABELLUM_ROUNDS.get());

                event.accept(RegistryBIBI.FOURTY_FOUR_S_AND_W_ROUNDS.get());
                event.accept(RegistryBIBI.FIFTY_S_AND_W_ROUNDS.get());
                event.accept(RegistryBIBI.FIFTY_AE_ROUNDS.get());




                event.accept(RegistryBIBI.TOTEM_OF_LIFE);
                // event.accept(RegistryIBBI.TOTEM_OF_UNCRYING);
            }
            if (event.getTabKey()==CreativeModeTabs.FOOD_AND_DRINKS) {

                event.accept(RegistryBIBI.WUMPA_FRUIT);

            }
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) { // ~~~INGREDIENTS TAB~~~

                event.accept(RegistryBIBI.TEST_ITEM);

                event.accept(RegistryBIBI.ANCIENT_CHUNK);

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

                event.accept(RegistryBIBI.CRYING_INGOT);
                event.accept(RegistryBIBI.CRYING_SMITHING_TEMPLATE);
                event.accept(RegistryBIBI.CRYING_RESIN);

                event.accept(RegistryBIBI.BLACK_FEATHER);
                event.accept(RegistryBIBI.BLUE_FEATHER);
                event.accept(RegistryBIBI.BROWN_FEATHER);
                event.accept(RegistryBIBI.CYAN_FEATHER);
                event.accept(RegistryBIBI.GRAY_FEATHER);
                event.accept(RegistryBIBI.GREEN_FEATHER);
                event.accept(RegistryBIBI.LIGHT_BLUE_FEATHER);
                event.accept(RegistryBIBI.LIGHT_GRAY_FEATHER);
                event.accept(RegistryBIBI.LIME_FEATHER);
                event.accept(RegistryBIBI.MAGENTA_FEATHER);
                event.accept(RegistryBIBI.ORANGE_FEATHER);
                event.accept(RegistryBIBI.PINK_FEATHER);
                event.accept(RegistryBIBI.PURPLE_FEATHER);
                event.accept(RegistryBIBI.RED_FEATHER);
                event.accept(RegistryBIBI.YELLOW_FEATHER);
                event.accept(RegistryBIBI.GOLDEN_FEATHER);

            }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) { // ~~~SPAWN EGGS TABS~~~

            //Spawn eggs go here!

        }
        }
    public static void register(IEventBus eventBus) {
        CREATIVETABSREGISTRY.register(eventBus);
    }
}
