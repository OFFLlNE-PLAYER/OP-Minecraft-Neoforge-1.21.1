
package net.offllneplayer.opminecraft.block.crying;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.PathType;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryFluids;
import net.offllneplayer.opminecraft.method.crying.essence.CryingEssence_OnTick_Method;
import net.offllneplayer.opminecraft.method.crying.essence.CryingEssenceCollision_Method;


public class CryingEssenceBlock extends LiquidBlock {

	public CryingEssenceBlock() {
		super(RegistryFluids.CRYING_ESSENCE.get(), Properties.of()
				.mapColor(MapColor.COLOR_PURPLE)
				.strength(1200f).hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 10)
				.noCollission()
				.noLootTable()
				.liquid()
				.pushReaction(PushReaction.DESTROY)
				.sound(SoundType.EMPTY).replaceable());
	}

	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return PathType.DANGER_OTHER;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		CryingEssence_OnTick_Method.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 20);
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		CryingEssenceCollision_Method.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}
