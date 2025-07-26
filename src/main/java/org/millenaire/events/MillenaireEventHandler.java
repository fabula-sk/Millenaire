package org.millenaire.events;

import org.millenaire.PlayerTracker;

import net.minecraft.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MillenaireEventHandler {

	@SubscribeEvent
        public void onEntityConstructing(EntityJoinWorldEvent event) {
                if (event.getEntity() instanceof Player && PlayerTracker.get((Player) event.getEntity()) == null)
                {
                        PlayerTracker.register((Player) event.getEntity());
                }
        }
}