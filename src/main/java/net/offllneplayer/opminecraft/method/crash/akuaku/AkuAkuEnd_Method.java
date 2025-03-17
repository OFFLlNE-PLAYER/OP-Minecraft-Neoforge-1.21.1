package net.offllneplayer.opminecraft.method.crash.akuaku;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class AkuAkuEnd_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide() && _entity.isInvulnerable()){
			_entity.setInvulnerable(false);
	}
		if (world instanceof Level _level) {
			float tone = Mth.nextFloat(RandomSource.create(), 0.9f, 1.05f);
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:aku_aku_dies")), SoundSource.MASTER, (float) 0.6, tone);
			}
		}
	}
}
