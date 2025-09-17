package net.offllneplayer.opminecraft.blocks._block.crying;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import net.offllneplayer.opminecraft.init.RegistrySounds;

public class CryingButtons_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {

				// Check if block has redstone power before playing sound
				if (!(_level.getSignal(BlockPos.containing(x, y, z), null) == 0)) {
					_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.CRYING_EXPLODE.get(), SoundSource.MASTER, 0.8F, 1F);
				}
			}
		}
	}
}
