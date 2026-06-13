package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class ToolIncreaseDrops extends OrevolutionToolPower {
    private final int extraDrops;
    private final double baseChance;

    public ToolIncreaseDrops(String tooltip_id, IConditional conditional, int extraDrops, double baseChance) {
        super(tooltip_id, conditional);
        this.extraDrops = extraDrops;
        this.baseChance = baseChance;
    }

    @Override
    public @NotNull Object addTooltipValue() {
        return (int)(baseChance * 100) + "%";
    }

    @Override
    public MutableComponent ctrlTooltip() {
        return Component.translatable("tooltip.orevolution.duplication_explanation");
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!getCBoolean(stack, state, level, player, null)) return super.onMineBlock(stack, level, pos, player, state);

        double chance = baseChance;
        Item item = stack.getItem();

        if (item instanceof TieredItem && stack.isCorrectToolForDrops(state)) {
            if(state.is(OrevolutionTags.Blocks.alwaysDuplicateChance)) {
                chance = 1;
            }
            else if(state.is(OrevolutionTags.Blocks.neverDuplicateChance)) {
                chance = 0;
            }
            else if(state.is(OrevolutionTags.Blocks.uncommonDuplicateChance)) {
                chance = baseChance / 2;
            }
            else if(state.is(Tags.Blocks.ORES)) {
                chance = baseChance / 5;
            }
            else if(state.is(OrevolutionTags.Blocks.rareDuplicateChance)) {
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
