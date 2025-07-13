package org.millenaire.gui;

import java.io.IOException;

import org.millenaire.entities.TileEntityMillChest;

import net.minecraft.client.gui.screens.inventory.ChestScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class GuiMillChest extends ChestScreen
{
	private boolean isLocked;

        private Inventory lowerChestInventory;
	private TileEntityMillChest chest;
	
        GuiMillChest(Inventory playerInv, Inventory chestInv, Player playerIn, TileEntityMillChest entityIn)
	{
		super(playerInv, chestInv);
		System.out.println("GuiCreated");
		lowerChestInventory = playerInv;
		chest = entityIn;
		isLocked = entityIn.isLockedFor(playerIn);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
		chest.checkForAdjacentChests();
                Component string;
                if(chest.adjacentChestXNeg == null && chest.adjacentChestXPos == null && chest.adjacentChestZNeg == null && chest.adjacentChestZPos == null) {
                        string = isLocked ? new TranslatableComponent("container.millChestLocked") : new TranslatableComponent("container.millChestUnlocked");
                }
                else {
                        string = isLocked ? new TranslatableComponent("container.millChestDoubleLocked") : new TranslatableComponent("container.millChestDoubleUnlocked");
                }
		
        this.fontRendererObj.drawString(string.getString(), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getString(), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void keyTyped(final char par1, final int par2) throws IOException 
	{
		if (!isLocked) 
		{
				super.keyTyped(par1, par2);
		}
		else 
		{
			if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) 
			{
				this.mc.thePlayer.closeScreen();
			}
		}
	}

	@Override
	protected void mouseClicked(final int i, final int j, final int k) throws IOException 
	{
		if (!isLocked) 
		{
			super.mouseClicked(i, j, k);
		}
	}
}
