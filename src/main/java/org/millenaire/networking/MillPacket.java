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
import net.minecraftforge.fmllegacy.network.simpleimpl.IMessage;
import net.minecraftforge.fmllegacy.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fmllegacy.network.simpleimpl.MessageContext;
import net.minecraftforge.api.distmarker.Dist;

public class MillPacket implements IMessage
{
	private int eventID;
	private boolean messageIsValid;
	
	// for use by the message handler only.
	public MillPacket() { messageIsValid = false; }
	
	public MillPacket(int IDin)
	{
		eventID = IDin;
		messageIsValid = true;
	}
	
	public boolean isMessageValid() { return messageIsValid; }
	
	public int getID() { return eventID; }

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		try
		{
			eventID = buf.readInt();
		}
		catch(IndexOutOfBoundsException ioe)
		{
			System.err.println("Exception while reading MillPacket: " + ioe);
		}
		messageIsValid = true;
	}

	@Override
        public void toBytes(ByteBuf buf)
        {
                if(!messageIsValid)
                {
                        return;
                }

                buf.writeInt(eventID);
        }

        public static void encode(MillPacket msg, PacketBuffer buf) {
                msg.toBytes(buf);
        }

        public static MillPacket decode(PacketBuffer buf) {
                MillPacket packet = new MillPacket();
                packet.fromBytes(buf);
                return packet;
        }

        public static void handle(MillPacket message, Supplier<NetworkEvent.Context> ctx) {
                ctx.get().enqueueWork(() -> {
                        EntityPlayerMP sendingPlayer = ctx.get().getSender();
                        if (sendingPlayer != null) {
                                if(message.getID() == 2) {
                                        ItemStack heldItem = sendingPlayer.getHeldItem();
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
                                        ItemStack heldItem = sendingPlayer.getHeldItem();
                                        if(heldItem.getItem() == MillItems.wandNegation) {
                                                Level world = sendingPlayer.level;
                                                CompoundTag nbt = heldItem.getTag();
                                                int id = nbt.getInteger("ID");
                                                world.createExplosion(world.getEntityByID(id), world.getEntityByID(id).posX, world.getEntityByID(id).posY, world.getEntityByID(id).posZ, 0.0F, false);
                                                world.playSound(null, world.getEntityByID(id).getPosition(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 0.4F);
                                                world.removeEntity(world.getEntityByID(id));
                                        } else {
                                                System.err.println("Player not holding Wand of Negation when attempting to delete Villager");
                                        }
                                }
                                if(message.getID() == 4) {
                                        ItemStack heldItem = sendingPlayer.getHeldItem();
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
                                System.err.println("EntityPlayerMP was null when MillPacket was received");
                        }
                });
                ctx.get().setPacketHandled(true);
        }

	public static class PacketHandlerOnServer implements IMessageHandler<MillPacket, IMessage>
	{
		@Override
		public IMessage onMessage(final MillPacket message, MessageContext ctx) 
		{
                        if(ctx.side != Dist.DEDICATED_SERVER)
			{
				System.err.println("MillPacket received on wrong side: " + ctx.side);
				return null;
			}
			if(!message.isMessageValid())
			{
				System.err.println("MillPacket was invalid");
				return null;
			}
			
			final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
			if (sendingPlayer == null) 
			{
				System.err.println("EntityPlayerMP was null when MillPacket was received");
				return null;
			}
			
                        final ServerLevel playerWorldServer = sendingPlayer.serverLevel();
                        playerWorldServer.getServer().execute(() -> processMessage(message, sendingPlayer));
			
			return null;
		}

		private void processMessage(MillPacket message, EntityPlayerMP sendingPlayer)
		{
			if(message.getID() == 2)
			{
				ItemStack heldItem = sendingPlayer.getHeldItem();
				if(heldItem.getItem() != MillItems.wandNegation)
				{
					System.err.println("Player not holding Wand of Negation when attempting to delete Village");
				}
				else
				{
                                        Level world = sendingPlayer.level;
                                        CompoundTag nbt = heldItem.getTag();
					int posX = nbt.getInteger("X");
					int posY = nbt.getInteger("Y");
					int posZ = nbt.getInteger("Z");
					
					BlockVillageStone villStone = (BlockVillageStone)world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
					villStone.negate(world, new BlockPos(posX, posY, posZ), sendingPlayer);
				}
			}
			if(message.getID() == 3)
			{
				ItemStack heldItem = sendingPlayer.getHeldItem();
				if(heldItem.getItem() != MillItems.wandNegation)
				{
					System.err.println("Player not holding Wand of Negation when attempting to delete Villager");
				}
				else
				{
                                        Level world = sendingPlayer.level;
                                        CompoundTag nbt = heldItem.getTag();
					int id = nbt.getInteger("ID");
					
					world.createExplosion(world.getEntityByID(id), world.getEntityByID(id).posX, world.getEntityByID(id).posY, world.getEntityByID(id).posZ, 0.0F, false);
                                        world.playSound(null, world.getEntityByID(id).getPosition(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 0.4F);
					//Will need to be actual removal (without respawn).
					world.removeEntity(world.getEntityByID(id));
				}
			}
			if(message.getID() == 4)
			{
				ItemStack heldItem = sendingPlayer.getHeldItem();
				if(heldItem.getItem() != MillItems.wandSummoning)
				{
					System.err.println("Player not holding Wand of Summoning when attempting to create Village");
				}
				else
				{
                                        Level world = sendingPlayer.level;
                                        CompoundTag nbt = heldItem.getTag();
					int posX = nbt.getInteger("X");
					int posY = nbt.getInteger("Y");
					int posZ = nbt.getInteger("Z");
					
                                        world.setBlockState(new BlockPos(posX, posY, posZ), MillBlocks.villageStone.getDefaultState());
				}
			}
		}
	}
}
