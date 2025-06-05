
package net.offllneplayer.opminecraft.UTIL;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.offllneplayer.opminecraft.OPMinecraft;


public class OP_TagKeyUtil {

    /* - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] */
   /*[Block Tags]*/
    public static class Blocks {

        public static final TagKey<Block> CRYING_PAXEL = createTag("mineable/crying_paxel");
        public static final TagKey<Block> CRYING_SWHOPAXEL = createTag("mineable/crying_swhopaxel");

        public static final TagKey<Block> CRASH_CRATES = createTag("crash_crates");

        public static final TagKey<Block> CRYING_OBSIDIAN_TRIGGERS = createTag("crying_obsidian_triggers");
        public static final TagKey<Block> LAVAS = createTag("lavas");
        public static final TagKey<Block> WATERS = createTag("waters");
        public static final TagKey<Block> ICES = createTag("ices");
        public static final TagKey<Block> SOUL_FIRES = createTag("soul_fires");

        public static final TagKey<Block> OP_LAMPS = createTag("op_lamps");

        public static final TagKey<Block> BULLET_FRAGILE = createTag("bullet_fragile");
        public static final TagKey<Block> SWORD_NO_STICK = createTag("sword_no_stick");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, name));
        }
    }


     /* - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] - [ ] */
    /* ___I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I____I___*/
   /*[Item Tags]*/
    public static class Items {

         public static final TagKey<Item> MIXIN_ROTATE_ITEMS = createTag("mixin_rotate_items");

        public static final TagKey<Item> CRYING_ITEMS = createTag("crying_items");

         public static final TagKey<Item> GUNBLADES = createTag("gunblades");

         public static final TagKey<Item> OP_SWORDS = createTag("op_swords");
         public static final TagKey<Item> VANILLA_SW0RDS = createTag("vanilla_sw0rds");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, name));
        }
    }


     /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____ENT____*/
   /*[Entity Tags]*/
    public static class Entities {

        public static final TagKey<EntityType<?>> IMPACT_PROJECTILES = createTag("impact_projectiles");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, name));
        }
    }
}
