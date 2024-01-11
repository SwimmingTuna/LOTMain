package net.swimmingtuna.lotm.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.swimmingtuna.lotm.networking.ModMessages;
import net.swimmingtuna.lotm.spirituality.PlayerSpiritualityProvider;

import java.util.function.Supplier;

public class SpiritualityC2SPacket {

    public SpiritualityC2SPacket() {

    }

    public SpiritualityC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            player.getCapability(PlayerSpiritualityProvider.PLAYER_SPIRITUALITY).ifPresent(spirituality -> {
                spirituality.subSpirituality(1);
                player.sendSystemMessage(Component.literal("Current Spirituality is " + spirituality.getSpirituality())
                        .withStyle(ChatFormatting.BLUE));
                ModMessages.sendToPlayer(new SpiritualityDataS2CPacket(spirituality.getSpirituality()), player); //the enqueue work part above calls all this to happen when enqueue work is used., everything her is self explanatory
            });
        });
        return true;
    }
    }
