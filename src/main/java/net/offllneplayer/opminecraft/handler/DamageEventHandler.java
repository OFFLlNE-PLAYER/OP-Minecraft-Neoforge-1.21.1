package net.offllneplayer.opminecraft.handler;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.method.crash.akuaku.PROCAkuAkuActivateProcedure;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

public class DamageEventHandler {
// Listen for incoming damage events
    @SubscribeEvent
    public void onEntityDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        Level world = entity.level();  // Use level() to access the world

    // Check if the Aku Aku Mask is in either the main hand or off hand
        if ((entity.getMainHandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get()) || (entity.getOffhandItem().getItem() == RegistryIBBI.AKU_AKU_MASK.get())) {
        // Cancel the damage event so no damage occurs
            event.setCanceled(true);
        // Execute the Aku Aku Mask procedure
            PROCAkuAkuActivateProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
        }
    }
}