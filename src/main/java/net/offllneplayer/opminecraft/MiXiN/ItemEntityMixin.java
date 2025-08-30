package net.offllneplayer.opminecraft.MiXiN;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil.Items.CRYING_ITEMS;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

	@Shadow
	public abstract ItemStack getItem();

	/**
	 * @author OffllnePlayer
	 * @return no spin on vanilla items
	 * @reason bc it looks better! (bc fu that's why frign java errors without this documentation)
	 */
	@Overwrite
	public float getSpin(float partialTicks) {
		return ((ItemEntity)(Object)this).getYRot();
	}

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {

        ItemEntity mixedItem = (ItemEntity)(Object)this;
        ItemStack stack = getItem();
        Level level = mixedItem.level();

        if (!(level.isClientSide())) {
        if (mixedItem.isInLava() && getItem().is(CRYING_ITEMS)) {

            DamageSource source = level.damageSources().source(RegistryDamageTypes.CRYING_ESSENCE);

            float explosionRadius = 1F;

            if (stack.is(RegistryBIBI.BLOCK_OF_CRYING_INGOTS.get().asItem()) || stack.is(RegistryBIBI.CRYING_ESSENCE_BUCKET.get())) {
                explosionRadius = 4F;
            }

            mixedItem.discard();
            level.explode(source.getEntity(), Math.floor(mixedItem.getX()), Math.floor(mixedItem.getY()), Math.floor(mixedItem.getZ()), explosionRadius, false, Level.ExplosionInteraction.BLOCK);
            }
        }
    }
}
