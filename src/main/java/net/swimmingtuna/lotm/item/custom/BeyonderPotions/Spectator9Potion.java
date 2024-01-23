package net.swimmingtuna.lotm.item.custom.BeyonderPotions;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.item.ModPotions;


@Mod.EventBusSubscriber(modid = LOTM.MOD_ID)
public class Spectator9Potion extends Potion {
@SubscribeEvent
     public void onAddSequence(LivingEntityUseItemEvent.Finish event, UseOnContext pContext) {
     ItemStack itemStack = pContext.getItemInHand();
        if (!event.isCanceled() && PotionUtils.getPotion(itemStack) == Potions.FIRE_RESISTANCE)
            System.out.println("worked");
    }
    public Spectator9Potion(MobEffectInstance mobEffectInstance) {
        super(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,600,3));
    }}
