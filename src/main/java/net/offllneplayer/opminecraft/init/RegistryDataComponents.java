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
    /*[Gunblade]*/
    public static final DeferredHolder<DataComponentType<?>,
            DataComponentType<Long>> GUNBLADE_LAST_HIT_TIME =
                DATA_COMPONENTS.register("gunblade_last_hit_time",
                    () -> DataComponentType.<Long>builder().persistent(Codec.LONG).build());

}
