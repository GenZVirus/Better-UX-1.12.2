package com.GenZVirus.BetterUX.Client.GUI;

import java.io.IOException;
import java.util.List;

import com.GenZVirus.BetterUX.Client.Events.BetterOverlayEvents;
import com.GenZVirus.BetterUX.Client.File.XMLFileJava;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;

public class EditOverlay extends GuiScreen {

	private SelectedOverlay leftShield, rightShield, healthBar, foodBar, expBar, fireBar, airBar, bossBar, effect;
	private Minecraft mc = Minecraft.getMinecraft();

	public static EditOverlay instance = new EditOverlay("EditOverlay");

	public EditOverlay(String titleIn) {

	}

	@Override
	public void initGui() {
		leftShield = new SelectedOverlay(0, BetterOverlay.leftShieldPosX - 30, BetterOverlay.leftShieldPosY - 2, 44, 16, "", BetterOverlayEvents.mainWindowWidth / 2, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.LeftShieldPosX += number;
				BetterOverlay.updateLeftShieldPosX();
				leftShield.x = BetterOverlay.leftShieldPosX - 30;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.LeftShieldPosY += number;
				BetterOverlay.updateLeftShieldPosY();
				leftShield.y = BetterOverlay.leftShieldPosY - 2;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.LeftShieldPosX -= number;
				BetterOverlay.updateLeftShieldPosX();
				leftShield.x = BetterOverlay.leftShieldPosX - 30;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.LeftShieldPosY -= number;
				BetterOverlay.updateLeftShieldPosY();
				leftShield.y = BetterOverlay.leftShieldPosY - 2;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.LeftShieldPosX = X;
				BetterOverlay.updateLeftShieldPosX();
				leftShield.x = BetterOverlay.leftShieldPosX - 30;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.LeftShieldPosY = Y;
				BetterOverlay.updateLeftShieldPosY();
				leftShield.y = BetterOverlay.leftShieldPosY - 2;
				super.setY(Y);
			}
		};
		rightShield = new SelectedOverlay(1, BetterOverlay.rightShieldPosX - 2, BetterOverlay.rightShieldPosY - 2, 44, 16, "", BetterOverlayEvents.mainWindowWidth / 2, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.RightShieldPosX += number;
				BetterOverlay.updateRightShieldPosX();
				rightShield.x = BetterOverlay.rightShieldPosX - 2;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.RightShieldPosY += number;
				BetterOverlay.updateRightShieldPosY();
				rightShield.y = BetterOverlay.rightShieldPosY - 2;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.RightShieldPosX -= number;
				BetterOverlay.updateRightShieldPosX();
				rightShield.x = BetterOverlay.rightShieldPosX - 2;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.RightShieldPosY -= number;
				BetterOverlay.updateRightShieldPosY();
				rightShield.y = BetterOverlay.rightShieldPosY - 2;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.RightShieldPosX = X;
				BetterOverlay.updateRightShieldPosX();
				rightShield.x = BetterOverlay.rightShieldPosX - 2;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.RightShieldPosY = Y;
				BetterOverlay.updateRightShieldPosY();
				rightShield.y = BetterOverlay.rightShieldPosY - 2;
				super.setY(Y);
			}
		};
		healthBar = new SelectedOverlay(2, BetterOverlay.HealthPosX, BetterOverlay.HealthPosY, 90, 10, "", BetterOverlayEvents.mainWindowWidth / 2, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.HealthBarPosX += number;
				BetterOverlay.updateHealthPosX();
				healthBar.x = BetterOverlay.HealthPosX;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.HealthBarPosY += number;
				BetterOverlay.updateHealthPosY();
				healthBar.y = BetterOverlay.HealthPosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.HealthBarPosX -= number;
				BetterOverlay.updateHealthPosX();
				healthBar.x = BetterOverlay.HealthPosX;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.HealthBarPosY -= number;
				BetterOverlay.updateHealthPosY();
				healthBar.y = BetterOverlay.HealthPosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.HealthBarPosX = X;
				BetterOverlay.updateHealthPosX();
				healthBar.x = BetterOverlay.HealthPosX;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.HealthBarPosY = Y;
				BetterOverlay.updateHealthPosY();
				healthBar.y = BetterOverlay.HealthPosY;
				super.setY(Y);
			}
		};
		foodBar = new SelectedOverlay(3, BetterOverlay.foodPosX - 90, BetterOverlay.foodPosY, 90, 10, "", BetterOverlayEvents.mainWindowWidth / 2 - 90, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.FoodBarPosX += number;
				BetterOverlay.updateFoodPosX();
				foodBar.x = BetterOverlay.foodPosX - 90;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.FoodBarPosY += number;
				BetterOverlay.updateFoodPosY();
				foodBar.y = BetterOverlay.foodPosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.FoodBarPosX -= number;
				BetterOverlay.updateFoodPosX();
				foodBar.x = BetterOverlay.foodPosX - 90;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.FoodBarPosY -= number;
				BetterOverlay.updateFoodPosY();
				foodBar.y = BetterOverlay.foodPosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.FoodBarPosX = X;
				BetterOverlay.updateFoodPosX();
				foodBar.x = BetterOverlay.foodPosX - 90;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.FoodBarPosY = Y;
				BetterOverlay.updateFoodPosY();
				foodBar.y = BetterOverlay.foodPosY;
				super.setY(Y);
			}
		};
		expBar = new SelectedOverlay(4, BetterOverlay.expPosX, BetterOverlay.expPosY, 182, 16, "", BetterOverlayEvents.mainWindowWidth / 2, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.ExpBarPosX += number;
				BetterOverlay.updateExpPosX();
				expBar.x = BetterOverlay.expPosX;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.ExpBarPosY += number;
				BetterOverlay.updateExpPosY();
				expBar.y = BetterOverlay.expPosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.ExpBarPosX -= number;
				BetterOverlay.updateExpPosX();
				expBar.x = BetterOverlay.expPosX;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.ExpBarPosY -= number;
				BetterOverlay.updateExpPosY();
				expBar.y = BetterOverlay.expPosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.ExpBarPosX = X;
				BetterOverlay.updateExpPosX();
				expBar.x = BetterOverlay.expPosX;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.ExpBarPosY = Y;
				BetterOverlay.updateExpPosY();
				expBar.y = BetterOverlay.expPosY;
				super.setY(Y);
			}
		};
		fireBar = new SelectedOverlay(5, BetterOverlay.firePosX, BetterOverlay.firePosY, 200, 32, "", BetterOverlayEvents.mainWindowWidth / 2, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.FirePosX += number;
				BetterOverlay.updateFirePosX();
				fireBar.x = BetterOverlay.firePosX;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.FirePosY += number;
				BetterOverlay.updateFirePosY();
				fireBar.y = BetterOverlay.firePosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.FirePosX -= number;
				BetterOverlay.updateFirePosX();
				fireBar.x = BetterOverlay.firePosX;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.FirePosY -= number;
				BetterOverlay.updateFirePosY();
				fireBar.y = BetterOverlay.firePosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.FirePosX = X;
				BetterOverlay.updateFirePosX();
				fireBar.x = BetterOverlay.firePosX;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.FirePosY = Y;
				BetterOverlay.updateFirePosY();
				fireBar.y = BetterOverlay.firePosY;
				super.setY(Y);
			}
		};
		airBar = new SelectedOverlay(6, BetterOverlay.airPosX, BetterOverlay.airPosY, 182, 16, "", BetterOverlayEvents.mainWindowWidth / 2, BetterOverlayEvents.mainWindowHeight) {
			@Override
			public void addX(int number) {
				BetterOverlay.AirBarPosX += number;
				BetterOverlay.updateAirPosX();
				airBar.x = BetterOverlay.airPosX;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.AirBarPosY += number;
				BetterOverlay.updateAirPosY();
				airBar.y = BetterOverlay.airPosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.AirBarPosX -= number;
				BetterOverlay.updateAirPosX();
				airBar.x = BetterOverlay.airPosX;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.AirBarPosY -= number;
				BetterOverlay.updateAirPosY();
				airBar.y = BetterOverlay.airPosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.AirBarPosX = X;
				BetterOverlay.updateAirPosX();
				airBar.x = BetterOverlay.airPosX;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.AirBarPosY = Y;
				BetterOverlay.updateAirPosY();
				airBar.y = BetterOverlay.airPosY;
				super.setY(Y);
			}
		};
		bossBar = new SelectedOverlay(7, BetterOverlay.bossPosX, BetterOverlay.bossPosY, 320, 32, "", BetterOverlayEvents.mainWindowWidth / 2, 0) {
			@Override
			public void addX(int number) {
				BetterOverlay.BossBarPosX += number;
				BetterOverlay.updateBossPosX();
				bossBar.x = BetterOverlay.bossPosX;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.BossBarPosY += number;
				BetterOverlay.updateBossPosY();
				bossBar.y = BetterOverlay.bossPosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.BossBarPosX -= number;
				BetterOverlay.updateBossPosX();
				bossBar.x = BetterOverlay.bossPosX;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.BossBarPosY -= number;
				BetterOverlay.updateBossPosY();
				bossBar.y = BetterOverlay.bossPosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				BetterOverlay.BossBarPosX = X;
				BetterOverlay.updateBossPosX();
				bossBar.x = BetterOverlay.bossPosX;
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.BossBarPosY = Y;
				BetterOverlay.updateBossPosY();
				bossBar.y = BetterOverlay.bossPosY;
				super.setY(Y);
			}
		};
		effect = new SelectedOverlay(8, BetterOverlay.effectsPosX - 25, BetterOverlay.effectsPosY, 24, 24, "", 0, 0) {
			@Override
			public void addX(int number) {
				BetterOverlay.EffectsPosX += number;
				BetterOverlay.updateEffectsPosX();
				effect.x = BetterOverlay.effectsPosX - 25;
				super.addX(number);
			}

			@Override
			public void addY(int number) {
				BetterOverlay.EffectsPosY += number;
				BetterOverlay.updateEffectsPosY();
				effect.y = BetterOverlay.effectsPosY;
				super.addY(number);
			}

			@Override
			public void substractX(int number) {
				BetterOverlay.EffectsPosX -= number;
				BetterOverlay.updateEffectsPosX();
				effect.x = BetterOverlay.effectsPosX - 25;
				super.substractX(number);
			}

			@Override
			public void substractY(int number) {
				BetterOverlay.EffectsPosY -= number;
				BetterOverlay.updateEffectsPosY();
				effect.y = BetterOverlay.effectsPosY;
				super.substractY(number);
			}

			@Override
			public void setX(int X) {
				if (BetterOverlay.effectsPosX > BetterOverlayEvents.mainWindowWidth / 2) {
					BetterOverlay.EffectsPosX = X - BetterOverlayEvents.mainWindowWidth + 25;
					BetterOverlay.updateEffectsPosX();
					effect.x = BetterOverlay.effectsPosX - 25;
				} else {
					BetterOverlay.EffectsPosX = X - BetterOverlayEvents.mainWindowWidth - 25;
					BetterOverlay.updateEffectsPosX();
					effect.x = BetterOverlay.effectsPosX + 25;
				}
				super.setX(X);
			}

			@Override
			public void setY(int Y) {
				BetterOverlay.EffectsPosY = Y;
				BetterOverlay.updateEffectsPosY();
				effect.y = BetterOverlay.effectsPosY;
				super.setY(Y);
			}
		};
		this.addButton(leftShield);
		this.addButton(rightShield);
		this.addButton(healthBar);
		this.addButton(foodBar);
		this.addButton(expBar);
		this.addButton(fireBar);
		this.addButton(airBar);
		this.addButton(bossBar);
		this.addButton(effect);
	}

	@Override
	public void drawScreen(int p_render_1_, int p_render_2_, float p_render_3_) {
		if (!mc.player.isBurning()) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FIRE));
			BetterOverlay.blit(BetterOverlay.firePosX, BetterOverlay.firePosY, 0, 0, 200, 32, 200, 5792);
		}
		if (!mc.player.isInsideOfMaterial(Material.WATER) || !(mc.player.getAir() < 300)) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR));
			BetterOverlay.blit(BetterOverlay.airPosX, BetterOverlay.airPosY, 0, 0, 182, 16, 182, 16);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR_FILL));
			BetterOverlay.blit(BetterOverlay.airPosX + 1, BetterOverlay.airPosY + 1, 0, 0, 180, 14, 180, 14);
		}

		if (mc.player.getTotalArmorValue() <= 0) {
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_LEFT));
			BetterOverlay.blit(BetterOverlay.leftShieldPosX * 2, BetterOverlay.leftShieldPosY * 2, 0, 0, 64, 64, 64, 64);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_RIGHT));
			BetterOverlay.blit(BetterOverlay.rightShieldPosX * 2, BetterOverlay.rightShieldPosY * 2, 0, 0, 64, 64, 64, 64);
			GlStateManager.scale(2.0F, 2.0F, 2.0F);
		}
		if (BetterOverlayEvents.checkForBosses <= 0) {
			int posX = BetterOverlay.bossPosX;
			int posY = BetterOverlay.bossPosY;
			GlStateManager.scale(1.0F, 0.8F, 1.0F);
			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BOSS_BAR_BACKGROUND));
			BetterOverlay.blit(posX, (int) (posY * 1.25F), 0, 0, 320, 40, 320, 40);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BOSS_BAR_HEALTH));
			BetterOverlay.blit(posX + 35, (int) (posY * 1.25F), 0, 0, 250, 40, 250, 40);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BOSS_BAR_OVERLAY));
			BetterOverlay.blit(posX, (int) (posY * 1.25F), 0, 0, 320, 40, 320, 40);
			GlStateManager.disableBlend();
			GlStateManager.scale(1.0F, 1.25F, 1.0F);
		}
		if (mc.player.getActivePotionEffects().isEmpty()) {
			mc.getTextureManager().bindTexture(GuiContainer.INVENTORY_BACKGROUND);
			if (BetterOverlay.effectsPosX > BetterOverlayEvents.mainWindowWidth / 2) {
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX - 25, BetterOverlay.effectsPosY, 141, 166, 24, 24, -90);
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX - 25 - 25, BetterOverlay.effectsPosY, 141, 166, 24, 24, -90);
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX - 25 - 50, BetterOverlay.effectsPosY, 141, 166, 24, 24, -90);
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX - 25, BetterOverlay.effectsPosY + 26, 141, 166, 24, 24, -90);
			} else {
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX + 25, BetterOverlay.effectsPosY, 141, 166, 24, 24, -90);
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX + 25 + 25, BetterOverlay.effectsPosY, 141, 166, 24, 24, -90);
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX + 25 + 50, BetterOverlay.effectsPosY, 141, 166, 24, 24, -90);
				BetterOverlay.drawTexturedModalRect(BetterOverlay.effectsPosX + 25, BetterOverlay.effectsPosY + 26, 141, 166, 24, 24, -90);
			}
		}
		super.drawScreen(p_render_1_, p_render_2_, p_render_3_);

	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		SelectedOverlay overlay = (SelectedOverlay) button;
		overlay.onPress();
		BetterOverlayEvents.mousePressed = true;
	}

	@Override
	public void onGuiClosed() {
		XMLFileJava.save();
		super.onGuiClosed();
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		BetterOverlayEvents.mousePressed = false;
	}

	public List<GuiButton> getButtons() {
		return this.buttonList;
	}

}
