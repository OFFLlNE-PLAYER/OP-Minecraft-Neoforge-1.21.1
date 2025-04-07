
package net.offllneplayer.opminecraft.block.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.HolderLookup;
import net.offllneplayer.opminecraft.init.RegistryBlockEntities;


public class StuckGunbladeBlockEntity extends BlockEntity {
	public StuckGunbladeBlockEntity(BlockPos position, BlockState state) {
		super(RegistryBlockEntities.STUCK_GUNBLADE.get(), position, state);
	}

	@Override
	protected void saveAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.saveAdditional(compound, lookupProvider);
	}

	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.loadAdditional(compound, lookupProvider);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
		return this.saveWithFullMetadata(lookupProvider);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
