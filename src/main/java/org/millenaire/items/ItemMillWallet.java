package org.millenaire.items;

import java.util.List;

import org.millenaire.CommonUtilities;
import org.millenaire.gui.MillAchievement;

import net.minecraft.entity.player.Player;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemMillWallet extends Item
{
        @Override
public InteractionResultHolder<ItemStack> use(World worldIn, Player playerIn, InteractionHand hand)
    {
                ItemStack itemStackIn = playerIn.getItemInHand(hand);
                if(playerIn.getInventory().hasItem(MillItems.denier) || playerIn.getInventory().hasItem(MillItems.denierArgent) || playerIn.getInventory().hasItem(MillItems.denierOr))
                {
                        addDenierToWallet(itemStackIn, playerIn);
                }
                else
                {
                        emptyWallet(itemStackIn, playerIn);
                }

        return InteractionResultHolder.success(itemStackIn);
    }
	
	@Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, Player playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.hasTagCompound())
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("DenierOr") && nbt.getInteger("DenierOr") > 0)
			{
				String or = nbt.getInteger("DenierOr") + "o ";
				String argent = nbt.getInteger("DenierArgent") + "a ";
				String denier = nbt.getInteger("Denier") + "d";

				tooltip.add(EnumChatFormatting.YELLOW + or + EnumChatFormatting.GRAY + argent + EnumChatFormatting.GOLD + denier);
			}
			else if(nbt.hasKey("DenierArgent") && nbt.getInteger("DenierArgent") > 0)
			{
				String argent = nbt.getInteger("DenierArgent") + "a ";
				String denier = nbt.getInteger("Denier") + "d";

				tooltip.add(EnumChatFormatting.GRAY + argent + EnumChatFormatting.GOLD + denier);
			}
			else
			{
				String denier = nbt.getInteger("Denier") + "d";

				tooltip.add(EnumChatFormatting.GOLD + denier);
			}
		}
    }

    private void addDenierToWallet(ItemStack stack, Player playerIn)
	{
		if(stack.getItem() == this)
		{
			CommonUtilities.changeMoney(playerIn);
			
			int denier = 0;
			int argent = 0;
			int or = 0;
			
                        for(int i = 0; i < playerIn.getInventory().getSizeInventory(); i++)
                        {
                                if(playerIn.getInventory().getStackInSlot(i) != null)
                                {
                                        ItemStack invStack = playerIn.getInventory().getStackInSlot(i);
                                        if(invStack.getItem() == MillItems.denier)
                                        {
                                                denier += invStack.getCount();
                                                playerIn.getInventory().removeStackFromSlot(i);
                                        }
                                        else if(invStack.getItem() == MillItems.denierArgent)
                                        {
                                                argent += invStack.getCount();
                                                playerIn.getInventory().removeStackFromSlot(i);
                                        }
                                        else if(invStack.getItem() == MillItems.denierOr)
                                        {
                                                or += invStack.getCount();
                                                playerIn.getInventory().removeStackFromSlot(i);
                                        }
                                }
                        }
			
			NBTTagCompound nbt;
			
			if(!stack.hasTagCompound())
			{
				nbt = new NBTTagCompound();
				stack.setTagCompound(nbt);
			}
			else
				nbt = stack.getTagCompound();
			
			denier += nbt.getInteger("Denier");
			argent += nbt.getInteger("DenierArgent");
			or += nbt.getInteger("DenierOr");
			
			if(or >= 1)
				playerIn.addStat(MillAchievement.cresus, 1);
			
			nbt.setInteger("Denier", denier);
			nbt.setInteger("DenierArgent", argent);
			nbt.setInteger("DenierOr", or);
		}
	}

    private void emptyWallet(ItemStack stack, Player playerIn)
	{
		if(stack.hasTagCompound())
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("DenierOr") && nbt.getInteger("DenierOr") > 0)
			{
				ItemStack or = new ItemStack(MillItems.denierOr, nbt.getInteger("DenierOr"), 0);
                                playerIn.getInventory().placeItemBackInInventory(or);
			}
			
			if(nbt.hasKey("DenierArgent") && nbt.getInteger("DenierArgent") > 0)
			{
				ItemStack argent = new ItemStack(MillItems.denierArgent, nbt.getInteger("DenierArgent"), 0);
                                playerIn.getInventory().placeItemBackInInventory(argent);
			}
			
			if(nbt.hasKey("Denier") && nbt.getInteger("Denier") > 0)
			{
				ItemStack denier = new ItemStack(MillItems.denier, nbt.getInteger("Denier"), 0);
                                playerIn.getInventory().placeItemBackInInventory(denier);
			}
			
			stack.setTagCompound(null);
		}
	}
}
