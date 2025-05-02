package com.cyber.expandedStorage.datagen;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.block.ModBlocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExpandedStorage.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.GALLIUM_BLOCK);
        blockWithItem(ModBlocks.GALLIUM_ORE);
        blockWithItem(ModBlocks.STORAGE_CONTROLLER);
        blockWithItem(ModBlocks.STORAGE_EXTENDER);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
