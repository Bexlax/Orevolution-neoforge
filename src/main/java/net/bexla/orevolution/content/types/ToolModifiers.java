package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.interfaces.IProgressRule;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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
    private static final Map<Tier, Supplier<? extends List<? extends String>>> TIERED_ITEMS = new IdentityHashMap<>();

    private ToolModifiers() {}

    public static void registerAdditionalTieredItems(Tier tier, Supplier<? extends List<? extends String>> entries) {
        TIERED_ITEMS.put(tier, entries);
    }

    public static void registerDurability(Tier tier, Supplier<Integer> supplier) {
        DURABILITY.put(tier, supplier);
    }

    public static void registerRule(Tier tier, IProgressRule rule) {
        RULES.computeIfAbsent(tier, t -> new ArrayList<>()).add(rule);
    }

    public static void registerDefaults(Tier tier, Supplier<Integer> supplier, Supplier<? extends List<? extends String>> blocks, Supplier<? extends List<? extends String>> items) {
        registerDurability(tier, supplier);
        registerRule(tier, IProgressRule.incorrectList(blocks));
        registerAdditionalTieredItems(tier, items);
    }

    public static int getMaxUses(Tier tier, int defaultUses) {
        Supplier<Integer> supplier = DURABILITY.get(tier);
        return supplier != null ? supplier.get() : defaultUses;
    }

    public static Tier adhereToTier(ItemStack stack) {
        for (Map.Entry<Tier, Supplier<? extends List<? extends String>>> entry : TIERED_ITEMS.entrySet()) {
            if (matchesItem(stack, entry.getValue().get())) {
                return entry.getKey();
            }
        }

        return null;
    }

    private static boolean matchesItem(ItemStack stack, List<? extends String> entries) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());

        for (String entry : entries) {
            if (entry == null || entry.isBlank()) continue;

            if (entry.startsWith("#")) {
                ResourceLocation id = ResourceLocation.parse(entry.substring(1));
                TagKey<Item> tag = TagKey.create(Registries.ITEM, id);

                if (stack.is(tag)) {
                    return true;
                }
            } else if (itemId.equals(ResourceLocation.parse(entry))) {
                return true;
            }
        }

        return false;
    }

    public static TriState isCorrectForDrops(ItemStack stack, BlockState state) {
        Tier tier = adhereToTier(stack);

        if (tier == null) {
            if (!(stack.getItem() instanceof TieredItem item)) {
                return TriState.DEFAULT;
            }

            tier = item.getTier();
        }

        List<IProgressRule> rules = RULES.get(tier);

        if (rules == null) {
            return TriState.DEFAULT;
        }

        for (IProgressRule rule : rules) {
            TriState result = rule.evaluate(stack, state);

            if (result != TriState.DEFAULT) {
                return result;
            }
        }

        return TriState.DEFAULT;
    }
}