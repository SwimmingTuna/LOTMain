package net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9;

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

public class SpectatorSequenceProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<SpectatorSequence> SPECTATORSEQUENCE = CapabilityManager.get(new CapabilityToken<SpectatorSequence>() {});

    private SpectatorSequence s9health = null;
    private final LazyOptional<SpectatorSequence> optional = LazyOptional.of(this::createSpectatorSequence);

    private SpectatorSequence createSpectatorSequence() {
        if (this.s9health == null) {
            this.s9health = new SpectatorSequence();
        }
        return this.s9health;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == SPECTATORSEQUENCE) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSpectatorSequence().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
createSpectatorSequence().loadNBTData(nbt);
    }
}
