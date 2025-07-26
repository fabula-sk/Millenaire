package org.millenaire.gui;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ChiefMenu extends AbstractContainerMenu {
    public ChiefMenu(MenuType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
