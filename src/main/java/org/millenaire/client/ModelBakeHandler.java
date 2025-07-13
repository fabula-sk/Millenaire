package org.millenaire.client;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelBakeHandler {
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        // register special models here if needed
    }
}
