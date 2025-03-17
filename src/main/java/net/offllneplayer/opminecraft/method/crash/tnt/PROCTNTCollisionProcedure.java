package net.offllneplayer.opminecraft.method.crash.tnt;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
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

public class PROCTNTCollisionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ArmorStand) && !(entity instanceof Arrow) && !(entity instanceof EvokerFangs) && !(entity instanceof ThrownExperienceBottle) && !(entity instanceof ExperienceOrb) && !(entity instanceof GlowItemFrame)
				&& !(entity instanceof HangingEntity) && !(entity instanceof ItemEntity) && !(entity instanceof Display.ItemDisplay) && !(entity instanceof ItemFrame) && !(entity instanceof Marker) && !(entity instanceof ThrownPotion)
				&& !(entity instanceof SpectralArrow)) {
			entity.fallDistance = 0;
			entity.push(0, 0.8, 0);
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("tntstate") instanceof IntegerProperty _getip16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip16) : -1) == 0) {
				PROCPrimeTNTProcedure.execute(world, x, y, z);
			} else {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("opminecraft:crash_crate_bounce")), SoundSource.MASTER, 1, 1);
					}
				}
			}
		}
	}
}
