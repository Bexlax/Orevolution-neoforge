package net.bexla.orevolution.content.worldgen;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.bexla.orevolution.Orevolution.lc;
import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.BiomeModifiers.*;
import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.Biomes.FORGOTTEN_CAVE;
import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.Biomes.GEODE_GARDENS;
import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.PlacedFeatures.*;

public class GENBiomeModifier {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        addFeature(context, RegFeatures.Placed.TIN_ORE_MIDDLE, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.TIN_ORE_UPPER, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.TIN_ORE_SMALL, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.TIN_ORE_EXTRA, Tags.Biomes.IS_COLD_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);

        addFeature(context, RegFeatures.Placed.CASSITERITE_ORE_OVERWORLD, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.CASSITERITE_ORE_NETHER, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.CASSITERITE_ORE_EXTRA, Tags.Biomes.IS_NETHER_FOREST, GenerationStep.Decoration.UNDERGROUND_ORES);

        addFeature(context, RegFeatures.Placed.PLATINUM_ORE_SMALL, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.PLATINUM_ORE_MEDIUM, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.PLATINUM_ORE_LARGE, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.PLATINUM_ORE_BURIED, BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, RegFeatures.Placed.PLATINUM_ORE_EXTRA, BiomeTags.IS_BADLANDS, GenerationStep.Decoration.UNDERGROUND_ORES);

        addFeature(context, RegFeatures.Placed.TUNGSTEN_ORE_LOWER, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_DECORATION);
        addFeature(context, RegFeatures.Placed.TUNGSTEN_ORE_HIGHER, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_DECORATION);
        addFeature(context, RegFeatures.Placed.TUNGSTEN_ORE_EXTRA, BiomeTags.HAS_NETHER_FOSSIL, GenerationStep.Decoration.UNDERGROUND_DECORATION);

        addFeature(context, MOONSTONE_PILLAR_NETHER, OrevolutionTags.Misc.HAS_MOONSTONE_NETHER, GenerationStep.Decoration.UNDERGROUND_DECORATION);

        addFeature(context, RegFeatures.Placed.PYRITE_ORE, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_DECORATION);
        addFeature(context, RegFeatures.Placed.PYRITE_ORE_EXTRA, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_DECORATION);

        addFeature(context, RegFeatures.Placed.END_EXPERIENCE_ORE, BiomeTags.IS_END, GenerationStep.Decoration.UNDERGROUND_DECORATION);
        addFeature(context, RegFeatures.Placed.NETHER_EXPERIENCE_ORE, BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_DECORATION);

        addFeature(context, "meteorite_low", BiomeTags.IS_END, Decoration.RAW_GENERATION);
        addFeature(context, "meteorite_high", BiomeTags.IS_END, Decoration.RAW_GENERATION);

        addFeature(context, "celestite_geode", BiomeTags.IS_OVERWORLD, Decoration.LOCAL_MODIFICATIONS);

        registerForgottenCaves(context);
        registerGeodeGardens(context);
    }

    private static void addFeature(BootstrapContext<BiomeModifier> context, ResourceKey<PlacedFeature> feature, TagKey<Biome> biomes, Decoration step) {
        register(context, "add_feature/" + feature.location().getPath(), () -> new BiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, feature), step));
    }

    private static void addFeature(BootstrapContext<BiomeModifier> context, String feature, TagKey<Biome> biomes, Decoration step) {
        register(context, "add_feature/" + feature, () -> new BiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, RegFeatures.Placed.create(feature)), step));
    }
    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc(name)), modifier.get());
    }

    private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
    }

    // Credits to naterbobber, a lot of the code below comes from their work, Darker Depths.
    // https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/worldgen/biomes/DDBiomeModifiers.java

    private static void registerForgottenCaves(BootstrapContext<BiomeModifier> context) {
        context.register(ADD_FORGOTTEN_CAVES_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                getBiome(context, FORGOTTEN_CAVE),
                getPlacedFeature(
                        context,
                        CHERT_DISK,
                        CHERT_PATCH,
                        QUARTZOLITE_DISK
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_FORGOTTEN_CAVES_RAW_GENERATION, new BiomeModifiers.AddFeaturesBiomeModifier(
                getBiome(context, FORGOTTEN_CAVE),
                getPlacedFeature(
                        context,
                        BOULDER,
                        RegFeatures.Placed.LARGE_CHERT
                ),
                GenerationStep.Decoration.RAW_GENERATION)
        );
        context.register(ADD_FORGOTTEN_CAVES_VEGETAL_FEATURES, new BiomeModifiers.AddFeaturesBiomeModifier(
                getBiome(context, FORGOTTEN_CAVE),
                getPlacedFeature(
                        context,
                        RHYOLITE_PILLAR,
                        FORGOTTEN_VEGETATION,
                        BLACKSTONE_PILLAR,
                        VINNELIO_VINES,
                        MOONSTONE_PILLAR,
                        MOONSTONE_PILLAR_LIQUID
                ),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static void registerGeodeGardens(BootstrapContext<BiomeModifier> context) {
        context.register(ADD_GEODE_GARDENS_LOCAL_MODIFICATIONS, new BiomeModifiers.AddFeaturesBiomeModifier(
                getBiome(context, GEODE_GARDENS),
                getPlacedFeature(
                        context,
                        CELESTITE_CRATER,
                        AMETHYST_CRATER,
                        GOLD_CRATER,
                        PLATINUM_CRATER
                ),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS
        ));
    }


    @SafeVarargs
    @NotNull
    private static HolderSet.Direct<PlacedFeature> getPlacedFeature(BootstrapContext<BiomeModifier> context, ResourceKey<PlacedFeature>... placedFeature) {
        return HolderSet.direct(Stream.of(placedFeature).map(resourceKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(resourceKey)).collect(Collectors.toList()));
    }

    private static HolderSet.@NotNull Direct<Biome> getBiome(BootstrapContext<BiomeModifier> bootstapContext, ResourceKey<Biome> biome) {
        return HolderSet.direct(bootstapContext.lookup(Registries.BIOME).getOrThrow(biome));
    }
}
