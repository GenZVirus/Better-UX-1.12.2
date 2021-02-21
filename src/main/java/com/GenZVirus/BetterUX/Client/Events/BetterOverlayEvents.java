package com.GenZVirus.BetterUX.Client.Events;

import java.util.List;

import com.GenZVirus.BetterUX.BetterUX;
import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;
import com.GenZVirus.BetterUX.Client.GUI.ChangeTextures;
import com.GenZVirus.BetterUX.Client.GUI.EditOverlay;
import com.GenZVirus.BetterUX.Client.GUI.SelectedOverlay;
import com.GenZVirus.BetterUX.Client.GUI.Settings;
import com.GenZVirus.BetterUX.Util.KeyboardHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class BetterOverlayEvents {

	public static Minecraft mc = Minecraft.getMinecraft();
	private static int cooldown = 0;
	public static boolean mousePressed = false;
	public static float fading = 1.0F;
	public static boolean increase = false;

	public static int mainWindowWidth = 0;
	public static int mainWindowHeight = 0;

	public static int checkForBosses = 0;
	public static int SEcooldown = 0;

	public static ISound heartbeat;

	@SubscribeEvent
	public void soundEffects(ClientTickEvent event) {
		if (mc.world == null) { return; }

		assert mc.player != null;
		if (!BetterOverlay.soundEffects) { return; }
		if (mc.player.getHealth() > mc.player.getMaxHealth() / 2)
			return;
		if (SEcooldown <= 0) {
			EntityPlayer player = mc.player;
			BlockPos pos = player.getPosition();
			if (heartbeat == null) {
				if (mc.player.getHealth() > mc.player.getMaxHealth() / 4) {
					heartbeat = new PositionedSoundRecord(new SoundEvent(new ResourceLocation(BetterUX.MOD_ID, "heartbeat")), SoundCategory.MASTER, 1.0f, 1.0f, pos);
				} else {
					heartbeat = new PositionedSoundRecord(new SoundEvent(new ResourceLocation(BetterUX.MOD_ID, "heartbeat2")), SoundCategory.MASTER, 1.0f, 1.0f, pos);
				}
			} else {
				BlockPos currentPos = new BlockPos(heartbeat.getXPosF(), heartbeat.getYPosF(), heartbeat.getZPosF());
				if (mc.getSoundHandler().isSoundPlaying(heartbeat))
					return;
				if (!currentPos.equals(pos)) {
					if (mc.player.getHealth() > mc.player.getMaxHealth() / 4) {
						heartbeat = new PositionedSoundRecord(new SoundEvent(new ResourceLocation(BetterUX.MOD_ID, "heartbeat")), SoundCategory.MASTER, 1.0f, 1.0f, pos);
						SEcooldown = 40;
					} else {
						heartbeat = new PositionedSoundRecord(new SoundEvent(new ResourceLocation(BetterUX.MOD_ID, "heartbeat2")), SoundCategory.MASTER, 1.0f, 1.0f, pos);
						SEcooldown = 20;
					}
				}
			}
			if(heartbeat.equals(new PositionedSoundRecord(new SoundEvent(new ResourceLocation(BetterUX.MOD_ID, "heartbeat")), SoundCategory.MASTER, 1.0f, 1.0f, pos))) {
				SEcooldown = 40;
			} else {
				SEcooldown = 20;
			}
			mc.getSoundHandler().playSound(heartbeat);
		} else {
			SEcooldown--;
		}
	}

	@SubscribeEvent
	public void stopEffects(DrawScreenEvent event) {
		if(event.getGui() instanceof Settings || event.getGui() instanceof EditOverlay || event.getGui() instanceof ChangeTextures)
			SEcooldown = 60;
	}
	
	@SubscribeEvent(receiveCanceled = true)
	public void Overlay(RenderGameOverlayEvent.Pre event) {
		if (BetterOverlay.Enabled_Disabled.contentEquals("disabled"))
			return;
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
			event.setCanceled(true);
			BetterOverlay.renderHealth();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
			event.setCanceled(true);
			BetterOverlay.renderFood();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR) {
			event.setCanceled(true);
			BetterOverlay.renderArmor();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
			event.setCanceled(true);
			BetterOverlay.renderExpBar();
			;
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
			event.setCanceled(true);
			BetterOverlay.renderAirBar();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
			event.setCanceled(true);
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.JUMPBAR) {
			event.setCanceled(true);
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO) {
			event.setCanceled(true);
			checkForBosses = 100;
		}
		if(event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
			event.setCanceled(true);
			BetterOverlay.renderPotionEffects(new ScaledResolution(mc));
		}
	}

	@SubscribeEvent(receiveCanceled = true)
	public void FireOverlay(RenderBlockOverlayEvent event) {
		if (BetterOverlay.Enabled_Disabled.contentEquals("disabled"))
			return;
		if (event.getOverlayType() == OverlayType.FIRE) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onPlayerLogging(PlayerLoggedInEvent event) {
		XMLFileJava.load();
	}

	@SubscribeEvent
	public void checkForClosestBoss(RenderGameOverlayEvent.Pre event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.CHAT)
			return;
		if (checkForBosses <= 0)
			return;
		EntityPlayer player = mc.player;
		AxisAlignedBB aabb = new AxisAlignedBB(player.getPosition()).grow(100);
		List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(player, aabb);
		float minDist = 200;
		Entity Boss = null;
		for (Entity entity : list) {
			if (!entity.isNonBoss() && entity instanceof EntityLiving) {
				float dist = entity.getDistance(player);
				if (dist < minDist) {
					minDist = dist;
					Boss = entity;
				}
			}
		}
		if (Boss != null) {
			BetterOverlay.renderBossBar((EntityLiving) Boss);
		}
	}

	@SubscribeEvent
	public void checkForBoss(ClientTickEvent event) {
		if (checkForBosses > 0) {
			checkForBosses--;
		}
	}

	@SubscribeEvent
	public void cooldown(ClientTickEvent event) {
		if (cooldown > 0) {
			cooldown--;
		}
	}

	@SubscribeEvent
	public void MenuOptions(ClientTickEvent event) {
		if (KeyboardHelper.isHoldingSettings() && !(mc.ingameGUI.getChatGUI().getChatOpen()) && mc.currentScreen == ((GuiScreen) null)) {
			mc.displayGuiScreen(Settings.instance);
		}
	}

	@SubscribeEvent
	public void updateWindowSize(DrawScreenEvent event) {
		ScaledResolution res = new ScaledResolution(mc);
		if (res.getScaledWidth() != mainWindowWidth || res.getScaledHeight() != mainWindowHeight) {
			mainWindowHeight = res.getScaledHeight();
			mainWindowWidth = res.getScaledWidth();
			BetterOverlay.updatePositions();
			if (event.getGui() instanceof EditOverlay) {
				for (GuiButton button : ((EditOverlay) event.getGui()).getButtons()) {
					if (button instanceof SelectedOverlay) {
						((SelectedOverlay) button).resetPosition();
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void Arrows(DrawScreenEvent event) {
		if (!(event.getGui() instanceof EditOverlay))
			return;
		SelectedOverlay overlay = null;
		for (GuiButton button : EditOverlay.instance.getButtons()) {
			if (((SelectedOverlay) button).isOverlaySelected) {
				overlay = (SelectedOverlay) button;
				break;
			}
		}

		if (overlay == null)
			return;

		// UP

		if (KeyboardHelper.isHoldingUP() && cooldown == 0) {
			overlay.substractY(1);
			cooldown = 3;
		}

		// DOWN

		if (KeyboardHelper.isHoldingDOWN() && cooldown == 0) {
			overlay.addY(1);
			cooldown = 3;
		}

		// RIGHT

		if (KeyboardHelper.isHoldingRIGHT() && cooldown == 0) {
			overlay.addX(1);
			cooldown = 3;
		}

		// LEFT

		if (KeyboardHelper.isHoldingLEFT() && cooldown == 0) {
			overlay.substractX(1);
			cooldown = 3;
		}
		if (mousePressed) {
			overlay.setX(event.getMouseX() - overlay.scaledX - overlay.width / 2);
			overlay.setY(event.getMouseY() - overlay.scaledY - overlay.height / 2);
		}
	}

	@SubscribeEvent
	public void fadingTick(ClientTickEvent event) {
		if (increase) {
			fading += 0.05;
		} else {
			fading -= 0.05;
		}
		if (fading < 0.0F) {
			increase = true;
		}
		if (fading > 1.0F) {
			increase = false;
		}
	}

}
