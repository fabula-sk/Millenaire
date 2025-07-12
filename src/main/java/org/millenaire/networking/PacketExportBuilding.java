package org.millenaire.networking;

import org.millenaire.building.PlanIO;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.core.BlockPos;

public class PacketExportBuilding {

	BlockPos pos;
	
	public PacketExportBuilding() {
		
	}
	
	public PacketExportBuilding(BlockPos startPos) { this.pos = startPos; }
        public static void encode(PacketExportBuilding msg, FriendlyByteBuf buf) {
                buf.writeInt(msg.pos.getX());
                buf.writeInt(msg.pos.getY());
                buf.writeInt(msg.pos.getZ());
        }

        public static PacketExportBuilding decode(FriendlyByteBuf buf) {
                return new PacketExportBuilding(new BlockPos(buf.readInt(), buf.readInt(), buf.readInt()));
        }

        public static void handle(PacketExportBuilding msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> PlanIO.exportBuilding(ctx.get().getSender(), msg.pos));
                ctx.get().setPacketHandled(true);
        }

}
