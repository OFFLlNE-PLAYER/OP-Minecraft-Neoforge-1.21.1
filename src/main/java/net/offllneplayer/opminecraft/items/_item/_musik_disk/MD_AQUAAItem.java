package net.offllneplayer.opminecraft.items._item._musik_disk;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.OPMinecraft;

public class MD_AQUAAItem extends Item {
	public MD_AQUAAItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.RARE)
				.jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "md_aquaa"))));
	}
}
