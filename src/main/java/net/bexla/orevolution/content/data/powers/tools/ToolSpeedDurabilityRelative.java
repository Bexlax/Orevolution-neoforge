package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ToolSpeedDurabilityRelative extends OrevolutionToolPower {
    private final boolean reversed;
    private final float minValue;
    private final float maxValue;
    private final float exponent;

    public ToolSpeedDurabilityRelative(String tooltipId, IConditional conditional, boolean reversed, float minValue, float maxValue, float exponent) {
        super(tooltipId, conditional);
        this.reversed = reversed;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.exponent = exponent;
    }

    public ToolSpeedDurabilityRelative(String tooltipId, IConditional conditional, float minValue, float maxValue, float exponent) {
        this(tooltipId, conditional, false, minValue, maxValue, exponent);
    }

    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        if(!getCondition(stack, state, null, null, null)) return defaultSpeed;

        float percentage = OrevolutionUtils.durabilityPercentage(stack, minValue, maxValue, exponent);

        if (reversed)
            percentage = minValue + maxValue - percentage;

        return defaultSpeed * percentage;
    }
}
