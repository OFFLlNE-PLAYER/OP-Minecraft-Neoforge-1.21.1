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
	/**
	 * Converts RGB values to an integer color
	 */
	public static int rgbToInt(int r, int g, int b) {
		// Clamp values to valid range
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));
		return (r << 16) | (g << 8) | b;
	}

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public static int countdownBarColor(float progress, String earlyColor, String midColor, String lateColor, String finalBaseColor, String finalPulseColor, float pulseFrequency) {

		// Convert hex colors to RGB components
		int earlyColorInt = hexToInt(earlyColor);
		int midColorInt = hexToInt(midColor);
		int lateColorInt = hexToInt(lateColor);
		int finalBaseColorInt = hexToInt(finalBaseColor);
		int finalPulseColorInt = hexToInt(finalPulseColor);

		int r, g, b;
		if (progress >= 0.69F) { // Starting pulsing at 69%
			// Final flashing stage
			float flashPhase = (progress - 0.65F) * 2.85F; // Rescale to 0.0-1.0 range (1/0.35 ≈ 2.85)
			float pulseIntensity = (float) Math.sin(flashPhase * Math.PI * pulseFrequency) * 0.5F + 0.5F;

			int finalBaseR = (finalBaseColorInt >> 16) & 0xFF;
			int finalBaseG = (finalBaseColorInt >> 8) & 0xFF;
			int finalBaseB = finalBaseColorInt & 0xFF;

			int finalPulseR = (finalPulseColorInt >> 16) & 0xFF;
			int finalPulseG = (finalPulseColorInt >> 8) & 0xFF;
			int finalPulseB = finalPulseColorInt & 0xFF;

			r = finalBaseR + (int)(finalPulseR * pulseIntensity);
			g = finalBaseG + (int)(finalPulseG * pulseIntensity);
			b = finalBaseB + (int)(finalPulseB * pulseIntensity);
		}
		else if (progress >= 0.420F) { // Late stage
			r = (lateColorInt >> 16) & 0xFF;
			g = (lateColorInt >> 8) & 0xFF;
			b = lateColorInt & 0xFF;
		}
		else if (progress >= 0.25F) { // Middle stage
			float stageProgress = (progress - 0.25F) / 0.2F; // 0.0-1.0 for this stage

			int midR = (midColorInt >> 16) & 0xFF;
			int midG = (midColorInt >> 8) & 0xFF;
			int midB = midColorInt & 0xFF;

			int lateR = (lateColorInt >> 16) & 0xFF;
			int lateG = (lateColorInt >> 8) & 0xFF;
			int lateB = lateColorInt & 0xFF;

			r = midR + (int)((lateR - midR) * stageProgress);
			g = midG + (int)((lateG - midG) * stageProgress);
			b = midB + (int)((lateB - midB) * stageProgress);
		}
		else { // Early stage
			float stageProgress = progress / 0.25F; // 0.0-1.0 for this stage

			int earlyR = (earlyColorInt >> 16) & 0xFF;
			int earlyG = (earlyColorInt >> 8) & 0xFF;
			int earlyB = earlyColorInt & 0xFF;

			int midR = (midColorInt >> 16) & 0xFF;
			int midG = (midColorInt >> 8) & 0xFF;
			int midB = midColorInt & 0xFF;

			r = earlyR + (int)((midR - earlyR) * stageProgress);
			g = earlyG + (int)((midG - earlyG) * stageProgress);
			b = earlyB + (int)((midB - earlyB) * stageProgress);
		}
		return rgbToInt(r, g, b);
	}
}

