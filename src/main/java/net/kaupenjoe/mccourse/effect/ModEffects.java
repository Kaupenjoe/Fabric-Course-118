package net.kaupenjoe.mccourse.effect;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {
    public static StatusEffect FREEZE;

    public static StatusEffect registerStatusEffects(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(MCCourseMod.MOD_ID, name),
                new FreezeEffect(StatusEffectCategory.HARMFUL, 3124687));
    }

    public static void registerEffects() {
        FREEZE = registerStatusEffects("freeze");
    }
}
