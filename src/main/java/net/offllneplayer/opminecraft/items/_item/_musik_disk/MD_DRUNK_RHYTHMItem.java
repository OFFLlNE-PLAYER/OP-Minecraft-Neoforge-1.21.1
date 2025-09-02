package net.offllneplayer.opminecraft.items._item._musik_disk;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.offllneplayer.opminecraft.OPMinecraft;

public class MD_DRUNK_RHYTHMItem extends Item {
	public MD_DRUNK_RHYTHMItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON)
				.jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "md_drunk_rhythm"))));
	}
}
