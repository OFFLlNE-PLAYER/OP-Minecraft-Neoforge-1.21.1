
package net.offllneplayer.opminecraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.block.ancientchests.AncientChestInv;
import net.offllneplayer.opminecraft.block.furnaces.OPFurnaceInv;

public class RegistryInventory {
	public static final DeferredRegister<MenuType<?>> INVREGISTRY = DeferredRegister.create(Registries.MENU, OPMinecraft.Mod_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<OPFurnaceInv>> OP_FURNACE_GUI =
			INVREGISTRY.register("gui_op_furnace", () -> IMenuTypeExtension.create(OPFurnaceInv::new));

	public static final DeferredHolder<MenuType<?>, MenuType<AncientChestInv>> ANCIENT_CHEST_GUI =
			INVREGISTRY.register("gui_ancient_chest", () -> IMenuTypeExtension.create(AncientChestInv::new));
}
