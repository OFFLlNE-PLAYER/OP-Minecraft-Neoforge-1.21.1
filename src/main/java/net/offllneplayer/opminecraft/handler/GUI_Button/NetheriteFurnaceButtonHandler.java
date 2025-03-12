
package net.offllneplayer.opminecraft.handler.GUI_Button;

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

import net.offllneplayer.opminecraft.method.furnaces.FurnacesStokeButton_Method;
import net.offllneplayer.opminecraft.world.inventory.NetheriteFurnaceInv;
import net.offllneplayer.opminecraft.method.furnaces.FurnacesExpButton_Method;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record NetheriteFurnaceButtonHandler(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<NetheriteFurnaceButtonHandler> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "gui_netherite_furnace_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, NetheriteFurnaceButtonHandler> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, NetheriteFurnaceButtonHandler message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new NetheriteFurnaceButtonHandler(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<NetheriteFurnaceButtonHandler> type() {
		return TYPE;
	}

	public static void handleData(final NetheriteFurnaceButtonHandler message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = NetheriteFurnaceInv.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			FurnacesExpButton_Method.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			FurnacesStokeButton_Method.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		OPMinecraft.addNetworkMessage(NetheriteFurnaceButtonHandler.TYPE, NetheriteFurnaceButtonHandler.STREAM_CODEC, NetheriteFurnaceButtonHandler::handleData);
	}
}