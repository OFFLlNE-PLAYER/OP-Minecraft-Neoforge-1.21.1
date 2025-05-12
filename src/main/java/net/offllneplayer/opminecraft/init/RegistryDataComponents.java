package net.offllneplayer.opminecraft.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegistryDataComponents {

/*-----------------------------------------------------------------------------------------------------------------------*/
    /*[Declare Registry]*/
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, "opminecraft");

    /*-----------------------------------------------------------------------------------------------------------------------*/
    /*[TNT]*/
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TNT_LIT_TIMER =
            DATA_COMPONENTS.register("tnt_lit_timer",
                    () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> TNT_SPIN_ROTATION =
            DATA_COMPONENTS.register("tnt_spin_rotation",
                    () -> DataComponentType.<Float>builder().persistent(Codec.FLOAT).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> TNT_GROUNDED_zROTATION =
            DATA_COMPONENTS.register("tnt_grounded_zrotation",
                    () -> DataComponentType.<Float>builder().persistent(Codec.FLOAT).build());

/*-----------------------------------------------------------------------------------------------------------------------*/
    /*[Gunblade]*/
    public static final DeferredHolder<DataComponentType<?>,
            DataComponentType<Long>> GUNBLADE_LAST_HIT_TIME =
                DATA_COMPONENTS.register("gunblade_last_hit_time",
                    () -> DataComponentType.<Long>builder().persistent(Codec.LONG).build());

}
