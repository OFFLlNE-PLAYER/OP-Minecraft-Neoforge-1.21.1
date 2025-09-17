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
import net.offllneplayer.opminecraft.entity.goal.GOAL_usePistol;
import net.offllneplayer.opminecraft.entity.goal.jump.GOAL_useJump;
import net.offllneplayer.opminecraft.items._iwe.pistol.PistolItem;

@EventBusSubscriber
public class EntitySpawnHandler {
	private static final String SPAWN_HANDLED_TAG = "spawnHandled";


	@SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		Level level = event.getLevel();

		if (level.isClientSide()) return;

		if (!(entity instanceof Mob mob)) return;  // Exit if not a mob immediately

		// mob filter for jump goal and random equipment
		if (!(mob instanceof Zombie || mob instanceof ZombieVillager || mob instanceof Husk || mob instanceof Drowned ||
				mob instanceof Skeleton || mob instanceof WitherSkeleton || mob instanceof Stray || mob instanceof Bogged ||
				mob instanceof Piglin || mob instanceof PiglinBrute || mob instanceof ZombifiedPiglin ||
				mob instanceof Pillager || mob instanceof Vindicator ||
				mob instanceof Creeper || mob instanceof Villager)) {
			return;
		}

		if (mob instanceof Villager) {
			mob.setCanPickUpLoot(true);
			return; // Villager is done here

		} else {
		mob.goalSelector.addGoal(3, new GOAL_useJump(mob));
		}

		if (mob instanceof Creeper) return; // Creeper is done here

		mob.setCanPickUpLoot(true);

		// --- Check persistant data; if already handled, still ensure pistol goal is present when holding a pistol ---
		if (mob.getPersistentData().getBoolean(SPAWN_HANDLED_TAG)) {
			if (mob.getMainHandItem().getItem() instanceof PistolItem) {
					mob.goalSelector.addGoal(3, new GOAL_usePistol(mob, 1.0420D, 18F));
			}
			return;
		}


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
