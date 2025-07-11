package org.millenaire.networking;

import org.millenaire.building.PlanIO;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketExportBuilding implements IMessage {

	BlockPos pos;
	
	public PacketExportBuilding() {
		
	}
	
	public PacketExportBuilding(BlockPos startPos) { this.pos = startPos; }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int x, y, z;
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		pos = new BlockPos(x, y, z);
	}

	@Override
        public void toBytes(ByteBuf buf) {
                buf.writeInt(pos.getX());
                buf.writeInt(pos.getY());
                buf.writeInt(pos.getZ());
        }

        public static void encode(PacketExportBuilding msg, PacketBuffer buf) {
                buf.writeInt(msg.pos.getX());
                buf.writeInt(msg.pos.getY());
                buf.writeInt(msg.pos.getZ());
        }

        public static PacketExportBuilding decode(PacketBuffer buf) {
                return new PacketExportBuilding(new BlockPos(buf.readInt(), buf.readInt(), buf.readInt()));
        }

        public static void handle(PacketExportBuilding msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> PlanIO.exportBuilding(ctx.get().getSender(), msg.pos));
                ctx.get().setPacketHandled(true);
        }

	public static class Handler implements IMessageHandler<PacketExportBuilding, IMessage> {

		@Override
		public IMessage onMessage(PacketExportBuilding message, MessageContext ctx) {
                        ServerLifecycleHooks.getCurrentServer().addScheduledTask(() -> handle(message, ctx));
			return null;
		}
		
		private void handle(PacketExportBuilding message, MessageContext ctx) {
			PlanIO.exportBuilding(ctx.getServerHandler().playerEntity, message.pos);
		}
	}
}
