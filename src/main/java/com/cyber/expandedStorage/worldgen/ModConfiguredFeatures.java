package com.cyber.expandedStorage.worldgen;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
//    CF -> PF -> BM

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_GALLIUM_ORE_KEY = registerKey("gallium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_GALLIUM_ORE_KEY = registerKey("nether_gallium_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
//        Ore Gen
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> overworldGalliumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.GALLIUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_GALLIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldGalliumOres, 9));
        register(context, NETHER_GALLIUM_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHER_GALLIUM_ORE.get().defaultBlockState(), 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ExpandedStorage.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
