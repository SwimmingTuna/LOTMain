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

public class Spectator9HealthProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<Spectator9Health> SPECTATOR9HEALTH = CapabilityManager.get(new CapabilityToken<Spectator9Health>() {});

    private Spectator9Health s9health = null;
    private final LazyOptional<Spectator9Health> optional = LazyOptional.of(this::createSpectator9Health);

    private Spectator9Health createSpectator9Health() {
        if (this.s9health == null) {
            this.s9health = new Spectator9Health();
        }
        return this.s9health;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == SPECTATOR9HEALTH) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSpectator9Health().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
createSpectator9Health().loadNBTData(nbt);
    }
}
