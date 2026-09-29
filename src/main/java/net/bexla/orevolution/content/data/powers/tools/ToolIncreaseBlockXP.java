package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolIncreaseBlockXP extends OrevolutionToolPower {
    private final int xpMultiplier;

    public ToolIncreaseBlockXP(String tooltipId, IConditional conditional, int xpMultiplier) {
        super(tooltipId, conditional);
        this.xpMultiplier = xpMultiplier;
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                xpMultiplier + 1
        };
    }

    @Override
    public boolean onDropXPBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {
        if(xpToDrop > 0 && (stack.isCorrectToolForDrops(state) || !state.requiresCorrectToolForDrops())) {
            for(int i = 0; i < xpMultiplier; i++) {
                entity.level().addFreshEntity(
                        new ExperienceOrb(
                                entity.level(),
                                pos.getX(),
                                pos.getY(),
                                pos.getZ(),
                                xpToDrop
                        )
                );
            }
        }
        return super.onDropXPBlock(stack, level, pos, entity, state, xpToDrop);
    }
}
