package org.millenaire.items;

import org.millenaire.Millenaire;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemWritableBook;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.SimpleMenuProvider;
import org.millenaire.gui.EmptyMenu;
import org.millenaire.gui.MillMenus;

public class ItemMillParchment extends ItemWritableBook
{
	public String title;
	public String[] contents;

	ItemMillParchment(String titleIn, String[] contentIn)
	{
		title = titleIn;
		contents = contentIn;
	}
	
	@Override
        public ItemStack onItemRightClick(ItemStack itemStackIn, Level worldIn, Player playerIn)
    {
                if(worldIn.isRemote)
                {
                        MenuProvider provider = new SimpleMenuProvider((id, inv, player) -> new EmptyMenu(MillMenus.PARCHMENT_MENU.get(), id), new TextComponent("Parchment"));
                        NetworkHooks.openGui((ServerPlayer)playerIn, provider);
                }
		
        return itemStackIn;
    }
}
