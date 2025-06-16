
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.world.inventory.furnaces.*;
import net.offllneplayer.opminecraft.world.inventory.lootchest.*;

public class RegistryInventory {
	public static final DeferredRegister<MenuType<?>> INVREGISTRY = DeferredRegister.create(Registries.MENU, OPMinecraft.Mod_ID);
	
	public static final DeferredHolder<MenuType<?>, MenuType<CopperFurnaceInv>> COPPER_FURNACE_GUI = INVREGISTRY.register("gui_copper_furnace", () -> IMenuTypeExtension.create(CopperFurnaceInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<IronFurnaceInv>> IRON_FURNACE_GUI = INVREGISTRY.register("gui_iron_furnace", () -> IMenuTypeExtension.create(IronFurnaceInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GoldFurnaceInv>> GOLD_FURNACE_GUI = INVREGISTRY.register("gui_gold_furnace", () -> IMenuTypeExtension.create(GoldFurnaceInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<DiamondFurnaceInv>> DIAMOND_FURNACE_GUI = INVREGISTRY.register("gui_diamond_furnace", () -> IMenuTypeExtension.create(DiamondFurnaceInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NetheriteFurnaceInv>> NETHERITE_FURNACE_GUI = INVREGISTRY.register("gui_netherite_furnace", () -> IMenuTypeExtension.create(NetheriteFurnaceInv::new));

	public static final DeferredHolder<MenuType<?>, MenuType<CopperLootChestInv>> COPPER_LOOT_CHEST_GUI = INVREGISTRY.register("gui_copper_loot_chest", () -> IMenuTypeExtension.create(CopperLootChestInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<IronLootChestInv>> IRON_LOOT_CHEST_GUI = INVREGISTRY.register("gui_iron_loot_chest", () -> IMenuTypeExtension.create(IronLootChestInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GoldLootChestInv>> GOLD_LOOT_CHEST_GUI = INVREGISTRY.register("gui_gold_loot_chest", () -> IMenuTypeExtension.create(GoldLootChestInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<DiamondLootChestInv>> DIAMOND_LOOT_CHEST_GUI = INVREGISTRY.register("gui_diamond_loot_chest", () -> IMenuTypeExtension.create(DiamondLootChestInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NetheriteLootChestInv>> NETHERITE_LOOT_CHEST_GUI = INVREGISTRY.register("gui_netherite_loot_chest", () -> IMenuTypeExtension.create(NetheriteLootChestInv::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NLootChestInv>> N_LOOT_CHEST_GUI = INVREGISTRY.register("gui_n_loot_chest", () -> IMenuTypeExtension.create(NLootChestInv::new));
}
