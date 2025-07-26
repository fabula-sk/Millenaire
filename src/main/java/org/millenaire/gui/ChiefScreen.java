package org.millenaire.gui;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.entity.player.Inventory;
import com.mojang.blaze3d.vertex.PoseStack;

public class ChiefScreen extends AbstractContainerScreen<EmptyMenu> {
    public ChiefScreen(EmptyMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
    }
}
