package org.millenaire.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class PacketSayTranslatedMessage {

	String message;
	
	public PacketSayTranslatedMessage() {
		
	}
	
	public PacketSayTranslatedMessage(String message) { this.message = message; }
	

        public static void encode(PacketSayTranslatedMessage msg, FriendlyByteBuf buf) {
                buf.writeUtf(msg.message);
        }

        public static PacketSayTranslatedMessage decode(FriendlyByteBuf buf) {
                return new PacketSayTranslatedMessage(buf.readUtf());
        }

        public static void handle(PacketSayTranslatedMessage msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> Minecraft.getInstance().player.sendSystemMessage(Component.translatable(msg.message)));
                ctx.get().setPacketHandled(true);
        }
	
}