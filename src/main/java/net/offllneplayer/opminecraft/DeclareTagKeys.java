
package net.offllneplayer.opminecraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class DeclareTagKeys {

    public static class Blocks {

        public static final TagKey<Block> CRYING_PAXEL = createTag("mineable/crying_paxel");
        public static final TagKey<Block> CRASH_CRATES = createTag("crash_crates");

        public static final TagKey<Block> CRYING_OBSIDIAN_TRIGGERS = createTag("crying_obsidian_triggers");
        public static final TagKey<Block> LAVAS = createTag("lavas");
        public static final TagKey<Block> WATERS = createTag("waters");
        public static final TagKey<Block> ICES = createTag("ices");
        public static final TagKey<Block> SOUL_FIRES = createTag("soul_fires");

        public static final TagKey<Block> OP_LAMPS = createTag("op_lamps");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, name));
        }
    }
/*--------------------------------------------------------------------------------------------*/
    /*[Block Tags]*/


    public static class Items {

        public static final TagKey<Item> CRYING_ITEMS = createTag("crying_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, name));
        }
    }
/*--------------------------------------------------------------------------------------------*/
    /*[Item Tags]*/
}
