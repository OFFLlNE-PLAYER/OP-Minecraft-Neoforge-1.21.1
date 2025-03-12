package net.offllneplayer.opminecraft.method.crying.essence;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class CryingEssenceFlowingCondition_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean its_water_logic = false;
		boolean flow_logic = false;
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:lavas")))
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(ResourceLocation.parse("opminecraft:waters")))) {
			flow_logic = false;
		} else {
			flow_logic = true;
		}
		return flow_logic;
	}
}
