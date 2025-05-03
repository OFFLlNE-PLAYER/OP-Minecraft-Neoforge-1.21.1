package net.offllneplayer.opminecraft.util;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashMap;
import java.util.Map;


public class PutNBT {
    /*--------------------------------------------------------------------------------------------*/
    /*[read WeaponData -> Entity Class]*/
    public record WeaponData(String itemName, int dmgValue, Map<Enchantment, Integer> enchantments) {}

    public static WeaponData readWeaponData(CompoundTag data, Level level) {
        String name = data.getString("nayme");
        int dmg = data.getInt("DMG_VALU");

        Map<Enchantment, Integer> enchs = new HashMap<>();
        Registry<Enchantment> reg   = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);

        // iterate *all* keys in the tag except our two fixed fields
        for (String key : data.getAllKeys()) {
            if (key.equals("nayme") || key.equals("DMG_VALU")) continue;
            // try to parse it as an enchantment ID
            ResourceLocation rl = ResourceLocation.tryParse(key);
            if (rl == null) continue;

            ResourceKey<Enchantment> rkey = ResourceKey.create(Registries.ENCHANTMENT, rl);
            reg.getHolder(rkey).ifPresent(holder -> {
                int lvl = data.getInt(key);
                if (lvl > 0) enchs.put(holder.value(), lvl);
            });
        }
        return new WeaponData(name, dmg, enchs);
    }

    /*--------------------------------------------------------------------------------------------*/
    /*[write WeaponData -> Block]*/
    public static void writeWeaponDataToBlock(ItemStack stack, BlockEntity be, Level level) {
        CompoundTag data = be.getPersistentData();

        data.putString("nayme",    stack.getHoverName().getString());
        data.putInt   ("DMG_VALU", stack.getDamageValue());

        for (Map.Entry<Holder<Enchantment>, Integer> entry : stack.getTagEnchantments().entrySet()) {
            int lvl = entry.getValue();
            if (lvl <= 0) continue;

            entry.getKey().unwrapKey().ifPresent(key -> data.putInt(key.location().toString(), lvl));
        }
    }

    /*--------------------------------------------------------------------------------------------*/
    /*[enchant WeaponData -> itemStack]*/
    public static void enchantWeaponDataToItemstack(ItemStack stack, CompoundTag nbt, Level level) {

        String name = nbt.getString("nayme");
        if (!stack.getHoverName().getString().equals(name)) {
            stack.set(DataComponents.CUSTOM_NAME, net.minecraft.network.chat.Component.literal(name));
        }
        stack.setDamageValue(nbt.getInt("DMG_VALU"));

        // restore every enchant we wrote
        Registry<Enchantment> reg = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        for (String key : nbt.getAllKeys()) {
            if (key.equals("nayme") || key.equals("DMG_VALU")) continue;
            ResourceLocation rl = ResourceLocation.tryParse(key);
            if (rl == null) continue;

            ResourceKey<Enchantment> rkey = ResourceKey.create(Registries.ENCHANTMENT, rl);
            reg.getHolder(rkey).ifPresent(holder -> {
                int lvl = nbt.getInt(key);
                if (lvl > 0) stack.enchant(holder, lvl);
            });
        }
    }
}