package net.offllneplayer.opminecraft.blocks._block.crash.crates.crate;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class CrashCrateBreak_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);
		world.destroyBlock(pos, false);
		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			_level.playSound(null, pos, RegistrySounds.WUMPA_FRUIT.get(), SoundSource.MASTER, 1, 1);
		}
			if (world instanceof ServerLevel _level) {
				ItemEntity fruitEntity = new ItemEntity(_level, (x + Math.random()), (y + Math.random()), (z + Math.random()), new ItemStack(RegistryBIBI.WUMPA_FRUIT.get()));
				fruitEntity.setPickUpDelay(0);
				_level.addFreshEntity(fruitEntity);
		}
	}
}
