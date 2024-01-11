package net.swimmingtuna.lotm.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.swimmingtuna.lotm.LOTM;

public class SpiritualityHUDOverlay {
    private static final ResourceLocation FULL_SPIRITUALITY = new ResourceLocation(LOTM.MOD_ID,"textures/spirituality/full_spirituality.png"); //calls an image from textures
    private static final ResourceLocation EMPTY_SPIRITUALITY = new ResourceLocation(LOTM.MOD_ID,"textures/spirituality/empty_spirituality.png"); // calls another image from textures

    public static final IGuiOverlay HUD_SPIRITUALITY = ((((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> { //everything in the parenthesis is part of a premade construct which is used to make the GUI
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,EMPTY_SPIRITUALITY); //all this is self-explanitory
        for (int i = 0; i < 10; i++) { //makes it so 10 of these icons are added with a max limit of 10 and a min of 0
            guiGraphics.blit(EMPTY_SPIRITUALITY,x-94+(i * 9),y-54,0,0,12,12,12,12); //where the hud is
        }
        RenderSystem.setShaderTexture(0,FULL_SPIRITUALITY);
        for (int i = 0; i < 10; i++) {
            if (ClientSpiritualityData.getPlayerSpirituality() > i) { //retrieves playerSpirituality
                guiGraphics.blit(FULL_SPIRITUALITY,x-94+(i*9),y-54,0,0,12,12,12,12);
            }
            else {
                break;
            }
        }
    })));}
