package com.cyber.expandedStorage.screen;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.screen.custom.GalliumChestMenu;
import com.cyber.expandedStorage.screen.custom.StorageControllerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, ExpandedStorage.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<StorageControllerMenu>> STORAGE_CONTROLLER_MENU =
            registerMenuType("storage_controller_menu", StorageControllerMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<GalliumChestMenu>> GALLIUM_CHEST_MENU =
            registerMenuType("gallium_chest_menu", (IContainerFactory<GalliumChestMenu>) GalliumChestMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
