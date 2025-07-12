package org.millenaire.networking;

import org.millenaire.building.PlanIO;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.core.BlockPos;

public class PacketImportBuilding {

	BlockPos pos;
	
	public PacketImportBuilding() {
		
	}
	
	public PacketImportBuilding(BlockPos startPos) { this.pos = startPos; }
        public static void encode(PacketImportBuilding msg, FriendlyByteBuf buf) {
                buf.writeInt(msg.pos.getX());
                buf.writeInt(msg.pos.getY());
                buf.writeInt(msg.pos.getZ());
        }

        public static PacketImportBuilding decode(FriendlyByteBuf buf) {
                return new PacketImportBuilding(new BlockPos(buf.readInt(), buf.readInt(), buf.readInt()));
        }

        public static void handle(PacketImportBuilding msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> PlanIO.importBuilding(ctx.get().getSender(), msg.pos));
                ctx.get().setPacketHandled(true);
        }

}
