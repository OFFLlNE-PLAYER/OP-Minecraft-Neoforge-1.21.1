package net.offllneplayer.opminecraft.UTIL.ZE_JAVA_GENERATION.texturetransfer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * TexturePaletteTransfer
 *
 * Utility to generate a new texture by transferring the perceptual lightness palette
 * from a "diamond" source texture onto the shading/alpha structure of a target texture
 * (protektor_beretta), producing a diamond_protektor_beretta.png.
 *
 * Key points:
 * - Extracts a thorough diamond palette by sampling the cumulative distribution of L* (CIE Lab)
 *   across all opaque pixels in the diamond source. This captures the diamond's shading gradient.
 * - For each target pixel, computes its L* (perceptual lightness) and maps it to the closest
 *   diamond palette entry by L*, preserving the exact target alpha channel.
 * - Optional Floyd–Steinberg dithering on normalized L* to reduce banding when mapping to a
 *   discrete palette.
 *
 * How to run (paths are relative to repo root by default):
 *   java net.offllneplayer.opminecraft.UTIL.ZE_JAVA_GENERATION.TexturePaletteTransfer \
 *        [diamond_src] [protektor_src] [output] [paletteSize] [dither]
 *
 * Defaults:
 *   paletteSize = 32
 *   dither = true
 */
public class TextureTransfer {

    // ANSI color helpers (pink / light magenta). If the console does not support ANSI, these are harmless.
    private static final class Colors {
        static final String RESET = "\u001B[0m";
        static final String PINK = "\u001B[95m"; // bright magenta (pink-ish)
    }

    public static void main(String[] args) {
        try {
            // Build default config case, then allow CLI overrides.
            ConfigTextureTransfer cfg = ConfigTextureTransfer.getFieldsFromConfig();

            if (args.length > 0 && !args[0].isBlank()) cfg.paletteSourcePath = Paths.get(args[0]);
            if (args.length > 1 && !args[1].isBlank()) cfg.targetSourcePath = Paths.get(args[1]);
            if (args.length > 2 && !args[2].isBlank()) cfg.outputPath = Paths.get(args[2]);
            if (args.length > 3 && isInt(args[3])) cfg.paletteSize = Math.max(1, Integer.parseInt(args[3]));
            if (args.length > 4 && !args[4].isBlank()) cfg.dither = Boolean.parseBoolean(args[4]);

            run(cfg);
        } catch (Exception e) {
            System.err.println(Colors.PINK + "[PaletteTransfer] ERROR: " + e.getMessage() + Colors.RESET);
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Execute transfer using a provided configuration.
     */
    public static void run(ConfigTextureTransfer cfg) throws IOException {
        Path colorPalletteTexture = cfg.paletteSourcePath;
        Path shapeTexture = cfg.targetSourcePath;
        Path output = cfg.outputPath;

        if (colorPalletteTexture == null) throw new IllegalArgumentException("paletteSourcePath not set in ConfigTextureTransfer");
        if (shapeTexture == null) throw new IllegalArgumentException("targetSourcePath not set in ConfigTextureTransfer");
        if (output == null) throw new IllegalArgumentException("outputPath not set in ConfigTextureTransfer");

        if (!Files.exists(colorPalletteTexture)) {
            throw new IllegalArgumentException("Palette source image not found: " + colorPalletteTexture.toAbsolutePath());
        }
        if (!Files.exists(shapeTexture)) {
            throw new IllegalArgumentException("Target source image not found: " + shapeTexture.toAbsolutePath());
        }
        Files.createDirectories(output.getParent());

        System.out.println(Colors.PINK + "[PaletteTransfer] Loading palette source from: " + colorPalletteTexture.toAbsolutePath() + Colors.RESET);
        BufferedImage diamondRead = ImageIO.read(colorPalletteTexture.toFile());
        if (diamondRead == null) throw new IOException("Failed to decode image (unsupported/invalid PNG): " + colorPalletteTexture.toAbsolutePath());
        BufferedImage diamondImg = forceARGB(diamondRead);

        System.out.println(Colors.PINK + "[PaletteTransfer] Loading target from: " + shapeTexture.toAbsolutePath() + Colors.RESET);
        BufferedImage protektorRead = ImageIO.read(shapeTexture.toFile());
        if (protektorRead == null) throw new IOException("Failed to decode image (unsupported/invalid PNG): " + shapeTexture.toAbsolutePath());
        BufferedImage protektorImg = forceARGB(protektorRead);

        // Optional dimension validation
        if (cfg.expectedWidth > 0 && cfg.expectedHeight > 0) {
            if (diamondImg.getWidth() != cfg.expectedWidth || diamondImg.getHeight() != cfg.expectedHeight) {
                System.out.println(Colors.PINK + "[PaletteTransfer] WARN: Palette source dimensions " + diamondImg.getWidth() + "x" + diamondImg.getHeight() +
                        " != expected " + cfg.expectedWidth + "x" + cfg.expectedHeight + ". Proceeding." + Colors.RESET);
            }
            if (protektorImg.getWidth() != cfg.expectedWidth || protektorImg.getHeight() != cfg.expectedHeight) {
                System.out.println(Colors.PINK + "[PaletteTransfer] WARN: Target source dimensions " + protektorImg.getWidth() + "x" + protektorImg.getHeight() +
                        " != expected " + cfg.expectedWidth + "x" + cfg.expectedHeight + ". Proceeding." + Colors.RESET);
            }
        }

        Palette palette = buildDiamondPalette(diamondImg, cfg.paletteSize, cfg.opaqueAlphaThreshold);
        System.out.println(Colors.PINK + "[PaletteTransfer] Palette size: " + palette.size() + Colors.RESET);
        if (palette.size() < cfg.paletteSize) {
            System.out.println(Colors.PINK + "[PaletteTransfer] INFO: only found \"" + palette.size() + "\" colors instead of desired \"" + cfg.paletteSize + "\" colors set in config." + Colors.RESET);
        }
        if (palette.size() < 1) {
            throw new IllegalStateException("Palette is empty. Ensure palette source has opaque pixels.");
        }

        BufferedImage out = applyPaletteToTarget(protektorImg, palette, cfg.dither, cfg.targetTransparentThreshold);

        // Resolve a non-clobbering output path if the desired one exists already
        Path finalOutput = nextAvailableOutput(output);
        // Ensure parent directory exists (already created above for desired output, but guard again for safety)
        if (finalOutput.getParent() != null) Files.createDirectories(finalOutput.getParent());

        ImageIO.write(out, "png", finalOutput.toFile());
        System.out.println(Colors.PINK + "[PaletteTransfer] Wrote result: " + finalOutput.toAbsolutePath() + Colors.RESET);
    }

    private static boolean isInt(String s) {
        try { Integer.parseInt(s); return true; } catch (Exception e) { return false; }
    }

    private static BufferedImage forceARGB(BufferedImage src) {
        if (src.getType() == BufferedImage.TYPE_INT_ARGB) return src;
        BufferedImage out = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        out.getGraphics().drawImage(src, 0, 0, null);
        return out;
    }

    // ---------------- Palette extraction ----------------

    private static class Palette {
        final int[] colors;           // ARGB
        final double[] L;             // L* values ascending
        final double minL;
        final double maxL;
        Palette(int[] colors, double[] L) {
            this.colors = colors;
            this.L = L;
            this.minL = L.length == 0 ? 0.0 : L[0];
            this.maxL = L.length == 0 ? 1.0 : L[L.length - 1];
        }
        int size() { return colors.length; }
    }

    private static Palette buildDiamondPalette(BufferedImage diamondImg, int desiredSize, int opaqueAlphaThreshold) {
        int w = diamondImg.getWidth();
        int h = diamondImg.getHeight();

        // Count colors by frequency for cumulative distribution over L*
        Map<Integer, Integer> counts = new HashMap<>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int argb = diamondImg.getRGB(x, y);
                int a = (argb >>> 24) & 0xFF;
                if (a <= opaqueAlphaThreshold) continue; // skip transparent/near-transparent per config
                // Normalize alpha to opaque for color identity (keep RGB only, we preserve alpha later on target)
                int rgb = argb & 0x00FFFFFF;
                counts.merge(rgb, 1, Integer::sum);
            }
        }
        if (counts.isEmpty()) throw new IllegalStateException("Diamond image has no opaque pixels.");

        // Build entries with L* and sort ascending by L*
        class Entry { int rgb; int count; double L; Entry(int rgb, int count, double L){this.rgb=rgb;this.count=count;this.L=L;} }
        List<Entry> entries = new ArrayList<>(counts.size());
        int total = 0;
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            int rgb = e.getKey() | 0xFF000000; // ensure ARGB opaque for L* calculation
            double L = rgbToLStar(rgb);
            int c = e.getValue();
            total += c;
            entries.add(new Entry(rgb, c, L));
        }
        entries.sort(Comparator.comparingDouble(o -> o.L));

        // Sample desiredSize points at equal cumulative mass intervals (percentiles)
        int size = Math.min(desiredSize, entries.size());
        int[] paletteColors = new int[size];
        double[] paletteL = new double[size];

        double[] cum = new double[entries.size()];
        int running = 0; int idx = 0;
        for (int i = 0; i < entries.size(); i++) {
            running += entries.get(i).count;
            cum[i] = (double) running / (double) total;
        }
        // pick mid-quantiles to avoid extremes bias
        int filled = 0;
        double lastL = Double.NaN;
        for (int i = 0; i < size * 3 && filled < size; i++) { // allow retries to avoid duplicates
            double q = (filled + 0.5) / size;
            int pick = percentileIndex(cum, q);
            Entry e = entries.get(pick);
            // Avoid identical L* repeats; nudge forward if necessary
            while (filled > 0 && Math.abs(e.L - lastL) < 1e-6 && pick + 1 < entries.size()) {
                pick++;
                e = entries.get(pick);
            }
            paletteColors[filled] = e.rgb;
            paletteL[filled] = e.L;
            lastL = e.L;
            filled++;
        }
        if (filled < size) {
            // backfill linearly if we couldn't fill all due to duplicates
            while (filled < size) {
                int i = (int) Math.round(((double) filled / (double) size) * (entries.size() - 1));
                Entry e = entries.get(i);
                paletteColors[filled] = e.rgb;
                paletteL[filled] = e.L;
                filled++;
            }
            // re-sort by L*
            sortByL(paletteColors, paletteL);
        }
        return new Palette(paletteColors, paletteL);
    }

    private static int percentileIndex(double[] cum, double q) {
        int lo = 0, hi = cum.length - 1, ans = hi;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (cum[mid] >= q) { ans = mid; hi = mid - 1; } else { lo = mid + 1; }
        }
        return ans;
    }

    private static void sortByL(int[] colors, double[] L) {
        Integer[] order = new Integer[colors.length];
        for (int i = 0; i < order.length; i++) order[i] = i;
        Arrays.sort(order, Comparator.comparingDouble(i -> L[i]));
        int[] c2 = new int[colors.length];
        double[] l2 = new double[L.length];
        for (int i = 0; i < order.length; i++) { c2[i] = colors[order[i]]; l2[i] = L[order[i]]; }
        System.arraycopy(c2, 0, colors, 0, colors.length);
        System.arraycopy(l2, 0, L, 0, L.length);
    }

    // ---------------- Mapping ----------------

    private static BufferedImage applyPaletteToTarget(BufferedImage target, Palette palette, boolean dither, int transparentThreshold) {
        int w = target.getWidth();
        int h = target.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // Precompute target L* and min/max over non-transparent pixels
        double[] tL = new double[w * h];
        int[] alpha = new int[w * h];
        double minL = Double.POSITIVE_INFINITY, maxL = Double.NEGATIVE_INFINITY;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                int argb = target.getRGB(x, y);
                int a = (argb >>> 24) & 0xFF;
                alpha[i] = a;
                if (a <= transparentThreshold) {
                    tL[i] = 0.0;
                } else {
                    double L = rgbToLStar(argb);
                    tL[i] = L;
                    if (L < minL) minL = L;
                    if (L > maxL) maxL = L;
                }
            }
        }
        if (!(maxL > minL)) {
            // Edge case: flat lightness. Map all to closest palette mid.
            int midIdx = paletteIndexForL((palette.minL + palette.maxL) * 0.5, palette.L);
            int color = (palette.colors[midIdx] & 0x00FFFFFF);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int i = y * w + x;
                    int a = alpha[i] & 0xFF;
                    int outArgb = (a << 24) | color;
                    out.setRGB(x, y, outArgb);
                }
            }
            return out;
        }

        // Normalize target L* to [0,1]
        double rangeT = (maxL - minL);
        double[] normTL = new double[w * h];
        for (int i = 0; i < normTL.length; i++) {
            if (alpha[i] <= transparentThreshold) { normTL[i] = 0; } else { normTL[i] = (tL[i] - minL) / rangeT; }
        }

        // Palette normalized L* (by its own min/max) for dithering
        double rangeP = Math.max(1e-9, (palette.maxL - palette.minL));
        double[] normPL = new double[palette.L.length];
        for (int i = 0; i < normPL.length; i++) normPL[i] = (palette.L[i] - palette.minL) / rangeP;

        int[] chosen = new int[w * h];
        if (dither) {
            // Floyd–Steinberg on normalized target L*
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int i = y * w + x;
                    if (alpha[i] <= transparentThreshold) continue;
                    double oldL = normTL[i];
                    int idx = paletteIndexForNormL(oldL, normPL);
                    chosen[i] = idx;
                    double newL = normPL[idx];
                    double err = oldL - newL;

                    // distribute error to neighbors (skip if neighbor transparent)
                    // (x+1, y) += err * 7/16
                    if (x + 1 < w) {
                        int j = y * w + (x + 1);
                        if (alpha[j] > transparentThreshold) normTL[j] = clamp01(normTL[j] + err * 7.0 / 16.0);
                    }
                    // (x-1, y+1) += err * 3/16
                    if (x - 1 >= 0 && y + 1 < h) {
                        int j = (y + 1) * w + (x - 1);
                        if (alpha[j] > transparentThreshold) normTL[j] = clamp01(normTL[j] + err * 3.0 / 16.0);
                    }
                    // (x, y+1) += err * 5/16
                    if (y + 1 < h) {
                        int j = (y + 1) * w + x;
                        if (alpha[j] > transparentThreshold) normTL[j] = clamp01(normTL[j] + err * 5.0 / 16.0);
                    }
                    // (x+1, y+1) += err * 1/16
                    if (x + 1 < w && y + 1 < h) {
                        int j = (y + 1) * w + (x + 1);
                        if (alpha[j] > transparentThreshold) normTL[j] = clamp01(normTL[j] + err * 1.0 / 16.0);
                    }
                }
            }
        } else {
            for (int i = 0; i < normTL.length; i++) {
                if (alpha[i] <= transparentThreshold) continue;
                chosen[i] = paletteIndexForNormL(normTL[i], normPL);
            }
        }

        // Compose output, preserving target alpha exactly
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                int a = alpha[i] & 0xFF;
                if (a <= transparentThreshold) { out.setRGB(x, y, 0); continue; }
                int color = palette.colors[chosen[i]] & 0x00FFFFFF; // use palette RGB, discard palette alpha
                int outArgb = (a << 24) | color;
                out.setRGB(x, y, outArgb);
            }
        }
        return out;
    }

    private static double clamp01(double v) { return v < 0 ? 0 : (v > 1 ? 1 : v); }

    private static Path nextAvailableOutput(Path desired) {
        try {
            if (desired == null) return null;
            Path parent = desired.getParent();
            String name = desired.getFileName().toString();
            String base;
            String ext = "";
            int dot = name.lastIndexOf('.');
            if (dot > 0) {
                base = name.substring(0, dot);
                ext = name.substring(dot);
            } else {
                base = name;
            }
            Path candidate = desired;
            if (Files.exists(candidate)) {
                int i = 1;
                while (true) {
                    String newName = base + "_" + i + ext;
                    candidate = (parent != null) ? parent.resolve(newName) : Paths.get(newName);
                    if (!Files.exists(candidate)) {
                        System.out.println(Colors.PINK + "[PaletteTransfer] INFO: Output already exists. Using next available: " + candidate.toAbsolutePath() + Colors.RESET);
                        break;
                    }
                    i++;
                }
            }
            return candidate;
        } catch (Exception e) {
            return desired;
        }
    }

    private static int paletteIndexForL(double L, double[] paletteL) {
        // Find the closest L* in an ascending array
        int n = paletteL.length;
        if (n == 0) return 0;
        if (L <= paletteL[0]) return 0;
        if (L >= paletteL[n - 1]) return n - 1;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            double v = paletteL[mid];
            if (v < L) lo = mid + 1; else hi = mid - 1;
        }
        // lo is first index with paletteL[lo] >= L
        int i2 = lo;
        int i1 = lo - 1;
        return (L - paletteL[i1] <= paletteL[i2] - L) ? i1 : i2;
    }

    private static int paletteIndexForNormL(double normL, double[] normPaletteL) {
        // normL in [0,1]
        double L = normL; // already normalized
        int n = normPaletteL.length;
        if (n == 0) return 0;
        if (L <= normPaletteL[0]) return 0;
        if (L >= normPaletteL[n - 1]) return n - 1;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            double v = normPaletteL[mid];
            if (v < L) lo = mid + 1; else hi = mid - 1;
        }
        int i2 = lo;
        int i1 = lo - 1;
        return (L - normPaletteL[i1] <= normPaletteL[i2] - L) ? i1 : i2;
    }

    // ---------------- Color science helpers ----------------

    /**
     * Compute CIE L* (perceptual lightness) from an ARGB pixel (sRGB, D65).
     * Alpha is ignored for the computation.
     */
    private static double rgbToLStar(int argb) {
        int r8 = (argb >> 16) & 0xFF;
        int g8 = (argb >> 8) & 0xFF;
        int b8 = (argb) & 0xFF;
        // sRGB to [0,1]
        double r = r8 / 255.0;
        double g = g8 / 255.0;
        double b = b8 / 255.0;
        // sRGB -> linear RGB
        r = srgbToLinear(r);
        g = srgbToLinear(g);
        b = srgbToLinear(b);
        // linear RGB -> XYZ (D65)
        double X = r * 0.4124564 + g * 0.3575761 + b * 0.1804375;
        double Y = r * 0.2126729 + g * 0.7151522 + b * 0.0721750;
        double Z = r * 0.0193339 + g * 0.1191920 + b * 0.9503041;
        // normalize by reference white D65
        double Xn = 0.95047, Yn = 1.00000, Zn = 1.08883;
        double x = X / Xn;
        double y = Y / Yn;
        double z = Z / Zn;
        // XYZ -> Lab
        double fx = fLab(x);
        double fy = fLab(y);
        // double fz = fLab(z); // not needed for L*
        return 116.0 * fy - 16.0; // L*
    }

    private static double srgbToLinear(double c) {
        if (c <= 0.04045) return c / 12.92;
        return Math.pow((c + 0.055) / 1.055, 2.4);
    }

    private static double fLab(double t) {
        double eps = 216.0 / 24389.0; // ~0.008856
        double kappa = 24389.0 / 27.0; // ~903.3
        if (t > eps) return Math.cbrt(t);
        return (kappa * t + 16.0) / 116.0;
    }
}
