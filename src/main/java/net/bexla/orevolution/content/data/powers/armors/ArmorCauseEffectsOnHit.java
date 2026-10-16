package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ArmorCauseEffectsOnHit extends ArmorPowerMobEffects {
    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectsTarget, List<Holder<MobEffect>> effectsWearer) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectsTarget, effectsWearer);
    }

    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effectTarget, Holder<MobEffect> effectWearer) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectTarget, effectWearer);
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        if(!condition(null, wearer.level(), wearer, target)) return;

        applyAllEffects(wearer, target);
    }
}
