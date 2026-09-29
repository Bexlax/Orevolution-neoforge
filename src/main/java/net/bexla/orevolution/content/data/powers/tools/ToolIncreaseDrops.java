package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class ToolIncreaseDrops extends OrevolutionToolPower {
    private final int extraDrops;
    private final double baseChance;

    public ToolIncreaseDrops(String tooltip_id, IConditional conditional, int extraDrops, double baseChance) {
        super(tooltip_id, conditional);
        this.extraDrops = extraDrops;
        this.baseChance = baseChance;
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                displayChance(0.1),
                displayChance(2)
        };
    }

    @Override
    public List<MutableComponent> ctrlTooltip() {
        return List.of(
                Component.translatable("power.orevolution.explanation.duplication"),
                Component.translatable("power.orevolution.explanation.double_chance", displayChance(2)),
                Component.translatable("power.orevolution.explanation.normal_chance", displayChance(1)),
                Component.translatable("power.orevolution.explanation.uncommon_chance", displayChance(0.5)),
                Component.translatable("power.orevolution.explanation.ore_chance", displayChance(0.2)),
                Component.translatable("power.orevolution.explanation.rare_chance", displayChance(0.1)),
                Component.translatable("power.orevolution.explanation.no_chance")
        );
    }

    private Object displayChance(double op) {
        return (int)(baseChance * 100 * op) + "%";
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!getCondition(stack, state, level, player, null)) return super.onMineBlock(stack, level, pos, player, state);

        double chance = baseChance;

        if (!state.requiresCorrectToolForDrops() || stack.isCorrectToolForDrops(state)) {
            if(state.is(OrevolutionTags.Blocks.DOUBLE_DUPLICATE_CHANCE)) {
                chance = baseChance * 2;
            } else if(state.is(OrevolutionTags.Blocks.NEVER_DUPLICATE_CHANCE)) {
                chance = 0;
            } else if(state.is(OrevolutionTags.Blocks.UNCOMMON_DUPLICATE_CHANCE)) {
                chance = baseChance / 2;
            } else if(state.is(Tags.Blocks.ORES)) {
                chance = baseChance / 5;
            } else if(state.is(OrevolutionTags.Blocks.RARE_DUPLICATE_CHANCE)) {
                chance = baseChance / 10;
            }

            if(Math.random() < chance) {
                for(int i = 0; i < extraDrops; i++) {
                    Block.dropResources(state, level, pos);
                }
            }
        }
        return super.onMineBlock(stack, level, pos, player, state);
    }
}
