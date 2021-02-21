package com.GenZVirus.BetterUX.Client.GUI;

import java.util.Collection;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.GenZVirus.BetterUX.Client.Events.BetterOverlayEvents;
import com.GenZVirus.BetterUX.ModCompatibility.VampirismComp;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CombatRules;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BetterOverlay extends Gui {

	private static Minecraft mc = Minecraft.getMinecraft();
	private static int delay = 0;
	public static int fire_offset = 0;

	public static int LeftShieldPosX = 0;
	public static int LeftShieldPosY = 0;
	public static int RightShieldPosX = 0;
	public static int RightShieldPosY = 0;
	public static int HealthBarPosX = 0;
	public static int HealthBarPosY = 0;
	public static int FirePosX = 0;
	public static int FirePosY = 0;
	public static int FoodBarPosX = 0;
	public static int FoodBarPosY = 0;
	public static int AirBarPosX = 0;
	public static int AirBarPosY = 0;
	public static int ExpBarPosX = 0;
	public static int ExpBarPosY = 0;
	public static String Texture;
	public static int BossBarPosX = 0;
	public static int BossBarPosY = 0;
	public static int EffectsPosX = 0;
	public static int EffectsPosY = 0;
	public static String Enabled_Disabled;
	public static boolean HasOverlay = false;
	public static boolean wasHoldingFood = false;
	public static boolean textDisabled = false;
	public static boolean soundEffects = true;

	/***********************************
	 * 
	 * Setting the left shield position
	 * 
	 ***********************************/

	public static int leftShieldPosX = new ScaledResolution(mc).getScaledWidth() / 2 + LeftShieldPosX;
	public static int leftShieldPosY = new ScaledResolution(mc).getScaledHeight() + LeftShieldPosY;

	/***********************************
	 * 
	 * Setting the right shield position
	 * 
	 ***********************************/

	public static int rightShieldPosX = new ScaledResolution(mc).getScaledWidth() / 2 + RightShieldPosX;
	public static int rightShieldPosY = new ScaledResolution(mc).getScaledHeight() + RightShieldPosY;

	/***********************************
	 * 
	 * Setting the position of the health bar and it's other companions
	 * 
	 ***********************************/

	public static int HealthPosX = new ScaledResolution(mc).getScaledWidth() / 2 + HealthBarPosX;
	public static int HealthPosY = new ScaledResolution(mc).getScaledHeight() + HealthBarPosY;

	/***********************************
	 * 
	 * Setting the position of the fire animation
	 * 
	 ***********************************/

	public static int firePosX = new ScaledResolution(mc).getScaledWidth() / 2 + FirePosX;
	public static int firePosY = new ScaledResolution(mc).getScaledHeight() + FirePosY;

	/***********************************
	 * 
	 * Setting the food bar position
	 * 
	 ***********************************/

	public static int foodPosX = new ScaledResolution(mc).getScaledWidth() / 2 + FoodBarPosX;
	public static int foodPosY = new ScaledResolution(mc).getScaledHeight() + FoodBarPosY;

	/***********************************
	 * 
	 * Setting the air bar position
	 * 
	 ***********************************/

	public static int airPosX = new ScaledResolution(mc).getScaledWidth() / 2 + AirBarPosX;
	public static int airPosY = new ScaledResolution(mc).getScaledHeight() + AirBarPosY;

	/***********************************
	 * 
	 * Setting the exp bar position
	 * 
	 ***********************************/

	public static int expPosX = new ScaledResolution(mc).getScaledWidth() / 2 + ExpBarPosX;
	public static int expPosY = new ScaledResolution(mc).getScaledHeight() + ExpBarPosY;

	/***********************************
	 * 
	 * Setting the boss bar position
	 * 
	 ***********************************/

	public static int bossPosX = new ScaledResolution(mc).getScaledWidth() / 2 + BossBarPosX;
	public static int bossPosY = BossBarPosY;
	
	/***********************************
	 * 
	 * Setting the Effects bar position
	 * 
	 ***********************************/

	public static int effectsPosX = new ScaledResolution(mc).getScaledWidth() + EffectsPosX;
	public static int effectsPosY = EffectsPosY;


	/***********************************
	 * 
	 * Global player current health variables
	 * 
	 ***********************************/

	public static float playerHealthValue = -1;
	public static String playerHealthText = "";
	public static int healthPercentage = 0;

	/***********************************
	 * 
	 * Global player food and saturation variables in text and integer format
	 * 
	 ***********************************/

	public static int playerFoodValue = -1;
	public static int playerSaturationValue = -1;
	public static String playerFoodText = "";
	public static String playerSaturationText = "";
	public static int foodPercentage = 0;
	public static int saturationPercentage = 0;
	public static int addedFoodPercentage = 0;
	public static int addedSaturationPercentage = 0;
	public static String foodFinal = "";

	/***********************************
	 * 
	 * Global added food and saturation variables in text and integer format
	 * 
	 ***********************************/

	public static int addedFoodValue = -1;
	public static int addedSaturationValue = -1;
	public static String addedFoodText = "";
	public static String addedSaturationText = "";

	/***********************************
	 * 
	 * Global added left and right shield variables in text and integer format
	 * 
	 ***********************************/

	public static float totalArmorValue = -1;
	public static String leftShieldDamageReductionText = "";
	public static String rightShieldDamageReductionText = "";

	/***********************************
	 * 
	 * Global added player level variables in text and integer format
	 * 
	 ***********************************/

	public static int playerLevelValue = -1;
	public static String playerLevelText = "";

	/***********************************
	 * 
	 * Global added to check if Vampirism is loaded
	 * 
	 ***********************************/

	public static boolean isVampirismLoaded = false;

	/***********************************
	 * 
	 * Checking if the player is inside a world
	 * 
	 ***********************************/

	private static void checkInGame() {
		if (mc.world == null) { return; }

		assert mc.player != null;

		if (!Minecraft.isGuiEnabled()) { return; }
	}

	/***********************************
	 * 
	 * Changing between fire images
	 * 
	 ***********************************/

	private static void incrementOverlayMovement() {
		if (Minecraft.getDebugFPS() / 10 <= delay) {
			fire_offset++;
			delay = 0;
		}
		delay++;

		if (fire_offset > 31) {
			fire_offset = 0;
		}
	}

	/***********************************
	 * 
	 * Rendering everything about the health bar
	 * 
	 ***********************************/

	public static void renderHealth() {

		/***********************************
		 * 
		 * Checking if the player is inside a world
		 * 
		 ***********************************/

		checkInGame();

		/***********************************
		 * 
		 * Fire animation
		 * 
		 ***********************************/

		incrementOverlayMovement();

		/***********************************
		 * 
		 * Checking if the player is in creative mode
		 * 
		 ***********************************/

		if (mc.player.isCreative()) { return; }

		/***********************************
		 * 
		 * Starting the health section in Minecraft profiler
		 * 
		 ***********************************/

		mc.mcProfiler.startSection("BUXHealth");

		/***********************************
		 * 
		 * Setting the rendering scale and color
		 * 
		 ***********************************/

		GlStateManager.scale(1.0F, 1.0F, 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		/***********************************
		 * 
		 * RENDER HEALTH BAR
		 * 
		 ***********************************/

		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		GlStateManager.enableBlend();

		/***********************************
		 * 
		 * Binding the health bar background image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BAR_LEFT));

		/***********************************
		 * 
		 * Displaying the background image
		 * 
		 ***********************************/

		blit(HealthPosX, HealthPosY, 0, 0, 90, 10, 90, 10);

		/***********************************
		 * 
		 * Setting the length of the health bar in percentages based on the current and
		 * the max health of the player
		 * 
		 ***********************************/

		if (playerHealthValue != mc.player.getHealth()) {

			healthPercentage = (int) (88 * mc.player.getHealth() / mc.player.getMaxHealth());
			if (healthPercentage > 88)
				healthPercentage = 88;
		}

		/***********************************
		 * 
		 * Binding the health bar image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.HEALTH_BAR_FILL));

		/***********************************
		 * 
		 * Checking if the player has poison effect If the player is poisoned, the
		 * poisoned version of the health bar will be bound to the texture manager
		 * 
		 ***********************************/

		if (mc.player.isPotionActive(Potion.getPotionById(19))) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.POISONED_HEALTH_BAR_FILL));
		}

		/***********************************
		 * 
		 * Checking if the player has wither effect if the player is withering, the
		 * withering version of the health bar will be bound to the texture manager
		 * 
		 ***********************************/

		if (mc.player.isPotionActive(Potion.getPotionById(20))) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WITHERING_HEALTH_BAR_FILL));
		}

		/***********************************
		 * 
		 * Displaying the health bar on the screen
		 * 
		 ***********************************/

		blit(HealthPosX + 1, HealthPosY + 1, 0, 0, healthPercentage, 8, 88, 8);

		/***********************************
		 * 
		 * Checking if the player has any amount of absorption If the player has any
		 * absorption the shield bar will bind to the texture manager and it will be
		 * displayed on top of the health bar on the screen
		 * 
		 ***********************************/

		if (mc.player.getAbsorptionAmount() > 0) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.SHIELD_BAR));
			blit(HealthPosX, HealthPosY, 0, 0, 90, 10, 90, 10);
		}

		/***********************************
		 * 
		 * Checking if the texture has overlays
		 * 
		 ***********************************/

		if (HasOverlay) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.HEALTH_BAR_OVERLAY));
			blit(HealthPosX, HealthPosY, 0, 0, 90, 10, 90, 10);
		}

		/***********************************
		 * 
		 * Checking if the player is burning
		 * 
		 ***********************************/

		if (mc.player.isBurning()) {

			/***********************************
			 * 
			 * Binding the fire sheet to the texture manager
			 * 
			 ***********************************/

			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FIRE));

			/***********************************
			 * 
			 * Displaying the current image from the fire sheet on the screen
			 * 
			 ***********************************/

			blit(firePosX, firePosY, 0, fire_offset * 32, 200, 32, 200, 5792);
		}

		/***********************************
		 * 
		 * Disable transparency
		 * 
		 ***********************************/

		GlStateManager.disableBlend();

		if (!textDisabled) {

			/***********************************
			 * 
			 * RENDERING HEALTH BAR TEXT
			 * 
			 ***********************************/

			/***********************************
			 * 
			 * Checking if the player current health has changed for an update
			 * 
			 ***********************************/

			if (playerHealthValue != mc.player.getHealth()) {

				/***********************************
				 * 
				 * Updating the player health
				 * 
				 ***********************************/

				playerHealthValue = mc.player.getHealth();

				/***********************************
				 * 
				 * Updating the text health Note: The text health is displayed on the on top of
				 * the health bar
				 * 
				 ***********************************/

				playerHealthText = Integer.toString((int) mc.player.getHealth()) + " / " + Integer.toString((int) mc.player.getMaxHealth());

				/***********************************
				 * 
				 * Checking if the player has any amount of absorption
				 * 
				 ***********************************/

				if (mc.player.getAbsorptionAmount() > 0) {

					/***********************************
					 * 
					 * Appending the absorption amount to the health text if present
					 * 
					 ***********************************/

					playerHealthText += " | " + Integer.toString((int) mc.player.getAbsorptionAmount());
				}
			}

			/***********************************
			 * 
			 * Calculating the text health length
			 * 
			 ***********************************/

			int stringWidth = mc.fontRenderer.getStringWidth(playerHealthText);

			/***********************************
			 * 
			 * Drawing the text on the screen
			 * 
			 ***********************************/

			mc.fontRenderer.drawString(playerHealthText, HealthPosX + 45 - stringWidth / 2, HealthPosY + 1, 0xFFFFFFFF);
		}
		/***********************************
		 * 
		 * Ending the health section
		 * 
		 ***********************************/

		mc.mcProfiler.endSection();
	}

	/***********************************
	 * 
	 * Rendering everything about food bar
	 * 
	 ***********************************/

	public static void renderFood() {

		/***********************************
		 * 
		 * Checking if the player is inside a world
		 * 
		 ***********************************/

		checkInGame();

		/***********************************
		 * 
		 * Checking if the player is in creative mode
		 * 
		 ***********************************/

		if (mc.player.isCreative()) { return; }

		/***********************************
		 * 
		 * Starting a food section in the Minecraft profiler
		 * 
		 ***********************************/

		mc.mcProfiler.startSection("BUXFood");

		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		GlStateManager.enableBlend();

		/***********************************
		 * 
		 * Binding the food bar background image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BAR_RIGHT));

		/***********************************
		 * 
		 * Display food bar background image on the screen
		 * 
		 ***********************************/

		blit(foodPosX - 90, foodPosY, 0, 0, 90, 10, 90, 10);

		if (isVampirismLoaded) {
			VampirismComp.bloodOverlay();
		} else {

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

			if (playerFoodValue != stats.getFoodLevel()) {
				foodPercentage = (int) (88 * level / 20);
				if (foodPercentage > 88)
					foodPercentage = 88;
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

			blit(foodPosX - 89 + 88 - foodPercentage, foodPosY + 1, 88 - foodPercentage, 0, foodPercentage, 8, 88, 8);

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

			if (playerSaturationValue != stats.getSaturationLevel()) {
				saturationPercentage = (int) (88 * level / 20);
				if (saturationPercentage > 88)
					saturationPercentage = 88;
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

			blit(foodPosX - 89 + 88 - saturationPercentage, foodPosY + 1, 88 - saturationPercentage, 0, saturationPercentage, 8, 88, 8);

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

				wasHoldingFood = true;

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

				if (healing != addedFoodValue) {
					addedFoodValue = healing + stats.getFoodLevel() > 20 ? 20 - stats.getFoodLevel() : healing;
					BetterOverlay.addedFoodText = "\u00A7a" + addedFoodValue + "+ \u00A7r";
				}

				/***********************************
				 * 
				 * Checking if the saturation has changed If a change is seen the value stored
				 * global will be updated
				 * 
				 ***********************************/

				if (Saturation != addedSaturationValue) {
					addedSaturationValue = Saturation + stats.getSaturationLevel() > 20 ? 20 - (int) (stats.getSaturationLevel()) : Saturation;
					BetterOverlay.addedSaturationText = "\u00A7a" + addedSaturationValue + "+ \u00A7r";
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

				addedFoodPercentage = (int) (88 * level / 20);
				if (addedFoodPercentage > 88)
					addedFoodPercentage = 88;

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

				blit(foodPosX - 89 + 88 - addedFoodPercentage, foodPosY + 1, 88 - addedFoodPercentage, 0, addedFoodPercentage, 8, 88, 8);

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

				addedSaturationPercentage = (int) (88 * level / 20);
				if (addedSaturationPercentage > 88)
					addedSaturationPercentage = 88;

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

				blit(foodPosX - 89 + 88 - addedSaturationPercentage, foodPosY + 1, 88 - addedSaturationPercentage, 0, addedSaturationPercentage, 8, 88, 8);

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
				 * Checking if player current food status has changed
				 * 
				 ***********************************/

				if (playerFoodValue != stats.getFoodLevel() || playerSaturationValue != (int) stats.getSaturationLevel()) {

					/***********************************
					 * 
					 * Updating player food status
					 * 
					 ***********************************/

					playerFoodValue = stats.getFoodLevel();
					playerSaturationValue = (int) stats.getSaturationLevel();
					playerFoodText = "" + ((int) stats.getFoodLevel());
					playerSaturationText = "" + ((int) stats.getSaturationLevel());
				}

				/***********************************
				 * 
				 * Checking if the texture has overlays
				 * 
				 ***********************************/

				if (BetterOverlay.HasOverlay) {
					mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_OVERLAY));
					blit(BetterOverlay.foodPosX - 90, BetterOverlay.foodPosY, 0, 0, 90, 10, 90, 10);
				}

				if (!textDisabled) {

					/***********************************
					 * 
					 * Creating display message
					 * 
					 ***********************************/

					foodFinal = BetterOverlay.addedFoodText + BetterOverlay.playerFoodText + " | " + BetterOverlay.addedSaturationText + BetterOverlay.playerSaturationText;

					/***********************************
					 * 
					 * Calculating message length
					 * 
					 ***********************************/

					int stringWidth = mc.fontRenderer.getStringWidth(foodFinal);

					/***********************************
					 * 
					 * Drawing the message on the screen
					 * 
					 ***********************************/

					mc.fontRenderer.drawString(foodFinal, foodPosX - 45 - stringWidth / 2, foodPosY + 1, 0xFFFFFFFF);
				}
			} else {

				/***********************************
				 * 
				 * Checking if player current food status has changed
				 * 
				 ***********************************/

				if (playerFoodValue != stats.getFoodLevel() || playerSaturationValue != (int) stats.getSaturationLevel() || addedFoodValue != 0 && addedSaturationValue != 0 || wasHoldingFood) {

					wasHoldingFood = false;

					/***********************************
					 * 
					 * Player doesn't hold any eatable items
					 * 
					 ***********************************/

					addedFoodText = "";
					addedSaturationText = "";
					addedFoodValue = 0;
					addedSaturationValue = 0;

					/***********************************
					 * 
					 * Updating player food status
					 * 
					 ***********************************/

					playerFoodValue = stats.getFoodLevel();
					playerSaturationValue = (int) stats.getSaturationLevel();
					playerFoodText = "" + ((int) stats.getFoodLevel());
					playerSaturationText = "" + ((int) stats.getSaturationLevel());

					/***********************************
					 * 
					 * Creating display message
					 * 
					 ***********************************/

					foodFinal = addedFoodText + playerFoodText + " | " + addedSaturationText + playerSaturationText;
				}

				/***********************************
				 * 
				 * Checking if the texture has overlays
				 * 
				 ***********************************/

				if (BetterOverlay.HasOverlay) {
					mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_OVERLAY));
					blit(BetterOverlay.foodPosX - 90, BetterOverlay.foodPosY, 0, 0, 90, 10, 90, 10);
				}

				if (!textDisabled) {

					/***********************************
					 * 
					 * Calculating message length
					 * 
					 ***********************************/

					int stringWidth = mc.fontRenderer.getStringWidth(foodFinal);

					/***********************************
					 * 
					 * Drawing the message on the screen
					 * 
					 ***********************************/

					mc.fontRenderer.drawString(foodFinal, foodPosX - 45 - stringWidth / 2, foodPosY + 1, 0xFFFFFFFF);
				}
			}
		}

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		GlStateManager.disableBlend();

		/***********************************
		 * 
		 * Ending section of Minecraft profiler
		 * 
		 ***********************************/

		mc.mcProfiler.endSection();
	}

	/***********************************
	 * 
	 * Render everything about armor
	 * 
	 ***********************************/

	public static void renderArmor() {

		/***********************************
		 * 
		 * Checking if the player is inside a world
		 * 
		 ***********************************/

		checkInGame();

		/***********************************
		 * 
		 * Checking if the player is in creative mode
		 * 
		 ***********************************/

		if (mc.player.isCreative()) { return; }

		/***********************************
		 * 
		 * Checking if the player has any armor value
		 * 
		 ***********************************/

		if (mc.player.getTotalArmorValue() <= 0)
			return;

		/***********************************
		 * 
		 * Starting the armor section in Minecraft profiler
		 * 
		 ***********************************/

		mc.mcProfiler.startSection("BUXArmor");

		/***********************************
		 * 
		 * Binding Left Shield image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_LEFT));

		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		GlStateManager.enableBlend();

		/***********************************
		 * 
		 * Getting player's total armor
		 * 
		 ***********************************/

		float totalArmor = mc.player.getTotalArmorValue();

		/***********************************
		 * 
		 * Getting player's toughness
		 * 
		 ***********************************/

		float toughness = (float) mc.player.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue();


		/***********************************
		 * 
		 * Scaling down everything that get's renderer from this point forward
		 * 
		 ***********************************/

		if (!textDisabled) {

			/***********************************
			 * 
			 * Checking if the player total armor has change If the player total armor has
			 * changed the global variables will be updated
			 * 
			 ***********************************/

			if (totalArmorValue != totalArmor) {
				totalArmorValue = totalArmor;
				float damageReduction = Math.round((100.0F - CombatRules.getDamageAfterAbsorb(100, totalArmor, toughness)) * 10) / 10.0F;
				for (ItemStack stack : mc.player.getArmorInventoryList()) {
					damageReduction += EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
				}
				if (damageReduction > 100)
					damageReduction = 100;
				leftShieldDamageReductionText = (damageReduction < 10 ? " " : "") + damageReduction + "%";

				List<String> list = Lists.newArrayList();
				list.add(leftShieldDamageReductionText);

				/***********************************
				 * 
				 * Drawing the damage reduction amount on the left shield
				 * 
				 ***********************************/
				renderLeftShieldText(list, leftShieldPosX - 24 - mc.fontRenderer.getStringWidth(leftShieldDamageReductionText) / 2, leftShieldPosY + 14, mc.fontRenderer);
//				mc.fontRenderer.drawString(new MatrixStack(), leftShieldDamageReductionText, leftShieldPosX + 16 - mc.fontRenderer.getStringWidth(leftShieldDamageReductionText) / 2, leftShieldPosY + 10, 0xFFFFFFFF);

				damageReduction = Math.round(4.0 * totalArmor * 10) / 10;
				for (ItemStack stack : mc.player.getArmorInventoryList()) {
					damageReduction += EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
				}
				if (damageReduction > 100)
					damageReduction = 100;
				rightShieldDamageReductionText = (damageReduction < 10 ? " " : "") + damageReduction + "%";

				list.remove(leftShieldDamageReductionText);
				list.add(rightShieldDamageReductionText);

				/***********************************
				 * 
				 * Drawing the damage reduction amount on the right shield
				 * 
				 ***********************************/
				renderRightShieldText(list, rightShieldPosX + 3 - mc.fontRenderer.getStringWidth(rightShieldDamageReductionText) / 2, rightShieldPosY + 14, mc.fontRenderer);

//				mc.fontRenderer.drawString(new MatrixStack(), rightShieldDamageReductionText, rightShieldPosX + 17 - mc.fontRenderer.getStringWidth(rightShieldDamageReductionText) / 2, rightShieldPosY + 10, 0xFFFFFFFF);
			} else {

				List<String> list = Lists.newArrayList();
				list.add(leftShieldDamageReductionText);

				/***********************************
				 * 
				 * Drawing the damage reduction amount on the left shield
				 * 
				 ***********************************/
				renderLeftShieldText(list, leftShieldPosX - 24 - mc.fontRenderer.getStringWidth(leftShieldDamageReductionText) / 2, leftShieldPosY + 14, mc.fontRenderer);
//				mc.fontRenderer.drawString(new MatrixStack(), leftShieldDamageReductionText, leftShieldPosX + 16 - mc.fontRenderer.getStringWidth(leftShieldDamageReductionText) / 2, leftShieldPosY + 10, 0xFFFFFFFF);

				list.remove(leftShieldDamageReductionText);
				/***********************************
				 * 
				 * Drawing the damage reduction amount on the right shield
				 * 
				 ***********************************/
				list.add(rightShieldDamageReductionText);
				renderRightShieldText(list, rightShieldPosX + 3 - mc.fontRenderer.getStringWidth(rightShieldDamageReductionText) / 2, rightShieldPosY + 14, mc.fontRenderer);
//				mc.fontRenderer.drawString(new MatrixStack(), rightShieldDamageReductionText, rightShieldPosX + 17 - mc.fontRenderer.getStringWidth(rightShieldDamageReductionText) / 2, rightShieldPosY + 10, 0xFFFFFFFF);

			}
		}
		
		/***********************************
		 * 
		 * Scaling down everything that get's renderer from this point forward
		 * 
		 ***********************************/

		GlStateManager.scale(0.2F, 0.2F, 0.2F);
		
		/***********************************
		 * 
		 * Binding Left Shield image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_LEFT));

		/***********************************
		 * 
		 * Displaying the left shield on the screen
		 * 
		 ***********************************/

		blit(leftShieldPosX * 5, leftShieldPosY * 5, 0, 0, 64, 64, 64, 64);

		/***********************************
		 * 
		 * Binding the right shield to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_RIGHT));

		/***********************************
		 * 
		 * Displaying the right shield on the screen
		 * 
		 ***********************************/

		blit(rightShieldPosX * 5, rightShieldPosY * 5, 0, 0, 64, 64, 64, 64);

		/***********************************
		 * 
		 * Scaling back up everything to the original dimensions
		 * 
		 ***********************************/

		GlStateManager.scale(5.0F, 5.0F, 5.0F);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		GlStateManager.disableBlend();

		/***********************************
		 * 
		 * Ending section of the profiler
		 * 
		 ***********************************/

		mc.mcProfiler.endSection();
	}

	/***********************************
	 * 
	 * Render everything about exp bar
	 * 
	 ***********************************/

	public static void renderExpBar() {

		/***********************************
		 * 
		 * Checking if the player is inside a world
		 * 
		 ***********************************/

		checkInGame();

		/***********************************
		 * 
		 * Setting rendering colors to max value
		 * 
		 ***********************************/

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		/***********************************
		 * 
		 * Checking if the player is in creative mode or spectator mode
		 * 
		 ***********************************/

		if (!mc.player.isCreative() && !mc.player.isSpectator()) {

			/***********************************
			 * 
			 * Starting exp section in Minecraft profiler
			 * 
			 ***********************************/

			mc.mcProfiler.startSection("BUXExpBar");

			/***********************************
			 * 
			 * Binding exp bar background image to texture manager
			 * 
			 ***********************************/

			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.EXP_BAR));

			/***********************************
			 * 
			 * Enable image transparency
			 * 
			 ***********************************/

			GlStateManager.enableBlend();

			/***********************************
			 * 
			 * Calculating current player experience
			 * 
			 ***********************************/

			int k = (int) (mc.player.experience * 182.0F);

			/***********************************
			 * 
			 * Displaying exp bar background image on the screen
			 * 
			 ***********************************/

			blit(expPosX, expPosY, 0, 0, 182, 16, 182, 16);

			/***********************************
			 * 
			 * Checking if current experience is > 0
			 * 
			 ***********************************/

			if (k > 0) {

				/***********************************
				 * 
				 * Binding exp bar image to texture manager
				 * 
				 ***********************************/

				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.EXP_BAR_FILL));

				/***********************************
				 * 
				 * Displaying exp bar image on the screen
				 * 
				 ***********************************/

				blit(expPosX, expPosY, 0, 0, k, 16, 182, 16);
			}

			/***********************************
			 * 
			 * Ending exp section from Minecraft profiler
			 * 
			 ***********************************/

			mc.mcProfiler.endSection();
		}

		/***********************************
		 * 
		 * Checking if current experience is > 0
		 * 
		 ***********************************/

		if (mc.player.experienceLevel > 0) {

			/***********************************
			 * 
			 * Starting exp level section in Minecraft profiler
			 * 
			 ***********************************/

			mc.mcProfiler.startSection("BUXExpLevel");

			/***********************************
			 * 
			 * Checking if player current level has changed If any change is seen global
			 * variables will be updated
			 * 
			 ***********************************/

			if (mc.player.experienceLevel != playerLevelValue) {
				playerFoodValue = mc.player.experienceLevel;
				playerLevelText = "" + mc.player.experienceLevel;
			}

			/***********************************
			 * 
			 * Scaling down the rendering system
			 * 
			 ***********************************/

			GlStateManager.scale(0.8F, 0.8F, 0.8F);

			/***********************************
			 * 
			 * Drawing the experience level on the screen
			 * 
			 ***********************************/

			mc.fontRenderer.drawString(playerLevelText, (int) ((expPosX + 93 - mc.fontRenderer.getStringWidth(playerLevelText) / 2) * 1.25F), (int) ((expPosY + 5) * 1.25F), 0xFFFFFFFF);

			/***********************************
			 * 
			 * Scaling back up the rendering system
			 * 
			 ***********************************/

			GlStateManager.scale(1.25F, 1.25F, 1.25F);

			/***********************************
			 * 
			 * Ending exp level section from Minecraft Profiler
			 * 
			 ***********************************/

			mc.mcProfiler.endSection();
		}

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		GlStateManager.disableBlend();

	}

	/***********************************
	 * 
	 * Render everything about air bar
	 * 
	 ***********************************/

	public static void renderAirBar() {

		/***********************************
		 * 
		 * Checking if the player is inside a world
		 * 
		 ***********************************/

		checkInGame();

		/***********************************
		 * 
		 * Setting rendering colors to max value
		 * 
		 ***********************************/

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		/***********************************
		 * 
		 * Checking if the player is under water
		 * 
		 ***********************************/

		if (mc.player.isInsideOfMaterial(Material.WATER) || mc.player.getAir() < 300) {

			/***********************************
			 * 
			 * Starting air bar section in Minecraft profiler
			 * 
			 ***********************************/

			mc.mcProfiler.startSection("BUXWaterBreathingBar");

			/***********************************
			 * 
			 * Binding air bar background image to the texture manager
			 * 
			 ***********************************/

			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR));

			/***********************************
			 * 
			 * Enable image transparency
			 * 
			 ***********************************/

			GlStateManager.enableBlend();

			/***********************************
			 * 
			 * Calculating air amount
			 * 
			 ***********************************/

			int k = (int) (((float) mc.player.getAir() / 300) * 182.0F);

			/***********************************
			 * 
			 * Displaying air bar background image on the screen
			 * 
			 ***********************************/

			blit(airPosX, airPosY, 0, 0, 182, 16, 182, 16);

			/***********************************
			 * 
			 * Checking if the player has any amount of air left
			 * 
			 ***********************************/

			if (k > 0) {

				/***********************************
				 * 
				 * Binding air bar image to the texture manager
				 * 
				 ***********************************/

				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR_FILL));

				/***********************************
				 * 
				 * Displaying air bar image on the screen
				 * 
				 ***********************************/

				blit(airPosX, airPosY, 0, 0, k, 16, 182, 16);
			}

			/***********************************
			 * 
			 * Checking if the texture has overlays
			 * 
			 ***********************************/

			if (BetterOverlay.HasOverlay) {
				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATING_BAR_OVERLAY));
				blit(airPosX, airPosY, 0, 0, 182, 16, 182, 16);
			}

			/***********************************
			 * 
			 * Disable image transparency
			 * 
			 ***********************************/

			GlStateManager.disableBlend();

			/***********************************
			 * 
			 * Ending air bar section in Minecraft profiler
			 * 
			 ***********************************/

			mc.mcProfiler.endSection();
		}
	}

	public static void renderBossBar(EntityLiving boss) {

		/***********************************
		 * 
		 * Checking if the player is inside a world
		 * 
		 ***********************************/

		checkInGame();

		/***********************************
		 * 
		 * Setting rendering colors to max value
		 * 
		 ***********************************/

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		/***********************************
		 * 
		 * Getting boss health percentage
		 * 
		 ***********************************/

		float percentage = boss.getHealth() / boss.getMaxHealth();

		/***********************************
		 * 
		 * Getting boss health name
		 * 
		 ***********************************/

		String name = boss.getName();

		/***********************************
		 * 
		 * Starting boss bar section in Minecraft profiler
		 * 
		 ***********************************/

		mc.mcProfiler.startSection("BUXBossBar");

		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		GlStateManager.enableBlend();

		/***********************************
		 * 
		 * Scaling down render system's height
		 * 
		 ***********************************/

		GlStateManager.scale(1.0F, 0.8F, 1.0F);

		/***********************************
		 * 
		 * Binding boss bar background image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BOSS_BAR_BACKGROUND));

		/***********************************
		 * 
		 * Displaying boss bar background image on the screen
		 * 
		 ***********************************/

		blit(bossPosX, (int) (bossPosY * 1.25F), 0, 0, 320, 40, 320, 40);

		/***********************************
		 * 
		 * Binding boss bar health image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BOSS_BAR_HEALTH));

		/***********************************
		 * 
		 * Displaying boss bar health image on the screen
		 * 
		 ***********************************/

		blit(bossPosX + 35, (int) (bossPosY * 1.25F), 0, 0, (int) (percentage * 250), 40, 250, 40);

		/***********************************
		 * 
		 * Binding boss bar overlay image to the texture manager
		 * 
		 ***********************************/

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BOSS_BAR_OVERLAY));

		/***********************************
		 * 
		 * Displaying boss bar overlay image on the screen
		 * 
		 ***********************************/

		blit(bossPosX, (int) (bossPosY * 1.25F), 0, 0, 320, 40, 320, 40);

		/***********************************
		 * 
		 * Calculating boss name width
		 * 
		 ***********************************/

		int stringWidth = mc.fontRenderer.getStringWidth(name);

		/***********************************
		 * 
		 * Drawing the name of the boss on the screen
		 * 
		 ***********************************/

		mc.fontRenderer.drawString(name, bossPosX + 160 - stringWidth / 2, (int) ((bossPosY + 5) * 1.25F), 0xFFFFFFFF);

		if (!textDisabled) {

			/***********************************
			 * 
			 * Calculating boss health percentage
			 * 
			 ***********************************/

			percentage = (float) ((int) (percentage * 10000) / 100.0F);

			/***********************************
			 * 
			 * Converting boss health percentage to text
			 * 
			 ***********************************/

			String percentageText = Float.toString(percentage) + "%";

			/***********************************
			 * 
			 * Calculating boss health percentage width as a text
			 * 
			 ***********************************/

			stringWidth = mc.fontRenderer.getStringWidth(percentageText);

			/***********************************
			 * 
			 * Drawing boss health percentage on the screen
			 * 
			 ***********************************/

			mc.fontRenderer.drawString(percentageText, bossPosX + 160 - stringWidth / 2, (int) ((bossPosY + 21) * 1.25F), 0xFFFFFFFF);
		}
		/***********************************
		 * 
		 * Scaling back up render system's height
		 * 
		 ***********************************/

		GlStateManager.scale(1.0F, 1.25F, 1.0F);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		GlStateManager.disableBlend();

		/***********************************
		 * 
		 * Ending boss bar section in Minecraft profiler
		 * 
		 ***********************************/

		mc.mcProfiler.endSection();
	}

	public static void renderPotionEffects(ScaledResolution resolution) {
		Collection<PotionEffect> collection = mc.player.getActivePotionEffects();

		if (!collection.isEmpty()) {
			mc.getTextureManager().bindTexture(GuiContainer.INVENTORY_BACKGROUND);
			GlStateManager.enableBlend();
			int i = 0;
			int j = 0;

			for (PotionEffect potioneffect : Ordering.natural().reverse().sortedCopy(collection)) {
				Potion potion = potioneffect.getPotion();

				if (!potion.shouldRenderHUD(potioneffect))
					continue;
				// Rebind in case previous renderHUDEffect changed texture
				mc.getTextureManager().bindTexture(GuiContainer.INVENTORY_BACKGROUND);
				if (potioneffect.doesShowParticles()) {
					int k = effectsPosX;
					int l = effectsPosY;

					if (mc.isDemo()) {
						l += 15;
					}

					int i1 = potion.getStatusIconIndex();

					if (potion.isBeneficial()) {
						++i;
						k = k - 25 * i;
					} else {
						++j;
						k = k - 25 * j;
						l += 26;
					}

					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					float f = 1.0F;

					if (potioneffect.getIsAmbient()) {
						drawTexturedModalRect(k, l, 165, 166, 24, 24, -90);
					} else {
						drawTexturedModalRect(k, l, 141, 166, 24, 24, -90);

						if (potioneffect.getDuration() <= 200) {
							int j1 = 10 - potioneffect.getDuration() / 20;
							f = MathHelper.clamp((float) potioneffect.getDuration() / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + MathHelper.cos((float) potioneffect.getDuration() * (float) Math.PI / 5.0F) * MathHelper.clamp((float) j1 / 10.0F * 0.25F, 0.0F, 0.25F);
						}
					}

					GlStateManager.color(1.0F, 1.0F, 1.0F, f);
					// FORGE - Move status icon check down from above so renderHUDEffect will still
					// be called without a status icon
					if (potion.hasStatusIcon())
						drawTexturedModalRect(k + 3, l + 3, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18, -90);
					potion.renderHUDEffect(potioneffect, mc.currentScreen, k, l, -90, f);
				}
			}
			GlStateManager.disableBlend();
		}
	}

	/***********************************
	 * 
	 * UPDATE ALL BARS POSITIONS
	 * 
	 ***********************************/

	public static void updatePositions() {

		/***********************************
		 * 
		 * Update the left shield position
		 * 
		 ***********************************/

		updateLeftShieldPosX();
		updateLeftShieldPosY();

		/***********************************
		 * 
		 * Update the right shield position
		 * 
		 ***********************************/

		updateRightShieldPosX();
		updateRightShieldPosY();

		/***********************************
		 * 
		 * Update health bar
		 * 
		 ***********************************/

		updateHealthPosX();
		updateHealthPosY();

		/***********************************
		 * 
		 * Update fire animation
		 * 
		 ***********************************/

		updateFirePosX();
		updateFirePosY();

		/***********************************
		 * 
		 * Update food bar
		 * 
		 ***********************************/

		updateFoodPosX();
		updateFoodPosY();

		/***********************************
		 * 
		 * Update air bar
		 * 
		 ***********************************/

		updateAirPosX();
		updateAirPosY();

		/***********************************
		 * 
		 * Update exp bar
		 * 
		 ***********************************/

		updateExpPosX();
		updateExpPosY();

		/***********************************
		 * 
		 * Update boss bar
		 * 
		 ***********************************/

		updateBossPosX();
		updateBossPosY();

		/***********************************
		 * 
		 * Update effects
		 * 
		 ***********************************/

		updateEffectsPosX();
		updateEffectsPosY();
	}

	/***********************************
	 * 
	 * Update health bar
	 * 
	 ***********************************/

	public static void updateHealthPosX() {
		HealthPosX = BetterOverlayEvents.mainWindowWidth / 2 + HealthBarPosX;
		if (HealthPosX < 0) {
			HealthPosX = 0;
		}
		if (HealthPosX > BetterOverlayEvents.mainWindowWidth - 90) {
			HealthPosX = BetterOverlayEvents.mainWindowWidth - 90;
		}
	}

	public static void updateHealthPosY() {
		HealthPosY = BetterOverlayEvents.mainWindowHeight + HealthBarPosY;
		if (HealthPosY < 0) {
			HealthPosY = 0;
		}
		if (HealthPosY > BetterOverlayEvents.mainWindowHeight - 10) {
			HealthPosY = BetterOverlayEvents.mainWindowHeight - 10;
		}
	}

	/***********************************
	 * 
	 * Update the left shield position
	 * 
	 ***********************************/

	public static void updateLeftShieldPosX() {
		leftShieldPosX = BetterOverlayEvents.mainWindowWidth / 2 + LeftShieldPosX;
		if (leftShieldPosX < 0) {
			leftShieldPosX = 0;
		}
		if (leftShieldPosX > BetterOverlayEvents.mainWindowWidth - 32) {
			leftShieldPosX = BetterOverlayEvents.mainWindowWidth - 32;
		}
	}

	public static void updateLeftShieldPosY() {
		leftShieldPosY = BetterOverlayEvents.mainWindowHeight + LeftShieldPosY;
		if (leftShieldPosY < 0) {
			leftShieldPosY = 0;
		}
		if (leftShieldPosY > BetterOverlayEvents.mainWindowHeight - 32) {
			leftShieldPosY = BetterOverlayEvents.mainWindowHeight - 32;
		}
	}

	/***********************************
	 * 
	 * Update the right shield position
	 * 
	 ***********************************/

	public static void updateRightShieldPosX() {
		rightShieldPosX = BetterOverlayEvents.mainWindowWidth / 2 + RightShieldPosX;
		if (rightShieldPosX < 0) {
			rightShieldPosX = 0;
		}
		if (rightShieldPosX > BetterOverlayEvents.mainWindowWidth - 32) {
			rightShieldPosX = BetterOverlayEvents.mainWindowWidth - 32;
		}
	}

	public static void updateRightShieldPosY() {
		rightShieldPosY = BetterOverlayEvents.mainWindowHeight + RightShieldPosY;
		if (rightShieldPosY < 0) {
			rightShieldPosY = 0;
		}
		if (rightShieldPosY > BetterOverlayEvents.mainWindowHeight - 32) {
			rightShieldPosY = BetterOverlayEvents.mainWindowHeight - 32;
		}
	}

	/***********************************
	 * 
	 * Update fire animation
	 * 
	 ***********************************/

	public static void updateFirePosX() {
		firePosX = BetterOverlayEvents.mainWindowWidth / 2 + FirePosX;
		if (firePosX < 0) {
			firePosX = 0;
		}
		if (firePosX > BetterOverlayEvents.mainWindowWidth - 200) {
			firePosX = BetterOverlayEvents.mainWindowWidth - 200;
		}
	}

	public static void updateFirePosY() {
		firePosY = BetterOverlayEvents.mainWindowHeight + FirePosY;
		if (firePosY < 0) {
			firePosY = 0;
		}
		if (firePosY > BetterOverlayEvents.mainWindowHeight - 32) {
			firePosY = BetterOverlayEvents.mainWindowHeight - 32;
		}
	}

	/***********************************
	 * 
	 * Update food bar
	 * 
	 ***********************************/

	public static void updateFoodPosX() {
		foodPosX = BetterOverlayEvents.mainWindowWidth / 2 + FoodBarPosX;
		if (foodPosX < 90) {
			foodPosX = 90;
		}
		if (foodPosX > BetterOverlayEvents.mainWindowWidth) {
			foodPosX = BetterOverlayEvents.mainWindowWidth;
		}
	}

	public static void updateFoodPosY() {
		foodPosY = BetterOverlayEvents.mainWindowHeight + FoodBarPosY;
		if (foodPosY < 0) {
			foodPosY = 0;
		}
		if (foodPosY > BetterOverlayEvents.mainWindowHeight - 10) {
			foodPosY = BetterOverlayEvents.mainWindowHeight - 10;
		}
	}

	/***********************************
	 * 
	 * Update air bar
	 * 
	 ***********************************/

	public static void updateAirPosX() {
		airPosX = BetterOverlayEvents.mainWindowWidth / 2 + AirBarPosX;
		if (airPosX < 0) {
			airPosX = 0;
		}
		if (airPosX > BetterOverlayEvents.mainWindowWidth - 182) {
			airPosX = BetterOverlayEvents.mainWindowWidth - 182;
		}
	}

	public static void updateAirPosY() {
		airPosY = BetterOverlayEvents.mainWindowHeight + AirBarPosY;
		if (airPosY < 0) {
			airPosY = 0;
		}
		if (airPosY > BetterOverlayEvents.mainWindowHeight - 16) {
			airPosY = BetterOverlayEvents.mainWindowHeight - 16;
		}
	}

	/***********************************
	 * 
	 * Update exp bar
	 * 
	 ***********************************/

	public static void updateExpPosX() {
		expPosX = BetterOverlayEvents.mainWindowWidth / 2 + ExpBarPosX;
		if (expPosX < 0) {
			expPosX = 0;
		}
		if (expPosX > BetterOverlayEvents.mainWindowWidth - 182) {
			expPosX = BetterOverlayEvents.mainWindowWidth - 182;
		}
	}

	public static void updateExpPosY() {
		expPosY = BetterOverlayEvents.mainWindowHeight + ExpBarPosY;
		if (expPosY < 0) {
			expPosY = 0;
		}
		if (expPosY > BetterOverlayEvents.mainWindowHeight - 16) {
			expPosY = BetterOverlayEvents.mainWindowHeight - 16;
		}
	}

	/***********************************
	 * 
	 * Update boss bar
	 * 
	 ***********************************/

	public static void updateBossPosX() {
		bossPosX = BetterOverlayEvents.mainWindowWidth / 2 + BossBarPosX;
		if (bossPosX < 0) {
			bossPosX = 0;
		}
		if (bossPosX > BetterOverlayEvents.mainWindowWidth - 320) {
			bossPosX = BetterOverlayEvents.mainWindowWidth - 320;
		}
	}

	public static void updateBossPosY() {
		bossPosY = BossBarPosY;
		if (bossPosY < 0) {
			bossPosY = 0;
		}
		if (bossPosY > BetterOverlayEvents.mainWindowHeight - (int) (40 * 0.8F)) {
			bossPosY = BetterOverlayEvents.mainWindowHeight - (int) (40 * 0.8F);
		}
	}
	
	/***********************************
	 * 
	 * Update Effects
	 * 
	 ***********************************/

	public static void updateEffectsPosX() {
		effectsPosX = BetterOverlayEvents.mainWindowWidth + EffectsPosX;
		if (effectsPosX < -25) {
			effectsPosX = -25;
		}
		if (effectsPosX > BetterOverlayEvents.mainWindowWidth) {
			effectsPosX = BetterOverlayEvents.mainWindowWidth;
		}
	}

	public static void updateEffectsPosY() {
		effectsPosY = EffectsPosY;
		if (effectsPosY < 0) {
			effectsPosY = 0;
		}
		if (effectsPosY > BetterOverlayEvents.mainWindowHeight - 51) {
			effectsPosY = BetterOverlayEvents.mainWindowHeight - 51;
		}
	}

	public static void blit(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
		float f = 1.0F / textureWidth;
		float f1 = 1.0F / textureHeight;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
		bufferbuilder.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
		bufferbuilder.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
		tessellator.draw();
	}
	
	@SuppressWarnings("unused")
	public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, int zLevel)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
	
	@SuppressWarnings({ "unused" })
	public static void renderLeftShieldText(List<String> textLines, int x, int y, FontRenderer font) {
		if (!textLines.isEmpty()) {
			GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int i = 0;

            for (String s : textLines)
            {
                int j = 36;

                if (j > i)
                {
                    i = j;
                }
            }

            int l1 = x + 12;
            int i2 = y - 12;
            int k = 9;

            int width = new ScaledResolution(mc).getScaledWidth();
            int height = new ScaledResolution(mc).getScaledHeight();
            
            if (textLines.size() > 1)
            {
                k += 2 + (textLines.size() - 1) * 10;
            }

            if (l1 + i > width)
            {
                l1 -= 28 + i;
            }

            if (i2 + k + 6 > height)
            {
                i2 = height - k - 6;
            }

            int l = -267386864;
            fillGradient(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
            fillGradient(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
            fillGradient(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
            fillGradient(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
            fillGradient(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
            int i1 = 1347420415;
            int j1 = 1344798847;
            fillGradient(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
            fillGradient(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
            fillGradient(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
            fillGradient(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);

            for (int k1 = 0; k1 < textLines.size(); ++k1)
            {
                String s1 = textLines.get(k1);
                mc.fontRenderer.drawStringWithShadow(s1, (float)l1, (float)i2 + 0.5F, -1);

                if (k1 == 0)
                {
                    i2 += 2;
                }

                i2 += 10;
            }

            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
		}
	}

	@SuppressWarnings({ "unused" })
	public static void renderRightShieldText(List<String> textLines, int x, int y, FontRenderer font) {
		if (!textLines.isEmpty()) {
			GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int i = 0;

            for (String s : textLines)
            {
                int j = 36;

                if (j > i)
                {
                    i = j;
                }
            }

            int l1 = x + 12;
            int i2 = y - 12;
            int k = 9;

            int width = new ScaledResolution(mc).getScaledWidth();
            int height = new ScaledResolution(mc).getScaledHeight();
            
            if (textLines.size() > 1)
            {
                k += 2 + (textLines.size() - 1) * 10;
            }

            if (l1 + i > width)
            {
                l1 -= 28 + i;
            }

            if (i2 + k + 6 > height)
            {
                i2 = height - k - 6;
            }

            int l = -267386864;
            fillGradient(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
            fillGradient(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
            fillGradient(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
            fillGradient(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
            fillGradient(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
            int i1 = 1347420415;
            int j1 = 1344798847;
            fillGradient(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
            fillGradient(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
            fillGradient(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
            fillGradient(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);

            for (int k1 = 0; k1 < textLines.size(); ++k1)
            {
                String s1 = textLines.get(k1);
                mc.fontRenderer.drawStringWithShadow(s1, (float)l1 + 11, (float)i2 + 0.5F, -1);

                if (k1 == 0)
                {
                    i2 += 2;
                }

                i2 += 10;
            }

            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
		}
	}
	
	public static void fillGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
		float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)-90).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)-90).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)-90).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)-90).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
	}

}
