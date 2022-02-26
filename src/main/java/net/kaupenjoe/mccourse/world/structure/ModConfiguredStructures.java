package net.kaupenjoe.mccourse.world.structure;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.structure.PlainsVillageData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ModConfiguredStructures {
    /**
     * Static instance of our configured structure so we can reference it and add it to biomes easily.
     */
    public static ConfiguredStructureFeature<?, ?> CONFIGURED_RUN_DOWN_HOUSE = ModStructures.RUN_DOWN_HOUSE
            .configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_KAUPEN_HOUSE = ModStructures.KAUPEN_HOUSE
            .configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_STORAGE_PLATFORM = ModStructures.STORAGE_PLATFORM
            .configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));
    // Dummy StructurePoolFeatureConfig values for now. We will modify the pool at runtime since we cannot get json pool files here at mod init.
    // You can create and register your pools in code, pass in the code create pool here, and delete both newConfig and newContext in RunDownHouseStructure's createPiecesGenerator.
    // Note: StructurePoolFeatureConfig only takes 0 - 7 size so that's another reason why we are going to bypass that "codec" by changing size at runtime to get higher sizes.

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * You can use the same identifier for the configured structure as the regular structure
     * because the two fo them are registered to different registries.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in onInitialize.
     */
    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new Identifier(MCCourseMod.MOD_ID, "configured_run_down_house"), CONFIGURED_RUN_DOWN_HOUSE);
        Registry.register(registry, new Identifier(MCCourseMod.MOD_ID, "configured_kaupen_house"), CONFIGURED_KAUPEN_HOUSE);
        Registry.register(registry, new Identifier(MCCourseMod.MOD_ID, "configured_storage_platform"), CONFIGURED_STORAGE_PLATFORM);
    }
}
