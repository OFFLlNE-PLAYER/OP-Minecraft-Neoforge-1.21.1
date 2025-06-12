package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.offllneplayer.opminecraft.iwe.pistol.PistolBullet;
import net.offllneplayer.opminecraft.iwe.sw0rd.StuckSw0rd;
import net.offllneplayer.opminecraft.iwe.gunblade.StuckGunblade;
import net.offllneplayer.opminecraft.iwe.hatchet.ThrownHatchet;
import net.offllneplayer.opminecraft.iwe.SMBSuperFan.ThrownSMBSuperFan;
import net.offllneplayer.opminecraft.iwe.opsw0rd.StuckOPSword;
import net.offllneplayer.opminecraft.iwe.tntstick.ThrownTNTStick;
import net.offllneplayer.opminecraft.OPMinecraft;

import java.util.function.Supplier;

public class RegistryEntities {

/*--------------------------------------------------------------------------------------------*/
    /*[Declare Registry]*/
    public static final DeferredRegister<EntityType<?>> ENTREGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, OPMinecraft.Mod_ID);

/*--------------------------------------------------------------------------------------------*/
    /*[Register Entities]*/

    public static final Supplier<EntityType<PistolBullet>> PISTOL_BULLET = ENTREGISTRY.register("pistol_bullet",
       ()-> EntityType.Builder.<PistolBullet>of(PistolBullet::new, MobCategory.MISC).build("pistol_bullet"));

    public static final Supplier<EntityType<StuckGunblade>> STUCK_GUNBLADE = ENTREGISTRY.register("stuck_gunblade",
       ()-> EntityType.Builder.<StuckGunblade>of(StuckGunblade::new, MobCategory.MISC).build("stuck_gunblade"));

    public static final Supplier<EntityType<StuckSw0rd>> STUCK_SW0RD = ENTREGISTRY.register("stuck_sw0rd",
       () -> EntityType.Builder.<StuckSw0rd>of(StuckSw0rd::new, MobCategory.MISC).build("stuck_sw0rd"));

    /*
    maybe we can just use StuckSw0rd?
    public static final Supplier<EntityType<StuckOPSw0rd>> STUCK_OP_SW0RD = ENTREGISTRY.register("stuck_op_sw0rd",
       () -> EntityType.Builder.<StuckOPSw0rd>of(StuckOPSw0rd::new, MobCategory.MISC).build("stuck_op_sw0rd"));
     */

    public static final Supplier<EntityType<StuckOPSword>> STUCK_OP_SWORD = ENTREGISTRY.register("stuck_op_sword",
       () -> EntityType.Builder.<StuckOPSword>of(StuckOPSword::new, MobCategory.MISC).build("stuck_op_sword"));


    public static final Supplier<EntityType<ThrownHatchet>> THROWN_HATCHET = ENTREGISTRY.register("thrown_hatchet",
       ()-> EntityType.Builder.<ThrownHatchet>of(ThrownHatchet::new, MobCategory.MISC).build("thrown_hatchet"));

    public static final Supplier<EntityType<ThrownTNTStick>> THROWN_TNT_STICK = ENTREGISTRY.register("thrown_tnt_stick",
            ()-> EntityType.Builder.<ThrownTNTStick>of(ThrownTNTStick::new, MobCategory.MISC).build("thrown_tnt_stick"));

    public static final Supplier<EntityType<ThrownSMBSuperFan>> THROWN_SMB_SUPER_FAN = ENTREGISTRY.register("thrown_smb_super_fan",
            ()-> EntityType.Builder.<ThrownSMBSuperFan>of(ThrownSMBSuperFan::new, MobCategory.MISC).build("thrown_smb_super_fan"));

}
