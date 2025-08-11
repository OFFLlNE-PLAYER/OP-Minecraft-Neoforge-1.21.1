package net.offllneplayer.opminecraft.blocks._block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;

import javax.annotation.Nullable;
import java.util.List;

public class GoldenBedBlock extends BedBlock {
    public static final Properties PROPERTIES = Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .sound(SoundType.WOOL)
            .strength(0.2F, 2.5F)
            .pushReaction(PushReaction.BLOCK)
            .noOcclusion()
            .isRedstoneConductor((bs, br, bp) -> false);

    public GoldenBedBlock() {
        super(DyeColor.YELLOW, PROPERTIES);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PART, BedPart.FOOT)
                .setValue(OCCUPIED, false)
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder lootContext) {
        return state.getValue(PART) == BedPart.HEAD
        ? super.getDrops(state, lootContext) : List.of();
    }

    @Override
    public void setPlacedBy(Level lvl, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(lvl, pos, state, placer, stack);
        if (!lvl.isClientSide) {
            BlockPos headPos = pos.relative(state.getValue(FACING));
            lvl.setBlock(headPos, state.setValue(PART, BedPart.HEAD), 3);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}