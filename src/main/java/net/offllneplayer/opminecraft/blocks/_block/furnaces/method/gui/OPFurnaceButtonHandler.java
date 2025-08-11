
package net.offllneplayer.opminecraft.blocks._block.furnaces.method.gui;

import net.minecraft.core.SectionPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.blocks._block.furnaces.OPFurnaceInv;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record OPFurnaceButtonHandler(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<OPFurnaceButtonHandler> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "gui_op_furnace_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, OPFurnaceButtonHandler> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, OPFurnaceButtonHandler message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new OPFurnaceButtonHandler(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<OPFurnaceButtonHandler> type() {
		return TYPE;
	}

	public static void handleData(final OPFurnaceButtonHandler message, final IPayloadContext context) {
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
		HashMap guistate = OPFurnaceInv.guistate;
		world.getChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z));
		if (buttonID == 0) {

			FurnacesExpButton_Method.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			FurnacesStokeButton_Method.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		OPMinecraft.addNetworkMessage(OPFurnaceButtonHandler.TYPE, OPFurnaceButtonHandler.STREAM_CODEC, OPFurnaceButtonHandler::handleData);
	}
}