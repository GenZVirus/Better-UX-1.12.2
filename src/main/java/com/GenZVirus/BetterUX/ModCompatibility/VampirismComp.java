package com.GenZVirus.BetterUX.ModCompatibility;

import org.lwjgl.opengl.GL11;

import com.GenZVirus.BetterUX.Client.Events.BetterOverlayEvents;
import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;
import com.GenZVirus.BetterUX.Client.GUI.BetterUXResources;

import de.teamlapen.vampirism.api.VReference;
import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.entity.player.vampire.IBloodStats;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.util.FoodStats;

public class VampirismComp {

	private static float playerBloodLevel = 0;
	private static String bloodLevel;

	public static void bloodOverlay() {
		Minecraft mc = Minecraft.getMinecraft();
		int posX = BetterOverlayEvents.mainWindowWidth / 2 + BetterOverlay.FoodBarPosX;
		int posY = BetterOverlayEvents.mainWindowHeight + BetterOverlay.FoodBarPosY;
		if (!VampirismAPI.getFactionPlayerHandler((mc.player)).isInFaction(VReference.VAMPIRE_FACTION)) {

			/***********************************
			 * 
			 * Getting player food stats
			 * 
			 ***********************************/

			FoodStats stats = mc.player.getFoodStats();
			int level = stats.getFoodLevel();

			/***********************************
			 * 
			 * Setting the length of the food bar in percentages based on the current and
			 * the max food level of the player
			 * 
			 ***********************************/

			if (BetterOverlay.playerFoodValue != stats.getFoodLevel()) {
				BetterOverlay.foodPercentage = (int) (88 * level / 20);
				if (BetterOverlay.foodPercentage > 88)
					BetterOverlay.foodPercentage = 88;
			}

			/***********************************
			 * 
			 * Checking it the player has hunger effect If the player has hunger effect
			 * Hunger version of the food bar will bind to the texture manager Otherwise the
			 * normal version of the food bar will bind to the texture manager
			 * 
			 ***********************************/

			if (mc.player.isPotionActive(MobEffects.HUNGER)) {
				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.HUNGER_BAR_FILL));
			} else {
				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_FILL));
			}

			/***********************************
			 * 
			 * Displaying the food bar image to the screen
			 * 
			 ***********************************/

			BetterOverlay.blit(BetterOverlay.foodPosX - 89 + 88 - BetterOverlay.foodPercentage, BetterOverlay.foodPosY + 1, 88 - BetterOverlay.foodPercentage, 0, BetterOverlay.foodPercentage, 8, 88, 8);

			/***********************************
			 * 
			 * Getting the current saturation level of the player
			 * 
			 ***********************************/

			level = (int) stats.getSaturationLevel();

			/***********************************
			 * 
			 * Setting the length of the saturation bar in percentages based on the current
			 * and the max saturation of the player\
			 * 
			 ***********************************/

			if (BetterOverlay.playerSaturationValue != stats.getSaturationLevel()) {
				BetterOverlay.saturationPercentage = (int) (88 * level / 20);
				if (BetterOverlay.saturationPercentage > 88)
					BetterOverlay.saturationPercentage = 88;
			}

			/***********************************
			 * 
			 * Binding the saturation bar image to the texture manager
			 * 
			 ***********************************/

			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.SATURATION_BAR_FILL));

			/***********************************
			 * 
			 * Displaying the saturation bar image on the screen
			 * 
			 ***********************************/

			BetterOverlay.blit(BetterOverlay.foodPosX - 89 + 88 - BetterOverlay.saturationPercentage, BetterOverlay.foodPosY + 1, 88 - BetterOverlay.saturationPercentage, 0, BetterOverlay.saturationPercentage, 8, 88, 8);

			/***********************************
			 * 
			 * RENDERING FADING FOOD AND SATURATION WHILE HOLDING AN EATABLE ITEM
			 * 
			 ***********************************/

			/***********************************
			 * 
			 * Checking if the playing has an eatable item either hand
			 * 
			 ***********************************/

			if (mc.player.getHeldItemMainhand().getItem() instanceof ItemFood || mc.player.getHeldItemOffhand().getItem() instanceof ItemFood) {

				BetterOverlay.wasHoldingFood = true;

				GlStateManager.enableBlend();

				/***********************************
				 * 
				 * Setting the alpha value of the rendering system to the current fading stage
				 * 
				 ***********************************/

				GlStateManager.color(1.0F, 1.0F, 1.0F, BetterOverlayEvents.fading);

				/***********************************
				 * 
				 * Changing the blending function
				 * 
				 ***********************************/

				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				/***********************************
				 * 
				 * Initializing healing and saturation with 0
				 * 
				 ***********************************/

				int healing = 0;
				int Saturation = 0;

				/***********************************
				 * 
				 * Checking if the player has any food in left or right hand saving the values
				 * inside healing and saturation
				 * 
				 ***********************************/

				if (mc.player.getHeldItemMainhand().getItem() instanceof ItemFood) {
					healing = ((ItemFood) mc.player.getHeldItemMainhand().getItem()).getHealAmount(mc.player.getHeldItemMainhand());
					Saturation = (int) Math.min(stats.getSaturationLevel() + (float) healing * ((ItemFood) mc.player.getHeldItemMainhand().getItem()).getSaturationModifier(mc.player.getHeldItemMainhand()) * 2.0F, (float) Math.min(healing + stats.getFoodLevel(), 20));
				} else {
					healing = ((ItemFood) mc.player.getHeldItemOffhand().getItem()).getHealAmount(mc.player.getHeldItemOffhand());
					Saturation = (int) Math.min(stats.getSaturationLevel() + (float) healing * ((ItemFood) mc.player.getHeldItemOffhand().getItem()).getSaturationModifier(mc.player.getHeldItemOffhand()) * 2.0F, (float) Math.min(healing + stats.getFoodLevel(), 20));
				}

				/***********************************
				 * 
				 * Checking if the healing has changed If a change is seen the value stored
				 * global will be updated
				 * 
				 ***********************************/

				if (healing != BetterOverlay.addedFoodValue || healing + BetterOverlay.playerFoodValue > 20) {
					BetterOverlay.addedFoodValue = healing + stats.getFoodLevel() > 20 ? 20 - stats.getFoodLevel() : healing;
					BetterOverlay.addedFoodText = "\u00A7a" + BetterOverlay.addedFoodValue + "+ \u00A7r";
				}

				/***********************************
				 * 
				 * Checking if the saturation has changed If a change is seen the value stored
				 * global will be updated
				 * 
				 ***********************************/

				if (Saturation != BetterOverlay.addedSaturationValue || Saturation + BetterOverlay.playerSaturationValue > 20) {
					BetterOverlay.addedSaturationValue = Saturation + stats.getSaturationLevel() > 20 ? 20 - (int) (stats.getSaturationLevel()) : Saturation;
					BetterOverlay.addedSaturationText = "\u00A7a" + BetterOverlay.addedSaturationValue + "+ \u00A7r";
				}

				/***********************************
				 * 
				 * Checking if the amount of healing added will exceed 20 points of food If it
				 * does the difference between 20 and the current food level will be stored
				 * Otherwise it will stored the amount it heals
				 * 
				 ***********************************/

				level = (stats.getFoodLevel() + healing) <= 20 ? stats.getFoodLevel() + healing : 20;

				/***********************************
				 * 
				 * Setting the length of the food bar in percentages based on the current and
				 * the max food of the player
				 * 
				 ***********************************/

				BetterOverlay.addedFoodPercentage = (int) (88 * level / 20);
				if (BetterOverlay.addedFoodPercentage > 88)
					BetterOverlay.addedFoodPercentage = 88;

				/***********************************
				 * 
				 * Binding the food bar image to the texture manager
				 * 
				 ***********************************/

				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_FILL));

				/***********************************
				 * 
				 * Displaying the fading effect for food bar on the screen
				 * 
				 ***********************************/

				BetterOverlay.blit(BetterOverlay.foodPosX - 89 + 88 - BetterOverlay.addedFoodPercentage, BetterOverlay.foodPosY + 1, 88 - BetterOverlay.addedFoodPercentage, 0, BetterOverlay.addedFoodPercentage, 8, 88, 8);

				/***********************************
				 * 
				 * Checking if the amount of saturation added will exceed 20 points of
				 * saturation\ If it does the difference between 20 and the current saturation
				 * level will be stored Otherwise it will stored the amount it heals
				 * 
				 ***********************************/

				level = (int) ((stats.getSaturationLevel() + Saturation) <= 20 ? stats.getSaturationLevel() + Saturation : 20);

				/***********************************
				 * 
				 * Setting the length of the saturation bar in percentages based on the current
				 * and the max saturation of the player
				 * 
				 ***********************************/

				BetterOverlay.addedSaturationPercentage = (int) (88 * level / 20);
				if (BetterOverlay.addedSaturationPercentage > 88)
					BetterOverlay.addedSaturationPercentage = 88;

				/***********************************
				 * 
				 * Binding the saturation bar image to the texture manager
				 * 
				 ***********************************/

				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.SATURATION_BAR_FILL));

				/***********************************
				 * 
				 * Displaying the fading effect for saturation bar on the screen
				 * 
				 ***********************************/

				BetterOverlay.blit(BetterOverlay.foodPosX - 89 + 88 - BetterOverlay.addedSaturationPercentage, BetterOverlay.foodPosY + 1, 88 - BetterOverlay.addedSaturationPercentage, 0, BetterOverlay.addedSaturationPercentage, 8, 88, 8);

				/***********************************
				 * 
				 * Displaying image transparency
				 * 
				 ***********************************/

				GlStateManager.disableBlend();

				/***********************************
				 * 
				 * Changing all base color percentages to max
				 * 
				 ***********************************/

				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

				/***********************************
				 * 
				 * Checking if the texture has overlays
				 * 
				 ***********************************/

				if (BetterOverlay.HasOverlay) {
					mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_OVERLAY));
					BetterOverlay.blit(BetterOverlay.foodPosX - 90, BetterOverlay.foodPosY, 0, 0, 90, 10, 90, 10);
				}
				/***********************************
				 * 
				 * Checking if player current food status has changed
				 * 
				 ***********************************/

				if (BetterOverlay.playerFoodValue != stats.getFoodLevel() || BetterOverlay.playerSaturationValue != (int) stats.getSaturationLevel()) {

					/***********************************
					 * 
					 * Updating player food status
					 * 
					 ***********************************/

					BetterOverlay.playerFoodValue = stats.getFoodLevel();
					BetterOverlay.playerSaturationValue = (int) stats.getSaturationLevel();
					BetterOverlay.playerFoodText = "" + ((int) stats.getFoodLevel());
					BetterOverlay.playerSaturationText = "" + ((int) stats.getSaturationLevel());
				}

				if (!BetterOverlay.textDisabled) {

					/***********************************
					 * 
					 * Creating display message
					 * 
					 ***********************************/

					BetterOverlay.foodFinal = BetterOverlay.addedFoodText + BetterOverlay.playerFoodText + " | " + BetterOverlay.addedSaturationText + BetterOverlay.playerSaturationText;

					/***********************************
					 * 
					 * Calculating message length
					 * 
					 ***********************************/

					int stringWidth = mc.fontRenderer.getStringWidth(BetterOverlay.foodFinal);

					/***********************************
					 * 
					 * Drawing the message on the screen
					 * 
					 ***********************************/

					mc.fontRenderer.drawString(BetterOverlay.foodFinal, BetterOverlay.foodPosX - 45 - stringWidth / 2, BetterOverlay.foodPosY + 1, 0xFFFFFFFF);
				}
			} else {

				/***********************************
				 * 
				 * Checking if player current food status has changed
				 * 
				 ***********************************/

				if (BetterOverlay.playerFoodValue != stats.getFoodLevel() || BetterOverlay.playerSaturationValue != (int) stats.getSaturationLevel() || BetterOverlay.addedFoodValue != 0 && BetterOverlay.addedSaturationValue != 0 || BetterOverlay.wasHoldingFood) {

					BetterOverlay.wasHoldingFood = false;

					/***********************************
					 * 
					 * Player doesn't hold any eatable items
					 * 
					 ***********************************/

					BetterOverlay.addedFoodText = "";
					BetterOverlay.addedSaturationText = "";
					BetterOverlay.addedFoodValue = 0;
					BetterOverlay.addedSaturationValue = 0;

					/***********************************
					 * 
					 * Updating player food status
					 * 
					 ***********************************/

					BetterOverlay.playerFoodValue = stats.getFoodLevel();
					BetterOverlay.playerSaturationValue = (int) stats.getSaturationLevel();
					BetterOverlay.playerFoodText = "" + ((int) stats.getFoodLevel());
					BetterOverlay.playerSaturationText = "" + ((int) stats.getSaturationLevel());

					/***********************************
					 * 
					 * Creating display message
					 * 
					 ***********************************/

					BetterOverlay.foodFinal = BetterOverlay.addedFoodText + BetterOverlay.playerFoodText + " | " + BetterOverlay.addedSaturationText + BetterOverlay.playerSaturationText;
				}

				/***********************************
				 * 
				 * Checking if the texture has overlays
				 * 
				 ***********************************/

				if (BetterOverlay.HasOverlay) {
					mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_OVERLAY));
					BetterOverlay.blit(BetterOverlay.foodPosX - 90, BetterOverlay.foodPosY, 0, 0, 90, 10, 90, 10);
				}

				if (!BetterOverlay.textDisabled) {

					/***********************************
					 * 
					 * Calculating message length
					 * 
					 ***********************************/

					int stringWidth = mc.fontRenderer.getStringWidth(BetterOverlay.foodFinal);

					/***********************************
					 * 
					 * Drawing the message on the screen
					 * 
					 ***********************************/

					mc.fontRenderer.drawString(BetterOverlay.foodFinal, BetterOverlay.foodPosX - 45 - stringWidth / 2, BetterOverlay.foodPosY + 1, 0xFFFFFFFF);
				}
			}
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
			mc.mcProfiler.endSection();
		} else {
			IBloodStats stats = (IBloodStats) ((IVampirePlayer) VampirismAPI.getFactionPlayerHandler((mc.player)).getCurrentFactionPlayer()).getBloodStats();
			int level = stats.getBloodLevel();
			int maxLevel = stats.getMaxBlood();
			int percentage = (int) (88 * level / maxLevel);
			if (percentage > 88)
				percentage = 88;
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BLOOD_BAR_FILL));
			BetterOverlay.blit(posX - 89 + 88 - percentage, posY + 1, 88 - percentage, 0, percentage, 8, 88, 8);
			
			/***********************************
			 * 
			 * Checking if the texture has overlays
			 * 
			 ***********************************/

			if (BetterOverlay.HasOverlay) {
				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BLOOD_BAR_OVERLAY));
				BetterOverlay.blit(BetterOverlay.foodPosX - 90, BetterOverlay.foodPosY, 0, 0, 90, 10, 90, 10);
			}
			if (!BetterOverlay.textDisabled) {
				if (playerBloodLevel != level) {
					bloodLevel = level + " / " + maxLevel + " | " + "\u00A7dLv " + VampirismAPI.getFactionPlayerHandler((mc.player)).getCurrentLevel();
				}
			int stringWidth = mc.fontRenderer.getStringWidth(bloodLevel);
			mc.fontRenderer.drawString(bloodLevel, posX - 45 - stringWidth / 2, posY + 1, 0xFFFFFFFF);
			} else {
				if (playerBloodLevel != level) {
					bloodLevel = "\u00A7dLv " + VampirismAPI.getFactionPlayerHandler((mc.player)).getCurrentLevel();
				}
				mc.fontRenderer.drawString(bloodLevel, posX + 5, posY + 1, 0xFFFFFFFF);
			}
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
			mc.mcProfiler.endSection();
		}
	}
}
