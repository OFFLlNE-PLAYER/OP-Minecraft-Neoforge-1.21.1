package net.offllneplayer.opminecraft.block.crash.crates.crashtnt;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class CrashTNTPrime_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.PRIME_TNT.get(), SoundSource.MASTER, 0.8F, 1);
		}
		{
			int _value = 4;
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("tntstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
				world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
		}
	}
}
