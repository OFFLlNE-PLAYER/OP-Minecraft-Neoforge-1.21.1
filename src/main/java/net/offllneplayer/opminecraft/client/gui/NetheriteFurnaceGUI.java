package net.offllneplayer.opminecraft.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.offllneplayer.opminecraft.method._handler.GUI_Button.NetheriteFurnaceButtonHandler;
import net.offllneplayer.opminecraft.method.furnaces.gui.FurnacesExpBar_SuperMethod;
import net.offllneplayer.opminecraft.method.furnaces.gui.FurnacesFuelBar_SuperMethod;
import net.offllneplayer.opminecraft.method.furnaces.gui.FurnacesProgressBar_SuperMethod;
import net.offllneplayer.opminecraft.world.inventory.NetheriteFurnaceInv;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class NetheriteFurnaceGUI extends AbstractContainerScreen<NetheriteFurnaceInv> {
	private final static HashMap<String, Object> guistate = NetheriteFurnaceInv.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_text_experience_bottle_button;
	ImageButton imagebutton_text_stoke_button;

	public NetheriteFurnaceGUI(NetheriteFurnaceInv container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 170;
		this.imageHeight = 160;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("opminecraft:textures/screens/gui_netherite_furnace.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar0_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_000.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar05_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_005.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar10_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_010.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar15_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_015.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar20_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_020.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar25_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_025.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar30_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_030.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar35_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_035.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar40_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_040.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar45_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_045.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar50_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_050.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar55_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_055.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar60_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_060.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar65_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_065.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar70_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_070.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar75_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_075.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar80_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_080.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar85_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_085.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar90_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_090.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar95_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_095.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesFuelBar_SuperMethod.FurnacesFuelBar100_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_fuel_100.png"), this.leftPos + 30, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar0_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_0.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar05_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_05.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar10_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_10.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar15_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_15.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar20_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_20.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar25_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_25.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar30_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_30.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar35_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_35.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar40_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_40.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar45_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_45.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar50_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_50.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar55_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_55.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar60_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_60.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar65_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_65.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar70_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_70.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar75_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_75.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar80_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_80.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar85_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_85.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar90_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_90.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar95_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_95.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesProgressBar_SuperMethod.FurnacesProgressBar100_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_furnaces_progress_100.png"), this.leftPos + 64, this.topPos + 71, 0, 0, 41, 2, 41, 2);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar0_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_000.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar05_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_5.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar10_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_10.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar15_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_15.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar20_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_20.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar25_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_25.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar30_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_30.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar35_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_35.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar40_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_40.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar45_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_45.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar50_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_50.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar55_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_55.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar60_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_60.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar65_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_65.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar70_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_70.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar75_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_75.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar80_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_80.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar85_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_85.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar90_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_90.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar95_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_95.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		if (FurnacesExpBar_SuperMethod.FurnacesExpBar100_Method.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("opminecraft:textures/screens/text_exp_100.png"), this.leftPos + 136, this.topPos + 48, 0, 0, 4, 26, 4, 26);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		imagebutton_text_experience_bottle_button = new ImageButton(this.leftPos + 115, this.topPos + 59, 16, 16,
				new WidgetSprites(ResourceLocation.parse("opminecraft:textures/screens/text_experience_bottle_button.png"), ResourceLocation.parse("opminecraft:textures/screens/text_experience_bottle_button_hover.png")), e -> {
			if (true) {
				PacketDistributor.sendToServer(new NetheriteFurnaceButtonHandler(0, x, y, z));
				NetheriteFurnaceButtonHandler.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_text_experience_bottle_button", imagebutton_text_experience_bottle_button);
		this.addRenderableWidget(imagebutton_text_experience_bottle_button);

		imagebutton_text_stoke_button = new ImageButton(this.leftPos + 39, this.topPos + 58, 16, 16,
				new WidgetSprites(ResourceLocation.parse("opminecraft:textures/screens/text_stoke_button.png"), ResourceLocation.parse("opminecraft:textures/screens/text_stoke_button_hover.png")), e -> {
			if (true) {
				PacketDistributor.sendToServer(new NetheriteFurnaceButtonHandler(1, x, y, z));
				NetheriteFurnaceButtonHandler.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_text_stoke_button", imagebutton_text_stoke_button);
		this.addRenderableWidget(imagebutton_text_stoke_button);
	}
}
