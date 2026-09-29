package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OreType;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.interfaces.IVinnelio;
import net.bexla.orevolution.content.types.block.EmeraldSpikeBlock;
import net.bexla.orevolution.content.types.block.VinnelioBlock;
import net.bexla.orevolution.content.worldgen.features.*;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.VeryBiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.bexla.orevolution.Orevolution.lc;

public final class RegFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Orevolution.MODID);

    // Credits to the Darker Depths mod by futurenp.
    // https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/init/DDFeatures.java
    public static final DeferredHolder<Feature<?>, BoulderFeature> BOULDER = FEATURES.register("boulder", () -> new BoulderFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, RandomFloorPlacementFeature> RANDOM_FLOOR_PLACEMENT = FEATURES.register("random_floor_placement", () -> new RandomFloorPlacementFeature(RandomFloorPlacementFeature.RandomFloorPlacementConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, PillarFeature> PILLAR = FEATURES.register("pillar", () -> new PillarFeature(PillarFeature.PillarConfiguration.CODEC, OrevolutionConfig.COMMON.generateMoonstonePillar));
    public static final DeferredHolder<Feature<?>, LargePillarFeature> LARGE_PILLAR = FEATURES.register("large_pillar", () -> new LargePillarFeature(LargePillarFeature.LargePillarConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, GeodeCraterFeature> GEODE_CRATER = FEATURES.register("geode_crater", () -> new GeodeCraterFeature(GeodeCraterFeature.CraterConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, MeteoriteFeature> METEORITE =
            FEATURES.register(
                    "meteorite_feature",
                    () -> new MeteoriteFeature(NoneFeatureConfiguration.CODEC)
            );

    public static final class Configured {

        public static final Map<String, ResourceKey<ConfiguredFeature<?, ?>>> ALL = new HashMap<>();

        public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
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
        
        public static final List<Block> CASSITERITE_ORES = List.of(
                RegBlocks.CASSITERITE_ORE.get(),
                RegBlocks.DEEPSLATE_CASSITERITE_ORE.get(),
                RegBlocks.NETHER_CASSITERITE_ORE.get()
        );

        public static final List<Block> PLATINUM_ORES = List.of(
                RegBlocks.PLATINUM_ORE.get(),
                RegBlocks.DEEPSLATE_PLATINUM_ORE.get()
        );

        public static ResourceKey<ConfiguredFeature<?, ?>> PYRITE_ORE = create("pyrite_ore");
        public static ResourceKey<ConfiguredFeature<?, ?>> PYRITE_ORE_EXTRA = create("pyrite_ore_extra");

        public static ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE = create("tin_ore");
        public static ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_SMALL = create("tin_ore_small");
        public static ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_EXTRA = create("tin_ore_extra");

        public static ResourceKey<ConfiguredFeature<?, ?>> CASSITERITE_ORE = create("cassiterite_ore");
        public static ResourceKey<ConfiguredFeature<?, ?>> CASSITERITE_ORE_SMALL = create("cassiterite_ore_small");
        public static ResourceKey<ConfiguredFeature<?, ?>> CASSITERITE_ORE_EXTRA = create("cassiterite_ore_extra");

        public static ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_EXTRA = create("platinum_ore_extra");

        public static ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_SMALL = create("platinum_ore_small");
        public static ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_MEDIUM = create("platinum_ore_medium");
        public static ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_LARGE = create("platinum_ore_large");
        public static ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_BURIED = create("platinum_ore_buried");

        public static ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE = create("tungsten_ore");
        public static ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_SMALL = create("tungsten_ore_small");
        public static ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_EXTRA = create("tungsten_ore_extra");

        public static ResourceKey<ConfiguredFeature<?, ?>> NETHER_EXPERIENCE_ORE = create("nether_experience_ore");
        public static ResourceKey<ConfiguredFeature<?, ?>> END_EXPERIENCE_ORE = create("end_experience_ore");

        public static ResourceKey<ConfiguredFeature<?, ?>> LARGE_CHERT = create("large_chert");

        public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
            registerOre(ctx, PYRITE_ORE, OreType.NETHER, List.of(RegBlocks.PYRITE_BLOCK.get()), 6, 0F);
            registerOre(ctx, PYRITE_ORE_EXTRA, OreType.OVERWORLD, List.of(RegBlocks.PYRITE_BLOCK.get()), 16, 1F);

            registerOre(ctx, TIN_ORE, OreType.OVERWORLD, TIN_ORES, 9, 0F);
            registerOre(ctx, TIN_ORE_SMALL, OreType.OVERWORLD, TIN_ORES, 5, 0F);
            registerOre(ctx, TIN_ORE_EXTRA, OreType.OVERWORLD, TIN_ORES, 12, 0.05F);

            registerOre(ctx, CASSITERITE_ORE, new OreType(OreType.OVERWORLD, OreType.NETHER), CASSITERITE_ORES, 5, 0.1F);
            registerOre(ctx, CASSITERITE_ORE_SMALL, OreType.OVERWORLD, CASSITERITE_ORES, 2, 0F);
            registerOre(ctx, CASSITERITE_ORE_EXTRA, OreType.OVERWORLD, CASSITERITE_ORES, 5, 0.2F);

            registerOre(ctx, PLATINUM_ORE_EXTRA, OreType.OVERWORLD, PLATINUM_ORES, 5, 0.4F);

            registerOre(ctx, PLATINUM_ORE_SMALL, OreType.OVERWORLD, PLATINUM_ORES, 6, 0.1F);
            registerOre(ctx, PLATINUM_ORE_MEDIUM, OreType.OVERWORLD, PLATINUM_ORES, 8, 0.15F);
            registerOre(ctx, PLATINUM_ORE_LARGE, OreType.OVERWORLD, PLATINUM_ORES, 15, 0.46F);
            registerOre(ctx, PLATINUM_ORE_BURIED, OreType.OVERWORLD, PLATINUM_ORES, 8, 1.0F);

            registerOre(ctx, TUNGSTEN_ORE, OreType.NETHER, List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()), 8, 0.9F);
            registerOre(ctx, TUNGSTEN_ORE_SMALL, OreType.NETHER, List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()), 3, 0.3F);
            registerOre(ctx, TUNGSTEN_ORE_EXTRA, OreType.NETHER, List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()), 5, 0.42F);

            registerOre(ctx, NETHER_EXPERIENCE_ORE, OreType.NETHER, List.of(RegBlocks.NETHER_XP_ORE.get()), 6, 0.7F);

            registerOre(ctx, END_EXPERIENCE_ORE, OreType.END, List.of(RegBlocks.END_XP_ORE.get()), 9, 0.8F);

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

            FeatureUtils.register(ctx, LARGE_CHERT, LARGE_PILLAR.get(),
                    new LargePillarFeature.LargePillarConfiguration(
                            30,
                            UniformInt.of(3, 15),
                            UniformFloat.of(0.4F, 2.0F),
                            0.33F,
                            UniformFloat.of(0.3F, 0.9F),
                            UniformFloat.of(0.4F, 0.8F),
                            UniformFloat.of(0.0F, 0.3F),
                            8,
                            0.3F,
                            BlockStateProvider.simple(RegBlocks.CHERT.get())
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.BOULDER, BOULDER.get(), FeatureConfiguration.NONE);

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.CELESTITE_GEODE, Feature.GEODE,
                    new GeodeConfiguration(
                            new GeodeBlockSettings(
                                    BlockStateProvider.simple(Blocks.AIR),
                                    BlockStateProvider.simple(RegBlocks.CELESTITE_BLOCK.get()),
                                    BlockStateProvider.simple(RegBlocks.BUDDING_CELESTITE.get()),
                                    BlockStateProvider.simple(Blocks.DIORITE),
                                    BlockStateProvider.simple(Blocks.TUFF),
                                    List.of(
                                            RegBlocks.SMALL_CELESTITE_BUD.get().defaultBlockState(),
                                            RegBlocks.MEDIUM_CELESTITE_BUD.get().defaultBlockState(),
                                            RegBlocks.LARGE_CELESTITE_BUD.get().defaultBlockState(),
                                            RegBlocks.CELESTITE_CLUSTER.get().defaultBlockState()
                                    ),
                                    BlockTags.FEATURES_CANNOT_REPLACE,
                                    BlockTags.GEODE_INVALID_BLOCKS
                            ),
                            new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                            new GeodeCrackSettings(0.95, 2.0, 2),
                            0.35,
                            0.083,
                            true,
                            UniformInt.of(4, 6),
                            UniformInt.of(3, 4),
                            UniformInt.of(1, 2),
                            -16,
                            16,
                            0.05,
                            1
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.AMETHYST_CRATER, GEODE_CRATER.get(),
                    new GeodeCraterFeature.CraterConfiguration(
                            UniformInt.of(4, 11),
                            UniformInt.of(4, 6),
                            ConstantFloat.of(0.95F),
                            ConstantFloat.of(1F),
                            ConstantFloat.of(0.18F),
                            new TagMatchTest(OrevolutionTags.Blocks.CRATER_PLACEMENT_BLACKLIST),
                            BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                            BlockStateProvider.simple(Blocks.CALCITE),
                            List.of(
                                    BlockStateProvider.simple(Blocks.AMETHYST_BLOCK)
                            ),
                            List.of(
                                    BlockStateProvider.simple(Blocks.SMALL_AMETHYST_BUD),
                                    BlockStateProvider.simple(Blocks.MEDIUM_AMETHYST_BUD),
                                    BlockStateProvider.simple(Blocks.LARGE_AMETHYST_BUD),
                                    BlockStateProvider.simple(Blocks.AMETHYST_CLUSTER)
                            )
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.CELESTITE_CRATER, GEODE_CRATER.get(),
                    new GeodeCraterFeature.CraterConfiguration(
                            UniformInt.of(4, 11),
                            UniformInt.of(4, 6),
                            ConstantFloat.of(0.95F),
                            ConstantFloat.of(1F),
                            ConstantFloat.of(0.15F),
                            new TagMatchTest(OrevolutionTags.Blocks.CRATER_PLACEMENT_BLACKLIST),
                            BlockStateProvider.simple(Blocks.TUFF),
                            BlockStateProvider.simple(Blocks.DIORITE),
                            List.of(
                                    BlockStateProvider.simple(RegBlocks.CELESTITE_BLOCK.get()),
                                    BlockStateProvider.simple(RegBlocks.PYRITE_BLOCK.get())
                            ),
                            List.of(
                                    BlockStateProvider.simple(RegBlocks.SMALL_CELESTITE_BUD.get()),
                                    BlockStateProvider.simple(RegBlocks.MEDIUM_CELESTITE_BUD.get()),
                                    BlockStateProvider.simple(RegBlocks.LARGE_CELESTITE_BUD.get()),
                                    BlockStateProvider.simple(RegBlocks.CELESTITE_CLUSTER.get())
                            )
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.GOLD_CRATER, GEODE_CRATER.get(),
                    new GeodeCraterFeature.CraterConfiguration(
                            UniformInt.of(4, 11),
                            UniformInt.of(2, 6),
                            ConstantFloat.of(0.8F),
                            ConstantFloat.of(0.26F),
                            ConstantFloat.of(0F),
                            new TagMatchTest(OrevolutionTags.Blocks.CRATER_PLACEMENT_BLACKLIST),
                            BlockStateProvider.simple(Blocks.GRANITE),
                            BlockStateProvider.simple(Blocks.TUFF),
                            List.of(
                                    BlockStateProvider.simple(Blocks.RAW_GOLD_BLOCK),
                                    BlockStateProvider.simple(Blocks.DEEPSLATE_GOLD_ORE)
                            ),
                            List.of()
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.PLATINUM_CRATER, GEODE_CRATER.get(),
                    new GeodeCraterFeature.CraterConfiguration(
                            UniformInt.of(5, 8),
                            UniformInt.of(2, 5),
                            ConstantFloat.of(0.56F),
                            ConstantFloat.of(0.15F),
                            ConstantFloat.of(0F),
                            new TagMatchTest(OrevolutionTags.Blocks.CRATER_PLACEMENT_BLACKLIST),
                            BlockStateProvider.simple(Blocks.DIORITE),
                            BlockStateProvider.simple(Blocks.BLACKSTONE),
                            List.of(
                                    BlockStateProvider.simple(RegBlocks.RAW_PLATINUM_BLOCK.get()),
                                    BlockStateProvider.simple(RegBlocks.PLATINUM_ORE.get())
                            ),
                            List.of()
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.FORGOTTEN_SPRING, Feature.SPRING, new SpringConfiguration(
                    Fluids.EMPTY.defaultFluidState(),
                    false, 1,
                    4,
                    HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DEEPSLATE, Blocks.TUFF, Blocks.CALCITE, Blocks.DIRT, RegBlocks.RHYOLITE.get())
            ));

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.CHERT_PATCH, Feature.ORE, new OreConfiguration(
                    List.of(
                            OreConfiguration.target(
                                    new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                                    RegBlocks.CHERT.get().defaultBlockState()
                            )
                    ),
                    25)
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.QUARTZOLITE_DISK, Feature.DISK, new DiskConfiguration(
                            RuleBasedBlockStateProvider.simple(RegBlocks.QUARTZOLITE.get()),
                            BlockPredicate.matchesBlocks(RegBlocks.RHYOLITE.get()),
                            UniformInt.of(2, 4),
                            2
                    )
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.FORGOTTEN_VEGETATION, RANDOM_FLOOR_PLACEMENT.get(), new RandomFloorPlacementFeature.RandomFloorPlacementConfiguration(
                            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                    .add(RegBlocks.EMERALD_CLUSTER.get().defaultBlockState().setValue(EmeraldSpikeBlock.TIP_DIRECTION, Direction.UP), 2)
                                    .add(RegBlocks.PRISMARINE_CLUSTER.get().defaultBlockState().setValue(EmeraldSpikeBlock.TIP_DIRECTION, Direction.UP), 4)
                                    .add(RegBlocks.CORELIO.get().defaultBlockState(), 85)
                                    .add(RegBlocks.UNKNOWN_ROOTS.get().defaultBlockState(), 54)
                                    .add(RegBlocks.UNKNOWN_ROOTS_BLOCK.get().defaultBlockState(), 4)
                                    .add(RegBlocks.LARGE_CORELIO.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 18)
                                    .add(Blocks.AIR.defaultBlockState(), 9)
                                    .build()
                            ),
                            HolderSet.direct(
                                    RegBlocks.RHYOLITE.getDelegate(),
                                    RegBlocks.CHERT.getDelegate()),
                            15,
                            4,
                            1,
                            7
                    )
            );

            FeatureUtils.register(
                    ctx,
                    OrevolutionKeys.ConfiguredFeatures.MOONSTONE_PILLAR,
                    PILLAR.get(),
                    new PillarFeature.PillarConfiguration(
                            SimpleWeightedRandomList.<PillarFeature.PillarSize>builder()
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    ConstantInt.of(1),
                                                    UniformInt.of(4, 5)
                                            ),
                                            530
                                    )
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 2),
                                                    UniformInt.of(5, 7)
                                            ),
                                            69
                                    )
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 3),
                                                    UniformInt.of(7, 11)
                                            ),
                                            1
                                    )
                                    .build(),
                            BlockStateProvider.simple(RegBlocks.RHYOLITE.get()),
                            BlockStateProvider.simple(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE.get()),
                            BlockStateProvider.simple(RegBlocks.MOONSTONE.get())
                    )
            );

            FeatureUtils.register(
                    ctx,
                    OrevolutionKeys.ConfiguredFeatures.MOONSTONE_PILLAR_NETHER,
                    PILLAR.get(),
                    new PillarFeature.PillarConfiguration(
                            SimpleWeightedRandomList.<PillarFeature.PillarSize>builder()
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    ConstantInt.of(1),
                                                    UniformInt.of(4, 5)
                                            ),
                                            530
                                    )
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 2),
                                                    UniformInt.of(5, 7)
                                            ),
                                            69
                                    )
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 3),
                                                    UniformInt.of(7, 11)
                                            ),
                                            1
                                    )
                                    .build(),
                            BlockStateProvider.simple(Blocks.BASALT),
                            BlockStateProvider.simple(RegBlocks.BASALT_ENCRUSTED_MOONSTONE.get()),
                            BlockStateProvider.simple(RegBlocks.MOONSTONE.get())
                    )
            );

            FeatureUtils.register(
                    ctx,
                    OrevolutionKeys.ConfiguredFeatures.BLACKSTONE_PILLAR,
                    PILLAR.get(),
                    new PillarFeature.PillarConfiguration(
                            SimpleWeightedRandomList.<PillarFeature.PillarSize>builder()
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 2),
                                                    UniformInt.of(4, 5)
                                            ),
                                            7
                                    )
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 2),
                                                    UniformInt.of(5, 7)
                                            ),
                                            1
                                    )
                                    .build(),
                            BlockStateProvider.simple(RegBlocks.RHYOLITE.get()),
                            BlockStateProvider.simple(Blocks.BLACKSTONE),
                            BlockStateProvider.simple(Blocks.BLACKSTONE)
                    )
            );

            FeatureUtils.register(
                    ctx,
                    OrevolutionKeys.ConfiguredFeatures.RHYOLITE_PILLAR,
                    PILLAR.get(),
                    new PillarFeature.PillarConfiguration(
                            SimpleWeightedRandomList.<PillarFeature.PillarSize>builder()
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 2),
                                                    UniformInt.of(4, 5)
                                            ),
                                            7
                                    )
                                    .add(
                                            new PillarFeature.PillarSize(
                                                    UniformInt.of(1, 2),
                                                    UniformInt.of(5, 7)
                                            ),
                                            1
                                    )
                                    .build(),
                            BlockStateProvider.simple(Blocks.GRANITE),
                            BlockStateProvider.simple(Blocks.TUFF),
                            BlockStateProvider.simple(RegBlocks.RHYOLITE.get())
                    )
            );

            RandomizedIntStateProvider randomizedintstateprovider = new RandomizedIntStateProvider(
                    new WeightedStateProvider(
                            SimpleWeightedRandomList.<BlockState>builder()
                                    .add(RegBlocks.VINNELIO.get().defaultBlockState(), 6)
                                    .add(RegBlocks.VINNELIO.get().defaultBlockState().setValue(IVinnelio.FRUIT, Boolean.valueOf(true)), 1)
                    ),
                    VinnelioBlock.AGE,
                    UniformInt.of(23, 25)
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.VINNELIO_VINES, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                    List.of(BlockColumnConfiguration.layer(
                                    new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                            .add(UniformInt.of(0, 10), 2)
                                            .add(UniformInt.of(0, 2), 5)
                                            .add(UniformInt.of(0, 5), 14)
                                            .build()),
                                    BlockStateProvider.simple(RegBlocks.VINNELIO_PLANT.get().defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(ConstantInt.of(1), randomizedintstateprovider)
                    ),
                    Direction.DOWN,
                    BlockPredicate.ONLY_IN_AIR_PREDICATE,
                    true)
            );

            FeatureUtils.register(ctx, OrevolutionKeys.ConfiguredFeatures.CHERT_DISK, Feature.DISK,
                    new DiskConfiguration(
                            RuleBasedBlockStateProvider.simple(RegBlocks.CHERT.get()),
                            BlockPredicate.matchesBlocks(RegBlocks.RHYOLITE.get()),
                            UniformInt.of(2, 6),
                            2
                    )
            );

        }

        private static void registerOre(
                BootstrapContext<ConfiguredFeature<?, ?>> ctx,
                ResourceKey<ConfiguredFeature<?, ?>> name,
                OreType type,
                List<Block> variants,
                int size,
                float discardChance
        ) {
            List<OreConfiguration.TargetBlockState> targets = new ArrayList<>();

            for (int i = 0; i < Math.min(type.targets().size(), variants.size()); i++) {
                targets.add(
                        OreConfiguration.target(
                                type.targets().get(i),
                                variants.get(i).defaultBlockState()
                        )
                );
            }

            ctx.register(
                    name,
                    new ConfiguredFeature<>(
                            Feature.ORE,
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

        public static ResourceKey<PlacedFeature> create(String name) {
            return ALL.computeIfAbsent(
                    name,
                    n -> ResourceKey.create(
                            Registries.PLACED_FEATURE,
                            lc(n)
                    )
            );
        }

        public static ResourceKey<PlacedFeature> PYRITE_ORE = create("pyrite_ore");
        public static ResourceKey<PlacedFeature> PYRITE_ORE_EXTRA = create("pyrite_ore_extra");

        public static ResourceKey<PlacedFeature> TIN_ORE_EXTRA = create("tin_ore_extra");

        public static ResourceKey<PlacedFeature> TIN_ORE_MIDDLE = create("tin_ore_middle");
        public static ResourceKey<PlacedFeature> TIN_ORE_UPPER = create("tin_ore_upper");
        public static ResourceKey<PlacedFeature> TIN_ORE_SMALL = create("tin_ore_small");

        public static ResourceKey<PlacedFeature> CASSITERITE_ORE_EXTRA = create("cassiterite_ore_extra");

        public static ResourceKey<PlacedFeature> CASSITERITE_ORE_OVERWORLD = create("cassiterite_ore_overworld");
        public static ResourceKey<PlacedFeature> CASSITERITE_ORE_NETHER = create("cassiterite_ore_nether");

        public static ResourceKey<PlacedFeature> PLATINUM_ORE_EXTRA = create("platinum_ore_extra");

        public static ResourceKey<PlacedFeature> PLATINUM_ORE_SMALL = create("platinum_ore_small");
        public static ResourceKey<PlacedFeature> PLATINUM_ORE_MEDIUM = create("platinum_ore_medium");
        public static ResourceKey<PlacedFeature> PLATINUM_ORE_LARGE = create("platinum_ore_large");
        public static ResourceKey<PlacedFeature> PLATINUM_ORE_BURIED = create("platinum_ore_buried");

        public static ResourceKey<PlacedFeature> TUNGSTEN_ORE_EXTRA = create("tungsten_ore_extra");

        public static ResourceKey<PlacedFeature> TUNGSTEN_ORE_HIGHER = create("tungsten_ore_higher");
        public static ResourceKey<PlacedFeature> TUNGSTEN_ORE_LOWER = create("tungsten_ore_lower");
        public static ResourceKey<PlacedFeature> TUNGSTEN_ORE_SMALL = create("tungsten_ore_small");

        public static ResourceKey<PlacedFeature> NETHER_EXPERIENCE_ORE = create("nether_experience_ore");
        public static ResourceKey<PlacedFeature> END_EXPERIENCE_ORE = create("end_experience_ore");

        public static ResourceKey<PlacedFeature> LARGE_CHERT = create("large_chert");

        public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {
            HolderGetter<ConfiguredFeature<?, ?>> features = ctx.lookup(Registries.CONFIGURED_FEATURE);

            registerOrePlacement(ctx, features, PYRITE_ORE, 18, 0, 319);
            registerOrePlacement(ctx, features, PYRITE_ORE_EXTRA, 5, 0, 319);

            registerOrePlacement(ctx, features, Configured.TIN_ORE, TIN_ORE_MIDDLE, 25, 0, 192);
            registerOrePlacement(ctx, features, Configured.TIN_ORE, TIN_ORE_UPPER, 120, 80, 384);
            registerOrePlacement(ctx, features, TIN_ORE_SMALL, 18, 0, 319);
            registerOrePlacement(ctx, features, TIN_ORE_EXTRA, 20, 0, 319);

            registerRareOrePlacement(ctx, features, Configured.CASSITERITE_ORE_SMALL, CASSITERITE_ORE_OVERWORLD, 10, -64, 0);
            registerOrePlacement(ctx, features, Configured.CASSITERITE_ORE, CASSITERITE_ORE_NETHER, 4, 78, 128);
            registerOrePlacement(ctx, features, CASSITERITE_ORE_EXTRA, 4, 0, 319);

            registerOrePlacement(ctx, features, PLATINUM_ORE_EXTRA, 11, VerticalAnchor.absolute(-64), VerticalAnchor.absolute(4), true);

            registerOrePlacement(ctx, features, PLATINUM_ORE_SMALL, 9, VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80), false);
            registerOrePlacement(ctx, features, PLATINUM_ORE_MEDIUM, 11, VerticalAnchor.absolute(-64), VerticalAnchor.absolute(4), true);
            registerRareOrePlacement(ctx, features, PLATINUM_ORE_LARGE, 16, VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80));
            registerOrePlacement(ctx, features, PLATINUM_ORE_BURIED, 6, VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80), false);

            registerOrePlacement(ctx, features, Configured.TUNGSTEN_ORE, TUNGSTEN_ORE_LOWER, 13, 0, 32);
            registerOrePlacement(ctx, features, Configured.TUNGSTEN_ORE, TUNGSTEN_ORE_HIGHER, 1, 80, 128);
            registerOrePlacement(ctx, features, TUNGSTEN_ORE_EXTRA, 4, 0, 128);

            registerOrePlacement(ctx, features, NETHER_EXPERIENCE_ORE, 7, 0, 30);
            registerOrePlacement(ctx, features, END_EXPERIENCE_ORE, 9, 0, 60);

            registerMeteoritePlacementTop(ctx, features, "meteorite_high", 120, 180);
            registerMeteoritePlacementRange(ctx, features, "meteorite_low", 210, 95, 140);

            PlacementUtils.register(ctx, LARGE_CHERT, features.getOrThrow(Configured.LARGE_CHERT),
                    CountPlacement.of(UniformInt.of(10, 48)),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome()
            );

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.CELESTITE_GEODE,
                    features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.CELESTITE_GEODE),
                    RarityFilter.onAverageOnceEvery(30),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)),
                    BiomeFilter.biome()
            );

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.AMETHYST_CRATER,
                    features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.AMETHYST_CRATER),
                    CountPlacement.of(14),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome(),
                    RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.matchesBlocks(
                                    Blocks.STONE,
                                    Blocks.DEEPSLATE,
                                    Blocks.COBBLED_DEEPSLATE,
                                    Blocks.COBBLESTONE,
                                    Blocks.DIRT
                            )),
                            BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                            16
                    )
            );

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.CELESTITE_CRATER,
                    features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.CELESTITE_CRATER),
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome(),
                    RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.matchesBlocks(
                                    Blocks.STONE,
                                    Blocks.DEEPSLATE,
                                    Blocks.COBBLED_DEEPSLATE,
                                    Blocks.COBBLESTONE,
                                    Blocks.DIRT
                            )),
                            BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                            16
                    )
            );


            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.GOLD_CRATER,
                    features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.GOLD_CRATER),
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome(),
                    RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.matchesBlocks(
                                    Blocks.STONE,
                                    Blocks.DEEPSLATE,
                                    Blocks.COBBLED_DEEPSLATE,
                                    Blocks.COBBLESTONE,
                                    Blocks.AMETHYST_BLOCK,
                                    Blocks.SMOOTH_BASALT,
                                    Blocks.CALCITE,
                                    Blocks.DIRT
                            )),
                            BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                            16
                    )
            );

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.PLATINUM_CRATER,
                    features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.PLATINUM_CRATER),
                    CountPlacement.of(2),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome(),
                    RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.matchesBlocks(
                                    Blocks.STONE,
                                    Blocks.DEEPSLATE,
                                    Blocks.COBBLED_DEEPSLATE,
                                    Blocks.COBBLESTONE,
                                    Blocks.AMETHYST_BLOCK,
                                    Blocks.SMOOTH_BASALT,
                                    Blocks.CALCITE,
                                    Blocks.DIRT,
                                    Blocks.DIORITE,
                                    Blocks.TUFF
                            )),
                            BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                            16
                    )
            );

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.BOULDER, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.BOULDER),
                    CountPlacement.of(UniformInt.of(54, 126)),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.FORGOTTEN_SPRING, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.FORGOTTEN_SPRING),
                    CountPlacement.of(4),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(VeryBiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(32), 32)),
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.CHERT_PATCH, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.CHERT_PATCH),
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.QUARTZOLITE_DISK, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.QUARTZOLITE_DISK),
                    CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR, Blocks.LAVA), 8));

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.CHERT_DISK, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.CHERT_DISK),
                    CountPlacement.of(21),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR, Blocks.LAVA), 8));

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.FORGOTTEN_VEGETATION, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.FORGOTTEN_VEGETATION),
                    CountPlacement.of(140),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                            BlockPredicate.allOf(
                                    BlockPredicate.unobstructed(),
                                    BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), RegBlocks.RHYOLITE.get())
                            ), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.VINNELIO_VINES, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.VINNELIO_VINES),
                    CountPlacement.of(76),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.hasSturdyFace(Direction.DOWN),
                            BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.MOONSTONE_PILLAR, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.MOONSTONE_PILLAR),
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                            BlockPredicate.allOf(
                                    BlockPredicate.solid(),
                                    BlockPredicate.unobstructed(new Vec3i(0, 1, 0)),
                                    BlockPredicate.matchesBlocks(RegBlocks.RHYOLITE.get())
                            ), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.MOONSTONE_PILLAR_NETHER, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.MOONSTONE_PILLAR_NETHER),
                    CountPlacement.of(2),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                            BlockPredicate.allOf(
                                    BlockPredicate.solid(),
                                    BlockPredicate.unobstructed(new Vec3i(0, 1, 0)),
                                    BlockPredicate.matchesBlocks(Blocks.BASALT)
                            ), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.BLACKSTONE_PILLAR, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.BLACKSTONE_PILLAR),
                    CountPlacement.of(15),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                            BlockPredicate.allOf(
                                    BlockPredicate.solid(),
                                    BlockPredicate.unobstructed(new Vec3i(0, 1, 0)),
                                    BlockPredicate.matchesBlocks(RegBlocks.RHYOLITE.get())
                            ), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.RHYOLITE_PILLAR, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.RHYOLITE_PILLAR),
                    CountPlacement.of(17),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                            BlockPredicate.allOf(
                                    BlockPredicate.solid(),
                                    BlockPredicate.unobstructed(new Vec3i(0, 1, 0))
                            ), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    BiomeFilter.biome());

            PlacementUtils.register(ctx, OrevolutionKeys.PlacedFeatures.MOONSTONE_PILLAR_LIQUID, features.getOrThrow(OrevolutionKeys.ConfiguredFeatures.MOONSTONE_PILLAR),
                    CountPlacement.of(20),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                            BlockPredicate.allOf(
                                    BlockPredicate.solid(),
                                    BlockPredicate.unobstructed(new Vec3i(0, 1, 0)),
                                    BlockPredicate.matchesBlocks(RegBlocks.RHYOLITE.get())
                            ), BlockPredicate.matchesBlocks(Blocks.WATER, Blocks.LAVA), 12),
                    BiomeFilter.biome());
        }

        private static void registerMeteoritePlacementTop(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, String name, int rarity, int minY) {
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

        private static void registerMeteoritePlacementRange(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, String name, int rarity, int minY, int maxY) {
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

        private static void registerOrePlacement(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, ResourceKey<PlacedFeature> name, int count, int minY, int maxY) {
            ctx.register(
                    name,
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name.location().getPath())),
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

        private static void registerOrePlacement(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, ResourceKey<PlacedFeature> name, int count, VerticalAnchor minY, VerticalAnchor maxY, boolean uniform) {
            ctx.register(
                    name,
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name.location().getPath())),
                            commonOrePlacement(
                                    count,
                                    !uniform? HeightRangePlacement.triangle(
                                            minY,
                                            maxY
                                    )
                                            :
                                    HeightRangePlacement.uniform(
                                            minY,
                                            maxY
                                    )
                            )
                    )
            );
        }

        private static void registerOrePlacement(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, ResourceKey<PlacedFeature> name, int count, int minY, int maxY) {
            ctx.register(
                    name,
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(configuredFeature.location().getPath())),
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

        private static void registerRareOrePlacement(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, ResourceKey<PlacedFeature> name, int rarity, int minY, int maxY) {
            ctx.register(
                    name,
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name.location().getPath())),
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

        private static void registerRareOrePlacement(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, ResourceKey<PlacedFeature> name, int count, int minY, int maxY) {
            ctx.register(
                    name,
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(configuredFeature.location().getPath())),
                            rareOrePlacement(
                                    count,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(minY),
                                            VerticalAnchor.absolute(maxY)
                                    )
                            )
                    )
            );
        }

        private static void registerRareOrePlacement(BootstrapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, ResourceKey<PlacedFeature> name, int rarity, VerticalAnchor minY, VerticalAnchor maxY) {
            ctx.register(
                    name,
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name.location().getPath())),
                            rareOrePlacement(
                                    rarity,
                                    HeightRangePlacement.triangle(
                                            minY,
                                            maxY
                                    )
                            )
                    )
            );
        }

        private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
            return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
        }

        private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
            return orePlacement(CountPlacement.of(count), heightRange);
        }

        private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
            return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
        }
    }
}