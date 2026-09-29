package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.Operator;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolModifyDealtDamage extends OrevolutionToolPower {
    private final DamageModifier modifier;
    private final Object tooltipValue;

    public ToolModifyDealtDamage(String tooltipId, IConditional conditional, DamageModifier modifier, Object tooltipValue) {
        super(tooltipId, conditional);
        this.modifier = modifier;
        this.tooltipValue = tooltipValue;
    }

    public ToolModifyDealtDamage(String tooltipId, IConditional conditional, Operator operator, float modifier) {
        this(tooltipId, conditional, (stack, target, attacker, source, damage) -> operator.apply(damage, modifier), operator.format(modifier));
    }

    public ToolModifyDealtDamage(String tooltipId, IConditional conditional, Operator operator, float modifier, Object tooltipValue) {
        this(tooltipId, conditional, (stack, target, attacker, source, damage) -> operator.apply(damage, modifier), tooltipValue);
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                tooltipValue
        };
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        return getCondition(stack, null, attacker.level(), attacker, target) ?
                modifier.modify(stack, target, attacker, source, dmgAmount)
                : super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }
}