package net.swimmingtuna.lotm.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.item.custom.BeyonderPotions.Spectator9Potion;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, LOTM.MOD_ID);

    public static final RegistryObject<Potion> Spectator9Potion = POTIONS.register("spectator_9_potion",
            ()-> new Spectator9Potion(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,600,3))
            );


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
