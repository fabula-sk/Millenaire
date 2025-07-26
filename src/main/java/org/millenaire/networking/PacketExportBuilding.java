package org.millenaire.networking;

import org.millenaire.building.PlanIO;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.core.BlockPos;

public class PacketExportBuilding {

	BlockPos pos;
	
	public PacketExportBuilding() {
		
	}
	
	public PacketExportBuilding(BlockPos startPos) { this.pos = startPos; }
        public static void encode(PacketExportBuilding msg, PacketBuffer buf) {
                buf.writeBlockPos(msg.pos);
        }

        public static PacketExportBuilding decode(PacketBuffer buf) {
                return new PacketExportBuilding(buf.readBlockPos());
        }

        public static void handle(PacketExportBuilding msg, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> PlanIO.exportBuilding(ctx.get().getSender(), msg.pos));
                ctx.get().setPacketHandled(true);
        }

}
