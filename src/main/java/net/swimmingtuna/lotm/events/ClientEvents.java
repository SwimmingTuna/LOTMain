package net.swimmingtuna.lotm.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.client.SpiritualityHUDOverlay;
import net.swimmingtuna.lotm.networking.ModMessages;
import net.swimmingtuna.lotm.networking.packet.SpiritualityC2SPacket;
import net.swimmingtuna.lotm.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = LOTM.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.SPIRITUALITY_KEY.consumeClick()) {
                ModMessages.sendToServer(new SpiritualityC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = LOTM.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.SPIRITUALITY_KEY);
        }

    @SubscribeEvent
  public static void  registerGuiOverlays(RegisterGuiOverlaysEvent event) {
      event.registerAboveAll("spirituality", SpiritualityHUDOverlay.HUD_SPIRITUALITY);
    }
    }
}
