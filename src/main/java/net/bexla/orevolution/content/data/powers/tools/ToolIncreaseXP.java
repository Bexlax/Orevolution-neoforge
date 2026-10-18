package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.Operator;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolIncreaseXP extends OrevolutionToolPower {
    private final int value;
    private final Operator operator;

    public ToolIncreaseXP(String tooltipId, IConditional conditional, Operator operator, int value) {
        super(tooltipId, conditional);
        this.value = value;
        this.operator = operator;
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                operator.format(value)
        };
    }

    @Override
    public int onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {
        return (int)operator.apply(xpToDrop, value);
    }
}
