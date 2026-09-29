package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.Color;
import net.bexla.orevolution.content.types.mobeffect.LesserPurificationMobEffect;
import net.bexla.orevolution.content.types.mobeffect.PetrifiedEffect;
import net.bexla.orevolution.content.types.mobeffect.PurificationMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.bexla.orevolution.Orevolution.lc;

public class RegMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Orevolution.MODID);

    public static final DeferredHolder<MobEffect, PetrifiedEffect> PETRIFIED = MOB_EFFECTS.register("petrified", PetrifiedEffect::new);
    public static final DeferredHolder<MobEffect, PurificationMobEffect> PURIFICATION = MOB_EFFECTS.register("purification", PurificationMobEffect::new);
    public static final DeferredHolder<MobEffect, LesserPurificationMobEffect> LESSER_PURIFICATION = MOB_EFFECTS.register("lesser_purification", LesserPurificationMobEffect::new);

    public static final DeferredHolder<MobEffect, MobEffect> QUICKNESS = MOB_EFFECTS.register(
            "quickness",
            () -> new MobEffectBlank(MobEffectCategory.BENEFICIAL, Color.ofRGB(255, 242, 143).getColorInt())
                    .addAttributeModifier(
                            Attributes.ATTACK_SPEED, lc("effect.quickness"), 0.5, AttributeModifier.Operation.ADD_VALUE
                    )
    );


    public static class MobEffectBlank extends MobEffect {
        protected MobEffectBlank(MobEffectCategory category, int color) {
            super(category, color);
        }
    }
}
