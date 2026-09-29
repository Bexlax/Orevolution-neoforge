package net.bexla.orevolution.content.interfaces;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public record ToolModifier(
        Supplier<Integer> maxUses,
        BiFunction<ItemStack, BlockState, TriState> correctForDrops
) {

    public static final ToolModifier EMPTY = new ToolModifier(null, null);

    public int modifyMaxUses(int defaultUses) {
        return maxUses != null ? maxUses.get() : defaultUses;
    }

    public TriState isCorrectForDrops(ItemStack stack, BlockState state) {
        return correctForDrops != null
                ? correctForDrops.apply(stack, state)
                : TriState.DEFAULT;
    }
}