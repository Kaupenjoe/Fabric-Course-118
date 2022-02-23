package net.kaupenjoe.mccourse.event;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.kaupenjoe.mccourse.util.KaupenTitleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;

public class ReplaceTitleScreenEvent implements ScreenEvents.BeforeInit {
    @Override
    public void beforeInit(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight) {
        if(screen instanceof TitleScreen && !(screen instanceof KaupenTitleScreen)) {
            client.setScreen(new KaupenTitleScreen());
        }
    }
}
