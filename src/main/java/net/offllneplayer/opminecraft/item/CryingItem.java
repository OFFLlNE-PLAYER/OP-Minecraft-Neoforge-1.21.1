
package net.offllneplayer.opminecraft.item;

import com.google.common.collect.Iterables;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.method.crying.armor.FullCrying_Method;

import java.util.EnumMap;
import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public abstract class CryingItem extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(Type.class), map -> {
				map.put(Type.BOOTS, 3);
				map.put(Type.LEGGINGS, 6);
				map.put(Type.CHESTPLATE, 8);
				map.put(Type.HELMET, 3);
				map.put(Type.BODY, 7);
			}), 20, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("item.armor.equip_netherite")),
					() -> Ingredient.of(new ItemStack(RegistryBIBI.CRYING_INGOT.get())),
					List.of(new ArmorMaterial.Layer(ResourceLocation.parse("opminecraft:crying_armor"))), 1f, -0.1f);
			registerHelper.register(ResourceLocation.parse("opminecraft:crying"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	public CryingItem(Type type, Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

		public static class Helmet extends CryingItem {
			public Helmet() {
				super(Type.HELMET, new Properties().durability(Type.HELMET.getDurability(40)).rarity(Rarity.EPIC));
			}

			@Override
			public void inventoryTick(ItemStack itemstack, Level menu, Entity entity, int slot, boolean selected) {
				super.inventoryTick(itemstack, menu, entity, slot, selected);
				if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
					FullCrying_Method.execute(menu, entity.getX(), entity.getY(), entity.getZ(), entity);
				}
			}
		}

		public static class Chestplate extends CryingItem {
			public Chestplate() {
				super(Type.CHESTPLATE, new Properties().durability(Type.CHESTPLATE.getDurability(40)).rarity(Rarity.EPIC));
			}
		}

		public static class Leggings extends CryingItem {
			public Leggings() {
				super(Type.LEGGINGS, new Properties().durability(Type.LEGGINGS.getDurability(40)).rarity(Rarity.EPIC));
			}
		}

		public static class Boots extends CryingItem {
			public Boots() {
				super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(40)).rarity(Rarity.EPIC));
			}
		}
}
