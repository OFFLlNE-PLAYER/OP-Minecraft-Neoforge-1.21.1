package net.offllneplayer.opminecraft.method.VANILLA_MIXIN;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.offllneplayer.opminecraft.init.RegistryDamageTypes;
import net.offllneplayer.opminecraft.init.RegistryBIBI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static net.offllneplayer.opminecraft.method.UTIL.OP_TagKeyUtil.Items.CRYING_ITEMS;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow
    public abstract ItemStack getItem();

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ItemEntity self = (ItemEntity)(Object)this;
        ItemStack stack = getItem();
        if (self.isInLava() && getItem().is(CRYING_ITEMS)) {
            int x = (int) Math.floor(self.getX());
            int y = (int) Math.floor(self.getY());
            int z = (int) Math.floor(self.getZ());
            Level level = self.level();
            DamageSource source = level.damageSources().source(RegistryDamageTypes.CRYING_ESSENCE);

            float explosionRadius = 1.0f;

            if (stack.is(RegistryBIBI.BLOCK_OF_CRYING_INGOTS.get().asItem())
                    || stack.is(RegistryBIBI.CRYING_ESSENCE_BUCKET.get())) {
                explosionRadius = 4.0f;
            }

            self.discard();
            level.explode(source.getEntity(), x, y, z, explosionRadius, false, Level.ExplosionInteraction.BLOCK);
        }
    }
}