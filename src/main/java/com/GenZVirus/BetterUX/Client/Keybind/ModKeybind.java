package com.GenZVirus.BetterUX.Client.Keybind;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeybind {

	// This class sets up keybinds
	
	 public static final KeyBinding SETTINGS = new KeyBinding("Settings", Keyboard.KEY_NONE, "Better UX");

	 
	 // Registering keybings
	 
	    public static void register()
	    {
	    	ClientRegistry.registerKeyBinding(SETTINGS);

	    }
	
}
