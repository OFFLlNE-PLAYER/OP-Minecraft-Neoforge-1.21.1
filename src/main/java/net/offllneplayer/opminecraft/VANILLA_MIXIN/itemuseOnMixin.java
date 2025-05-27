package net.offllneplayer.opminecraft.VANILLA_MIXIN;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil;
import net.offllneplayer.opminecraft.entity.sw0rd.StickSw0rd_Method;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class itemuseOnMixin {
	@Inject(method = "useOn", at = @At("HEAD"))
	private void onUseOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {

		ItemStack stack = context.getItemInHand();

		// sword or gunblade useon
		if (stack.is(OP_TagKeyUtil.Items.VANILLA_SW0RDS) || stack.is(OP_TagKeyUtil.Items.GUNBLADES)) {
			if (!context.getLevel().isClientSide()) {
				StickSw0rd_Method.execute(context);
			}
		}
	}
}
