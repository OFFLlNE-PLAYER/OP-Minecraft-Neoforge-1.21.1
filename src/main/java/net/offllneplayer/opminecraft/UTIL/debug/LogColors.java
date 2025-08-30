package net.offllneplayer.opminecraft.UTIL.debug;

/**
 * Simple ANSI color helper for debug logging. Use these constants to avoid
 * sprinkling raw escape sequences across the codebase.
 *
 * Notes:
 * - Works in most consoles (IntelliJ Run, Gradle console). If your target
 *   console strips ANSI, logs will just show plain text.
 * - Compose styles by concatenation: BOLD + BLUE + "text" + RESET.
 * - Prefer BRIGHT_* for higher contrast when needed.
 */
public final class LogColors {

    // Reset / styles
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    // Standard 8 colors
    public static final String BLACK   = "\u001B[30m";
    public static final String RED     = "\u001B[31m";
    public static final String GREEN   = "\u001B[32m";
    public static final String YELLOW  = "\u001B[33m";
    public static final String BLUE    = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN    = "\u001B[36m";
    public static final String WHITE   = "\u001B[37m";

    // Bright variants (high-intensity)
    public static final String BRIGHT_BLACK   = "\u001B[90m";
    public static final String BRIGHT_RED     = "\u001B[91m";
    public static final String BRIGHT_GREEN   = "\u001B[92m";
    public static final String BRIGHT_YELLOW  = "\u001B[93m";
    public static final String BRIGHT_BLUE    = "\u001B[94m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m";
    public static final String BRIGHT_CYAN    = "\u001B[96m";
    public static final String BRIGHT_WHITE   = "\u001B[97m";

    // Convenience helpers
    public static String color(String msg, String... codes) {
        if (msg == null) return "";
        if (codes == null || codes.length == 0) return msg;
        StringBuilder sb = new StringBuilder();
        for (String c : codes) { if (c != null) sb.append(c); }
        sb.append(msg).append(RESET);
        return sb.toString();
    }

	 public static void debugRedBold(String msg) {
		System.out.println(LogColors.BOLD + LogColors.RED + msg + LogColors.RESET);
	 }
	 public static void debugRed(String msg) {
		System.out.println(LogColors.BRIGHT_RED + msg + LogColors.RESET);
	 }
	public static void debugBlueBold(String msg) {
		System.out.println(LogColors.BOLD + LogColors.BLUE + msg + LogColors.RESET);
	}
	public static void debugBlue(String msg) {
		System.out.println(LogColors.BRIGHT_BLUE + msg + LogColors.RESET);
	}
}
