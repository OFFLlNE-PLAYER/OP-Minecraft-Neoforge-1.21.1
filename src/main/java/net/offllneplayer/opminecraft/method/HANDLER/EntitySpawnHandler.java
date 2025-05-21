package net.offllneplayer.opminecraft.method.HANDLER;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.offllneplayer.opminecraft.init.RegistryEnchantments;
import net.offllneplayer.opminecraft.init.RegistryBIBI;


import java.util.Random;

@EventBusSubscriber
public class EntitySpawnHandler {
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();

        if (!(entity instanceof Mob mob)) {
            return;
        }

        if (mob instanceof Villager) {
            mob.setCanPickUpLoot(true);
            return;
        }

        if (!(mob instanceof Zombie || mob instanceof ZombieVillager || mob instanceof Husk || mob instanceof Drowned ||
                mob instanceof Skeleton || mob instanceof WitherSkeleton || mob instanceof Stray ||
                mob instanceof Piglin || mob instanceof PiglinBrute || mob instanceof ZombifiedPiglin ||
                mob instanceof Pillager || mob instanceof Vindicator)) {
            return;
        }
        mob.setCanPickUpLoot(true);


        // --- Weapon Equipping ---
        boolean allowHighTiers = !level.dimension().equals(Level.OVERWORLD) && RANDOM.nextBoolean();
        ItemStack weapon = getRandomVanillaWeapon(allowHighTiers);

        if (RANDOM.nextInt(4) == 0) {  // 25% chance for a primary weapon.

            if (mob instanceof PiglinBrute) { weapon = getBruteAxe();
            } else if (mob instanceof Vindicator) { weapon = new ItemStack(Items.DIAMOND_AXE);
            }

        }else // WitherSkeletons roll a 25% chance to receive a bow
            if (mob instanceof WitherSkeleton && RANDOM.nextInt(4) == 0) {
                weapon = new ItemStack(Items.BOW);
        }else // 25% chance for a special item on non-piglin zombies
            if ((mob instanceof Zombie) && !(mob instanceof ZombifiedPiglin) && RANDOM.nextInt(4) == 0) {
                weapon = getRandomZombieItem(mob);
        }else  // Drowned: 10% chance to receive a trident.
            if (mob instanceof Drowned && RANDOM.nextInt(10) == 0) {
                weapon = new ItemStack(Items.TRIDENT);
        }

        if (RANDOM.nextBoolean()) {  // 50% chance to add enchantments.
            applyWeaponEnchantments(level, weapon);
        }
        mob.setItemInHand(InteractionHand.MAIN_HAND, weapon);

        // --- Armor Equipping ---
        equipRandomArmor(mob, level);

    // --- Zombified Piglin Off-hand ---
        if (mob instanceof ZombifiedPiglin && RANDOM.nextInt(420) == 0) mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.NETHERITE_SCRAP));

    }

/*--------------------------------------------------------------------------------------------*/
    /*[Weapons]*/
    private static ItemStack getBruteAxe() {
        int pick = RANDOM.nextInt(5);
        return switch (pick) {
            case 0 -> new ItemStack(Items.DIAMOND_AXE);
            case 1 -> new ItemStack(Items.NETHERITE_AXE);
            case 2 -> new ItemStack(RegistryBIBI.CRYING_AXE.get());
            case 3 -> new ItemStack(RegistryBIBI.CRYING_SWHOPAXEL.get());
            case 4 -> new ItemStack(RegistryBIBI.CRYING_PAXEL.get());
            default -> new ItemStack(Items.DIAMOND_AXE);
        };
    }

    private static final Item[][] WEAPONZ = {
            {Items.WOODEN_AXE, Items.WOODEN_SWORD},
            {Items.STONE_AXE, Items.STONE_SWORD},
            {Items.IRON_AXE, Items.IRON_SWORD},
            {Items.GOLDEN_AXE, Items.GOLDEN_SWORD},
            {Items.DIAMOND_AXE, Items.DIAMOND_SWORD},
            {Items.NETHERITE_AXE, Items.NETHERITE_SWORD}
    };

    private static ItemStack getRandomVanillaWeapon(boolean allowHighTiers) {
        int maxTier = allowHighTiers ? 5 : 3;
        int tier    = RANDOM.nextInt(maxTier + 1);
        if (tier >= 3 && allowHighTiers) tier = RANDOM.nextInt(maxTier + 1);

        Item[] pair = WEAPONZ[tier];
        return new ItemStack(RANDOM.nextBoolean() ? pair[0] : pair[1]);
    }

    // Applies weapon enchantments.
    private static void applyWeaponEnchantments(Level level, ItemStack weapon) {

        Item witem = weapon.getItem();
        boolean isSword = (witem instanceof SwordItem || witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/sword"))));
        boolean isAxeLike = (witem instanceof AxeItem);


        if (isSword) {
            if (RANDOM.nextInt(40) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.FIRE_ASPECT, 2);
            if (RANDOM.nextInt(40) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.LOOTING, 2);
            if (RANDOM.nextInt(20) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.KNOCKBACK, 2);
            if (RANDOM.nextInt(20) == 4) applyRandomLevelEnchant(level, weapon, Enchantments.SWEEPING_EDGE, 3);
        }

        if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/mining")))) {
            if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.EFFICIENCY, 5);
        }

        if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/mining_loot")))) {
            boolean fortune = (RANDOM.nextInt(40) == 0);
            boolean silk = (RANDOM.nextInt(40) == 0);
            if (fortune && silk) {
                if (RANDOM.nextBoolean()) {
                    silk = false;
                } else {
                    fortune = false;
                }
            }
            if (fortune) {
                applyRandomLevelEnchant(level, weapon, Enchantments.FORTUNE, 2);
            } else if (silk) applyRandomLevelEnchant(level, weapon, Enchantments.SILK_TOUCH, 1);
        }
        
        if (witem == Items.TRIDENT) {
            if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.IMPALING, 5);
            if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.UNBREAKING, 3);
            if (RANDOM.nextInt(1000) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.MENDING, 1);

            if (RANDOM.nextInt(40) == 0) {
                applyRandomLevelEnchant(level, weapon, Enchantments.LOYALTY, 3);
            } else if (RANDOM.nextInt(40) == 0) applyRandomLevelEnchant(level, weapon, Enchantments.RIPTIDE, 3);

            if (RANDOM.nextInt(20) == 0) {
                applyRandomLevelEnchant(level, weapon, Enchantments.CHANNELING, 1);
            } else if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, weapon, RegistryEnchantments.TEMPEST, 3);
        }
        
        if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/sharp_weapon")))) {
            if (RANDOM.nextInt(4) == 0) {
                switch (RANDOM.nextInt(3)) {
                    case 0 -> applyRandomLevelEnchant(level, weapon, Enchantments.SHARPNESS, 5);
                    case 1 -> applyRandomLevelEnchant(level, weapon, Enchantments.SMITE, 5);
                    case 2 -> applyRandomLevelEnchant(level, weapon, Enchantments.BANE_OF_ARTHROPODS, 5);
                }
            }
        }
        
        if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/durability")))) {
            if (RANDOM.nextInt(4) == 0) {
                applyRandomLevelEnchant(level, weapon, Enchantments.UNBREAKING, 3);
            }
            if (RANDOM.nextInt(1000) == 4) {
                applyRandomLevelEnchant(level, weapon, Enchantments.MENDING, 1);
            }
        }
        
        if (witem.builtInRegistryHolder().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/weapon")))) {
            if (RANDOM.nextInt(20) == 4) applyRandomLevelEnchant(level, weapon, RegistryEnchantments.TEMPEST, 1);
        }
    }

    private static ItemStack getRandomZombieItem(Mob mob) {
        for (int attempt = 0; attempt < 4; attempt++) {
            Item miscItem = switch (RANDOM.nextInt(10)) {
                case 0 -> Items.ICE; case 1 -> Items.PACKED_ICE; case 2 -> Items.BLUE_ICE;
                case 3 -> Items.BLAZE_POWDER; case 4 -> Items.LAVA_BUCKET;
                case 5 ->  Items.SPIDER_EYE; case 6 -> Items.POISONOUS_POTATO;
                case 7 -> Items.FIRE_CHARGE; case 8 -> Items.WIND_CHARGE;
                case 9 -> RegistryBIBI.AKU_AKU_MASK.get();
                default -> Items.POISONOUS_POTATO;
            };

            if (mob instanceof Drowned &&
                    miscItem == Items.BLAZE_POWDER || miscItem == Items.FIRE_CHARGE || miscItem == Items.LAVA_BUCKET) continue;
            if (mob instanceof Husk
                    && miscItem == Items.ICE || miscItem == Items.PACKED_ICE || miscItem == Items.BLUE_ICE) continue;
            return new ItemStack(miscItem);
        }
        return new ItemStack(Items.POISONOUS_POTATO);
    }


/*--------------------------------------------------------------------------------------------*/
    /*[Armor]*/
private static final String[] MATERIALS = {"leather", "chainmail", "iron", "golden", "diamond", "netherite"};
    private static final EquipmentSlot[] SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final String[] SUFFIXES = {"helmet", "chestplate", "leggings", "boots"};
    private static final Item[][] ARMOR_TABLE = new Item[4][6];

    static {
        for (int i = 0; i < 4; i++) {
            for (int t = 0; t < 6; t++) {
                ResourceLocation rl = ResourceLocation.fromNamespaceAndPath("minecraft", MATERIALS[t] + "_" + SUFFIXES[i]);
                ARMOR_TABLE[i][t] = BuiltInRegistries.ITEM.get(rl);
            }
        }
    }

    private static void equipRandomArmor(Mob mob, Level level) {
        boolean allowHigh = mob instanceof PiglinBrute
                || (!level.dimension().equals(Level.OVERWORLD) && RANDOM.nextBoolean());

        for (int i = 0; i < SLOTS.length; i++) {
            if (RANDOM.nextInt(10) != 0) continue;

            int maxTier = allowHigh ? MATERIALS.length - 1 : 3;
            int tier    = RANDOM.nextInt(maxTier + 1);
            if (tier >= 4) tier = RANDOM.nextInt(maxTier + 1);

            Item chosen = ARMOR_TABLE[i][tier];
            if (chosen == null) continue;

            ItemStack stack = new ItemStack(chosen);
            if (RANDOM.nextBoolean()) {
                applyArmorEnchantments(level, stack, SLOTS[i]);
                mob.setItemSlot(SLOTS[i], stack);
            }
        }
    }

    // Applies armor enchantments, including a 50% chance roll before selecting one protection enchantment.
    private static void applyArmorEnchantments(Level level, ItemStack armor, EquipmentSlot slot) {

        if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, armor, Enchantments.UNBREAKING, 3);
        if (RANDOM.nextInt(1000) == 0) applyRandomLevelEnchant(level, armor, Enchantments.MENDING, 1);

        if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, armor, Enchantments.THORNS, 3);

        if (RANDOM.nextInt(6) == 0) {
            switch (RANDOM.nextInt(4)) {
                case 0 -> applyRandomLevelEnchant(level, armor, Enchantments.PROTECTION, 4);
                case 1 -> applyRandomLevelEnchant(level, armor, Enchantments.PROJECTILE_PROTECTION, 4);
                case 2 -> applyRandomLevelEnchant(level, armor, Enchantments.BLAST_PROTECTION, 4);
                case 3 -> applyRandomLevelEnchant(level, armor, Enchantments.FIRE_PROTECTION, 4);
            }
        }
        // Slot-specific enchantments.
        switch (slot) {
            case HEAD:
                if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, armor, Enchantments.AQUA_AFFINITY, 1);
                if (RANDOM.nextInt(20) == 0) applyRandomLevelEnchant(level, armor, Enchantments.RESPIRATION, 3);
                break;
            case CHEST:
                break;
            case LEGS:
                if (RANDOM.nextInt(1000) == 0) applyRandomLevelEnchant(level, armor, Enchantments.SWIFT_SNEAK, 3);
                break;
            case FEET:
                // roll a 1% chance to apply Frost Walker (rare), otherwise apply Depth Strider.
                if (RANDOM.nextInt(100) == 0) {
                    applyRandomLevelEnchant(level, armor, Enchantments.FROST_WALKER, 2);
                } else if (RANDOM.nextInt(10) == 0) applyRandomLevelEnchant(level, armor, Enchantments.DEPTH_STRIDER, 3);

                if (RANDOM.nextInt(40) == 0) applyRandomLevelEnchant(level, armor, Enchantments.FEATHER_FALLING, 4);
                break;

            default: break;
        }
    }

/*--------------------------------------------------------------------------------------------*/
    /*[Handle Enchanting]*/
    private static void applyRandomLevelEnchant(Level level, ItemStack stack, ResourceKey<net.minecraft.world.item.enchantment.Enchantment> enchantKey, int maxLevel) {
        Holder<net.minecraft.world.item.enchantment.Enchantment> holder =
            level.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(enchantKey).orElseThrow(() -> new IllegalStateException("Enchantment not found: " + enchantKey.location()));
        int chosenLevel = 1 + RANDOM.nextInt(maxLevel);
        stack.enchant(holder, chosenLevel);
    }
}
