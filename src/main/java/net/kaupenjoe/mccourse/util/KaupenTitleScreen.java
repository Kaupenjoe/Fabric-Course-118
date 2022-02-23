package net.kaupenjoe.mccourse.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.mixin.ScreenMixin;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KaupenTitleScreen extends TitleScreen {
    private static final Identifier SPLASH =
            new Identifier(MCCourseMod.MOD_ID, "textures/gui/background/kaupenmenu.jpg");

    private static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier("textures/gui/title/minecraft.png");

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int width = this.width;
        int height = this.height;

        drawCustomTitleScreen(matrixStack, width, height);
        drawMinecraftLogo(matrixStack);
        drawCopyrightNotice(matrixStack, width, height);
        drawTitleScreenButtons(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawCopyrightNotice(MatrixStack matrixStack, int width, int height) {
        drawStringWithShadow(matrixStack, this.client.textRenderer, "Copyright Mojang AB. Do not distribute!",
                width - this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!") - 2,
                height - 10, 0xFFFFFFFF);
    }

    private void drawCustomTitleScreen(MatrixStack matrixStack, int width, int height) {
        RenderSystem.enableTexture();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, SPLASH);
        drawTexture(matrixStack, 0, 0, 0, 0, this.width, this.height, width, height);
    }

    private void drawMinecraftLogo(MatrixStack matrixStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, MINECRAFT_TITLE_TEXTURE);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        this.drawWithOutline(this.width / 2 - 137, 30, (x, y) -> {
            this.drawTexture(matrixStack, x + 0, (int)y, 0, 0, 99, 44);
            this.drawTexture(matrixStack, x + 99, (int)y, 129, 0, 27, 44);
            this.drawTexture(matrixStack, x + 99 + 26, (int)y, 126, 0, 3, 44);
            this.drawTexture(matrixStack, x + 99 + 26 + 3, (int)y, 99, 0, 26, 44);
            this.drawTexture(matrixStack, x + 155, (int)y, 0, 45, 155, 44);
        });
    }

    private void drawTitleScreenButtons(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (Drawable widget : ((ScreenMixin)this).getDrawables()) {
            widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
