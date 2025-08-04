package net.offllneplayer.opminecraft.item.balloon;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

/**
 * Enum representing all possible balloon colors
 */
public enum BalloonColor implements StringRepresentable {
    WHITE("white"),
    ORANGE("orange"),
    MAGENTA("magenta"),
    LIGHT_BLUE("light_blue"),
    YELLOW("yellow"),
    LIME("lime"),
    PINK("pink"),
    GRAY("gray"),
    LIGHT_GRAY("light_gray"),
    CYAN("cyan"),
    PURPLE("purple"),
    BLUE("blue"),
    BROWN("brown"),
    GREEN("green"),
    RED("red"),
    BLACK("black");

    private final String name;

    BalloonColor(String name) {
        this.name = name;
    }

    /**
     * Gets the texture path for this balloon color
     * @return The texture path
     */
    public String getTexturePath() {
        return "opminecraft:item/text_balloon_" + name;
    }

    /**
     * Gets the model path for this balloon color
     * @return The model path
     */
    public String getModelPath() {
        return "balloon_" + name;
    }

    /**
     * Gets the translation key for this balloon color
     * @return The translation key
     */
    public String getTranslationKey() {
        return "item.opminecraft.balloon_" + name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    /**
     * Gets the color name
     * @return The color name
     */
    public String getName() {
        return name;
    }
}