package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.Operator;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ToolModifyCritDamage extends OrevolutionToolPower {
    private final CriticalModifier modifier;
    private final Object tooltipValue;

    public ToolModifyCritDamage(String tooltipId, IConditional conditional, CriticalModifier modifier, Object tooltipValue) {
        super(tooltipId, conditional);
        this.modifier = modifier;
        this.tooltipValue = tooltipValue;
    }

    public ToolModifyCritDamage(String tooltipId, IConditional conditional, Operator operator, float modifier) {
        this(tooltipId, conditional, (stack, target, attacker, mult, crit) -> operator.apply(mult, modifier), operator.format(modifier));
    }

    public ToolModifyCritDamage(String tooltipId, IConditional conditional, Operator operator, float modifier, Object tooltipValue) {
        this(tooltipId, conditional, (stack, target, attacker, mult, crit) -> operator.apply(mult, modifier), tooltipValue);
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                tooltipValue
        };
    }

    @Override
    public float onCriticalHit(ItemStack stack, LivingEntity target, Player player, float dmgMultiplier, boolean isCrit) {
        return getCondition(stack, null, player.level(), player, target) ?
                modifier.modify(stack, target, player, dmgMultiplier, isCrit)
                : super.onCriticalHit(stack, target, player, dmgMultiplier, isCrit);
    }
}
