package net.offllneplayer.opminecraft.blocks._block._ancientchest;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;


public class AncientChestGUI extends AbstractContainerScreen<AncientChestInv> {
	private final static HashMap<String, Object> guistate = AncientChestGUI.guistate;
	private Level world;
	private int x, y, z;
	private Player entity;
	private ResourceLocation bgTexture;
	private ResourceLocation trimTexture;
	private String woodName;
	private String trimName;

	// Background and trim image dimensions with clear naming
	private final int bgGUIWidth = 168;
	private final int bgGUIHeight = 194;
	private int trimGUIWidth;
	private int trimGUIHeight;

	public AncientChestGUI(AncientChestInv container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;

		// Get the wood type and trim type directly from the container
		this.woodName = container.getWoodType();
		this.trimName = container.getTrimType();

		// Set trim dimensions based on the trim type
		setTrimDimensions();

		// Set textures
		this.bgTexture = ResourceLocation.parse("opminecraft:textures/screens/ancient_chest/wood/gui_ancient_chest_" + woodName + ".png");
		this.trimTexture = ResourceLocation.parse("opminecraft:textures/screens/ancient_chest/trim/gui_ancient_chest_" + trimName + ".png");

		// Set the overall image dimensions to match the trim dimensions
		this.imageWidth = this.trimGUIWidth;
		this.imageHeight = this.trimGUIHeight;
	}

	/**
	 * Sets the GUI dimensions based on the trim type
	 */
	private void setTrimDimensions() {
		switch (this.trimName) {
			case "copper":
				this.trimGUIWidth = 168;
				this.trimGUIHeight = 132;
				break;
			case "iron":
				this.trimGUIWidth = 168;
				this.trimGUIHeight = 144;
				break;
			case "gold":
				this.trimGUIWidth = 168;
				this.trimGUIHeight = 166;
				break;
			case "diamond":
				this.trimGUIWidth = 168;
				this.trimGUIHeight = 178;
				break;
			case "netherite":
			case "amethyst":
			case "emerald":
			case "lapis":
			case "quartz":
			case "redstone":
			default:
				this.trimGUIWidth = 168;
				this.trimGUIHeight = 194;
				break;
		}
	}

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

		// Render the wood type background texture first (always fixed size)
		guiGraphics.blit(bgTexture, this.leftPos, this.topPos, 0, 0, this.bgGUIWidth, this.bgGUIHeight, this.bgGUIWidth, this.bgGUIHeight);

		// Render the trim texture on top (using trim-specific dimensions)
		guiGraphics.blit(trimTexture, this.leftPos, this.topPos, 0, 0, this.trimGUIWidth, this.trimGUIHeight, this.trimGUIWidth, this.trimGUIHeight);

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
	}
}

