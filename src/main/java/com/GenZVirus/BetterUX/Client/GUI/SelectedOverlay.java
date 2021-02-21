package com.GenZVirus.BetterUX.Client.GUI;

import java.util.List;

import com.GenZVirus.BetterUX.Client.Events.BetterOverlayEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class SelectedOverlay extends GuiButton {

	public boolean isOverlaySelected = false;
	public int scaledX;
	public int scaledY;
	
	public SelectedOverlay(int id, int xIn, int yIn, int widthIn, int heightIn, String msg, int scaledX, int scaledY) {
		super(id, xIn, yIn, widthIn, heightIn, msg);
		this.scaledX = scaledX;
		this.scaledY = scaledY;
	}

	@Override
	public void drawButton(Minecraft mc, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		if(!this.isOverlaySelected) return;
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.COLOR));
		// Square/Rectangle
		
		BetterOverlay.blit(this.x, this.y, 0, 0, 1, this.height, 1, 1);
		BetterOverlay.blit(this.x, this.y, 0, 0, this.width, 1, 1, 1);
		BetterOverlay.blit(this.x + this.width, this.y, 0, 0, 1, this.height, 1, 1);
		BetterOverlay.blit(this.x, this.y + this.height, 0, 0, this.width, 1, 1, 1);
		
		// Position lines
		
		BetterOverlay.blit(this.x + this.width / 2, 0, 0, 0, 1, this.y, 1, 1);
		BetterOverlay.blit(this.x + this.width / 2, this.y + this.height, 0, 0, 1, BetterOverlayEvents.mainWindowHeight - this.height - this.y, 1, 1);
		BetterOverlay.blit(0, this.y + this.height / 2, 0, 0, this.x, 1, 1, 1);
		BetterOverlay.blit(this.x + this.width, this.y + this.height / 2, 0, 0, BetterOverlayEvents.mainWindowWidth - this.width - this.x, 1, 1, 1);
	
	}
	
	public void onPress() {
		for(GuiButton button : EditOverlay.instance.getButtons()) {
			if(button instanceof SelectedOverlay) {
				((SelectedOverlay) button).isOverlaySelected = false;
			}
		}
		this.isOverlaySelected = true;
	}
	
	public void addX(int number) {
	}

	public void addY(int number) {
	}

	public void substractX(int number) {
	}

	public void substractY(int number) {
	}

	public void setX(int X) {
	}

	public void setY(int Y) {
	}
	
	public void resetPosition() {
		
	}
	
	public void onClick(double x, double y) {
		List<GuiButton> buttons = EditOverlay.instance.getButtons();
		for (GuiButton button : buttons) {
			int widthIn = button.x;
			int heightIn = button.y;
			int width = button.width;
			int height = button.height;
			if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
				if (button instanceof SelectedOverlay) {
					((SelectedOverlay) button).onPress();
				} 
			}
		}
	}
	
}
