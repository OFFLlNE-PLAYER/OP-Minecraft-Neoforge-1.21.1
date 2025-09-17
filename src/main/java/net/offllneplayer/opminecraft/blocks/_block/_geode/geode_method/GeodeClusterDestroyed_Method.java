package net.offllneplayer.opminecraft.blocks._block._geode.geode_method;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.Map;

public class GeodeClusterDestroyed_Method {
	private static boolean isBudOrCluster(BlockState state, GeodeMaterial material) {
		return state.is(material.smallBud())
			|| state.is(material.mediumBud())
			|| state.is(material.largeBud())
			|| state.is(material.cluster());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, GeodeMaterial material) {
		BlockState Block_At_x_yplus1_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_yminus1_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_y_zminus1 = Blocks.AIR.defaultBlockState();
		BlockState Block_At_xminus1_y_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_xplus1_y_z = Blocks.AIR.defaultBlockState();
		BlockState Block_At_x_y_zplus1 = Blocks.AIR.defaultBlockState();
		Block_At_xplus1_y_z = (world.getBlockState(BlockPos.containing(x + 1, y, z)));
		Block_At_xminus1_y_z = (world.getBlockState(BlockPos.containing(x - 1, y, z)));
		Block_At_x_yplus1_z = (world.getBlockState(BlockPos.containing(x, y + 1, z)));
		Block_At_x_yminus1_z = (world.getBlockState(BlockPos.containing(x, y - 1, z)));
		Block_At_x_y_zplus1 = (world.getBlockState(BlockPos.containing(x, y, z + 1)));
		Block_At_x_y_zminus1 = (world.getBlockState(BlockPos.containing(x, y, z - 1)));
		if (isBudOrCluster(Block_At_xplus1_y_z, material)) {
			if (Block_At_xplus1_y_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp7 && Block_At_xplus1_y_z.getValue(_getbp7)) {
				{
					BlockPos _bp = BlockPos.containing(x + 1, y, z);
					BlockState _bs = Blocks.WATER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x + 1, y, z);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
			world.levelEvent(2001, BlockPos.containing(x + 1, y, z), Block.getId(Block_At_xplus1_y_z));
		}
		if (isBudOrCluster(Block_At_xminus1_y_z, material)) {
			if (Block_At_xminus1_y_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp12 && Block_At_xminus1_y_z.getValue(_getbp12)) {
				{
					BlockPos _bp = BlockPos.containing(x - 1, y, z);
					BlockState _bs = Blocks.WATER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x - 1, y, z);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
			world.levelEvent(2001, BlockPos.containing(x - 1, y, z), Block.getId(Block_At_xminus1_y_z));
		}
		if (isBudOrCluster(Block_At_x_yplus1_z, material)) {
			if (Block_At_x_yplus1_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp17 && Block_At_x_yplus1_z.getValue(_getbp17)) {
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					BlockState _bs = Blocks.WATER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
			world.levelEvent(2001, BlockPos.containing(x, y + 1, z), Block.getId(Block_At_x_yplus1_z));
		}
		if (isBudOrCluster(Block_At_x_yminus1_z, material)) {
			if (Block_At_x_yminus1_z.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp22 && Block_At_x_yminus1_z.getValue(_getbp22)) {
				{
					BlockPos _bp = BlockPos.containing(x, y - 1, z);
					BlockState _bs = Blocks.WATER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y - 1, z);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
			world.levelEvent(2001, BlockPos.containing(x, y - 1, z), Block.getId(Block_At_x_yminus1_z));
		}
		if (isBudOrCluster(Block_At_x_y_zplus1, material)) {
			if (Block_At_x_y_zplus1.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp27 && Block_At_x_y_zplus1.getValue(_getbp27)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z + 1);
					BlockState _bs = Blocks.WATER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z + 1);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
			world.levelEvent(2001, BlockPos.containing(x, y, z + 1), Block.getId(Block_At_x_y_zplus1));
		}
		if (isBudOrCluster(Block_At_x_y_zminus1, material)) {
			if (Block_At_x_y_zminus1.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp32 && Block_At_x_y_zminus1.getValue(_getbp32)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z - 1);
					BlockState _bs = Blocks.WATER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z - 1);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
			world.levelEvent(2001, BlockPos.containing(x, y, z - 1), Block.getId(Block_At_x_y_zminus1));
		}
	}
}
