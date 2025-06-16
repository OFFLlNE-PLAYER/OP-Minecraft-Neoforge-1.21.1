package net.offllneplayer.opminecraft.client.gui.lootchest;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.offllneplayer.opminecraft.world.inventory.lootchest.NLootChestInv;

import java.util.HashMap;


public class NLootChestGUI extends AbstractContainerScreen<NLootChestInv> {
	private final static HashMap<String, Object> guistate = NLootChestGUI.guistate;
	private Level world;
	private int x, y, z;
	private Player entity;
	private ResourceLocation texture;

	public NLootChestGUI(NLootChestInv container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 168;
		this.imageHeight = 194;

		// Get the block at the container's position
		BlockPos pos = new BlockPos(x, y, z);
		Block block = world.getBlockState(pos).getBlock();
		ResourceLocation regName = BuiltInRegistries.BLOCK.getKey(block);
		String regPath = regName.getPath();

		String trimName = extractTrimName(regPath);
		this.texture = ResourceLocation.parse("opminecraft:textures/screens/" + trimName + "_loot_chest_gui.png");
	}

	private String extractTrimName(String regPath) {
		if (regPath.contains("copper_trim")) return "copper";
		else if (regPath.contains("iron_trim")) return "iron";
		else if (regPath.contains("gold_trim")) return "gold";
		else if (regPath.contains("diamond_trim")) return "diamond";
		else if (regPath.contains("netherite_trim")) return "netherite";
		else if (regPath.contains("amethyst_trim")) return "amethyst";
		else if (regPath.contains("emerald_trim")) return "emerald";
		else if (regPath.contains("lapis_trim")) return "lapis";
		else if (regPath.contains("quartz_trim")) return "quartz";
		else if (regPath.contains("redstone_trim")) return "redstone";
		else return "copper"; // Default to copper if no match
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
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
