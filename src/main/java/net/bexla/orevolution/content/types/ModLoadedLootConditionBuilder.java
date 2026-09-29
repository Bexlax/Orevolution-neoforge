package net.bexla.orevolution.content.types;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public record ModLoadedLootConditionBuilder(List<String> mods, boolean requireAll) implements LootItemCondition.Builder {

    public static ModLoadedLootConditionBuilder any(String... mods) {
        return new ModLoadedLootConditionBuilder(List.of(mods), false);
    }

    public static ModLoadedLootConditionBuilder all(String... mods) {
        return new ModLoadedLootConditionBuilder(List.of(mods), true);
    }

    @Override
    public LootItemCondition build() {
        return new ModLoadedLootCondition(mods, requireAll);
    }
}