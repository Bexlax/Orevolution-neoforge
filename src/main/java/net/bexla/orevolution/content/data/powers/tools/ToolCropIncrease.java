package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ToolCropIncrease extends OrevolutionToolPower {
    private final int extraDrops;
    private final double chance;

    public ToolCropIncrease(String tooltip_id, IConditional conditional, int extraDrops, double chance) {
        super(tooltip_id, conditional);
        this.extraDrops = extraDrops;
        this.chance = chance;
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                (int) (chance * 100) + "%"
        };
    }

    @Override
    public List<ItemEntity> onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state, List<ItemEntity> drops) {
        if(!state.is(BlockTags.CROPS)) return super.onMineBlock(stack, level, pos, player, state, drops);

        if(getCondition(stack, state, level, player, null)) {
            if(!(Math.random() < chance)) return super.onMineBlock(stack, level, pos, player, state, drops);

            if (Math.random() < chance && extraDrops > 0) {
                List<ItemEntity> additionalDrops = new ArrayList<>();

                for (ItemEntity drop : drops) {
                    for (int i = 0; i < extraDrops; i++) {
                        ItemEntity duplicate = new ItemEntity(level, drop.getX(), drop.getY(), drop.getZ(), drop.getItem().copy());
                        additionalDrops.add(duplicate);
                    }
                }

                drops.addAll(additionalDrops);
            }
        }
        return super.onMineBlock(stack, level, pos, player, state, drops);
    }
}
