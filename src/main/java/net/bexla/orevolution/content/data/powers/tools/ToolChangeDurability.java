package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.item.ItemStack;

public class ToolChangeDurability extends OrevolutionToolPower {
    private final int durability;
    private final boolean replaceOriginalValue;

    public ToolChangeDurability(String tooltipId, IConditional conditional, int durability, boolean replaceOriginalValue) {
        super(tooltipId, conditional);
        this.durability = durability;
        this.replaceOriginalValue = replaceOriginalValue;
    }

    public ToolChangeDurability(String tooltipId, IConditional conditional, int durability) {
        super(tooltipId, conditional);
        this.durability = durability;
        this.replaceOriginalValue = false;
    }

    @Override
    public Object addTooltipValue() {
        return durability;
    }

    @Override
    public int setMaxUses(ItemStack stack, int defaultUses) {
        if(!getCBoolean(stack, null, null, null, null)) return defaultUses;

        return replaceOriginalValue? durability : defaultUses + durability;
    }
}
