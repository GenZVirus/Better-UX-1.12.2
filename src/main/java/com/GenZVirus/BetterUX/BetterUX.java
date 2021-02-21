package com.GenZVirus.BetterUX;

import org.apache.logging.log4j.Logger;

import com.GenZVirus.BetterUX.Client.Events.BetterOverlayEvents;
import com.GenZVirus.BetterUX.Client.Events.ItemToolTipEventsHandler;
import com.GenZVirus.BetterUX.Util.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BetterUX.MOD_ID, name = BetterUX.NAME, version = BetterUX.VERSION)
public class BetterUX
{
    public static final String MOD_ID = "betterux";
    public static final String NAME = "Better UX";
    public static final String VERSION = "1.0";

    @Instance
    public static BetterUX instance;
    
	public static Logger logger;

    @SidedProxy(clientSide="com.GenZVirus.BetterUX.Util.ClientOnlyProxy", serverSide="com.GenZVirus.BetterUX.Util.DedicatedServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit();
        logger = event.getModLog();
        BetterOverlayEvents BUXEvent = new BetterOverlayEvents();
        ItemToolTipEventsHandler BUXItemEvent = new ItemToolTipEventsHandler();
		MinecraftForge.EVENT_BUS.register(BUXEvent);
		MinecraftForge.EVENT_BUS.register(BUXItemEvent);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    
}
