package org.millenaire;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.millenaire.items.MillItems;

public class MillTabs {
    public static final ItemGroup MILLENAIRE_TAB = new ItemGroup("milltab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MillItems.denierOr.get());
        }
    };
}
