
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import net.offllneplayer.opminecraft.method.crash.akuaku.PROCAkuAkuUseProcedure;
import net.offllneplayer.opminecraft.method.crash.akuaku.PROCAkuAkuActivateProcedure;

import java.util.List;

public class AkuAkuMaskItem extends Item {
	public AkuAkuMaskItem() {super(new Properties()
			.stacksTo(1).rarity(Rarity.RARE));}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {return UseAnim.SPEAR;}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {return 1;}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.opminecraft.aku_aku_mask.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		PROCAkuAkuUseProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);

		if ((selected) || (slot == 40)) {PROCAkuAkuActivateProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);}
	/*
	USE TO GET SLOT WHEN ITS IN HAND
	System.out.println(slot);
	*/
	}
}
