package net.offllneplayer.opminecraft.eventhandler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonActivate_Method;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;

/**
 * Triggers balloon activation for airborne mobs that are holding a balloon.
 *
 * Separation of concerns:
 *  - Check A: entity is a Mob and currently not on the ground.
 *  - Check B: entity is holding a Balloon in either hand.
 * If both are true, we call BalloonActivate_Method.execute(entity).
 */
@EventBusSubscriber(modid = OPMinecraft.Mod_ID, bus = EventBusSubscriber.Bus.GAME)
public class FallingEventHandler {
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        Entity e = event.getEntity();
        if (!(e instanceof LivingEntity living)) return;

		  //player && mob logic could be here

        if ((living instanceof Player)) return; // mobs; players are handled by BalloonItem logic
        if (living.level().isClientSide()) return; // server-side only
        if (living.onGround()) return; // Check airborne state

        // Check holding a balloon in either hand
       if ((living.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BalloonItem)
                || (living.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BalloonItem)) {
			 BalloonActivate_Method.execute(living);
		 }
    }
}
