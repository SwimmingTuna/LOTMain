package net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;

public class Spectator9Health {
    private int s9Health = getS9Health();

    public int getS9Health() {
        return s9Health;
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

