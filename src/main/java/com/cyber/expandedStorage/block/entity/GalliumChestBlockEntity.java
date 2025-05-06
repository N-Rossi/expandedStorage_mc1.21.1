package com.cyber.expandedStorage.block.entity;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.screen.custom.GalliumChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GalliumChestBlockEntity extends BaseContainerBlockEntity {
    public static final int SIZE = 27;
//    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    private final SimpleContainer container = new SimpleContainer(SIZE) {
        @Override
        public int getMaxStackSize() {
            return 10000;
        }
    };

    public GalliumChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GALLIUM_CHEST_BE.get(), pos, state);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        NonNullList<ItemStack> list = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < container.getContainerSize(); ++i) {
            list.set(i, container.getItem(i));
        }
        return list;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        for (int i = 0; i < items.size(); ++i) {
            container.setItem(i, items.get(i));
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container."+ ExpandedStorage.MOD_ID + ".gallium_chest");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return new GalliumChestMenu(id, playerInventory, container);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }
}
