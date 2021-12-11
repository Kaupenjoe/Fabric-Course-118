package net.kaupenjoe.mccourse.painting;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPaintings {
    public static final PaintingMotive SUNSET = registerPainting("sunset", new PaintingMotive(32, 16));
    public static final PaintingMotive PLANT = registerPainting("plant", new PaintingMotive(16, 16));
    public static final PaintingMotive WANDERER = registerPainting("wanderer", new PaintingMotive(16, 32));

    private static PaintingMotive registerPainting(String name, PaintingMotive paintingMotive) {
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(MCCourseMod.MOD_ID, name), paintingMotive);
    }

    public static void registerPaintings() {
        System.out.println("Registering Paintings for " + MCCourseMod.MOD_ID);
    }
}
