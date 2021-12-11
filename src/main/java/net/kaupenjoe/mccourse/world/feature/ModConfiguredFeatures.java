package net.kaupenjoe.mccourse.world.feature;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> CHERRY_BLOSSOM_TREE =
            register("cherry_blossom", Feature.TREE.configure(new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LOG),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final ConfiguredFeature<RandomFeatureConfig, ?> CHERRY_BLOSSOM_TREE_RANDOM = register("cherry_blossom_feature",
                    Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(
                            CHERRY_BLOSSOM_TREE.withWouldSurviveFilter(ModBlocks.CHERRY_BLOSSOM_SAPLING), 0.1f)),
                            CHERRY_BLOSSOM_TREE.withWouldSurviveFilter(ModBlocks.CHERRY_BLOSSOM_SAPLING))));



    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MCCourseMod.MOD_ID, name),
                configuredFeature);
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + MCCourseMod.MOD_ID);
    }
}
