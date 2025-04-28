package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;
import net.offllneplayer.opminecraft.util.PutNBT;

public class Stuck_Gunblade_OnClick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (!(entity instanceof LivingEntity living)) return;
		if (!(world instanceof Level level) || level.isClientSide()) return;

		BlockPos pos = BlockPos.containing(x, y, z);
		BlockState before = level.getBlockState(pos);
		BlockEntity gun = level.getBlockEntity(pos);
		if (gun == null) return;

		CompoundTag nbt = gun.getPersistentData();

		ItemStack blade = new ItemStack(RegistryIBBI.GUNBLADE.get());
		InteractionHand hand = living.getMainHandItem().isEmpty() ? InteractionHand.MAIN_HAND
				: living.getOffhandItem().isEmpty() ? InteractionHand.OFF_HAND
				: null;
		if (hand == null) return;

		PutNBT.writeWeaponDataToItemstack(blade, nbt, level);

		living.setItemInHand(hand, blade);
		if (living instanceof Player player) player.getInventory().setChanged();

		if (before.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty wp
				&& before.getValue(wp))
			level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
		else
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

		level.playSound(null, pos, RegistrySounds.GUNBLADE_SLASH.get(), SoundSource.MASTER, 0.42F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.1420F));
	}
}
