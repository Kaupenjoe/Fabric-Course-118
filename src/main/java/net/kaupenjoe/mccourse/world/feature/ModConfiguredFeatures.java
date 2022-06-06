package net.kaupenjoe.mccourse.world.feature;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CHERRY_BLOSSOM_TREE =
            ConfiguredFeatures.register("cherry_blossom", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LOG),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final RegistryEntry<PlacedFeature> CHERRY_BLOSSOM_CHECKED = PlacedFeatures.register("cherry_blossom_checked",
            CHERRY_BLOSSOM_TREE, PlacedFeatures.wouldSurvive(ModBlocks.CHERRY_BLOSSOM_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> CHERRY_BLOSSOM_SPAWN =
            ConfiguredFeatures.register("cherry_blossom_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(CHERRY_BLOSSOM_CHECKED,
                            0.5F)), CHERRY_BLOSSOM_CHECKED));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PINK_ROSE =
            ConfiguredFeatures.register("flower_pink_rose", Feature.FLOWER,
                    new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PINK_ROSE)))));

    public static final List<OreFeatureConfig.Target> OVERWORLD_ORICHALCUM_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORICHALCUM_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORICHALCUM_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORICHALCUM_ORE = ConfiguredFeatures.register("orichalcum_ore",
            Feature.ORE, new OreFeatureConfig(OVERWORLD_ORICHALCUM_ORES, 9));


    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> ORICHALCUM_GEODE =
            ConfiguredFeatures.register("orichalcum_geode", Feature.GEODE ,
                    new GeodeFeatureConfig(new GeodeLayerConfig(BlockStateProvider.of(Blocks.AIR),
                            BlockStateProvider.of(Blocks.DEEPSLATE),
                            BlockStateProvider.of(ModBlocks.ORICHALCUM_ORE),
                            BlockStateProvider.of(Blocks.DIRT),
                            BlockStateProvider.of(Blocks.EMERALD_BLOCK),
                            List.of(ModBlocks.ORICHALCUM_BLOCK.getDefaultState()),
                            BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerThicknessConfig(1.7D, 1.2D, 2.5D, 3.5D),
                            new GeodeCrackConfig(0.25D, 1.5D, 1),
                            0.5D, 0.1D,
                            true, UniformIntProvider.create(3, 8),
                            UniformIntProvider.create(2, 6), UniformIntProvider.create(1, 2),
                            -18, 18, 0.075D, 1));



    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + MCCourseMod.MOD_ID);
    }
}
