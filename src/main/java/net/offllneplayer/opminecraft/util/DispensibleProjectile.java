package net.offllneplayer.opminecraft.util;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface DispensibleProjectile {
    Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction);
    default float getDispenseSpeed() {
        return 1.5F;
    }
}

