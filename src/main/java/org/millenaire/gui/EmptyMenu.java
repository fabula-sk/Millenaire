package org.millenaire.gui;

import net.minecraft.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class EmptyMenu extends AbstractContainerMenu {
    public EmptyMenu(MenuType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
