package org.millenaire.items;

import org.millenaire.blocks.BlockDecorativeStone;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBlockDecorativeStone extends BlockItem
{

       public ItemBlockDecorativeStone(Block block, Item.Properties properties)
       {
               super(block, properties);
       }

	public String getUnlocalizedName(ItemStack stack)
    {
        return ((BlockDecorativeStone)this.block).getUnlocalizedName(stack.getMetadata());
    }
	
	public int getMetadata(int damage) { return damage; }
}
