package net.offllneplayer.opminecraft.init.fml.dispenser;

import net.minecraft.world.level.block.DispenserBlock;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

public class FMLDispenser {

    public static void DispenserBehaviors() {
		 DispenserBlock.registerBehavior(RegistryBIBI.WOODEN_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.CLAY_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.STONE_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.GOLDEN_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.IRON_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.EMERALD_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.DIAMOND_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.NETHERITE_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.ONYX_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.TITAN_HATCHET.get(), new HatchetDispenseBehavior());
		 DispenserBlock.registerBehavior(RegistryBIBI.CRYING_HATCHET.get(), new HatchetDispenseBehavior());
		  
        DispenserBlock.registerBehavior(RegistryBIBI.SMB_SUPER_FAN.get(), new SMBSuperFanDispenseBehavior());
        DispenserBlock.registerBehavior(RegistryBIBI.TNT_STICK.get(), new TNTStickDispenseBehavior());
    }
}

