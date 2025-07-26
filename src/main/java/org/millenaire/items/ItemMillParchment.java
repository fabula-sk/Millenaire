package org.millenaire.items;

import org.millenaire.Millenaire;

import net.minecraft.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemWritableBook;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
        public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand)
    {
                ItemStack itemStackIn = playerIn.getItemInHand(hand);
                if(worldIn.isRemote)
                {
                        MenuProvider provider = new SimpleMenuProvider((id, inv, player) -> new EmptyMenu(MillMenus.PARCHMENT_MENU.get(), id), new TextComponent("Parchment"));
                        NetworkHooks.openGui((ServerPlayer)playerIn, provider);
                }

        return InteractionResultHolder.success(itemStackIn);
    }
}
