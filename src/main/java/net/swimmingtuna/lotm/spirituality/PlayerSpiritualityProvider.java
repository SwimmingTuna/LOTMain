package net.swimmingtuna.lotm.spirituality;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerSpiritualityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerSpirituality> PLAYER_SPIRITUALITY = CapabilityManager.get(new CapabilityToken<PlayerSpirituality>() { });

    private PlayerSpirituality spirituality = null;
    private final LazyOptional<PlayerSpirituality> optional = LazyOptional.of(this::createPlayerSpirituality);

    private PlayerSpirituality createPlayerSpirituality() {
        if(this.spirituality == null) {
            this.spirituality = new PlayerSpirituality();
        }

        return this.spirituality;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_SPIRITUALITY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSpirituality().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSpirituality().loadNBTData(nbt);
    }
}
