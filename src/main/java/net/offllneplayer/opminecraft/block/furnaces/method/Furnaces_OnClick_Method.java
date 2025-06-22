package net.offllneplayer.opminecraft.block.furnaces.method;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import io.netty.buffer.Unpooled;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import net.offllneplayer.opminecraft.block.furnaces.OPFurnaceInv;
import net.offllneplayer.opminecraft.init.RegistryBIBI;


public class Furnaces_OnClick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState Block_At_XYZ = Blocks.AIR.defaultBlockState();
		Block_At_XYZ = (world.getBlockState(BlockPos.containing(x, y, z)));

		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.LAVA_BUCKET) {
			if (new Object() {
				public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
					if (level instanceof ILevelExtension _ext) {
						IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
						if (_fluidHandler != null)
							return _fluidHandler.getFluidInTank(tank).getAmount();
					}
					return 0;
				}
			}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) < 10000) {
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 1000), IFluidHandler.FluidAction.EXECUTE);
				}
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.NEUTRAL, 1, 1);
				}
			} else {
				openFurnaceGUI(world, x, y, z, entity, Block_At_XYZ);
			}
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.LAVA_BUCKET) {
			if (new Object() {
				public int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank) {
					if (level instanceof ILevelExtension _ext) {
						IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
						if (_fluidHandler != null)
							return _fluidHandler.getFluidInTank(tank).getAmount();
					}
					return 0;
				}
			}.getFluidTankLevel(world, BlockPos.containing(x, y, z), 1) < 10000) {
				if (world instanceof ILevelExtension _ext) {
					IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), Direction.getRandom(RandomSource.create()));
					if (_fluidHandler != null)
						_fluidHandler.fill(new FluidStack(Fluids.LAVA, 1000), IFluidHandler.FluidAction.EXECUTE);
				}
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				if ((world instanceof Level _level) && (!_level.isClientSide())) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.NEUTRAL, 1, 1);
				}
			} else {
				openFurnaceGUI(world, x, y, z, entity, Block_At_XYZ);
			}
		} else {
			openFurnaceGUI(world, x, y, z, entity, Block_At_XYZ);
		}
	}

	private static void openFurnaceGUI(LevelAccessor world, double x, double y, double z, Entity entity, BlockState blockState) {
		if (!(entity instanceof ServerPlayer _ent)) return;

		// Check if it's one of our furnace blocks
		if (blockState.getBlock() == RegistryBIBI.COPPER_FURNACE.get() ||
				blockState.getBlock() == RegistryBIBI.IRON_FURNACE.get() ||
				blockState.getBlock() == RegistryBIBI.GOLD_FURNACE.get() ||
				blockState.getBlock() == RegistryBIBI.DIAMOND_FURNACE.get() ||
				blockState.getBlock() == RegistryBIBI.NETHERITE_FURNACE.get()) {

			BlockPos _bpos = BlockPos.containing(x, y, z);
			_ent.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("OPFurnace");
				}

				@Override
				public boolean shouldTriggerClientSideContainerClosingOnOpen() {
					return false;
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new OPFurnaceInv(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}
