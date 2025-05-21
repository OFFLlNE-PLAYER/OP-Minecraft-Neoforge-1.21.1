package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.offllneplayer.opminecraft.iwe.Gunblade.StuckGunblade;
import net.offllneplayer.opminecraft.iwe.Hatchet.ThrownHatchet;
import net.offllneplayer.opminecraft.iwe.SMBSuperFan.ThrownSMBSuperFan;
import net.offllneplayer.opminecraft.iwe.TNTStick.ThrownTNTStick;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.function.Supplier;

public class RegistryEntities {

/*--------------------------------------------------------------------------------------------*/
    /*[Declare Registry]*/
    public static final DeferredRegister<EntityType<?>> ENTREGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, OPMinecraft.Mod_ID);

/*--------------------------------------------------------------------------------------------*/
    /*[Register Entities]*/

    public static final Supplier<EntityType<StuckGunblade>> STUCK_GUNBLADE = ENTREGISTRY.register("stuck_gunblade",
       ()-> EntityType.Builder.<StuckGunblade>of(StuckGunblade::new, MobCategory.MISC).build("stuck_gunblade"));

    public static final Supplier<EntityType<ThrownHatchet>> THROWN_HATCHET = ENTREGISTRY.register("thrown_hatchet",
       ()-> EntityType.Builder.<ThrownHatchet>of(ThrownHatchet::new, MobCategory.MISC).build("thrown_hatchet"));

    public static final Supplier<EntityType<ThrownTNTStick>> THROWN_TNT_STICK = ENTREGISTRY.register("thrown_tnt_stick",
            ()-> EntityType.Builder.<ThrownTNTStick>of(ThrownTNTStick::new, MobCategory.MISC).build("thrown_tnt_stick"));

    public static final Supplier<EntityType<ThrownSMBSuperFan>> THROWN_SMB_SUPER_FAN = ENTREGISTRY.register("thrown_smb_super_fan",
            ()-> EntityType.Builder.<ThrownSMBSuperFan>of(ThrownSMBSuperFan::new, MobCategory.MISC).build("thrown_smb_super_fan"));

}
