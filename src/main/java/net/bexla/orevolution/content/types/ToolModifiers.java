package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.interfaces.IProgressRule;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ToolModifiers {

    private static final Map<Tier, Supplier<Integer>> DURABILITY = new IdentityHashMap<>();
    private static final Map<Tier, List<IProgressRule>> RULES = new IdentityHashMap<>();

    private ToolModifiers() {}

    public static void registerDurability(Tier tier, Supplier<Integer> supplier) {
        DURABILITY.put(tier, supplier);
    }

    public static int getMaxUses(Tier tier, int defaultUses) {
        Supplier<Integer> supplier = DURABILITY.get(tier);
        return supplier != null ? supplier.get() : defaultUses;
    }

    public static void registerRule(Tier tier, IProgressRule rule) {
        RULES.computeIfAbsent(tier, t -> new ArrayList<>()).add(rule);
    }

    public static TriState isCorrectForDrops(ItemStack stack, BlockState state) {
        if (!(stack.getItem() instanceof TieredItem item)) return TriState.DEFAULT;

        List<IProgressRule> rules = RULES.get(item.getTier());

        if (rules == null) return TriState.DEFAULT;

        for (IProgressRule rule : rules) {
            TriState result = rule.evaluate(stack, state);

            if (result != TriState.DEFAULT) return result;
        }

        return TriState.DEFAULT;
    }
}