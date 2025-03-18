
package net.offllneplayer.opminecraft.init;

import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.block.*;
import net.offllneplayer.opminecraft.block.chiseledore.*;
import net.offllneplayer.opminecraft.block.crash.*;
import net.offllneplayer.opminecraft.block.cryingbricks.*;
import net.offllneplayer.opminecraft.block.cryingtiles.*;
import net.offllneplayer.opminecraft.block.densestones.*;
import net.offllneplayer.opminecraft.block.densestones.densestonebricks.*;
import net.offllneplayer.opminecraft.block.densestones.densestonetiles.*;
import net.offllneplayer.opminecraft.block.furnaces.*;
import net.offllneplayer.opminecraft.block.stonetiles.*;
import net.offllneplayer.opminecraft.item.*;

import java.util.function.Supplier;

public class RegistryIBBI {

/*--------------------------------------------------------------------------------------------*/
	/*[Declare Registries]*/

	public static final DeferredRegister.Items ITEMSREGISTRY = DeferredRegister.createItems(OPMinecraft.Mod_ID);

	public static final DeferredRegister.Blocks BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks FR_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks FR_EPIC_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks UNCOMMON_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks RARE_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);

/*--------------------------------------------------------------------------------------------*/
	/*[Declare Blocks]*/

	public static final DeferredBlock<Block> BLOCK_OF_CHARCOAL = registerBlock("block_of_charcoal", BlockofCharcoalBlock::new);

	public static final DeferredBlock<Block> BLOCK_OF_CRYING_INGOTS = registerBlock("block_of_crying_ingots", BlockofCryingIngotsBlock::new);
	public static final DeferredBlock<Block> CRYING_ESSENCE = registerBlock("crying_essence", CryingEssenceBlock::new);

	public static final DeferredBlock<Block> CRYING_BRICKS = registerBlock("crying_bricks", CryingBricksBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_BUTTON = registerBlock("crying_brick_button", CryingBrickButtonBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_FENCE = registerBlock("crying_brick_fence", CryingBrickFenceBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_FENCE_GATE = registerBlock("crying_brick_fence_gate", CryingBrickFenceGateBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_PRESSURE_PLATE = registerBlock("crying_brick_pressure_plate", CryingBrickPressurePlateBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_SLAB = registerBlock("crying_brick_slab", CryingBrickSlabBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_STAIRS = registerBlock("crying_brick_stairs", CryingBrickStairsBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_TRAPDOOR = registerBlock("crying_brick_trapdoor", CryingBrickTrapdoorBlock::new);
	public static final DeferredBlock<Block> CRYING_BRICK_WALL = registerBlock("crying_brick_wall", CryingBrickWallBlock::new);

	public static final DeferredBlock<Block> CRYING_TILES = registerBlock("crying_tiles", CryingTilesBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_BUTTON = registerBlock("crying_tile_button", CryingTileButtonBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_FENCE = registerBlock("crying_tile_fence", CryingTileFenceBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_FENCE_GATE = registerBlock("crying_tile_fence_gate", CryingTileFenceGateBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_PRESSURE_PLATE = registerBlock("crying_tile_pressure_plate", CryingTilePressurePlateBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_SLAB = registerBlock("crying_tile_slab", CryingTileSlabBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_STAIRS = registerBlock("crying_tile_stairs", CryingTileStairsBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_TRAPDOOR = registerBlock("crying_tile_trapdoor", CryingTileTrapdoorBlock::new);
	public static final DeferredBlock<Block> CRYING_TILE_WALL = registerBlock("crying_tile_wall", CryingTileWallBlock::new);

	public static final DeferredBlock<Block> CHISELED_DIAMOND = registerBlock("chiseled_diamond", ChiseledDiamondBlock::new);
	public static final DeferredBlock<Block> CHISELED_GOLD = registerBlock("chiseled_gold", ChiseledGoldBlock::new);
	public static final DeferredBlock<Block> CHISELED_IRON = registerBlock("chiseled_iron", ChiseledIronBlock::new);

	public static final DeferredBlock<Block> DENSE_STONE = registerBlock("dense_stone", DenseStoneBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BUTTON = registerBlock("dense_stone_button", DenseStoneButtonBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_FENCE = registerBlock("dense_stone_fence", DenseStoneFenceBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_FENCE_GATE = registerBlock("dense_stone_fence_gate", DenseStoneFenceGateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_PRESSURE_PLATE = registerBlock("dense_stone_pressure_plate", DenseStonePressurePlateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_SLAB = registerBlock("dense_stone_slab", DenseStoneSlabBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_STAIRS = registerBlock("dense_stone_stairs", DenseStoneStairsBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TRAPDOOR = registerBlock("dense_stone_trapdoor", DenseStoneTrapdoorBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_WALL = registerBlock("dense_stone_wall",  DenseStoneWallBlock::new);

	public static final DeferredBlock<Block> DENSE_STONE_BRICKS = registerBlock("dense_stone_bricks", DenseStoneBricksBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_BUTTON = registerBlock("dense_stone_brick_button", DenseStoneBrickButtonBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_FENCE = registerBlock("dense_stone_brick_fence", DenseStoneBrickFenceBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_FENCE_GATE = registerBlock("dense_stone_brick_fence_gate", DenseStoneBrickFenceGateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_PRESSURE_PLATE = registerBlock("dense_stone_brick_pressure_plate", DenseStoneBrickPressurePlateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_SLAB = registerBlock("dense_stone_brick_slab", DenseStoneBrickSlabBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_STAIRS = registerBlock("dense_stone_brick_stairs", DenseStoneBrickStairsBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_TRAPDOOR = registerBlock("dense_stone_brick_trapdoor", DenseStoneBrickTrapdoorBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_WALL = registerBlock("dense_stone_brick_wall", DenseStoneBrickWallBlock::new);

	public static final DeferredBlock<Block> DENSE_STONE_TILES = registerBlock("dense_stone_tiles", DenseStoneTilesBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_BUTTON = registerBlock("dense_stone_tile_button", DenseStoneTileButtonBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_FENCE = registerBlock("dense_stone_tile_fence", DenseStoneTileFenceBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_FENCE_GATE = registerBlock("dense_stone_tile_fence_gate", DenseStoneTileFenceGateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_PRESSURE_PLATE = registerBlock("dense_stone_tile_pressure_plate", DenseStoneTilePressurePlateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_SLAB = registerBlock("dense_stone_tile_slab", DenseStoneTileSlabBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_STAIRS = registerBlock("dense_stone_tile_stairs", DenseStoneTileStairsBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_TRAPDOOR = registerBlock("dense_stone_tile_trapdoor", DenseStoneTileTrapdoorBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_WALL = registerBlock("dense_stone_tile_wall", DenseStoneTileWallBlock::new);

	public static final DeferredBlock<Block> COPPER_FURNACE = registerBlock("copper_furnace", CopperFurnaceBlock::new);
	public static final DeferredBlock<Block> DIAMOND_FURNACE = registerBlock("diamond_furnace", DiamondFurnaceBlock::new);
	public static final DeferredBlock<Block> GOLD_FURNACE = registerBlock("gold_furnace", GoldFurnaceBlock::new);
	public static final DeferredBlock<Block> IRON_FURNACE = registerBlock("iron_furnace", IronFurnaceBlock::new);

	public static final DeferredBlock<Block> STONE_TILES = registerBlock("stone_tiles", StoneTilesBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_BUTTON = registerBlock("stone_tile_button", StoneTileButtonBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_FENCE = registerBlock("stone_tile_fence", StoneTileFenceBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_FENCE_GATE = registerBlock("stone_tile_fence_gate", StoneTileFenceGateBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_PRESSURE_PLATE = registerBlock("stone_tile_pressure_plate", StoneTilePressurePlateBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_SLAB = registerBlock("stone_tile_slab", StoneTileSlabBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_STAIRS = registerBlock("stone_tile_stairs", StoneTileStairsBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_TRAPDOOR = registerBlock("stone_tile_trapdoor", StoneTileTrapdoorBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_WALL = registerBlock("stone_tile_wall", StoneTileWallBlock::new);

	public static final DeferredBlock<Block> CHISELED_NETHERITE = registerFRBlock("chiseled_netherite", ChiseledNetheriteBlock::new);
	public static final DeferredBlock<Block> NETHERITE_FURNACE = registerFRBlock("netherite_furnace", NetheriteFurnaceBlock::new);

	// public static final DeferredBlock<Block> BLOCK_OF_FIRERES_EPICNESS = registerFREpicBlock("block_of_crying_ingots", BlockofCryingIngotsBlock::new);

	public static final DeferredBlock<Block> BOUNCE_CRATE = registerUncommonBlock("bounce_crate", BounceCrateBlock::new);
	public static final DeferredBlock<Block> CRASH_CRATE = registerUncommonBlock("crash_crate", CrashCrateBlock::new);
	public static final DeferredBlock<Block> CRASH_TNT = registerUncommonBlock("crash_tnt", CrashTNTBlock::new);
	public static final DeferredBlock<Block> NITRO = registerUncommonBlock("nitro", NitroBlock::new);
	public static final DeferredBlock<Block> FLOWERING_PITCHER_PLANT = registerUncommonBlock("flowering_pitcher_plant", FloweringPitcherPlantBlock::new);

	public static final DeferredBlock<Block> AKU_AKU_CRATE = registerRareBlock("aku_aku_crate", AkuAkuCrateBlock::new);
	public static final DeferredBlock<Block> WUMPA_PLANT = registerRareBlock("wumpa_plant", WumpaPlantBlock::new);

/*--------------------------------------------------------------------------------------------*/
	/*[Register Items]*/

	public static final DeferredItem<Item> AKU_AKU_MASK = ITEMSREGISTRY.register("aku_aku_mask", AkuAkuMaskItem::new);
	public static final DeferredItem<Item> WUMPA_FRUIT = ITEMSREGISTRY.register("wumpa_fruit", WumpaFruitItem::new);

	public static final DeferredItem<Item> TOTEM_OF_LIFE = ITEMSREGISTRY.register("totem_of_life", TotemOfLifeItem::new);

	public static final DeferredItem<Item> CHISEL = ITEMSREGISTRY.register("chisel", ChiselItem::new);
	public static final DeferredItem<Item> SCULK_HAMMER = ITEMSREGISTRY.register("sculk_hammer", SculkHammerItem::new);

	public static final DeferredItem<Item> CRYING_ESSENCE_BUCKET = ITEMSREGISTRY.register("crying_essence_bucket", CryingEssenceItem::new);
	public static final DeferredItem<Item> CRYING_INGOT = ITEMSREGISTRY.register("crying_ingot", CryingIngotItem::new);
	public static final DeferredItem<Item> CRYING_SMITHING_TEMPLATE = ITEMSREGISTRY.register("crying_smithing_template", CryingSmithingTemplateItem::new);

	public static final DeferredItem<Item> CRYING_SWORD = ITEMSREGISTRY.register("crying_sword", CryingSwordItem::new);
	public static final DeferredItem<Item> CRYING_AXE = ITEMSREGISTRY.register("crying_axe", CryingAxeItem::new);
	public static final DeferredItem<Item> CRYING_PICKAXE = ITEMSREGISTRY.register("crying_pickaxe", CryingPickaxeItem::new);
	public static final DeferredItem<Item> CRYING_SHOVEL = ITEMSREGISTRY.register("crying_shovel", CryingShovelItem::new);
	public static final DeferredItem<Item> CRYING_SICKLE = ITEMSREGISTRY.register("crying_sickle", CryingSickleItem::new);
	public static final DeferredItem<Item> CRYING_PAXEL = ITEMSREGISTRY.register("crying_paxel", CryingPaxelItem::new);

	public static final DeferredItem<Item> CRYING_HELMET = ITEMSREGISTRY.register("crying_helmet", CryingItem.Helmet::new);
	public static final DeferredItem<Item> CRYING_CHESTPLATE = ITEMSREGISTRY.register("crying_chestplate", CryingItem.Chestplate::new);
	public static final DeferredItem<Item> CRYING_LEGGINGS = ITEMSREGISTRY.register("crying_leggings", CryingItem.Leggings::new);
	public static final DeferredItem<Item> CRYING_BOOTS = ITEMSREGISTRY.register("crying_boots", CryingItem.Boots::new);

	public static final DeferredItem<Item> CRYING_HORSE_ARMOR = ITEMSREGISTRY.register("crying_horse_armor",
			() -> new AnimalArmorItem(CryingItem.ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
	//-----------------------------------------------------Kaupenjoe shoutout for providing this^

	public static final DeferredItem<TNTStickItem> TNT_STICK = ITEMSREGISTRY.register("tnt_stick", TNTStickItem::new);

	public static final DeferredItem<DynamiteStickItem> DYNAMITE_STICK = ITEMSREGISTRY.register("dynamite_stick", DynamiteStickItem::new);

/*--------------------------------------------------------------------------------------------*/
	/*[Register Blocks]*/

	private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKSREGISTRY.register(name, block);
		registerBlockItem(name, toReturn); return toReturn;
	}

	private static <T extends Block> DeferredBlock<T> registerFRBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = FR_BLOCKSREGISTRY.register(name, block);
		registerFRBlockItem(name, toReturn); return toReturn;
	}

	private static <T extends Block> DeferredBlock<T> registerFREpicBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = FR_EPIC_BLOCKSREGISTRY.register(name, block);
		registerFREpicBlockItem(name, toReturn); return toReturn;
	}

	private static <T extends Block> DeferredBlock<T> registerUncommonBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = UNCOMMON_BLOCKSREGISTRY.register(name, block);
		registerUncommonBlockItem(name, toReturn); return toReturn;
	}

	private static <T extends Block> DeferredBlock<T> registerRareBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = RARE_BLOCKSREGISTRY.register(name, block);
		registerRareBlockItem(name, toReturn); return toReturn;
	}

/*--------------------------------------------------------------------------------------------*/
	/*[Register BlockItems]*/

	private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
		ITEMSREGISTRY.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().stacksTo(64)));
	}

	private static <T extends Block> void registerFRBlockItem(String name, DeferredBlock<T> block) {
		ITEMSREGISTRY.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().stacksTo(64).fireResistant()));
	}

	private static <T extends Block> void registerFREpicBlockItem(String name, DeferredBlock<T> block) {
		ITEMSREGISTRY.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC)));
	}

	private static <T extends Block> void registerUncommonBlockItem(String name, DeferredBlock<T> block) {
		ITEMSREGISTRY.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	}

	private static <T extends Block> void registerRareBlockItem(String name, DeferredBlock<T> block) {
		ITEMSREGISTRY.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));
	}

/*--------------------------------------------------------------------------------------------*/
	/*[Register EventBus]*/

	public static void registerItems(IEventBus eventBus) {ITEMSREGISTRY.register(eventBus);}

	public static void registerBlocks(IEventBus eventBus) {BLOCKSREGISTRY.register(eventBus);}
	public static void registerFRBlocks(IEventBus eventBus) {FR_BLOCKSREGISTRY.register(eventBus);}
	public static void registerFREpicBlocks(IEventBus eventBus) {FR_EPIC_BLOCKSREGISTRY.register(eventBus);}
	public static void registerUncommonBlocks(IEventBus eventBus) {UNCOMMON_BLOCKSREGISTRY.register(eventBus);}
	public static void registerRareBlocks(IEventBus eventBus) {RARE_BLOCKSREGISTRY.register(eventBus);}

}
