package net.swimmingtuna.lotm.events;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.swimmingtuna.lotm.LOTM;
import net.swimmingtuna.lotm.spirituality.SpiritualityAttribute.ModAttributeRegistry;

import java.util.Arrays;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = LOTM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeAdder {
    @SubscribeEvent
    public static void modifyAttributes(EntityAttributeModificationEvent event) {
        addToPlayer(event, ModAttributeRegistry.SPIRITUALITY);
    }
    @SafeVarargs
    private static void addToPlayer(EntityAttributeModificationEvent event, Supplier<Attribute>...attributes) {
        Arrays.stream(attributes).map(Supplier::get).forEach(attribute -> event.add(EntityType.PLAYER, attribute));
    }
}
