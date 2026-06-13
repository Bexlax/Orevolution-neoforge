package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ToolIncreaseCropDrops extends OrevolutionToolPower {
    private final int dropIncrement;
    private final double baseChance;

    public ToolIncreaseCropDrops(String tooltip_id, IConditional conditional, int dropIncrement, double chance) {
        super(tooltip_id, conditional);
        this.dropIncrement = dropIncrement;
        this.baseChance = chance;
    }

    @Override
    public @NotNull Object addTooltipValue() {
        return ((int)(baseChance * 100)) + "%";
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!state.is(BlockTags.CROPS)) return super.onMineBlock(stack, level, pos, player, state);

        if(getCBoolean(stack, state, level, player, null)) {
            if(!(Math.random() < baseChance)) return super.onMineBlock(stack, level, pos, player, state);

            for(int i = 0; i < this.dropIncrement; i++) {
                Block.dropResources(state, level, pos);
            }
        }
        return super.onMineBlock(stack, level, pos, player, state);
    }
}
