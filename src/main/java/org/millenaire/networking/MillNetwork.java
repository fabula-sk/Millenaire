package org.millenaire.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.millenaire.Millenaire;

public class MillNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Millenaire.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    private static int nextId() {
        return id++;
    }

    public static void init() {
        CHANNEL.registerMessage(nextId(), PacketSayTranslatedMessage.class,
                PacketSayTranslatedMessage::encode,
                PacketSayTranslatedMessage::decode,
                PacketSayTranslatedMessage::handle);
        CHANNEL.registerMessage(nextId(), PacketImportBuilding.class,
                PacketImportBuilding::encode,
                PacketImportBuilding::decode,
                PacketImportBuilding::handle);
        CHANNEL.registerMessage(nextId(), PacketExportBuilding.class,
                PacketExportBuilding::encode,
                PacketExportBuilding::decode,
                PacketExportBuilding::handle);
    }
}
