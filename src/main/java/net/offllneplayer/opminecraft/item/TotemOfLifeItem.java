
package net.offllneplayer.opminecraft.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.offllneplayer.opminecraft.method.totemoflife.TOLActivate_Method;

import java.util.List;

public class TotemOfLifeItem extends Item {
	public TotemOfLifeItem() {super(new Properties()
			.stacksTo(1).rarity(Rarity.UNCOMMON));}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {return UseAnim.SPEAR;}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {return 1;}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.opminecraft.totem_of_life.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if ((selected) || (slot == 40)) {TOLActivate_Method.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);}
	}

}
