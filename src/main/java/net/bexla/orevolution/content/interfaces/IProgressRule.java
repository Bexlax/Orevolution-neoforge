package net.bexla.orevolution.content.interfaces;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface IProgressRule {
    TriState evaluate(ItemStack stack, BlockState state);

    static IProgressRule configDisabled(Supplier<Boolean> enabled, TagKey<Block> tag) {
        return (stack, state) -> !enabled.get() && state.is(tag) ? TriState.TRUE : TriState.DEFAULT;
    }

    static IProgressRule configEnabled(Supplier<Boolean> enabled, TagKey<Block> tag) {
        return (stack, state) -> enabled.get() && state.is(tag) ? TriState.TRUE : TriState.DEFAULT;
    }

    static IProgressRule incorrectTag(Supplier<Boolean> enabled, TagKey<Block> tag) {
        return (stack, state) -> enabled.get() && !state.is(tag) ? TriState.TRUE : TriState.DEFAULT;
    }

    static IProgressRule predicate(BiPredicate<ItemStack, BlockState> predicate) {
        return (stack, state) -> predicate.test(stack, state) ? TriState.TRUE : TriState.DEFAULT;
    }

    default IProgressRule not() {
        return (stack, state) -> {
            TriState result = evaluate(stack, state);

            return switch (result) {
                case TRUE -> TriState.FALSE;
                case FALSE -> TriState.TRUE;
                default -> TriState.DEFAULT;
            };
        };
    }

    default IProgressRule or(IProgressRule other) {
        return (stack, state) -> {
            TriState first = evaluate(stack, state);

            if (first != TriState.DEFAULT) {
                return first;
            }

            return other.evaluate(stack, state);
        };
    }

    default IProgressRule and(IProgressRule other) {
        return (stack, state) -> {
            TriState first = evaluate(stack, state);

            if (first == TriState.FALSE) {
                return TriState.FALSE;
            }

            TriState second = other.evaluate(stack, state);

            if (second == TriState.FALSE) {
                return TriState.FALSE;
            }

            if (first == TriState.TRUE && second == TriState.TRUE) {
                return TriState.TRUE;
            }

            return TriState.DEFAULT;
        };
    }
}