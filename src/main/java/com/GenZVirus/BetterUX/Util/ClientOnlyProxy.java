package com.GenZVirus.BetterUX.Util;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;
import com.GenZVirus.BetterUX.Client.Keybind.ModKeybind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Loader;

public class ClientOnlyProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
	}

	@Override
	public void init() {
		super.init();
		ModKeybind.register();
		XMLFileJava.load();	
		if(Loader.isModLoaded("vampirism")) {
			BetterOverlay.isVampirismLoaded = true;
		}
	}

	@Override
	public void postInit() {
		super.postInit();
	}

	@Override
	public boolean playerIsInCreativeMode(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
			return entityPlayerMP.interactionManager.isCreative();
		} else if (player instanceof EntityPlayerSP) { return Minecraft.getMinecraft().playerController.isInCreativeMode(); }
		return false;
	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}

}
