package net.kaupenjoe.mccourse.painting;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPaintings {
    public static final PaintingVariant SUNSET = registerPainting("sunset", new PaintingVariant(32, 16));
    public static final PaintingVariant PLANT = registerPainting("plant", new PaintingVariant(16, 16));
    public static final PaintingVariant WANDERER = registerPainting("wanderer", new PaintingVariant(16, 32));

    private static PaintingVariant registerPainting(String name, PaintingVariant PaintingVariant) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(MCCourseMod.MOD_ID, name), PaintingVariant);
    }

    public static void registerPaintings() {
        System.out.println("Registering Paintings for " + MCCourseMod.MOD_ID);
    }
}
