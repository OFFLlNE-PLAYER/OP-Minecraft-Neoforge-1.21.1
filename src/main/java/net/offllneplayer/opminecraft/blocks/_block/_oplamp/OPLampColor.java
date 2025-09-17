package net.offllneplayer.opminecraft.blocks._block._oplamp;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;

public enum OPLampColor implements StringRepresentable {
	BLACK("black"),
	BLUE("blue"),
	BROWN("brown"),
	CYAN("cyan"),
	GOLDEN("golden"), // custom extra color beyond vanilla dyes
	GRAY("gray"),
	GREEN("green"),
	LIGHT_BLUE("light_blue"),
	LIGHT_GRAY("light_gray"),
	LIME("lime"),
	MAGENTA("magenta"),
	ORANGE("orange"),
	PINK("pink"),
	PURPLE("purple"),
	RED("red"),
	WHITE("white"),
	YELLOW("yellow");

	private final String name;

	OPLampColor(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public String getName() {
        return name;
    }

	public static OPLampColor dyeToOPLampColor(DyeColor dyeColor) {
		if (dyeColor == null) return null;
		return switch (dyeColor) {
			case BLACK -> OPLampColor.BLACK;
			case BLUE -> OPLampColor.BLUE;
			case BROWN -> OPLampColor.BROWN;
			case CYAN -> OPLampColor.CYAN;
			case GRAY -> OPLampColor.GRAY;
			case GREEN -> OPLampColor.GREEN;
			case LIGHT_BLUE -> OPLampColor.LIGHT_BLUE;
			case LIGHT_GRAY -> OPLampColor.LIGHT_GRAY;
			case LIME -> OPLampColor.LIME;
			case MAGENTA -> OPLampColor.MAGENTA;
			case ORANGE -> OPLampColor.ORANGE;
			case PINK -> OPLampColor.PINK;
			case PURPLE -> OPLampColor.PURPLE;
			case RED -> OPLampColor.RED;
			case WHITE -> OPLampColor.WHITE;
			case YELLOW -> OPLampColor.YELLOW;
		};
	}
}
