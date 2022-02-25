package net.kaupenjoe.mccourse.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.kaupenjoe.mccourse.world.structure.ModConfiguredStructures;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ModStructureGeneration {
    /**
     * used for spawning our structures in biomes.
     * You can move the BiomeModification API anywhere you prefer it to be at.
     * Just make sure you call BiomeModifications.addStructure at mod init.
     */
    public static void addStructureSpawningToDimensionsAndBiomes() {

        /*
         * This is the API you will use to add anything to any biome.
         * This includes spawns, changing the biome's looks, messing with its temperature,
         * adding carvers, spawning new features... etc
         */
        BiomeModifications.addStructure(
                // Add our structure to all biomes that have any of these biome categories. This includes modded biomes.
                // You can filter to certain biomes based on stuff like temperature, scale, precipitation, mod id, etc.
                // See BiomeSelectors's methods for more options or write your own by doing `(context) -> context.whatever() == condition`
                BiomeSelectors.categories(
                        Biome.Category.DESERT,
                        Biome.Category.EXTREME_HILLS,
                        Biome.Category.FOREST,
                        Biome.Category.ICY,
                        Biome.Category.JUNGLE,
                        Biome.Category.PLAINS,
                        Biome.Category.SAVANNA,
                        Biome.Category.TAIGA),
                // The registry key of our ConfiguredStructure so BiomeModification API can grab it
                // later to tell the game which biomes that your structure can spawn within.
                RegistryKey.of(
                        Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                        BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(ModConfiguredStructures.CONFIGURED_RUN_DOWN_HOUSE))
        );
    }
}
