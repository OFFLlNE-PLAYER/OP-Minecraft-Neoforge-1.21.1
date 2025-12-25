
package net.offllneplayer.opminecraft.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.blocks._block.GoldenBedBlock;
import net.offllneplayer.opminecraft.blocks._block._geode.building.*;
import net.offllneplayer.opminecraft.blocks._block._geode.geode.*;
import net.offllneplayer.opminecraft.blocks._block._geode.geode_method.GeodeMaterial;
import net.offllneplayer.opminecraft.blocks._block._oplamp.OPLampBlock;
import net.offllneplayer.opminecraft.blocks._block._oplamp.OPLampColor;
import net.offllneplayer.opminecraft.blocks._block._oplamp.OPLampMaterial;
import net.offllneplayer.opminecraft.blocks._block._ore.chiseled_ore.ChiseledDiamondBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.chiseled_ore.ChiseledGoldBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.chiseled_ore.ChiseledIronBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.chiseled_ore.ChiseledNetheriteBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.onyx.BlockofOnyxBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.onyx.OnyxBlockBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.titanium.BlockofTitaniumBlock;
import net.offllneplayer.opminecraft.blocks._block._ore.titanium.TitaniumBlockBlock;
import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestBlock;
import net.offllneplayer.opminecraft.blocks._block.charcoal.*;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.crashtnt.CrashTNTBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.crate.BounceCrateBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.akuaku.AkuAkuCrateBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.crate.CrashCrateBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.nitro.NitroBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.wumpaplant.FloweringPitcherPlantBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.wumpaplant.WumpaPlantBlock;
import net.offllneplayer.opminecraft.blocks._block.crying.blockofcryingingots.BlockofCryingIngotsBlock;
import net.offllneplayer.opminecraft.blocks._block.crying.essence.CryingEssenceBlock;
import net.offllneplayer.opminecraft.blocks._block.crying.cryingbricks.*;
import net.offllneplayer.opminecraft.blocks._block.crying.cryingtiles.*;
import net.offllneplayer.opminecraft.blocks._block.slate.*;
import net.offllneplayer.opminecraft.blocks._block._furnace.OPFurnaceBlock;
import net.offllneplayer.opminecraft.blocks._block._furnace.OPFurnaceMaterial;
import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestWoodMaterial;
import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestTrimMaterial;
import net.offllneplayer.opminecraft.blocks._block.stonetile.*;
import net.offllneplayer.opminecraft.items._item.ChiselItem;
import net.offllneplayer.opminecraft.items._item.PrototypeGunbladeItem;
import net.offllneplayer.opminecraft.items._item.SculkHammerItem;
import net.offllneplayer.opminecraft.items._item._musik_disk.*;
import net.offllneplayer.opminecraft.items._iwe.pistol.PistolMaterial;
import net.offllneplayer.opminecraft.items._iwe.gunblade.GunbladeMaterial;
import net.offllneplayer.opminecraft.items._iwe.hatchet.HatchetMaterial;
import net.offllneplayer.opminecraft.items._iwe.opsw0rd.OPSwordItem;
import net.offllneplayer.opminecraft.items._iwe.opsw0rd.OPSwordMaterial;

import net.offllneplayer.opminecraft.items._item.crash.akuaku.AkuAkuMaskItem;
import net.offllneplayer.opminecraft.items._item.crash.wumpafruit.WumpaFruitItem;
import net.offllneplayer.opminecraft.items._item.crying.*;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonColor;
import net.offllneplayer.opminecraft.items._item.tol.TotemOfLifeItem;
import net.offllneplayer.opminecraft.items._item.tou.TotemOfUncryingItem;
import net.offllneplayer.opminecraft.items._iwe.gunblade.GunbladeItem;
import net.offllneplayer.opminecraft.items._iwe.hatchet.HatchetItem;
import net.offllneplayer.opminecraft.items._iwe.SMBSuperFan.SMBSuperFanItem;
import net.offllneplayer.opminecraft.items._iwe.pistol.PistolItem;
import net.offllneplayer.opminecraft.items._iwe.sw0rd.Sw0rdItem;
import net.offllneplayer.opminecraft.items._iwe.sw0rd.Sw0rdMaterial;
import net.offllneplayer.opminecraft.items._iwe.tntstick.TNTStickItem;

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

	// public static final DeferredBlock<Block> BLOCK_OF_FIRERES_EPICNESS = registerFREpicBlock("block_of_crying_ingots", BlockofCryingIngotsBlock::new);

	public static final DeferredBlock<Block> BLOCK_OF_TEST =
			registerBlock("block_of_test", () -> new RotatedPillarBlock(Properties.of() // instead of block, "RotatedPillarBlock" used to have "axis" property
					.strength(3F, 3F)
					.requiresCorrectToolForDrops()
					.noOcclusion())); // no occ prevents culling of faces behind this bloc

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


	public static final DeferredBlock<Block> STONE_TILES = registerBlock("stone_tiles", StoneTilesBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_BUTTON = registerBlock("stone_tile_button", StoneTileButtonBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_FENCE = registerBlock("stone_tile_fence", StoneTileFenceBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_FENCE_GATE = registerBlock("stone_tile_fence_gate", StoneTileFenceGateBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_PRESSURE_PLATE = registerBlock("stone_tile_pressure_plate", StoneTilePressurePlateBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_SLAB = registerBlock("stone_tile_slab", StoneTileSlabBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_STAIRS = registerBlock("stone_tile_stairs", StoneTileStairsBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_TRAPDOOR = registerBlock("stone_tile_trapdoor", StoneTileTrapdoorBlock::new);
	public static final DeferredBlock<Block> STONE_TILE_WALL = registerBlock("stone_tile_wall", StoneTileWallBlock::new);


	public static final DeferredBlock<Block> SLATE = registerBlock("slate", SlateBlock::new);
	public static final DeferredBlock<Block> SLATE_BUTTON = registerBlock("slate_button", SlateButtonBlock::new);
	public static final DeferredBlock<Block> SLATE_FENCE = registerBlock("slate_fence", SlateFenceBlock::new);
	public static final DeferredBlock<Block> SLATE_FENCE_GATE = registerBlock("slate_fence_gate", SlateFenceGateBlock::new);
	public static final DeferredBlock<Block> SLATE_PRESSURE_PLATE = registerBlock("slate_pressure_plate", SlatePressurePlateBlock::new);
	public static final DeferredBlock<Block> SLATE_SLAB = registerBlock("slate_slab", SlateSlabBlock::new);
	public static final DeferredBlock<Block> SLATE_STAIRS = registerBlock("slate_stairs", SlateStairsBlock::new);
	public static final DeferredBlock<Block> SLATE_TRAPDOOR = registerBlock("slate_trapdoor", SlateTrapdoorBlock::new);
	public static final DeferredBlock<Block> SLATE_WALL = registerBlock("slate_wall", SlateWallBlock::new);

	public static final DeferredBlock<Block> COBBLED_SLATE = registerBlock("cobbled_slate",
			() -> new SlateBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_BUTTON = registerBlock("cobbled_slate_button",
			() -> new SlateButtonBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_FENCE = registerBlock("cobbled_slate_fence",
			() -> new SlateFenceBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_FENCE_GATE = registerBlock("cobbled_slate_fence_gate",
			() -> new SlateFenceGateBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_PRESSURE_PLATE = registerBlock("cobbled_slate_pressure_plate",
			() -> new SlatePressurePlateBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_SLAB = registerBlock("cobbled_slate_slab",
			() -> new SlateSlabBlock(Properties.of().strength(3.5F, 9F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_STAIRS = registerBlock("cobbled_slate_stairs",
			() -> new SlateStairsBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_TRAPDOOR = registerBlock("cobbled_slate_trapdoor",
			() -> new SlateTrapdoorBlock(Properties.of().strength(3.5F, 8F)));
	public static final DeferredBlock<Block> COBBLED_SLATE_WALL = registerBlock("cobbled_slate_wall",
			() -> new SlateWallBlock(Properties.of().strength(3.5F, 8F)));

	public static final DeferredBlock<Block> SMOOTH_SLATE = registerBlock("smooth_slate",
			() -> new SlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_BUTTON = registerBlock("smooth_slate_button",
			() -> new SlateButtonBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_FENCE = registerBlock("smooth_slate_fence",
			() -> new SlateFenceBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_FENCE_GATE = registerBlock("smooth_slate_fence_gate",
			() -> new SlateFenceGateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_PRESSURE_PLATE = registerBlock("smooth_slate_pressure_plate",
			() -> new SlatePressurePlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_SLAB = registerBlock("smooth_slate_slab",
			() -> new SlateSlabBlock(Properties.of().strength(3.5F, 12F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_STAIRS = registerBlock("smooth_slate_stairs",
			() -> new SlateStairsBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_TRAPDOOR = registerBlock("smooth_slate_trapdoor",
			() -> new SlateTrapdoorBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_SLATE_WALL = registerBlock("smooth_slate_wall",
			() -> new SlateWallBlock(Properties.of().strength(3.5F, 10F)));

	public static final DeferredBlock<Block> SLATE_BRICKS = registerBlock("slate_bricks",
			() -> new SlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_BUTTON = registerBlock("slate_brick_button",
			() -> new SlateButtonBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_FENCE = registerBlock("slate_brick_fence",
			() -> new SlateFenceBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_FENCE_GATE = registerBlock("slate_brick_fence_gate",
			() -> new SlateFenceGateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_PRESSURE_PLATE = registerBlock("slate_brick_pressure_plate",
			() -> new SlatePressurePlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_SLAB = registerBlock("slate_brick_slab",
			() -> new SlateSlabBlock(Properties.of().strength(3.5F, 12F)));
	public static final DeferredBlock<Block> SLATE_BRICK_STAIRS = registerBlock("slate_brick_stairs",
			() -> new SlateStairsBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_TRAPDOOR = registerBlock("slate_brick_trapdoor",
			() -> new SlateTrapdoorBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_BRICK_WALL = registerBlock("slate_brick_wall",
			() -> new SlateWallBlock(Properties.of().strength(3.5F, 10F)));

	public static final DeferredBlock<Block> SLATE_TILES = registerBlock("slate_tiles",
			() -> new SlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_BUTTON = registerBlock("slate_tile_button",
			() -> new SlateButtonBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_FENCE = registerBlock("slate_tile_fence",
			() -> new SlateFenceBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_FENCE_GATE = registerBlock("slate_tile_fence_gate",
			() -> new SlateFenceGateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_PRESSURE_PLATE = registerBlock("slate_tile_pressure_plate",
			() -> new SlatePressurePlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_SLAB = registerBlock("slate_tile_slab",
			() -> new SlateSlabBlock(Properties.of().strength(3.5F, 12F)));
	public static final DeferredBlock<Block> SLATE_TILE_STAIRS = registerBlock("slate_tile_stairs",
			() -> new SlateStairsBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_TRAPDOOR = registerBlock("slate_tile_trapdoor",
			() -> new SlateTrapdoorBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SLATE_TILE_WALL = registerBlock("slate_tile_wall",
			() -> new SlateWallBlock(Properties.of().strength(3.5F, 10F)));

	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE = registerBlock("smooth_deepslate",
			() -> new SlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_BUTTON = registerBlock("smooth_deepslate_button",
			() -> new SlateButtonBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_FENCE = registerBlock("smooth_deepslate_fence",
			() -> new SlateFenceBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_FENCE_GATE = registerBlock("smooth_deepslate_fence_gate",
			() -> new SlateFenceGateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_PRESSURE_PLATE = registerBlock("smooth_deepslate_pressure_plate",
			() -> new SlatePressurePlateBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_SLAB = registerBlock("smooth_deepslate_slab",
			() -> new SlateSlabBlock(Properties.of().strength(3.5F, 12F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_STAIRS = registerBlock("smooth_deepslate_stairs",
			() -> new SlateStairsBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_TRAPDOOR = registerBlock("smooth_deepslate_trapdoor",
			() -> new SlateTrapdoorBlock(Properties.of().strength(3.5F, 10F)));
	public static final DeferredBlock<Block> SMOOTH_DEEPSLATE_WALL = registerBlock("smooth_deepslate_wall",
			() -> new SlateWallBlock(Properties.of().strength(3.5F, 10F)));


	public static final DeferredBlock<Block> CHISELED_IRON = registerBlock("chiseled_iron", ChiseledIronBlock::new);
	public static final DeferredBlock<Block> CHISELED_GOLD = registerBlock("chiseled_gold", ChiseledGoldBlock::new);
	public static final DeferredBlock<Block> CHISELED_DIAMOND = registerBlock("chiseled_diamond", ChiseledDiamondBlock::new);
	public static final DeferredBlock<Block> CHISELED_NETHERITE = registerFRBlock("chiseled_netherite", ChiseledNetheriteBlock::new);


	public static final DeferredBlock<Block> BLOCK_OF_ONYX = registerBlock("block_of_onyx", BlockofOnyxBlock::new);
	public static final DeferredBlock<Block> BLOCK_OF_TITANIUM = registerBlock("block_of_titanium", BlockofTitaniumBlock::new);
	public static final DeferredBlock<Block> ONYX_BLOCK = registerBlock("onyx_block", OnyxBlockBlock::new);
	public static final DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("titanium_block", TitaniumBlockBlock::new);


 // stone ore variants of geode-gems here
 // Place stone ores above deepslate ores, ABC order, using vanilla diamond_ore strength/resistance
 public static final DeferredBlock<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> ANDALUSITE_ORE = registerBlock("andalusite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> CHRYSOBERYL_ORE = registerBlock("chrysoberyl_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> CORUNDUM_ORE = registerBlock("corundum_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> CYMOPHANE_ORE = registerBlock("cymophane_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> DRAGONITE_ORE = registerBlock("dragonite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> JADEITE_ORE = registerBlock("jadeite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> OPALITE_ORE = registerBlock("opalite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> PADPARADSCHA_ORE = registerBlock("padparadscha_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> SCAPOLITE_ORE = registerBlock("scapolite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> STAUROLITE_ORE = registerBlock("staurolite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));
 public static final DeferredBlock<Block> TANZANITE_ORE = registerBlock("tanzanite_ore", () -> new Block(Properties.of().strength(3F, 3F).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> DEEPSLATE_ONYX_ORE = registerBlock("deepslate_onyx_ore", () -> new Block(Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore", () -> new Block(Properties.of().strength(5F, 4.2F).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlock("deepslate_alexandrite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_ANDALUSITE_ORE = registerBlock("deepslate_andalusite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_AQUAMARINE_ORE = registerBlock("deepslate_aquamarine_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_CHRYSOBERYL_ORE = registerBlock("deepslate_chrysoberyl_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_CORUNDUM_ORE = registerBlock("deepslate_corundum_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_CYMOPHANE_ORE = registerBlock("deepslate_cymophane_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_DRAGONITE_ORE = registerBlock("deepslate_dragonite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_JADEITE_ORE = registerBlock("deepslate_jadeite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_OPALITE_ORE = registerBlock("deepslate_opalite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_PADPARADSCHA_ORE = registerBlock("deepslate_padparadscha_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_SCAPOLITE_ORE = registerBlock("deepslate_scapolite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_STAUROLITE_ORE = registerBlock("deepslate_staurolite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_TANZANITE_ORE = registerBlock("deepslate_tanzanite_ore", () -> new Block(Properties.of().strength(4F, 3.5F).requiresCorrectToolForDrops()));


		// Geode natural blocks
	
		// Alexandrite
		public static final DeferredBlock<Block> BUDDING_ALEXANDRITE_BLOCK = registerBlock("budding_alexandrite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.ALEXANDRITE));
		public static final DeferredBlock<Block> SMALL_ALEXANDRITE_BUD = registerBlock("small_alexandrite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.ALEXANDRITE));
		public static final DeferredBlock<Block> MEDIUM_ALEXANDRITE_BUD = registerBlock("medium_alexandrite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.ALEXANDRITE));
		public static final DeferredBlock<Block> LARGE_ALEXANDRITE_BUD = registerBlock("large_alexandrite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.ALEXANDRITE));
		public static final DeferredBlock<Block> ALEXANDRITE_CLUSTER = registerBlock("alexandrite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.ALEXANDRITE));
		public static final DeferredBlock<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block", () -> new GeodeBlockBlock(GeodeMaterial.ALEXANDRITE));
		
	// Alexandrite building variants
	public static final DeferredBlock<Block> ALEXANDRITE_BUTTON = registerBlock("alexandrite_button", () -> new GeodeButtonBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_FENCE = registerBlock("alexandrite_fence", () -> new GeodeFenceBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_FENCE_GATE = registerBlock("alexandrite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_PRESSURE_PLATE = registerBlock("alexandrite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_SLAB = registerBlock("alexandrite_slab", () -> new GeodeSlabBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_STAIRS = registerBlock("alexandrite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_TRAPDOOR = registerBlock("alexandrite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.ALEXANDRITE));
	public static final DeferredBlock<Block> ALEXANDRITE_WALL = registerBlock("alexandrite_wall", () -> new GeodeWallBlock(GeodeMaterial.ALEXANDRITE));


		// Andalusite
		public static final DeferredBlock<Block> BUDDING_ANDALUSITE_BLOCK = registerBlock("budding_andalusite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.ANDALUSITE));
		public static final DeferredBlock<Block> SMALL_ANDALUSITE_BUD = registerBlock("small_andalusite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.ANDALUSITE));
		public static final DeferredBlock<Block> MEDIUM_ANDALUSITE_BUD = registerBlock("medium_andalusite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.ANDALUSITE));
		public static final DeferredBlock<Block> LARGE_ANDALUSITE_BUD = registerBlock("large_andalusite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.ANDALUSITE));
		public static final DeferredBlock<Block> ANDALUSITE_CLUSTER = registerBlock("andalusite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.ANDALUSITE));
		public static final DeferredBlock<Block> ANDALUSITE_BLOCK = registerBlock("andalusite_block", () -> new GeodeBlockBlock(GeodeMaterial.ANDALUSITE));

	// Andalusite building variants
	public static final DeferredBlock<Block> ANDALUSITE_BUTTON = registerBlock("andalusite_button", () -> new GeodeButtonBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_FENCE = registerBlock("andalusite_fence", () -> new GeodeFenceBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_FENCE_GATE = registerBlock("andalusite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_PRESSURE_PLATE = registerBlock("andalusite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_SLAB = registerBlock("andalusite_slab", () -> new GeodeSlabBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_STAIRS = registerBlock("andalusite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_TRAPDOOR = registerBlock("andalusite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.ANDALUSITE));
	public static final DeferredBlock<Block> ANDALUSITE_WALL = registerBlock("andalusite_wall", () -> new GeodeWallBlock(GeodeMaterial.ANDALUSITE));


		// Aquamarine
		public static final DeferredBlock<Block> BUDDING_AQUAMARINE_BLOCK = registerBlock("budding_aquamarine_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.AQUAMARINE));
		public static final DeferredBlock<Block> SMALL_AQUAMARINE_BUD = registerBlock("small_aquamarine_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.AQUAMARINE));
		public static final DeferredBlock<Block> MEDIUM_AQUAMARINE_BUD = registerBlock("medium_aquamarine_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.AQUAMARINE));
		public static final DeferredBlock<Block> LARGE_AQUAMARINE_BUD = registerBlock("large_aquamarine_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.AQUAMARINE));
		public static final DeferredBlock<Block> AQUAMARINE_CLUSTER = registerBlock("aquamarine_cluster", () -> new GeodeClusterBlock(GeodeMaterial.AQUAMARINE));
		public static final DeferredBlock<Block> AQUAMARINE_BLOCK = registerBlock("aquamarine_block", () -> new GeodeBlockBlock(GeodeMaterial.AQUAMARINE));

	// Aquamarine building variants
	public static final DeferredBlock<Block> AQUAMARINE_BUTTON = registerBlock("aquamarine_button", () -> new GeodeButtonBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_FENCE = registerBlock("aquamarine_fence", () -> new GeodeFenceBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_FENCE_GATE = registerBlock("aquamarine_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_PRESSURE_PLATE = registerBlock("aquamarine_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_SLAB = registerBlock("aquamarine_slab", () -> new GeodeSlabBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_STAIRS = registerBlock("aquamarine_stairs", () -> new GeodeStairsBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_TRAPDOOR = registerBlock("aquamarine_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.AQUAMARINE));
	public static final DeferredBlock<Block> AQUAMARINE_WALL = registerBlock("aquamarine_wall", () -> new GeodeWallBlock(GeodeMaterial.AQUAMARINE));


		// Chrysoberyl
		public static final DeferredBlock<Block> BUDDING_CHRYSOBERYL_BLOCK = registerBlock("budding_chrysoberyl_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.CHRYSOBERYL));
		public static final DeferredBlock<Block> SMALL_CHRYSOBERYL_BUD = registerBlock("small_chrysoberyl_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.CHRYSOBERYL));
		public static final DeferredBlock<Block> MEDIUM_CHRYSOBERYL_BUD = registerBlock("medium_chrysoberyl_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.CHRYSOBERYL));
		public static final DeferredBlock<Block> LARGE_CHRYSOBERYL_BUD = registerBlock("large_chrysoberyl_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.CHRYSOBERYL));
		public static final DeferredBlock<Block> CHRYSOBERYL_CLUSTER = registerBlock("chrysoberyl_cluster", () -> new GeodeClusterBlock(GeodeMaterial.CHRYSOBERYL));
		public static final DeferredBlock<Block> CHRYSOBERYL_BLOCK = registerBlock("chrysoberyl_block", () -> new GeodeBlockBlock(GeodeMaterial.CHRYSOBERYL));

	// Chrysoberyl building variants
	public static final DeferredBlock<Block> CHRYSOBERYL_BUTTON = registerBlock("chrysoberyl_button", () -> new GeodeButtonBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_FENCE = registerBlock("chrysoberyl_fence", () -> new GeodeFenceBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_FENCE_GATE = registerBlock("chrysoberyl_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_PRESSURE_PLATE = registerBlock("chrysoberyl_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_SLAB = registerBlock("chrysoberyl_slab", () -> new GeodeSlabBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_STAIRS = registerBlock("chrysoberyl_stairs", () -> new GeodeStairsBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_TRAPDOOR = registerBlock("chrysoberyl_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.CHRYSOBERYL));
	public static final DeferredBlock<Block> CHRYSOBERYL_WALL = registerBlock("chrysoberyl_wall", () -> new GeodeWallBlock(GeodeMaterial.CHRYSOBERYL));


		// Corundum
		public static final DeferredBlock<Block> BUDDING_CORUNDUM_BLOCK = registerBlock("budding_corundum_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.CORUNDUM));
		public static final DeferredBlock<Block> SMALL_CORUNDUM_BUD = registerBlock("small_corundum_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.CORUNDUM));
		public static final DeferredBlock<Block> MEDIUM_CORUNDUM_BUD = registerBlock("medium_corundum_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.CORUNDUM));
		public static final DeferredBlock<Block> LARGE_CORUNDUM_BUD = registerBlock("large_corundum_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.CORUNDUM));
		public static final DeferredBlock<Block> CORUNDUM_CLUSTER = registerBlock("corundum_cluster", () -> new GeodeClusterBlock(GeodeMaterial.CORUNDUM));
		public static final DeferredBlock<Block> CORUNDUM_BLOCK = registerBlock("corundum_block", () -> new GeodeBlockBlock(GeodeMaterial.CORUNDUM));

	// Corundum building variants
	public static final DeferredBlock<Block> CORUNDUM_BUTTON = registerBlock("corundum_button", () -> new GeodeButtonBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_FENCE = registerBlock("corundum_fence", () -> new GeodeFenceBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_FENCE_GATE = registerBlock("corundum_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_PRESSURE_PLATE = registerBlock("corundum_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_SLAB = registerBlock("corundum_slab", () -> new GeodeSlabBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_STAIRS = registerBlock("corundum_stairs", () -> new GeodeStairsBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_TRAPDOOR = registerBlock("corundum_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.CORUNDUM));
	public static final DeferredBlock<Block> CORUNDUM_WALL = registerBlock("corundum_wall", () -> new GeodeWallBlock(GeodeMaterial.CORUNDUM));


		// Cymophane
		public static final DeferredBlock<Block> BUDDING_CYMOPHANE_BLOCK = registerBlock("budding_cymophane_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.CYMOPHANE));
		public static final DeferredBlock<Block> SMALL_CYMOPHANE_BUD = registerBlock("small_cymophane_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.CYMOPHANE));
		public static final DeferredBlock<Block> MEDIUM_CYMOPHANE_BUD = registerBlock("medium_cymophane_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.CYMOPHANE));
		public static final DeferredBlock<Block> LARGE_CYMOPHANE_BUD = registerBlock("large_cymophane_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.CYMOPHANE));
		public static final DeferredBlock<Block> CYMOPHANE_CLUSTER = registerBlock("cymophane_cluster", () -> new GeodeClusterBlock(GeodeMaterial.CYMOPHANE));
		public static final DeferredBlock<Block> CYMOPHANE_BLOCK = registerBlock("cymophane_block", () -> new GeodeBlockBlock(GeodeMaterial.CYMOPHANE));

	// Cymophane building variants
	public static final DeferredBlock<Block> CYMOPHANE_BUTTON = registerBlock("cymophane_button", () -> new GeodeButtonBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_FENCE = registerBlock("cymophane_fence", () -> new GeodeFenceBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_FENCE_GATE = registerBlock("cymophane_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_PRESSURE_PLATE = registerBlock("cymophane_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_SLAB = registerBlock("cymophane_slab", () -> new GeodeSlabBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_STAIRS = registerBlock("cymophane_stairs", () -> new GeodeStairsBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_TRAPDOOR = registerBlock("cymophane_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.CYMOPHANE));
	public static final DeferredBlock<Block> CYMOPHANE_WALL = registerBlock("cymophane_wall", () -> new GeodeWallBlock(GeodeMaterial.CYMOPHANE));


		// Diamond
		public static final DeferredBlock<Block> BUDDING_DIAMOND_BLOCK = registerBlock("budding_diamond_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.DIAMOND));
		public static final DeferredBlock<Block> SMALL_DIAMOND_BUD = registerBlock("small_diamond_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.DIAMOND));
		public static final DeferredBlock<Block> MEDIUM_DIAMOND_BUD = registerBlock("medium_diamond_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.DIAMOND));
		public static final DeferredBlock<Block> LARGE_DIAMOND_BUD = registerBlock("large_diamond_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.DIAMOND));
		public static final DeferredBlock<Block> DIAMOND_CLUSTER = registerBlock("diamond_cluster", () -> new GeodeClusterBlock(GeodeMaterial.DIAMOND));
		public static final DeferredBlock<Block> DIAMOND_BLOCK = registerBlock("diamond_block", () -> new GeodeBlockBlock(GeodeMaterial.DIAMOND));

	// Diamond building variants
	public static final DeferredBlock<Block> DIAMOND_BUTTON = registerBlock("diamond_button", () -> new GeodeButtonBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_FENCE = registerBlock("diamond_fence", () -> new GeodeFenceBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_FENCE_GATE = registerBlock("diamond_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_PRESSURE_PLATE = registerBlock("diamond_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_SLAB = registerBlock("diamond_slab", () -> new GeodeSlabBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_STAIRS = registerBlock("diamond_stairs", () -> new GeodeStairsBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_TRAPDOOR = registerBlock("diamond_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.DIAMOND));
	public static final DeferredBlock<Block> DIAMOND_WALL = registerBlock("diamond_wall", () -> new GeodeWallBlock(GeodeMaterial.DIAMOND));


	// Dragonite
	public static final DeferredBlock<Block> BUDDING_DRAGONITE_BLOCK = registerBlock("budding_dragonite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> SMALL_DRAGONITE_BUD = registerBlock("small_dragonite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> MEDIUM_DRAGONITE_BUD = registerBlock("medium_dragonite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> LARGE_DRAGONITE_BUD = registerBlock("large_dragonite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_CLUSTER = registerBlock("dragonite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_BLOCK = registerBlock("dragonite_block", () -> new GeodeBlockBlock(GeodeMaterial.DRAGONITE));

	// Dragonite building variants
	public static final DeferredBlock<Block> DRAGONITE_BUTTON = registerBlock("dragonite_button", () -> new GeodeButtonBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_FENCE = registerBlock("dragonite_fence", () -> new GeodeFenceBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_FENCE_GATE = registerBlock("dragonite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_PRESSURE_PLATE = registerBlock("dragonite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_SLAB = registerBlock("dragonite_slab", () -> new GeodeSlabBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_STAIRS = registerBlock("dragonite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_TRAPDOOR = registerBlock("dragonite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.DRAGONITE));
	public static final DeferredBlock<Block> DRAGONITE_WALL = registerBlock("dragonite_wall", () -> new GeodeWallBlock(GeodeMaterial.DRAGONITE));


	// Emerald
	public static final DeferredBlock<Block> BUDDING_EMERALD_BLOCK = registerBlock("budding_emerald_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> SMALL_EMERALD_BUD = registerBlock("small_emerald_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> MEDIUM_EMERALD_BUD = registerBlock("medium_emerald_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> LARGE_EMERALD_BUD = registerBlock("large_emerald_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_CLUSTER = registerBlock("emerald_cluster", () -> new GeodeClusterBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_BLOCK = registerBlock("emerald_block", () -> new GeodeBlockBlock(GeodeMaterial.EMERALD));

	// Emerald building variants
	public static final DeferredBlock<Block> EMERALD_BUTTON = registerBlock("emerald_button", () -> new GeodeButtonBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_FENCE = registerBlock("emerald_fence", () -> new GeodeFenceBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_FENCE_GATE = registerBlock("emerald_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_PRESSURE_PLATE = registerBlock("emerald_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_SLAB = registerBlock("emerald_slab", () -> new GeodeSlabBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_STAIRS = registerBlock("emerald_stairs", () -> new GeodeStairsBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_TRAPDOOR = registerBlock("emerald_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.EMERALD));
	public static final DeferredBlock<Block> EMERALD_WALL = registerBlock("emerald_wall", () -> new GeodeWallBlock(GeodeMaterial.EMERALD));


		// Jadeite
		public static final DeferredBlock<Block> BUDDING_JADEITE_BLOCK = registerBlock("budding_jadeite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.JADEITE));
		public static final DeferredBlock<Block> SMALL_JADEITE_BUD = registerBlock("small_jadeite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.JADEITE));
		public static final DeferredBlock<Block> MEDIUM_JADEITE_BUD = registerBlock("medium_jadeite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.JADEITE));
		public static final DeferredBlock<Block> LARGE_JADEITE_BUD = registerBlock("large_jadeite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.JADEITE));
		public static final DeferredBlock<Block> JADEITE_CLUSTER = registerBlock("jadeite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.JADEITE));
		public static final DeferredBlock<Block> JADEITE_BLOCK = registerBlock("jadeite_block", () -> new GeodeBlockBlock(GeodeMaterial.JADEITE));

	// Jadeite building variants
	public static final DeferredBlock<Block> JADEITE_BUTTON = registerBlock("jadeite_button", () -> new GeodeButtonBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_FENCE = registerBlock("jadeite_fence", () -> new GeodeFenceBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_FENCE_GATE = registerBlock("jadeite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_PRESSURE_PLATE = registerBlock("jadeite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_SLAB = registerBlock("jadeite_slab", () -> new GeodeSlabBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_STAIRS = registerBlock("jadeite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_TRAPDOOR = registerBlock("jadeite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.JADEITE));
	public static final DeferredBlock<Block> JADEITE_WALL = registerBlock("jadeite_wall", () -> new GeodeWallBlock(GeodeMaterial.JADEITE));


		// Opalite
		public static final DeferredBlock<Block> BUDDING_OPALITE_BLOCK = registerBlock("budding_opalite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.OPALITE));
		public static final DeferredBlock<Block> SMALL_OPALITE_BUD = registerBlock("small_opalite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.OPALITE));
		public static final DeferredBlock<Block> MEDIUM_OPALITE_BUD = registerBlock("medium_opalite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.OPALITE));
		public static final DeferredBlock<Block> LARGE_OPALITE_BUD = registerBlock("large_opalite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.OPALITE));
		public static final DeferredBlock<Block> OPALITE_CLUSTER = registerBlock("opalite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.OPALITE));
		public static final DeferredBlock<Block> OPALITE_BLOCK = registerBlock("opalite_block", () -> new GeodeBlockBlock(GeodeMaterial.OPALITE));

	// Opalite building variants
	public static final DeferredBlock<Block> OPALITE_BUTTON = registerBlock("opalite_button", () -> new GeodeButtonBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_FENCE = registerBlock("opalite_fence", () -> new GeodeFenceBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_FENCE_GATE = registerBlock("opalite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_PRESSURE_PLATE = registerBlock("opalite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_SLAB = registerBlock("opalite_slab", () -> new GeodeSlabBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_STAIRS = registerBlock("opalite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_TRAPDOOR = registerBlock("opalite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.OPALITE));
	public static final DeferredBlock<Block> OPALITE_WALL = registerBlock("opalite_wall", () -> new GeodeWallBlock(GeodeMaterial.OPALITE));


		// Padparadscha
		public static final DeferredBlock<Block> BUDDING_PADPARADSCHA_BLOCK = registerBlock("budding_padparadscha_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.PADPARADSCHA));
		public static final DeferredBlock<Block> SMALL_PADPARADSCHA_BUD = registerBlock("small_padparadscha_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.PADPARADSCHA));
		public static final DeferredBlock<Block> MEDIUM_PADPARADSCHA_BUD = registerBlock("medium_padparadscha_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.PADPARADSCHA));
		public static final DeferredBlock<Block> LARGE_PADPARADSCHA_BUD = registerBlock("large_padparadscha_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.PADPARADSCHA));
		public static final DeferredBlock<Block> PADPARADSCHA_CLUSTER = registerBlock("padparadscha_cluster", () -> new GeodeClusterBlock(GeodeMaterial.PADPARADSCHA));
		public static final DeferredBlock<Block> PADPARADSCHA_BLOCK = registerBlock("padparadscha_block", () -> new GeodeBlockBlock(GeodeMaterial.PADPARADSCHA));

	// Padparadscha building variants
	public static final DeferredBlock<Block> PADPARADSCHA_BUTTON = registerBlock("padparadscha_button", () -> new GeodeButtonBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_FENCE = registerBlock("padparadscha_fence", () -> new GeodeFenceBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_FENCE_GATE = registerBlock("padparadscha_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_PRESSURE_PLATE = registerBlock("padparadscha_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_SLAB = registerBlock("padparadscha_slab", () -> new GeodeSlabBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_STAIRS = registerBlock("padparadscha_stairs", () -> new GeodeStairsBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_TRAPDOOR = registerBlock("padparadscha_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.PADPARADSCHA));
	public static final DeferredBlock<Block> PADPARADSCHA_WALL = registerBlock("padparadscha_wall", () -> new GeodeWallBlock(GeodeMaterial.PADPARADSCHA));


		// Ruby
		public static final DeferredBlock<Block> BUDDING_RUBY_BLOCK = registerBlock("budding_ruby_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.RUBY));
		public static final DeferredBlock<Block> SMALL_RUBY_BUD = registerBlock("small_ruby_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.RUBY));
		public static final DeferredBlock<Block> MEDIUM_RUBY_BUD = registerBlock("medium_ruby_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.RUBY));
		public static final DeferredBlock<Block> LARGE_RUBY_BUD = registerBlock("large_ruby_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.RUBY));
		public static final DeferredBlock<Block> RUBY_CLUSTER = registerBlock("ruby_cluster", () -> new GeodeClusterBlock(GeodeMaterial.RUBY));
		public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new GeodeBlockBlock(GeodeMaterial.RUBY));

	// Ruby building variants
	public static final DeferredBlock<Block> RUBY_BUTTON = registerBlock("ruby_button", () -> new GeodeButtonBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_FENCE = registerBlock("ruby_fence", () -> new GeodeFenceBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_FENCE_GATE = registerBlock("ruby_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_PRESSURE_PLATE = registerBlock("ruby_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_SLAB = registerBlock("ruby_slab", () -> new GeodeSlabBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_STAIRS = registerBlock("ruby_stairs", () -> new GeodeStairsBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_WALL = registerBlock("ruby_wall", () -> new GeodeWallBlock(GeodeMaterial.RUBY));
	public static final DeferredBlock<Block> RUBY_TRAPDOOR = registerBlock("ruby_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.RUBY));


		// Sapphire
		public static final DeferredBlock<Block> BUDDING_SAPPHIRE_BLOCK = registerBlock("budding_sapphire_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.SAPPHIRE));
		public static final DeferredBlock<Block> SMALL_SAPPHIRE_BUD = registerBlock("small_sapphire_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.SAPPHIRE));
		public static final DeferredBlock<Block> MEDIUM_SAPPHIRE_BUD = registerBlock("medium_sapphire_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.SAPPHIRE));
		public static final DeferredBlock<Block> LARGE_SAPPHIRE_BUD = registerBlock("large_sapphire_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.SAPPHIRE));
		public static final DeferredBlock<Block> SAPPHIRE_CLUSTER = registerBlock("sapphire_cluster", () -> new GeodeClusterBlock(GeodeMaterial.SAPPHIRE));
		public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new GeodeBlockBlock(GeodeMaterial.SAPPHIRE));

	// Sapphire building variants
	public static final DeferredBlock<Block> SAPPHIRE_BUTTON = registerBlock("sapphire_button", () -> new GeodeButtonBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_FENCE = registerBlock("sapphire_fence", () -> new GeodeFenceBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_SLAB = registerBlock("sapphire_slab", () -> new GeodeSlabBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_STAIRS = registerBlock("sapphire_stairs", () -> new GeodeStairsBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_TRAPDOOR = registerBlock("sapphire_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.SAPPHIRE));
	public static final DeferredBlock<Block> SAPPHIRE_WALL = registerBlock("sapphire_wall", () -> new GeodeWallBlock(GeodeMaterial.SAPPHIRE));


		// Scapolite
		public static final DeferredBlock<Block> BUDDING_SCAPOLITE_BLOCK = registerBlock("budding_scapolite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.SCAPOLITE));
		public static final DeferredBlock<Block> SMALL_SCAPOLITE_BUD = registerBlock("small_scapolite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.SCAPOLITE));
		public static final DeferredBlock<Block> MEDIUM_SCAPOLITE_BUD = registerBlock("medium_scapolite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.SCAPOLITE));
		public static final DeferredBlock<Block> LARGE_SCAPOLITE_BUD = registerBlock("large_scapolite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.SCAPOLITE));
		public static final DeferredBlock<Block> SCAPOLITE_CLUSTER = registerBlock("scapolite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.SCAPOLITE));
		public static final DeferredBlock<Block> SCAPOLITE_BLOCK = registerBlock("scapolite_block", () -> new GeodeBlockBlock(GeodeMaterial.SCAPOLITE));

	// Scapolite building variants
	public static final DeferredBlock<Block> SCAPOLITE_BUTTON = registerBlock("scapolite_button", () -> new GeodeButtonBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_FENCE = registerBlock("scapolite_fence", () -> new GeodeFenceBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_FENCE_GATE = registerBlock("scapolite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_PRESSURE_PLATE = registerBlock("scapolite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_SLAB = registerBlock("scapolite_slab", () -> new GeodeSlabBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_STAIRS = registerBlock("scapolite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_TRAPDOOR = registerBlock("scapolite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.SCAPOLITE));
	public static final DeferredBlock<Block> SCAPOLITE_WALL = registerBlock("scapolite_wall", () -> new GeodeWallBlock(GeodeMaterial.SCAPOLITE));


		// Staurolite
		public static final DeferredBlock<Block> BUDDING_STAUROLITE_BLOCK = registerBlock("budding_staurolite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.STAUROLITE));
		public static final DeferredBlock<Block> SMALL_STAUROLITE_BUD = registerBlock("small_staurolite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.STAUROLITE));
		public static final DeferredBlock<Block> MEDIUM_STAUROLITE_BUD = registerBlock("medium_staurolite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.STAUROLITE));
		public static final DeferredBlock<Block> LARGE_STAUROLITE_BUD = registerBlock("large_staurolite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.STAUROLITE));
		public static final DeferredBlock<Block> STAUROLITE_CLUSTER = registerBlock("staurolite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.STAUROLITE));
		public static final DeferredBlock<Block> STAUROLITE_BLOCK = registerBlock("staurolite_block", () -> new GeodeBlockBlock(GeodeMaterial.STAUROLITE));

	// Staurolite building variants
	public static final DeferredBlock<Block> STAUROLITE_BUTTON = registerBlock("staurolite_button", () -> new GeodeButtonBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_FENCE = registerBlock("staurolite_fence", () -> new GeodeFenceBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_FENCE_GATE = registerBlock("staurolite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_PRESSURE_PLATE = registerBlock("staurolite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_SLAB = registerBlock("staurolite_slab", () -> new GeodeSlabBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_STAIRS = registerBlock("staurolite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_TRAPDOOR = registerBlock("staurolite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.STAUROLITE));
	public static final DeferredBlock<Block> STAUROLITE_WALL = registerBlock("staurolite_wall", () -> new GeodeWallBlock(GeodeMaterial.STAUROLITE));


		// Tanzanite
		public static final DeferredBlock<Block> BUDDING_TANZANITE_BLOCK = registerBlock("budding_tanzanite_block", () -> new BuddingGeodeBlockBlock(GeodeMaterial.TANZANITE));
		public static final DeferredBlock<Block> SMALL_TANZANITE_BUD = registerBlock("small_tanzanite_bud", () -> new SmallGeodeBudBlock(GeodeMaterial.TANZANITE));
		public static final DeferredBlock<Block> MEDIUM_TANZANITE_BUD = registerBlock("medium_tanzanite_bud", () -> new MediumGeodeBudBlock(GeodeMaterial.TANZANITE));
		public static final DeferredBlock<Block> LARGE_TANZANITE_BUD = registerBlock("large_tanzanite_bud", () -> new LargeGeodeBudBlock(GeodeMaterial.TANZANITE));
		public static final DeferredBlock<Block> TANZANITE_CLUSTER = registerBlock("tanzanite_cluster", () -> new GeodeClusterBlock(GeodeMaterial.TANZANITE));

	// Tanzanite building variants
	public static final DeferredBlock<Block> TANZANITE_BLOCK = registerBlock("tanzanite_block", () -> new GeodeBlockBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_BUTTON = registerBlock("tanzanite_button", () -> new GeodeButtonBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_FENCE = registerBlock("tanzanite_fence", () -> new GeodeFenceBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_FENCE_GATE = registerBlock("tanzanite_fence_gate", () -> new GeodeFenceGateBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_PRESSURE_PLATE = registerBlock("tanzanite_pressure_plate", () -> new GeodePressurePlateBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_SLAB = registerBlock("tanzanite_slab", () -> new GeodeSlabBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_STAIRS = registerBlock("tanzanite_stairs", () -> new GeodeStairsBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_TRAPDOOR = registerBlock("tanzanite_trapdoor", () -> new GeodeTrapdoorBlock(GeodeMaterial.TANZANITE));
	public static final DeferredBlock<Block> TANZANITE_WALL = registerBlock("tanzanite_wall", () -> new GeodeWallBlock(GeodeMaterial.TANZANITE));



	public static final DeferredBlock<Block> BOUNCE_CRATE = registerUncommonBlock("bounce_crate", BounceCrateBlock::new);
	public static final DeferredBlock<Block> CRASH_CRATE = registerUncommonBlock("crash_crate", CrashCrateBlock::new);
	public static final DeferredBlock<Block> CRASH_TNT = registerUncommonBlock("crash_tnt", CrashTNTBlock::new);
	public static final DeferredBlock<Block> NITRO = registerUncommonBlock("nitro", NitroBlock::new);
	public static final DeferredBlock<Block> FLOWERING_PITCHER_PLANT = registerUncommonBlock("flowering_pitcher_plant", FloweringPitcherPlantBlock::new);

	public static final DeferredBlock<Block> GOLDEN_BED = registerUncommonBlock("golden_bed", GoldenBedBlock::new);

	public static final DeferredBlock<Block> AKU_AKU_CRATE = registerRareBlock("aku_aku_crate", AkuAkuCrateBlock::new);
	public static final DeferredBlock<Block> WUMPA_PLANT = registerRareBlock("wumpa_plant", WumpaPlantBlock::new);


	  // Furnaces
	 /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	//map for ent registry
	public static final Map<String, Supplier<OPFurnaceBlock>> FURNACE_VARIANTS = new HashMap<>();

	// Helper method for registering furnace blocks
	private static Supplier<OPFurnaceBlock> registerFurnaceBlock(String name, OPFurnaceMaterial material) {
		Supplier<OPFurnaceBlock> supplier;

		if (material == OPFurnaceMaterial.NETHERITE) { // special case for netherite furnace block is fireproof!
			supplier = registerFRBlock(name, () -> new OPFurnaceBlock(material));
		} else {
			supplier = registerBlock(name, () -> new OPFurnaceBlock(material));
		}

		FURNACE_VARIANTS.put(name, supplier);
		return supplier;
	}

	// Furnace variants
	public static final Supplier<OPFurnaceBlock> COPPER_FURNACE = registerFurnaceBlock("copper_furnace", OPFurnaceMaterial.COPPER);
	public static final Supplier<OPFurnaceBlock> IRON_FURNACE = registerFurnaceBlock("iron_furnace", OPFurnaceMaterial.IRON);
	public static final Supplier<OPFurnaceBlock> GOLD_FURNACE = registerFurnaceBlock("gold_furnace", OPFurnaceMaterial.GOLD);
	public static final Supplier<OPFurnaceBlock> DIAMOND_FURNACE = registerFurnaceBlock("diamond_furnace", OPFurnaceMaterial.DIAMOND);
	public static final Supplier<OPFurnaceBlock> NETHERITE_FURNACE = registerFurnaceBlock("netherite_furnace", OPFurnaceMaterial.NETHERITE);



	  // Ancient Chests
	 /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	//map for ent registry
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


	  // lamp blocks
	 /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	// Helper method for registering lamp blocks
	private static Supplier<OPLampBlock> registerOPLampBlock(String name, OPLampMaterial lampMaterial, OPLampColor lampcolor) {
		Supplier<OPLampBlock> supplier = registerBlock(name, () -> new OPLampBlock(lampMaterial, lampcolor));
		return supplier;
	}

	// Golden lamp variants
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_BLACK = registerOPLampBlock("golden_oplamp_black", OPLampMaterial.GOLDEN, OPLampColor.BLACK);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_BLUE = registerOPLampBlock("golden_oplamp_blue", OPLampMaterial.GOLDEN, OPLampColor.BLUE);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_BROWN = registerOPLampBlock("golden_oplamp_brown", OPLampMaterial.GOLDEN, OPLampColor.BROWN);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_CYAN = registerOPLampBlock("golden_oplamp_cyan", OPLampMaterial.GOLDEN, OPLampColor.CYAN);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_GOLDEN = registerOPLampBlock("golden_oplamp_golden", OPLampMaterial.GOLDEN, OPLampColor.GOLDEN);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_GRAY = registerOPLampBlock("golden_oplamp_gray", OPLampMaterial.GOLDEN, OPLampColor.GRAY);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_GREEN = registerOPLampBlock("golden_oplamp_green", OPLampMaterial.GOLDEN, OPLampColor.GREEN);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_LIGHT_BLUE = registerOPLampBlock("golden_oplamp_light_blue", OPLampMaterial.GOLDEN, OPLampColor.LIGHT_BLUE);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_LIGHT_GRAY = registerOPLampBlock("golden_oplamp_light_gray", OPLampMaterial.GOLDEN, OPLampColor.LIGHT_GRAY);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_LIME = registerOPLampBlock("golden_oplamp_lime", OPLampMaterial.GOLDEN, OPLampColor.LIME);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_MAGENTA = registerOPLampBlock("golden_oplamp_magenta", OPLampMaterial.GOLDEN, OPLampColor.MAGENTA);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_ORANGE = registerOPLampBlock("golden_oplamp_orange", OPLampMaterial.GOLDEN, OPLampColor.ORANGE);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_PINK = registerOPLampBlock("golden_oplamp_pink", OPLampMaterial.GOLDEN, OPLampColor.PINK);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_PURPLE = registerOPLampBlock("golden_oplamp_purple", OPLampMaterial.GOLDEN, OPLampColor.PURPLE);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_RED = registerOPLampBlock("golden_oplamp_red", OPLampMaterial.GOLDEN, OPLampColor.RED);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_WHITE = registerOPLampBlock("golden_oplamp_white", OPLampMaterial.GOLDEN, OPLampColor.WHITE);
	public static final Supplier<OPLampBlock> GOLDEN_OPLAMP_YELLOW = registerOPLampBlock("golden_oplamp_yellow", OPLampMaterial.GOLDEN, OPLampColor.YELLOW);

	// Iron lamp variants
	public static final Supplier<OPLampBlock> IRON_OPLAMP_BLACK = registerOPLampBlock("iron_oplamp_black", OPLampMaterial.IRON, OPLampColor.BLACK);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_BLUE = registerOPLampBlock("iron_oplamp_blue", OPLampMaterial.IRON, OPLampColor.BLUE);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_BROWN = registerOPLampBlock("iron_oplamp_brown", OPLampMaterial.IRON, OPLampColor.BROWN);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_CYAN = registerOPLampBlock("iron_oplamp_cyan", OPLampMaterial.IRON, OPLampColor.CYAN);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_GOLDEN = registerOPLampBlock("iron_oplamp_golden", OPLampMaterial.IRON, OPLampColor.GOLDEN);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_GRAY = registerOPLampBlock("iron_oplamp_gray", OPLampMaterial.IRON, OPLampColor.GRAY);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_GREEN = registerOPLampBlock("iron_oplamp_green", OPLampMaterial.IRON, OPLampColor.GREEN);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_LIGHT_BLUE = registerOPLampBlock("iron_oplamp_light_blue", OPLampMaterial.IRON, OPLampColor.LIGHT_BLUE);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_LIGHT_GRAY = registerOPLampBlock("iron_oplamp_light_gray", OPLampMaterial.IRON, OPLampColor.LIGHT_GRAY);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_LIME = registerOPLampBlock("iron_oplamp_lime", OPLampMaterial.IRON, OPLampColor.LIME);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_MAGENTA = registerOPLampBlock("iron_oplamp_magenta", OPLampMaterial.IRON, OPLampColor.MAGENTA);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_ORANGE = registerOPLampBlock("iron_oplamp_orange", OPLampMaterial.IRON, OPLampColor.ORANGE);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_PINK = registerOPLampBlock("iron_oplamp_pink", OPLampMaterial.IRON, OPLampColor.PINK);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_PURPLE = registerOPLampBlock("iron_oplamp_purple", OPLampMaterial.IRON, OPLampColor.PURPLE);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_RED = registerOPLampBlock("iron_oplamp_red", OPLampMaterial.IRON, OPLampColor.RED);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_WHITE = registerOPLampBlock("iron_oplamp_white", OPLampMaterial.IRON, OPLampColor.WHITE);
	public static final Supplier<OPLampBlock> IRON_OPLAMP_YELLOW = registerOPLampBlock("iron_oplamp_yellow", OPLampMaterial.IRON, OPLampColor.YELLOW);

	// Diamond lamp variants
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_BLACK = registerOPLampBlock("diamond_oplamp_black", OPLampMaterial.DIAMOND, OPLampColor.BLACK);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_BLUE = registerOPLampBlock("diamond_oplamp_blue", OPLampMaterial.DIAMOND, OPLampColor.BLUE);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_BROWN = registerOPLampBlock("diamond_oplamp_brown", OPLampMaterial.DIAMOND, OPLampColor.BROWN);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_CYAN = registerOPLampBlock("diamond_oplamp_cyan", OPLampMaterial.DIAMOND, OPLampColor.CYAN);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_GOLDEN = registerOPLampBlock("diamond_oplamp_golden", OPLampMaterial.DIAMOND, OPLampColor.GOLDEN);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_GRAY = registerOPLampBlock("diamond_oplamp_gray", OPLampMaterial.DIAMOND, OPLampColor.GRAY);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_GREEN = registerOPLampBlock("diamond_oplamp_green", OPLampMaterial.DIAMOND, OPLampColor.GREEN);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_LIGHT_BLUE = registerOPLampBlock("diamond_oplamp_light_blue", OPLampMaterial.DIAMOND, OPLampColor.LIGHT_BLUE);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_LIGHT_GRAY = registerOPLampBlock("diamond_oplamp_light_gray", OPLampMaterial.DIAMOND, OPLampColor.LIGHT_GRAY);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_LIME = registerOPLampBlock("diamond_oplamp_lime", OPLampMaterial.DIAMOND, OPLampColor.LIME);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_MAGENTA = registerOPLampBlock("diamond_oplamp_magenta", OPLampMaterial.DIAMOND, OPLampColor.MAGENTA);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_ORANGE = registerOPLampBlock("diamond_oplamp_orange", OPLampMaterial.DIAMOND, OPLampColor.ORANGE);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_PINK = registerOPLampBlock("diamond_oplamp_pink", OPLampMaterial.DIAMOND, OPLampColor.PINK);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_PURPLE = registerOPLampBlock("diamond_oplamp_purple", OPLampMaterial.DIAMOND, OPLampColor.PURPLE);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_RED = registerOPLampBlock("diamond_oplamp_red", OPLampMaterial.DIAMOND, OPLampColor.RED);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_WHITE = registerOPLampBlock("diamond_oplamp_white", OPLampMaterial.DIAMOND, OPLampColor.WHITE);
	public static final Supplier<OPLampBlock> DIAMOND_OPLAMP_YELLOW = registerOPLampBlock("diamond_oplamp_yellow", OPLampMaterial.DIAMOND, OPLampColor.YELLOW);

	// Netherite lamp variants
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_BLACK = registerOPLampBlock("netherite_oplamp_black", OPLampMaterial.NETHERITE, OPLampColor.BLACK);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_BLUE = registerOPLampBlock("netherite_oplamp_blue", OPLampMaterial.NETHERITE, OPLampColor.BLUE);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_BROWN = registerOPLampBlock("netherite_oplamp_brown", OPLampMaterial.NETHERITE, OPLampColor.BROWN);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_CYAN = registerOPLampBlock("netherite_oplamp_cyan", OPLampMaterial.NETHERITE, OPLampColor.CYAN);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_GOLDEN = registerOPLampBlock("netherite_oplamp_golden", OPLampMaterial.NETHERITE, OPLampColor.GOLDEN);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_GRAY = registerOPLampBlock("netherite_oplamp_gray", OPLampMaterial.NETHERITE, OPLampColor.GRAY);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_GREEN = registerOPLampBlock("netherite_oplamp_green", OPLampMaterial.NETHERITE, OPLampColor.GREEN);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_LIGHT_BLUE = registerOPLampBlock("netherite_oplamp_light_blue", OPLampMaterial.NETHERITE, OPLampColor.LIGHT_BLUE);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_LIGHT_GRAY = registerOPLampBlock("netherite_oplamp_light_gray", OPLampMaterial.NETHERITE, OPLampColor.LIGHT_GRAY);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_LIME = registerOPLampBlock("netherite_oplamp_lime", OPLampMaterial.NETHERITE, OPLampColor.LIME);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_MAGENTA = registerOPLampBlock("netherite_oplamp_magenta", OPLampMaterial.NETHERITE, OPLampColor.MAGENTA);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_ORANGE = registerOPLampBlock("netherite_oplamp_orange", OPLampMaterial.NETHERITE, OPLampColor.ORANGE);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_PINK = registerOPLampBlock("netherite_oplamp_pink", OPLampMaterial.NETHERITE, OPLampColor.PINK);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_PURPLE = registerOPLampBlock("netherite_oplamp_purple", OPLampMaterial.NETHERITE, OPLampColor.PURPLE);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_RED = registerOPLampBlock("netherite_oplamp_red", OPLampMaterial.NETHERITE, OPLampColor.RED);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_WHITE = registerOPLampBlock("netherite_oplamp_white", OPLampMaterial.NETHERITE, OPLampColor.WHITE);
	public static final Supplier<OPLampBlock> NETHERITE_OPLAMP_YELLOW = registerOPLampBlock("netherite_oplamp_yellow", OPLampMaterial.NETHERITE, OPLampColor.YELLOW);

	// Onyx lamp variants
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_BLACK = registerOPLampBlock("onyx_oplamp_black", OPLampMaterial.ONYX, OPLampColor.BLACK);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_BLUE = registerOPLampBlock("onyx_oplamp_blue", OPLampMaterial.ONYX, OPLampColor.BLUE);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_BROWN = registerOPLampBlock("onyx_oplamp_brown", OPLampMaterial.ONYX, OPLampColor.BROWN);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_CYAN = registerOPLampBlock("onyx_oplamp_cyan", OPLampMaterial.ONYX, OPLampColor.CYAN);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_GOLDEN = registerOPLampBlock("onyx_oplamp_golden", OPLampMaterial.ONYX, OPLampColor.GOLDEN);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_GRAY = registerOPLampBlock("onyx_oplamp_gray", OPLampMaterial.ONYX, OPLampColor.GRAY);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_GREEN = registerOPLampBlock("onyx_oplamp_green", OPLampMaterial.ONYX, OPLampColor.GREEN);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_LIGHT_BLUE = registerOPLampBlock("onyx_oplamp_light_blue", OPLampMaterial.ONYX, OPLampColor.LIGHT_BLUE);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_LIGHT_GRAY = registerOPLampBlock("onyx_oplamp_light_gray", OPLampMaterial.ONYX, OPLampColor.LIGHT_GRAY);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_LIME = registerOPLampBlock("onyx_oplamp_lime", OPLampMaterial.ONYX, OPLampColor.LIME);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_MAGENTA = registerOPLampBlock("onyx_oplamp_magenta", OPLampMaterial.ONYX, OPLampColor.MAGENTA);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_ORANGE = registerOPLampBlock("onyx_oplamp_orange", OPLampMaterial.ONYX, OPLampColor.ORANGE);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_PINK = registerOPLampBlock("onyx_oplamp_pink", OPLampMaterial.ONYX, OPLampColor.PINK);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_PURPLE = registerOPLampBlock("onyx_oplamp_purple", OPLampMaterial.ONYX, OPLampColor.PURPLE);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_RED = registerOPLampBlock("onyx_oplamp_red", OPLampMaterial.ONYX, OPLampColor.RED);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_WHITE = registerOPLampBlock("onyx_oplamp_white", OPLampMaterial.ONYX, OPLampColor.WHITE);
	public static final Supplier<OPLampBlock> ONYX_OPLAMP_YELLOW = registerOPLampBlock("onyx_oplamp_yellow", OPLampMaterial.ONYX, OPLampColor.YELLOW);

	// Titan lamp variants
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_BLACK = registerOPLampBlock("titan_oplamp_black", OPLampMaterial.TITAN, OPLampColor.BLACK);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_BLUE = registerOPLampBlock("titan_oplamp_blue", OPLampMaterial.TITAN, OPLampColor.BLUE);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_BROWN = registerOPLampBlock("titan_oplamp_brown", OPLampMaterial.TITAN, OPLampColor.BROWN);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_CYAN = registerOPLampBlock("titan_oplamp_cyan", OPLampMaterial.TITAN, OPLampColor.CYAN);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_GOLDEN = registerOPLampBlock("titan_oplamp_golden", OPLampMaterial.TITAN, OPLampColor.GOLDEN);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_GRAY = registerOPLampBlock("titan_oplamp_gray", OPLampMaterial.TITAN, OPLampColor.GRAY);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_GREEN = registerOPLampBlock("titan_oplamp_green", OPLampMaterial.TITAN, OPLampColor.GREEN);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_LIGHT_BLUE = registerOPLampBlock("titan_oplamp_light_blue", OPLampMaterial.TITAN, OPLampColor.LIGHT_BLUE);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_LIGHT_GRAY = registerOPLampBlock("titan_oplamp_light_gray", OPLampMaterial.TITAN, OPLampColor.LIGHT_GRAY);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_LIME = registerOPLampBlock("titan_oplamp_lime", OPLampMaterial.TITAN, OPLampColor.LIME);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_MAGENTA = registerOPLampBlock("titan_oplamp_magenta", OPLampMaterial.TITAN, OPLampColor.MAGENTA);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_ORANGE = registerOPLampBlock("titan_oplamp_orange", OPLampMaterial.TITAN, OPLampColor.ORANGE);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_PINK = registerOPLampBlock("titan_oplamp_pink", OPLampMaterial.TITAN, OPLampColor.PINK);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_PURPLE = registerOPLampBlock("titan_oplamp_purple", OPLampMaterial.TITAN, OPLampColor.PURPLE);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_RED = registerOPLampBlock("titan_oplamp_red", OPLampMaterial.TITAN, OPLampColor.RED);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_WHITE = registerOPLampBlock("titan_oplamp_white", OPLampMaterial.TITAN, OPLampColor.WHITE);
	public static final Supplier<OPLampBlock> TITAN_OPLAMP_YELLOW = registerOPLampBlock("titan_oplamp_yellow", OPLampMaterial.TITAN, OPLampColor.YELLOW);




	/*-----------------------------------------------------------------------------------------------------------------------*/
	 /*-----------------------------------------------------------------------------------------------------------------------*/
	/*[Register Items]*/
	public static final DeferredItem<Item> TEST_ITEM = ITEMSREGISTRY.registerItem("test_item", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));

	public static final DeferredItem<Item> CHISEL = ITEMSREGISTRY.register("chisel", ChiselItem::new);
	public static final DeferredItem<Item> SCULK_HAMMER = ITEMSREGISTRY.register("sculk_hammer", SculkHammerItem::new);

	public static final DeferredItem<Item> AKU_AKU_MASK = ITEMSREGISTRY.register("aku_aku_mask", AkuAkuMaskItem::new);
	public static final DeferredItem<Item> WUMPA_FRUIT = ITEMSREGISTRY.register("wumpa_fruit", WumpaFruitItem::new);

	public static final DeferredItem<Item> CRUDE_ALEXANDRITE = ITEMSREGISTRY.registerItem("crude_alexandrite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> ALEXANDRITE = ITEMSREGISTRY.registerItem("alexandrite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_ALEXANDRITE = ITEMSREGISTRY.registerItem("flawless_alexandrite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_ALEXANDRITE = ITEMSREGISTRY.registerItem("perfect_alexandrite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_ALEXANDRITE = ITEMSREGISTRY.registerItem("brilliant_alexandrite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_AMETHYST = ITEMSREGISTRY.registerItem("crude_amethyst", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_AMETHYST = ITEMSREGISTRY.registerItem("flawless_amethyst", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_AMETHYST = ITEMSREGISTRY.registerItem("perfect_amethyst", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_AMETHYST = ITEMSREGISTRY.registerItem("brilliant_amethyst", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

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

	public static final DeferredItem<Item> CRUDE_DIAMOND = ITEMSREGISTRY.registerItem("crude_diamond", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_DIAMOND = ITEMSREGISTRY.registerItem("flawless_diamond", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_DIAMOND = ITEMSREGISTRY.registerItem("perfect_diamond", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_DIAMOND = ITEMSREGISTRY.registerItem("brilliant_diamond", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	
	public static final DeferredItem<Item> CRUDE_DRAGONITE = ITEMSREGISTRY.registerItem("crude_dragonite", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> DRAGONITE = ITEMSREGISTRY.registerItem("dragonite", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_DRAGONITE = ITEMSREGISTRY.registerItem("flawless_dragonite", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_DRAGONITE = ITEMSREGISTRY.registerItem("perfect_dragonite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_DRAGONITE = ITEMSREGISTRY.registerItem("brilliant_dragonite", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> CRUDE_EMERALD = ITEMSREGISTRY.registerItem("crude_emerald", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
	public static final DeferredItem<Item> FLAWLESS_EMERALD = ITEMSREGISTRY.registerItem("flawless_emerald", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(64));
	public static final DeferredItem<Item> PERFECT_EMERALD = ITEMSREGISTRY.registerItem("perfect_emerald", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
	public static final DeferredItem<Item> BRILLIANT_EMERALD = ITEMSREGISTRY.registerItem("brilliant_emerald", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

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

	public static final DeferredItem<Item> GEMSTONE_DUST = ITEMSREGISTRY.registerItem("gemstone_dust", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));

	public static final DeferredItem<Item> RAW_ONYX = ITEMSREGISTRY.registerItem("raw_onyx", Item::new, new Item.Properties().stacksTo(64));
	public static final DeferredItem<Item> RAW_TITANIUM = ITEMSREGISTRY.registerItem("raw_titanium", Item::new, new Item.Properties().stacksTo(64));

	public static final DeferredItem<Item> ONYX_INGOT = ITEMSREGISTRY.registerItem("onyx_ingot", Item::new, new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	public static final DeferredItem<Item> TITANIUM_INGOT = ITEMSREGISTRY.registerItem("titanium_ingot", Item::new, new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));

	public static final DeferredItem<Item> CRYING_INGOT = ITEMSREGISTRY.registerItem("crying_ingot", Item::new, new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	public static final DeferredItem<Item> CRYING_ESSENCE_BUCKET = ITEMSREGISTRY.register("crying_essence_bucket", CryingEssenceItem::new);
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
	public static final Supplier<HatchetItem> CLAY_HATCHET = ITEMSREGISTRY.register("clay_hatchet",
			() -> new HatchetItem(HatchetMaterial.CLAY));
	public static final Supplier<HatchetItem> STONE_HATCHET = ITEMSREGISTRY.register("stone_hatchet",
		() -> new HatchetItem(HatchetMaterial.STONE));
	public static final Supplier<HatchetItem> GOLDEN_HATCHET = ITEMSREGISTRY.register("golden_hatchet",
			() -> new HatchetItem(HatchetMaterial.GOLDEN));
	public static final Supplier<HatchetItem> IRON_HATCHET = ITEMSREGISTRY.register("iron_hatchet",
		() -> new HatchetItem(HatchetMaterial.IRON));
	public static final Supplier<HatchetItem> EMERALD_HATCHET = ITEMSREGISTRY.register("emerald_hatchet",
		() -> new HatchetItem(HatchetMaterial.EMERALD));
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
	public static final Supplier<GunbladeItem> IRON_GUNBLADE = ITEMSREGISTRY.register("iron_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.IRON));
	public static final Supplier<GunbladeItem> EMERALD_GUNBLADE = ITEMSREGISTRY.register("emerald_gunblade",
		() -> new GunbladeItem(GunbladeMaterial.EMERALD));

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


	public static final Supplier<PistolItem> GOLDEN_CZ75 = ITEMSREGISTRY.register("golden_cz75",
			() -> new PistolItem(PistolMaterial.GOLDEN_CZ75));
	public static final Supplier<PistolItem> IRON_CZ75 = ITEMSREGISTRY.register("iron_cz75",
			() -> new PistolItem(PistolMaterial.IRON_CZ75));
	public static final Supplier<PistolItem> EMERALD_CZ75 = ITEMSREGISTRY.register("emerald_cz75",
			() -> new PistolItem(PistolMaterial.EMERALD_CZ75));
	public static final Supplier<PistolItem> DIAMOND_CZ75 = ITEMSREGISTRY.register("diamond_cz75",
			() -> new PistolItem(PistolMaterial.DIAMOND_CZ75));
	public static final Supplier<PistolItem> NETHERITE_CZ75 = ITEMSREGISTRY.register("netherite_cz75",
			() -> new PistolItem(PistolMaterial.NETHERITE_CZ75));
	public static final Supplier<PistolItem> ONYX_CZ75 = ITEMSREGISTRY.register("onyx_cz75",
			() -> new PistolItem(PistolMaterial.ONYX_CZ75));
	public static final Supplier<PistolItem> TITAN_CZ75 = ITEMSREGISTRY.register("titan_cz75",
			() -> new PistolItem(PistolMaterial.TITAN_CZ75));

	public static final Supplier<PistolItem> GOLDEN_92FS = ITEMSREGISTRY.register("golden_92fs",
			() -> new PistolItem(PistolMaterial.GOLDEN_92FS));
	public static final Supplier<PistolItem> IRON_92FS = ITEMSREGISTRY.register("iron_92fs",
			() -> new PistolItem(PistolMaterial.IRON_92FS));
	public static final Supplier<PistolItem> EMERALD_92FS = ITEMSREGISTRY.register("emerald_92fs",
			() -> new PistolItem(PistolMaterial.EMERALD_92FS));
	public static final Supplier<PistolItem> DIAMOND_92FS = ITEMSREGISTRY.register("diamond_92fs",
			() -> new PistolItem(PistolMaterial.DIAMOND_92FS));
	public static final Supplier<PistolItem> NETHERITE_92FS = ITEMSREGISTRY.register("netherite_92fs",
			() -> new PistolItem(PistolMaterial.NETHERITE_92FS));
	public static final Supplier<PistolItem> ONYX_92FS = ITEMSREGISTRY.register("onyx_92fs",
			() -> new PistolItem(PistolMaterial.ONYX_92FS));
	public static final Supplier<PistolItem> TITAN_92FS = ITEMSREGISTRY.register("titan_92fs",
			() -> new PistolItem(PistolMaterial.TITAN_92FS));
	public static final Supplier<PistolItem> VALENTINE_92FS = ITEMSREGISTRY.register("valentine_92fs",
			() -> new PistolItem(PistolMaterial.VALENTINE_92FS));

	public static final Supplier<PistolItem> GOLDEN_M9A1 = ITEMSREGISTRY.register("golden_m9a1",
			() -> new PistolItem(PistolMaterial.GOLDEN_M9A1));
	public static final Supplier<PistolItem> IRON_M9A1 = ITEMSREGISTRY.register("iron_m9a1",
			() -> new PistolItem(PistolMaterial.IRON_M9A1));
	public static final Supplier<PistolItem> EMERALD_M9A1 = ITEMSREGISTRY.register("emerald_m9a1",
			() -> new PistolItem(PistolMaterial.EMERALD_M9A1));
	public static final Supplier<PistolItem> DIAMOND_M9A1 = ITEMSREGISTRY.register("diamond_m9a1",
			() -> new PistolItem(PistolMaterial.DIAMOND_M9A1));
	public static final Supplier<PistolItem> NETHERITE_M9A1 = ITEMSREGISTRY.register("netherite_m9a1",
			() -> new PistolItem(PistolMaterial.NETHERITE_M9A1));
	public static final Supplier<PistolItem> ONYX_M9A1 = ITEMSREGISTRY.register("onyx_m9a1",
			() -> new PistolItem(PistolMaterial.ONYX_M9A1));
	public static final Supplier<PistolItem> TITAN_M9A1 = ITEMSREGISTRY.register("titan_m9a1",
			() -> new PistolItem(PistolMaterial.TITAN_M9A1));


	public static final Supplier<PistolItem> GOLDEN_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("golden_protektor_type_75",
			() -> new PistolItem(PistolMaterial.GOLDEN_PROTEKTOR_TYPE_75));
	public static final Supplier<PistolItem> IRON_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("iron_protektor_type_75",
			() -> new PistolItem(PistolMaterial.IRON_PROTEKTOR_TYPE_75));
	public static final Supplier<PistolItem> EMERALD_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("emerald_protektor_type_75",
			() -> new PistolItem(PistolMaterial.EMERALD_PROTEKTOR_TYPE_75));
	public static final Supplier<PistolItem> DIAMOND_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("diamond_protektor_type_75",
			() -> new PistolItem(PistolMaterial.DIAMOND_PROTEKTOR_TYPE_75));
	public static final Supplier<PistolItem> NETHERITE_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("netherite_protektor_type_75",
			() -> new PistolItem(PistolMaterial.NETHERITE_PROTEKTOR_TYPE_75));
	public static final Supplier<PistolItem> ONYX_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("onyx_protektor_type_75",
			() -> new PistolItem(PistolMaterial.ONYX_PROTEKTOR_TYPE_75));
	public static final Supplier<PistolItem> TITAN_PROTEKTOR_TYPE_75 = ITEMSREGISTRY.register("titan_protektor_type_75",
			() -> new PistolItem(PistolMaterial.TITAN_PROTEKTOR_TYPE_75));


	public static final Supplier<PistolItem> GOLDEN_HANDCANNON = ITEMSREGISTRY.register("golden_handcannon",
			() -> new PistolItem(PistolMaterial.GOLDEN_HANDCANNON));
	public static final Supplier<PistolItem> IRON_HANDCANNON = ITEMSREGISTRY.register("iron_handcannon",
			() -> new PistolItem(PistolMaterial.IRON_HANDCANNON));
	public static final Supplier<PistolItem> EMERALD_HANDCANNON = ITEMSREGISTRY.register("emerald_handcannon",
			() -> new PistolItem(PistolMaterial.EMERALD_HANDCANNON));
	public static final Supplier<PistolItem> DIAMOND_HANDCANNON = ITEMSREGISTRY.register("diamond_handcannon",
			() -> new PistolItem(PistolMaterial.DIAMOND_HANDCANNON));
	public static final Supplier<PistolItem> NETHERITE_HANDCANNON = ITEMSREGISTRY.register("netherite_handcannon",
			() -> new PistolItem(PistolMaterial.NETHERITE_HANDCANNON));
	public static final Supplier<PistolItem> ONYX_HANDCANNON = ITEMSREGISTRY.register("onyx_handcannon",
			() -> new PistolItem(PistolMaterial.ONYX_HANDCANNON));
	public static final Supplier<PistolItem> TITAN_HANDCANNON = ITEMSREGISTRY.register("titan_handcannon",
			() -> new PistolItem(PistolMaterial.TITAN_HANDCANNON));


	public static final Supplier<PistolItem> GOLDEN_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("golden_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.GOLDEN_IMI_DESERT_EAGLE));
	public static final Supplier<PistolItem> IRON_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("iron_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.IRON_IMI_DESERT_EAGLE));
	public static final Supplier<PistolItem> EMERALD_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("emerald_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.EMERALD_IMI_DESERT_EAGLE));
	public static final Supplier<PistolItem> DIAMOND_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("diamond_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.DIAMOND_IMI_DESERT_EAGLE));
	public static final Supplier<PistolItem> NETHERITE_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("netherite_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.NETHERITE_IMI_DESERT_EAGLE));
	public static final Supplier<PistolItem> ONYX_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("onyx_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.ONYX_IMI_DESERT_EAGLE));
	public static final Supplier<PistolItem> TITAN_IMI_DESERT_EAGLE = ITEMSREGISTRY.register("titan_imi_desert_eagle",
			() -> new PistolItem(PistolMaterial.TITAN_IMI_DESERT_EAGLE));


	public static final Supplier<PistolItem> GOLDEN_RRAR = ITEMSREGISTRY.register("golden_rrar",
			() -> new PistolItem(PistolMaterial.GOLDEN_RRAR));
	public static final Supplier<PistolItem> IRON_RRAR = ITEMSREGISTRY.register("iron_rrar",
			() -> new PistolItem(PistolMaterial.IRON_RRAR));
	public static final Supplier<PistolItem> EMERALD_RRAR = ITEMSREGISTRY.register("emerald_rrar",
			() -> new PistolItem(PistolMaterial.EMERALD_RRAR));
	public static final Supplier<PistolItem> DIAMOND_RRAR = ITEMSREGISTRY.register("diamond_rrar",
			() -> new PistolItem(PistolMaterial.DIAMOND_RRAR));
	public static final Supplier<PistolItem> NETHERITE_RRAR = ITEMSREGISTRY.register("netherite_rrar",
			() -> new PistolItem(PistolMaterial.NETHERITE_RRAR));

	public static final Supplier<PistolItem> ONYX_RRAR = ITEMSREGISTRY.register("onyx_rrar",
			() -> new PistolItem(PistolMaterial.ONYX_RRAR));
	public static final Supplier<PistolItem> TITAN_RRAR = ITEMSREGISTRY.register("titan_rrar",
			() -> new PistolItem(PistolMaterial.TITAN_RRAR));


	public static final Supplier<PistolItem> GOLDEN_COLT_ANACONDA = ITEMSREGISTRY.register("golden_colt_anaconda",
			() -> new PistolItem(PistolMaterial.GOLDEN_COLT_ANACONDA));
	public static final Supplier<PistolItem> IRON_COLT_ANACONDA = ITEMSREGISTRY.register("iron_colt_anaconda",
			() -> new PistolItem(PistolMaterial.IRON_COLT_ANACONDA));
	public static final Supplier<PistolItem> EMERALD_COLT_ANACONDA = ITEMSREGISTRY.register("emerald_colt_anaconda",
			() -> new PistolItem(PistolMaterial.EMERALD_COLT_ANACONDA));
	public static final Supplier<PistolItem> DIAMOND_COLT_ANACONDA = ITEMSREGISTRY.register("diamond_colt_anaconda",
			() -> new PistolItem(PistolMaterial.DIAMOND_COLT_ANACONDA));
	public static final Supplier<PistolItem> NETHERITE_COLT_ANACONDA = ITEMSREGISTRY.register("netherite_colt_anaconda",
			() -> new PistolItem(PistolMaterial.NETHERITE_COLT_ANACONDA));

	public static final Supplier<PistolItem> ONYX_COLT_ANACONDA = ITEMSREGISTRY.register("onyx_colt_anaconda",
			() -> new PistolItem(PistolMaterial.ONYX_COLT_ANACONDA));
	public static final Supplier<PistolItem> TITAN_COLT_ANACONDA = ITEMSREGISTRY.register("titan_colt_anaconda",
			() -> new PistolItem(PistolMaterial.TITAN_COLT_ANACONDA));

	public static final Supplier<PistolItem> GOLDEN_S_AND_W_M629C = ITEMSREGISTRY.register("golden_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.GOLDEN_S_AND_W_M629C));
	public static final Supplier<PistolItem> IRON_S_AND_W_M629C = ITEMSREGISTRY.register("iron_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.IRON_S_AND_W_M629C));
	public static final Supplier<PistolItem> EMERALD_S_AND_W_M629C = ITEMSREGISTRY.register("emerald_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.EMERALD_S_AND_W_M629C));
	public static final Supplier<PistolItem> DIAMOND_S_AND_W_M629C = ITEMSREGISTRY.register("diamond_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.DIAMOND_S_AND_W_M629C));
	public static final Supplier<PistolItem> NETHERITE_S_AND_W_M629C = ITEMSREGISTRY.register("netherite_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.NETHERITE_S_AND_W_M629C));
	
	public static final Supplier<PistolItem> ONYX_S_AND_W_M629C = ITEMSREGISTRY.register("onyx_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.ONYX_S_AND_W_M629C));
	public static final Supplier<PistolItem> TITAN_S_AND_W_M629C = ITEMSREGISTRY.register("titan_s_and_w_m629c",
			() -> new PistolItem(PistolMaterial.TITAN_S_AND_W_M629C));


	public static final Supplier<PistolItem> GOLDEN_DESERT_EAGLE = ITEMSREGISTRY.register("golden_desert_eagle",
			() -> new PistolItem(PistolMaterial.GOLDEN_DESERT_EAGLE));
	public static final Supplier<PistolItem> IRON_DESERT_EAGLE = ITEMSREGISTRY.register("iron_desert_eagle",
			() -> new PistolItem(PistolMaterial.IRON_DESERT_EAGLE));
	public static final Supplier<PistolItem> EMERALD_DESERT_EAGLE = ITEMSREGISTRY.register("emerald_desert_eagle",
			() -> new PistolItem(PistolMaterial.EMERALD_DESERT_EAGLE));
	public static final Supplier<PistolItem> DIAMOND_DESERT_EAGLE = ITEMSREGISTRY.register("diamond_desert_eagle",
			() -> new PistolItem(PistolMaterial.DIAMOND_DESERT_EAGLE));
	public static final Supplier<PistolItem> NETHERITE_DESERT_EAGLE = ITEMSREGISTRY.register("netherite_desert_eagle",
			() -> new PistolItem(PistolMaterial.NETHERITE_DESERT_EAGLE));
	
	public static final Supplier<PistolItem> ONYX_DESERT_EAGLE = ITEMSREGISTRY.register("onyx_desert_eagle",
			() -> new PistolItem(PistolMaterial.ONYX_DESERT_EAGLE));
	public static final Supplier<PistolItem> TITAN_DESERT_EAGLE = ITEMSREGISTRY.register("titan_desert_eagle",
			() -> new PistolItem(PistolMaterial.TITAN_DESERT_EAGLE));


	public static final Supplier<PistolItem> GOLDEN_S_AND_W_500 = ITEMSREGISTRY.register("golden_s_and_w_500",
			() -> new PistolItem(PistolMaterial.GOLDEN_S_AND_W_500));
	public static final Supplier<PistolItem> IRON_S_AND_W_500 = ITEMSREGISTRY.register("iron_s_and_w_500",
			() -> new PistolItem(PistolMaterial.IRON_S_AND_W_500));
	public static final Supplier<PistolItem> EMERALD_S_AND_W_500 = ITEMSREGISTRY.register("emerald_s_and_w_500",
			() -> new PistolItem(PistolMaterial.EMERALD_S_AND_W_500));
	public static final Supplier<PistolItem> DIAMOND_S_AND_W_500 = ITEMSREGISTRY.register("diamond_s_and_w_500",
			() -> new PistolItem(PistolMaterial.DIAMOND_S_AND_W_500));
	public static final Supplier<PistolItem> NETHERITE_S_AND_W_500 = ITEMSREGISTRY.register("netherite_s_and_w_500",
			() -> new PistolItem(PistolMaterial.NETHERITE_S_AND_W_500));
	
	public static final Supplier<PistolItem> ONYX_S_AND_W_500 = ITEMSREGISTRY.register("onyx_s_and_w_500",
			() -> new PistolItem(PistolMaterial.ONYX_S_AND_W_500));
	public static final Supplier<PistolItem> TITAN_S_AND_W_500 = ITEMSREGISTRY.register("titan_s_and_w_500",
			() -> new PistolItem(PistolMaterial.TITAN_S_AND_W_500));


	public static final DeferredItem<Item> PISTOL_BULLET = ITEMSREGISTRY.registerItem("pistol_bullet", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));

	public static final DeferredItem<Item> FOURTY_FOUR_S_AND_W_ROUNDS = ITEMSREGISTRY.registerItem("fourty_four_s_and_w_rounds", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(12));
	public static final DeferredItem<Item> FIFTY_S_AND_W_ROUNDS = ITEMSREGISTRY.registerItem("fifty_s_and_w_rounds", Item::new, new Item.Properties().rarity(Rarity.EPIC).stacksTo(12));

	public static final DeferredItem<Item> FIFTY_AE_ROUNDS = ITEMSREGISTRY.registerItem("fifty_ae_rounds", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(8));
	public static final DeferredItem<Item> NINEmm_PARABELLUM_ROUNDS = ITEMSREGISTRY.registerItem("ninemm_parabellum_rounds", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32));
	public static final DeferredItem<Item> TENmm_PARABELLUM_ROUNDS = ITEMSREGISTRY.registerItem("tenmm_parabellum_rounds", Item::new, new Item.Properties().rarity(Rarity.RARE).stacksTo(20));

	public static final DeferredItem<Item> JAR_GUNPOWDER_A = ITEMSREGISTRY.registerItem("jar_gunpowder_a", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
	public static final DeferredItem<Item> JAR_GUNPOWDER_B = ITEMSREGISTRY.registerItem("jar_gunpowder_b", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
	public static final DeferredItem<Item> JAR_GUNPOWDER_C = ITEMSREGISTRY.registerItem("jar_gunpowder_c", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
	public static final DeferredItem<Item> JAR_GUNPOWDER_D = ITEMSREGISTRY.registerItem("jar_gunpowder_d", Item::new, new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
	
	// Balloons
	public static final DeferredItem<Item> BALLOON_WHITE = ITEMSREGISTRY.register("balloon_white", 
			() -> new BalloonItem(BalloonColor.WHITE));
	public static final DeferredItem<Item> BALLOON_ORANGE = ITEMSREGISTRY.register("balloon_orange",
			() -> new BalloonItem(BalloonColor.ORANGE));
	public static final DeferredItem<Item> BALLOON_MAGENTA = ITEMSREGISTRY.register("balloon_magenta", 
			() -> new BalloonItem(BalloonColor.MAGENTA));
	public static final DeferredItem<Item> BALLOON_LIGHT_BLUE = ITEMSREGISTRY.register("balloon_light_blue", 
			() -> new BalloonItem(BalloonColor.LIGHT_BLUE));
	public static final DeferredItem<Item> BALLOON_YELLOW = ITEMSREGISTRY.register("balloon_yellow", 
			() -> new BalloonItem(BalloonColor.YELLOW));
	public static final DeferredItem<Item> BALLOON_LIME = ITEMSREGISTRY.register("balloon_lime", 
			() -> new BalloonItem(BalloonColor.LIME));
	public static final DeferredItem<Item> BALLOON_PINK = ITEMSREGISTRY.register("balloon_pink", 
			() -> new BalloonItem(BalloonColor.PINK));
	public static final DeferredItem<Item> BALLOON_GRAY = ITEMSREGISTRY.register("balloon_gray", 
			() -> new BalloonItem(BalloonColor.GRAY));
	public static final DeferredItem<Item> BALLOON_LIGHT_GRAY = ITEMSREGISTRY.register("balloon_light_gray", 
			() -> new BalloonItem(BalloonColor.LIGHT_GRAY));
	public static final DeferredItem<Item> BALLOON_CYAN = ITEMSREGISTRY.register("balloon_cyan", 
			() -> new BalloonItem(BalloonColor.CYAN));
	public static final DeferredItem<Item> BALLOON_PURPLE = ITEMSREGISTRY.register("balloon_purple", 
			() -> new BalloonItem(BalloonColor.PURPLE));
	public static final DeferredItem<Item> BALLOON_BLUE = ITEMSREGISTRY.register("balloon_blue", 
			() -> new BalloonItem(BalloonColor.BLUE));
	public static final DeferredItem<Item> BALLOON_BROWN = ITEMSREGISTRY.register("balloon_brown", 
			() -> new BalloonItem(BalloonColor.BROWN));
	public static final DeferredItem<Item> BALLOON_GREEN = ITEMSREGISTRY.register("balloon_green", 
			() -> new BalloonItem(BalloonColor.GREEN));
	public static final DeferredItem<Item> BALLOON_RED = ITEMSREGISTRY.register("balloon_red", 
			() -> new BalloonItem(BalloonColor.RED));
	public static final DeferredItem<Item> BALLOON_BLACK = ITEMSREGISTRY.register("balloon_black", 
			() -> new BalloonItem(BalloonColor.BLACK));

	// feathers
	public static final DeferredItem<Item> FEATHER_BLACK = ITEMSREGISTRY.registerItem("feather_black", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_BLUE = ITEMSREGISTRY.registerItem("feather_blue", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_BROWN = ITEMSREGISTRY.registerItem("feather_brown", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_CYAN = ITEMSREGISTRY.registerItem("feather_cyan", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_GRAY = ITEMSREGISTRY.registerItem("feather_gray", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_GREEN = ITEMSREGISTRY.registerItem("feather_green", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_LIGHT_BLUE = ITEMSREGISTRY.registerItem("feather_light_blue", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_LIGHT_GRAY = ITEMSREGISTRY.registerItem("feather_light_gray", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_LIME = ITEMSREGISTRY.registerItem("feather_lime", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_MAGENTA = ITEMSREGISTRY.registerItem("feather_magenta", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_ORANGE = ITEMSREGISTRY.registerItem("feather_orange", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_PINK = ITEMSREGISTRY.registerItem("feather_pink", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_PURPLE = ITEMSREGISTRY.registerItem("feather_purple", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_RED = ITEMSREGISTRY.registerItem("feather_red", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_YELLOW = ITEMSREGISTRY.registerItem("feather_yellow", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));
	public static final DeferredItem<Item> FEATHER_GOLDEN = ITEMSREGISTRY.registerItem("feather_golden", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));

	public static final DeferredItem<Item> GOLDEN_DYE = ITEMSREGISTRY.registerItem("golden_dye", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64));

	//totems
	public static final DeferredItem<Item> TOTEM_OF_LIFE = ITEMSREGISTRY.register("totem_of_life", TotemOfLifeItem::new);
	public static final DeferredItem<Item> TOTEM_OF_UNCRYING = ITEMSREGISTRY.register("totem_of_uncrying", TotemOfUncryingItem::new);

	// Musik Disks
	public static final DeferredItem<Item> MD_P1NG_P0NG = ITEMSREGISTRY.register("md_p1ng_p0ng", MD_P1NGP0NGItem::new);
	public static final DeferredItem<Item> MD_CANT_GET_YOU_OUT_OF_MY_HEAD = ITEMSREGISTRY.register("md_cant_get_you_out_of_my_head", MD_CANT_GET_YOU_OUT_OF_MY_HEADItem::new);
	public static final DeferredItem<Item> MD_AQUAA = ITEMSREGISTRY.register("md_aquaa", MD_AQUAAItem::new);
	public static final DeferredItem<Item> MD_DRUNK_RHYTHM = ITEMSREGISTRY.register("md_drunk_rhythm", MD_DRUNK_RHYTHMItem::new);

	public static final DeferredItem<TNTStickItem> TNT_STICK = ITEMSREGISTRY.register("tnt_stick", TNTStickItem::new);

	public static final DeferredItem<SMBSuperFanItem> SMB_SUPER_FAN = ITEMSREGISTRY.register("smb_super_fan", SMBSuperFanItem::new);

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
