package net.offllneplayer.opminecraft.blocks._block._oplamp;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum OPLampMaterial implements StringRepresentable {
    GOLDEN("golden"),
    IRON("iron"),
    DIAMOND("diamond"),
    NETHERITE("netherite"),
    ONYX("onyx"),
    TITAN("titan");

    private final String name;

    OPLampMaterial(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    public String getName() {
        return name;
    }
}
