package net.offllneplayer.opminecraft.eventhandler.spawnhandler;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber
public class EntitySpawnHandler {
	private static final String SPAWN_HANDLED_TAG = "spawnHandled";


	@SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		Level level = event.getLevel();

		// Exit if not a mob or already processed
		if (!(entity instanceof Mob mob)) {
			return;
		} else if (mob.getPersistentData().getBoolean(SPAWN_HANDLED_TAG)) {
			return;
		}

// ~---= [ PICKUP LOOT ] =---~
		if (mob instanceof Villager) {
			mob.setCanPickUpLoot(true);
			return;
		}

		if (!(mob instanceof Zombie || mob instanceof ZombieVillager || mob instanceof Husk || mob instanceof Drowned ||
				mob instanceof Skeleton || mob instanceof WitherSkeleton || mob instanceof Stray ||
				mob instanceof Piglin || mob instanceof PiglinBrute || mob instanceof ZombifiedPiglin ||
				mob instanceof Pillager || mob instanceof Vindicator)) {
			return;
		}

		mob.setCanPickUpLoot(true);


// ~---= [ WEAPON ] =---~
        SpawnEquipWeapons.equipPrimaryWeapon(mob, level);


// ~---= [ OFF-HAND ] =---~
        SpawnEquipWeapons.maybeEquipOffhand(mob);


// ~---= [ ARMOR EQUIP ] =---~
  SpawnEquipArmor.equipRandomArmor(mob, level);


// --- Put persistant data that entity ran this script allready ---
		mob.getPersistentData().putBoolean(SPAWN_HANDLED_TAG, true);

	}
}
