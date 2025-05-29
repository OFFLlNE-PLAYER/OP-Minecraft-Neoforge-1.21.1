package net.offllneplayer.opminecraft.VANILLA_MIXIN;

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

import java.util.Random;

import static net.offllneplayer.opminecraft.UTIL.OP_TagKeyUtil.Items.CRYING_ITEMS;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow
    public abstract ItemStack getItem();

    @Overwrite
    public float getSpin(float partialTicks) {
        ItemEntity mixedItem = (ItemEntity)(Object)this;
        long seed = mixedItem.getUUID().getLeastSignificantBits();
        Random random = new Random(seed);
        float fixedRotation = random.nextFloat() * 360F;

        return fixedRotation;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {

        ItemEntity mixedItem = (ItemEntity)(Object)this;
        ItemStack stack = getItem();
        Level level = mixedItem.level();

        if (!(level.isClientSide())) {

        if (mixedItem.isInLava() && getItem().is(CRYING_ITEMS)) {

            DamageSource source = level.damageSources().source(RegistryDamageTypes.CRYING_ESSENCE);

            float explosionRadius = 1.0f;

            if (stack.is(RegistryBIBI.BLOCK_OF_CRYING_INGOTS.get().asItem()) || stack.is(RegistryBIBI.CRYING_ESSENCE_BUCKET.get())) {
                explosionRadius = 4.0f;
            }

            mixedItem.discard();
            level.explode(source.getEntity(), Math.floor(mixedItem.getX()), Math.floor(mixedItem.getY()), Math.floor(mixedItem.getZ()), explosionRadius, false, Level.ExplosionInteraction.BLOCK);
            }
        }
    }
}
