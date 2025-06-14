
package net.offllneplayer.opminecraft.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;

import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryDamageTypes {


    public static final ResourceKey<DamageType> AKU_AKU = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "aku_aku"));

    public static final ResourceKey<DamageType> CRYING_ESSENCE = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "crying_essence"));

    public static final ResourceKey<DamageType> GUNBLADE = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "gunblade"));

    public static final ResourceKey<DamageType> HATCHET = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "hatchet"));

    public static final ResourceKey<DamageType> SMB_SUPER_FAN = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "smb_super_fan"));

    public static final ResourceKey<DamageType> SW0RD = ResourceKey.create(Registries.DAMAGE_TYPE,
       ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "sw0rd"));

    public static final ResourceKey<DamageType> BERETTA = ResourceKey.create(Registries.DAMAGE_TYPE,
       ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "beretta"));
    public static final ResourceKey<DamageType> SAMURAI_EDGE = ResourceKey.create(Registries.DAMAGE_TYPE,
       ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "samurai_edge"));

    public static final ResourceKey<DamageType> WUMPA_FRUIT = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "wumpa_fruit"));

}
