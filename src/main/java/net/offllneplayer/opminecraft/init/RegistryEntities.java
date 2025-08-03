package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.offllneplayer.opminecraft.iwe.beretta.PistolBullet;
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
    /*[Helper Method]*/
    private static <T extends net.minecraft.world.entity.Entity> Supplier<EntityType<T>> registerEntity(String name, EntityType.EntityFactory<T> factory, MobCategory category) {
        return ENTREGISTRY.register(name, () -> EntityType.Builder.<T>of(factory, category).build(name));
    }

    /*--------------------------------------------------------------------------------------------*/
    /*[Register Entities]*/

    public static final Supplier<EntityType<PistolBullet>> PISTOL_BULLET = registerEntity("pistol_bullet", PistolBullet::new, MobCategory.MISC);

    public static final Supplier<EntityType<StuckGunblade>> STUCK_GUNBLADE = registerEntity("stuck_gunblade", StuckGunblade::new, MobCategory.MISC);

    public static final Supplier<EntityType<StuckSw0rd>> STUCK_SW0RD = registerEntity("stuck_sw0rd", StuckSw0rd::new, MobCategory.MISC);

    public static final Supplier<EntityType<StuckOPSword>> STUCK_OP_SWORD = registerEntity("stuck_op_sword", StuckOPSword::new, MobCategory.MISC);

    public static final Supplier<EntityType<ThrownHatchet>> THROWN_HATCHET = registerEntity("thrown_hatchet", ThrownHatchet::new, MobCategory.MISC);

    public static final Supplier<EntityType<ThrownTNTStick>> THROWN_TNT_STICK = registerEntity("thrown_tnt_stick", ThrownTNTStick::new, MobCategory.MISC);

    public static final Supplier<EntityType<ThrownSMBSuperFan>> THROWN_SMB_SUPER_FAN = registerEntity("thrown_smb_super_fan", ThrownSMBSuperFan::new, MobCategory.MISC);
}

