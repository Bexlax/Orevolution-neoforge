package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.ToolPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ToolCauseMultipleEffectsOnHit extends ToolPowerMobEffects {
    public ToolCauseMultipleEffectsOnHit(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectTarget, List<Holder<MobEffect>> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, duration, amplifier, effectTarget, effectAttacker);
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        if(!getCBoolean(stack, null, attacker.level(), attacker, target)) return super.onHitEntity(stack, target, attacker, source, dmgAmount);

        if(this.effectTarget != null) {
            for(Holder<MobEffect> p : this.effectTarget) {
                if(target.hasEffect(p)) {
                    target.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }

        if(this.effectAttacker != null) {
            for(Holder<MobEffect> p : this.effectAttacker) {
                MobEffectInstance attackerInstance = attacker.getEffect(p);

                if(attacker.hasEffect(p) && attackerInstance != null) {
                    attackerInstance.update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    attacker.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }
        return super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }
}
