package net.offllneplayer.opminecraft.init;

import com.mojang.serialization.MapCodec;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.offllneplayer.opminecraft.enchantment.TempestEnchantmentEffect;

import java.util.function.Supplier;

public class RegistryEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, OPMinecraft.Mod_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> TEMPEST =
            registerEnchantmentEffect("tempest", TempestEnchantmentEffect.CODEC);

    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>>
        registerEnchantmentEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return ENTITY_ENCHANTMENT_EFFECTS.register(name, () -> codec);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
