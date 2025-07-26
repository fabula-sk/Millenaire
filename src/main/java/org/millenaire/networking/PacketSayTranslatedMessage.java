package org.millenaire.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class PacketSayTranslatedMessage {

	String message;
	
	public PacketSayTranslatedMessage() {
		
	}
	
	public PacketSayTranslatedMessage(String message) { this.message = message; }
	

        public static void encode(PacketSayTranslatedMessage msg, PacketBuffer buf) {
                buf.writeUtf(msg.message);
        }

        public static PacketSayTranslatedMessage decode(PacketBuffer buf) {
                return new PacketSayTranslatedMessage(buf.readUtf());
        }

        public static void handle(PacketSayTranslatedMessage msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> Minecraft.getInstance().player.sendMessage(new TranslationTextComponent(msg.message), Minecraft.getInstance().player.getUniqueID()));
                ctx.get().setPacketHandled(true);
        }
	
}