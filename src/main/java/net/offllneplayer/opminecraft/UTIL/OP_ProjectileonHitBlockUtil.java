package net.offllneplayer.opminecraft.UTIL;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class OP_ProjectileonHitBlockUtil {

	public static boolean handleButtonInteraction(BlockHitResult result, Level level, Entity entity) {
		if (level.isClientSide()) return false;

		BlockPos pos = result.getBlockPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		// Handle button interaction using the buttons tag
		if (block.builtInRegistryHolder().is(BlockTags.BUTTONS)) {
			if (state.hasProperty(ButtonBlock.POWERED) && !state.getValue(ButtonBlock.POWERED)) {
				// Only press if not already pressed
				BlockState pressedState = state.setValue(ButtonBlock.POWERED, true);
				level.setBlock(pos, pressedState, 3);

				level.playSound(null, pos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, 1F);

				level.scheduleTick(pos, block, 30);
				level.updateNeighborsAt(pos, block);

				// Get the attached face direction
				if (state.hasProperty(ButtonBlock.FACING) && state.hasProperty(ButtonBlock.FACE)) {
					Direction direction = state.getValue(ButtonBlock.FACING);
					AttachFace face = state.getValue(ButtonBlock.FACE);

					// Convert AttachFace to Direction
					Direction faceDirection;
					switch (face) {
						case FLOOR:
							faceDirection = Direction.DOWN;
							break;
						case CEILING:
							faceDirection = Direction.UP;
							break;
						case WALL:
						default:
							faceDirection = direction.getOpposite();
							break;
					}

					level.updateNeighborsAt(pos.relative(faceDirection), block);
				}

				// Create block event to notify neighbors
				// Get the owner if it's a projectile, otherwise use the entity itself
				Entity source = entity;
				if (entity instanceof Projectile projectile && projectile.getOwner() != null) {
					source = projectile.getOwner();
				}

				level.gameEvent(source, GameEvent.BLOCK_ACTIVATE, pos);
				return true;
			}
		}
		return false;
	}
}

