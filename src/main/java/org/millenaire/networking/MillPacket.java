package org.millenaire.networking;

import org.millenaire.blocks.BlockVillageStone;
import org.millenaire.blocks.MillBlocks;
import org.millenaire.items.MillItems;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.util.SoundCategory;
import net.minecraft.init.SoundEvents;
import net.minecraft.server.level.ServerLevel;
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

        public static void encode(MillPacket msg, FriendlyByteBuf buf) {
                buf.writeInt(msg.eventID);
        }

        public static MillPacket decode(FriendlyByteBuf buf) {
                return new MillPacket(buf.readInt());
        }

        public static void handle(MillPacket message, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> {
                        ServerPlayer sendingPlayer = ctx.get().getSender();
                        if (sendingPlayer != null) {
                                if(message.getID() == 2) {
                                        ItemStack heldItem = sendingPlayer.getMainHandItem();
                                        if(heldItem.getItem() == MillItems.wandNegation) {
                                                Level world = sendingPlayer.level;
                                                CompoundTag nbt = heldItem.getTag();
                                                int posX = nbt.getInteger("X");
                                                int posY = nbt.getInteger("Y");
                                                int posZ = nbt.getInteger("Z");
                                                BlockVillageStone villStone = (BlockVillageStone)world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
                                                villStone.negate(world, new BlockPos(posX, posY, posZ), sendingPlayer);
                                        } else {
                                                System.err.println("Player not holding Wand of Negation when attempting to delete Village");
                                        }
                                }
                                if(message.getID() == 3) {
                                        ItemStack heldItem = sendingPlayer.getMainHandItem();
                                        if(heldItem.getItem() == MillItems.wandNegation) {
                                                Level world = sendingPlayer.level;
                                                CompoundTag nbt = heldItem.getTag();
                                                int id = nbt.getInteger("ID");
                                                world.createExplosion(world.getEntityByID(id), world.getEntityByID(id).getX(), world.getEntityByID(id).getY(), world.getEntityByID(id).getZ(), 0.0F, false);
                                                world.playSound(null, world.getEntityByID(id).getPosition(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 0.4F);
                                                world.removeEntity(world.getEntityByID(id));
                                        } else {
                                                System.err.println("Player not holding Wand of Negation when attempting to delete Villager");
                                        }
                                }
                                if(message.getID() == 4) {
                                        ItemStack heldItem = sendingPlayer.getMainHandItem();
                                        if(heldItem.getItem() == MillItems.wandSummoning) {
                                                Level world = sendingPlayer.level;
                                                CompoundTag nbt = heldItem.getTag();
                                                int posX = nbt.getInteger("X");
                                                int posY = nbt.getInteger("Y");
                                                int posZ = nbt.getInteger("Z");
                                                world.setBlockState(new BlockPos(posX, posY, posZ), MillBlocks.villageStone.getDefaultState());
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
