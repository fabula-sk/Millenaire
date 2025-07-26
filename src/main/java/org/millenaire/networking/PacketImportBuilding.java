package org.millenaire.networking;

import org.millenaire.building.PlanIO;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.core.BlockPos;

public class PacketImportBuilding {

	BlockPos pos;
	
	public PacketImportBuilding() {
		
	}
	
	public PacketImportBuilding(BlockPos startPos) { this.pos = startPos; }
        public static void encode(PacketImportBuilding msg, PacketBuffer buf) {
                buf.writeBlockPos(msg.pos);
        }

        public static PacketImportBuilding decode(PacketBuffer buf) {
                return new PacketImportBuilding(buf.readBlockPos());
        }

        public static void handle(PacketImportBuilding msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> PlanIO.importBuilding(ctx.get().getSender(), msg.pos));
                ctx.get().setPacketHandled(true);
        }

}
