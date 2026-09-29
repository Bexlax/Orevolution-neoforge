package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolDamageConsecutiveHits extends OrevolutionToolPower {
    private final boolean sameTarget;
    private final int maxHits;

    public ToolDamageConsecutiveHits(String tooltipId, IConditional conditional, int maxHits, boolean sameTarget) {
        super(tooltipId, conditional);
        this.sameTarget = sameTarget;
        this.maxHits = maxHits;
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                maxHits
        };
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        boolean sameLastTarget = attacker.getLastHurtMob() != null && attacker.getLastHurtMob().is(target);
        boolean reset = !getCondition(stack, null, attacker.level(), attacker, target)
                || (sameTarget && !sameLastTarget);

        int hits = stack.getOrDefault(RegDataComponents.CONSECUTIVE_HITS, 0);

        if (reset) {
            stack.set(RegDataComponents.CONSECUTIVE_HITS, 0);
            return super.onHitEntity(stack, target, attacker, source, dmgAmount);
        }

        if (hits < maxHits) {
            hits++;
            stack.set(RegDataComponents.CONSECUTIVE_HITS, hits);
        }

        return dmgAmount + hits;
    }
}