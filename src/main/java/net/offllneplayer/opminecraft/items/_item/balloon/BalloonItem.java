package net.offllneplayer.opminecraft.items._item.balloon;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class BalloonItem extends Item {

    private final BalloonColor color;
    
    public BalloonItem(BalloonColor color) {
        super(new Properties()
                .stacksTo(1)
                .durability(100)
                .rarity(Rarity.UNCOMMON));
        this.color = color;
    }
    
    /**
     * Gets the color of this balloon
     * @return The balloon color
     */
    public BalloonColor getColor() {
        return color;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.opminecraft.balloon.description_0"));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
        return 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, net.minecraft.world.InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

	// Only handle players - mobs do not trigger this method - they use GOAL_BalloonJump for balloon activation
	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (entity instanceof Player player) {
			if ((entity.tickCount % 20 == 0) && (!entity.onGround())) {
				if (!world.isClientSide()) {
					if (selected || slot == 40) {
						BalloonActivate_Method.execute(player);
					} else if ((player.getMainHandItem() == itemstack) && (player.getOffhandItem() == itemstack)) {
						BalloonActivate_Method.execute(player);
					}

					// Check where this itemstack is being held by the player and apply damage
					boolean isHeld = (player.getMainHandItem() == itemstack) || (player.getOffhandItem() == itemstack);
					if (isHeld) {
						int cur = itemstack.getDamageValue();
						int max = itemstack.getMaxDamage();
						if (cur + 1 >= max) {
							PopBalloons_Method.execute(player);
						} else {
							itemstack.setDamageValue(cur + 1);
						}
					}
				}
			}
		}
	}
}