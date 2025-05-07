package net.offllneplayer.opminecraft.init.fml;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.util.DispensibleProjectile;


public class FMLDispenserBehaviors {

    public static void DispenserBehaviors() {
        registerDispensibleProjectile(RegistryIBBI.CRYING_HATCHET.get());
        registerDispensibleProjectile(RegistryIBBI.SMB_SUPER_FAN.get());
        }

    private static void registerDispensibleProjectile(Item item) {
        if (item instanceof DispensibleProjectile dispensible) {
            DispenserBlock.registerBehavior(item, new DefaultDispenseItemBehavior() {
                @Override
                protected ItemStack execute(BlockSource source, ItemStack stack) {
                    Level level = source.level();
                    Position position = DispenserBlock.getDispensePosition(source);
                    Direction direction = source.state().getValue(DispenserBlock.FACING);
                    Projectile projectile = dispensible.asProjectile(level, position, stack, direction);

                    level.addFreshEntity(projectile);
                    stack.shrink(1);
                    level.playSound(null, source.pos().getX(), source.pos().getY(), source.pos().getZ(), SoundEvents.DISPENSER_LAUNCH, SoundSource.BLOCKS, 1.0F, 1.0F);

                    return stack;
                }
            });
        }
    }
}

