package net.offllneplayer.opminecraft.method.util;


/// *~* OP ARR COPYRIGHT DISCLAIMER *~*
/// --------------------------------------------------
/// ~ USE OF THIS CODE IS EXPLICITLY PROHIBITED WITHOUT DIRECT PERMISSION.
/// ~ COPY OR _ANY_USE_ OF THIS CODE GOES AGAINST COPYRIGHT.
/// ~ YOU SHOULD CLOSE THIS HAVING ACKNOWLEDGED THAT.
/// ~ SUPPORT/ENJOY MY MOD!
/// ---------------------------------------------------
/// *~* OP ARR COPYRIGHT DISCLAIMER *~*

public class OP_getBarColorUtil {

/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public static int rgbToInt(int r, int g, int b) {
		// Clamp values to valid range
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return (r << 16) | (g << 8) | b;
	}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public static int calculateBarColor(float progress,
		int[] earlyColor,
		int[] midColor,
		int[] lateColor,
		int[] finalBaseColor,
		int[] finalPulseColor,
		float pulseFrequency) {
		int r, g, b;

		if (progress >= 0.69F) { // Starting pulsing at 69%
			// Final flashing stage
			float flashPhase = (progress - 0.65F) * 2.85F; // Rescale to 0.0-1.0 range (1/0.35 ≈ 2.85)
			float pulseIntensity = (float) Math.sin(flashPhase * Math.PI * pulseFrequency) * 0.5F + 0.5F;

			r = finalBaseColor[0] + (int)(finalPulseColor[0] * pulseIntensity);
			g = finalBaseColor[1] + (int)(finalPulseColor[1] * pulseIntensity);
			b = finalBaseColor[2] + (int)(finalPulseColor[2] * pulseIntensity);
		}
		else if (progress >= 0.420F) { // Late stage
			r = lateColor[0];
			g = lateColor[1];
			b = lateColor[2];
		}
		else if (progress >= 0.25F) { // Middle stage
			float stageProgress = (progress - 0.25F) / 0.2F; // 0.0-1.0 for this stage
			r = midColor[0] + (int)((lateColor[0] - midColor[0]) * stageProgress);
			g = midColor[1] + (int)((lateColor[1] - midColor[1]) * stageProgress);
			b = midColor[2] + (int)((lateColor[2] - midColor[2]) * stageProgress);
		}
		else { // Early stage
			float stageProgress = progress / 0.25F; // 0.0-1.0 for this stage
			r = earlyColor[0] + (int)((midColor[0] - earlyColor[0]) * stageProgress);
			g = earlyColor[1] + (int)((midColor[1] - earlyColor[1]) * stageProgress);
			b = earlyColor[2] + (int)((midColor[2] - earlyColor[2]) * stageProgress);
		}

		return rgbToInt(r, g, b);
	}

}

