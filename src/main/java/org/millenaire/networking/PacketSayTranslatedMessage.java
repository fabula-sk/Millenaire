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
                buf.writeInt(msg.message.length());
                for(char c : msg.message.toCharArray()) {
                        buf.writeChar(c);
                }
        }

        public static PacketSayTranslatedMessage decode(FriendlyByteBuf buf) {
                int length = buf.readInt();
                char[] chars = new char[length];
                for(int i = 0; i < length; i++) {
                        chars[i] = buf.readChar();
                }
                return new PacketSayTranslatedMessage(String.copyValueOf(chars));
        }

        public static void handle(PacketSayTranslatedMessage msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> Minecraft.getInstance().player.sendSystemMessage(Component.translatable(msg.message)));
                ctx.get().setPacketHandled(true);
        }
	
}