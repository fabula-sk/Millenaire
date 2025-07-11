package org.millenaire.gui;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.millenaire.Millenaire;

public class MillMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Millenaire.MODID);

    public static final RegistryObject<MenuType<EmptyMenu>> PARCHMENT_MENU =
            MENUS.register("parchment_menu", () -> IForgeMenuType.create((id, inv, buf) -> new EmptyMenu(PARCHMENT_MENU.get(), id)));
    public static final RegistryObject<MenuType<EmptyMenu>> CHEST_MENU =
            MENUS.register("mill_chest_menu", () -> IForgeMenuType.create((id, inv, buf) -> new EmptyMenu(CHEST_MENU.get(), id)));
    public static final RegistryObject<MenuType<EmptyMenu>> OPTIONS_MENU =
            MENUS.register("options_menu", () -> IForgeMenuType.create((id, inv, buf) -> new EmptyMenu(OPTIONS_MENU.get(), id)));
    public static final RegistryObject<MenuType<EmptyMenu>> CHIEF_MENU =
            MENUS.register("chief_menu", () -> IForgeMenuType.create((id, inv, buf) -> new EmptyMenu(CHIEF_MENU.get(), id)));
}
