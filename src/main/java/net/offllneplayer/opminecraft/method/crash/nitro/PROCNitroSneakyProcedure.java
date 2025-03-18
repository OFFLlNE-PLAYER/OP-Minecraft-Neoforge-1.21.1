package net.offllneplayer.opminecraft.method.crash.nitro;

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

public class PROCNitroSneakyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ArmorStand) && !(entity instanceof Arrow) && !(entity instanceof EvokerFangs) && !(entity instanceof ThrownExperienceBottle) && !(entity instanceof ExperienceOrb) && !(entity instanceof GlowItemFrame)
				&& !(entity instanceof HangingEntity) && !(entity instanceof ItemEntity) && !(entity instanceof Display.ItemDisplay) && !(entity instanceof ItemFrame) && !(entity instanceof Marker) && !(entity instanceof ThrownPotion)
				&& !(entity instanceof SpectralArrow)) {
			if (!entity.isShiftKeyDown()) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:nitro_boom")), SoundSource.MASTER, (float) 0.6, 1);
					}
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, x, (y + 1), z, 2, true, Level.ExplosionInteraction.BLOCK);
			}
		}
	}
}
