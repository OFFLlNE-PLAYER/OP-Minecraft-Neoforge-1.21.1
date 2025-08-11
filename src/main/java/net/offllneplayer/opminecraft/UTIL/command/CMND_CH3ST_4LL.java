package net.offllneplayer.opminecraft.UTIL.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.offllneplayer.opminecraft.OPMinecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.offllneplayer.opminecraft.blocks._block.ancientchests.AncientChestBlockEntity;
import net.offllneplayer.opminecraft.blocks._block.ancientchests.AncientChestTrimMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Command:
 * /OP_CH3ST_4LL
 * When executed by a player, finds the targeted block and places a random ancient chest of maximum capacity
 * above it, then fills it sequentially with every item registered under this mod's namespace (1 per slot).
 * Trims copper, iron, gold, and diamond are excluded from selection as requested.
 */
@EventBusSubscriber(modid = OPMinecraft.Mod_ID)
public class CMND_CH3ST_4LL {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("OP_CH3ST_4LL")
                .requires(src -> src.hasPermission(2))
                .executes(ctx -> execute(ctx.getSource()));
        event.getDispatcher().register(builder);
    }

    private static int execute(CommandSourceStack source) {
        try {
            ServerLevel level = source.getLevel();
            var player = source.getPlayer();
            if (player == null) {
                source.sendFailure(Component.literal("Must be a player to use this command."));
                return 0;
            }

            // Ray trace to find targeted blocks up to 16 blocks.
            HitResult hit = player.pick(16D, 0F, false);
            if (!(hit instanceof BlockHitResult bhr) || hit.getType() != HitResult.Type.BLOCK) {
                source.sendFailure(Component.literal("No blocks targeted within range."));
                return 0;
            }

            BlockPos target = bhr.getBlockPos();
            BlockPos placePos = target.above();

            // Gather all items from this mod's namespace
            List<Item> modItems = new ArrayList<>();
            for (Item item : BuiltInRegistries.ITEM) {
                ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
                if (id != null && OPMinecraft.Mod_ID.equals(id.getNamespace())) {
                    modItems.add(item);
                }
            }

            if (modItems.isEmpty()) {
                source.sendFailure(Component.literal("No mod items found for namespace: " + OPMinecraft.Mod_ID));
                return 0;
            }

            int idx = 0;
            int chestCount = 0;
            int totalFilled = 0;
            BlockPos currentPos = placePos;

            while (idx < modItems.size()) {
                // Determine a random ancient chest variant with maximum capacity excluding certain trims
                BlockState chosenChestState = pickRandomMaxCapacityAncientChestState(level);
                if (chosenChestState == null) {
                    source.sendFailure(Component.literal("No suitable ancient chest variants found (after exclusions)."));
                    return 0;
                }

                // Place the chosen chest
                level.setBlockAndUpdate(currentPos, chosenChestState);

                // Fill the chest starting from current index
                int filled = fillAncientChestFromIndex(level, currentPos, modItems, idx);
                if (filled <= 0) {
                    break; // safety: avoid infinite loop if something goes wrong
                }

                idx += filled;
                totalFilled += filled;
                chestCount++;
                currentPos = currentPos.above();
            }

            final int finalChestCount = chestCount;
            final int finalTotalFilled = totalFilled;
            source.sendSuccess(() -> Component.literal("Player " + source.getPlayer().getName().getString() +
                    " placed " + finalChestCount + " chest(s) and filled " + finalTotalFilled + " slot(s)."), true);
            return Command.SINGLE_SUCCESS;
        } catch (Exception e) {
            source.sendFailure(Component.literal("Command failed: " + e.getMessage()));
            return 0;
        }
    }

    private static BlockState pickRandomMaxCapacityAncientChestState(ServerLevel level) {
        // Trim exclusions
        List<String> excluded = List.of("copper", "iron", "gold", "diamond");

        Map<Block, Integer> candidateToSlots = new HashMap<>();
        int maxSlots = 0;

        for (Block block : BuiltInRegistries.BLOCK) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            if (key == null) continue;
            if (!OPMinecraft.Mod_ID.equals(key.getNamespace())) continue;
            String path = key.getPath();
            if (!path.endsWith("_trim_ancient_chest")) continue;

            String[] parts = path.split("_");
            if (parts.length < 5) continue; // expect <wood>_<trim>_trim_ancient_chest
            String trim = parts[1];
            if (excluded.contains(trim)) continue;

            AncientChestTrimMaterial mat;
            try {
                mat = AncientChestTrimMaterial.valueOf(trim.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException ex) {
                continue; // unknown trim -> skip
            }
            int slots = mat.getSlots();
            candidateToSlots.put(block, slots);
            if (slots > maxSlots) maxSlots = slots;
        }

        if (maxSlots <= 0) return null;

        // collect max-capacity blocks
        List<Block> maxBlocks = new ArrayList<>();
        for (Map.Entry<Block, Integer> e : candidateToSlots.entrySet()) {
            if (e.getValue() == maxSlots) maxBlocks.add(e.getKey());
        }
        if (maxBlocks.isEmpty()) return null;

        Block chosen = maxBlocks.get(new Random().nextInt(maxBlocks.size()));
        // Default state should already have FACING set to NORTH; no orientation dependency here
        return chosen.defaultBlockState();
    }

    private static int fillAncientChestFromIndex(ServerLevel level, BlockPos pos, List<Item> items, int startIndex) {
        int placed = 0;
        var be = level.getBlockEntity(pos);
        if (be instanceof AncientChestBlockEntity chest) {
            int size = chest.getContainerSize();
            int idx = startIndex;
            for (int slot = 0; slot < size && idx < items.size(); slot++, idx++) {
                chest.setItem(slot, new ItemStack(items.get(idx)));
                placed++;
            }
            chest.setChanged();
        }
        return placed;
    }
}
