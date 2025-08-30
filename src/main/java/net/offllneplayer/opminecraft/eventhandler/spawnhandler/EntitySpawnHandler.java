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
import net.offllneplayer.opminecraft.entity.goal.jump.GOAL_useJump;

@EventBusSubscriber
public class EntitySpawnHandler {
	private static final String SPAWN_HANDLED_TAG = "spawnHandled";


	@SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		Level level = event.getLevel();

		// Server only
		if (level.isClientSide()) {
			return;
		}

		// Exit if not a mob immediately
		if (!(entity instanceof Mob mob)) {
			return;
		}

		// --- Check persistant data; exit if mob ran this script allready (for chunk reloading) ---
		if (mob.getPersistentData().getBoolean(SPAWN_HANDLED_TAG)) {
			return;
		}

		// ~---= [ PICKUP LOOT ] =---~
		if (mob instanceof Villager) {
			mob.setCanPickUpLoot(true);
			return;
		}

		// mob filter for jump goal and random equipment
		if (!(mob instanceof Zombie || mob instanceof ZombieVillager || mob instanceof Husk || mob instanceof Drowned ||
				mob instanceof Skeleton || mob instanceof WitherSkeleton || mob instanceof Stray || mob instanceof Bogged ||
				mob instanceof Piglin || mob instanceof PiglinBrute || mob instanceof ZombifiedPiglin ||
				mob instanceof Pillager || mob instanceof Vindicator)) {
			return;
		}

		mob.setCanPickUpLoot(true);
		mob.goalSelector.addGoal(3, new GOAL_useJump(mob));

		// ~---= [ WEAPON ] =---~
		SpawnEquipWeapons.equipPrimaryWeapon(mob, level);

		// ~---= [ OFF-HAND ] =---~
		SpawnEquipWeapons.maybeEquipOffhand(mob);

		// ~---= [ ARMOR EQUIP ] =---~
		SpawnEquipArmor.equipRandomArmor(mob, level);

		// --- Put persistant data that entity ran this package allready ---
		mob.getPersistentData().putBoolean(SPAWN_HANDLED_TAG, true);
	}
}
