package net.offllneplayer.opminecraft;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.Tuple;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.common.NeoForge;

import net.offllneplayer.opminecraft.init.fml.FMLDispenserBehaviors;
import net.offllneplayer.opminecraft.method._handler.DamageEventHandler;
import net.offllneplayer.opminecraft.method._handler.EntitySpawnHandler;
import net.offllneplayer.opminecraft.init.*;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


@Mod(OPMinecraft.Mod_ID)
public class OPMinecraft {

    public static final String Mod_ID = "opminecraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public OPMinecraft(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::registerNetworking);
        modEventBus.addListener(this::commonSetup);

        RegistrySounds.SOUNDSREGISTRY.register(modEventBus);

        RegistryIBBI.BLOCKSREGISTRY.register(modEventBus);
        RegistryIBBI.FR_BLOCKSREGISTRY.register(modEventBus);
        RegistryIBBI.FR_EPIC_BLOCKSREGISTRY.register(modEventBus);
        RegistryIBBI.UNCOMMON_BLOCKSREGISTRY.register(modEventBus);
        RegistryIBBI.RARE_BLOCKSREGISTRY.register(modEventBus);

        RegistryBlockEntities.BLOCKENTREGISTRY.register(modEventBus);

        RegistryInventory.INVREGISTRY.register(modEventBus);

        RegistryEntities.ENTREGISTRY.register(modEventBus);

        RegistryIBBI.ITEMSREGISTRY.register(modEventBus);

        RegistryPotions.POTSREGISTRY.register(modEventBus);
        RegistryMobEffects.MOBEFFECTSREGISTRY.register(modEventBus);

        RegistryParticleTypes.PARTICLETYPESREGISTRY.register(modEventBus);

        RegistryFluids.FLUIDSREGISTRY.register(modEventBus);
        RegistryFluids.FLUIDTYPESREGISTRY.register(modEventBus);

        RegistryCreative.CREATIVETABSREGISTRY.register(modEventBus);

        RegistryEnchantmentEffects.register(modEventBus);

        RegistryDataComponents.DATA_COMPONENTS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(FMLDispenserBehaviors::DispenserBehaviors);
    }

    private static boolean networkingRegistered = false;
    private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

    private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
    }

    public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
        if (networkingRegistered)
            throw new IllegalStateException("Cannot register new _handler messages after networking has been registered");
        MESSAGES.put(id, new NetworkMessage<>(reader, handler));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void registerNetworking(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Mod_ID);
        MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, ((NetworkMessage) networkMessage).reader(), ((NetworkMessage) networkMessage).handler()));
        networkingRegistered = true;
    }

    private static final Collection<Tuple<Runnable, Integer>> TupleQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
            TupleQueue.add(new Tuple<>(action, tick));
    }

    @SubscribeEvent
    public void tick(ServerTickEvent.Post event) {
        List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
        TupleQueue.forEach(work -> {
            work.setB(work.getB() - 1);
            if (work.getB() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getA().run());
        TupleQueue.removeAll(actions);
    }
}
