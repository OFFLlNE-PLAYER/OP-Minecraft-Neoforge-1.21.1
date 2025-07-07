package net.offllneplayer.opminecraft.VANILLA_eventHandler;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import net.offllneplayer.opminecraft.iwe.beretta.PistolMaterial;


import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import static net.offllneplayer.opminecraft.UTIL.Enchantment.OP_ArmorEnchantmentUtil.applyArmorEnchantments;
import static net.offllneplayer.opminecraft.UTIL.Enchantment.OP_WeaponEnchantmentUtil.applyWeaponEnchantments;

@EventBusSubscriber
public class EntitySpawnHandler {
    private static final Random RANDOM = new Random();
    private static final String SPAWN_HANDLED_TAG = "spawnHandled";


    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();

        if (!(entity instanceof Mob mob)) {
            return;
        }

        // Exit if already processed
        if (mob.getPersistentData().getBoolean(SPAWN_HANDLED_TAG)) {
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


        if (RANDOM.nextInt(4) == 0) {

            // 25% chance for a primary weapon for mobs

            if (mob instanceof WitherSkeleton witherSkeleton) {
                int roll = RANDOM.nextInt(100);

                // 4% chance to spawn with GOLDEN_BERETTA
                if (roll < 4) {
                    weapon = new ItemStack(RegistryBIBI.GOLDEN_BERETTA.get());

                    // Give 18x 9mm rounds for golden beretta
                    ItemStack ammo = new ItemStack(RegistryBIBI.NINEmm_PARABELLUM_ROUNDS.get(), 18);
                    witherSkeleton.setItemInHand(InteractionHand.OFF_HAND, ammo);

                    // 1% chance to spawn with random gun from tag
                } else if (roll == 4) {
                    TagKey<Item> pistolsTag = TagKey.create(Registries.ITEM, ResourceLocation.parse("opminecraft:pistols"));
                    Registry<Item> itemRegistry = witherSkeleton.level().registryAccess().registryOrThrow(Registries.ITEM);

                    List<Item> pistolItems = StreamSupport.stream(itemRegistry.getTagOrEmpty(pistolsTag).spliterator(), false).map(Holder::value).toList();

                    Item randomPistol = pistolItems.get(witherSkeleton.getRandom().nextInt(pistolItems.size()));
                    weapon = new ItemStack(randomPistol);

                    // Get the corresponding ammo based on the pistol type
                    String materialName = randomPistol.builtInRegistryHolder().key().location().getPath().toUpperCase();
                    PistolMaterial material = PistolMaterial.valueOf(materialName);

                    Item ammoItem = material.getRegisteredAmmo();
                    int ammoCount = material.getDurability();
                    ItemStack ammo = new ItemStack(ammoItem, ammoCount);
                    witherSkeleton.setItemInHand(InteractionHand.OFF_HAND, ammo);
                }

                // Add pistol goal if skeleton has any pistol
                if (roll <= 4) {
                    witherSkeleton.goalSelector.addGoal(3, new PistolUseGoal(witherSkeleton, 1.0420D, 18F));
                }


            } else if (mob instanceof PiglinBrute) {
                weapon = getBruteAxe();
            } else if (mob instanceof Vindicator) {
                weapon = new ItemStack(Items.DIAMOND_AXE);
            } else if (mob instanceof Skeleton) {
                // Regular skeletons get melee weapon from handler when they hit the 25% primary weapon chance
                weapon = getRandomVanillaWeapon(allowHighTiers);
            }

        } else if ((mob instanceof Zombie) && !(mob instanceof ZombifiedPiglin) && RANDOM.nextInt(4) == 0) {
            weapon = getRandomZombieItem(mob);
        } else if (mob instanceof Drowned && RANDOM.nextInt(10) == 0) {
            weapon = new ItemStack(Items.TRIDENT);
        }


        // 50% chance to add enchantments
        if (RANDOM.nextBoolean()) {
            applyWeaponEnchantments(level, weapon);
        }

        mob.setItemInHand(InteractionHand.MAIN_HAND, weapon);


        // --- Armor Equipping ---
        equipRandomArmor(mob, level);

    // --- Zombified Piglin Off-hand ---
        if (mob instanceof ZombifiedPiglin && RANDOM.nextInt(420) == 0) mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.NETHERITE_SCRAP));

        // --- Put persistant data that entity ran this script allready ---
        mob.getPersistentData().putBoolean(SPAWN_HANDLED_TAG, true);

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

    static ItemStack getRandomVanillaWeapon(boolean allowHighTiers) {
        int maxTier = allowHighTiers ? 5 : 3;
        int minTier = allowHighTiers ? 2 : 0; // Start from iron (tier 2) when high tiers
        int tier = RANDOM.nextInt(minTier, maxTier + 1);
        if (tier <= 2 && allowHighTiers) tier = RANDOM.nextInt(minTier, maxTier + 1);


        Item[] pair = WEAPONZ[tier];
        return new ItemStack(RANDOM.nextBoolean() ? pair[0] : pair[1]);
    }


    private static ItemStack getRandomZombieItem(Mob mob) {
        for (int attempt = 0; attempt < 4; attempt++) {
            Item miscItem = switch (RANDOM.nextInt(10)) {
                case 0 -> Items.ICE;
                case 1 -> Items.PACKED_ICE;
                case 2 -> Items.BLUE_ICE;

                case 3 -> Items.BLAZE_POWDER;
                case 4 -> Items.LAVA_BUCKET;
                case 5 ->  Items.FIRE_CHARGE;

                case 6 -> Items.POISONOUS_POTATO;
                case 7 -> Items.SPIDER_EYE;

                case 8 -> Items.WIND_CHARGE;
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
}
