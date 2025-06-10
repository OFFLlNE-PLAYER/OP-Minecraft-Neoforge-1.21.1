
package net.offllneplayer.opminecraft.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.block.GoldenBedBlock;
import net.offllneplayer.opminecraft.block.OnyxLampBlock;
import net.offllneplayer.opminecraft.block.charcoal.*;
import net.offllneplayer.opminecraft.block.chiseledore.*;
import net.offllneplayer.opminecraft.block.crash.crates.crashtnt.CrashTNTBlock;
import net.offllneplayer.opminecraft.block.crash.crates.crate.BounceCrateBlock;
import net.offllneplayer.opminecraft.block.crash.crates.akuaku.AkuAkuCrateBlock;
import net.offllneplayer.opminecraft.block.crash.crates.crate.CrashCrateBlock;
import net.offllneplayer.opminecraft.block.crash.crates.nitro.NitroBlock;
import net.offllneplayer.opminecraft.block.crash.wumpaplant.FloweringPitcherPlantBlock;
import net.offllneplayer.opminecraft.block.crash.wumpaplant.WumpaPlantBlock;
import net.offllneplayer.opminecraft.block.crying.blockofcryingingots.BlockofCryingIngotsBlock;
import net.offllneplayer.opminecraft.block.crying.essence.CryingEssenceBlock;
import net.offllneplayer.opminecraft.block.crying.cryingbricks.*;
import net.offllneplayer.opminecraft.block.crying.cryingtiles.*;
import net.offllneplayer.opminecraft.block.densestones.*;
import net.offllneplayer.opminecraft.block.furnaces.furnace.*;
import net.offllneplayer.opminecraft.block.onyx.*;
import net.offllneplayer.opminecraft.block.stonetiles.*;
import net.offllneplayer.opminecraft.iwe.gunblade.GunbladeMaterial;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetMaterial;
import net.offllneplayer.opminecraft.iwe.opsw0rd.OPSwordItem;
import net.offllneplayer.opminecraft.iwe.opsw0rd.OPSwordMaterial;
import net.offllneplayer.opminecraft.item.*;

import net.offllneplayer.opminecraft.item.crash.akuaku.AkuAkuMaskItem;
import net.offllneplayer.opminecraft.item.crash.wumpafruit.WumpaFruitItem;
import net.offllneplayer.opminecraft.item.crying.*;
import net.offllneplayer.opminecraft.item.tol.TotemOfLifeItem;
import net.offllneplayer.opminecraft.iwe.gunblade.GunbladeItem;
import net.offllneplayer.opminecraft.iwe.hatchet.HatchetItem;
import net.offllneplayer.opminecraft.iwe.SMBSuperFan.SMBSuperFanItem;
import net.offllneplayer.opminecraft.iwe.pistol.PistolGunMaterial;
import net.offllneplayer.opminecraft.iwe.pistol.PistolItem;
import net.offllneplayer.opminecraft.iwe.tntstick.TNTStickItem;

import java.util.function.Supplier;

public class RegistryBIBI {

/*--------------------------------------------------------------------------------------------*/
	/*[Declare Registries]*/
	public static final DeferredRegister.Items ITEMSREGISTRY = DeferredRegister.createItems(OPMinecraft.Mod_ID);

	public static final DeferredRegister.Blocks BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks FR_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks FR_EPIC_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks UNCOMMON_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);
	public static final DeferredRegister.Blocks RARE_BLOCKSREGISTRY = DeferredRegister.createBlocks(OPMinecraft.Mod_ID);

/*-----------------------------------------------------------------------------------------------------------------------*/
	/*[Declare Blocks]*/
	public static final DeferredBlock<Block> BLOCK_OF_TEST = registerBlock("block_of_test", BlockofOnyxBlock::new);

	public static final DeferredBlock<Block> BLOCK_OF_CHARCOAL = registerBlock("block_of_charcoal", BlockofCharcoalBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BUTTON = registerBlock("charcoal_button", CharcoalButtonBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_FENCE = registerBlock("charcoal_fence", CharcoalFenceBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_FENCE_GATE = registerBlock("charcoal_fence_gate", CharcoalFenceGateBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_PRESSURE_PLATE = registerBlock("charcoal_pressure_plate", CharcoalPressurePlateBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_SLAB = registerBlock("charcoal_slab", CharcoalSlabBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_STAIRS = registerBlock("charcoal_stairs", CharcoalStairsBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TRAPDOOR = registerBlock("charcoal_trapdoor", CharcoalTrapdoorBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_WALL = registerBlock("charcoal_wall", CharcoalWallBlock::new);

	public static final DeferredBlock<Block> CHARCOAL_BRICKS = registerBlock("charcoal_bricks", BlockofCharcoalBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_BUTTON = registerBlock("charcoal_brick_button", CharcoalButtonBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_FENCE = registerBlock("charcoal_brick_fence", CharcoalFenceBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_FENCE_GATE = registerBlock("charcoal_brick_fence_gate", CharcoalFenceGateBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_PRESSURE_PLATE = registerBlock("charcoal_brick_pressure_plate", CharcoalPressurePlateBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_SLAB = registerBlock("charcoal_brick_slab", CharcoalSlabBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_STAIRS = registerBlock("charcoal_brick_stairs", CharcoalStairsBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_TRAPDOOR = registerBlock("charcoal_brick_trapdoor", CharcoalTrapdoorBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_BRICK_WALL = registerBlock("charcoal_brick_wall", CharcoalWallBlock::new);

	public static final DeferredBlock<Block> CHARCOAL_TILES = registerBlock("charcoal_tiles", BlockofCharcoalBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_BUTTON = registerBlock("charcoal_tile_button", CharcoalButtonBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_FENCE = registerBlock("charcoal_tile_fence", CharcoalFenceBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_FENCE_GATE = registerBlock("charcoal_tile_fence_gate", CharcoalFenceGateBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_PRESSURE_PLATE = registerBlock("charcoal_tile_pressure_plate", CharcoalPressurePlateBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_SLAB = registerBlock("charcoal_tile_slab", CharcoalSlabBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_STAIRS = registerBlock("charcoal_tile_stairs", CharcoalStairsBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_TRAPDOOR = registerBlock("charcoal_tile_trapdoor", CharcoalTrapdoorBlock::new);
	public static final DeferredBlock<Block> CHARCOAL_TILE_WALL = registerBlock("charcoal_tile_wall", CharcoalWallBlock::new);

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
	public static final DeferredBlock<Block> DENSE_STONE_WALL = registerBlock("dense_stone_wall", DenseStoneWallBlock::new);

	public static final DeferredBlock<Block> DENSE_STONE_BRICKS = registerBlock("dense_stone_bricks", DenseStoneBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_BUTTON = registerBlock("dense_stone_brick_button", DenseStoneButtonBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_FENCE = registerBlock("dense_stone_brick_fence", DenseStoneFenceBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_FENCE_GATE = registerBlock("dense_stone_brick_fence_gate", DenseStoneFenceGateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_PRESSURE_PLATE = registerBlock("dense_stone_brick_pressure_plate", DenseStonePressurePlateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_SLAB = registerBlock("dense_stone_brick_slab", DenseStoneSlabBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_STAIRS = registerBlock("dense_stone_brick_stairs", DenseStoneStairsBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_TRAPDOOR = registerBlock("dense_stone_brick_trapdoor", DenseStoneTrapdoorBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_BRICK_WALL = registerBlock("dense_stone_brick_wall", DenseStoneWallBlock::new);

	public static final DeferredBlock<Block> DENSE_STONE_TILES = registerBlock("dense_stone_tiles", DenseStoneBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_BUTTON = registerBlock("dense_stone_tile_button", DenseStoneButtonBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_FENCE = registerBlock("dense_stone_tile_fence", DenseStoneFenceBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_FENCE_GATE = registerBlock("dense_stone_tile_fence_gate", DenseStoneFenceGateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_PRESSURE_PLATE = registerBlock("dense_stone_tile_pressure_plate", DenseStonePressurePlateBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_SLAB = registerBlock("dense_stone_tile_slab", DenseStoneSlabBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_STAIRS = registerBlock("dense_stone_tile_stairs", DenseStoneStairsBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_TRAPDOOR = registerBlock("dense_stone_tile_trapdoor", DenseStoneTrapdoorBlock::new);
	public static final DeferredBlock<Block> DENSE_STONE_TILE_WALL = registerBlock("dense_stone_tile_wall", DenseStoneWallBlock::new);

	public static final DeferredBlock<Block> COPPER_FURNACE = registerBlock("copper_furnace", CopperFurnaceBlock::new);
	public static final DeferredBlock<Block> DIAMOND_FURNACE = registerBlock("diamond_furnace", DiamondFurnaceBlock::new);
	public static final DeferredBlock<Block> GOLD_FURNACE = registerBlock("gold_furnace", GoldFurnaceBlock::new);
	public static final DeferredBlock<Block> IRON_FURNACE = registerBlock("iron_furnace", IronFurnaceBlock::new);

	public static final DeferredBlock<Block> BLOCK_OF_ONYX = registerBlock("block_of_onyx", BlockofOnyxBlock::new);

	public static final DeferredBlock<Block> ONYX_LAMP_BLACK = registerBlock("onyx_lamp_black", () -> new OnyxLampBlock(DyeColor.BLACK));
	public static final DeferredBlock<Block> ONYX_LAMP_BLUE = registerBlock("onyx_lamp_blue", () -> new OnyxLampBlock(DyeColor.BLUE));
	public static final DeferredBlock<Block> ONYX_LAMP_BROWN = registerBlock("onyx_lamp_brown", () -> new OnyxLampBlock(DyeColor.BROWN));
	public static final DeferredBlock<Block> ONYX_LAMP_CYAN = registerBlock("onyx_lamp_cyan", () -> new OnyxLampBlock(DyeColor.CYAN));
	public static final DeferredBlock<Block> ONYX_LAMP_GRAY = registerBlock("onyx_lamp_gray", () -> new OnyxLampBlock(DyeColor.GRAY));
	public static final DeferredBlock<Block> ONYX_LAMP_GREEN = registerBlock("onyx_lamp_green", () -> new OnyxLampBlock(DyeColor.GREEN));
	public static final DeferredBlock<Block> ONYX_LAMP_LIGHT_BLUE = registerBlock("onyx_lamp_light_blue", () -> new OnyxLampBlock(DyeColor.LIGHT_BLUE));
	public static final DeferredBlock<Block> ONYX_LAMP_LIGHT_GRAY = registerBlock("onyx_lamp_light_gray", () -> new OnyxLampBlock(DyeColor.LIGHT_GRAY));
	public static final DeferredBlock<Block> ONYX_LAMP_LIME = registerBlock("onyx_lamp_lime", () -> new OnyxLampBlock(DyeColor.LIME));
	public static final DeferredBlock<Block> ONYX_LAMP_MAGENTA = registerBlock("onyx_lamp_magenta", () -> new OnyxLampBlock(DyeColor.MAGENTA));
	public static final DeferredBlock<Block> ONYX_LAMP_ORANGE = registerBlock("onyx_lamp_orange", () -> new OnyxLampBlock(DyeColor.ORANGE));
	public static final DeferredBlock<Block> ONYX_LAMP_PINK = registerBlock("onyx_lamp_pink", () -> new OnyxLampBlock(DyeColor.PINK));
	public static final DeferredBlock<Block> ONYX_LAMP_PURPLE = registerBlock("onyx_lamp_purple", () -> new OnyxLampBlock(DyeColor.PURPLE));
	public static final DeferredBlock<Block> ONYX_LAMP_RED = registerBlock("onyx_lamp_red", () -> new OnyxLampBlock(DyeColor.RED));
	public static final DeferredBlock<Block> ONYX_LAMP_WHITE = registerBlock("onyx_lamp_white", () -> new OnyxLampBlock(DyeColor.WHITE));
	public static final DeferredBlock<Block> ONYX_LAMP_YELLOW = registerBlock("onyx_lamp_yellow", () -> new OnyxLampBlock(DyeColor.YELLOW));


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

	public static final DeferredBlock<Block> GOLDEN_BED = registerUncommonBlock("golden_bed", GoldenBedBlock::new);

	public static final DeferredBlock<Block> AKU_AKU_CRATE = registerRareBlock("aku_aku_crate", AkuAkuCrateBlock::new);
	public static final DeferredBlock<Block> WUMPA_PLANT = registerRareBlock("wumpa_plant", WumpaPlantBlock::new);

/*-----------------------------------------------------------------------------------------------------------------------*/
	/*[Register Items]*/
	public static final DeferredItem<Item> TEST_ITEM = ITEMSREGISTRY.registerItem("test_item", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));

	public static final DeferredItem<Item> ANCIENT_CHUNK = ITEMSREGISTRY.registerItem("ancient_chunk", Item::new, new Item.Properties().stacksTo(64).fireResistant());

	public static final DeferredItem<Item> CHISEL = ITEMSREGISTRY.register("chisel", ChiselItem::new);
	public static final DeferredItem<Item> SCULK_HAMMER = ITEMSREGISTRY.register("sculk_hammer", SculkHammerItem::new);

	public static final DeferredItem<Item> AKU_AKU_MASK = ITEMSREGISTRY.register("aku_aku_mask", AkuAkuMaskItem::new);
	public static final DeferredItem<Item> WUMPA_FRUIT = ITEMSREGISTRY.register("wumpa_fruit", WumpaFruitItem::new);

	public static final DeferredItem<Item> CRUDE_ALEXANDRITE = ITEMSREGISTRY.registerItem("crude_alexandrite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> ALEXANDRITE = ITEMSREGISTRY.registerItem("alexandrite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_ALEXANDRITE = ITEMSREGISTRY.registerItem("flawless_alexandrite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_ALEXANDRITE = ITEMSREGISTRY.registerItem("perfect_alexandrite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_ALEXANDRITE = ITEMSREGISTRY.registerItem("brilliant_alexandrite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_ANDALUSITE = ITEMSREGISTRY.registerItem("crude_andalusite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> ANDALUSITE = ITEMSREGISTRY.registerItem("andalusite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_ANDALUSITE = ITEMSREGISTRY.registerItem("flawless_andalusite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_ANDALUSITE = ITEMSREGISTRY.registerItem("perfect_andalusite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_ANDALUSITE = ITEMSREGISTRY.registerItem("brilliant_andalusite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_AQUAMARINE = ITEMSREGISTRY.registerItem("crude_aquamarine", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> AQUAMARINE = ITEMSREGISTRY.registerItem("aquamarine", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_AQUAMARINE = ITEMSREGISTRY.registerItem("flawless_aquamarine", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_AQUAMARINE = ITEMSREGISTRY.registerItem("perfect_aquamarine", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_AQUAMARINE = ITEMSREGISTRY.registerItem("brilliant_aquamarine", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_CHRYSOBERYL = ITEMSREGISTRY.registerItem("crude_chrysoberyl", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> CHRYSOBERYL = ITEMSREGISTRY.registerItem("chrysoberyl", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_CHRYSOBERYL = ITEMSREGISTRY.registerItem("flawless_chrysoberyl", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_CHRYSOBERYL = ITEMSREGISTRY.registerItem("perfect_chrysoberyl", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_CHRYSOBERYL = ITEMSREGISTRY.registerItem("brilliant_chrysoberyl", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_CORUNDUM = ITEMSREGISTRY.registerItem("crude_corundum", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> CORUNDUM = ITEMSREGISTRY.registerItem("corundum", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_CORUNDUM = ITEMSREGISTRY.registerItem("flawless_corundum", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_CORUNDUM = ITEMSREGISTRY.registerItem("perfect_corundum", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_CORUNDUM = ITEMSREGISTRY.registerItem("brilliant_corundum", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_CYMOPHANE = ITEMSREGISTRY.registerItem("crude_cymophane", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> CYMOPHANE = ITEMSREGISTRY.registerItem("cymophane", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_CYMOPHANE = ITEMSREGISTRY.registerItem("flawless_cymophane", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_CYMOPHANE = ITEMSREGISTRY.registerItem("perfect_cymophane", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_CYMOPHANE = ITEMSREGISTRY.registerItem("brilliant_cymophane", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	
	public static final DeferredItem<Item> CRUDE_DRAGONITE = ITEMSREGISTRY.registerItem("crude_dragonite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> DRAGONITE = ITEMSREGISTRY.registerItem("dragonite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_DRAGONITE = ITEMSREGISTRY.registerItem("flawless_dragonite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_DRAGONITE = ITEMSREGISTRY.registerItem("perfect_dragonite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_DRAGONITE = ITEMSREGISTRY.registerItem("brilliant_dragonite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_JADEITE = ITEMSREGISTRY.registerItem("crude_jadeite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> JADEITE = ITEMSREGISTRY.registerItem("jadeite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_JADEITE = ITEMSREGISTRY.registerItem("flawless_jadeite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_JADEITE = ITEMSREGISTRY.registerItem("perfect_jadeite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_JADEITE = ITEMSREGISTRY.registerItem("brilliant_jadeite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_OPALITE = ITEMSREGISTRY.registerItem("crude_opalite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> OPALITE = ITEMSREGISTRY.registerItem("opalite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_OPALITE = ITEMSREGISTRY.registerItem("flawless_opalite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_OPALITE = ITEMSREGISTRY.registerItem("perfect_opalite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_OPALITE = ITEMSREGISTRY.registerItem("brilliant_opalite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_PADPARADSCHA = ITEMSREGISTRY.registerItem("crude_padparadscha", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> PADPARADSCHA = ITEMSREGISTRY.registerItem("padparadscha", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_PADPARADSCHA = ITEMSREGISTRY.registerItem("flawless_padparadscha", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_PADPARADSCHA = ITEMSREGISTRY.registerItem("perfect_padparadscha", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_PADPARADSCHA = ITEMSREGISTRY.registerItem("brilliant_padparadscha", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_RUBY = ITEMSREGISTRY.registerItem("crude_ruby", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> RUBY = ITEMSREGISTRY.registerItem("ruby", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_RUBY = ITEMSREGISTRY.registerItem("flawless_ruby", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_RUBY = ITEMSREGISTRY.registerItem("perfect_ruby", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_RUBY = ITEMSREGISTRY.registerItem("brilliant_ruby", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_SAPPHIRE = ITEMSREGISTRY.registerItem("crude_sapphire", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> SAPPHIRE = ITEMSREGISTRY.registerItem("sapphire", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_SAPPHIRE = ITEMSREGISTRY.registerItem("flawless_sapphire", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_SAPPHIRE = ITEMSREGISTRY.registerItem("perfect_sapphire", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_SAPPHIRE = ITEMSREGISTRY.registerItem("brilliant_sapphire", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_SCAPOLITE = ITEMSREGISTRY.registerItem("crude_scapolite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> SCAPOLITE = ITEMSREGISTRY.registerItem("scapolite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_SCAPOLITE = ITEMSREGISTRY.registerItem("flawless_scapolite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_SCAPOLITE = ITEMSREGISTRY.registerItem("perfect_scapolite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_SCAPOLITE = ITEMSREGISTRY.registerItem("brilliant_scapolite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_STAUROLITE = ITEMSREGISTRY.registerItem("crude_staurolite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> STAUROLITE = ITEMSREGISTRY.registerItem("staurolite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_STAUROLITE = ITEMSREGISTRY.registerItem("flawless_staurolite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_STAUROLITE = ITEMSREGISTRY.registerItem("perfect_staurolite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_STAUROLITE = ITEMSREGISTRY.registerItem("brilliant_staurolite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_TANZANITE = ITEMSREGISTRY.registerItem("crude_tanzanite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> TANZANITE = ITEMSREGISTRY.registerItem("tanzanite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_TANZANITE = ITEMSREGISTRY.registerItem("flawless_tanzanite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_TANZANITE = ITEMSREGISTRY.registerItem("perfect_tanzanite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_TANZANITE = ITEMSREGISTRY.registerItem("brilliant_tanzanite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_AMETHYST = ITEMSREGISTRY.registerItem("crude_amethyst", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_AMETHYST = ITEMSREGISTRY.registerItem("flawless_amethyst", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_AMETHYST = ITEMSREGISTRY.registerItem("perfect_amethyst", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_AMETHYST = ITEMSREGISTRY.registerItem("brilliant_amethyst", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_EMERALD = ITEMSREGISTRY.registerItem("crude_emerald", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_EMERALD = ITEMSREGISTRY.registerItem("flawless_emerald", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_EMERALD = ITEMSREGISTRY.registerItem("perfect_emerald", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_EMERALD = ITEMSREGISTRY.registerItem("brilliant_emerald", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_DIAMOND = ITEMSREGISTRY.registerItem("crude_diamond", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_DIAMOND = ITEMSREGISTRY.registerItem("flawless_diamond", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_DIAMOND = ITEMSREGISTRY.registerItem("perfect_diamond", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_DIAMOND = ITEMSREGISTRY.registerItem("brilliant_diamond", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> GEMSTONE_DUST = ITEMSREGISTRY.registerItem("gemstone_dust", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));

	public static final DeferredItem<Item> BLACK_FEATHER = ITEMSREGISTRY.registerItem("black_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> BLUE_FEATHER = ITEMSREGISTRY.registerItem("blue_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> BROWN_FEATHER = ITEMSREGISTRY.registerItem("brown_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> CYAN_FEATHER = ITEMSREGISTRY.registerItem("cyan_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> GRAY_FEATHER = ITEMSREGISTRY.registerItem("gray_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> GREEN_FEATHER = ITEMSREGISTRY.registerItem("green_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> LIGHT_BLUE_FEATHER = ITEMSREGISTRY.registerItem("light_blue_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> LIGHT_GRAY_FEATHER = ITEMSREGISTRY.registerItem("light_gray_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> LIME_FEATHER = ITEMSREGISTRY.registerItem("lime_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> MAGENTA_FEATHER = ITEMSREGISTRY.registerItem("magenta_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> ORANGE_FEATHER = ITEMSREGISTRY.registerItem("orange_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> PINK_FEATHER = ITEMSREGISTRY.registerItem("pink_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> PURPLE_FEATHER = ITEMSREGISTRY.registerItem("purple_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> RED_FEATHER = ITEMSREGISTRY.registerItem("red_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> YELLOW_FEATHER = ITEMSREGISTRY.registerItem("yellow_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> GOLDEN_FEATHER = ITEMSREGISTRY.registerItem("golden_feather", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));

	public static final DeferredItem<Item> CRYING_ESSENCE_BUCKET = ITEMSREGISTRY.register("crying_essence_bucket", CryingEssenceItem::new);
	public static final DeferredItem<Item> CRYING_INGOT = ITEMSREGISTRY.register("crying_ingot", CryingIngotItem::new);
	public static final DeferredItem<Item> CRYING_SMITHING_TEMPLATE = ITEMSREGISTRY.register("crying_smithing_template", CryingSmithingTemplateItem::new);

	public static final DeferredItem<Item> CRYING_AXE = ITEMSREGISTRY.register("crying_axe", CryingAxeItem::new);
	public static final DeferredItem<Item> CRYING_PICKAXE = ITEMSREGISTRY.register("crying_pickaxe", CryingPickaxeItem::new);
	public static final DeferredItem<Item> CRYING_SHOVEL = ITEMSREGISTRY.register("crying_shovel", CryingShovelItem::new);
	public static final DeferredItem<Item> CRYING_HOE = ITEMSREGISTRY.register("crying_hoe", CryingHoeItem::new);
	public static final DeferredItem<Item> CRYING_PAXEL = ITEMSREGISTRY.register("crying_paxel", CryingPaxelItem::new);
	public static final DeferredItem<Item> CRYING_SWHOPAXEL = ITEMSREGISTRY.register("crying_swhopaxel", CryingSwhopaxelItem::new);

	public static final DeferredItem<Item> CRYING_HELMET = ITEMSREGISTRY.register("crying_helmet", CryingItem.Helmet::new);
	public static final DeferredItem<Item> CRYING_CHESTPLATE = ITEMSREGISTRY.register("crying_chestplate", CryingItem.Chestplate::new);
	public static final DeferredItem<Item> CRYING_LEGGINGS = ITEMSREGISTRY.register("crying_leggings", CryingItem.Leggings::new);
	public static final DeferredItem<Item> CRYING_BOOTS = ITEMSREGISTRY.register("crying_boots", CryingItem.Boots::new);

	public static final DeferredItem<Item> CRYING_RESIN = ITEMSREGISTRY.registerItem("crying_resin", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(64).fireResistant());

	public static final DeferredItem<Item> CRYING_HORSE_ARMOR = ITEMSREGISTRY.register("crying_horse_armor",
			() -> new AnimalArmorItem(CryingItem.ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

	public static final DeferredItem<Item> KAUPENJOE_SMITHING_TEMPLATE = ITEMSREGISTRY.register("kaupenjoe_armor_trim_smithing_template",
			() -> SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "kaupenjoe")));
	//-----------------------------------------------------Kaupenjoe tribute for providing horse armor and custom trims^


	public static final Supplier<HatchetItem> WOODEN_HATCHET = ITEMSREGISTRY.register("wooden_hatchet",
		() -> new HatchetItem(HatchetMaterial.WOODEN));

	public static final Supplier<HatchetItem> STONE_HATCHET = ITEMSREGISTRY.register("stone_hatchet",
		() -> new HatchetItem(HatchetMaterial.STONE));

	public static final Supplier<HatchetItem> IRON_HATCHET = ITEMSREGISTRY.register("iron_hatchet",
		() -> new HatchetItem(HatchetMaterial.IRON));

	public static final Supplier<HatchetItem> GOLDEN_HATCHET = ITEMSREGISTRY.register("golden_hatchet",
		() -> new HatchetItem(HatchetMaterial.GOLDEN));

	public static final Supplier<HatchetItem> DIAMOND_HATCHET = ITEMSREGISTRY.register("diamond_hatchet",
		() -> new HatchetItem(HatchetMaterial.DIAMOND));

	public static final Supplier<HatchetItem> NETHERITE_HATCHET = ITEMSREGISTRY.register("netherite_hatchet",
		() -> new HatchetItem(HatchetMaterial.NETHERITE));

	public static final Supplier<HatchetItem> ONYX_HATCHET = ITEMSREGISTRY.register("onyx_hatchet",
		() -> new HatchetItem(HatchetMaterial.ONYX));

	public static final Supplier<HatchetItem> TITAN_HATCHET = ITEMSREGISTRY.register("titan_hatchet",
		() -> new HatchetItem(HatchetMaterial.TITAN));

	public static final Supplier<HatchetItem> CRYING_HATCHET = ITEMSREGISTRY.register("crying_hatchet",
		() -> new HatchetItem(HatchetMaterial.CRYING));


	public static final DeferredItem<PrototypeGunbladeItem> PROTOTYPE_GUNBLADE = ITEMSREGISTRY.register("prototype_gunblade", PrototypeGunbladeItem::new);

	public static final Supplier<GunbladeItem> GOLDEN_GUNBLADE = ITEMSREGISTRY.register("golden_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.GOLDEN));

	public static final Supplier<GunbladeItem> DIAMOND_GUNBLADE = ITEMSREGISTRY.register("diamond_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.DIAMOND));

	public static final Supplier<GunbladeItem> NETHERITE_GUNBLADE = ITEMSREGISTRY.register("netherite_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.NETHERITE));


	public static final Supplier<GunbladeItem> ONYX_GUNBLADE = ITEMSREGISTRY.register("onyx_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.ONYX));

	public static final Supplier<GunbladeItem> TITAN_GUNBLADE = ITEMSREGISTRY.register("titan_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.TITAN));

	public static final Supplier<GunbladeItem> CRYING_GUNBLADE = ITEMSREGISTRY.register("crying_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.CRYING));

	/*
		public static final Supplier<OPSw0rdItem> CLAY_SWORD = ITEMSREGISTRY.register("clay_sword",
		() -> new OPSwordItem(OPSw0rdMaterialMap.CLAY, 5.5F, -2F));
*/

	public static final Supplier<OPSwordItem> CRYING_SWORD = ITEMSREGISTRY.register("crying_sword",
		() -> new OPSwordItem(OPSwordMaterial.CRYING));

	public static final Supplier<OPSwordItem> CLAYMORE = ITEMSREGISTRY.register("claymore_sword",
		() -> new OPSwordItem(OPSwordMaterial.CLAY));


	public static final DeferredItem<TNTStickItem> TNT_STICK = ITEMSREGISTRY.register("tnt_stick", TNTStickItem::new);

	public static final DeferredItem<SMBSuperFanItem> SMB_SUPER_FAN = ITEMSREGISTRY.register("smb_super_fan", SMBSuperFanItem::new);

	public static final Supplier<PistolItem> TITAN_SAMURAI_EDGE = ITEMSREGISTRY.register("titan_samurai_edge",
		() -> new PistolItem(PistolGunMaterial.TITAN_SAMURAI_EDGE));
	public static final Supplier<PistolItem> VALENTINE_SAMURAI_EDGE = ITEMSREGISTRY.register("valentine_samurai_edge",
		() -> new PistolItem(PistolGunMaterial.VALENTINE_SAMURAI_EDGE));

	public static final DeferredItem<Item> PISTOL_BULLET = ITEMSREGISTRY.registerItem("pistol_bullet", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> NINEmm_PARABELLUM_ROUNDS = ITEMSREGISTRY.registerItem("ninemm_parabellum_rounds", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));

	public static final DeferredItem<Item> TOTEM_OF_LIFE = ITEMSREGISTRY.register("totem_of_life", TotemOfLifeItem::new);

	// public static final DeferredItem<Item> TOTEM_OF_UNCRYING = ITEMSREGISTRY.register("totem_of_uncrying", TotemOfUncryingItem::new);

/*-----------------------------------------------------------------------------------------------------------------------*/
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

/*-----------------------------------------------------------------------------------------------------------------------*/
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
}
