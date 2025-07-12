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

    public static void init() {
        int id = 0;
        CHANNEL.messageBuilder(PacketSayTranslatedMessage.class, id++)
                .encoder(PacketSayTranslatedMessage::encode)
                .decoder(PacketSayTranslatedMessage::decode)
                .consumer(PacketSayTranslatedMessage::handle)
                .add();
        CHANNEL.messageBuilder(PacketImportBuilding.class, id++)
                .encoder(PacketImportBuilding::encode)
                .decoder(PacketImportBuilding::decode)
                .consumer(PacketImportBuilding::handle)
                .add();
        CHANNEL.messageBuilder(PacketExportBuilding.class, id++)
                .encoder(PacketExportBuilding::encode)
                .decoder(PacketExportBuilding::decode)
                .consumer(PacketExportBuilding::handle)
                .add();
    }
}
