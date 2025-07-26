package org.millenaire;

import java.util.Random;

import org.millenaire.gui.MillAchievement;
import org.millenaire.items.MillItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class CommonUtilities 
{
	public static Random random = new Random();
	
	/**
	 * pretty much orgainizes the player's money
	 * @param playerIn The player to orgainize
	 */
       public static void changeMoney(PlayerEntity playerIn)
       {
               ItemStack denier = new ItemStack(MillItems.denier, 0);
               ItemStack argent = new ItemStack(MillItems.denierArgent, 0);
               ItemStack or = new ItemStack(MillItems.denierOr, 0);
		
               PlayerInventory inv = playerIn.inventory;
               for(int i = 0; i < inv.getContainerSize(); i++)
               {
                       ItemStack stack = inv.getItem(i);
                       if(stack != null)
                       {
                               if(stack.getItem() == MillItems.denier)
                               {
                                       denier.grow(stack.getCount());
                                       inv.removeItemNoUpdate(i);
                               }
                               if(stack.getItem() == MillItems.denierArgent)
                               {
                                       argent.grow(stack.getCount());
                                       inv.removeItemNoUpdate(i);
                               }
                               if(stack.getItem() == MillItems.denierOr)
                               {
                                       or.grow(stack.getCount());
                                       inv.removeItemNoUpdate(i);
                               }
                       }
               }

                argent.grow(denier.getCount() / 64);
                denier.setCount(denier.getCount() % 64);

                or.grow(argent.getCount() / 64);
                if(or.getCount() >= 1)
                {
                        playerIn.addStat(MillAchievement.cresus, 1);
                }

               argent.setCount(argent.getCount() % 64);

               inv.addItemStackToInventory(denier);
               inv.addItemStackToInventory(argent);

                while(or.getCount() > 64)
                {
                       inv.addItemStackToInventory(new ItemStack(MillItems.denierOr, 64));
                       or.shrink(64);
               }

               inv.addItemStackToInventory(or);
       }
	
	/**
	 * yep
	 * @return A random non-zero integer
	 */
	public static float getRandomNonzero() { return random.nextFloat()+0.1f; }
	
	/**
	 * gets a random Millager Gender
	 * @return
	 */
	public static int randomizeGender() { return random.nextInt(3) - 2; }
	
	/**
	 * yep
	 * @param b the block to check
	 * @param surface if the ground is on the top of the ground (true) or underground (false)
	 * @return
	 */
	public static Block getValidGroundBlock(final Block b, final boolean surface) 
	{
                if (b == Blocks.BEDROCK || b == Blocks.DIRT ||
                        b == Blocks.GRASS_BLOCK) {
            return Blocks.DIRT;
                } else if (b == Blocks.STONE) {
                    if (surface) {
                return Blocks.DIRT;
            } else {
                return Blocks.GRASS_BLOCK;
            }
        } else if (b == Blocks.GRAVEL) {
                    return Blocks.GRAVEL;
        } else if (b == Blocks.SAND) {
                    return Blocks.SAND;
        } else if (b == Blocks.SANDSTONE) {
                    if (surface) {
                return Blocks.SAND;
            } else {
                return Blocks.SANDSTONE;
            }
        }

		return null;
	}
}