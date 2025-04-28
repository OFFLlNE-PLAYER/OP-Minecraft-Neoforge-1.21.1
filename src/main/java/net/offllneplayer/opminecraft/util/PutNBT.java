package net.offllneplayer.opminecraft.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.offllneplayer.opminecraft.init.RegistryEnchantments;

public class PutNBT {

    public static void writeWeaponDataToBlock(ItemStack itemstack, BlockEntity entity, Level level) {

        CompoundTag data = entity.getPersistentData();
        var enchReg = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);

        data.putDouble("DMG_VALU", itemstack.getDamageValue());
        data.putString("nayme", itemstack.getHoverName().getString());
        data.putInt("bane", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.BANE_OF_ARTHROPODS), itemstack));
        data.putInt("smiite", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SMITE), itemstack));
        data.putInt("sharp", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SHARPNESS), itemstack));
        data.putInt("unbreakin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.UNBREAKING), itemstack));
        data.putInt("lootin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.LOOTING), itemstack));
        data.putInt("sweepin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SWEEPING_EDGE), itemstack));
        data.putInt("firey", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.FIRE_ASPECT), itemstack));
        data.putInt("knickerbocker", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.KNOCKBACK), itemstack));
        data.putInt("mendi", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.MENDING), itemstack));
        data.putInt("vanish", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.VANISHING_CURSE), itemstack));
        data.putInt("tempest", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(RegistryEnchantments.TEMPEST), itemstack));
    }

    public static void writeWeaponDataToEntity(ItemStack itemstack, Entity entity, Level level) {

        CompoundTag data = entity.getPersistentData();
        var enchReg = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);

        data.putDouble("DMG_VALU", itemstack.getDamageValue());
        data.putString("nayme", itemstack.getHoverName().getString());
        data.putInt("bane", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.BANE_OF_ARTHROPODS), itemstack));
        data.putInt("smiite", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SMITE), itemstack));
        data.putInt("sharp", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SHARPNESS), itemstack));
        data.putInt("unbreakin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.UNBREAKING), itemstack));
        data.putInt("lootin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.LOOTING), itemstack));
        data.putInt("sweepin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SWEEPING_EDGE), itemstack));
        data.putInt("firey", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.FIRE_ASPECT), itemstack));
        data.putInt("knickerbocker", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.KNOCKBACK), itemstack));
        data.putInt("mendi", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.MENDING), itemstack));
        data.putInt("vanish", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.VANISHING_CURSE), itemstack));
        data.putInt("tempest", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(RegistryEnchantments.TEMPEST), itemstack));
    }

    public static void writeWeaponDataToItemstack(ItemStack blade, CompoundTag nbt, Level level) {
        var enchReg = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);

        blade.setDamageValue(nbt.getInt("DMG_VALU"));
        String item_nayme = nbt.getString("nayme");
        if (!blade.getHoverName().getString().equals(item_nayme)) blade.set(DataComponents.CUSTOM_NAME, Component.literal(item_nayme));
        if (nbt.getInt("bane") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.BANE_OF_ARTHROPODS), nbt.getInt("bane"));
        if (nbt.getInt("smiite") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.SMITE), nbt.getInt("smiite"));
        if (nbt.getInt("sharp") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.SHARPNESS), nbt.getInt("sharp"));
        if (nbt.getInt("unbreakin") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.UNBREAKING), nbt.getInt("unbreakin"));
        if (nbt.getInt("lootin") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.LOOTING), nbt.getInt("lootin"));
        if (nbt.getInt("sweepin") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.SWEEPING_EDGE), nbt.getInt("sweepin"));
        if (nbt.getInt("firey") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.FIRE_ASPECT), nbt.getInt("firey"));
        if (nbt.getInt("knickerbocker") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.KNOCKBACK), nbt.getInt("knickerbocker"));
        if (nbt.getInt("mendi") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.MENDING), nbt.getInt("mendi"));
        if (nbt.getInt("vanish") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.VANISHING_CURSE), nbt.getInt("vanish"));
        if (nbt.getInt("tempest") > 0) blade.enchant(enchReg.getHolderOrThrow(RegistryEnchantments.TEMPEST), nbt.getInt("tempest"));
    }
}
