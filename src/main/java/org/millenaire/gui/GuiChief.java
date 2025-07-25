package org.millenaire.gui;

import org.millenaire.Millenaire;
import org.millenaire.gui.GuiParchment.NextPageButton;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.ResourceLocation;

public class GuiChief extends Screen
{
       private final static ResourceLocation CHIEFGUI = new ResourceLocation(Millenaire.MODID + ":textures/gui/ml_village_chief.png");
	private String string;
	private int page = 0;
	private int maxPage = 4;

        private Button forward;
        private Button backward;
	
	@Override
	public void render(com.mojang.blaze3d.vertex.PoseStack poseStack, int mouseX, int mouseY, float partialTicks) 
	{
	    this.renderBackground(poseStack);
	    Minecraft.getInstance().getTextureManager().bind(CHIEFGUI);
	    this.blit(poseStack, (this.width - 255) / 2, 2, 0, 0, 255, 199);
	    this.font.drawWordWrap(Component.literal(string), (this.width / 2) - 94, 20, 190, 0);
	    
	    super.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
        public void init()
        {
            this.backward = new NextPageButton(0, (this.width / 2) - 95, 208, false);
            this.forward = new NextPageButton(1, (this.width / 2) + 77, 208, true);
            this.addRenderableWidget(this.backward);
            this.addRenderableWidget(this.forward);
            updateButtons();
        }
	
	@Override
        protected void onPress(Button button)
	{
	    if (button == this.forward) 
	    {
	        page++;
	        updateButtons();
	    }
	    if (button == this.backward)
	    {
	    	page--;
	    	updateButtons();
	    }
	}
	
	private void updateButtons()
	{
		this.backward.visible = page != 0;

		this.forward.visible = page != maxPage - 1;
	}
	
	@Override
	public boolean doesGuiPauseGame() { return false; }
}
