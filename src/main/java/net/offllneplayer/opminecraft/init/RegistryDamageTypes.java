
package net.offllneplayer.opminecraft.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;

import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistryDamageTypes {

    public static final ResourceKey<DamageType> CRYING_ESSENCE = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "crying_essence")
    );

    public static final ResourceKey<DamageType> SMB_SUPER_FAN = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "smb_super_fan")
    );

    public static final ResourceKey<DamageType> WUMPA_FRUIT = ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "wumpa_fruit")
    );

}
