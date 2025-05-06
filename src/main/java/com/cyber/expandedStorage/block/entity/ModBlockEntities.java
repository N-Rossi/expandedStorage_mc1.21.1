package com.cyber.expandedStorage.block.entity;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExpandedStorage.MOD_ID);

    public static final Supplier<BlockEntityType<StorageControllerBlockEntity>> STORAGE_CONTROLLER_BE =
            BLOCK_ENTITIES.register("storage_controller_be", () -> BlockEntityType.Builder.of(
                    StorageControllerBlockEntity::new, ModBlocks.STORAGE_CONTROLLER.get()).build(null));

    public static final Supplier<BlockEntityType<GalliumChestBlockEntity>> GALLIUM_CHEST_BE =
            BLOCK_ENTITIES.register("gallium_chest_be", () ->
                    BlockEntityType.Builder.of(GalliumChestBlockEntity::new, ModBlocks.GALLIUM_CHEST.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
