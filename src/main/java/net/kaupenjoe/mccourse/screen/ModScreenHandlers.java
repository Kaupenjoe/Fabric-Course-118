package net.kaupenjoe.mccourse.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<OrichalcumBlasterScreenHandler> ORICHALCUM_BLASTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(MCCourseMod.MOD_ID, "orichalcum_blaster"),
                    OrichalcumBlasterScreenHandler::new);
}
