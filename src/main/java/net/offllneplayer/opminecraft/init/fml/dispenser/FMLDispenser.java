package net.offllneplayer.opminecraft.init.fml.dispenser;

import net.minecraft.world.level.block.DispenserBlock;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

public class FMLDispenser {

    public static void DispenserBehaviors() {
        DispenserBlock.registerBehavior(RegistryBIBI.CRYING_HATCHET.get(), new HatchetDispenseBehavior());
        DispenserBlock.registerBehavior(RegistryBIBI.SMB_SUPER_FAN.get(), new SMBSuperFanDispenseBehavior());
        DispenserBlock.registerBehavior(RegistryBIBI.TNT_STICK.get(), new TNTStickDispenseBehavior());
    }
}

