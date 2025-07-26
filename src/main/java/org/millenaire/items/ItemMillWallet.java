package org.millenaire.items;

import java.util.List;

import org.millenaire.CommonUtilities;
import org.millenaire.gui.MillAchievement;

import net.minecraft.entity.player.Player;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import javax.annotation.Nullable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemMillWallet extends Item
{
        @Override
public InteractionResultHolder<ItemStack> use(World worldIn, Player playerIn, InteractionHand hand)
    {
                ItemStack itemStackIn = playerIn.getItemInHand(hand);
                if(playerIn.getInventory().hasItem(MillItems.denier.get()) || playerIn.getInventory().hasItem(MillItems.denierArgent.get()) || playerIn.getInventory().hasItem(MillItems.denierOr.get()))
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
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
                if(stack.hasTag())
                {
                        CompoundNBT nbt = stack.getTag();
			
                        if(nbt.contains("DenierOr") && nbt.getInt("DenierOr") > 0)
                        {
                                String or = nbt.getInt("DenierOr") + "o ";
                                String argent = nbt.getInt("DenierArgent") + "a ";
                                String denier = nbt.getInt("Denier") + "d";

				tooltip.add(EnumChatFormatting.YELLOW + or + EnumChatFormatting.GRAY + argent + EnumChatFormatting.GOLD + denier);
			}
                        else if(nbt.contains("DenierArgent") && nbt.getInt("DenierArgent") > 0)
                        {
                                String argent = nbt.getInt("DenierArgent") + "a ";
                                String denier = nbt.getInt("Denier") + "d";

				tooltip.add(EnumChatFormatting.GRAY + argent + EnumChatFormatting.GOLD + denier);
			}
			else
			{
                                String denier = nbt.getInt("Denier") + "d";

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
                                        if(invStack.getItem() == MillItems.denier.get())
                                        {
                                                denier += invStack.getCount();
                                                playerIn.getInventory().removeStackFromSlot(i);
                                        }
                                        else if(invStack.getItem() == MillItems.denierArgent.get())
                                        {
                                                argent += invStack.getCount();
                                                playerIn.getInventory().removeStackFromSlot(i);
                                        }
                                        else if(invStack.getItem() == MillItems.denierOr.get())
                                        {
                                                or += invStack.getCount();
                                                playerIn.getInventory().removeStackFromSlot(i);
                                        }
                                }
                        }
			
                        CompoundNBT nbt;
			
                        if(!stack.hasTag())
                        {
                                nbt = new CompoundNBT();
                                stack.setTag(nbt);
                        }
                        else
                                nbt = stack.getTag();
			
                        denier += nbt.getInt("Denier");
                        argent += nbt.getInt("DenierArgent");
                        or += nbt.getInt("DenierOr");
			
			if(or >= 1)
				playerIn.addStat(MillAchievement.cresus, 1);
			
                        nbt.putInt("Denier", denier);
                        nbt.putInt("DenierArgent", argent);
                        nbt.putInt("DenierOr", or);
		}
	}

    private void emptyWallet(ItemStack stack, Player playerIn)
	{
                if(stack.hasTag())
                {
                        CompoundNBT nbt = stack.getTag();
			
                        if(nbt.contains("DenierOr") && nbt.getInt("DenierOr") > 0)
                        {
                                ItemStack or = new ItemStack(MillItems.denierOr.get(), nbt.getInt("DenierOr"), 0);
                                playerIn.getInventory().placeItemBackInInventory(or);
                        }

                        if(nbt.contains("DenierArgent") && nbt.getInt("DenierArgent") > 0)
                        {
                                ItemStack argent = new ItemStack(MillItems.denierArgent.get(), nbt.getInt("DenierArgent"), 0);
                                playerIn.getInventory().placeItemBackInInventory(argent);
                        }

                        if(nbt.contains("Denier") && nbt.getInt("Denier") > 0)
                        {
                                ItemStack denier = new ItemStack(MillItems.denier.get(), nbt.getInt("Denier"), 0);
                                playerIn.getInventory().placeItemBackInInventory(denier);
                        }

                        stack.setTag(null);
                }
	}
}
