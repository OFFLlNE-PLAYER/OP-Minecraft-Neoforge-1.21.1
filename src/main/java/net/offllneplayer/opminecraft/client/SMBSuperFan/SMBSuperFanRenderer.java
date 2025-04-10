package net.offllneplayer.opminecraft.client.SMBSuperFan;


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
import net.offllneplayer.opminecraft.entity.SMBSuperFan;
import net.offllneplayer.opminecraft.init.RegistryIBBI;

public class SMBSuperFanRenderer extends EntityRenderer<SMBSuperFan> {

    public SMBSuperFanRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SMBSuperFan entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // Apply rotation if facing North or South
        Direction direction = entity.getDirection();
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
        }

        // Translate upward so the item appears correctly.
        poseStack.translate(0.0, 0.0, 0.0);

        // Create an ItemStack of your registered item.
        ItemStack stack = new ItemStack(RegistryIBBI.SMB_SUPER_FAN.get());

        // Retrieve the BakedModel for the ItemStack.
        BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, entity.getCommandSenderWorld(), null, entity.getId());

        // Render the item using the obtained BakedModel.
        Minecraft.getInstance().getItemRenderer().render(
                stack,
                ItemDisplayContext.FIXED,
                false,
                poseStack,
                buffer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                model
        );

        poseStack.popPose();
    }


    @Override
    public ResourceLocation getTextureLocation(SMBSuperFan entity) {
        // This is not used by the item renderer but must be implemented.
        return ResourceLocation.fromNamespaceAndPath(OPMinecraft.Mod_ID, "item/text_smb_super_fan.png");
    }
}