package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OreType;
import net.bexla.orevolution.content.types.features.MeteoriteFeature;
import net.bexla.orevolution.content.types.features.OreConditionalFeature;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.bexla.orevolution.Orevolution.lc;

public final class RegFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Orevolution.MODID);

    public static final DeferredHolder<Feature<?>, OreConditionalFeature> TIN_ORE =
            FEATURES.register(
                    "tin_ore_feature",
                    () -> new OreConditionalFeature(
                            OreConfiguration.CODEC,
                            OrevolutionConfig.COMMON.generateTinOre
                    )
            );

    public static final DeferredHolder<Feature<?>, OreConditionalFeature> PLATINUM_ORE =
            FEATURES.register(
                    "platinum_ore_feature",
                    () -> new OreConditionalFeature(
                            OreConfiguration.CODEC,
                            OrevolutionConfig.COMMON.generatePlatOre
                    )
            );

    public static final DeferredHolder<Feature<?>, OreConditionalFeature> TUNGSTEN_ORE =
            FEATURES.register(
                    "tungsten_ore_feature",
                    () -> new OreConditionalFeature(
                            OreConfiguration.CODEC,
                            OrevolutionConfig.COMMON.generateTungstenOre
                    )
            );

    public static final DeferredHolder<Feature<?>, OreConditionalFeature> EXPERIENCE_ORE =
            FEATURES.register(
                    "experience_ore_feature",
                    () -> new OreConditionalFeature(
                            OreConfiguration.CODEC,
                            OrevolutionConfig.COMMON.generateExperienceOre
                    )
            );

    public static final DeferredHolder<Feature<?>, OreConditionalFeature> LIMESTONE_PATCH =
            FEATURES.register(
                    "limestone_patch",
                    () -> new OreConditionalFeature(
                            OreConfiguration.CODEC,
                            OrevolutionConfig.COMMON.generateLimestone
                    )
            );

    public static final DeferredHolder<Feature<?>, MeteoriteFeature> METEORITE =
            FEATURES.register(
                    "meteorite_feature",
                    () -> new MeteoriteFeature(NoneFeatureConfiguration.CODEC)
            );

    public static final class Configured {

        public static final Map<String, ResourceKey<ConfiguredFeature<?, ?>>> ALL = new HashMap<>();

        private static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
            return ALL.computeIfAbsent(
                    name,
                    n -> ResourceKey.create(
                            Registries.CONFIGURED_FEATURE,
                            lc(n)
                    )
            );
        }

        public static final List<Block> TIN_ORES = List.of(
                RegBlocks.TIN_ORE.get(),
                RegBlocks.DEEPSLATE_TIN_ORE.get()
        );

        public static final List<Block> PLATINUM_ORES = List.of(
                RegBlocks.PLATINUM_ORE.get(),
                RegBlocks.DEEPSLATE_PLATINUM_ORE.get()
        );

        public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {

            registerOre(ctx, "tin_ore", TIN_ORE, OreType.OVERWORLD, TIN_ORES, 10, 0F);
            registerOre(ctx, "tin_ore_extra", TIN_ORE, OreType.OVERWORLD, TIN_ORES, 16, 0.3F);

            registerOre(ctx, "platinum_ore", PLATINUM_ORE, OreType.OVERWORLD, PLATINUM_ORES, 8, 0.43F);
            registerOre(ctx, "platinum_ore_extra", PLATINUM_ORE, OreType.OVERWORLD, PLATINUM_ORES, 14, 0.65F);

            registerOre(ctx, "tungsten_ore", TUNGSTEN_ORE, OreType.NETHER,
                    List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()), 8, 0.4F);
            registerOre(ctx, "tungsten_ore_extra", TUNGSTEN_ORE, OreType.NETHER,
                    List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()), 16, 0.6F);

            registerOre(ctx, "nether_experience_ore", EXPERIENCE_ORE, OreType.NETHER,
                    List.of(RegBlocks.NETHER_XP_ORE.get()), 6, 0.7F);

            registerOre(ctx, "end_experience_ore", EXPERIENCE_ORE, OreType.END,
                    List.of(RegBlocks.END_XP_ORE.get()), 9, 0.8F);

            registerOre(ctx, "limestone_patch", LIMESTONE_PATCH, OreType.OVERWORLD,
                    List.of(RegBlocks.LIMESTONE.get()), 33, 0F);

            ctx.register(
                    create("meteorite_high"),
                    new ConfiguredFeature<>(
                            METEORITE.value(),
                            NoneFeatureConfiguration.INSTANCE
                    )
            );

            ctx.register(
                    create("meteorite_low"),
                    new ConfiguredFeature<>(
                            METEORITE.value(),
                            NoneFeatureConfiguration.INSTANCE
                    )
            );
        }

        private static void registerOre(
                BootstrapContext<ConfiguredFeature<?, ?>> ctx,
                String name,
                DeferredHolder<Feature<?>, ? extends Feature<OreConfiguration>> feature,
                OreType type,
                List<Block> variants,
                int size,
                float discardChance
        ) {
            List<OreConfiguration.TargetBlockState> targets = new ArrayList<>();

            for (int i = 0; i < Math.min(type.getTargets().size(), variants.size()); i++) {
                targets.add(
                        OreConfiguration.target(
                                type.getTargets().get(i),
                                variants.get(i).defaultBlockState()
                        )
                );
            }

            ctx.register(
                    create(name),
                    new ConfiguredFeature<>(
                            feature.value(),
                            new OreConfiguration(
                                    targets,
                                    size,
                                    discardChance
                            )
                    )
            );
        }
    }

    public static final class Placed {

        public static final Map<String, ResourceKey<PlacedFeature>> ALL = new HashMap<>();

        private static ResourceKey<PlacedFeature> create(String name) {
            return ALL.computeIfAbsent(
                    name,
                    n -> ResourceKey.create(
                            Registries.PLACED_FEATURE,
                            lc(n)
                    )
            );
        }

        public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {

            HolderGetter<ConfiguredFeature<?, ?>> features =
                    ctx.lookup(Registries.CONFIGURED_FEATURE);

            registerOrePlacement(ctx, features, "tin_ore", 14, 0, 115);
            registerOrePlacement(ctx, features, "tin_ore_extra", 14, 30, 115);

            registerOrePlacement(ctx, features, "platinum_ore", 9, -40, 35);
            registerOrePlacement(ctx, features, "platinum_ore_extra", 9, -40, 35);

            registerOrePlacement(ctx, features, "nether_experience_ore", 7, -40, 40);
            registerOrePlacement(ctx, features, "end_experience_ore", 9, -40, 60);

            registerOrePlacement(ctx, features, "tungsten_ore", 8, -15, 40);
            registerRareOrePlacement(ctx, features, "tungsten_ore_extra", 8, -40, -10);

            registerMeteoritePlacementTop(ctx, features, "meteorite_high", 120, 180);
            registerMeteoritePlacementRange(ctx, features, "meteorite_low", 210, 95, 140);

            registerOrePlacement(ctx, features, "limestone_patch", 19, -30, 80);
        }

        private static void registerMeteoritePlacementTop(
                BootstrapContext<PlacedFeature> ctx,
                HolderGetter<ConfiguredFeature<?, ?>> features,
                String name,
                int rarity,
                int minY
        ) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            rareOrePlacement(
                                    rarity,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(minY),
                                            VerticalAnchor.top()
                                    )
                            )
                    )
            );
        }

        private static void registerMeteoritePlacementRange(
                BootstrapContext<PlacedFeature> ctx,
                HolderGetter<ConfiguredFeature<?, ?>> features,
                String name,
                int rarity,
                int minY,
                int maxY
        ) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            rareOrePlacement(
                                    rarity,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(minY),
                                            VerticalAnchor.absolute(maxY)
                                    )
                            )
                    )
            );
        }

        private static void registerOrePlacement(
                BootstrapContext<PlacedFeature> ctx,
                HolderGetter<ConfiguredFeature<?, ?>> features,
                String name,
                int count,
                int minY,
                int maxY
        ) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            commonOrePlacement(
                                    count,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(minY),
                                            VerticalAnchor.absolute(maxY)
                                    )
                            )
                    )
            );
        }

        private static void registerRareOrePlacement(
                BootstrapContext<PlacedFeature> ctx,
                HolderGetter<ConfiguredFeature<?, ?>> features,
                String name,
                int rarity,
                int minY,
                int maxY
        ) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            rareOrePlacement(
                                    rarity,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(minY),
                                            VerticalAnchor.absolute(maxY)
                                    )
                            )
                    )
            );
        }

        private static List<PlacementModifier> orePlacement(
                PlacementModifier count,
                PlacementModifier height
        ) {
            return List.of(
                    count,
                    InSquarePlacement.spread(),
                    height,
                    BiomeFilter.biome()
            );
        }

        private static List<PlacementModifier> commonOrePlacement(
                int count,
                PlacementModifier height
        ) {
            return orePlacement(
                    CountPlacement.of(count),
                    height
            );
        }

        private static List<PlacementModifier> rareOrePlacement(
                int rarity,
                PlacementModifier height
        ) {
            return orePlacement(
                    RarityFilter.onAverageOnceEvery(rarity),
                    height
            );
        }
    }
}