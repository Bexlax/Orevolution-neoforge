package net.bexla.orevolution.init;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RegConditionSerializer {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, Orevolution.MODID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, ConfigValueCondition.Serializer> CONFIG =
            CONDITION_SERIALIZERS.register("config",
                    () -> new ConfigValueCondition.Serializer(
                            DataUtil.getConfigValues(OrevolutionConfig.COMMON)
                    ));

    public static class Conditions {
        public static final ConfigValueCondition CHEST_LOOT =
                config(OrevolutionConfig.COMMON.chestLoot, "integrate_chest_loot");

        public static final ConfigValueCondition ENTITY_LOOT =
                config(OrevolutionConfig.COMMON.entityLoot, "integrate_entity_loot");

        public static final ConfigValueCondition GENERATE_TIN =
                config(OrevolutionConfig.COMMON.generateTinOre, "generate_tin");

//        public static final ConfigValueCondition GENERATE_NICKEL =
//                config(OrevolutionConfig.COMMON.generateNickelOre, "generate_nickel");

        public static final ConfigValueCondition GENERATE_CELESTITE_GEODE =
                config(OrevolutionConfig.COMMON.generateCelestite, "generate_celestite");

        public static final ConfigValueCondition GENERATE_PLATINUM =
                config(OrevolutionConfig.COMMON.generatePlatOre, "generate_platinum");

        public static final ConfigValueCondition GENERATE_TUNGSTEN =
                config(OrevolutionConfig.COMMON.generateTungstenOre, "generate_tungsten");

        public static final ConfigValueCondition GENERATE_XP =
                config(OrevolutionConfig.COMMON.generateExperienceOre, "generate_xp");

        public static final ConfigValueCondition GENERATE_PYRITE =
                config(OrevolutionConfig.COMMON.generatePyriteOre, "generate_pyrite");

        public static final ConfigValueCondition ALLOW_BRONZE_ALLOY =
                config(OrevolutionConfig.COMMON.bronzeRecipes, "allow_bronze");

        public static final ConfigValueCondition ALLOW_STEEL_ALLOY =
                config(OrevolutionConfig.COMMON.steelRecipes, "allow_steel");

        public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
            return new ConfigValueCondition(CONFIG.get(), value, key, Maps.newHashMap(), inverted);
        }

        public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
            return config(value, key, false);
        }
    }
}
