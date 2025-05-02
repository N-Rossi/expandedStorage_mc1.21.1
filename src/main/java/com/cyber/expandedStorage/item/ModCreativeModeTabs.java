package com.cyber.expandedStorage.item;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExpandedStorage.MOD_ID);

    public static final Supplier<CreativeModeTab> EXPANDED_STORAGE_TAB = CREATIVE_MODE_TAB.register("expanded_storage_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GALLIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.expandedstorage.expanded_storage_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GALLIUM_INGOT);
                        output.accept(ModItems.RAW_GALLIUM);
                        output.accept(ModBlocks.GALLIUM_BLOCK);
                        output.accept(ModBlocks.GALLIUM_ORE);
                        output.accept(ModBlocks.STORAGE_EXTENDER);
                        output.accept(ModBlocks.STORAGE_CONTROLLER);
                        output.accept(ModBlocks.NETHER_GALLIUM_ORE);
                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
