package net.kaupenjoe.mccourse.recipe;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MCCourseMod.MOD_ID, OrichalcumBlasterRecipe.Serializer.ID),
                OrichalcumBlasterRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MCCourseMod.MOD_ID, OrichalcumBlasterRecipe.Type.ID),
                OrichalcumBlasterRecipe.Type.INSTANCE);
    }
}
