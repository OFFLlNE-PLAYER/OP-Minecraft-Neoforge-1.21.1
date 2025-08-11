package net.offllneplayer.opminecraft.blocks._block.crash.crates.crashtnt;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistrySounds;

public class CrashTNTBoom_Method {
	public static void execute(LevelAccessor world, double x, double y, double z) {

		double x_power = Mth.nextInt(RandomSource.create(), 2, 5);
		double y_power = Mth.nextInt(RandomSource.create(), 2, 5);
		double z_power = Mth.nextInt(RandomSource.create(), 2, 5);
		double new_x_power = 0;
		double new_y_power = 0;
		double new_z_power = 0;

		if ((world instanceof Level _level) && (!_level.isClientSide())) {
			_level.playSound(null, BlockPos.containing(x, y, z), RegistrySounds.TNT_BOOM.get(), SoundSource.MASTER, 1F, 1F);
			_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.TNT);
		}
		while (x_power > 0 && y_power > 0 && z_power > 0) {
			if (!(world.getBlockState(BlockPos.containing(x + x_power, y, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x + x_power, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x - x_power, y, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x - x_power, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y + y_power, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y + y_power, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y - y_power, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y - y_power, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y, z + z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y, z + z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y, z - z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y, z - z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x + x_power, y + y_power, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x + x_power, y + y_power, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x + x_power, y - y_power, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x + x_power, y - y_power, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x - x_power, y + y_power, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x - x_power, y + y_power, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x - x_power, y - y_power, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x - x_power, y - y_power, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x + x_power, y, z + z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x + x_power, y, z + z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x + x_power, y, z - z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x + x_power, y, z - z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x - x_power, y, z + z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x - x_power, y, z + z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x - x_power, y, z - z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x - x_power, y, z - z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y + y_power, z + z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y + y_power, z + z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y + y_power, z - z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y + y_power, z - z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y - y_power, z + z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y - y_power, z + z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (!(world.getBlockState(BlockPos.containing(x, y - y_power, z - z_power))).is(BlockTags.create(ResourceLocation.parse("opminecraft:tnt_immune")))) {
				world.setBlock(BlockPos.containing(x, y - y_power, z - z_power), Blocks.AIR.defaultBlockState(), 3);
			}
			if (x_power >= y_power && x_power >= z_power) {
				new_x_power = x_power - 1;
			}
			if (y_power >= x_power && y_power >= z_power) {
				new_y_power = y_power - 1;
			}
			if (z_power >= x_power && z_power >= y_power) {
				new_z_power = z_power - 1;
			}
			x_power = new_x_power;
			y_power = new_y_power;
			z_power = new_z_power;
		}
	}
}
