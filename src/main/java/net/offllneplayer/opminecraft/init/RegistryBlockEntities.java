package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.offllneplayer.opminecraft.block.crash.crates.crashtnt.CrashTNTBlockEntity;
import net.offllneplayer.opminecraft.block.crash.crates.nitro.NitroBlockEntity;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.block.furnaces.furnace.*;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RegistryBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTREGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, OPMinecraft.Mod_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> IRON_FURNACE =
            register("iron_furnace", RegistryBIBI.IRON_FURNACE, IronFurnaceBlockEntity::new);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> COPPER_FURNACE =
            register("copper_furnace", RegistryBIBI.COPPER_FURNACE, CopperFurnaceBlockEntity::new);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> GOLD_FURNACE =
            register("gold_furnace", RegistryBIBI.GOLD_FURNACE, GoldFurnaceBlockEntity::new);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DIAMOND_FURNACE =
            register("diamond_furnace", RegistryBIBI.DIAMOND_FURNACE, DiamondFurnaceBlockEntity::new);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> NETHERITE_FURNACE =
            register("netherite_furnace", RegistryBIBI.NETHERITE_FURNACE, NetheriteFurnaceBlockEntity::new);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CRASH_TNT =
            register("crash_tnt", RegistryBIBI.CRASH_TNT, CrashTNTBlockEntity::new);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> NITRO =
            register("nitro", RegistryBIBI.NITRO, NitroBlockEntity::new);


    private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return BLOCKENTREGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, IRON_FURNACE.get(), (blockEntity, side) -> ((IronFurnaceBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, IRON_FURNACE.get(), (blockEntity, side) -> ((IronFurnaceBlockEntity) blockEntity).getFluidTank());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COPPER_FURNACE.get(), (blockEntity, side) -> ((CopperFurnaceBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, COPPER_FURNACE.get(), (blockEntity, side) -> ((CopperFurnaceBlockEntity) blockEntity).getFluidTank());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GOLD_FURNACE.get(), (blockEntity, side) -> ((GoldFurnaceBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, GOLD_FURNACE.get(), (blockEntity, side) -> ((GoldFurnaceBlockEntity) blockEntity).getFluidTank());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIAMOND_FURNACE.get(), (blockEntity, side) -> ((DiamondFurnaceBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, DIAMOND_FURNACE.get(), (blockEntity, side) -> ((DiamondFurnaceBlockEntity) blockEntity).getFluidTank());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHERITE_FURNACE.get(), (blockEntity, side) -> ((NetheriteFurnaceBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, NETHERITE_FURNACE.get(), (blockEntity, side) -> ((NetheriteFurnaceBlockEntity) blockEntity).getFluidTank());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CRASH_TNT.get(), (blockEntity, side) -> ((CrashTNTBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NITRO.get(), (blockEntity, side) -> ((NitroBlockEntity) blockEntity).getItemHandler());

    }
}
