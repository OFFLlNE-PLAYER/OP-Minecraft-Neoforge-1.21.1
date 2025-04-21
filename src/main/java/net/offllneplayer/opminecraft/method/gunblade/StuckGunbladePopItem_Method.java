
package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

import net.offllneplayer.opminecraft.init.RegistryEnchantments;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

import static net.minecraft.tags.BlockTags.REPLACEABLE;


public class StuckGunbladePopItem_Method {
	private static int getValue(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity be = world.getBlockEntity(pos);
		return be != null ? be.getPersistentData().getInt(tag) : -1;
	}

	private static Direction getDirection(BlockState _bs) {
		Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
		if (_prop instanceof DirectionProperty _dp) {
			return _bs.getValue(_dp);
		}
		_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
		if (_prop instanceof EnumProperty<?> _ep
				&& _ep.getPossibleValues().toArray()[0] instanceof Direction.Axis) {
			return Direction.fromAxisAndDirection(
					(Direction.Axis) _bs.getValue((EnumProperty<Direction.Axis>) _ep),
					Direction.AxisDirection.POSITIVE
			);
		}
		return Direction.NORTH;
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean replaceable_block = false;

		BlockPos pos = BlockPos.containing(x, y, z);
		Direction direction_of_sword = getDirection(world.getBlockState(pos));

		if (direction_of_sword == Direction.WEST
				&& world.getBlockState(BlockPos.containing(x + 1, y, z)).is(REPLACEABLE)) {
			replaceable_block = true;
		} else if (direction_of_sword == Direction.EAST
				&& world.getBlockState(BlockPos.containing(x - 1, y, z)).is(REPLACEABLE)) {
			replaceable_block = true;
		} else if (direction_of_sword == Direction.DOWN
				&& world.getBlockState(BlockPos.containing(x, y + 1, z)).is(REPLACEABLE)) {
			replaceable_block = true;
		} else if (direction_of_sword == Direction.UP
				&& world.getBlockState(BlockPos.containing(x, y - 1, z)).is(REPLACEABLE)) {
			replaceable_block = true;
		} else if (direction_of_sword == Direction.NORTH
				&& world.getBlockState(BlockPos.containing(x, y, z + 1)).is(REPLACEABLE)) {
			replaceable_block = true;
		} else if (direction_of_sword == Direction.SOUTH
				&& world.getBlockState(BlockPos.containing(x, y, z - 1)).is(REPLACEABLE)) {
			replaceable_block = true;
		}

		if (replaceable_block && world instanceof Level _level && !_level.isClientSide()) {
			int damage_number          = getValue(world, pos, "DMG_VALU");
			int level_of_bane          = getValue(world, pos, "bane");
			int level_of_smite         = getValue(world, pos, "smiite");
			int level_of_sharp         = getValue(world, pos, "sharp");
			int level_of_unbreakin     = getValue(world, pos, "unbreakin");
			int level_of_lootin        = getValue(world, pos, "lootin");
			int level_of_sweepin       = getValue(world, pos, "sweepin");
			int level_of_firey         = getValue(world, pos, "firey");
			int level_of_knickerbocker = getValue(world, pos, "knickerbocker");
			int level_of_mendi         = getValue(world, pos, "mendi");
			int level_of_vanish        = getValue(world, pos, "vanish");
			int tempest                = getValue(world, pos, "tempest");

			_level.removeBlock(pos, false);

			ItemStack stack = new ItemStack(RegistryIBBI.GUNBLADE.get());
			stack.setDamageValue(damage_number);

			var ench = _level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

			if (level_of_bane > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.BANE_OF_ARTHROPODS), level_of_bane);
			} else if (level_of_smite > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.SMITE), level_of_smite);
			} else if (level_of_sharp > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.SHARPNESS), level_of_sharp);
			}

			if (level_of_unbreakin > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.UNBREAKING), level_of_unbreakin);
			}
			if (level_of_lootin > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.LOOTING), level_of_lootin);
			}
			if (level_of_sweepin > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.SWEEPING_EDGE), level_of_sweepin);
			}
			if (level_of_firey > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.FIRE_ASPECT), level_of_firey);
			}
			if (level_of_knickerbocker > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.KNOCKBACK), level_of_knickerbocker);
			}
			if (level_of_mendi > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.MENDING), 1);
			}
			if (level_of_vanish > 0) {
				stack.enchant(ench.getOrThrow(Enchantments.VANISHING_CURSE), 1);
			}
			if (tempest > 0) {
				stack.enchant(ench.getOrThrow(RegistryEnchantments.TEMPEST), tempest);
			}
			ItemEntity drop = new ItemEntity(_level, x + 0.5, y + 0.5, z + 0.5, stack);
			_level.addFreshEntity(drop);
			_level.playSound(null, pos, RegistrySounds.GUNBLADE_IN_DIRT.get(), SoundSource.MASTER, 0.8F, Mth.nextFloat(RandomSource.create(), 0.5420F, 0.7420F));
		}
	}
}
