package net.offllneplayer.opminecraft.eventhandler.spawnhandler;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.entity.goal.GOAL_PistolUse;
import net.offllneplayer.opminecraft.entity.goal.GOAL_BalloonJump;
import net.offllneplayer.opminecraft.items._item.balloon.BalloonItem;
import net.offllneplayer.opminecraft.items._iwe.beretta.PistolMaterial;
import net.offllneplayer.opminecraft.init.RegistryBIBI;

import java.util.Random;

public final class SpawnEquipWeapons {
    public static void equipPrimaryWeapon(Mob mob, Level level) {
        // 25% chance to give a primary weapon
        if (RANDOM.nextInt(4) != 0) return;

        boolean allowHighTiers = !level.dimension().equals(Level.OVERWORLD) && RANDOM.nextBoolean();
        ItemStack weapon = ItemStack.EMPTY;

        if (mob instanceof WitherSkeleton witherSkeleton) {
            int roll = RANDOM.nextInt(100);
            if (roll <= 4) {
                weapon = equipWitherPistolAndReturn(witherSkeleton, roll);
            } else {
                weapon = getRandomNonWoodenVanillaWeapon(allowHighTiers);
            }
        } else if (mob instanceof PiglinBrute) {
            weapon = getBruteAxe();
        } else if (mob instanceof Vindicator) {
            weapon = new ItemStack(Items.DIAMOND_AXE);
        } else if (mob instanceof Drowned && RANDOM.nextInt(10) == 0) {
            weapon = new ItemStack(Items.TRIDENT);
        } else {
            weapon = getRandomVanillaWeapon(allowHighTiers);
        }

        if (!weapon.isEmpty()) {
            if (RANDOM.nextBoolean()) {
                SpawnEnchantments.applyWeaponEnchantments(level, weapon);
            }
            mob.setItemInHand(InteractionHand.MAIN_HAND, weapon);
        }
    }

    public static void maybeEquipOffhand(Mob mob) {
        // Zombie off-hand: 5% chance, excluding Zombified Piglin
		 if (RANDOM.nextInt(1) == 0) {
			 if ((mob instanceof Zombie) && !(mob instanceof ZombifiedPiglin)) {
				 ItemStack off = getRandomZombieItem(mob);
				 if (!off.isEmpty()) {
					 mob.setItemInHand(InteractionHand.OFF_HAND, off);
					 // If we just equipped a balloon, add the balloon jump goal for this mob
					 if (off.getItem() instanceof BalloonItem) {
						 mob.goalSelector.addGoal(1, new GOAL_BalloonJump(mob));
					 }
				 }
			 }
		 }

        // Zombified Piglin off-hand: 1/420 Netherite Scrap
        if (mob instanceof ZombifiedPiglin && RANDOM.nextInt(420) == 0) {
            mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.NETHERITE_SCRAP));
        }
    }
    private static final Random RANDOM = new Random();

    private static final Item[][] WEAPONZ = new Item[][]{
            {Items.WOODEN_AXE, Items.WOODEN_SWORD},
            {Items.STONE_AXE, Items.STONE_SWORD},
            {Items.IRON_AXE, Items.IRON_SWORD},
            {Items.GOLDEN_AXE, Items.GOLDEN_SWORD},
            {Items.DIAMOND_AXE, Items.DIAMOND_SWORD},
            {Items.NETHERITE_AXE, Items.NETHERITE_SWORD}
    };

    public static ItemStack getBruteAxe() {
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

    public static ItemStack getRandomVanillaWeapon(boolean allowHighTiers) {
        int maxTier = allowHighTiers ? 5 : 3;
        int minTier = allowHighTiers ? 2 : 0; // Start from iron (tier 2) when high tiers
        int tier = RANDOM.nextInt(minTier, maxTier + 1);
        if (tier <= 2 && allowHighTiers) tier = RANDOM.nextInt(minTier, maxTier + 1);

        Item[] pair = WEAPONZ[tier];
        return new ItemStack(RANDOM.nextBoolean() ? pair[0] : pair[1]);
    }

    // New: random non-wooden (stone or higher) sword or axe
    public static ItemStack getRandomNonWoodenVanillaWeapon(boolean allowHighTiers) {
        int maxTier = allowHighTiers ? 5 : 3;
        int minTier = allowHighTiers ? 2 : 1; // avoid wooden; bias to iron+ when high tiers
        int tier = RANDOM.nextInt(minTier, maxTier + 1);
        if (allowHighTiers && tier <= 2) tier = RANDOM.nextInt(minTier, maxTier + 1);
        Item[] pair = WEAPONZ[tier];
        return new ItemStack(RANDOM.nextBoolean() ? pair[0] : pair[1]);
    }

    public static ItemStack getRandomZombieItem(Mob mob) {
        Registry<Item> itemRegistry = mob.level().registryAccess().registryOrThrow(Registries.ITEM);
        Iterable<Holder<Item>> tagEntries = itemRegistry.getTagOrEmpty(OP_TagKeyUtil.Items.ZOMBIE_MISC_ITEMS);
        if (!tagEntries.iterator().hasNext()) {
            return new ItemStack(Items.POISONOUS_POTATO);
        }

        for (int attempt = 0; attempt < 8; attempt++) {
            Item candidate = null;
            int seen = 0;
            for (Holder<Item> holder : tagEntries) {
                seen++;
                if (mob.getRandom().nextInt(seen) == 0) {
                    candidate = holder.value();
                }
            }
            if (candidate == null) {
                break;
            }

            if (mob instanceof Drowned && (candidate == Items.BLAZE_POWDER || candidate == Items.FIRE_CHARGE || candidate == Items.LAVA_BUCKET)) {
                continue;
            }
            if (mob instanceof Husk && (candidate == Items.ICE || candidate == Items.PACKED_ICE || candidate == Items.BLUE_ICE)) {
                continue;
            }

            return new ItemStack(candidate);
        }

        return new ItemStack(Items.POISONOUS_POTATO);
    }


    // Equip pistol for Wither Skeleton when roll <= 4, and return the weapon stack for main-hand.
    public static ItemStack equipWitherPistolAndReturn(WitherSkeleton witherSkeleton, int roll) {
        if (roll > 4) {
            return ItemStack.EMPTY;
        }

        ItemStack weapon;
        if (roll < 4) {
            weapon = new ItemStack(RegistryBIBI.GOLDEN_BERETTA.get());
        } else {
            // roll == 4 -> pick a random pistol from tag
            TagKey<Item> pistolsTag = TagKey.create(Registries.ITEM, ResourceLocation.parse("opminecraft:pistols"));
            Registry<Item> itemRegistry = witherSkeleton.level().registryAccess().registryOrThrow(Registries.ITEM);
            Iterable<Holder<Item>> pistolEntries = itemRegistry.getTagOrEmpty(pistolsTag);
            Item randomPistol = null;
            int seen = 0;
            for (Holder<Item> holder : pistolEntries) {
                seen++;
                if (witherSkeleton.getRandom().nextInt(seen) == 0) {
                    randomPistol = holder.value();
                }
            }
            weapon = randomPistol == null ? ItemStack.EMPTY : new ItemStack(randomPistol);
        }

        if (!weapon.isEmpty()) {
            // Assign ammo based on pistol material enum, no special-cases
            Item weaponItem = weapon.getItem();
            String matName = weaponItem.builtInRegistryHolder().key().location().getPath().toUpperCase();
            PistolMaterial material = PistolMaterial.valueOf(matName);
            Item ammoItem = material.getRegisteredAmmo();
            int ammoCount = material.getDurability();
            ItemStack ammo = new ItemStack(ammoItem, ammoCount);
            witherSkeleton.setItemInHand(InteractionHand.OFF_HAND, ammo);

            // Add pistol usage goal
            witherSkeleton.goalSelector.addGoal(3, new GOAL_PistolUse(witherSkeleton, 1.0420D, 18F));
        }

        return weapon;
    }
}
