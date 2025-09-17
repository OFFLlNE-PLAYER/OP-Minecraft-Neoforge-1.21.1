package net.offllneplayer.opminecraft.blocks._block._geode.geode_method;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BuddingGeodeBlock_Method {
	public static void execute(LevelAccessor world, BlockPos origin, RandomSource random, GeodeMaterial material) {
		// Vanilla-like behavior:
		// - 20% chance to attempt growth each random tick
		// - If attempting, operate on ONE random side only
		if (random.nextInt(5) != 0) {
			return;
		}

		Direction dir = Direction.getRandom(random);
		BlockPos npos = origin.relative(dir);
		BlockState target = world.getBlockState(npos);

		// Cache fluid/waterlogged info BEFORE any placement to preserve refined waterlogging behavior
		boolean wasSourceWater = world.getFluidState(npos).isSource();
		boolean wasWaterlogged = false;
		Property<?> targetWaterProp = target.getBlock().getStateDefinition().getProperty("waterlogged");
		if (targetWaterProp instanceof BooleanProperty bp) {
			wasWaterlogged = target.getValue(bp);
		}

		// Spawn: into air or replaceable blocks only
		if (target.isAir() || target.canBeReplaced()) {
			BlockState bud = material.smallBud().defaultBlockState();
			Property<?> fprop = bud.getBlock().getStateDefinition().getProperty("facing");
			if (fprop instanceof DirectionProperty dp && dp.getPossibleValues().contains(dir)) {
				bud = bud.setValue(dp, dir);
			}
			Property<?> wlProp = bud.getBlock().getStateDefinition().getProperty("waterlogged");
			if ((wasSourceWater || wasWaterlogged) && wlProp instanceof BooleanProperty wlb) {
				bud = bud.setValue(wlb, true);
			}
			world.setBlock(npos, bud, 3);
			return;
		}

		// Upgrade: only if the existing bud faces this direction and is attached to THIS budding block
		Property<?> facingProp = target.getBlock().getStateDefinition().getProperty("facing");
		if (!(facingProp instanceof DirectionProperty fdp) || target.getValue(fdp) != dir) {
			return;
		}
		// Ensure attachment to this origin
		if (!npos.relative(dir.getOpposite()).equals(origin)) {
			return;
		}

		BlockState next = null;
		if (target.is(material.smallBud())) {
			next = material.mediumBud().defaultBlockState();
		} else if (target.is(material.mediumBud())) {
			next = material.largeBud().defaultBlockState();
		} else if (target.is(material.largeBud())) {
			next = material.cluster().defaultBlockState();
		} else {
			return; // Cluster or other blocks: do nothing
		}

		// Preserve facing
		Property<?> nfp = next.getBlock().getStateDefinition().getProperty("facing");
		if (nfp instanceof DirectionProperty ndp && ndp.getPossibleValues().contains(dir)) {
			next = next.setValue(ndp, dir);
		}
		// Preserve waterlogged from the old bud state
		boolean wasOldWaterlogged = false;
		Property<?> oldW = target.getBlock().getStateDefinition().getProperty("waterlogged");
		if (oldW instanceof BooleanProperty ob) {
			wasOldWaterlogged = target.getValue(ob);
		}
		Property<?> newW = next.getBlock().getStateDefinition().getProperty("waterlogged");
		if (wasOldWaterlogged && newW instanceof BooleanProperty nb) {
			next = next.setValue(nb, true);
		}

		world.setBlock(npos, next, 3);
	}
}