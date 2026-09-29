package net.bexla.orevolution.content.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.bexla.orevolution.init.RegLootConditions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.fml.ModList;

import java.util.List;

public record ModLoadedLootCondition(List<String> mods, boolean requireAll) implements LootItemCondition {

    public static final MapCodec<ModLoadedLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    com.mojang.serialization.Codec.STRING.listOf().fieldOf("mods").forGetter(ModLoadedLootCondition::mods),
                    com.mojang.serialization.Codec.BOOL.optionalFieldOf("require_all", false).forGetter(ModLoadedLootCondition::requireAll)
            ).apply(instance, ModLoadedLootCondition::new)
    );

    @Override
    public LootItemConditionType getType() {
        return RegLootConditions.MOD_LOADED.get();
    }

    @Override
    public boolean test(LootContext context) {
        if (requireAll) {
            return mods.stream().allMatch(ModList.get()::isLoaded);
        }

        return mods.stream().anyMatch(ModList.get()::isLoaded);
    }
}