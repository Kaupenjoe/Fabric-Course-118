package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.config.ModConfigs;
import net.kaupenjoe.mccourse.effect.ModEffects;
import net.kaupenjoe.mccourse.enchantment.ModEnchantments;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.painting.ModPaintings;
import net.kaupenjoe.mccourse.potion.ModPotions;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.kaupenjoe.mccourse.util.ModLootTableModifiers;
import net.kaupenjoe.mccourse.util.ModRegistries;
import net.kaupenjoe.mccourse.villager.ModVillagers;
import net.kaupenjoe.mccourse.world.feature.ModConfiguredFeatures;
import net.kaupenjoe.mccourse.world.gen.ModWorldGen;
import net.kaupenjoe.mccourse.world.structures.ModStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class MCCourseMod implements ModInitializer {
	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	// A Comment
	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();

		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerAllEntities();

		ModEnchantments.registerModEnchantments();
		ModLootTableModifiers.modifyLootTables();

		ModRecipes.register();

		ModRegistries.registerModStuffs();
		ModPaintings.registerPaintings();

		ModWorldGen.generateModWorldGen();

		ModEffects.registerEffects();
		ModPotions.registerPotions();

		ModStructures.registerStructureFeatures();

		GeckoLib.initialize();
	}
}
