package net.swimmingtuna.lotm.item.custom.BeyonderPotions;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9.Spectator9HealthProvider;

public class Spectator9Potion extends Potion {
    public Spectator9Potion(MobEffectInstance mobEffectInstance) {
        super(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,600,3));
    }

    @SubscribeEvent
    public static void onAttachCapabilitesPlayer(AttachCapabilitiesEvent<Entity> event, LivingEntityUseItemEvent.Finish event2) {
        if (!event2.isCanceled())
            event.addCapability(new ResourceLocation(LOTM.MOD_ID,"beyonderhealth"),new Spectator9HealthProvider());
        }
    }
