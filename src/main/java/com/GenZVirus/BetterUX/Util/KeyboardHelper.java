package com.GenZVirus.BetterUX.Util;

import org.lwjgl.input.Keyboard;

import com.GenZVirus.BetterUX.Client.Keybind.ModKeybind;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyboardHelper {

	// Checks if the up is being held down

	@SideOnly(Side.CLIENT)
	public static boolean isHoldingUP() {
		return Keyboard.isKeyDown(Keyboard.KEY_UP);
	}

	// Checks if the down is being held down

	@SideOnly(Side.CLIENT)
	public static boolean isHoldingDOWN() {
		return Keyboard.isKeyDown(Keyboard.KEY_DOWN);
	}

	// Checks if the right is being held down

	@SideOnly(Side.CLIENT)
	public static boolean isHoldingRIGHT() {
		return Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
	}

	// Checks if the left is being held down

	@SideOnly(Side.CLIENT)
	public static boolean isHoldingLEFT() {
		return Keyboard.isKeyDown(Keyboard.KEY_LEFT);
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isHoldingSettings() {
		return Keyboard.isKeyDown(ModKeybind.SETTINGS.getKeyCode());
	}
}
