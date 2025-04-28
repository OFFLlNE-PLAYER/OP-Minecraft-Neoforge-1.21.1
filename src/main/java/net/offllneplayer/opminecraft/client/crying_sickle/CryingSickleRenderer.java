package net.offllneplayer.opminecraft.client.crying_sickle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import net.offllneplayer.opminecraft.OPMinecraft;
import net.offllneplayer.opminecraft.entity.CryingSickle;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

public class CryingSickleRenderer extends EntityRenderer<CryingSickle> {
    public CryingSickleRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CryingSickle entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        Direction dir = entity.getDirection();
        Direction stuckFace = entity.getStuckFace();

        if (dir == Direction.NORTH) {
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
        }
        if (dir == Direction.SOUTH) {
            poseStack.mulPose(Axis.YP.rotationDegrees(270));
        }
        if (dir == Direction.EAST) {
            poseStack.mulPose(Axis.YP.rotationDegrees(0));
        }
        if (dir == Direction.WEST) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
        }

        if (!entity.isGrounded()) {
            poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getRenderingRotation() * 15F));

        } else {
            if (stuckFace != Direction.DOWN) {
                poseStack.mulPose(Axis.XP.rotationDegrees(180));
            }
        }

        ItemStack stack = new ItemStack(RegistryIBBI.CRYING_SICKLE.get());
        BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, entity.getCommandSenderWorld(), null, entity.getId());
        Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(CryingSickle entity) {
        return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_crying_sickle.png");
    }
}
