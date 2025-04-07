package net.offllneplayer.opminecraft.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistryEnchantments;

import java.util.Random;

public class EntitySpawnHandler {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();

        if (!(entity instanceof Mob mob)) {
            return;
        }

        // Villagers: only pickup ability.
        if (mob instanceof Villager) {
            mob.setCanPickUpLoot(true);
            return;
        }

        // Process only targeted mobs:
        // Eligible: Zombie, ZombieVillager, Husk, Skeleton, WitherSkeleton, Piglin, PiglinBrute,
        // ZombifiedPiglin, Stray, Drowned, Pillager, Vindicator.
        if (!(mob instanceof Zombie || mob instanceof ZombieVillager || mob instanceof Husk ||
                mob instanceof Skeleton || mob instanceof WitherSkeleton || mob instanceof Piglin ||
                mob instanceof PiglinBrute || mob instanceof ZombifiedPiglin || mob instanceof Stray ||
                mob instanceof Drowned || mob instanceof Pillager || mob instanceof Vindicator)) {
            return;
        }
        mob.setCanPickUpLoot(true);

        // Precompute group memberships.
        boolean isPiglinBrute = mob instanceof PiglinBrute;
        boolean isVindicator = mob instanceof Vindicator;
        boolean isDrowned = mob instanceof Drowned;
        boolean isZombieLike = (mob instanceof Zombie) && !(mob instanceof ZombifiedPiglin);
        // Randomized armor conversion group: Zombies, ZombieVillagers, Skeletons, Piglins, Strays,
        // Drowned, Pillagers, and Husks (but not ZombifiedPiglins).
        boolean useRandomizedArmorConversion = (mob instanceof Zombie
                || mob instanceof Skeleton
                || mob instanceof Piglin
                || mob instanceof Stray
                || mob instanceof Pillager)
                && !(mob instanceof ZombifiedPiglin);

        // --- Weapon Assignment ---
        if (RANDOM.nextInt(4) == 0) {  // 25% chance for primary weapon.
            ItemStack weapon;
            if (isPiglinBrute) {
                // Piglin Brutes get their brute axe (raw, no conversion).
                weapon = getBruteAxe();
            } else if (isVindicator) {
                // Vindicators receive a Diamond Axe.
                weapon = new ItemStack(Items.DIAMOND_AXE);
            } else if (isDrowned) {
                // Drowned (and others) receive a random vanilla weapon processed through our randomized replacement.
                weapon = replaceNetheriteWithRandomWeapon(level, getRandomVanillaWeapon());
            } else {
                weapon = replaceNetheriteWithRandomWeapon(level, getRandomVanillaWeapon());
            }
            if (RANDOM.nextBoolean()) {  // 50% chance to add enchantments.
                applyWeaponEnchantments(level, weapon);
            }
            mob.setItemInHand(InteractionHand.MAIN_HAND, weapon);
        }
        // New branch: WitherSkeletons roll a 25% chance to receive a bow.
        else if (mob instanceof WitherSkeleton && RANDOM.nextInt(4) == 0) {
            ItemStack bow = new ItemStack(Items.BOW);
            mob.setItemInHand(InteractionHand.MAIN_HAND, bow);
        }
        // Fallback for Zombie-like group: 25% chance for a special item.
        else if (isZombieLike && RANDOM.nextInt(4) == 0) {
            ItemStack special = getRandomZombieItem(mob);
            mob.setItemInHand(InteractionHand.MAIN_HAND, special);
        }
        // Alternate fallback for Drowned: 10% chance to receive a trident.
        else if (isDrowned && RANDOM.nextInt(10) == 0) {
            ItemStack trident = new ItemStack(Items.TRIDENT);
            if (RANDOM.nextBoolean()) {
                applyTridentEnchantments(level, trident);
            }
            mob.setItemInHand(InteractionHand.MAIN_HAND, trident);
        }
        // (No main-hand fallback for ZombifiedPiglins.)

        // --- Armor Equipping ---
        equipRandomArmor(mob, level);

        // --- Independent Off-hand Assignment ---
        // For ZombifiedPiglins: 1 in 200 chance to receive Netherite Scrap in the off-hand.
        if (mob instanceof ZombifiedPiglin && RANDOM.nextInt(200) == 0) {
            mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.NETHERITE_SCRAP));
        }
    }

    // Converts Netherite items to their Diamond equivalents.
    private ItemStack replaceNetheriteWithDiamond(Level level, ItemStack stack) {
        if (stack.getItem() == Items.NETHERITE_AXE) {
            return new ItemStack(Items.DIAMOND_AXE, stack.getCount());
        } else if (stack.getItem() == Items.NETHERITE_SWORD) {
            return new ItemStack(Items.DIAMOND_SWORD, stack.getCount());
        } else if (stack.getItem() == Items.NETHERITE_HELMET) {
            return new ItemStack(Items.DIAMOND_HELMET, stack.getCount());
        } else if (stack.getItem() == Items.NETHERITE_CHESTPLATE) {
            return new ItemStack(Items.DIAMOND_CHESTPLATE, stack.getCount());
        } else if (stack.getItem() == Items.NETHERITE_LEGGINGS) {
            return new ItemStack(Items.DIAMOND_LEGGINGS, stack.getCount());
        } else if (stack.getItem() == Items.NETHERITE_BOOTS) {
            return new ItemStack(Items.DIAMOND_BOOTS, stack.getCount());
        }
        return stack;
    }

    // For weapons: if the item is Netherite, randomly select a replacement candidate.
    private ItemStack replaceNetheriteWithRandomWeapon(Level level, ItemStack stack) {
        if (stack.getItem() == Items.NETHERITE_AXE &&
                (level.dimension().equals(Level.OVERWORLD) || RANDOM.nextBoolean())) {
            Item[] candidates = {Items.DIAMOND_AXE, Items.GOLDEN_AXE, Items.IRON_AXE, Items.STONE_AXE, Items.WOODEN_AXE};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.NETHERITE_SWORD &&
                (level.dimension().equals(Level.OVERWORLD) || RANDOM.nextBoolean())) {
            Item[] candidates = {Items.DIAMOND_SWORD, Items.GOLDEN_SWORD, Items.IRON_SWORD, Items.STONE_SWORD, Items.WOODEN_SWORD};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        // For Netherite armor items.
        if (stack.getItem() == Items.NETHERITE_HELMET &&
                (level.dimension().equals(Level.OVERWORLD) || RANDOM.nextBoolean())) {
            Item[] candidates = {Items.DIAMOND_HELMET, Items.IRON_HELMET, Items.CHAINMAIL_HELMET, Items.GOLDEN_HELMET};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.NETHERITE_CHESTPLATE &&
                (level.dimension().equals(Level.OVERWORLD) || RANDOM.nextBoolean())) {
            Item[] candidates = {Items.DIAMOND_CHESTPLATE, Items.IRON_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, Items.GOLDEN_CHESTPLATE};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.NETHERITE_LEGGINGS &&
                (level.dimension().equals(Level.OVERWORLD) || RANDOM.nextBoolean())) {
            Item[] candidates = {Items.DIAMOND_LEGGINGS, Items.IRON_LEGGINGS, Items.CHAINMAIL_LEGGINGS, Items.GOLDEN_LEGGINGS};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.NETHERITE_BOOTS &&
                (level.dimension().equals(Level.OVERWORLD) || RANDOM.nextBoolean())) {
            Item[] candidates = {Items.DIAMOND_BOOTS, Items.IRON_BOOTS, Items.CHAINMAIL_BOOTS, Items.GOLDEN_BOOTS};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        return stack;
    }

    // For armor: if the item is Diamond or Netherite, randomly select a replacement candidate.
    private ItemStack replaceDiamondAndNetheriteWithRandomArmor(ItemStack stack) {
        if (stack.getItem() == Items.DIAMOND_HELMET || stack.getItem() == Items.NETHERITE_HELMET) {
            Item[] candidates = {Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET, Items.IRON_HELMET, Items.GOLDEN_HELMET};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.DIAMOND_CHESTPLATE || stack.getItem() == Items.NETHERITE_CHESTPLATE) {
            Item[] candidates = {Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, Items.IRON_CHESTPLATE, Items.GOLDEN_CHESTPLATE};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.DIAMOND_LEGGINGS || stack.getItem() == Items.NETHERITE_LEGGINGS) {
            Item[] candidates = {Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS, Items.IRON_LEGGINGS, Items.GOLDEN_LEGGINGS};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        if (stack.getItem() == Items.DIAMOND_BOOTS || stack.getItem() == Items.NETHERITE_BOOTS) {
            Item[] candidates = {Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS, Items.IRON_BOOTS, Items.GOLDEN_BOOTS};
            int index = RANDOM.nextInt(candidates.length);
            return new ItemStack(candidates[index], stack.getCount());
        }
        return stack;
    }

    // Returns one of several brute axes.
    private ItemStack getBruteAxe() {
        int pick = RANDOM.nextInt(5);
        switch (pick) {
            case 0: return new ItemStack(Items.DIAMOND_AXE);
            case 1: return new ItemStack(Items.NETHERITE_AXE);
            case 2: return new ItemStack(RegistryIBBI.CRYING_AXE.get());
            case 3: return new ItemStack(RegistryIBBI.CRYING_SICKLE.get());
            case 4: return new ItemStack(RegistryIBBI.CRYING_PAXEL.get());
            default: return new ItemStack(Items.DIAMOND_AXE);
        }
    }

    // Returns a random vanilla weapon (sword or axe) from one of six tiers.
    private ItemStack getRandomVanillaWeapon() {
        int tier = RANDOM.nextInt(6);
        boolean isAxe = RANDOM.nextBoolean();
        switch (tier) {
            case 0: return isAxe ? new ItemStack(Items.WOODEN_AXE) : new ItemStack(Items.WOODEN_SWORD);
            case 1: return isAxe ? new ItemStack(Items.STONE_AXE) : new ItemStack(Items.STONE_SWORD);
            case 2: return isAxe ? new ItemStack(Items.IRON_AXE) : new ItemStack(Items.IRON_SWORD);
            case 3: return isAxe ? new ItemStack(Items.DIAMOND_AXE) : new ItemStack(Items.DIAMOND_SWORD);
            case 4: return isAxe ? new ItemStack(Items.NETHERITE_AXE) : new ItemStack(Items.NETHERITE_SWORD);
            case 5: return isAxe ? new ItemStack(Items.GOLDEN_AXE) : new ItemStack(Items.GOLDEN_SWORD);
            default: return ItemStack.EMPTY;
        }
    }

    // Applies weapon enchantments.
    private void applyWeaponEnchantments(Level level, ItemStack weapon) {
        if (RANDOM.nextBoolean()) {
            applyRandomLevelEnchant(level, weapon, Enchantments.UNBREAKING, 3);
        }
        if (RANDOM.nextInt(20) == 0) {
            applyRandomLevelEnchant(level, weapon, Enchantments.MENDING, 1);
        }
        if (RANDOM.nextInt(5) == 0) {
            int tempestLvl = 1 + RANDOM.nextInt(2);
            applyRandomLevelEnchant(level, weapon, RegistryEnchantments.TEMPEST, tempestLvl);
        }
        // Roll a 50% chance to apply one damage enchantment among Sharpness, Smite, or Bane.
        if (RANDOM.nextBoolean()) {
            int damagePick = RANDOM.nextInt(3);
            switch (damagePick) {
                case 0:
                    applyRandomLevelEnchant(level, weapon, Enchantments.SHARPNESS, 5);
                    break;
                case 1:
                    applyRandomLevelEnchant(level, weapon, Enchantments.SMITE, 5);
                    break;
                case 2:
                    applyRandomLevelEnchant(level, weapon, Enchantments.BANE_OF_ARTHROPODS, 5);
                    break;
            }
        }
        Item witem = weapon.getItem();
        boolean isSwordLike = (witem instanceof SwordItem);
        boolean isAxeLike = (witem instanceof AxeItem);
        if (witem == RegistryIBBI.CRYING_SICKLE.get()) {
            isAxeLike = true;
        } else if (witem == RegistryIBBI.CRYING_PAXEL.get()) {
            isSwordLike = true;
            isAxeLike = true;
        }
        if (isSwordLike) {
            if (RANDOM.nextBoolean()) {
                applyRandomLevelEnchant(level, weapon, Enchantments.LOOTING, 3);
            }
            if (RANDOM.nextBoolean()) {
                applyRandomLevelEnchant(level, weapon, Enchantments.FIRE_ASPECT, 2);
            }
            if (RANDOM.nextBoolean()) {
                applyRandomLevelEnchant(level, weapon, Enchantments.KNOCKBACK, 2);
            }
        }
        if (isAxeLike) {
            if (RANDOM.nextBoolean()) {
                applyRandomLevelEnchant(level, weapon, Enchantments.EFFICIENCY, 5);
            }
            boolean fortune = (RANDOM.nextInt(4) == 0);
            boolean silk = (RANDOM.nextInt(4) == 0);
            if (fortune && silk) {
                if (RANDOM.nextBoolean()) {
                    silk = false;
                } else {
                    fortune = false;
                }
            }
            if (fortune) {
                applyRandomLevelEnchant(level, weapon, Enchantments.FORTUNE, 3);
            } else if (silk) {
                applyRandomLevelEnchant(level, weapon, Enchantments.SILK_TOUCH, 1);
            }
        }
    }

    // Special fallback for Zombies, ZombieVillagers, or Husks.
    private ItemStack getRandomZombieItem(Mob mob) {
        for (int attempt = 0; attempt < 5; attempt++) {
            int pick = RANDOM.nextInt(11);
            Item candidate;
            switch (pick) {
                case 0:  candidate = Items.ICE; break;
                case 1:  candidate = Items.PACKED_ICE; break;
                case 2:  candidate = Items.BLUE_ICE; break;
                case 3:  candidate = Items.BLAZE_ROD; break;
                case 4:  candidate = Items.BLAZE_POWDER; break;
                case 5:  candidate = Items.LAVA_BUCKET; break;
                case 6:  candidate = Items.FIRE_CHARGE; break;
                case 7:  candidate = Items.POISONOUS_POTATO; break;
                case 8:  candidate = Items.SPIDER_EYE; break;
                case 9:  candidate = Items.WIND_CHARGE; break;
                case 10: candidate = RegistryIBBI.AKU_AKU_MASK.get(); break;
                default: candidate = Items.ICE; break;
            }
            // For Drowned, skip fire items.
            if (mob instanceof Drowned && isFireItem(candidate)) {
                continue;
            }
            // For Husks, skip ice items.
            if (mob instanceof Husk && isIceItem(candidate)) {
                continue;
            }
            return new ItemStack(candidate);
        }
        return new ItemStack(Items.SPIDER_EYE);
    }

    private boolean isFireItem(Item i) {
        return (i == Items.BLAZE_POWDER || i == Items.BLAZE_ROD ||
                i == Items.FIRE_CHARGE || i == Items.LAVA_BUCKET);
    }

    private boolean isIceItem(Item i) {
        return (i == Items.ICE || i == Items.PACKED_ICE || i == Items.BLUE_ICE);
    }

    // For Drowned: 10% chance to receive a trident with enchantments.
    private void applyTridentEnchantments(Level level, ItemStack trident) {
        if (RANDOM.nextBoolean()) {
            applyRandomLevelEnchant(level, trident, Enchantments.UNBREAKING, 3);
        }
        if (RANDOM.nextInt(20) == 0) {
            applyRandomLevelEnchant(level, trident, Enchantments.MENDING, 1);
        }
        if (RANDOM.nextBoolean()) {
            applyRandomLevelEnchant(level, trident, Enchantments.IMPALING, 5);
        }
        boolean riptide = RANDOM.nextBoolean();
        boolean loyalty = RANDOM.nextBoolean();
        boolean channeling = RANDOM.nextBoolean();
        if (riptide) {
            applyRandomLevelEnchant(level, trident, Enchantments.RIPTIDE, 3);
        } else {
            if (loyalty) {
                applyRandomLevelEnchant(level, trident, Enchantments.LOYALTY, 3);
            }
            if (channeling) {
                applyRandomLevelEnchant(level, trident, Enchantments.CHANNELING, 1);
            }
        }
    }

    // Armor equipping: 20% chance per slot.
    // For mobs in the randomized armor conversion group (Zombies, ZombieVillagers, Skeletons, Piglins, Strays,
    // Drowned, Pillagers, and Husks, but not ZombifiedPiglins), if the armor piece is Diamond or Netherite,
    // a randomized replacement is chosen. Otherwise, Netherite items are converted to Diamond.
    // Piglin Brutes receive armor as chosen (no conversion).
    private void equipRandomArmor(Mob mob, Level level) {
        boolean useRandomizedArmorConversion = (mob instanceof Zombie
                || mob instanceof Skeleton
                || mob instanceof Piglin
                || mob instanceof Stray
                || mob instanceof Pillager)
                && !(mob instanceof ZombifiedPiglin);

        // Helmet
        if (RANDOM.nextInt(5) == 0) {
            ItemStack helmet = getRandomArmor(EquipmentSlot.HEAD);
            if (!helmet.isEmpty()) {
                if (!(mob instanceof PiglinBrute)) {
                    helmet = useRandomizedArmorConversion ? replaceDiamondAndNetheriteWithRandomArmor(helmet)
                            : replaceNetheriteWithDiamond(level, helmet);
                }
                if (RANDOM.nextBoolean()) {
                    applyArmorEnchantments(level, helmet, EquipmentSlot.HEAD);
                }
                mob.setItemSlot(EquipmentSlot.HEAD, helmet);
            }
        }
        // Chestplate
        if (RANDOM.nextInt(5) == 0) {
            ItemStack chest = getRandomArmor(EquipmentSlot.CHEST);
            if (!chest.isEmpty()) {
                if (!(mob instanceof PiglinBrute)) {
                    chest = useRandomizedArmorConversion ? replaceDiamondAndNetheriteWithRandomArmor(chest)
                            : replaceNetheriteWithDiamond(level, chest);
                }
                if (RANDOM.nextBoolean()) {
                    applyArmorEnchantments(level, chest, EquipmentSlot.CHEST);
                }
                mob.setItemSlot(EquipmentSlot.CHEST, chest);
            }
        }
        // Leggings
        if (RANDOM.nextInt(5) == 0) {
            ItemStack legs = getRandomArmor(EquipmentSlot.LEGS);
            if (!legs.isEmpty()) {
                if (!(mob instanceof PiglinBrute)) {
                    legs = useRandomizedArmorConversion ? replaceDiamondAndNetheriteWithRandomArmor(legs)
                            : replaceNetheriteWithDiamond(level, legs);
                }
                if (RANDOM.nextBoolean()) {
                    applyArmorEnchantments(level, legs, EquipmentSlot.LEGS);
                }
                mob.setItemSlot(EquipmentSlot.LEGS, legs);
            }
        }
        // Boots
        if (RANDOM.nextInt(5) == 0) {
            ItemStack boots = getRandomArmor(EquipmentSlot.FEET);
            if (!boots.isEmpty()) {
                if (!(mob instanceof PiglinBrute)) {
                    boots = useRandomizedArmorConversion ? replaceDiamondAndNetheriteWithRandomArmor(boots)
                            : replaceNetheriteWithDiamond(level, boots);
                }
                // For boots, we now roll a 50% chance to apply one protection enchantment,
                // but we want Frost Walker to be rare.
                // If RANDOM.nextInt(20)==0 (5% chance), apply Frost Walker; otherwise, apply Depth Strider.
                if (RANDOM.nextInt(20) == 0) {
                    applyRandomLevelEnchant(level, boots, Enchantments.FROST_WALKER, 2);
                } else {
                    applyRandomLevelEnchant(level, boots, Enchantments.DEPTH_STRIDER, 3);
                }
                if (RANDOM.nextBoolean()) {
                    applyRandomLevelEnchant(level, boots, Enchantments.FEATHER_FALLING, 4);
                }
                mob.setItemSlot(EquipmentSlot.FEET, boots);
            }
        }
    }

    // Returns a random armor piece for the given slot from tier 0 (Leather) to tier 5 (Netherite).
    private ItemStack getRandomArmor(EquipmentSlot slot) {
        int tier = RANDOM.nextInt(6);
        switch (slot) {
            case HEAD:
                switch (tier) {
                    case 0: return new ItemStack(Items.LEATHER_HELMET);
                    case 1: return new ItemStack(Items.CHAINMAIL_HELMET);
                    case 2: return new ItemStack(Items.IRON_HELMET);
                    case 3: return new ItemStack(Items.GOLDEN_HELMET);
                    case 4: return new ItemStack(Items.DIAMOND_HELMET);
                    case 5: return new ItemStack(Items.NETHERITE_HELMET);
                }
                break;
            case CHEST:
                switch (tier) {
                    case 0: return new ItemStack(Items.LEATHER_CHESTPLATE);
                    case 1: return new ItemStack(Items.CHAINMAIL_CHESTPLATE);
                    case 2: return new ItemStack(Items.IRON_CHESTPLATE);
                    case 3: return new ItemStack(Items.GOLDEN_CHESTPLATE);
                    case 4: return new ItemStack(Items.DIAMOND_CHESTPLATE);
                    case 5: return new ItemStack(Items.NETHERITE_CHESTPLATE);
                }
                break;
            case LEGS:
                switch (tier) {
                    case 0: return new ItemStack(Items.LEATHER_LEGGINGS);
                    case 1: return new ItemStack(Items.CHAINMAIL_LEGGINGS);
                    case 2: return new ItemStack(Items.IRON_LEGGINGS);
                    case 3: return new ItemStack(Items.GOLDEN_LEGGINGS);
                    case 4: return new ItemStack(Items.DIAMOND_LEGGINGS);
                    case 5: return new ItemStack(Items.NETHERITE_LEGGINGS);
                }
                break;
            case FEET:
                switch (tier) {
                    case 0: return new ItemStack(Items.LEATHER_BOOTS);
                    case 1: return new ItemStack(Items.CHAINMAIL_BOOTS);
                    case 2: return new ItemStack(Items.IRON_BOOTS);
                    case 3: return new ItemStack(Items.GOLDEN_BOOTS);
                    case 4: return new ItemStack(Items.DIAMOND_BOOTS);
                    case 5: return new ItemStack(Items.NETHERITE_BOOTS);
                }
                break;
            default:
                break;
        }
        return ItemStack.EMPTY;
    }

    // Applies armor enchantments, including a 50% chance roll before selecting one protection enchantment.
    private void applyArmorEnchantments(Level level, ItemStack armor, EquipmentSlot slot) {
        if (RANDOM.nextBoolean()) {
            applyRandomLevelEnchant(level, armor, Enchantments.UNBREAKING, 3);
        }
        if (RANDOM.nextInt(20) == 0) {
            applyRandomLevelEnchant(level, armor, Enchantments.MENDING, 1);
        }
        if (RANDOM.nextInt(10) == 0) {
            applyRandomLevelEnchant(level, armor, Enchantments.THORNS, 3);
        }
        // Roll a 50% chance to apply one protection enchantment.
        if (RANDOM.nextBoolean()) {
            int protectionPick = RANDOM.nextInt(4);
            switch (protectionPick) {
                case 0:
                    applyRandomLevelEnchant(level, armor, Enchantments.PROTECTION, 4);
                    break;
                case 1:
                    applyRandomLevelEnchant(level, armor, Enchantments.PROJECTILE_PROTECTION, 4);
                    break;
                case 2:
                    applyRandomLevelEnchant(level, armor, Enchantments.BLAST_PROTECTION, 4);
                    break;
                case 3:
                    applyRandomLevelEnchant(level, armor, Enchantments.FIRE_PROTECTION, 4);
                    break;
            }
        }
        // Slot-specific enchantments.
        switch (slot) {
            case HEAD:
                if (RANDOM.nextBoolean()) {
                    applyRandomLevelEnchant(level, armor, Enchantments.AQUA_AFFINITY, 1);
                }
                if (RANDOM.nextBoolean()) {
                    applyRandomLevelEnchant(level, armor, Enchantments.RESPIRATION, 3);
                }
                break;
            case CHEST:
                break;
            case LEGS:
                if (RANDOM.nextInt(20) == 0) {
                    applyRandomLevelEnchant(level, armor, Enchantments.SWIFT_SNEAK, 3);
                }
                break;
            case FEET:
                // Now, roll a 5% chance to apply Frost Walker (rare), otherwise apply Depth Strider.
                if (RANDOM.nextInt(20) == 0) {
                    applyRandomLevelEnchant(level, armor, Enchantments.FROST_WALKER, 2);
                } else {
                    applyRandomLevelEnchant(level, armor, Enchantments.DEPTH_STRIDER, 3);
                }
                if (RANDOM.nextBoolean()) {
                    applyRandomLevelEnchant(level, armor, Enchantments.FEATHER_FALLING, 4);
                }
                break;
            default:
                break;
        }
    }

    // Helper: applies an enchantment with a random level.
    private void applyRandomLevelEnchant(Level level, ItemStack stack,
                                         ResourceKey<net.minecraft.world.item.enchantment.Enchantment> enchantKey,
                                         int maxLevel) {
        Holder<net.minecraft.world.item.enchantment.Enchantment> holder =
                level.registryAccess()
                        .registryOrThrow(Registries.ENCHANTMENT)
                        .getHolder(enchantKey)
                        .orElseThrow(() -> new IllegalStateException(
                                "Enchantment not found: " + enchantKey.location()));
        int chosenLevel = 1 + RANDOM.nextInt(maxLevel);
        stack.enchant(holder, chosenLevel);
    }
}

