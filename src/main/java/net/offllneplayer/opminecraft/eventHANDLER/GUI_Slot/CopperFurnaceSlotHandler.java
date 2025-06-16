
package net.offllneplayer.opminecraft.eventHANDLER.GUI_Slot;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.offllneplayer.opminecraft.world.inventory.furnaces.CopperFurnaceInv;
import net.offllneplayer.opminecraft.block.furnaces.FurnacesResetProgress_Method;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CopperFurnaceSlotHandler(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {

	public static final Type<CopperFurnaceSlotHandler> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "gui_copper_furnace_slots"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CopperFurnaceSlotHandler> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CopperFurnaceSlotHandler message) -> {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}, (RegistryFriendlyByteBuf buffer) -> new CopperFurnaceSlotHandler(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<CopperFurnaceSlotHandler> type() {
		return TYPE;
	}

	public static void handleData(final CopperFurnaceSlotHandler message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = CopperFurnaceInv.guistate;

		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) { FurnacesResetProgress_Method.execute(world, x, y, z);
		}
		if (slot == 1 && changeType == 0) { FurnacesResetProgress_Method.execute(world, x, y, z);
		}
		if (slot == 2 && changeType == 0) { FurnacesResetProgress_Method.execute(world, x, y, z);
		}
		if (slot == 3 && changeType == 0) { FurnacesResetProgress_Method.execute(world, x, y, z);
		}
		if (slot == 4 && changeType == 0) { FurnacesResetProgress_Method.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		OPMinecraft.addNetworkMessage(CopperFurnaceSlotHandler.TYPE, CopperFurnaceSlotHandler.STREAM_CODEC, CopperFurnaceSlotHandler::handleData);
	}
}
