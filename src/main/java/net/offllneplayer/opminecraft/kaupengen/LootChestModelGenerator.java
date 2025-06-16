package net.offllneplayer.opminecraft.kaupengen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class LootChestModelGenerator {

	private static final List<String> WOOD_TYPES = Arrays.asList(
		"acacia", "oak", "birch", "spruce", "jungle", "dark_oak",
		"mangrove", "cherry", "bamboo", "crimson", "warped"
	);

	private static final List<String> TRIM_MATERIALS = Arrays.asList(
		"gold", "iron", "copper", "diamond", "netherite",
		"emerald", "lapis", "redstone", "quartz", "amethyst"
	);

	public static void main(String[] args) {
		// Path to your temporary generated folder
		String resourcesPath = "src/main/resources/generated";

		// Create directories for generated files
		String blockModelsDir = resourcesPath + "/models/block";
		String itemModelsDir = resourcesPath + "/models/item";
		String blockstatesDir = resourcesPath + "/blockstates";

		// Create output directories if they don't exist
		try {
			Files.createDirectories(Paths.get(blockModelsDir));
			Files.createDirectories(Paths.get(itemModelsDir));
			Files.createDirectories(Paths.get(blockstatesDir));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// Create GSON with HTML escaping disabled to prevent \u003d for '='
		Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.disableHtmlEscaping()
			.create();

		int blockModelCount = 0;
		int itemModelCount = 0;
		int blockstateCount = 0;

		for (String wood : WOOD_TYPES) {
			for (String trim : TRIM_MATERIALS) {
				String modelId = wood + "_" + trim + "_trim_loot_chest";
				boolean isBaseModel = wood.equals("acacia") && trim.equals("gold");

				// Create block model JSON (skip the base model)
				if (!isBaseModel) {
					JsonObject modelJson = new JsonObject();
					modelJson.addProperty("parent", "opminecraft:block/acacia_gold_trim_loot_chest");

					JsonObject textures = new JsonObject();
					textures.addProperty("0", "block/" + wood + "_planks");
					textures.addProperty("1", getTextureForTrim(trim));
					textures.addProperty("particle", "block/" + wood + "_planks");

					modelJson.add("textures", textures);
					modelJson.addProperty("render_type", "minecraft:cutout_mipped");

					// Write block model file
					File blockModelFile = new File(blockModelsDir, modelId + ".json");
					try (FileWriter writer = new FileWriter(blockModelFile)) {
						gson.toJson(modelJson, writer);
						blockModelCount++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				// Create item model JSON (for all variants including base model)
				JsonObject itemModelJson = new JsonObject();
				itemModelJson.addProperty("parent", "opminecraft:block/" + modelId);

				// Write item model file
				File itemModelFile = new File(itemModelsDir, modelId + ".json");
				try (FileWriter writer = new FileWriter(itemModelFile)) {
					gson.toJson(itemModelJson, writer);
					itemModelCount++;
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Create blockstate JSON (for all variants including base model)
				JsonObject blockstateJson = new JsonObject();
				JsonObject variants = new JsonObject();

				// North facing
				JsonObject northVariant = new JsonObject();
				northVariant.addProperty("model", "opminecraft:block/" + modelId);
				variants.add("facing=north,waterlogged=false", northVariant);

				// Add waterlogged=true variant
				JsonObject northWaterloggedVariant = new JsonObject();
				northWaterloggedVariant.addProperty("model", "opminecraft:block/" + modelId);
				variants.add("facing=north,waterlogged=true", northWaterloggedVariant);

				// East facing
				JsonObject eastVariant = new JsonObject();
				eastVariant.addProperty("model", "opminecraft:block/" + modelId);
				eastVariant.addProperty("y", 90);
				variants.add("facing=east,waterlogged=false", eastVariant);

				// Add waterlogged=true variant
				JsonObject eastWaterloggedVariant = new JsonObject();
				eastWaterloggedVariant.addProperty("model", "opminecraft:block/" + modelId);
				eastWaterloggedVariant.addProperty("y", 90);
				variants.add("facing=east,waterlogged=true", eastWaterloggedVariant);

				// South facing
				JsonObject southVariant = new JsonObject();
				southVariant.addProperty("model", "opminecraft:block/" + modelId);
				southVariant.addProperty("y", 180);
				variants.add("facing=south,waterlogged=false", southVariant);

				// Add waterlogged=true variant
				JsonObject southWaterloggedVariant = new JsonObject();
				southWaterloggedVariant.addProperty("model", "opminecraft:block/" + modelId);
				southWaterloggedVariant.addProperty("y", 180);
				variants.add("facing=south,waterlogged=true", southWaterloggedVariant);

				// West facing
				JsonObject westVariant = new JsonObject();
				westVariant.addProperty("model", "opminecraft:block/" + modelId);
				westVariant.addProperty("y", 270);
				variants.add("facing=west,waterlogged=false", westVariant);

				// Add waterlogged=true variant
				JsonObject westWaterloggedVariant = new JsonObject();
				westWaterloggedVariant.addProperty("model", "opminecraft:block/" + modelId);
				westWaterloggedVariant.addProperty("y", 270);
				variants.add("facing=west,waterlogged=true", westWaterloggedVariant);

				blockstateJson.add("variants", variants);

				// Write blockstate file
				File blockstateFile = new File(blockstatesDir, modelId + ".json");
				try (FileWriter writer = new FileWriter(blockstateFile)) {
					gson.toJson(blockstateJson, writer);
					blockstateCount++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Generated files:");
		System.out.println("- Block Models: " + blockModelCount + " files in " + blockModelsDir);
		System.out.println("- Item Models: " + itemModelCount + " files in " + itemModelsDir);
		System.out.println("- Blockstates: " + blockstateCount + " files in " + blockstatesDir);
		System.out.println("Copy these files to your mod's assets folder when ready.");
	}

	private static String getTextureForTrim(String trim) {
		switch (trim) {
			case "redstone":
				return "block/redstone_block";
			case "lapis":
				return "block/lapis_block";
			case "amethyst":
				return "block/amethyst_block";
			case "quartz":
				return "block/quartz_block_top";
			default:
				return "block/" + trim + "_block";
		}
	}
}
