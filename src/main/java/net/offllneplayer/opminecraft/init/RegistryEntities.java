package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.entity.SMBSuperFan;
import net.offllneplayer.opminecraft.entity.ThrownDynamiteStick;
import net.offllneplayer.opminecraft.entity.ThrownTNTStick;

import java.util.function.Supplier;

public class RegistryEntities {

/*--------------------------------------------------------------------------------------------*/
    /*[Declare Registry]*/
    public static final DeferredRegister<EntityType<?>> ENTREGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, OPMinecraft.Mod_ID);

/*--------------------------------------------------------------------------------------------*/
    /*[Register Entities]*/
    public static final Supplier<EntityType<ThrownDynamiteStick>> THROWN_DYNAMITE_STICK = ENTREGISTRY.register("thrown_dynamite_stick",
            ()-> EntityType.Builder.<ThrownDynamiteStick>of(ThrownDynamiteStick::new, MobCategory.MISC).build("thrown_dynamite_stick"));

    public static final Supplier<EntityType<ThrownTNTStick>> THROWN_TNT_STICK = ENTREGISTRY.register("thrown_tnt_stick",
            ()-> EntityType.Builder.<ThrownTNTStick>of(ThrownTNTStick::new, MobCategory.MISC).build("thrown_tnt_stick"));

    public static final Supplier<EntityType<SMBSuperFan>> SMB_SUPER_FAN = ENTREGISTRY.register("smb_super_fan",
            ()-> EntityType.Builder.<SMBSuperFan>of(SMBSuperFan::new, MobCategory.MISC).build("smb_super_fan"));

}
