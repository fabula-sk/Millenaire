package org.millenaire.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSayTranslatedMessage implements IMessage {

	String message;
	
	public PacketSayTranslatedMessage() {
		
	}
	
	public PacketSayTranslatedMessage(String message) { this.message = message; }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int length = buf.readInt();
		char[] chars = new char[length];
		
		for(int i = 0; i < length; i++) {
			chars[i] = buf.readChar();
		}
		
		message = String.copyValueOf(chars);
	}

	@Override
        public void toBytes(ByteBuf buf) {
                buf.writeInt(message.length());

                for(char c : message.toCharArray()) {
                        buf.writeChar(c);
                }
        }

        public static void encode(PacketSayTranslatedMessage msg, PacketBuffer buf) {
                buf.writeInt(msg.message.length());
                for(char c : msg.message.toCharArray()) {
                        buf.writeChar(c);
                }
        }

        public static PacketSayTranslatedMessage decode(PacketBuffer buf) {
                int length = buf.readInt();
                char[] chars = new char[length];
                for(int i = 0; i < length; i++) {
                        chars[i] = buf.readChar();
                }
                return new PacketSayTranslatedMessage(String.copyValueOf(chars));
        }

        public static void handle(PacketSayTranslatedMessage msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation(msg.message)));
                ctx.get().setPacketHandled(true);
        }
	
	public static class Handler implements IMessageHandler<PacketSayTranslatedMessage, IMessage> {

		@Override
		public IMessage onMessage(PacketSayTranslatedMessage message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> handle(message, ctx));
			return null;
		}
		
		private void handle(PacketSayTranslatedMessage message, MessageContext ctx) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation(message.message));
		}
	}
}