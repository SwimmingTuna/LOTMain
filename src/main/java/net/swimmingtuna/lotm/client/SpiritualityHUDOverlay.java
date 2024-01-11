package net.swimmingtuna.lotm.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.swimmingtuna.lotm.LOTM;

public class SpiritualityHUDOverlay {
    private static final ResourceLocation FULL_SPIRITUALITY = new ResourceLocation(LOTM.MOD_ID,"textures/spirituality/full_spirituality.png");
    private static final ResourceLocation EMPTY_SPIRITUALITY = new ResourceLocation(LOTM.MOD_ID,"textures/spirituality/empty_spirituality.png");

    public static final IGuiOverlay HUD_SPIRITUALITY = ((((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,EMPTY_SPIRITUALITY);
        for (int i = 0; i < 10; i++) {
            guiGraphics.blit(EMPTY_SPIRITUALITY,x-94+(i * 9),y-54,0,0,12,12,12,12);
        }
        RenderSystem.setShaderTexture(0,FULL_SPIRITUALITY);
        for (int i = 0; i < 10; i++) {
            if (ClientSpiritualityData.getPlayerSpirituality() > i) {
                guiGraphics.blit(FULL_SPIRITUALITY,x-94+(i*9),y-54,0,0,12,12,12,12);
            }
            else {
                break;
            }
        }
    })));}
