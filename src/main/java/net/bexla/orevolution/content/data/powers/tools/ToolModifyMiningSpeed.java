package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.Operator;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ToolModifyMiningSpeed extends OrevolutionToolPower {
    private final DestroySpeedModifier modifier;
    private final Object tooltipValue;

    public ToolModifyMiningSpeed(String tooltipId, IConditional conditional, DestroySpeedModifier modifier, Object tooltipValue) {
        super(tooltipId, conditional);
        this.modifier = modifier;
        this.tooltipValue = tooltipValue;
    }

    public ToolModifyMiningSpeed(String tooltipId, IConditional conditional, Operator operator, float modifier) {
        this(tooltipId, conditional, (stack, state, speed) -> operator.apply(speed, modifier), operator.format(modifier));
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                tooltipValue
        };
    }

    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        return getCondition(stack, state, null, null, null)? modifier.modify(stack, state, defaultSpeed) : super.setDestroySpeed(stack, state, defaultSpeed);
    }
}