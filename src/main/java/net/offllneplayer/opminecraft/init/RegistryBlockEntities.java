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

import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestBlock;
import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestBlockEntity;
import net.offllneplayer.opminecraft.blocks._block._furnace.OPFurnaceBlock;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.crashtnt.CrashTNTBlockEntity;
import net.offllneplayer.opminecraft.blocks._block.crash.crates.nitro.NitroBlockEntity;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.blocks._block._furnace.OPFurnaceBlockEntity;
import net.offllneplayer.opminecraft.blocks._block._furnace.OPFurnaceMaterial;
import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestWoodMaterial;
import net.offllneplayer.opminecraft.blocks._block._ancientchest.AncientChestTrimMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RegistryBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTREGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, OPMinecraft.Mod_ID);


    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   //~[OP Furnaces]~
    private static final Map<OPFurnaceMaterial, DeferredHolder<BlockEntityType<?>, BlockEntityType<?>>> FURNACE_MATERIAL_TO_BLOCKENTITY = new HashMap<>();

    // Register one BlockEntity type per furnace material
	 static {
		 for (OPFurnaceMaterial material : OPFurnaceMaterial.values()) {
			 String registryName = material.name().toLowerCase() + "_furnace";

			 // Register the BlockEntity type with a lazy supplier
			 DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> holder =
					 BLOCKENTREGISTRY.register(registryName,
							 () -> {
								 String key = material.name().toLowerCase() + "_furnace";
								 Supplier<OPFurnaceBlock> blockSupplier = RegistryBIBI.FURNACE_VARIANTS.get(key);
								 Block furnaceBlock = blockSupplier != null ? blockSupplier.get() : null;

								 return BlockEntityType.Builder.of((pos, state) ->
										 new OPFurnaceBlockEntity(pos, state, material), furnaceBlock).build(null);
							 });

			 // Store in map for lookup
			 FURNACE_MATERIAL_TO_BLOCKENTITY.put(material, holder);
		 }
	 }

	// Helper method to get the BlockEntity type for a furnace material
	public static BlockEntityType<?> getOPFurnaceType(OPFurnaceMaterial material) {
		return FURNACE_MATERIAL_TO_BLOCKENTITY.get(material).get();
	}


    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   //~[Ancient Chests]~
    private static final Map<AncientChestTrimMaterial, DeferredHolder<BlockEntityType<?>, BlockEntityType<?>>> TRIM_MATERIAL_TO_BLOCKENTITY = new HashMap<>();

    // Register one BlockEntity type per trim material
    static {
        for (AncientChestTrimMaterial trimMaterial : AncientChestTrimMaterial.values()) {
            String registryName = trimMaterial.name().toLowerCase() + "_trim_ancient_chest";

            // Register the BlockEntity type with a lazy supplier
            DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> holder =
                  BLOCKENTREGISTRY.register(registryName,
                        () -> {
                            // This code runs during registration, not during static init
                            List<Block> blocksWithTrim = new ArrayList<>();
                            for (AncientChestWoodMaterial woodMaterial : AncientChestWoodMaterial.values()) {
                                String key = woodMaterial.name().toLowerCase() + "_" + trimMaterial.name().toLowerCase() + "_trim_ancient_chest";
                                Supplier<AncientChestBlock> blockSupplier = RegistryBIBI.ANCIENT_CHEST_VARIANTS.get(key);
                                if (blockSupplier != null) {
                                    blocksWithTrim.add(blockSupplier.get());
                                }
                            }

                            return BlockEntityType.Builder.of((pos, state) ->
                                  new AncientChestBlockEntity(pos, state, trimMaterial), blocksWithTrim.toArray(new Block[0])).build(null);
                        });

            // Store in map for lookup
            TRIM_MATERIAL_TO_BLOCKENTITY.put(trimMaterial, holder);
        }
    }

    // Helper method to get the BlockEntity type for a trim material
    public static BlockEntityType<?> getLootChestType(AncientChestTrimMaterial trimMaterial) {
        return TRIM_MATERIAL_TO_BLOCKENTITY.get(trimMaterial).get();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   //~[Crash]~
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CRASH_TNT =
          register("crash_tnt", RegistryBIBI.CRASH_TNT, CrashTNTBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> NITRO =
          register("nitro", RegistryBIBI.NITRO, NitroBlockEntity::new);


    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   //~[Register]~
    private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return BLOCKENTREGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   //~[Register Capabilites]~
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Register capabilities for all furnace materials dynamically
        for (OPFurnaceMaterial material : OPFurnaceMaterial.values()) {
            BlockEntityType<?> blockEntityType = FURNACE_MATERIAL_TO_BLOCKENTITY.get(material).get();
            event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, blockEntityType, (blockEntity, side) -> ((OPFurnaceBlockEntity) blockEntity).getFluidTank());
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, blockEntityType, (blockEntity, side) -> ((OPFurnaceBlockEntity) blockEntity).getItemHandler());
        }

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CRASH_TNT.get(), (blockEntity, side) -> ((CrashTNTBlockEntity) blockEntity).getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NITRO.get(), (blockEntity, side) -> ((NitroBlockEntity) blockEntity).getItemHandler());

        for (AncientChestTrimMaterial trimMaterial : AncientChestTrimMaterial.values()) {
            BlockEntityType<?> blockEntityType = TRIM_MATERIAL_TO_BLOCKENTITY.get(trimMaterial).get();
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, blockEntityType, (blockEntity, side) -> ((AncientChestBlockEntity) blockEntity).getItemHandler());
        }
    }
}

