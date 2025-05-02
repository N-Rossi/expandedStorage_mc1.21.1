package com.cyber.expandedStorage.datagen;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExpandedStorage.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GALLIUM_INGOT.get());
        basicItem(ModItems.RAW_GALLIUM.get());
    }
}
