
package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.method.UTIL.OP_NBTUtil;


public class StuckGunbladePopItem_Method {

	private static Direction getDirection(BlockState bs) {
		Property<?> prop = bs.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp) return bs.getValue(dp);
		prop = bs.getBlock().getStateDefinition().getProperty("axis");
		if (prop instanceof EnumProperty<?> ep && ep.getPossibleValues().iterator().next() instanceof Direction.Axis axis)
			return Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!(world instanceof Level level) || level.isClientSide()) return;
		BlockPos pos = BlockPos.containing(x, y, z);
		Direction face = getDirection(level.getBlockState(pos));
		BlockPos dropPos = pos.relative(face.getOpposite());
		if (!level.getBlockState(dropPos).is(BlockTags.REPLACEABLE)) return;
		BlockEntity gun = level.getBlockEntity(pos);
		if (gun == null) return;

		CompoundTag nbt = gun.getPersistentData();

		ItemStack blade = new ItemStack(RegistryBIBI.TITAN_GUNBLADE.get());

		level.removeBlock(pos, false);

		OP_NBTUtil.enchantWeaponDataToItemstack(blade, nbt, level);

		ItemEntity drop = new ItemEntity(level, dropPos.getX() + 0.5, dropPos.getY() + 0.5, dropPos.getZ() + 0.5, blade);
		level.addFreshEntity(drop);

		level.playSound(null, pos, RegistrySounds.BLADE_SLASH.get(), SoundSource.MASTER, 0.8F, Mth.nextFloat(RandomSource.create(), 0.5420F, 0.7420F));
	}
}
