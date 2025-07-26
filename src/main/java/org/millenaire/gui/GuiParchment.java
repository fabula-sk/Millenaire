package org.millenaire.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.millenaire.Millenaire;
import org.millenaire.items.ItemMillParchment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiParchment extends Screen
{
       private final static ResourceLocation PARCHMENTGUI = new ResourceLocation(Millenaire.MODID + ":textures/gui/ml_parchment.png");
       private final static ResourceLocation BOOKGUI = new ResourceLocation(Millenaire.MODID + ":textures/gui/ml_book.png");

	private ItemMillParchment item;
	private List<String> stringPages = new ArrayList<String>();
	private int page = 0;

        private Button forward;
        private Button backward;

	GuiParchment(ItemStack stack)
	{
		if(stack.getItem() instanceof ItemMillParchment)
			item = (ItemMillParchment)stack.getItem();
		else
			System.err.println("Parchment Gui called from wrong Item.  Something failed.");
		
		for(int i = 0; i < item.contents.length; i++)
		{
			String current = I18n.format(item.contents[i]);
			int marker = 0;
			while(marker < current.length())
			{
				int j = 650;
				marker = 0;
			
				while(marker < current.length() && (j > 0 || current.charAt(marker) != ' '))
				{
					if(current.substring(marker).startsWith("\n\n"))
					{
						j= j - 37;
					}
				
					j--;
					marker++;
				}

				if(marker == current.length())
				{
					stringPages.add(current);
					break;
				}
				else
				{
					String sub = current.substring(0, marker);
					stringPages.add(sub);
					current = current.substring(marker + 1, current.length());
					marker = 0;
				}
			}
		}
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
        public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
            this.renderBackground(poseStack);
            minecraft.getTextureManager().bind(PARCHMENTGUI);
            blit(poseStack, (this.width - 203) / 2, 2, 0, 0, 203, 219);
	    
	    String drawTitle = I18n.format(item.title);
            this.font.draw(poseStack, drawTitle, (this.width - this.font.width(drawTitle)) / 2, 6, 0);
	    
	    String drawContents = stringPages.get(page);
            this.font.drawWordWrap(Component.literal(drawContents), (this.width / 2) - 94, 20, 190, 0);
	    
            super.render(poseStack, mouseX, mouseY, partialTicks);
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

		this.forward.visible = page != stringPages.size() - 1;
	}
	
        @Override
        public boolean isPauseScreen()
        {
            return false;
        }
	
        static class NextPageButton extends Button {
            private final boolean nextPage;

            NextPageButton(int id, int xIn, int yIn, boolean nextPageIn) {
                super(xIn, yIn, 18, 10, Component.literal(""), btn -> {});
                this.nextPage = nextPageIn;
            }

            public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
                if (this.visible) {
                    boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    Minecraft.getInstance().getTextureManager().bind(BOOKGUI);
                    int i = 0;
                    int j = 180;
                    if (flag) {
                        i += 18;
                    }
                    if (!this.nextPage) {
                        j += 10;
                    }
                    blit(poseStack, this.x, this.y, i, j, 18, 10);
                }
            }
        }
    }
