package net.swimmingtuna.lotm.beyonder.Spectator.Spectator_9;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

public interface Spectator9HealthInterface {
    Multimap<Attribute, AttributeModifier> createAttributeMap(Player pPlayer);
}
