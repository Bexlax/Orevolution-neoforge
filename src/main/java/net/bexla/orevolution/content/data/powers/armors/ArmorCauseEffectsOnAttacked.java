package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ArmorCauseEffectsOnAttacked extends ArmorPowerMobEffects {
    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectsWearer, List<Holder<MobEffect>> effectsAttacker) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectsAttacker, effectsWearer);
    }

    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effectWearer, Holder<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier,
                effectAttacker != null? List.of(effectAttacker) : List.of(),
                effectWearer != null? List.of(effectWearer) : List.of()
        );
    }

    @Override
    public float onDamaged(LivingEntity wearer, DamageSource source, float amount) {
        LivingEntity attacker = null;

        if(source.getEntity() instanceof LivingEntity)
            attacker = (LivingEntity)source.getEntity();


        if(!getCBoolean(null, wearer.level(), wearer, attacker)) return super.onDamaged(wearer, source, amount);

        if(!this.effectsPlayer.isEmpty()) {
            for(Holder<MobEffect> p : this.effectsPlayer) {
                MobEffectInstance instance = wearer.getEffect(p);

                if(wearer.hasEffect(p) && instance != null) {
                    instance.update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    wearer.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }

        if(attacker == null) return super.onDamaged(wearer, source, amount);

        if(!this.effectsMob.isEmpty()) {
            for(Holder<MobEffect> p : this.effectsMob) {
                MobEffectInstance instance = attacker.getEffect(p);
                if(attacker.hasEffect(p) && instance != null) {
                    instance.update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    attacker.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }
        return super.onDamaged(wearer, source, amount);
    }
}
