package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.menu.SteelAnvilMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RegMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Orevolution.MODID);

    public static final Supplier<MenuType<SteelAnvilMenu>> STEEL_ANVIL =
            MENUS.register("steel_anvil", () -> new MenuType<>(SteelAnvilMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
