package net.kaupenjoe.mccourse.world.feature;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> CHERRY_BLOSSOM_PLACED_KEY = registerKey("cherry_blossom_placed");
    public static final RegistryKey<PlacedFeature> PINK_ROSE_PLACED_KEY = registerKey("pink_rose_placed");

    public static final PlacedFeature CHERRY_BLOSSOM_PLACED = registerPlacedFeature("cherry_blossom_placed",
            ModConfiguredFeatures.CHERRY_BLOSSOM_TREE_RANDOM.withPlacement(VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2))));

    public static final PlacedFeature PINK_ROSE_PLACED = registerPlacedFeature("pink_rose_placed",
            ModConfiguredFeatures.PINK_ROSE.withPlacement(RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));



    private static PlacedFeature registerPlacedFeature(String name, PlacedFeature placedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MCCourseMod.MOD_ID, name), placedFeature);
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MCCourseMod.MOD_ID, name));
    }
}
