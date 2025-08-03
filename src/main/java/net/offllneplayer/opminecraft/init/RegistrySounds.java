package net.offllneplayer.opminecraft.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.offllneplayer.opminecraft.OPMinecraft;

public class RegistrySounds {

        /*-----------------------------------------------------------------------------------------------------------------------*/
        /*[Declare Registry]*/
        public static final DeferredRegister<SoundEvent> SOUNDSREGISTRY =
              DeferredRegister.create(Registries.SOUND_EVENT, OPMinecraft.Mod_ID);

        /*-----------------------------------------------------------------------------------------------------------------------*/
        /*[Helper Method]*/
        private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
                return SOUNDSREGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, name)));
        }

        /*-----------------------------------------------------------------------------------------------------------------------*/

        public static final DeferredHolder<SoundEvent, SoundEvent> SILENT_SOUND = registerSound("silent_sound");

        public static final DeferredHolder<SoundEvent, SoundEvent> BLADE_SLASH = registerSound("blade_slash");
        public static final DeferredHolder<SoundEvent, SoundEvent> BLADE_STICK = registerSound("blade_stick");

        public static final DeferredHolder<SoundEvent, SoundEvent> AKU_AKU = registerSound("aku_aku");
        public static final DeferredHolder<SoundEvent, SoundEvent> AKU_AKU_DIES = registerSound("aku_aku_dies");

        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_CRATE_BREAK = registerSound("crash_crate_break");
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_CRATE_BOUNCE = registerSound("crash_crate_bounce");
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_SPIN = registerSound("crash_spin");

        public static final DeferredHolder<SoundEvent, SoundEvent> CRYING_EXPLODE = registerSound("crying_explode");
        public static final DeferredHolder<SoundEvent, SoundEvent> CRYING_FURNACE = registerSound("crying_furnace");

        public static final DeferredHolder<SoundEvent, SoundEvent> TNT_FUSE = registerSound("tnt_fuse");
        public static final DeferredHolder<SoundEvent, SoundEvent> TNT_SIZZLE = registerSound("tnt_sizzle");

        public static final DeferredHolder<SoundEvent, SoundEvent> LAMP_USE = registerSound("lamp_use");

        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_BREAK = registerSound("gunblade_break");
        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_SHOT = registerSound("gunblade_shot");

        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_BOOM = registerSound("nitro_boom");
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_1 = registerSound("nitro_idle_1");
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_2 = registerSound("nitro_idle_2");
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_3 = registerSound("nitro_idle_3");

        public static final DeferredHolder<SoundEvent, SoundEvent> MAGNUM_R = registerSound("magnum_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> MAGNUM_0 = registerSound("magnum_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> MAGNUM_1 = registerSound("magnum_1");

        public static final DeferredHolder<SoundEvent, SoundEvent> REVOLVER_R = registerSound("revolver_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> REVOLVER_0 = registerSound("revolver_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> REVOLVER_1 = registerSound("revolver_1");

        public static final DeferredHolder<SoundEvent, SoundEvent> VALENTINE_REVOLVER_R = registerSound("valentine_revolver_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> VALENTINE_REVOLVER_0 = registerSound("valentine_revolver_0");

        public static final DeferredHolder<SoundEvent, SoundEvent> BERETTA_R = registerSound("beretta_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> BERETTA_0 = registerSound("beretta_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> BERETTA_1 = registerSound("beretta_1");

        public static final DeferredHolder<SoundEvent, SoundEvent> B3R3TT4_R = registerSound("b3r3tt4_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> B3R3TT4_0 = registerSound("b3r3tt4_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> B3R3TT4_1 = registerSound("b3r3tt4_1");

        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_0 = registerSound("samurai_edge_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_R = registerSound("samurai_edge_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_1 = registerSound("samurai_edge_1");
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_2 = registerSound("samurai_edge_2");
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_3 = registerSound("samurai_edge_3");

        public static final DeferredHolder<SoundEvent, SoundEvent> PROTEKTOR_BERETTA_R = registerSound("protektor_beretta_r");
        public static final DeferredHolder<SoundEvent, SoundEvent> PROTEKTOR_BERETTA_0 = registerSound("protektor_beretta_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> PROTEKTOR_BERETTA_1 = registerSound("protektor_beretta_1");

        public static final DeferredHolder<SoundEvent, SoundEvent> PRIME_TNT = registerSound("prime_tnt");
        public static final DeferredHolder<SoundEvent, SoundEvent> FAST_TICK_TNT = registerSound("fast_tick_tnt");
        public static final DeferredHolder<SoundEvent, SoundEvent> TICK_TNT = registerSound("tick_tnt");
        public static final DeferredHolder<SoundEvent, SoundEvent> TNT_BOOM = registerSound("tnt_boom");

        public static final DeferredHolder<SoundEvent, SoundEvent> PUNCH = registerSound("punch");
        public static final DeferredHolder<SoundEvent, SoundEvent> FLESHRIP_0 = registerSound("fleshrip_0");
        public static final DeferredHolder<SoundEvent, SoundEvent> FLESHRIP_1 = registerSound("fleshrip_1");

        public static final DeferredHolder<SoundEvent, SoundEvent> SMB_SUPER_FAN_HIT = registerSound("smb_super_fan_hit");
        public static final DeferredHolder<SoundEvent, SoundEvent> SMB_SUPER_FAN_IDLE = registerSound("smb_super_fan_idle");

        public static final DeferredHolder<SoundEvent, SoundEvent> WUMPA_FRUIT = registerSound("wumpa_fruit");

}

