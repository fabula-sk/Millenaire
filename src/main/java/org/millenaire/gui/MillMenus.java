package org.millenaire.gui;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.millenaire.Millenaire;
import org.millenaire.gui.ChiefMenu;
import org.millenaire.gui.MillChestMenu;
import org.millenaire.gui.OptionsMenu;
import org.millenaire.gui.ParchmentMenu;

public class MillMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Millenaire.MODID);

    public static final RegistryObject<MenuType<ParchmentMenu>> PARCHMENT_MENU =
            MENUS.register("parchment_menu", () -> IForgeMenuType.create((id, inv, buf) -> new ParchmentMenu(PARCHMENT_MENU.get(), id)));
    public static final RegistryObject<MenuType<MillChestMenu>> CHEST_MENU =
            MENUS.register("mill_chest_menu", () -> IForgeMenuType.create((id, inv, buf) -> new MillChestMenu(CHEST_MENU.get(), id)));
    public static final RegistryObject<MenuType<OptionsMenu>> OPTIONS_MENU =
            MENUS.register("options_menu", () -> IForgeMenuType.create((id, inv, buf) -> new OptionsMenu(OPTIONS_MENU.get(), id)));
    public static final RegistryObject<MenuType<ChiefMenu>> CHIEF_MENU =
            MENUS.register("chief_menu", () -> IForgeMenuType.create((id, inv, buf) -> new ChiefMenu(CHIEF_MENU.get(), id)));
}
