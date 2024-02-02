package net.swimmingtuna.lotm.spirituality.SpiritualityAttribute;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.swimmingtuna.lotm.LOTM;

public class ModAttributeRegistry {
    public static final DeferredRegister<Attribute> MODATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, LOTM.MOD_ID);

    public static final RegistryObject<Attribute> SPIRITUALITY = MODATTRIBUTES.register("spirituality",
            ()-> new RangedAttribute("attribute.lotm.spirituality",100.0D,0.0D,1000000).setSyncable(true));



    public static void register(IEventBus eventBus) {
        MODATTRIBUTES.register(eventBus);
    }
}
