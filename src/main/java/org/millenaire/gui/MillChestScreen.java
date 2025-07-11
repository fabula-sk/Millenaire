package org.millenaire.gui;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import com.mojang.blaze3d.vertex.PoseStack;

public class MillChestScreen extends AbstractContainerScreen<EmptyMenu> {
    public MillChestScreen(EmptyMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
    }
}
