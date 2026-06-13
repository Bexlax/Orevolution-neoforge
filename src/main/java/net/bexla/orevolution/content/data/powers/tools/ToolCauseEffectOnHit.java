package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class ToolCauseEffectOnHit extends ToolCauseMultipleEffectsOnHit {
    public ToolCauseEffectOnHit(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effectTarget, Holder<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, duration, amplifier, effectTarget != null? List.of(effectTarget) : null, effectAttacker != null?  List.of(effectAttacker) : null);
    }
}
