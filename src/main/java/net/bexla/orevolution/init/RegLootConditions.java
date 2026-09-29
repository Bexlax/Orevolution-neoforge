package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.api.conditions.loot.ConfigLootCondition;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ModLoadedLootCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;

public class RegLootConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS =
            DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, Orevolution.MODID);

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> CONFIG =
            LOOT_CONDITIONS.register("config",
                    () -> ConfigLootCondition.ConfigSerializer.asType(
                            DataUtil.getConfigValues(OrevolutionConfig.COMMON)
                    ));

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> MOD_LOADED =
            LOOT_CONDITIONS.register("mod_loaded",
                    () -> new LootItemConditionType(ModLoadedLootCondition.CODEC));

    public static void register(IEventBus bus) {
        LOOT_CONDITIONS.register(bus);
    }

    public static class Conditions {
        public static ConfigLootCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
            return new ConfigLootCondition(
                    CONFIG.get(),
                    value,
                    key,
                    new HashMap<>(),
                    false
            );
        }

        public static ConfigLootCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
            return new ConfigLootCondition(
                    CONFIG.get(),
                    value,
                    key,
                    new HashMap<>(),
                    inverted
            );
        }
    }
}