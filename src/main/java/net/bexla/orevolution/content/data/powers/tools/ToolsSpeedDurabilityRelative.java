package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ToolsSpeedDurabilityRelative extends OrevolutionToolPower {
    private final boolean reversed;

    public ToolsSpeedDurabilityRelative(String tooltipId, IConditional conditional, boolean reversed) {
        super(tooltipId, conditional);
        this.reversed = reversed;
    }

    public ToolsSpeedDurabilityRelative(String tooltipId, IConditional conditional) {
        this(tooltipId, conditional, false);
    }

    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        if(!getCondition(stack, state, null, null, null)) return defaultSpeed;

        if(reversed)
            return (defaultSpeed * 4) / OrevolutionUtils.durabilityPercentage(stack);
        return defaultSpeed * OrevolutionUtils.durabilityPercentage(stack);
    }
}
