package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.NBTUtil;

public class StickGunblade_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (!(entity instanceof LivingEntity living)) return;
		if (!(world instanceof Level level) || level.isClientSide()) return;

		var eye = living.getEyePosition(1f);
		var look = living.getViewVector(1f);
		var hit = world.clip(new ClipContext(eye, eye.add(look.scale(4)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, living));
		Direction face = hit.getDirection();

		BlockPos origin = BlockPos.containing(x, y, z);
		BlockPos first  = origin.relative(face);
		BlockPos second = origin.relative(face, 2);
		if (!world.getBlockState(first).is(BlockTags.REPLACEABLE) || !world.getBlockState(second).is(BlockTags.REPLACEABLE)) return;

		ItemStack stack = living.getMainHandItem().getItem() == RegistryIBBI.GUNBLADE.get() ? living.getMainHandItem()
				: living.getOffhandItem().getItem() == RegistryIBBI.GUNBLADE.get() ? living.getOffhandItem() : ItemStack.EMPTY;
		if (stack.isEmpty()) return;

		BlockState stuck = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
		if (stuck.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty dp) {
			stuck = stuck.setValue(dp, face);
		}

		BlockState orig = level.getBlockState(first);
		if ((orig.getBlock() == Blocks.WATER) || (orig.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty bp && orig.getValue(bp))) {
			if (stuck.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty wb) {
				stuck = stuck.setValue(wb, true);
			}
		}
		level.setBlock(first, stuck, 3);

		BlockEntity gun = level.getBlockEntity(first);
		if (gun != null) {

			NBTUtil.writeItemStackNBTToBlock(stack, gun);

			gun.setChanged();
			level.sendBlockUpdated(first, stuck, stuck, 3);
		}

		level.playSound(null, first, RegistrySounds.GUNBLADE_IN_DIRT.get(), SoundSource.BLOCKS, 1f, Mth.nextFloat(RandomSource.create(), 0.9f, 1.1f));
		stack.shrink(1);
	}
}
