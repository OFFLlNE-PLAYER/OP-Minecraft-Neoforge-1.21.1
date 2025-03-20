package net.offllneplayer.opminecraft.handler;


import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.method.crash.akuaku.AkuAkuActivate_Method;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.DeclareTagKeys;

public class DamageEventHandler {
// Listen for incoming damage events
    @SubscribeEvent
    public void onEntityDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        Level world = entity.level();  // Use level() to access the world
        boolean cancelaku = false;

        //If about to take fall dmg, check block for a crates and negate accordingly
        if (event.getSource().is(DamageTypes.FALL)) {
            BlockState belowBlock = world.getBlockState(entity.blockPosition().below());
            if (belowBlock.is(DeclareTagKeys.Blocks.CRASH_CRATES)) {
                event.setCanceled(true);
                cancelaku = true;
            }
        }

        if (!cancelaku) {
            // Check if the Aku Aku Mask is in either hand
            if ((entity.getMainHandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get()) || (entity.getOffhandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get())) {
                // Cancel the damage event so no damage occurs
                event.setCanceled(true);
                // Execute the Aku Aku Mask procedure
                AkuAkuActivate_Method.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
            }
        }
    }
}