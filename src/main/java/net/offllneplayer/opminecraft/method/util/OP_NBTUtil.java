package net.offllneplayer.opminecraft.method.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashMap;
import java.util.Map;

    /// *~* OP ARR COPYRIGHT DISCLAIMER *~*
    /// --------------------------------------------------
    /// ~ USE OF THIS CODE IS EXPLICITLY PROHIBITED WITHOUT DIRECT PERMISSION.
    /// ~ COPY OR _ANY_USE_ OF THIS CODE GOES AGAINST COPYRIGHT.
    /// ~ YOU SHOULD CLOSE THIS HAVING ACKNOWLEDGED THAT.
    /// ~ SUPPORT/ENJOY MY MOD!
    /// ---------------------------------------------------
    /// *~* OP ARR COPYRIGHT DISCLAIMER *~*

public class OP_NBTUtil {

     /*whataf***ingpaininmya$$thisonewas*/
    /**/
   /*[WeaponData Record]*/
    public record WeaponData(String itemName, int dmgValue, Map<Enchantment,Integer> enchants) {}


    /*x-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>o*/
	/*[read WeaponData -> Entity Class]*/
    public static WeaponData readItemStacktoClass(CompoundTag tag, Level level) {
        String name = tag.getString("nayme");
        int dmg = tag.getInt("DMG_VALU");

        var registry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Map<Enchantment,Integer> map = new HashMap<>();

        for (String key : tag.getAllKeys()) {
            if (key.equals("nayme") || key.equals("DMG_VALU")) continue;
            int lvl = tag.getInt(key);
            if (lvl <= 0) continue;

        // Try full ResourceLocation first
            ResourceLocation rl = ResourceLocation.tryParse(key);

        // If key is just a path, try with "minecraft" namespace
            if (rl == null && !key.contains(":")) {
                rl = ResourceLocation.tryParse("minecraft:" + key);
            }

            if (rl == null) continue;

            var rkey = ResourceKey.create(Registries.ENCHANTMENT, rl);
            registry.getHolder(rkey).ifPresent(h -> map.put(h.value(), lvl));
        }

        return new WeaponData(name, dmg, map);
    }

      /*x-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>ox-=>o*/
	  /* - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] */
    /*[write WeaponData -> BlockEntity]*/
    public static void writeItemStackNBTToBlock(ItemStack stack, BlockEntity be) {
        CompoundTag data = be.getPersistentData();

        data.putString("nayme",    stack.getHoverName().getString());
        data.putInt   ("DMG_VALU", stack.getDamageValue());

        var enchants = stack.getComponents().get(DataComponents.ENCHANTMENTS);
        for (var entry : enchants.entrySet()) {
            entry.getKey().unwrapKey().ifPresent(key ->
                    data.putInt(key.location().toString(), entry.getIntValue())
            );
        }
    }


     /* - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] */
    /*-~>-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o-~>o*/
        /*[enchant WeaponData -> itemStack]*/
    public static void enchantWeaponDataToItemstack(ItemStack stack, CompoundTag tag, Level level) {
    // restore custom name
        String name = tag.getString("nayme");
        if (!stack.getHoverName().getString().equals(name)) {
            stack.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        }

    // restore damage
        stack.setDamageValue(tag.getInt("DMG_VALU"));

    // lookup each saved enchant by ID and re-apply
        var registry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);

        for (String key : tag.getAllKeys()) {
        if (key.equals("nayme") || key.equals("DMG_VALU")) continue;
        int lvl = tag.getInt(key);
        if (lvl <= 0) continue;

        // Try full ResourceLocation first
        ResourceLocation rl = ResourceLocation.tryParse(key);

        // If key is just a path, try with "minecraft" namespace
        if (rl == null && !key.contains(":")) {
            rl = ResourceLocation.tryParse("minecraft:" + key);
        }

        if (rl == null) continue;

        ResourceKey<Enchantment> rkey = ResourceKey.create(Registries.ENCHANTMENT, rl);
        registry.getHolder(rkey).ifPresent(holder -> stack.enchant(holder, lvl));
        }
    }


/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
}
