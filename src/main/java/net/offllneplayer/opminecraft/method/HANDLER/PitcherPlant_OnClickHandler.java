package net.offllneplayer.opminecraft.method.HANDLER;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.init.RegistryBIBI;

import javax.annotation.Nullable;

@EventBusSubscriber
public class PitcherPlant_OnClickHandler {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {

		if (entity == null) return;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.PITCHER_PLANT) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.APPLE) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(Items.APPLE);
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep6 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep6).toString() : "")
						.equals("upper")) {
					world.setBlock(BlockPos.containing(x, y - 1, z), RegistryBIBI.FLOWERING_PITCHER_PLANT.get().defaultBlockState(), 3);
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep9
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep9).toString()
						: "").equals("lower")) {
					world.setBlock(BlockPos.containing(x, y, z), RegistryBIBI.FLOWERING_PITCHER_PLANT.get().defaultBlockState(), 3);
				}
			}
		}
	}
}
