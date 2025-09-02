package net.offllneplayer.opminecraft.items._item._musik_disk;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.OPMinecraft;

public class MD_CANT_GET_YOU_OUT_OF_MY_HEADItem extends Item {
	public MD_CANT_GET_YOU_OUT_OF_MY_HEADItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC)
				.jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "md_cant_get_you_out_of_my_head"))));
	}
}
