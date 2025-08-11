package net.offllneplayer.opminecraft.init.fml.dispenser;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.offllneplayer.opminecraft.items._iwe.hatchet.HatchetItem;
import net.offllneplayer.opminecraft.items._iwe.hatchet.ThrownHatchet;

/**
 * Dispenser dispenser for Crying Hatchet and any future hatchet items.
 * Keeps per-item projectile construction in the item via asProjectile.
 */
public class HatchetDispenseBehavior extends DefaultDispenseItemBehavior {
    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Level level = source.level();
        Position position = DispenserBlock.getDispensePosition(source);
        Direction direction = source.state().getValue(DispenserBlock.FACING);

        HatchetItem item = (HatchetItem) stack.getItem();
        ThrownHatchet projectile = (ThrownHatchet) item.asProjectile(level, position, stack, direction);

        level.addFreshEntity(projectile);
        stack.shrink(1);
        level.playSound(null, source.pos().getX(), source.pos().getY(), source.pos().getZ(),
                SoundEvents.DISPENSER_LAUNCH, SoundSource.BLOCKS, 1.0F, 1.0F);
        return stack;
    }
}
