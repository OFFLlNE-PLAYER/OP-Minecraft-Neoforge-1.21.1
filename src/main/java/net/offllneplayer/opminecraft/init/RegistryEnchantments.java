package net.offllneplayer.opminecraft.init;

import net.minecraft.core.HolderSet;
import net.minecraft.world.item.enchantment.Enchantments;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.enchantment.TempestEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class RegistryEnchantments {

    public static final ResourceKey<Enchantment> TEMPEST = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "tempest"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantment = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        registerEnchants(context, TEMPEST, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE), 1, 10,
                        Enchantment.dynamicCost(20, 4), Enchantment.dynamicCost(69, 12), 3, EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(HolderSet.direct(
                        enchantment.getOrThrow(Enchantments.SWEEPING_EDGE),
                        enchantment.getOrThrow(Enchantments.CHANNELING)
                ))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new TempestEnchantmentEffect()));
    }

    private static void registerEnchants(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}