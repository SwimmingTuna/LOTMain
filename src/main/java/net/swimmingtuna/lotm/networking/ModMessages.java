package net.swimmingtuna.lotm.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.networking.packet.SpiritualityC2SPacket;
import net.swimmingtuna.lotm.networking.packet.SpiritualityDataS2CPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(LOTM.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SpiritualityC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpiritualityC2SPacket::new)
                .encoder(SpiritualityC2SPacket::toBytes)
                .consumerMainThread(SpiritualityC2SPacket::handle)
                .add();

        net.messageBuilder(SpiritualityDataS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SpiritualityDataS2CPacket::new)
                .encoder(SpiritualityDataS2CPacket::toBytes)
                .consumerMainThread(SpiritualityDataS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message); //also got no clue lmao
    }
}

