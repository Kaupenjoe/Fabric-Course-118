package net.kaupenjoe.mccourse.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.block.ComposterBlock;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
        registerModComposterChances();
    }

    private static void registerFuels() {
        System.out.println("Registering Fuels for + " + MCCourseMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        // 400 / 20 = 20 Seconds
        registry.add(ModItems.COAL_SLIVER, 400);
    }

    private static void registerModComposterChances() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TURNIP_SEEDS, 0.35f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TURNIP, 0.65f);
    }
}
