package com.GenZVirus.BetterUX.Client.Events;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemFood;
import net.minecraft.util.FoodStats;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemToolTipEventsHandler {

	@SubscribeEvent
	public void heavyTitaniumArmorSet(ItemTooltipEvent event) {
		if (event.getEntityPlayer() == null)
			return;
		if (!(event.getItemStack().getItem() instanceof ItemFood))
			return;
		FoodStats stats = event.getEntityPlayer().getFoodStats();
		int healing = 0;
		int Saturation = 0;

		healing = ((ItemFood) event.getItemStack().getItem()).getHealAmount(event.getItemStack());
		Saturation = (int) Math.min(stats.getSaturationLevel() + (float) healing * ((ItemFood) event.getItemStack().getItem()).getSaturationModifier(event.getItemStack()) * 2.0F, (float) Math.min(healing + stats.getFoodLevel(), 20));
	
		List<String> text = Lists.newArrayList();

		text.add("");

		// Add Food
		text.add("\u00A7l" + "Food Value: " + "\u00A7r" + "\u00A76" + healing);

		// Add Saturation
		text.add("\u00A7l" + "Saturation Value: " + "\u00A7r" + "\u00A7e" + Saturation);
		
		event.getToolTip().addAll(text);
	}
}
