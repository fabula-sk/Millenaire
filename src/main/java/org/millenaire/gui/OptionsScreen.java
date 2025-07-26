package org.millenaire.gui;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.entity.player.Inventory;
import com.mojang.blaze3d.vertex.PoseStack;
import org.millenaire.gui.OptionsMenu;

public class OptionsScreen extends AbstractContainerScreen<OptionsMenu> {
    public OptionsScreen(OptionsMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
    }
}
