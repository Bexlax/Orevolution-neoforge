package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ArmorCauseEffectsOnHit extends ArmorPowerMobEffects {
    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectsTarget, List<Holder<MobEffect>> effectsWearer) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectsTarget, effectsWearer);
    }

    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effectTarget, Holder<MobEffect> effectWearer) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier,
                effectTarget != null ? List.of(effectTarget) : List.of(),
                effectWearer != null ? List.of(effectWearer) : List.of()
        );
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        if(!getCBoolean(null, wearer.level(), wearer, target)) return;

        if(!this.effectsMob.isEmpty()) {
            for(Holder<MobEffect> p : this.effectsMob) {
                if(target.hasEffect(p)) {
                    target.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }

        if(!this.effectsPlayer.isEmpty()) {
            for(Holder<MobEffect> p : this.effectsPlayer) {
                if(wearer.hasEffect(p)) {
                    wearer.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    wearer.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }
    }
}
