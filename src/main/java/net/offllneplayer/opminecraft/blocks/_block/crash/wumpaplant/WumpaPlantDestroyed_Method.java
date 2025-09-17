package net.offllneplayer.opminecraft.blocks._block.crash.wumpaplant;

import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

/**
 * Centralized behavior for Wumpa Plant being destroyed (non-silk scenarios and interactions).
 * Plays the crop-break and Wumpa sounds and spawns the two drops at the provided coordinates.
 *
 * Coordinate policy: use the exact x/y/z given by caller. Callers can pass centered values
 * (e.g., x+0.5, z+0.5) when desired. This keeps behavior consistent across different triggers.
 */
public class WumpaPlantDestroyed_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);
		if (world instanceof Level level && !level.isClientSide()) {
			// Play the generic crop break sound (as originally done on click) and the custom Wumpa sound.
			level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.MASTER, 1.0F, 1.1F);
			level.playSound(null, pos, RegistrySounds.WUMPA_FRUIT.get(), SoundSource.MASTER, 1, 1);
		}
		if (world instanceof ServerLevel serverLevel) {
			ItemEntity fruitEntity = new ItemEntity(serverLevel, x, y, z, new ItemStack(RegistryBIBI.WUMPA_FRUIT.get()));
			fruitEntity.setPickUpDelay(0);
			serverLevel.addFreshEntity(fruitEntity);

			ItemEntity podEntity = new ItemEntity(serverLevel, x, y, z, new ItemStack(Items.PITCHER_POD));
			podEntity.setPickUpDelay(0);
			serverLevel.addFreshEntity(podEntity);
		}
	}
}
