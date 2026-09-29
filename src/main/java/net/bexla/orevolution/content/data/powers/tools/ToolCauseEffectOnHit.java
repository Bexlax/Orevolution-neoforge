package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.ToolPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ToolCauseEffectOnHit extends ToolPowerMobEffects {
    public ToolCauseEffectOnHit(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectTarget, List<Holder<MobEffect>> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, duration, amplifier, effectTarget, effectAttacker);
    }

    public ToolCauseEffectOnHit(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effectTarget, Holder<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, duration, amplifier, effectTarget, effectAttacker);
    }

    public ToolCauseEffectOnHit(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, boolean forceEffect, int duration, int amplifier, List<Holder<MobEffect>> effectTarget, List<Holder<MobEffect>> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, forceEffect, duration, amplifier, effectTarget, effectAttacker);
    }

    public ToolCauseEffectOnHit(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, boolean forceEffect, int duration, int amplifier, Holder<MobEffect> effectTarget, Holder<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, forceEffect, duration, amplifier, effectTarget, effectAttacker);
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        if(!getCondition(stack, null, attacker.level(), attacker, target)) return super.onHitEntity(stack, target, attacker, source, dmgAmount);

        if(this.effectTarget != null) {
            for(Holder<MobEffect> p : this.effectTarget) {
                MobEffectInstance instance = new MobEffectInstance(p, this.duration, this.amplifier);

                if(target.hasEffect(p)) {
                    target.getEffect(p).update(instance);
                }
                else {
                    if(this.forceEffect) {
                        target.forceAddEffect(instance, attacker);
                    } else {
                        target.addEffect(instance);
                    }
                }
            }
        }

        if(this.effectAttacker != null) {
            for(Holder<MobEffect> p : this.effectAttacker) {
                MobEffectInstance instance = new MobEffectInstance(p, this.duration, this.amplifier);

                if(attacker.hasEffect(p)) {
                    target.getEffect(p).update(instance);
                }
                else {
                    if(this.forceEffect) {
                        target.forceAddEffect(instance, attacker);
                    } else {
                        target.addEffect(instance);
                    }
                }
            }
        }
        return super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }
}
