package net.swimmingtuna.lotm.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9.SpectatorSequence;
import net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9.SpectatorSequenceProvider;
import net.swimmingtuna.lotm.networking.ModMessages;
import net.swimmingtuna.lotm.networking.packet.SpiritualityDataS2CPacket;
import net.swimmingtuna.lotm.spirituality.PlayerSpirituality;
import net.swimmingtuna.lotm.spirituality.PlayerSpiritualityProvider;

@Mod.EventBusSubscriber(modid = LOTM.MOD_ID)
public class ModEvents {
    @SubscribeEvent

    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
    if(event.getObject() instanceof Player) {
        if(!event.getObject().getCapability(PlayerSpiritualityProvider.PLAYER_SPIRITUALITY).isPresent()) {
            event.addCapability(new ResourceLocation(LOTM.MOD_ID, "properties"), new PlayerSpiritualityProvider()); //adds spirituailty to the player
        }
        if(!event.getObject().getCapability(SpectatorSequenceProvider.SPECTATORSEQUENCE).isPresent()) {
            event.addCapability(new ResourceLocation(LOTM.MOD_ID, "spectatorsequence"), new SpectatorSequenceProvider());
        }
    }
}

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerSpiritualityProvider.PLAYER_SPIRITUALITY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerSpiritualityProvider.PLAYER_SPIRITUALITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);//on death, saves your spirituality when you died and links it to when you come back
                });
            });
            event.getOriginal().getCapability(SpectatorSequenceProvider.SPECTATORSEQUENCE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(SpectatorSequenceProvider.SPECTATORSEQUENCE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerSpirituality.class); //registers spirituality as well
        event.register(SpectatorSequence.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerSpiritualityProvider.PLAYER_SPIRITUALITY).ifPresent(spirituality -> {
                if(spirituality.getSpirituality() >= 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    spirituality.addSpirituality(1);
                    ModMessages.sendToPlayer(new SpiritualityDataS2CPacket(spirituality.getSpirituality()),((ServerPlayer) event.player)); //Every 10 seconds, add a spirituality to the player
                }
            });
        }

    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerSpiritualityProvider.PLAYER_SPIRITUALITY).ifPresent(spirituality -> {
                    ModMessages.sendToPlayer(new SpiritualityDataS2CPacket(spirituality.getSpirituality()), player); //sets a spirituality to the first time the player joins the world.
                });
            }
        }
    }

}
