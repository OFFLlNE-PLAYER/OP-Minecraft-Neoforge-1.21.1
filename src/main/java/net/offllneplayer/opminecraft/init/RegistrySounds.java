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

        // UI/Silent sound - keep this one as variable range since it's for UI
        public static final DeferredHolder<SoundEvent, SoundEvent> SILENT_SOUND = SOUNDSREGISTRY.register("silent_sound", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "silent_sound"), 1F));

        // Weapon sounds - 16 blocks range
        public static final DeferredHolder<SoundEvent, SoundEvent> BLADE_SLASH = SOUNDSREGISTRY.register("blade_slash", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "blade_slash"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> BLADE_STICK = SOUNDSREGISTRY.register("blade_stick", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "blade_stick"), 12F));

        // Character/entity sounds - 16 blocks range
        public static final DeferredHolder<SoundEvent, SoundEvent> AKU_AKU = SOUNDSREGISTRY.register("aku_aku", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "aku_aku"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> AKU_AKU_DIES = SOUNDSREGISTRY.register("aku_aku_dies", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "aku_aku_dies"), 12F));

        // Block interaction sounds - 16 blocks range
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_CRATE_BREAK = SOUNDSREGISTRY.register("crash_crate_break", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crash_crate_break"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_CRATE_BOUNCE = SOUNDSREGISTRY.register("crash_crate_bounce", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crash_crate_bounce"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> CRASH_SPIN = SOUNDSREGISTRY.register("crash_spin", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crash_spin"), 12F));

        // Explosion sounds - 32 blocks range
        public static final DeferredHolder<SoundEvent, SoundEvent> CRYING_EXPLODE = SOUNDSREGISTRY.register("crying_explode", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crying_explode"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> CRYING_FURNACE = SOUNDSREGISTRY.register("crying_furnace", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "crying_furnace"), 12F));

        public static final DeferredHolder<SoundEvent, SoundEvent> LAMP_USE = SOUNDSREGISTRY.register("lamp_use", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "lamp_use"), 8F));

        public static final DeferredHolder<SoundEvent, SoundEvent> GUNBLADE_SHOT = SOUNDSREGISTRY.register("gunblade_shot", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "gunblade_shot"), 12F));

        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_BOOM = SOUNDSREGISTRY.register("nitro_boom", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_boom"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_1 = SOUNDSREGISTRY.register("nitro_idle_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_idle_1"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_2 = SOUNDSREGISTRY.register("nitro_idle_2", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_idle_2"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> NITRO_IDLE_3 = SOUNDSREGISTRY.register("nitro_idle_3", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "nitro_idle_3"), 12F));

        // Gun sounds - reload (r) = 12F,  empty (0) = 12F, shots (#) = 16F
        public static final DeferredHolder<SoundEvent, SoundEvent> MAGNUM_R = SOUNDSREGISTRY.register("magnum_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "magnum_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> MAGNUM_0 = SOUNDSREGISTRY.register("magnum_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "magnum_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> MAGNUM_1 = SOUNDSREGISTRY.register("magnum_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "magnum_1"), 16F));

        public static final DeferredHolder<SoundEvent, SoundEvent> REVOLVER_R = SOUNDSREGISTRY.register("revolver_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "revolver_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> REVOLVER_0 = SOUNDSREGISTRY.register("revolver_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "revolver_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> REVOLVER_1 = SOUNDSREGISTRY.register("revolver_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "revolver_1"), 16F));

        public static final DeferredHolder<SoundEvent, SoundEvent> VALENTINE_REVOLVER_R = SOUNDSREGISTRY.register("valentine_revolver_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "valentine_revolver_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> VALENTINE_REVOLVER_0 = SOUNDSREGISTRY.register("valentine_revolver_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "valentine_revolver_0"), 12F));

        public static final DeferredHolder<SoundEvent, SoundEvent> BERETTA_R = SOUNDSREGISTRY.register("beretta_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "beretta_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> BERETTA_0 = SOUNDSREGISTRY.register("beretta_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "beretta_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> BERETTA_1 = SOUNDSREGISTRY.register("beretta_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "beretta_1"), 16F));

        public static final DeferredHolder<SoundEvent, SoundEvent> B3R3TT4_R = SOUNDSREGISTRY.register("b3r3tt4_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "b3r3tt4_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> B3R3TT4_0 = SOUNDSREGISTRY.register("b3r3tt4_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "b3r3tt4_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> B3R3TT4_1 = SOUNDSREGISTRY.register("b3r3tt4_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "b3r3tt4_1"), 16F));

        public static final DeferredHolder<SoundEvent, SoundEvent> PROTEKTOR_BERETTA_R = SOUNDSREGISTRY.register("protektor_beretta_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "protektor_beretta_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> PROTEKTOR_BERETTA_0 = SOUNDSREGISTRY.register("protektor_beretta_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "protektor_beretta_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> PROTEKTOR_BERETTA_1 = SOUNDSREGISTRY.register("protektor_beretta_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "protektor_beretta_1"), 16F));

        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_0 = SOUNDSREGISTRY.register("samurai_edge_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "samurai_edge_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_R = SOUNDSREGISTRY.register("samurai_edge_r", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "samurai_edge_r"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_1 = SOUNDSREGISTRY.register("samurai_edge_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "samurai_edge_1"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_2 = SOUNDSREGISTRY.register("samurai_edge_2", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "samurai_edge_2"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> SAMURAI_EDGE_3 = SOUNDSREGISTRY.register("samurai_edge_3", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "samurai_edge_3"), 16F));

        // Crash TNT
        public static final DeferredHolder<SoundEvent, SoundEvent> PRIME_TNT = SOUNDSREGISTRY.register("prime_tnt", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "prime_tnt"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> FAST_TICK_TNT = SOUNDSREGISTRY.register("fast_tick_tnt", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "fast_tick_tnt"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> TICK_TNT = SOUNDSREGISTRY.register("tick_tnt", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "tick_tnt"), 16F));
        public static final DeferredHolder<SoundEvent, SoundEvent> TNT_BOOM = SOUNDSREGISTRY.register("tnt_boom", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "tnt_boom"), 16F));

			// TNT stick
			public static final DeferredHolder<SoundEvent, SoundEvent> TNT_FUSE = SOUNDSREGISTRY.register("tnt_fuse", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "tnt_fuse"), 16F));
			public static final DeferredHolder<SoundEvent, SoundEvent> TNT_SIZZLE = SOUNDSREGISTRY.register("tnt_sizzle", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "tnt_sizzle"), 16F));


			// Dmg sounds
        public static final DeferredHolder<SoundEvent, SoundEvent> PUNCH = SOUNDSREGISTRY.register("punch", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "punch"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> FLESHRIP_0 = SOUNDSREGISTRY.register("fleshrip_0", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "fleshrip_0"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> FLESHRIP_1 = SOUNDSREGISTRY.register("fleshrip_1", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "fleshrip_1"), 12F));

        // Entity sounds - 16 blocks range
        public static final DeferredHolder<SoundEvent, SoundEvent> SMB_SUPER_FAN_HIT = SOUNDSREGISTRY.register("smb_super_fan_hit", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "smb_super_fan_hit"), 12F));
        public static final DeferredHolder<SoundEvent, SoundEvent> SMB_SUPER_FAN_IDLE = SOUNDSREGISTRY.register("smb_super_fan_idle", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "smb_super_fan_idle"), 6F));

        // Item sounds - 16 blocks range
        public static final DeferredHolder<SoundEvent, SoundEvent> WUMPA_FRUIT = SOUNDSREGISTRY.register("wumpa_fruit", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "wumpa_fruit"), 8F));

        // Music disk
		  public static final DeferredHolder<SoundEvent, SoundEvent> MD_P1NG_P0NG = SOUNDSREGISTRY.register("md_p1ng_p0ng", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("opminecraft", "md_p1ng_p0ng")));

}

