package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolDamageDurabilityRelative extends OrevolutionToolPower {
    private final boolean reversed;
    private final float minValue;
    private final float maxValue;
    private final float exponent;

    public ToolDamageDurabilityRelative(String tooltipId, IConditional conditional, boolean reversed, float minValue, float maxValue, float exponent) {
        super(tooltipId, conditional);
        this.reversed = reversed;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.exponent = exponent;
    }

    public ToolDamageDurabilityRelative(String tooltipId, IConditional conditional, float minValue, float maxValue, float exponent) {
        this(tooltipId, conditional, false, minValue, maxValue, exponent);
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        if (!getCondition(stack, null, attacker.level(), attacker, target)) return super.onHitEntity(stack, target, attacker, source, dmgAmount);

        float percentage = OrevolutionUtils.durabilityPercentage(stack, minValue, maxValue, exponent);

        if (reversed)
            percentage = minValue + maxValue - percentage;

        return dmgAmount * percentage;
    }
}
