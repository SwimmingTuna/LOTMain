package net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class Spectator9Health {
    private int s9Health = Player.MAX_HEALTH;
    private final int MAX_S9HEALTH = 30;

    public int getS9Health() {
        return s9Health;
    }
    public AttributeSupplier.Builder createAttributes() {
        return Player.createAttributes().add(Attributes.MAX_HEALTH,10);
    }
    public void copyFrom(Spectator9Health source) {
        this.s9Health= source.s9Health;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("s9health", s9Health);
    }

    public void loadNBTData(CompoundTag nbt) {
        s9Health = nbt.getInt("s9health");
    }
}
