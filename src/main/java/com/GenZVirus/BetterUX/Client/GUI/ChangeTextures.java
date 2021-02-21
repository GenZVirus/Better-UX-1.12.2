package com.GenZVirus.BetterUX.Client.GUI;

import java.io.IOException;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ChangeTextures extends GuiScreen {

	private GuiButton GenZVirus, Zetta, Squared;
	private Minecraft mc = Minecraft.getMinecraft();
	public static ChangeTextures instance = new ChangeTextures();

	public ChangeTextures() {
	}

	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution scaledResolution = new ScaledResolution(mc);
		GenZVirus = new GuiButton(0, scaledResolution.getScaledWidth() / 2 - 105, scaledResolution.getScaledHeight() / 2 - 5, 100, 20, "GenZVirus");

		Zetta = new GuiButton(1, scaledResolution.getScaledWidth() / 2 + 5, scaledResolution.getScaledHeight() / 2 - 5, 100, 20, "Zetta");
		Squared = new GuiButton(2, scaledResolution.getScaledWidth() / 2 + 5, scaledResolution.getScaledHeight() / 2 + 25, 100, 20, "Squared");
		XMLFileJava.checkFileAndMake();
		String texture = XMLFileJava.readElement("Texture");
		if (texture.contentEquals("genzvirus")) {
			GenZVirus.enabled = false;
		} else if (texture.contentEquals("zetta")) {
			Zetta.enabled = false;
		} else if (texture.contentEquals("squared")) {
			Squared.enabled = false;
		}

		this.addButton(GenZVirus);
		this.addButton(Zetta);
		this.addButton(Squared);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for (GuiButton button : this.buttonList) {
			int widthIn = button.x;
			int heightIn = button.y;
			int width = button.width;
			int height = button.height;
			int x = mouseX;
			int y = mouseY;
			if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
				switch (button.id) {
				case 0: {
					resetButtons();
					GenZVirus.enabled = false;
					XMLFileJava.checkFileAndMake();
					XMLFileJava.editElement("Texture", "genzvirus");
					XMLFileJava.editElement("HasOverlay", "false");
					XMLFileJava.load();
					break;
				}
				case 1: {
					resetButtons();
					Zetta.enabled = false;
					XMLFileJava.checkFileAndMake();
					XMLFileJava.editElement("Texture", "zetta");
					XMLFileJava.editElement("HasOverlay", "false");
					XMLFileJava.load();
					break;
				}
				case 2: {
					resetButtons();
					Squared.enabled = false;
					XMLFileJava.checkFileAndMake();
					XMLFileJava.editElement("Texture", "squared");
					XMLFileJava.editElement("HasOverlay", "true");
					XMLFileJava.load();
				}
				default:
					break;
				}
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);

	}

	public void resetButtons() {
		for (GuiButton button : this.buttonList) {
			button.enabled = true;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	@Override
	public void drawScreen(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.drawDefaultBackground();
		super.drawScreen(p_render_1_, p_render_2_, p_render_3_);
	}

}
