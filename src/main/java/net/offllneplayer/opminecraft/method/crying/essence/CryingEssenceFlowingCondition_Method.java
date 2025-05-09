package net.offllneplayer.opminecraft.method.crying.essence;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.offllneplayer.opminecraft.util.TagKeyUtil;

public class CryingEssenceFlowingCondition_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean flow_logic = false;
		if (!(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(TagKeyUtil.Blocks.LAVAS)
				|| !(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(TagKeyUtil.Blocks.WATERS))  {
			flow_logic = true;
		}
		return flow_logic;
	}
}