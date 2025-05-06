package com.cyber.expandedStorage.block.custom;

import com.cyber.expandedStorage.block.entity.GalliumChestBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GalliumChestBlock extends BaseEntityBlock {
    public static final MapCodec<GalliumChestBlock> CODEC = simpleCodec(GalliumChestBlock::new);

    public GalliumChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GalliumChestBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MenuProvider provider && level.getBlockEntity(pos) instanceof GalliumChestBlockEntity galliumChestBlockEntity) {
//                NetworkHooks.openScreen((ServerPlayer) player, provider, pos);
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(galliumChestBlockEntity, Component.literal("gallium_chest")), pos);

            }
        }
        return InteractionResult.SUCCESS;
    }

}
