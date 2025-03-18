package net.offllneplayer.opminecraft.method.crash.crate;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.OPMinecraft;

public class PROCCrashCrateCollisionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ArmorStand) && !(entity instanceof Arrow) && !(entity instanceof EvokerFangs) && !(entity instanceof ThrownExperienceBottle) && !(entity instanceof ExperienceOrb) && !(entity instanceof GlowItemFrame)
				&& !(entity instanceof HangingEntity) && !(entity instanceof ItemEntity) && !(entity instanceof Display.ItemDisplay) && !(entity instanceof ItemFrame) && !(entity instanceof Marker) && !(entity instanceof ThrownPotion)
				&& !(entity instanceof SpectralArrow)) {
			entity.fallDistance = 0;
			entity.push(0, 0.8, 0);
			if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_bounce")), SoundSource.MASTER, 1, 1);
			}
			world.destroyBlock(BlockPos.containing(x, y, z), false);
			OPMinecraft.queueServerWork(3, () -> {
				PROCCrashCrateBreakProcedure.execute(world, x, y, z);
			});
		}
	}
}
