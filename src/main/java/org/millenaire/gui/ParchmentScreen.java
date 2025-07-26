package org.millenaire.gui;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import com.mojang.blaze3d.vertex.PoseStack;

public class ParchmentScreen extends AbstractContainerScreen<EmptyMenu> {
    public ParchmentScreen(EmptyMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
    }
}
