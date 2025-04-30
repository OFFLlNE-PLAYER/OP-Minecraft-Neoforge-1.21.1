package net.offllneplayer.opminecraft.init;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import net.offllneplayer.opminecraft.client.ModModelLayers;
import net.offllneplayer.opminecraft.client.SMBSuperFan.SMBSuperFanModel;
import net.offllneplayer.opminecraft.client.crying_hatchet.CryingHatchetModel;
import net.offllneplayer.opminecraft.client.dynamite.ThrownDynamiteStickModel;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.client.tnt.ThrownTNTStickModel;

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
        event.registerLayerDefinition(ModModelLayers.CRYING_HATCHET, CryingHatchetModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.THROWN_DYNAMITE_STICK, ThrownDynamiteStickModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.THROWN_TNT_STICK, ThrownTNTStickModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.SMB_SUPER_FAN, SMBSuperFanModel::createBodyLayer);
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
