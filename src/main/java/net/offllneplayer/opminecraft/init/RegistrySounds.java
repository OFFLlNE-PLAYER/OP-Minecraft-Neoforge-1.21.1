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

        public static final DeferredHolder<SoundEvent, SoundEvent> SILENT_SOUND = SOUNDSREGISTRY.register("silent_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "silent_sound")));

        public static final DeferredHolder<SoundEvent, SoundEvent> AKU_AKU = SOUNDSREGISTRY.register("aku_aku", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "aku_aku")));
        public static final DeferredHolder<SoundEvent, SoundEvent> AKU_AKU_DIES = SOUNDSREGISTRY.register("aku_aku_dies", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "aku_aku_dies")));

        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_CRATE_BREAK = SOUNDSREGISTRY.register("crash_crate_break", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crash_crate_break")));
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_CRATE_BOUNCE = SOUNDSREGISTRY.register("crash_crate_bounce", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crash_crate_bounce")));
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_SPIN = SOUNDSREGISTRY.register("crash_spin", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crash_spin")));

        public static final DeferredHolder<SoundEvent, SoundEvent> CRYING_EXPLODE = SOUNDSREGISTRY.register("crying_explode", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crying_explode")));
        public static final DeferredHolder<SoundEvent, SoundEvent> CRYING_FURNACE = SOUNDSREGISTRY.register("crying_furnace", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crying_furnace")));

        public static final DeferredHolder<SoundEvent, SoundEvent> LAMP_USE = SOUNDSREGISTRY.register("lamp_use", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "lamp_use")));

        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_BREAK = SOUNDSREGISTRY.register("gunblade_break", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "gunblade_break")));
        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_IN_DIRT = SOUNDSREGISTRY.register("gunblade_in_dirt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "gunblade_in_dirt")));
        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_SLASH = SOUNDSREGISTRY.register("gunblade_slash", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "gunblade_slash")));
        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_SHOT = SOUNDSREGISTRY.register("gunblade_shot", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "gunblade_shot")));

        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_BOOM = SOUNDSREGISTRY.register("nitro_boom", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_boom")));
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_1 = SOUNDSREGISTRY.register("nitro_idle_1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_idle_1")));
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_2 = SOUNDSREGISTRY.register("nitro_idle_2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_idle_2")));
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_3 = SOUNDSREGISTRY.register("nitro_idle_3", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_idle_3")));

        public static final DeferredHolder<SoundEvent, SoundEvent> PRIME_TNT = SOUNDSREGISTRY.register("prime_tnt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "prime_tnt")));
        public static final DeferredHolder<SoundEvent, SoundEvent> FAST_TICK_TNT = SOUNDSREGISTRY.register("fast_tick_tnt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "fast_tick_tnt")));
        public static final DeferredHolder<SoundEvent, SoundEvent> TICK_TNT = SOUNDSREGISTRY.register("tick_tnt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "tick_tnt")));
        public static final DeferredHolder<SoundEvent, SoundEvent> TNT_BOOM = SOUNDSREGISTRY.register("tnt_boom", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "tnt_boom")));

        public static final DeferredHolder<SoundEvent, SoundEvent> PUNCH = SOUNDSREGISTRY.register("punch", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "punch")));

        public static final DeferredHolder<SoundEvent, SoundEvent> SMB_SUPER_FAN_HIT = SOUNDSREGISTRY.register("smb_super_fan_hit", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "smb_super_fan_hit")));
        public static final DeferredHolder<SoundEvent, SoundEvent> SMB_SUPER_FAN_IDLE = SOUNDSREGISTRY.register("smb_super_fan_idle", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "smb_super_fan_idle")));

        public static final DeferredHolder<SoundEvent, SoundEvent> WUMPA_FRUIT = SOUNDSREGISTRY.register("wumpa_fruit", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "wumpa_fruit")));

}
