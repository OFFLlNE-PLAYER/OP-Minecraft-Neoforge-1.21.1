package net.offllneplayer.opminecraft.UTIL.ZE_JAVA_GENERATION;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
		String blockModelsDir = resourcesPath + "/models/blocks";
		String itemModelsDir = resourcesPath + "/models/items";
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
				String modelId = wood + "_" + trim + "_trim_ancient_chest";
				boolean isBaseModel = wood.equals("acacia") && trim.equals("gold");

				// Create blocks model JSON (skip the base model)
				if (!isBaseModel) {
					JsonObject modelJson = new JsonObject();
					modelJson.addProperty("parent", "opminecraft:blocks/acacia_gold_trim_ancient_chest");

					JsonObject textures = new JsonObject();
					textures.addProperty("0", "blocks/" + wood + "_planks");
					textures.addProperty("1", getTextureForTrim(trim));
					textures.addProperty("particle", "blocks/" + wood + "_planks");

					modelJson.add("textures", textures);
					modelJson.addProperty("render_type", "minecraft:cutout_mipped");

					// Write blocks model file
					File blockModelFile = new File(blockModelsDir, modelId + ".json");
					try (FileWriter writer = new FileWriter(blockModelFile)) {
						gson.toJson(modelJson, writer);
						blockModelCount++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				// Create items model JSON (for all variants including base model)
				JsonObject itemModelJson = new JsonObject();
				itemModelJson.addProperty("parent", "opminecraft:blocks/" + modelId);

				// Write items model file
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
				northVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				variants.add("facing=north,waterlogged=false", northVariant);

				// Add waterlogged=true variant
				JsonObject northWaterloggedVariant = new JsonObject();
				northWaterloggedVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				variants.add("facing=north,waterlogged=true", northWaterloggedVariant);

				// East facing
				JsonObject eastVariant = new JsonObject();
				eastVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				eastVariant.addProperty("y", 90);
				variants.add("facing=east,waterlogged=false", eastVariant);

				// Add waterlogged=true variant
				JsonObject eastWaterloggedVariant = new JsonObject();
				eastWaterloggedVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				eastWaterloggedVariant.addProperty("y", 90);
				variants.add("facing=east,waterlogged=true", eastWaterloggedVariant);

				// South facing
				JsonObject southVariant = new JsonObject();
				southVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				southVariant.addProperty("y", 180);
				variants.add("facing=south,waterlogged=false", southVariant);

				// Add waterlogged=true variant
				JsonObject southWaterloggedVariant = new JsonObject();
				southWaterloggedVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				southWaterloggedVariant.addProperty("y", 180);
				variants.add("facing=south,waterlogged=true", southWaterloggedVariant);

				// West facing
				JsonObject westVariant = new JsonObject();
				westVariant.addProperty("model", "opminecraft:blocks/" + modelId);
				westVariant.addProperty("y", 270);
				variants.add("facing=west,waterlogged=false", westVariant);

				// Add waterlogged=true variant
				JsonObject westWaterloggedVariant = new JsonObject();
				westWaterloggedVariant.addProperty("model", "opminecraft:blocks/" + modelId);
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
				return "blocks/redstone_block";
			case "lapis":
				return "blocks/lapis_block";
			case "amethyst":
				return "blocks/amethyst_block";
			case "quartz":
				return "blocks/quartz_block_top";
			default:
				return "blocks/" + trim + "_block";
		}
	}
}
