package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ArmorCauseEffectsOnAttacked extends ArmorPowerMobEffects {
    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int baseDuration, int amplifier, List<Holder<MobEffect>> effectsWearer, List<Holder<MobEffect>> effectsAttacker) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, baseDuration, amplifier, effectsAttacker, effectsWearer);
    }

    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int baseDuration, int amplifier, Holder<MobEffect> effectWearer, Holder<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, baseDuration, amplifier, effectAttacker, effectWearer);
    }

    @Override
    public float onDamaged(LivingEntity wearer, DamageSource source, float amount) {
        LivingEntity attacker = null;

        if(source.getEntity() instanceof LivingEntity)
            attacker = (LivingEntity)source.getEntity();

        if(!condition(null, wearer.level(), wearer, attacker)) return super.onDamaged(wearer, source, amount);

        applyAllEffects(wearer, attacker);

        return super.onDamaged(wearer, source, amount);
    }
}
