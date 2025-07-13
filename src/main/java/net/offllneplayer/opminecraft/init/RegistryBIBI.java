
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
import net.offllneplayer.opminecraft.block.ancientchests.AncientChestBlock;
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
import net.offllneplayer.opminecraft.block.furnaces.OPFurnaceBlock;
import net.offllneplayer.opminecraft.block.furnaces.OPFurnaceMaterial;
import net.offllneplayer.opminecraft.block.ancientchests.AncientChestWoodMaterial;
import net.offllneplayer.opminecraft.block.ancientchests.AncientChestTrimMaterial;
import net.offllneplayer.opminecraft.block.onyx.*;
import net.offllneplayer.opminecraft.block.stonetiles.*;
import net.offllneplayer.opminecraft.iwe.beretta.PistolMaterial;
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
import net.offllneplayer.opminecraft.iwe.beretta.PistolItem;
import net.offllneplayer.opminecraft.iwe.sw0rd.Sw0rdItem;
import net.offllneplayer.opminecraft.iwe.sw0rd.Sw0rdMaterial;
import net.offllneplayer.opminecraft.iwe.tntstick.TNTStickItem;

import java.util.HashMap;
import java.util.Map;
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

	public static final DeferredBlock<OPFurnaceBlock> COPPER_FURNACE = registerBlock("copper_furnace",
			() -> new OPFurnaceBlock(OPFurnaceMaterial.COPPER));
	public static final DeferredBlock<OPFurnaceBlock> IRON_FURNACE = registerBlock("iron_furnace",
			() -> new OPFurnaceBlock(OPFurnaceMaterial.IRON));
	public static final DeferredBlock<OPFurnaceBlock> GOLD_FURNACE = registerBlock("gold_furnace",
			() -> new OPFurnaceBlock(OPFurnaceMaterial.GOLD));
	public static final DeferredBlock<OPFurnaceBlock> DIAMOND_FURNACE = registerBlock("diamond_furnace",
			() -> new OPFurnaceBlock(OPFurnaceMaterial.DIAMOND));
	public static final DeferredBlock<OPFurnaceBlock> NETHERITE_FURNACE = registerFRBlock("netherite_furnace",
			() -> new OPFurnaceBlock(OPFurnaceMaterial.NETHERITE));


	public static final DeferredBlock<Block> CHISELED_IRON = registerBlock("chiseled_iron", ChiseledIronBlock::new);
	public static final DeferredBlock<Block> CHISELED_GOLD = registerBlock("chiseled_gold", ChiseledGoldBlock::new);
	public static final DeferredBlock<Block> CHISELED_DIAMOND = registerBlock("chiseled_diamond", ChiseledDiamondBlock::new);
	public static final DeferredBlock<Block> CHISELED_NETHERITE = registerFRBlock("chiseled_netherite", ChiseledNetheriteBlock::new);

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



	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	public static final Map<String, Supplier<AncientChestBlock>> ANCIENT_CHEST_VARIANTS = new HashMap<>();

	// Helper method for registering loot chest blocks
	private static Supplier<AncientChestBlock> registerLootChestBlock(String name, AncientChestWoodMaterial woodMaterial, AncientChestTrimMaterial trimMaterial) {
		Supplier<AncientChestBlock> supplier = registerBlock(name, () -> new AncientChestBlock(woodMaterial, trimMaterial));
		ANCIENT_CHEST_VARIANTS.put(name, supplier);
		return supplier;
	}

	//Acacia variants
	public static final Supplier<AncientChestBlock> ACACIA_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_copper_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> ACACIA_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_iron_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> ACACIA_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_gold_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> ACACIA_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_diamond_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> ACACIA_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_netherite_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> ACACIA_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_amethyst_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> ACACIA_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_emerald_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> ACACIA_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_lapis_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> ACACIA_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_quartz_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> ACACIA_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"acacia_redstone_trim_ancient_chest", AncientChestWoodMaterial.ACACIA, AncientChestTrimMaterial.REDSTONE);

	//Bamboo variants
	public static final Supplier<AncientChestBlock> BAMBOO_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_copper_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> BAMBOO_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_iron_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> BAMBOO_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_gold_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> BAMBOO_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_diamond_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> BAMBOO_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_netherite_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> BAMBOO_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_amethyst_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> BAMBOO_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_emerald_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> BAMBOO_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_lapis_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> BAMBOO_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_quartz_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> BAMBOO_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"bamboo_redstone_trim_ancient_chest", AncientChestWoodMaterial.BAMBOO, AncientChestTrimMaterial.REDSTONE);

	//Birch variants
	public static final Supplier<AncientChestBlock> BIRCH_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_copper_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> BIRCH_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_iron_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> BIRCH_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_gold_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> BIRCH_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_diamond_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> BIRCH_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_netherite_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> BIRCH_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_amethyst_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> BIRCH_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_emerald_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> BIRCH_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_lapis_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> BIRCH_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_quartz_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> BIRCH_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"birch_redstone_trim_ancient_chest", AncientChestWoodMaterial.BIRCH, AncientChestTrimMaterial.REDSTONE);

	//Cherry variants
	public static final Supplier<AncientChestBlock> CHERRY_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_copper_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> CHERRY_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_iron_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> CHERRY_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_gold_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> CHERRY_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_diamond_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> CHERRY_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_netherite_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> CHERRY_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_amethyst_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> CHERRY_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_emerald_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> CHERRY_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_lapis_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> CHERRY_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_quartz_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> CHERRY_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"cherry_redstone_trim_ancient_chest", AncientChestWoodMaterial.CHERRY, AncientChestTrimMaterial.REDSTONE);

	//Dark Oak variants
	public static final Supplier<AncientChestBlock> DARK_OAK_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_copper_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> DARK_OAK_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_iron_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> DARK_OAK_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_gold_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> DARK_OAK_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_diamond_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> DARK_OAK_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_netherite_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> DARK_OAK_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_amethyst_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> DARK_OAK_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_emerald_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> DARK_OAK_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_lapis_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> DARK_OAK_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_quartz_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> DARK_OAK_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"dark_oak_redstone_trim_ancient_chest", AncientChestWoodMaterial.DARK_OAK, AncientChestTrimMaterial.REDSTONE);

	//Jungle variants
	public static final Supplier<AncientChestBlock> JUNGLE_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_copper_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> JUNGLE_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_iron_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> JUNGLE_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_gold_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> JUNGLE_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_diamond_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> JUNGLE_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_netherite_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> JUNGLE_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_amethyst_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> JUNGLE_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_emerald_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> JUNGLE_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_lapis_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> JUNGLE_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_quartz_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> JUNGLE_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"jungle_redstone_trim_ancient_chest", AncientChestWoodMaterial.JUNGLE, AncientChestTrimMaterial.REDSTONE);

	//Mangrove variants
	public static final Supplier<AncientChestBlock> MANGROVE_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_copper_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> MANGROVE_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_iron_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> MANGROVE_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_gold_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> MANGROVE_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_diamond_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> MANGROVE_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_netherite_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> MANGROVE_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_amethyst_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> MANGROVE_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_emerald_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> MANGROVE_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_lapis_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> MANGROVE_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_quartz_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> MANGROVE_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"mangrove_redstone_trim_ancient_chest", AncientChestWoodMaterial.MANGROVE, AncientChestTrimMaterial.REDSTONE);

	//Oak variants
	public static final Supplier<AncientChestBlock> OAK_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_copper_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> OAK_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_iron_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> OAK_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_gold_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> OAK_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_diamond_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> OAK_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_netherite_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> OAK_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_amethyst_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> OAK_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_emerald_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> OAK_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_lapis_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> OAK_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_quartz_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> OAK_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"oak_redstone_trim_ancient_chest", AncientChestWoodMaterial.OAK, AncientChestTrimMaterial.REDSTONE);

	//Spruce variants
	public static final Supplier<AncientChestBlock> SPRUCE_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_copper_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> SPRUCE_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_iron_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> SPRUCE_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_gold_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> SPRUCE_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_diamond_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> SPRUCE_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_netherite_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> SPRUCE_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_amethyst_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> SPRUCE_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_emerald_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> SPRUCE_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_lapis_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> SPRUCE_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_quartz_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> SPRUCE_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"spruce_redstone_trim_ancient_chest", AncientChestWoodMaterial.SPRUCE, AncientChestTrimMaterial.REDSTONE);

	//Crimson variants
	public static final Supplier<AncientChestBlock> CRIMSON_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_copper_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> CRIMSON_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_iron_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> CRIMSON_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_gold_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> CRIMSON_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_diamond_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> CRIMSON_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_netherite_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> CRIMSON_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_amethyst_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> CRIMSON_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_emerald_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> CRIMSON_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_lapis_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> CRIMSON_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_quartz_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> CRIMSON_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"crimson_redstone_trim_ancient_chest", AncientChestWoodMaterial.CRIMSON, AncientChestTrimMaterial.REDSTONE);

	//Warped variants
	public static final Supplier<AncientChestBlock> WARPED_COPPER_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_copper_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.COPPER);
	public static final Supplier<AncientChestBlock> WARPED_IRON_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_iron_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.IRON);
	public static final Supplier<AncientChestBlock> WARPED_GOLD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_gold_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.GOLD);
	public static final Supplier<AncientChestBlock> WARPED_DIAMOND_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_diamond_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.DIAMOND);
	public static final Supplier<AncientChestBlock> WARPED_NETHERITE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_netherite_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.NETHERITE);
	public static final Supplier<AncientChestBlock> WARPED_AMETHYST_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_amethyst_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.AMETHYST);
	public static final Supplier<AncientChestBlock> WARPED_EMERALD_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_emerald_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.EMERALD);
	public static final Supplier<AncientChestBlock> WARPED_LAPIS_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_lapis_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.LAPIS);
	public static final Supplier<AncientChestBlock> WARPED_QUARTZ_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_quartz_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.QUARTZ);
	public static final Supplier<AncientChestBlock> WARPED_REDSTONE_TRIM_ANCIENT_CHEST = registerLootChestBlock(
		"warped_redstone_trim_ancient_chest", AncientChestWoodMaterial.WARPED, AncientChestTrimMaterial.REDSTONE);

	
	public static final DeferredBlock<Block> STONE_TILES = registerBlock("stone_tiles", StoneTilesBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_BUTTON = registerBlock("stone_tile_button", StoneTileButtonBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_FENCE = registerBlock("stone_tile_fence", StoneTileFenceBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_FENCE_GATE = registerBlock("stone_tile_fence_gate", StoneTileFenceGateBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_PRESSURE_PLATE = registerBlock("stone_tile_pressure_plate", StoneTilePressurePlateBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_SLAB = registerBlock("stone_tile_slab", StoneTileSlabBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_STAIRS = registerBlock("stone_tile_stairs", StoneTileStairsBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_TRAPDOOR = registerBlock("stone_tile_trapdoor", StoneTileTrapdoorBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_WALL = registerBlock("stone_tile_wall", StoneTileWallBlock::new);

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


		public static final Supplier<Sw0rdItem> CLAY_SWORD = ITEMSREGISTRY.register("clay_sword",
		() -> new Sw0rdItem(Sw0rdMaterial.CLAY));


	public static final Supplier<OPSwordItem> CRYING_SWORD = ITEMSREGISTRY.register("crying_sword",
		() -> new OPSwordItem(OPSwordMaterial.CRYING));

	public static final Supplier<OPSwordItem> CLAYMORE = ITEMSREGISTRY.register("claymore_sword",
		() -> new OPSwordItem(OPSwordMaterial.CLAY));


	public static final DeferredItem<TNTStickItem> TNT_STICK = ITEMSREGISTRY.register("tnt_stick", TNTStickItem::new);

	public static final DeferredItem<SMBSuperFanItem> SMB_SUPER_FAN = ITEMSREGISTRY.register("smb_super_fan", SMBSuperFanItem::new);


	public static final Supplier<PistolItem> REDFIELD_BERETTA = ITEMSREGISTRY.register("redfield_beretta",
		() -> new PistolItem(PistolMaterial.REDFIELD_BERETTA));
	public static final Supplier<PistolItem> WESKER_BERETTA = ITEMSREGISTRY.register("wesker_beretta",
		() -> new PistolItem(PistolMaterial.WESKER_BERETTA));
	public static final Supplier<PistolItem> VALENTINE_BERETTA = ITEMSREGISTRY.register("valentine_beretta",
		() -> new PistolItem(PistolMaterial.VALENTINE_BERETTA));

	public static final Supplier<PistolItem> GOLDEN_BERETTA = ITEMSREGISTRY.register("golden_beretta",
			() -> new PistolItem(PistolMaterial.GOLDEN_BERETTA));
	public static final Supplier<PistolItem> DIAMOND_BERETTA = ITEMSREGISTRY.register("diamond_beretta",
			() -> new PistolItem(PistolMaterial.DIAMOND_BERETTA));
	public static final Supplier<PistolItem> ONYX_BERETTA = ITEMSREGISTRY.register("onyx_beretta",
			() -> new PistolItem(PistolMaterial.ONYX_BERETTA));

	public static final Supplier<PistolItem> TITAN_BERETTA = ITEMSREGISTRY.register("titan_beretta",
			() -> new PistolItem(PistolMaterial.TITAN_BERETTA));
	public static final Supplier<PistolItem> TITAN_HANDCANNON = ITEMSREGISTRY.register("titan_handcannon",
			() -> new PistolItem(PistolMaterial.TITAN_HANDCANNON));
	public static final Supplier<PistolItem> TITAN_DESERT_EAGLE = ITEMSREGISTRY.register("titan_desert_eagle",
			() -> new PistolItem(PistolMaterial.TITAN_DESERT_EAGLE));
	public static final Supplier<PistolItem> TITAN_REVOLVER = ITEMSREGISTRY.register("titan_revolver",
			() -> new PistolItem(PistolMaterial.TITAN_REVOLVER));

	public static final Supplier<PistolItem> PROTEKTOR_BERETTA = ITEMSREGISTRY.register("protektor_beretta",
			() -> new PistolItem(PistolMaterial.PROTEKTOR_BERETTA));
	public static final Supplier<PistolItem> PROTEKTOR_HANDCANNON = ITEMSREGISTRY.register("protektor_handcannon",
			() -> new PistolItem(PistolMaterial.PROTEKTOR_HANDCANNON));
	public static final Supplier<PistolItem> PROTEKTOR_DESERT_EAGLE = ITEMSREGISTRY.register("protektor_desert_eagle",
			() -> new PistolItem(PistolMaterial.PROTEKTOR_DESERT_EAGLE));
	public static final Supplier<PistolItem> PROTEKTOR_REVOLVER = ITEMSREGISTRY.register("protektor_revolver",
			() -> new PistolItem(PistolMaterial.PROTEKTOR_REVOLVER));

		public static final Supplier<PistolItem> VALENTINE_REVOLVER = ITEMSREGISTRY.register("valentine_revolver",
			() -> new PistolItem(PistolMaterial.VALENTINE_REVOLVER));


	public static final DeferredItem<Item> PISTOL_BULLET = ITEMSREGISTRY.registerItem("beretta_bullet", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));


	public static final DeferredItem<Item> FOURTY_FOUR_S_AND_W_ROUNDS = ITEMSREGISTRY.registerItem("fourty_four_s_and_w_rounds", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(6));
	public static final DeferredItem<Item> FIFTY_S_AND_W_ROUNDS = ITEMSREGISTRY.registerItem("fifty_s_and_w_rounds", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(6));

	public static final DeferredItem<Item> FIFTY_AE_ROUNDS = ITEMSREGISTRY.registerItem("fifty_ae_rounds", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(8));
	public static final DeferredItem<Item> NINEmm_PARABELLUM_ROUNDS = ITEMSREGISTRY.registerItem("ninemm_parabellum_rounds", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32));
	public static final DeferredItem<Item> TENmm_PARABELLUM_ROUNDS = ITEMSREGISTRY.registerItem("tenmm_parabellum_rounds", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(20));

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

 /*-----------------------------------------------------------------------------------------------------------------------*/
}
