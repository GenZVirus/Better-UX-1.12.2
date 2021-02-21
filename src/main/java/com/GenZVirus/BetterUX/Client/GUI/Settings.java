package com.GenZVirus.BetterUX.Client.GUI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.GenZVirus.BetterUX.BetterUX;
import com.GenZVirus.BetterUX.Client.File.XMLFileJava;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.event.ClickEvent;

public class Settings extends GuiScreen {

	private GuiButton closeButton, resetButton, editButton, changeButton, discordButton, enable_or_disable_BetterUX, enable_or_disable_text, enable_or_disable_soundeffects;
	private static Minecraft mc = Minecraft.getMinecraft();
	public static Settings instance = new Settings("BUXSettings");

	public Settings(String titleIn) {
	}

	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution scaledresolution = new ScaledResolution(mc);
		closeButton = new GuiButton(0, scaledresolution.getScaledWidth() / 2 + 5, scaledresolution.getScaledHeight() / 2 + 20, 100, 20, "Close");
		resetButton = new GuiButton(1, scaledresolution.getScaledWidth() / 2 - 105, scaledresolution.getScaledHeight() / 2 - 5, 100, 20, "Reset to Default");
		editButton = new GuiButton(2, scaledresolution.getScaledWidth() / 2 + 5, scaledresolution.getScaledHeight() / 2 - 5, 100, 20, "Edit UI");
		changeButton = new GuiButton(3, scaledresolution.getScaledWidth() / 2 - 105, scaledresolution.getScaledHeight() / 2 + 20, 100, 20, "Change Textures");
		discordButton = new GuiButton(4, 4, scaledresolution.getScaledHeight() - 30, 20, 20, "");
		enable_or_disable_BetterUX = new GuiButton(5, scaledresolution.getScaledWidth() / 2 - 105, scaledresolution.getScaledHeight() / 2 + 45, 210, 20, XMLFileJava.readElement("Enabled_Disabled").contentEquals("enabled") ? "Disable Better UX Overlay" : "Enable Better UX Overlay");
		enable_or_disable_text = new GuiButton(6, scaledresolution.getScaledWidth() / 2 - 105, scaledresolution.getScaledHeight() / 2 + 70, 210, 20, XMLFileJava.readElement("TextDisabled").contentEquals("false")? "Disable Text" : "Enable Text");
		enable_or_disable_soundeffects = new GuiButton(7, scaledresolution.getScaledWidth() / 2 - 105, scaledresolution.getScaledHeight() / 2 -30, 210, 20, XMLFileJava.readElement("SoundEffects").contentEquals("false")? "Enable Sound Effects" : "Disable Sound Effects");
		this.addButton(closeButton);
		this.addButton(resetButton);
		this.addButton(editButton);
		this.addButton(changeButton);
		this.addButton(discordButton);
		this.addButton(enable_or_disable_BetterUX);
		this.addButton(enable_or_disable_text);
		this.addButton(enable_or_disable_soundeffects);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for (GuiButton button : this.getButtons()) {
			int widthIn = button.x;
			int heightIn = button.y;
			int width = button.width;
			int height = button.height;
			int x = mouseX;
			int y = mouseY;
			if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
				switch (button.id) {
				case 0: {
					mc.displayGuiScreen((GuiScreen) null);
					break;
				}
				case 1: {
					XMLFileJava.resetToDefault();
					XMLFileJava.load();
					break;
				}
				case 2: {
					mc.displayGuiScreen(EditOverlay.instance);
					break;
				}
				case 3: {
					mc.displayGuiScreen(ChangeTextures.instance);
					break;
				}
				case 4: {
					mc.displayGuiScreen(new GuiConfirmOpenLink(this, new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD").getValue(), 31102009, false));
					break;
				}
				case 5: {
					XMLFileJava.checkFileAndMake();
					if(XMLFileJava.readElement("Enabled_Disabled").contentEquals("enabled")) {
						XMLFileJava.editElement("Enabled_Disabled", "disabled");
					} else XMLFileJava.editElement("Enabled_Disabled", "enabled");
					this.enable_or_disable_BetterUX.displayString = XMLFileJava.readElement("Enabled_Disabled").contentEquals("enabled")? "Disable Better UX Overlay" : "Enable Better UX Overlay";
					XMLFileJava.load();
				}
				case 6: {
					XMLFileJava.checkFileAndMake();
					if(XMLFileJava.readElement("TextDisabled").contentEquals("false")) {
						XMLFileJava.editElement("TextDisabled", "true");
					} else XMLFileJava.editElement("TextDisabled", "false");
					this.enable_or_disable_text.displayString = XMLFileJava.readElement("TextDisabled").contentEquals("false")? "Disable Text" : "Enable Text";
					XMLFileJava.load();
				}
				case 7:{
					XMLFileJava.checkFileAndMake();
					if(XMLFileJava.readElement("SoundEffects").contentEquals("false")) {
						XMLFileJava.editElement("SoundEffects", "true");
					} else XMLFileJava.editElement("SoundEffects", "false");
					this.enable_or_disable_soundeffects.displayString = XMLFileJava.readElement("SoundEffects").contentEquals("false") ? "Enable Sound Effects" : "Disable Sound Effects";
					XMLFileJava.load();
				}
				default:
					break;
				}
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);

	}

	public void confirmClicked(boolean result, int id) {
		if (id == 31102009) {
			if (result) {
				try {
					this.openWebLink(new URI("https://discord.com/invite/ty6gQaD"));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			mc.displayGuiScreen(this);
		}
	}

	private void openWebLink(URI url) {
		try {
			Class<?> oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop").invoke((Object) null);
			oclass.getMethod("browse", URI.class).invoke(object, url);
		} catch (Throwable throwable1) {
			Throwable throwable = throwable1.getCause();
			BetterUX.logger.error("Couldn't open link: {}", (Object) (throwable == null ? "<UNKNOWN>" : throwable.getMessage()));
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.drawDefaultBackground();
		ScaledResolution scaledresolution = new ScaledResolution(mc);
		int posX = 0;
		int posY = scaledresolution.getScaledHeight() - 40;
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.DISCORD_BACKGROUND));
		GlStateManager.enableBlend();
		BetterOverlay.blit(posX, posY, 0, 0, 120, 40, 120, 40);
		GlStateManager.disableBlend();

		super.drawScreen(p_render_1_, p_render_2_, p_render_3_);

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.DISCORD));
		GlStateManager.scale(0.1F, 0.1F, 0.1F);
		GlStateManager.enableBlend();
		posX = 6;
		posY = scaledresolution.getScaledHeight() - 28;
		BetterOverlay.blit(posX * 10, posY * 10, 0, 0, 160, 160, 160, 160);
		GlStateManager.disableBlend();
		GlStateManager.scale(10.0F, 10.0F, 10.0F);
		mc.fontRenderer.drawString("Better UX", posX + 28, posY + 4, 0xFFFFFFFF);
	}

	public List<GuiButton> getButtons() {
		return this.buttonList;
	}

}
