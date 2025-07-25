package org.millenaire.gui;

import org.millenaire.Millenaire;
import org.millenaire.networking.MillPacket;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.chat.Component;

public class GuiOptions extends Screen
{
       private final static ResourceLocation OPTIONGUI = new ResourceLocation(Millenaire.MODID + ":textures/gui/ml_village_chief.png");
	private String string;
	private int eventID;

        private Button yes;
        private Button no;

	GuiOptions(int IDin, String stringIn)
	{
		string = I18n.format(stringIn);
		eventID = IDin;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
	    this.drawDefaultBackground();
	    mc.getTextureManager().bindTexture(OPTIONGUI);
	    this.drawTexturedModalRect((this.width - 255) / 2, 2, 0, 0, 255, 199);
	    this.fontRendererObj.drawSplitString(string, (this.width / 2) - 94, 20, 190, 0);
	    
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void initGui() 
	{
            this.buttonList.add(this.yes = new Button((this.width / 2) - 50, (this.height / 2) + 40, 40, 20, Component.literal("Yes"), b -> {}));
            this.buttonList.add(this.no = new Button((this.width / 2) + 10, (this.height / 2) + 40, 40, 20, Component.literal("No"), b -> {}));
	}
	
	@Override
        protected void actionPerformed(Button button)
	{
		if(button == this.yes)
		{
			if(eventID == 2)
			{
                                Millenaire.channel.sendToServer(new MillPacket(2));
			}
			if(eventID == 3)
			{
                                Millenaire.channel.sendToServer(new MillPacket(3));
			}
			if(eventID == 4)
			{
                                Millenaire.channel.sendToServer(new MillPacket(4));
			}
			this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
		}
		if(button == this.no)
		{
			this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() { return false; }
}
