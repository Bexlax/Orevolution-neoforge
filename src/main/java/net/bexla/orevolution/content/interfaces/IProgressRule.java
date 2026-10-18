package net.bexla.orevolution.content.interfaces;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface IProgressRule {
    TriState evaluate(ItemStack stack, BlockState state);

    static IProgressRule tag(Boolean enabled, TagKey<Block> tag) {
        return (stack, state) -> enabled && state.is(tag) ? TriState.TRUE : TriState.DEFAULT;
    }

    static IProgressRule incorrectList(Supplier<? extends List<? extends String>> entries) {
        return new IProgressRule() {
            private List<? extends String> cachedEntries;
            private List<Restriction> restrictions = List.of();

            @Override
            public TriState evaluate(ItemStack stack, BlockState state) {
                List<? extends String> currentEntries = entries.get();

                if (currentEntries != cachedEntries) {
                    cachedEntries = currentEntries;
                    restrictions = parseRestrictions(currentEntries);
                }

                for (Restriction restriction : restrictions) {
                    if (restriction.matches(state)) {
                        return TriState.FALSE;
                    }
                }

                return hasCorrectToolType(stack, state)
                        ? TriState.TRUE
                        : TriState.DEFAULT;
            }
        };
    }

    private static boolean hasCorrectToolType(ItemStack stack, BlockState state) {
        if (!state.requiresCorrectToolForDrops()) {
            return true;
        }

        TagKey<Block> heldTag = OrevolutionUtils.getToolTag(stack);
        TagKey<Block> requiredTag = OrevolutionUtils.getRequiredToolTag(state);

        return requiredTag == null || (heldTag != null && heldTag.equals(requiredTag));
    }

    private static List<Restriction> parseRestrictions(List<? extends String> entries) {
        List<Restriction> restrictions = new ArrayList<>();

        for (String entry : entries) {
            if (entry == null || entry.isBlank()) continue;

            if (entry.startsWith("#")) {
                ResourceLocation id = ResourceLocation.parse(entry.substring(1));

                restrictions.add(new TagRestriction(TagKey.create(Registries.BLOCK, id)));
            } else {
                restrictions.add(new BlockRestriction(ResourceLocation.parse(entry)));
            }
        }

        return List.copyOf(restrictions);
    }

    static IProgressRule predicate(BiPredicate<ItemStack, BlockState> predicate) {
        return (stack, state) -> predicate.test(stack, state) ? TriState.TRUE : TriState.DEFAULT;
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

    interface Restriction {
        boolean matches(BlockState state);
    }

    record BlockRestriction(ResourceLocation id) implements Restriction {
        @Override
        public boolean matches(BlockState state) {
            return BuiltInRegistries.BLOCK.getKey(state.getBlock()).equals(id);
        }
    }

    record TagRestriction(TagKey<Block> tag) implements Restriction {
        @Override
        public boolean matches(BlockState state) {
            return state.is(tag);
        }
    }
}