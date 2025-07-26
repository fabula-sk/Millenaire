package org.millenaire;

import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

public class RaidEvent 
{
	public RaidEvent()
	{
		
	}
	
	//////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public static class RaidEventHandler
	{
		@SubscribeEvent(priority = EventPriority.NORMAL)
		public void onServerTick(TickEvent.ServerTickEvent event)
		{

			//Call proper command here
		}
	}
}
