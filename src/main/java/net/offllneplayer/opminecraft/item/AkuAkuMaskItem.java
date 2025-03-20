
package net.offllneplayer.opminecraft.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.network.chat.Component;

import java.util.List;

public class AkuAkuMaskItem extends Item {
	public AkuAkuMaskItem() {super(new Properties()
			.stacksTo(1).rarity(Rarity.RARE));}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.opminecraft.aku_aku_mask.description_0"));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {return UseAnim.NONE;}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {return 0;}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, net.minecraft.world.InteractionHand hand) {
		return InteractionResultHolder.pass(player.getItemInHand(hand));
	}

}
