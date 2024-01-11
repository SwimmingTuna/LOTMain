package net.swimmingtuna.lotm.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.swimmingtuna.lotm.client.ClientSpiritualityData;

import java.util.function.Supplier;

public class SpiritualityDataS2CPacket {
    private final int spirituality;

    public SpiritualityDataS2CPacket(int spirituality) {
        this.spirituality = spirituality;
    }

    public SpiritualityDataS2CPacket(FriendlyByteBuf buf) {
        this.spirituality = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(spirituality);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientSpiritualityData.set(spirituality);
        });
        return true;
    }
}
