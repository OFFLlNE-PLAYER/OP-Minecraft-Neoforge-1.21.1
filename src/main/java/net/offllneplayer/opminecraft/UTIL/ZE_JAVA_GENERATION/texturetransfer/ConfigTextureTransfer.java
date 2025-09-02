package net.offllneplayer.opminecraft.UTIL.ZE_JAVA_GENERATION.texturetransfer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ConfigTextureTransfer
 *
 * Encapsulates all tunable parameters for a palette-based texture color transfer.
 * Create/modify an instance to "plug in" different textures and behaviors without
 * changing the core logic in TextureTransfer.
 */
public class ConfigTextureTransfer {
    /**
     * Absolute or relative (to project root) path to the palette source image.
     * For diamond→protektor, this is the diamond texture whose colors form the palette.
     */
    public Path paletteSourcePath;

    /**
     * Absolute or relative path to the target image whose shading/alpha we will recolor.
     */
    public Path targetSourcePath;

    /**
     * Absolute or relative path where the output PNG will be written.
     */
    public Path outputPath;

    /**
     * Number of colors to sample from the palette source, distributed by cumulative L* mass.
     * Higher values reduce banding but can increase file size/processing time.
     */
    public int paletteSize = 64;

    /**
     * Enable Floyd–Steinberg dithering over normalized L* to reduce banding.
     */
    public boolean dither = true;

    /**
     * Pixels in the palette source with alpha <= opaqueAlphaThreshold are ignored when
     * building the palette (treated as transparent). Range: 0..255. Typical: 0 or ~8.
     */
    public int opaqueAlphaThreshold = 0;

    /**
     * Pixels in the target with alpha <= targetTransparentThreshold are treated as fully
     * transparent and copied as-is (alpha preserved exactly). Range: 0..255.
     */
    public int targetTransparentThreshold = 0;

    /**
     * If specified (>0), validates that both palette and target images match these dimensions.
     * Set to 0 to disable validation for width/height respectively.
     */
    public int expectedWidth = 0;
    public int expectedHeight = 0;

	public String vanilla_ass_path = "src\\main\\resources\\assets\\minecraft\\textures\\";
	public String mod_ass_path = "src\\main\\resources\\assets\\opminecraft\\textures\\";

	public String sourceColorPaletteTexture = "item\\pistol\\text_emerald_92fs.png";
	public String sourceShapeTexture = "item\\pistol\\text_golden_m9a1.png";
	public String outputTexturePath = "item\\pistol\\text_emerald_m9a1.png";


	public static ConfigTextureTransfer getFieldsFromConfig() {
		ConfigTextureTransfer cnfgTextureTransfer = new ConfigTextureTransfer();
		cnfgTextureTransfer.paletteSourcePath = Paths.get(cnfgTextureTransfer.mod_ass_path + cnfgTextureTransfer.sourceColorPaletteTexture);
		cnfgTextureTransfer.targetSourcePath = Paths.get(cnfgTextureTransfer.mod_ass_path + cnfgTextureTransfer.sourceShapeTexture);
		cnfgTextureTransfer.outputPath = Paths.get(cnfgTextureTransfer.mod_ass_path + cnfgTextureTransfer.outputTexturePath);
        cnfgTextureTransfer.paletteSize = 1000;            // Adjust if you want a coarser/finer palette
        cnfgTextureTransfer.dither = true;               // Disable if you prefer a cleaner, non-dithered look
        cnfgTextureTransfer.opaqueAlphaThreshold = 0;    // Treat only alpha==0 as transparent in the palette source
        cnfgTextureTransfer.targetTransparentThreshold = 0; // Preserve exact transparency for alpha==0 in the target
        cnfgTextureTransfer.expectedWidth = 0;           // Set to e.g. 128 if you want strict size checks
        cnfgTextureTransfer.expectedHeight = 0;          // Set to e.g. 128 if you want strict size checks
        return cnfgTextureTransfer;
    }
}
