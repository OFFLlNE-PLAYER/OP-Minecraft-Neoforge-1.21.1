package net.offllneplayer.opminecraft.blocks._block.crying.essence.method.effect;

public class CryingActiveTickCondition_Method {
	public static boolean execute(double amplifier, double duration) {
		double baseRate = 20;
		double rateWithAmplifier = baseRate / Math.pow(2, amplifier);
		if (Math.floor(rateWithAmplifier) > 0) {
			return duration % Math.floor(rateWithAmplifier) == 0;
		}
		return true;
	}
}
