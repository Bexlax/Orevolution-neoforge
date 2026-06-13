package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ToolChangeBreakSpeed extends OrevolutionToolPower {
    private final float speedIncrease;
    private final boolean replaceOriginalValue;

    public ToolChangeBreakSpeed(String tooltipId, IConditional conditional, float speedIncrease, boolean replaceOriginalValue) {
        super(tooltipId, conditional);
        this.speedIncrease = speedIncrease;
        this.replaceOriginalValue = replaceOriginalValue;
    }

    public ToolChangeBreakSpeed(String tooltipId, IConditional conditional, float speedIncrease) {
        super(tooltipId, conditional);
        this.speedIncrease = speedIncrease;
        this.replaceOriginalValue = false;
    }


    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        if(!getCBoolean(stack, state, null, null, null)) return defaultSpeed;

        return replaceOriginalValue? speedIncrease : defaultSpeed + speedIncrease;
    }
}
