package net.offllneplayer.opminecraft.items._iwe.tntstick;


public class tntStickBarColor_Method {

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/**
	 * Converts a hex color string to an integer
	 */
	public static int hexToInt(String hex) {
		if (hex.startsWith("#")) {
			hex = hex.substring(1);
		}
		return Integer.parseInt(hex, 16);
	}

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public static int countdownBarColor(float progress, String startColor, String nearDangerColor, String dangercolor) {

		// Clamp progress to [0,1]
		progress = Math.max(0.0F, Math.min(1.0F, progress));

		// Convert hex colors that we actually use
		int startInt = hexToInt(startColor);        // 0% color (e.g., yellow)
		int nearDangerInt = hexToInt(nearDangerColor); // 80% color (bright red)
		int dangerInt = hexToInt(dangercolor);     // 95% color (dark red)

		// We'll compute a base color from progress, then apply a pulse whose frequency scales with progress.
		int baseColorInt;

		if (progress < 0.80F) {
			// 0%..80%: smooth transition start -> bright red
			float t = progress / 0.80F; // 0..1 over this span
			int sr = (startInt >> 16) & 0xFF;
			int sg = (startInt >> 8) & 0xFF;
			int sb = startInt & 0xFF;
			int br = (nearDangerInt >> 16) & 0xFF;
			int bg = (nearDangerInt >> 8) & 0xFF;
			int bb = nearDangerInt & 0xFF;
			int r = sr + Math.round((br - sr) * t);
			int g = sg + Math.round((bg - sg) * t);
			int b = sb + Math.round((bb - sb) * t);
			baseColorInt = (r << 16) | (g << 8) | b;

		} else if (progress < 0.95F) {
			// 80%..95%: transition bright red -> dark red
			float t = (progress - 0.80F) / 0.15F; // 0..1
			int br = (nearDangerInt >> 16) & 0xFF;
			int dr = (dangerInt >> 16) & 0xFF;
			int r = Math.round(br + (dr - br) * t);
			baseColorInt = (r << 16);
		} else {
			// >=95%: base color will be pulsed between bright and dark red below
			baseColorInt = dangerInt; // placeholder; will get replaced by pulse blend
		}

		// Frequency scales with progress: slow at 0%, fast at 100%.
		// Keep a gentle minimum to ensure visible pulsing even early on.
		float minFreq = 1.25F;
		float maxFreq = 10.0F;
		float freq = minFreq + (maxFreq - minFreq) * progress;

		// Sine pulse 0..1
		float s = (float) Math.sin(progress * (float) Math.PI * 2.0F * freq) * 0.5F + 0.5F;

		int outColorInt;
		if (progress < 0.95F) {
			// Below 95%: pulse the brightness subtly by blending toward white.
			// Amplitude increases slightly with progress to make it feel more urgent.
			float minAmp = 0.05F;   // 5% at start
			float maxAmp = 0.12F;   // 12% near 95%
			float amp = minAmp + (maxAmp - minAmp) * progress;
			int r = (baseColorInt >> 16) & 0xFF;
			int g = (baseColorInt >> 8) & 0xFF;
			int b = baseColorInt & 0xFF;
			int wr = 0xFF, wg = 0xFF, wb = 0xFF;
			int pr = Math.round(r + (wr - r) * (amp * s));
			int pg = Math.round(g + (wg - g) * (amp * s));
			int pb = Math.round(b + (wb - b) * (amp * s));
			outColorInt = (pr << 16) | (pg << 8) | pb;
		} else {
			// 95%+: pulse between bright red and dark red as requested, but use the scaled frequency.
			int br = (nearDangerInt >> 16) & 0xFF;
			int dr = (dangerInt >> 16) & 0xFF;
			int r = Math.round(dr + (br - dr) * s);
			outColorInt = (r << 16);
		}

		return outColorInt;
	}
}

