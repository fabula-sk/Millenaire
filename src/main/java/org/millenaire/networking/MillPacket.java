package org.millenaire.networking;

import org.millenaire.blocks.BlockVillageStone;
import org.millenaire.blocks.MillBlocks;
import org.millenaire.items.MillItems;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
public class MillPacket
{
        private int eventID;

        // for use by the message handler only.
        public MillPacket() {}

        public MillPacket(int IDin)
        {
                eventID = IDin;
        }
	
	public int getID() { return eventID; }

        public static void encode(MillPacket msg, PacketBuffer buf) {
                buf.writeInt(msg.eventID);
        }

        public static MillPacket decode(PacketBuffer buf) {
                return new MillPacket(buf.readInt());
        }

        public static void handle(MillPacket message, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> {
                        ServerPlayerEntity sendingPlayer = ctx.get().getSender();
                        if (sendingPlayer != null) {
                                if(message.getID() == 2) {
                                        ItemStack heldItem = sendingPlayer.getHeldItemMainhand();
                                        if(heldItem.getItem() == MillItems.wandNegation) {
                                                World world = sendingPlayer.world;
                                                CompoundNBT nbt = heldItem.getTag();
                                                int posX = nbt.getInt("X");
                                                int posY = nbt.getInt("Y");
                                                int posZ = nbt.getInt("Z");
                                                BlockVillageStone villStone = (BlockVillageStone)world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
                                                villStone.negate(world, new BlockPos(posX, posY, posZ), sendingPlayer);
                                        } else {
                                                System.err.println("Player not holding Wand of Negation when attempting to delete Village");
                                        }
                                }
                                if(message.getID() == 3) {
                                        ItemStack heldItem = sendingPlayer.getHeldItemMainhand();
                                        if(heldItem.getItem() == MillItems.wandNegation) {
                                                World world = sendingPlayer.world;
                                                CompoundNBT nbt = heldItem.getTag();
                                                int id = nbt.getInt("ID");
                                                world.createExplosion(world.getEntityById(id), world.getEntityById(id).getPosX(), world.getEntityById(id).getPosY(), world.getEntityById(id).getPosZ(), 0.0F, false);
                                                world.playSound(null, world.getEntityById(id).getPosition(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 0.4F);
                                                world.removeEntity(world.getEntityById(id));
                                        } else {
                                                System.err.println("Player not holding Wand of Negation when attempting to delete Villager");
                                        }
                                }
                                if(message.getID() == 4) {
                                        ItemStack heldItem = sendingPlayer.getHeldItemMainhand();
                                        if(heldItem.getItem() == MillItems.wandSummoning) {
                                                World world = sendingPlayer.world;
                                                CompoundNBT nbt = heldItem.getTag();
                                                int posX = nbt.getInt("X");
                                                int posY = nbt.getInt("Y");
                                                int posZ = nbt.getInt("Z");
                                                world.setBlockState(new BlockPos(posX, posY, posZ), MillBlocks.villageStone.get().getDefaultState(), 3);
                                        } else {
                                                System.err.println("Player not holding Wand of Summoning when attempting to create Village");
                                        }
                                }
                        } else {
                                System.err.println("ServerPlayer was null when MillPacket was received");
                        }
                });
                ctx.get().setPacketHandled(true);
        }

}
