package net.swimmingtuna.lotm.spirituality;

import net.minecraft.nbt.CompoundTag;

public class PlayerSpirituality {
    private int spirituality;
    private final int MIN_SPIRITUALITY = 0; //sets Minimum Spirituality
    private final int MAX_SPIRITUALITY = 10; //sets Maximum Spirituality

    public int getSpirituality() {
        return spirituality;
    }

    public void addSpirituality(int add) {
        this.spirituality = Math.min(spirituality + add, MAX_SPIRITUALITY);
    }

    public void subSpirituality(int sub) {
        this.spirituality = Math.max(spirituality - sub, MIN_SPIRITUALITY);
    }

    public void copyFrom(PlayerSpirituality source) {
        this.spirituality = source.spirituality;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("spirituality", spirituality);
    } //nbts are pretty much tags on a player

    public void loadNBTData(CompoundTag nbt) {spirituality = nbt.getInt("spirituality");
    }
}
