package net.offllneplayer.opminecraft.init;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import net.offllneplayer.opminecraft.client.ModModelLayers;

import net.offllneplayer.opminecraft.iwe.CryingHatchet.ThrownCryingHatchetModel;
import net.offllneplayer.opminecraft.iwe.SMBSuperFan.ThrownSMBSuperFanModel;
import net.offllneplayer.opminecraft.iwe.TNTStick.ThrownTNTStickModel;

import net.offllneplayer.opminecraft.OPMinecraft;

@EventBusSubscriber(modid = OPMinecraft.Mod_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryModEventBus {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        /*

        event.registerLayerDefinition(ModModelLayers.PENGUIN, PenguinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GIRAFFE, GiraffeModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WARTURTLE, WarturtleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WARTURTLE_ARMOR, WarturtleModel::createBodyLayer);

         */
        event.registerLayerDefinition(ModModelLayers.THROWN_CRYING_HATCHET, ThrownCryingHatchetModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.THROWN_SMB_SUPER_FAN, ThrownSMBSuperFanModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.THROWN_TNT_STICK, ThrownTNTStickModel::createBodyLayer);

    }

    /*

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PENGUIN.get(), PenguinEntity.createAttributes().build());
        event.put(ModEntities.GIRAFFE.get(), GiraffeEntity.createAttributes().build());
        event.put(ModEntities.WARTURTLE.get(), WarturtleEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.PENGUIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.GIRAFFE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.WARTURTLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        }

        */

}
