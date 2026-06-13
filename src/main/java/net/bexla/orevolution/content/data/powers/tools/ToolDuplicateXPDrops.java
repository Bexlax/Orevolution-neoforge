package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ToolDuplicateXPDrops extends OrevolutionToolPower {

    public ToolDuplicateXPDrops(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public boolean onDropXPBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {
        if(state.getBlock() instanceof DropExperienceBlock) {
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
        return super.onDropXPBlock(stack, level, pos, entity, state, xpToDrop);
    }
}
