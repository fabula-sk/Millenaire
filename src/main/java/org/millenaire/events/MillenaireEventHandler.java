package org.millenaire.events;

import org.millenaire.PlayerTracker;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MillenaireEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof Player && PlayerTracker.get((Player) event.entity) == null)
		{
			PlayerTracker.register((Player) event.entity);
		}
	}
}